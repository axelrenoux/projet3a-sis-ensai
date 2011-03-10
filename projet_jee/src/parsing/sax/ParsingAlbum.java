package parsing.sax;

import handler.sax.AlbumSearchHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.client.methods.HttpGet;
import org.xml.sax.SAXException;

import appelHttp.AppelHTTP;
import metier.Album;

public class ParsingAlbum extends Parsing {

	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	

	public List<Album> parser(String motCleAlbum) {
		List<Album> lstAlbum = new ArrayList<Album>();
		
		
		try{
			// création d'une fabrique de parseurs SAX
			SAXParserFactory fabrique = SAXParserFactory.newInstance();

			// création d'un parseur SAX
			SAXParser parseur = fabrique.newSAXParser();

			//album
			//on veut rechercher un album:              http://ws.audioscrobbler.com/2.0/?method=album.search&album=believe&api_key=ca33590ba46941a9186c4777b5046445
			//on veut récuperer les infos sur un album: http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=ca33590ba46941a9186c4777b5046445&artist=Cher&album=Believe


		
			//on crée la requete avec le mot clé album
			HttpGet requeteGet = new HttpGet("http://ws.audioscrobbler.com/2.0/?method=album.search&album="+ motCleAlbum +"&api_key=ca33590ba46941a9186c4777b5046445"); 

			//on recupère le nombre total de pages de résultats disponibles
			int nbPagesResultats = calculerNbPages(requeteGet);
			//on limite le nombre de pages à un certain nombre: getNbpagesMax
			if(nbPagesResultats>getNbpagesmax()){
				nbPagesResultats=getNbpagesmax();
			}
			
			
			for(int i=1;i<=nbPagesResultats;i++){
				
				requeteGet = new HttpGet("http://ws.audioscrobbler.com/2.0/?method=album.search&album="+ motCleAlbum +"&api_key=ca33590ba46941a9186c4777b5046445&page="+i); 
				//on recupere sous forme d'input stream le resultat de la requete
				InputStream input = AppelHTTP.recupererDonnees(requeteGet);
				
				//on cree un gestionnaire d'albums pour parser le resultat de la requete
				AlbumSearchHandler gestionnaireAlbum = new AlbumSearchHandler();
				parseur.parse(input, gestionnaireAlbum);
				

				//on récupère une liste des albmus parsés que l'on ajoute à notre liste d'albums
				lstAlbum.addAll(gestionnaireAlbum.getLstAlbums());
				
				//affichage du resultat pour verification
				/*for (Iterator iterator = lstAlbum.iterator(); iterator
				.hasNext();) {
					Album album = (Album) iterator.next();
					System.out.println(album);
				}*/
				
			}
			System.out.println("fin du traitement");
			System.out.println("taille liste albums "  + lstAlbum.size());
			System.out.println("nb de pages albums lues " + nbPagesResultats);
			
			
			
			
			


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
		
		return lstAlbum;

	}


	
}
