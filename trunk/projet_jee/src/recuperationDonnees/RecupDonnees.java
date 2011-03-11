package recuperationDonnees;

import java.util.ArrayList;

import metier.Album;
import metier.Artiste;
import metier.Chanson;

import parsing.sax.ParsingAlbum;
import parsing.sax.ParsingArtiste;
import parsing.sax.ParsingChanson;

public class RecupDonnees {
	
	ArrayList<String> motsCleChansons = new ArrayList<String>();
	ArrayList<String> motsCleAlbums = new ArrayList<String>();
	ArrayList<String> motsCleArtistes = new ArrayList<String>();
	
	ArrayList<Chanson> listeChansons = new ArrayList<Chanson>();
	ArrayList<Album> listeAlbums = new ArrayList<Album>();
	ArrayList<Artiste> listeArtistes = new ArrayList<Artiste>();
	
	
	public void initialisation(){
		motsCleChansons.add("ordinateur");
		motsCleAlbums.add("ordinateur");
		motsCleArtistes.add("cher");
	}
	
	public void recupererDonnees(){
		
		initialisation();
		
		/************ recuperation de chansons ***********************/
		/*ParsingChanson parsingChanson = new ParsingChanson();
		
		for(String chanson : motsCleChansons){
			listeChansons.addAll(parsingChanson.parser(chanson));
		}
		System.out.println(listeChansons.toString());
		*/
		
		/************ recuperation d'albums    ***********************/
		ParsingAlbum parsingAlbum = new ParsingAlbum();
		
		for(String album : motsCleAlbums){
			listeAlbums.addAll(parsingAlbum.parser(album));
		}
		System.out.println(listeAlbums.toString());
		
		
		Album a = listeAlbums.get(3);
		System.out.println(" album avant "+ a);
		a = parsingAlbum.parserInfos(a);
		System.out.println(" album complete "+ a);
		
		
		
		
		/************ recuperation d'artistes  ***********************/
		/*ParsingArtiste parsingArtiste = new ParsingArtiste();
		
		for(String artiste : motsCleArtistes){
			listeArtistes.addAll(parsingArtiste.parser(artiste));
		}
		System.out.println(listeArtistes.toString());
		*/
	
		
		/********************** test get info album ******************/
		/*Album albumCompletExemple = new Album();
		Artiste artiste = new Artiste();
		artiste.setName("cher");
		albumCompletExemple.setName("ordinateur");
		albumCompletExemple.setArtiste(artiste);
		
		System.out.println(" album avant" + albumCompletExemple.toString());
		//essai de recuperation d'infos complémentaires sur un album 
		
		albumCompletExemple = parsingAlbum.parserInfos(albumCompletExemple);
		
		System.out.println(" album apres" + albumCompletExemple.toString());
		*/
	}

}
