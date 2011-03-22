package rechercheParFormulaire.vue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;

import rechercheParFormulaire.gestionRecherche.GestionnaireAffichageResultat;


import metier.Cluster;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;


/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 */
/**
 * @author Administrateur
 *
 */
/**
 * @author Administrateur
 *
 */
@ManagedBean
@SessionScoped
public class VueAffichageResultatXML {

	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	@ManagedProperty(value="#{gestionnaireAffichageResultat}")
	private GestionnaireAffichageResultat gestionnaireAffichageResultat;


	private Cluster clustersAlbum;
	private Cluster clustersArtiste;
	private Cluster clustersChanson;



	private ArrayList<Cluster> clustersAlbumsNiveau1;
	private ArrayList<Cluster> clustersArtistesNiveau1;
	private ArrayList<Cluster> clustersChansonsNiveau1;

 
 
 
	



	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/


	public void init() {
		 
		clustersAlbumsNiveau1 = gestionnaireAffichageResultat.retournerClustersAlbumNiveau1(clustersAlbum);
		clustersArtistesNiveau1 = gestionnaireAffichageResultat.retournerClustersArtisteNiveau1(clustersArtiste);
		clustersChansonsNiveau1 = gestionnaireAffichageResultat.retournerClustersChansonNiveau1(clustersChanson);

	}



	public void affichageAlbums(){
		
	}
	
	public void affichageArtistes(){
		
	}

	public void affichageChansons(){
	
	}
	
	
	
 
	
 
	
	
	/**
	 *  methodes de navigation
	 */
 

	
	public String retourDebut(){
		return "success";
	}
	
	
	/**
	 * methodes qui ajoutent un message d'erreur
	 */
	public void addErrorEtape1Album() { 
		FacesContext.getCurrentInstance().
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur","Veuillez sélectionner un premier critère pour votre recherche d'album")); 
	}
	public void addErrorEtape1Artiste() { 
		FacesContext.getCurrentInstance().
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur","Veuillez sélectionner un premier critère pour votre recherche d'artiste")); 
	}
	public void addErrorEtape1Chanson() { 
		FacesContext.getCurrentInstance().
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur","Veuillez sélectionner un premier critère pour votre recherche de chanson"));
	}
	public void addErrorEtape2Album() {
		FacesContext.getCurrentInstance().
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur","Veuillez sélectionner un deuxième critère pour votre recherche d'album")); 
	}
	public void addErrorEtape2Artiste() {
		FacesContext.getCurrentInstance().
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur","Veuillez sélectionner un deuxième critère pour votre recherche d'artiste")); 
	}
	public void addErrorEtape2Chanson() {
		FacesContext.getCurrentInstance().
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur","Veuillez sélectionner un deuxième critère pour votre recherche de chanson")); 
	}

	
  
	
	


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

	



	public ArrayList<Cluster> getClustersAlbumsNiveau1() {
		return clustersAlbumsNiveau1;
	}

	public void setClustersAlbumsNiveau1(ArrayList<Cluster> clustersAlbumsNiveau1) {
		this.clustersAlbumsNiveau1 = clustersAlbumsNiveau1;
	}

	public ArrayList<Cluster> getClustersArtistesNiveau1() {
		return clustersArtistesNiveau1;
	}

	public void setClustersArtistesNiveau1(
			ArrayList<Cluster> clustersArtistesNiveau1) {
		this.clustersArtistesNiveau1 = clustersArtistesNiveau1;
	}

	public ArrayList<Cluster> getClustersChansonsNiveau1() {
		return clustersChansonsNiveau1;
	}

	public void setClustersChansonsNiveau1(
			ArrayList<Cluster> clustersChansonsNiveau1) {
		this.clustersChansonsNiveau1 = clustersChansonsNiveau1;
	}

	public Cluster getClustersAlbum() {
		return clustersAlbum;
	}

	public void setClustersAlbum(Cluster clustersAlbum) {
		this.clustersAlbum = clustersAlbum;
	}

	public Cluster getClustersArtiste() {
		return clustersArtiste;
	}

	public void setClustersArtiste(Cluster clustersArtiste) {
		this.clustersArtiste = clustersArtiste;
	}

	public Cluster getClustersChanson() {
		return clustersChanson;
	}

	public void setClustersChanson(Cluster clustersChanson) {
		this.clustersChanson = clustersChanson;
	}

	

	 


}
