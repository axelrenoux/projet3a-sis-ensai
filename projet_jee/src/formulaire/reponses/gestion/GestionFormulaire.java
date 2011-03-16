package formulaire.reponses.gestion;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
public class GestionFormulaire {
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private RechercheAlbum rechercheAlbum = new RechercheAlbum();
	private RechercheArtiste rechercheArtiste = new RechercheArtiste();
	private RechercheChanson rechercheChanson = new RechercheChanson();
	private ArrayList<Album> resultatsAlbum = new ArrayList<Album>();
	private ArrayList<Artiste> resultatsArtiste = new ArrayList<Artiste>();
	private ArrayList<Chanson> resultatsChanson = new ArrayList<Chanson>();


	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/

	
	
	
	public ArrayList<Album> lancerRechercheAlbum() {
		
		return rechercheAlbum.lancerRecherche();
		
	}
	
	public ArrayList<Artiste> lancerRechercheArtiste() {
		
		return rechercheArtiste.lancerRecherche();
		
	}
	
	public ArrayList<Chanson> lancerRechercheChanson() {
		
		return rechercheChanson.lancerRecherche();
		
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
