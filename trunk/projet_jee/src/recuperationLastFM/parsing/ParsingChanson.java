package recuperationLastFM.parsing;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;

import org.apache.http.client.methods.HttpGet;
import org.xml.sax.SAXException;

import recuperationLastFM.appelHttp.AppelHTTP;
import recuperationLastFM.handler.ArtisteInfoHandler;
import recuperationLastFM.handler.ChansonInfoHandler;
import recuperationLastFM.handler.ChansonSearchHandler;

import controleur.Controleur;



public class ParsingChanson extends Parsing{

	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	public List<Chanson> parser(String motCleChanson) {
		List<Chanson> lstChanson = new ArrayList<Chanson>();
		try{
			// cr�ation d'une fabrique de parseurs SAX
			SAXParserFactory fabrique = SAXParserFactory.newInstance();

			// cr�ation d'un parseur SAX
			SAXParser parseur = fabrique.newSAXParser();

	
			//chanson
			//on veut rechercher une chanson: 				http://ws.audioscrobbler.com/2.0/?method=track.search&track=Believe&api_key=ca33590ba46941a9186c4777b5046445
			//on veut r�cuperer les infos sur une chanson: http://ws.audioscrobbler.com/2.0/?method=track.getinfo&api_key=ca33590ba46941a9186c4777b5046445&artist=cher&track=believe

			
			
			//on cr�e la requete avec le mot cl� chanson
			HttpGet requeteGet = new HttpGet("http://ws.audioscrobbler.com/2.0/?method=track.search&track=" + motCleChanson +"&api_key=ca33590ba46941a9186c4777b5046445"); 

			//on recup�re le nombre total de pages de r�sultats disponibles
			int nbPagesResultats = calculerNbPages(requeteGet);
			//on limite le nombre de pages � un certain nombre: getNbpagesMax
			if(nbPagesResultats>getNbpagesmax()){
				nbPagesResultats=getNbpagesmax();
			}
			
			//on va effectuer une requete pour chacune des nbPagesResultats premi�res pages de resultats
			//pour recuperer les resultats
			for(int i=1;i<=nbPagesResultats;i++){
				
				requeteGet = new HttpGet("http://ws.audioscrobbler.com/2.0/?method=track.search&track=" + motCleChanson +"&api_key=ca33590ba46941a9186c4777b5046445&page="+i); 

				//on recupere sous forme d'input stream le resultat de la requete
				InputStream input = AppelHTTP.recupererDonnees(requeteGet);
	
				//on cree un gestionnaire de chansons pour parser le resultat de la requete
				ChansonSearchHandler gestionnaire = new ChansonSearchHandler();
				parseur.parse(input, gestionnaire);
				
	
				//on r�cup�re une liste des chansons pars�es que l'on ajoute � notre liste de chansons
				lstChanson.addAll(gestionnaire.getLstChanson());
				
				//affichage du resultat pour verification
				/*for (Iterator iterator = lstChanson.iterator(); iterator
				.hasNext();) {
					Chanson chanson = (Chanson) iterator.next();
					System.out.println(chanson);
				}*/
			}
			
			//System.out.println("fin du traitement");
			//System.out.println("taille liste chanson " + lstChanson.size());
			//System.out.println("nb de pages chanson lues " + nbPagesResultats);
			
			 

		}catch(ParserConfigurationException pce){
			System.out.println("Erreur de configuration du parseur");
			System.out.println("Lors de l'appel � SAXParser.newSAXParser()");
		}catch(SAXException se){
			System.out.println("Erreur de parsing");
			System.out.println("Lors de l'appel � parse()");
			se.printStackTrace();
		}catch(IOException ioe){
			System.out.println("Erreur d'entr�e/sortie");
			System.out.println("Lors de l'appel � parse()");
		}
		
		return lstChanson;
	}

	
	
	public Chanson parserInfos(Chanson chanson) {

		String marequete="";
		try{
			// cr�ation d'une fabrique de parseurs SAX
			SAXParserFactory fabrique = SAXParserFactory.newInstance();

			// cr�ation d'un parseur SAX
			SAXParser parseur = fabrique.newSAXParser();

			//chanson
			//on veut rechercher une chanson: 				http://ws.audioscrobbler.com/2.0/?method=track.search&track=Believe&api_key=ca33590ba46941a9186c4777b5046445
			//on veut r�cuperer les infos sur une chanson: http://ws.audioscrobbler.com/2.0/?method=track.getinfo&api_key=ca33590ba46941a9186c4777b5046445&artist=cher&track=believe



			//on cr�e la requete avec le nom de la chanson et le nom de l'artiste
			//System.out.println("ma requete pour le get info");
			marequete = "http://ws.audioscrobbler.com/2.0/?method=track.getinfo&api_key=ca33590ba46941a9186c4777b5046445&artist="+ chanson.getArtiste().getName()+"&track="+chanson.getName();

			marequete = transformationURL(marequete);

			HttpGet requeteGet = new HttpGet(marequete);


			//on recupere sous forme d'input stream le resultat de la requete
			InputStream input = AppelHTTP.recupererDonnees(requeteGet);

			//on cree un gestionnaire de chansons pour parser le resultat de la requete
			ChansonInfoHandler gestionnaireChanson = new ChansonInfoHandler(chanson);
			parseur.parse(input, gestionnaireChanson);


			//TODO semble pas tr�s propre de recup�rer ainsi l'album...
			//on r�cup�re la chanson mise � jour apr�s avoir pars� les infos compl�mentaires
			chanson = gestionnaireChanson.getChanson();


			//System.out.println("fin du traitement");
						


		}catch(ParserConfigurationException pce){
			System.out.println("Erreur de configuration du parseur");
			System.out.println("Lors de l'appel � SAXParser.newSAXParser()");
		}catch(SAXException se){
			System.out.println("Erreur de parsing");
			System.out.println("Lors de l'appel � parse()");
			se.printStackTrace();
		}catch(IOException ioe){
			System.out.println("Erreur d'entr�e/sortie");
			System.out.println("Lors de l'appel � parse()");
		}catch(IllegalArgumentException iae){
			System.out.println("Erreur dans l'expression de la requete");
			System.out.println("Lors de l'appel � httpGet");
			Controleur.getInstanceuniquecontroleur().
			ajouterProbleme("Probleme " + + Controleur.getInstanceuniquecontroleur().getListeProblemesRencontres().size(),
					"Erreur dans l'expression de la requete lors de l'appel � httpGet " + "\n " +
					"pb requete: " + marequete + "\n " +
					"pb chanson: " + chanson.getName()+"\n " +
					"pb artiste: " + chanson.getArtiste() +"\n ");			
		}


		return chanson;
	}


}
