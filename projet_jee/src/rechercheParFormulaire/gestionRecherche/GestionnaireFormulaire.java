package rechercheParFormulaire.gestionRecherche;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import metier.Cluster;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;
/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 *
 */
@ManagedBean
@SessionScoped
public class GestionnaireFormulaire {
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private RechercheAlbum rechercheAlbum = new RechercheAlbum();
	private RechercheArtiste rechercheArtiste = new RechercheArtiste();
	private RechercheChanson rechercheChanson = new RechercheChanson();



	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/

	
	
	
	public ArrayList<Cluster> lancerRechercheAlbum(String motCle) {
		return rechercheAlbum.lancerRecherche(motCle);
	}
	
	public ArrayList<Cluster> lancerRechercheArtiste(String motCle) {
		return rechercheArtiste.lancerRecherche(motCle);
	}
	
	public ArrayList<Cluster> lancerRechercheChanson(String motCle) {
		return rechercheChanson.lancerRecherche(motCle);
	}
	
	

 
	
	




	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	

	public RechercheAlbum getRechercheAlbum() {
		return rechercheAlbum;
	}


	public void setRechercheAlbum(RechercheAlbum rechercheAlbum) {
		this.rechercheAlbum = rechercheAlbum;
	}


	public RechercheArtiste getRechercheArtiste() {
		return rechercheArtiste;
	}


	public void setRechercheArtiste(RechercheArtiste rechercheArtiste) {
		this.rechercheArtiste = rechercheArtiste;
	}


	public RechercheChanson getRechercheChanson() {
		return rechercheChanson;
	}


	public void setRechercheChanson(RechercheChanson rechercheChanson) {
		this.rechercheChanson = rechercheChanson;
	}




	

}
