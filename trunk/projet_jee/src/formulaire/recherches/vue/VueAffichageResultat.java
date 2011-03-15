package formulaire.recherches.vue;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import metier.Album;
import metier.Artiste;
import metier.Chanson;

/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 */
@ManagedBean
@SessionScoped
public class VueAffichageResultat {
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	private ArrayList<Album> resultatsAlbum;
	private ArrayList<Artiste> resultatsArtiste;
	private ArrayList<Chanson> resultatsChanson;


	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	

	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	public ArrayList<Album> getResultatsAlbum() {
		return resultatsAlbum;
	}
	public void setResultatsAlbum(ArrayList<Album> resultatsAlbum) {
		this.resultatsAlbum = resultatsAlbum;
	}
	public ArrayList<Artiste> getResultatsArtiste() {
		return resultatsArtiste;
	}
	public void setResultatsArtiste(ArrayList<Artiste> resultatsArtiste) {
		this.resultatsArtiste = resultatsArtiste;
	}
	public ArrayList<Chanson> getResultatsChanson() {
		return resultatsChanson;
	}
	public void setResultatsChanson(ArrayList<Chanson> resultatsChanson) {
		this.resultatsChanson = resultatsChanson;
	}
	
	
}
