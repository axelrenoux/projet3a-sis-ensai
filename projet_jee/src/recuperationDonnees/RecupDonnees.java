package recuperationDonnees;

import java.sql.Date;
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
import parsing.sax.ParsingTag;

public class RecupDonnees {
	
	ArrayList<String> motsCleChansons = new ArrayList<String>();
	ArrayList<String> motsCleAlbums = new ArrayList<String>();
	ArrayList<String> motsCleArtistes = new ArrayList<String>();
	
	
	
	
	
	/************ methodes d'initialisation ***********************/
	
	public void initialisationChanson(){
		motsCleChansons.add("ordinateur");
	}
	public void initialisationArtiste(){
		motsCleArtistes.add("ordinateur");
	}
	public void initialisationAlbum(){
		motsCleAlbums.add("ordinateur");
	}
	
	
	/************ recuperation de chansons ***********************/
	public void recupererDonneesChansons(){
		
		initialisationChanson();
		ArrayList<Chanson> listeChansons = new ArrayList<Chanson>();
		
		ParsingChanson parsingChanson = new ParsingChanson();
		
		for(String chanson : motsCleChansons){
			listeChansons.addAll(parsingChanson.parser(chanson));
		}
		
		//pour chaque chanson, on recupere les infos complementaires
		for(Chanson chanson: listeChansons){
			chanson = parsingChanson.parserInfos(chanson);
			Controleur.getInstanceuniquecontroleur().ajouter(chanson);
		}
		
	}
	
