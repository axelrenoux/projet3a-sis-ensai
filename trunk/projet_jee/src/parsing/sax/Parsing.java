/**
 * 
 */
package parsing.sax;

import handler.sax.AlbumSearchHandler;
import handler.sax.NbPagesHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.client.methods.HttpGet;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import appelHttp.AppelHTTP;


/**
 * @author Administrateur
 * Classe permettant de lancer le parsing du fichier xml
 *
 */
public abstract class Parsing {
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	

	//on souhaite recuperer au maximum 1000 pages lors de nos requetes
	private static final int nbpagesMax = 1000;
	
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	
	/**
	 * methode qui calcule le nombre total de pages
	 * de résultat d'une requete
	 * @param requeteGet
	 * @return
	 */
	public int calculerNbPages(HttpGet requeteGet){
		int nbpages =0;
		try{
		// création d'une fabrique de parseurs SAX
		SAXParserFactory fabrique = SAXParserFactory.newInstance();

		// création d'un parseur SAX
		SAXParser parseur = fabrique.newSAXParser();
		
		//on recupere sous forme d'input stream le resultat de la requete
		InputStream input = AppelHTTP.recupererDonnees(requeteGet);
		
		//on cree un gestionnaire pour parser le resultat de la requete
		NbPagesHandler gestionnaire = new NbPagesHandler();
		parseur.parse(input, gestionnaire);

		//on récupère le nombre de pages
		nbpages = gestionnaire.getNbPagesMax();
		
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
		return nbpages;
	}


	
	 //album
    //on veut rechercher un album:              http://ws.audioscrobbler.com/2.0/?method=album.search&album=believe&api_key=ca33590ba46941a9186c4777b5046445
    //on veut récuperer les infos sur un album: http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=ca33590ba46941a9186c4777b5046445&artist=Cher&album=Believe
    
    //chanson
    //on veut rechercher une chanson: 				http://ws.audioscrobbler.com/2.0/?method=track.search&track=Believe&api_key=ca33590ba46941a9186c4777b5046445
    //on veut récuperer les infos sur une chanson: http://ws.audioscrobbler.com/2.0/?method=track.getinfo&api_key=ca33590ba46941a9186c4777b5046445&artist=cher&track=believe
    
    //artiste
    //on veut rechercher un artiste:              http://ws.audioscrobbler.com/2.0/?method=artist.search&artist=cher&api_key=ca33590ba46941a9186c4777b5046445
    //on veut récuperer les infos sur un artiste: http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=Cher&api_key=ca33590ba46941a9186c4777b5046445

	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/
	
	
	public static int getNbpagesmax() {
		return nbpagesMax;
	}
	
}
