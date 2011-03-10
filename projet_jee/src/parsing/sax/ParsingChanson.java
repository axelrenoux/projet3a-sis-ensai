package parsing.sax;

import handler.sax.ChansonHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import metier.Chanson;

import org.apache.http.client.methods.HttpGet;
import org.xml.sax.SAXException;

import appelHttp.AppelHTTP;


public class ParsingChanson extends Parsing{

	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	public List<Chanson> parser(String motCleChanson) {
		List<Chanson> lstChanson = new ArrayList<Chanson>();
		try{
			// création d'une fabrique de parseurs SAX
			SAXParserFactory fabrique = SAXParserFactory.newInstance();

			// création d'un parseur SAX
			SAXParser parseur = fabrique.newSAXParser();

	
			//chanson
			//on veut rechercher une chanson: 				http://ws.audioscrobbler.com/2.0/?method=track.search&track=Believe&api_key=ca33590ba46941a9186c4777b5046445
			//on veut récuperer les infos sur une chanson: http://ws.audioscrobbler.com/2.0/?method=track.getinfo&api_key=ca33590ba46941a9186c4777b5046445&artist=cher&track=believe

			
			
			//on crée la requete avec le mot clé chanson
			HttpGet requeteGet = new HttpGet("http://ws.audioscrobbler.com/2.0/?method=track.search&track=" + motCleChanson +"&api_key=ca33590ba46941a9186c4777b5046445"); 

			//on recupère le nombre total de pages de résultats disponibles
			int nbPagesResultats = calculerNbPages(requeteGet);
			//on limite le nombre de pages à un certain nombre: getNbpagesMax
			if(nbPagesResultats>getNbpagesmax()){
				nbPagesResultats=getNbpagesmax();
			}
			
			//on va effectuer une requete pour chacune des nbPagesResultats premières pages de resultats
			//pour recuperer les resultats
			for(int i=1;i<=nbPagesResultats;i++){
				
				requeteGet = new HttpGet("http://ws.audioscrobbler.com/2.0/?method=track.search&track=" + motCleChanson +"&api_key=ca33590ba46941a9186c4777b5046445&page="+i); 

				//on recupere sous forme d'input stream le resultat de la requete
				InputStream input = AppelHTTP.recupererDonnees(requeteGet);
	
				//on cree un gestionnaire de chansons pour parser le resultat de la requete
				ChansonHandler gestionnaire = new ChansonHandler();
				parseur.parse(input, gestionnaire);
				
	
				//on récupère une liste des chansons parsées que l'on ajoute à notre liste de chansons
				lstChanson.addAll(gestionnaire.getLstChanson());
				
				//affichage du resultat pour verification
				/*for (Iterator iterator = lstChanson.iterator(); iterator
				.hasNext();) {
					Chanson chanson = (Chanson) iterator.next();
					System.out.println(chanson);
				}*/
			}
			
			System.out.println("fin du traitement");
			System.out.println("taille liste chanson " + lstChanson.size());
			System.out.println("nb de pages chanson lues " + nbPagesResultats);
			
			 

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
		
		return lstChanson;
	}

}