	/************ recuperation d'albums    ***********************/
	public void recupererDonneesAlbums(){
	
		initialisationAlbum();
		ArrayList<Album> listeAlbums = new ArrayList<Album>();
		
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
		
	/************ recuperation d'artistes  ***********************/
	public void recupererDonneesArtistes(){
		
		initialisationArtiste();
		ArrayList<Artiste> listeArtistes = new ArrayList<Artiste>();
		
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
	
	
	/************ recuperation infos tags ***********************/
	public void recupererDonneesComplementairesTags(){
		ParsingTag parsingTag = new ParsingTag();
		
		//pour chaque tag, on recupere les infos complementaires
		for(Entry<String, Tag> currentEntry : Controleur.getInstanceuniquecontroleur().getListeTags().entrySet()){
			Tag t = currentEntry.getValue();
			t = parsingTag.parserInfos(t);
		}
	}
	
	
	/**
	 * pour chaque tag, on recupere :
	 * - la liste des artistes qui ont ce tag
	 * - la liste des albums qui ont ce tag
	 * - la liste des chansons qui ont ce tag	
	 */
	/*
	public void recupererListesDesTags(){
		//System.out.println("@liste tags " +Controleur.getInstanceuniquecontroleur().getListeTags());
		for(Entry<String, Tag> entryTag : Controleur.getInstanceuniquecontroleur().getListeTags().entrySet()){
			//System.out.println("@tag " + entryTag.getValue());
			//les artistes
			for(Entry<String, Artiste> entryArtiste : Controleur.getInstanceuniquecontroleur().getListeArtistes().entrySet()){
				if(entryArtiste.getValue().getToptags()!=null){
					//System.out.println("@artiste " + entryArtiste.getValue());
					//System.out.println("@tags de l'artiste : " + entryArtiste.getValue().getToptags());
					//System.out.println("@@ contains " + entryArtiste.getValue().getToptags().contains(entryTag.getValue()));
					if(entryArtiste.getValue().getToptags().contains(entryTag.getValue())){
						//System.out.println("@ taille avant "+ entryTag.getValue().getListeArtistes().size());
						entryTag.getValue().getListeArtistes().add(entryArtiste.getValue());
						//System.out.println("@ taille apres "+ entryTag.getValue().getListeArtistes().size());
					}
				}
			}
			//les albums
			for(Entry<String, Album> entryAlbum : Controleur.getInstanceuniquecontroleur().getListeAlbums().entrySet()){
				if(entryAlbum.getValue().getToptags()!=null){
					System.out.println("@album " + entryAlbum.getValue());
					System.out.println("@tags de l'album : " + entryAlbum.getValue().getToptags());
					System.out.println("@@ contains " + entryAlbum.getValue().getToptags().contains(entryTag.getValue()));
					if(entryAlbum.getValue().getToptags().contains(entryTag.getValue())){
						System.out.println("@ taille avant "+entryTag.getValue().getListeAlbums().size());
						entryTag.getValue().getListeAlbums().add(entryAlbum.getValue());
						System.out.println("@ taille avant "+entryTag.getValue().getListeAlbums().size());
					}	
				}
				
			}
			//les chansons
			for(Entry<String, Chanson> entryChanson : Controleur.getInstanceuniquecontroleur().getListeChansons().entrySet()){
				//System.out.println("@chanson " + entryChanson.getValue());
				if(entryChanson.getValue().getToptags()!=null){
					//System.out.println("@tags de la chanson : " + entryChanson.getValue().getToptags());
					if(entryChanson.getValue().getToptags().contains(entryTag.getValue())){
						entryTag.getValue().getListeChansons().add(entryChanson.getValue());
					}	
				}
			}
		}
	}
	*/
	

	/**
	 * pour chaque tag, on recupere :
	 * - la liste des artistes qui ont ce tag
	 * - la liste des albums qui ont ce tag
	 * - la liste des chansons qui ont ce tag	
	 */
	public void recupererListesDesTags(){
			//les artistes
			for(Entry<String, Artiste> entryArtiste : Controleur.getInstanceuniquecontroleur().getListeArtistes().entrySet()){
				if(entryArtiste.getValue().getToptags()!=null){
					for(Tag t : entryArtiste.getValue().getToptags()){
						t.getListeArtistes().add(entryArtiste.getValue());
					}
				}
			}
			//les albums
			for(Entry<String, Album> entryAlbum : Controleur.getInstanceuniquecontroleur().getListeAlbums().entrySet()){
				if(entryAlbum.getValue().getToptags()!=null){
					for(Tag t : entryAlbum.getValue().getToptags()){
						t.getListeAlbums().add(entryAlbum.getValue());
					}
				}
			}
			//les chansons
			for(Entry<String, Chanson> entryChanson : Controleur.getInstanceuniquecontroleur().getListeChansons().entrySet()){
				if(entryChanson.getValue().getToptags()!=null){
					for(Tag t : entryChanson.getValue().getToptags()){
						t.getListeChansons().add(entryChanson.getValue());
					}
				}
			}
	}
	
	
	/**
	 * quand on recupere des artistes, on recuperes des artistes similaires,
	 * avec le minimum d'infos.
	 * cette methode permet notamment d'obtenir plus d'infos complementaires
	 * concernant ces artistes 
	 */
	/******************** on recupere encore plus d'infos commplementaires***/
	public void recupererInfosComplementairesPlus(){
		ParsingArtiste parsingArtiste = new ParsingArtiste();
		
		//pour chaque artiste, on recupere les infos complementaires
		ArrayList<Artiste> l = new ArrayList<Artiste>();
		for(Entry<String, Artiste> currentEntry : Controleur.getInstanceuniquecontroleur().getListeArtistes().entrySet()){
			Artiste a = currentEntry.getValue();
			l.add(a);
		}
		
		
		for(Artiste a : l){
			a = parsingArtiste.parserInfos(a);
		}
		
		
	}
	
	
		/************** affichage des données recuperees*************/
	
	public void affichageDonneesRecuperees(){
		
		System.out.println("***********************************************");
		System.out.println("************* affichage des albums ************");
		System.out.println("***********************************************");
		
		System.out.println(" nb d'albums " + Controleur.getInstanceuniquecontroleur().getListeAlbums().size());
		for(Entry<String, Album> currentEntry : Controleur.getInstanceuniquecontroleur().getListeAlbums().entrySet()){
			System.out.println("key " + currentEntry.getKey() + " " + currentEntry.getValue());
		}
		
		System.out.println("***********************************************");
		System.out.println("*********** affichage des artistes ************");
		System.out.println("***********************************************");
		
		System.out.println(" nb d'artistes " + Controleur.getInstanceuniquecontroleur().getListeArtistes().size());
		for(Entry<String, Artiste> currentEntry : Controleur.getInstanceuniquecontroleur().getListeArtistes().entrySet()){
			System.out.println(currentEntry.getKey() + " " + currentEntry.getValue());
		}
		
		System.out.println("***********************************************");
		System.out.println("************ affichage des chansons ***********");
		System.out.println("***********************************************");
		
		System.out.println(" nb de chansons " + Controleur.getInstanceuniquecontroleur().getListeChansons().size());
		for(Entry<String, Chanson> currentEntry : Controleur.getInstanceuniquecontroleur().getListeChansons().entrySet()){
			System.out.println(currentEntry.getKey() + " " + currentEntry.getValue());
		}
		
		System.out.println("***********************************************");
		System.out.println("************** affichage des tags *************");
		System.out.println("***********************************************");
		
		System.out.println(" nb de tags " + Controleur.getInstanceuniquecontroleur().getListeTags().size());
		for(Entry<String, Tag> currentEntry : Controleur.getInstanceuniquecontroleur().getListeTags().entrySet()){
			System.out.println(currentEntry.getKey() + " " + currentEntry.getValue());
		}
		
		System.out.println("***********************************************");
		System.out.println("***** affichage des problemes rencontres ******");
		System.out.println("***********************************************");
		
		System.out.println(" nb de pb " + Controleur.getInstanceuniquecontroleur().getListeProblemesRencontres().size());
		for(Entry<String, String> currentEntry : Controleur.getInstanceuniquecontroleur().getListeProblemesRencontres().entrySet()){
			System.out.println(currentEntry.getKey() + " " + currentEntry.getValue());
		}
		
		
		System.out.println("***********************************************");
		System.out.println("***** affichage des date  ******");
		System.out.println("***********************************************");
		System.out.println("nb dates " + Controleur.getInstanceuniquecontroleur().getListeDate().size());
		for(Entry<String, Date> currentEntry : Controleur.getInstanceuniquecontroleur().getListeDate().entrySet()){
			System.out.println(currentEntry.getKey() + " " + currentEntry.getValue());
		}
		

	}
	

}
