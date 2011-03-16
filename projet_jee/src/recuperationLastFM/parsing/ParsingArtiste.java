package recuperationLastFM.parsing;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import metier.oeuvres.Album;
import metier.oeuvres.Artiste;

import org.apache.http.client.methods.HttpGet;
import org.xml.sax.SAXException;

import recuperationLastFM.appelHttp.AppelHTTP;
import recuperationLastFM.handler.AlbumInfoHandler;
import recuperationLastFM.handler.ArtisteInfoHandler;
import recuperationLastFM.handler.ArtisteSearchHandler;

import controleur.Controleur;


public class ParsingArtiste extends Parsing {



	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/

	public List<Artiste> parser(String motCleArtiste) {
		List<Artiste> lstArtiste = new ArrayList<Artiste>();
		try{

			// création d'une fabrique de parseurs SAX
			SAXParserFactory fabrique = SAXParserFactory.newInstance();

			// création d'un parseur SAX
			SAXParser parseur = fabrique.newSAXParser();



			//artiste
			//on veut rechercher un artiste:              http://ws.audioscrobbler.com/2.0/?method=artist.search&artist=cher&api_key=ca33590ba46941a9186c4777b5046445
			//on veut récuperer les infos sur un artiste: http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=Cher&api_key=ca33590ba46941a9186c4777b5046445



			//on crée la requete avec le mot clé artiste
			HttpGet requeteGet = new HttpGet("http://ws.audioscrobbler.com/2.0/?method=artist.search&artist="+ motCleArtiste + "&api_key=ca33590ba46941a9186c4777b5046445"); 

			//on recupère le nombre total de pages de résultats disponibles
			int nbPagesResultats = calculerNbPages(requeteGet);
			//on limite le nombre de pages à un certain nombre: getNbpagesMax
			if(nbPagesResultats>getNbpagesmax()){
				nbPagesResultats=getNbpagesmax();
			}

			for(int i=1;i<=nbPagesResultats;i++){
				requeteGet = new HttpGet("http://ws.audioscrobbler.com/2.0/?method=artist.search&artist="+ motCleArtiste + "&api_key=ca33590ba46941a9186c4777b5046445&page="+i); 

				//on recupere sous forme d'input stream le resultat de la requete
				InputStream input = AppelHTTP.recupererDonnees(requeteGet);

				//on cree un gestionnaire d'artistes pour parser le resultat de la requete
				ArtisteSearchHandler gestionnaireArtiste = new ArtisteSearchHandler();
				parseur.parse(input, gestionnaireArtiste);




				//on récupère une liste des artistes parsés que l'on ajoute à notre liste d'artistes
				lstArtiste.addAll(gestionnaireArtiste.getLstArtistes());


				/*//affichage du resultat pour verification
		        for (Iterator iterator = lstArtiste.iterator(); iterator
						.hasNext();) {
					Artiste artiste = (Artiste) iterator.next();
					System.out.println(artiste);
				}*/


			}

			//System.out.println("fin du traitement");
			//System.out.println("taille liste artistes " + lstArtiste.size());
			//System.out.println("nb de pages artistes lues " + nbPagesResultats);
			

		}catch(ParserConfigurationException pce){
			System.out.println("Erreur de configuration du parseur");
			System.out.println("Lors de l'appel à SAXParser.newSAXParser()");
		}catch(SAXException se){
			System.out.println("Erreur de parsing");
			System.out.println("Lors de l'appel à parse()");
			se.printStackTrace();
		}catch(IOException ioe){
			System.out.println("Erreur d'entrée/sortie");
			System.out.println("Lors de l'appel à parse()");
		}
		return lstArtiste;
	}

	
	public Artiste parserInfos(Artiste artiste) {
		String marequete = "";

		try{
			// création d'une fabrique de parseurs SAX
			SAXParserFactory fabrique = SAXParserFactory.newInstance();

			// création d'un parseur SAX
			SAXParser parseur = fabrique.newSAXParser();

			//artiste
			//on veut rechercher un artiste:              http://ws.audioscrobbler.com/2.0/?method=artist.search&artist=cher&api_key=ca33590ba46941a9186c4777b5046445
			//on veut récuperer les infos sur un artiste: http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=Cher&api_key=ca33590ba46941a9186c4777b5046445



			//on crée la requete avec le nom de l'artiste
			//System.out.println("ma requete pour le get info");
			marequete = "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist="+ artiste.getName()+"&api_key=ca33590ba46941a9186c4777b5046445";
			
			marequete = marequete.replaceAll(" ", "+");
			
				
			
			//System.out.println(marequete);
			
			HttpGet requeteGet = new HttpGet(marequete);


			//on recupere sous forme d'input stream le resultat de la requete
			InputStream input = AppelHTTP.recupererDonnees(requeteGet);

			//on cree un gestionnaire d'albums pour parser le resultat de la requete
			ArtisteInfoHandler gestionnaireArtiste = new ArtisteInfoHandler(artiste);
			parseur.parse(input, gestionnaireArtiste);


			//TODO semble pas très propre de recupérer ainsi l'album...
			//on récupère l'artiste mis à jour après avoir parsé les infos complémentaires
			artiste = gestionnaireArtiste.getArtiste();


			//System.out.println("fin du traitement");
						


		}catch(ParserConfigurationException pce){
			System.out.println("Erreur de configuration du parseur");
			System.out.println("Lors de l'appel à SAXParser.newSAXParser()");
		}catch(SAXException se){
			System.out.println("Erreur de parsing");
			System.out.println("Lors de l'appel à parse()");
			se.printStackTrace();
		}catch(IOException ioe){
			System.out.println("Erreur d'entrée/sortie");
			System.out.println("Lors de l'appel à parse()");
		}catch(IllegalArgumentException iae){
			System.out.println("Erreur dans l'expression de la requete");
			System.out.println("Lors de l'appel à httpGet");
			Controleur.getInstanceuniquecontroleur().
			ajouterProbleme("Probleme " + + Controleur.getInstanceuniquecontroleur().getListeProblemesRencontres().size(),
					"Erreur dans l'expression de la requete lors de l'appel à httpGet " + "\n " +
					"pb requete: " + marequete + "\n " +
					"pb artiste: " + artiste.getName()+"\n ");
		}

		return artiste;
	}


}
