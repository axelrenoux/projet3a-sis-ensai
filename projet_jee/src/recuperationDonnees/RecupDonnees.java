package recuperationDonnees;

import java.util.ArrayList;
import java.util.Map.Entry;

import controleur.Controleur;

import metier.Album;
import metier.Artiste;
import metier.Chanson;
import metier.Tag;

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
	
	public void recupererDonneesChansons(){
		
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
			Controleur.getInstanceuniquecontroleur().ajouter(chanson);
		}
		
	}
	
	public void recupererDonneesAlbums(){
	
		initialisation();
		
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
			Controleur.getInstanceuniquecontroleur().ajouter(album);
		}
	}	
		
	
	public void recupererDonneesArtistes(){
		
		initialisation();
		
		/************ recuperation d'artistes  ***********************/
		ParsingArtiste parsingArtiste = new ParsingArtiste();
		
		for(String artiste : motsCleArtistes){
			listeArtistes.addAll(parsingArtiste.parser(artiste));
		}
		System.out.println(listeArtistes.toString());
		
		//pour chaque artiste, on recupere les infos complementaires
		for(Artiste artiste: listeArtistes){
			artiste = parsingArtiste.parserInfos(artiste);
			Controleur.getInstanceuniquecontroleur().ajouter(artiste);
		}
	}
	
	
		/************** affichage des données recuperees*************/
	
	public void affichageDonneesRecuperees(){
		
		System.out.println("***********************************************");
		System.out.println("************* affichage des albums ************");
		System.out.println("***********************************************");
		
		for(Entry<String, Album> currentEntry : Controleur.getInstanceuniquecontroleur().getListeAlbums().entrySet()){
			System.out.println("key " + currentEntry.getKey() + " " + currentEntry.getValue());
		}
		
		System.out.println("***********************************************");
		System.out.println("*********** affichage des artistes ************");
		System.out.println("***********************************************");
		
		
		for(Entry<String, Artiste> currentEntry : Controleur.getInstanceuniquecontroleur().getListeArtistes().entrySet()){
			System.out.println(currentEntry.getKey() + " " + currentEntry.getValue());
		}
		
		System.out.println("***********************************************");
		System.out.println("************ affichage des chansons ***********");
		System.out.println("***********************************************");
		
		
		for(Entry<String, Chanson> currentEntry : Controleur.getInstanceuniquecontroleur().getListeChansons().entrySet()){
			System.out.println(currentEntry.getKey() + " " + currentEntry.getValue());
		}
		
		System.out.println("***********************************************");
		System.out.println("************** affichage des tags *************");
		System.out.println("***********************************************");
		
		
		for(Entry<String, Tag> currentEntry : Controleur.getInstanceuniquecontroleur().getListeTags().entrySet()){
			System.out.println(currentEntry.getKey() + " " + currentEntry.getValue());
		}
		
		System.out.println("***********************************************");
		System.out.println("***** affichage des problemes rencontres ******");
		System.out.println("***********************************************");
		
		
		for(Entry<String, String> currentEntry : Controleur.getInstanceuniquecontroleur().getListeProblemesRencontres().entrySet()){
			System.out.println(currentEntry.getKey() + " " + currentEntry.getValue());
		}
		
	}
	

}
