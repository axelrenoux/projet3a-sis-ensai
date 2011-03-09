package parsing.sax;

import handler.sax.ChansonHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import metier.Chanson;

import org.apache.http.client.methods.HttpGet;
import org.xml.sax.SAXException;

import appelHttp.AppelHTTP;


public class ParsingChanson implements IParsing{

	@Override
	public void parser() {
		// TODO Auto-generated method stub

		try{
			// création d'une fabrique de parseurs SAX
			SAXParserFactory fabrique = SAXParserFactory.newInstance();

			// création d'un parseur SAX
			SAXParser parseur = fabrique.newSAXParser();




			// lecture d'un fichier XML avec un DefaultHandler
			//File fichier = new File("C:\\Application\\workspace\\Projet3A\\src\\fr\\ensai\\exemple\\parsing\\sax\\exemple.xml");

	
			//chanson
			//on veut rechercher une chanson: 				http://ws.audioscrobbler.com/2.0/?method=track.search&track=Believe&api_key=ca33590ba46941a9186c4777b5046445
			//on veut récuperer les infos sur une chanson: http://ws.audioscrobbler.com/2.0/?method=track.getinfo&api_key=ca33590ba46941a9186c4777b5046445&artist=cher&track=believe

		
			

			HttpGet requeteGet = new HttpGet("http://ws.audioscrobbler.com/2.0/?method=track.search&track=Believe&api_key=ca33590ba46941a9186c4777b5046445&limit=100"); 

			InputStream fichier = AppelHTTP.recupererDonnees(requeteGet);


			ChansonHandler gestionnaire = new ChansonHandler();
			parseur.parse(fichier, gestionnaire);
			


			List<Chanson> lstChanson = gestionnaire.getLstChanson();
			for (Iterator iterator = lstChanson.iterator(); iterator
			.hasNext();) {
				Chanson chanson = (Chanson) iterator.next();
				System.out.println(chanson);
			}
			
			System.out.println("fini");

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
	}

}
