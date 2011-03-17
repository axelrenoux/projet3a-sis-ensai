package rechercheParFormulaire.vue;

import java.util.ArrayList;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import rechercheParFormulaire.gestionRecherche.GestionnaireAffichageResultat;
import rechercheParFormulaire.gestionRecherche.GestionnaireFormulaire;

import metier.Cluster;
import metier.ComposantCluster;
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


	private Cluster clustersAlbum;
	private Cluster clustersArtiste;
	private Cluster clustersChanson;



	private ArrayList<Cluster> clustersAlbumsNiveau1;
	private ArrayList<Cluster> clustersArtistesNiveau1;
	private ArrayList<Cluster> clustersChansonsNiveau1;

	private Cluster clusterAlbumNiveau1Choisi;
	private Cluster clusterArtisteNiveau1Choisi;
	private Cluster clusterChansonNiveau1Choisi;

	private Cluster clusterAlbumNiveau2Choisi;
	private Cluster clusterArtisteNiveau2Choisi;
	private Cluster clusterChansonNiveau2Choisi;


	private ArrayList<Cluster> clustersAlbumsNiveau2;
	private ArrayList<Cluster> clustersArtistesNiveau2;
	private ArrayList<Cluster> clustersChansonsNiveau2;

	private ArrayList<Album> resultatAlbums;
	private ArrayList<Artiste> resultatArtistes;
	private ArrayList<Chanson> resultatChansons;

	//boolean permettant de savoir a quelle etape de la recherche on se trouve
	private boolean etape1=true;
	private boolean etape2chanson=false;
	private boolean etape2artiste=false;
	private boolean etape2album=false;
	private boolean etape3chanson=false;
	private boolean etape3artiste=false;
	private boolean etape3album=false;





	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/


	public void init() {
		 
		clustersAlbumsNiveau1 = gestionnaireAffichageResultat.retournerClustersAlbumNiveau1(clustersAlbum);
		clustersArtistesNiveau1 = gestionnaireAffichageResultat.retournerClustersArtisteNiveau1(clustersArtiste);
		clustersChansonsNiveau1 = gestionnaireAffichageResultat.retournerClustersChansonNiveau1(clustersChanson);

	 

	}



	/**
	 * methode executee quand l'utilisateur clique sur "affiner sa recherche" d'albums
	 */
	public void affinerRechercheAlbum1(){
		//on cree le cluster suivant
		clustersAlbumsNiveau2 = gestionnaireAffichageResultat.retournerClustersAlbumNiveau2(clusterAlbumNiveau1Choisi);
		//on passe a l'étape 2:
		reinitialisationEtapes();
		etape2album = true;
		/*System.out.println("a choisi  album");
		System.out.println(clusterAlbumNiveau1Choisi.getNom());
		System.out.println(clustersAlbumsNiveau2);*/
		System.out.println(etape2album);
	}
	/**
	 * methode executee quand l'utilisateur clique sur "affiner sa recherche" d'artistes
	 */
	public void affinerRechercheArtiste1(){
		clustersArtistesNiveau2 = gestionnaireAffichageResultat.retournerClustersArtisteNiveau2(clusterArtisteNiveau1Choisi);
		//on passe a l'étape 2:
		reinitialisationEtapes();
		etape2artiste = true;
	}
	/**
	 * methode executee quand l'utilisateur clique sur "affiner sa recherche" de chansons
	 */
	public void affinerRechercheChanson1(){
		clustersChansonsNiveau2 = gestionnaireAffichageResultat.retournerClustersChansonNiveau2(clusterChansonNiveau1Choisi);
		//on passe a l'étape 2:
		reinitialisationEtapes();
		etape2chanson = true;
	}


	public void affinerRechercheAlbum2(){
		resultatAlbums = gestionnaireAffichageResultat.retournerAlbums(clusterAlbumNiveau2Choisi);
		//on passe a l'étape 3:
		reinitialisationEtapes();
		etape3album = true;
	}
	public void affinerRechercheArtiste2(){
		resultatArtistes = gestionnaireAffichageResultat.retournerArtistes(clusterArtisteNiveau2Choisi);
		//on passe a l'étape 3:
		reinitialisationEtapes();
		etape3artiste = true;
	}
	public void affinerRechercheChanson2(){
		resultatChansons = gestionnaireAffichageResultat.retournerChansons(clusterChansonNiveau2Choisi);
		//on passe a l'étape 3:
		reinitialisationEtapes();
		etape3chanson = true;
	}



	public void reinitialisationEtapes(){
		etape1=false;
		etape2album = false;
		etape2artiste = false;
		etape2chanson = false;
		etape3album =false;
		etape3artiste = false;
		etape3chanson = false;
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

	/*public ArrayList<String> getTitresClustersNiveau1Album() {
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
	}*/



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

	public Cluster getClusterAlbumNiveau1Choisi() {
		return clusterAlbumNiveau1Choisi;
	}

	public void setClusterAlbumNiveau1Choisi(Cluster clusterAlbumNiveau1Choisi) {
		this.clusterAlbumNiveau1Choisi = clusterAlbumNiveau1Choisi;
	}

	public Cluster getClusterArtisteNiveau1Choisi() {
		return clusterArtisteNiveau1Choisi;
	}

	public void setClusterArtisteNiveau1Choisi(Cluster clusterArtisteNiveau1Choisi) {
		this.clusterArtisteNiveau1Choisi = clusterArtisteNiveau1Choisi;
	}

	public Cluster getClusterChansonNiveau1Choisi() {
		return clusterChansonNiveau1Choisi;
	}

	public void setClusterChansonNiveau1Choisi(Cluster clusterChansonNiveau1Choisi) {
		this.clusterChansonNiveau1Choisi = clusterChansonNiveau1Choisi;
	}

	public ArrayList<Cluster> getClustersAlbumsNiveau2() {
		return clustersAlbumsNiveau2;
	}

	public void setClustersAlbumsNiveau2(ArrayList<Cluster> clustersAlbumsNiveau2) {
		this.clustersAlbumsNiveau2 = clustersAlbumsNiveau2;
	}

	public ArrayList<Cluster> getClustersArtistesNiveau2() {
		return clustersArtistesNiveau2;
	}

	public void setClustersArtistesNiveau2(
			ArrayList<Cluster> clustersArtistesNiveau2) {
		this.clustersArtistesNiveau2 = clustersArtistesNiveau2;
	}

	public ArrayList<Cluster> getClustersChansonsNiveau2() {
		return clustersChansonsNiveau2;
	}

	public void setClustersChansonsNiveau2(
			ArrayList<Cluster> clustersChansonsNiveau2) {
		this.clustersChansonsNiveau2 = clustersChansonsNiveau2;
	}

	public Cluster getClusterAlbumNiveau2Choisi() {
		return clusterAlbumNiveau2Choisi;
	}

	public void setClusterAlbumNiveau2Choisi(Cluster clusterAlbumNiveau2Choisi) {
		this.clusterAlbumNiveau2Choisi = clusterAlbumNiveau2Choisi;
	}

	public Cluster getClusterArtisteNiveau2Choisi() {
		return clusterArtisteNiveau2Choisi;
	}

	public void setClusterArtisteNiveau2Choisi(Cluster clusterArtisteNiveau2Choisi) {
		this.clusterArtisteNiveau2Choisi = clusterArtisteNiveau2Choisi;
	}

	public Cluster getClusterChansonNiveau2Choisi() {
		return clusterChansonNiveau2Choisi;
	}

	public void setClusterChansonNiveau2Choisi(Cluster clusterChansonNiveau2Choisi) {
		this.clusterChansonNiveau2Choisi = clusterChansonNiveau2Choisi;
	}

	public ArrayList<Album> getResultatAlbums() {
		return resultatAlbums;
	}

	public void setResultatAlbums(ArrayList<Album> resultatAlbums) {
		this.resultatAlbums = resultatAlbums;
	}

	public ArrayList<Artiste> getResultatArtistes() {
		return resultatArtistes;
	}

	public void setResultatArtistes(ArrayList<Artiste> resultatArtistes) {
		this.resultatArtistes = resultatArtistes;
	}

	public ArrayList<Chanson> getResultatChansons() {
		return resultatChansons;
	}

	public void setResultatChansons(ArrayList<Chanson> resultatChansons) {
		this.resultatChansons = resultatChansons;
	}

	public boolean isEtape1() {
		return etape1;
	}

	public void setEtape1(boolean etape1) {
		this.etape1 = etape1;
	}

	public boolean isEtape2chanson() {
		return etape2chanson;
	}

	public void setEtape2chanson(boolean etape2chanson) {
		this.etape2chanson = etape2chanson;
	}

	public boolean isEtape2artiste() {
		return etape2artiste;
	}

	public void setEtape2artiste(boolean etape2artiste) {
		this.etape2artiste = etape2artiste;
	}

	public boolean isEtape2album() {
		return etape2album;
	}

	public void setEtape2album(boolean etape2album) {
		this.etape2album = etape2album;
	}

	public boolean isEtape3chanson() {
		return etape3chanson;
	}

	public void setEtape3chanson(boolean etape3chanson) {
		this.etape3chanson = etape3chanson;
	}

	public boolean isEtape3artiste() {
		return etape3artiste;
	}

	public void setEtape3artiste(boolean etape3artiste) {
		this.etape3artiste = etape3artiste;
	}

	public boolean isEtape3album() {
		return etape3album;
	}

	public void setEtape3album(boolean etape3album) {
		this.etape3album = etape3album;
	}


	public boolean isEtape2(){
		if(etape2album || etape2artiste || etape2chanson){
			return true;
		}else return false;
	}





}
