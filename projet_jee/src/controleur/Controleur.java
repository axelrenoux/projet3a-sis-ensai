package controleur;

import java.sql.Date;
import java.util.HashMap;

import exceptions.ExceptionMiseAjour;

import metier.Tag;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;

public class  Controleur {

	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private HashMap<String,Album> listeAlbums= new HashMap<String, Album>();
	private HashMap<String,Artiste> listeArtistes = new HashMap<String, Artiste>();
	private HashMap<String,Chanson> listeChansons = new HashMap<String, Chanson>();
	private HashMap<String,Tag> listeTags = new HashMap<String, Tag>();
	private HashMap<String,String> listeProblemesRencontres = new HashMap<String, String>();
	private HashMap<String,Date> listeDate = new HashMap<String, Date>();
	
	private static final Controleur instanceUniqueControleur = new Controleur();
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	private Controleur(){
	}
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	/****************methodes qui ajoutent des objets********************/
	/**
	 * il faut verifier que l'album/chanson/artiste n'existe pas deja
	 * si oui: on propose une mise a jour de l'album/chanson/artiste  existant
	 * sinon: on l'ajoute dans la liste des albums/chansons/artistes 
	 * @param newalbum
	 */
	
	/**
	 * @param newalbum
	 */
	public void ajouter(Album newalbum){
		if(listeAlbums.containsKey(newalbum.getUrl())){
			try {
				listeAlbums.get(newalbum.getUrl()).mettreAjour(newalbum);
			} catch (ExceptionMiseAjour e) {
				Controleur.getInstanceuniquecontroleur().
				ajouterProbleme(e.getTitre(),e.getMessage());
			}
		}else{
			listeAlbums.put(newalbum.getUrl(), newalbum);	
		}
	}
	

	/**
	 * @param newartiste
	 */
	public void ajouter(Artiste newartiste){
		if(listeArtistes.containsKey(newartiste.getName())){
			try {
				listeArtistes.get(newartiste.getName()).mettreAjour(newartiste);
			} catch (ExceptionMiseAjour e) {
				Controleur.getInstanceuniquecontroleur().
				ajouterProbleme(e.getTitre(),e.getMessage());
			}
		}else{
			listeArtistes.put(newartiste.getName(), newartiste);	
		}
	}

	/**
	 * @param newchanson
	 */
	public void ajouter(Chanson newchanson){
		if(listeChansons.containsKey(newchanson.getUrl())){
			try {
				listeChansons.get(newchanson.getUrl()).mettreAjour(newchanson);
			} catch (ExceptionMiseAjour e) {
				Controleur.getInstanceuniquecontroleur().
				ajouterProbleme(e.getTitre(),e.getMessage());
			}
		}
		else{
			listeChansons.put(newchanson.getUrl(), newchanson);			
		}
	}

	
	/**
	 * @param newtag
	 */
	public void ajouter(Tag newtag){
		if(!listeTags.containsKey(newtag.getUrl())){
			listeTags.put(newtag.getUrl(), newtag);
		}
	}
	
	
	public void ajouter(Date date,String s){
		listeDate.put(s,date);
	}
	
	/***********methodes qui verifient l'existence d'objets**************/
	
	public boolean existeDeja(Album album){
		if(listeAlbums.containsKey(album.getUrl()))return true;
		else return false;
	}
	
	public boolean existeDeja(Artiste artiste){
		if(listeArtistes.containsKey(artiste.getName()))return true;
		else return false;
	}
	
	public boolean existeDeja(Chanson chanson){
		if(listeChansons.containsKey(chanson.getUrl()))return true;
		else return false;
	}
	
	public boolean existeDeja(Tag tag){
		if(listeTags.containsKey(tag.getUrl()))return true;
		else return false;
	}
	
	/**********methode qui recupere les differents problemes*************/
	/**********recontres pendant une recuperation de donnees*************/
	
	/**
	 * @param titre
	 * @param probleme
	 */
	public void ajouterProbleme(String titre, String probleme){
		listeProblemesRencontres.put(titre, probleme);
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

	public HashMap<String, String> getListeProblemesRencontres() {
		return listeProblemesRencontres;
	}

	public HashMap<String, Date> getListeDate() {
		return listeDate;
	}
	
	
}
