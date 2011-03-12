package controleur;

import java.util.HashMap;

import metier.Album;
import metier.Artiste;
import metier.Chanson;
import metier.Tag;

public class  Controleur {

	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private HashMap<String,Album> listeAlbums= new HashMap<String, Album>();
	private HashMap<String,Artiste> listeArtistes = new HashMap<String, Artiste>();
	private HashMap<String,Chanson> listeChansons = new HashMap<String, Chanson>();
	private HashMap<String,Tag> listeTags = new HashMap<String, Tag>();
	
	private static final Controleur instanceUniqueControleur = new Controleur();
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	private Controleur(){
	}
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	//methodes qui ajoutent des objets
	
	public void ajouterAlbum(Album album){
		listeAlbums.put(album.getID(), album);
	}
	
	public void ajouterArtiste(Artiste artiste){
		listeArtistes.put(artiste.getName(), artiste);
	}

	public void ajouterChanson(Chanson chanson){
		listeChansons.put(chanson.getUrl(), chanson);
	}

	//methodes qui verifient l'existence d'objets 
	public boolean existeDeja(Album album){
		if(listeAlbums.containsKey(album.getID()))return true;
		else return false;
	}
	
	public boolean existeDeja(Artiste artiste){
		if(listeAlbums.containsKey(artiste.getID()))return true;
		else return false;
	}
	
	public boolean existeDeja(Chanson chanson){
		if(listeAlbums.containsKey(chanson.getID()))return true;
		else return false;
	}
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/
	
	public static Controleur getInstanceuniquecontroleur() {
		return instanceUniqueControleur;
	}

	public HashMap<String, Album> getListeAlbums() {
		return listeAlbums;
	}

	public HashMap<String, Artiste> getListeArtistes() {
		return listeArtistes;
	}

	public HashMap<String, Chanson> getListeChansons() {
		return listeChansons;
	}

	public HashMap<String, Tag> getListeTags() {
		return listeTags;
	}
	
	
}
