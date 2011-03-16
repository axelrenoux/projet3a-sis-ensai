package rechercheParFormulaire.gestionRecherche;

import java.util.ArrayList;

public abstract class Recherche {
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private String artist=null;
	private String album=null;
	private String track=null;
	private String tag=null;
	private String motCle=null;
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/

	public ArrayList lancerRecherche() {
		return null;
	}
	
	public String retournerTypeAffichage(){
		return null;
	}



	

	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}
	

	
}
