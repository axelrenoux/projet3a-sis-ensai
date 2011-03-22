package bdd.rechercheBDDOLD;

import java.util.HashMap;
import java.util.Map;

import metier.Tag;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;
import bdd.sqlviajdbc.ControlAccesSQLViaJDBC;
import controleur.Controleur;
import exceptions.ChargementException;

public abstract class RechercheBDDOld {//
	
	//Nos données en Java, associées à leurs clés primaires
	private Map<Integer,Artiste> artistes=new HashMap<Integer,Artiste>();
	private Map<Integer,Chanson> chansons=new HashMap<Integer,Chanson>();
	private Map<Integer,Album> albums=new HashMap<Integer,Album>();
	private Map<Integer,Tag> tags=new HashMap<Integer,Tag>();
	
	public void charger(String artiste, String album, String chanson, String tag){
		try {
			chargerListeTags(tag);
			chargerListeChansons(chanson);
			chargerListeArtistes(artiste);
			chargerListeAlbums(album);
		} catch (ChargementException e) {e.printStackTrace();}
		ControlAccesSQLViaJDBC.fermerBDD();
	}

	protected abstract void chargerListeAlbums(String album) throws ChargementException;

	protected abstract void chargerListeArtistes(String artiste) throws ChargementException;

	protected abstract void chargerListeTags(String tag) throws ChargementException;

	protected abstract void chargerListeChansons(String chanson) throws ChargementException;


	public Map<Integer, Artiste> getArtistes() {
		return artistes;
	}

	protected void add(Integer clef, Artiste artiste) {
		artistes.put(clef, artiste);
		Controleur.getInstanceuniquecontroleur().ajouter(artiste);
	}

	public Map<Integer, Chanson> getChansons() {
		return chansons;
	}

	protected void add(Integer clef, Chanson chanson) {
		chansons.put(clef, chanson);
		Controleur.getInstanceuniquecontroleur().ajouter(chanson);
	}

	public Map<Integer, Album> getAlbums() {
		return albums;
	}

	protected void add(Integer clef, Album album) {
		albums.put(clef, album);
		Controleur.getInstanceuniquecontroleur().ajouter(album);
	}

	public Map<Integer, Tag> getTags() {
		return tags;
	}

	protected void add(Integer clef, Tag tag) {
		tags.put(clef, tag);
		Controleur.getInstanceuniquecontroleur().ajouter(tag);
	}
}
