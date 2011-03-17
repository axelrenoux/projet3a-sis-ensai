package rechercheParFormulaire.vue;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import rechercheParFormulaire.gestionRecherche.GestionnaireAffichageResultat;
import rechercheParFormulaire.gestionRecherche.GestionnaireFormulaire;

import metier.Cluster;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;
import metier.oeuvres.Oeuvre;

/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 */
/**
 * @author Administrateur
 *
 */
@ManagedBean
@SessionScoped
public class VueAffichageResultat {
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	@ManagedProperty(value="#{gestionnaireAffichageResultat}")
	private GestionnaireAffichageResultat gestionnaireAffichageResultat;
	
	
	private ArrayList<String> titresClustersNiveau1Album = new ArrayList<String>();
	private ArrayList<String> titresClustersNiveau1Artiste = new ArrayList<String>();
	private ArrayList<String> titresClustersNiveau1Chanson = new ArrayList<String>();
	private ArrayList<String> titresClustersNiveau2Album = new ArrayList<String>();
	private ArrayList<String> titresClustersNiveau2Artiste = new ArrayList<String>();
	private ArrayList<String> titresClustersNiveau2Chanson = new ArrayList<String>();

	private ArrayList<Oeuvre> resultatsAafficher;

	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	

	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	
	
	
	
	public GestionnaireAffichageResultat getGestionnaireAffichageResultat() {
		return gestionnaireAffichageResultat;
	}

	public void setGestionnaireAffichageResultat(
			GestionnaireAffichageResultat gestionnaireAffichageResultat) {
		this.gestionnaireAffichageResultat = gestionnaireAffichageResultat;
	}

	public ArrayList<String> getTitresClustersNiveau1Album() {
		return titresClustersNiveau1Album;
	}

	public void setTitresClustersNiveau1Album(
			ArrayList<String> titresClustersNiveau1Album) {
		this.titresClustersNiveau1Album = titresClustersNiveau1Album;
	}

	public ArrayList<String> getTitresClustersNiveau1Artiste() {
		return titresClustersNiveau1Artiste;
	}

	public void setTitresClustersNiveau1Artiste(
			ArrayList<String> titresClustersNiveau1Artiste) {
		this.titresClustersNiveau1Artiste = titresClustersNiveau1Artiste;
	}

	public ArrayList<String> getTitresClustersNiveau1Chanson() {
		return titresClustersNiveau1Chanson;
	}

	public void setTitresClustersNiveau1Chanson(
			ArrayList<String> titresClustersNiveau1Chanson) {
		this.titresClustersNiveau1Chanson = titresClustersNiveau1Chanson;
	}

	public ArrayList<String> getTitresClustersNiveau2Album() {
		return titresClustersNiveau2Album;
	}

	public void setTitresClustersNiveau2Album(
			ArrayList<String> titresClustersNiveau2Album) {
		this.titresClustersNiveau2Album = titresClustersNiveau2Album;
	}

	public ArrayList<String> getTitresClustersNiveau2Artiste() {
		return titresClustersNiveau2Artiste;
	}

	public void setTitresClustersNiveau2Artiste(
			ArrayList<String> titresClustersNiveau2Artiste) {
		this.titresClustersNiveau2Artiste = titresClustersNiveau2Artiste;
	}

	public ArrayList<String> getTitresClustersNiveau2Chanson() {
		return titresClustersNiveau2Chanson;
	}

	public void setTitresClustersNiveau2Chanson(
			ArrayList<String> titresClustersNiveau2Chanson) {
		this.titresClustersNiveau2Chanson = titresClustersNiveau2Chanson;
	}

	public ArrayList<Oeuvre> getResultatsAafficher() {
		return resultatsAafficher;
	}

	public void setResultatsAafficher(ArrayList<Oeuvre> resultatsAafficher) {
		this.resultatsAafficher = resultatsAafficher;
	}
	
	

	
	

	
}
