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
	
	private HashMap<String,Album> listeAlbums;
	private HashMap<String,Artiste> listeArtistes;
	private HashMap<String,Chanson> listeChansons;
	private HashMap<String,Tag> listeTags;
	
	private static final Controleur instanceUniqueControleur = new Controleur();
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	private Controleur(){
	}
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	public void ajouterAlbum(Album album){
		
	}
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/
	
	public static Controleur getInstanceuniquecontroleur() {
		return instanceUniqueControleur;
	}
	
	
}
