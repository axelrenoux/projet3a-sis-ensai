package recuperationDonnees;

import java.util.ArrayList;
import java.util.Map.Entry;

import controleur.Controleur;

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
		motsCleArtistes.add("ordinateur");
	}
	
	public void recupererDonnees(){
		
		initialisation();
		
		/************ recuperation de chansons ***********************/
		ParsingChanson parsingChanson = new ParsingChanson();
		
		for(String chanson : motsCleChansons){
			listeChansons.addAll(parsingChanson.parser(chanson));
		}
		System.out.println(listeChansons.toString());
		
		//pour chaque chanson, on recupere les infos complementaires
		for(Chanson chanson: listeChansons){
			chanson = parsingChanson.parserInfos(chanson);
			Controleur.getInstanceuniquecontroleur().ajouterChanson(chanson);
		}
		
		
		
	
		
		/************ recuperation d'albums    ***********************/
		ParsingAlbum parsingAlbum = new ParsingAlbum();
		
		//pour chaque mot cle, on recupere les albums associes
		for(String motclealbum : motsCleAlbums){
			listeAlbums.addAll(parsingAlbum.parser(motclealbum));
		}
		System.out.println(listeAlbums.toString());
		
		//pour chaque album, on recupere les infos complementaires
		for(Album album: listeAlbums){
			album = parsingAlbum.parserInfos(album);
			Controleur.getInstanceuniquecontroleur().ajouterAlbum(album);
		}
		
		
		
		
		
		
		/************ recuperation d'artistes  ***********************/
		ParsingArtiste parsingArtiste = new ParsingArtiste();
		
		for(String artiste : motsCleArtistes){
			listeArtistes.addAll(parsingArtiste.parser(artiste));
		}
		System.out.println(listeArtistes.toString());
		
		//pour chaque artiste, on recupere les infos complementaires
		for(Artiste artiste: listeArtistes){
			artiste = parsingArtiste.parserInfos(artiste);
			Controleur.getInstanceuniquecontroleur().ajouterArtiste(artiste);
		}
		
		
		/************** affichage des données recuperees*************/
		
		System.out.println("***********************************************");
		System.out.println("************* affichage des albums ************");
		System.out.println("***********************************************");
		
		//affichage des albums
		for(Entry<String, Album> currentEntry : Controleur.getInstanceuniquecontroleur().getListeAlbums().entrySet()){
			System.out.println(currentEntry.getKey() + " " + currentEntry.getValue());
		}
		//affichage des artistes
		
		for(Entry<String, Artiste> currentEntry : Controleur.getInstanceuniquecontroleur().getListeArtistes().entrySet()){
			System.out.println(currentEntry.getKey() + " " + currentEntry.getValue());
		}
		//affichage des chansons
		for(Entry<String, Chanson> currentEntry : Controleur.getInstanceuniquecontroleur().getListeChansons().entrySet()){
			System.out.println(currentEntry.getKey() + " " + currentEntry.getValue());
		}
		
	}
	

}
