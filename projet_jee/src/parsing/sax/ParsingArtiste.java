package parsing.sax;

import handler.sax.ArtisteHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import metier.Artiste;

import org.apache.http.client.methods.HttpGet;
import org.xml.sax.SAXException;

import appelHttp.AppelHTTP;
 
public class ParsingArtiste implements IParsing {

	
	
	@Override
	public void parser() {
		// TODO Auto-generated method stub

        try{
	         // création d'une fabrique de parseurs SAX
	         SAXParserFactory fabrique = SAXParserFactory.newInstance();
				
	         // création d'un parseur SAX
	         SAXParser parseur = fabrique.newSAXParser();
			
	         
	         
	         
	         // lecture d'un fichier XML avec un DefaultHandler
      
	         //artiste
	         //on veut rechercher un artiste:              http://ws.audioscrobbler.com/2.0/?method=artist.search&artist=cher&api_key=ca33590ba46941a9186c4777b5046445
	         //on veut récuperer les infos sur un artiste: http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=Cher&api_key=ca33590ba46941a9186c4777b5046445
	         

	        	  

	         HttpGet requeteGet = new HttpGet("http://ws.audioscrobbler.com/2.0/?method=artist.search&artist=cher&api_key=ca33590ba46941a9186c4777b5046445"); 
	 		
	         InputStream fichier = AppelHTTP.recupererDonnees(requeteGet);
	         
	         
	         ArtisteHandler gestionnaire = new ArtisteHandler();
	         parseur.parse(fichier, gestionnaire);
	         
	         
			
	         List<Artiste> lstArtiste = gestionnaire.getLstArtistes();
	         for (Iterator iterator = lstArtiste.iterator(); iterator
					.hasNext();) {
				Artiste artiste = (Artiste) iterator.next();
				System.out.println(artiste);
			}
	         
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
