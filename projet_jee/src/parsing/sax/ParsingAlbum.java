package parsing.sax;

import handler.sax.AlbumSearchHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.client.methods.HttpGet;
import org.xml.sax.SAXException;

import appelHttp.AppelHTTP;
import metier.Album;

public class ParsingAlbum implements IParsing {


 
	
	@Override
	public void parser() {

        try{
	         // création d'une fabrique de parseurs SAX
	         SAXParserFactory fabrique = SAXParserFactory.newInstance();
				
	         // création d'un parseur SAX
	         SAXParser parseur = fabrique.newSAXParser();
         
	         
	         // lecture d'un fichier XML avec un DefaultHandler
	         //File fichier = new File("C:\\Application\\workspace\\Projet3A\\src\\fr\\ensai\\exemple\\parsing\\sax\\exemple.xml");
	       
	         //album
	         //on veut rechercher un album:              http://ws.audioscrobbler.com/2.0/?method=album.search&album=believe&api_key=ca33590ba46941a9186c4777b5046445
	         //on veut récuperer les infos sur un album: http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=ca33590ba46941a9186c4777b5046445&artist=Cher&album=Believe
	         
	       

	        	  
	         
	        	 
	         
		         HttpGet requeteGet = new HttpGet("http://ws.audioscrobbler.com/2.0/?method=album.search&album=believe&api_key=ca33590ba46941a9186c4777b5046445"); 
		 		
		         
		         InputStream fichier = AppelHTTP.recupererDonnees(requeteGet);
		         
		         
		         AlbumSearchHandler gestionnaire = new AlbumSearchHandler();
		         
		         parseur.parse(fichier, gestionnaire);
		         
		         
				
		         List<Album> lstAlbum = gestionnaire.getLstAlbums();
		         for (Iterator iterator = lstAlbum.iterator(); iterator
						.hasNext();) {
					Album album = (Album) iterator.next();
					System.out.println(album);
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
