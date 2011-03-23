package rechercheParFormulaire.vue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.LazyDataModel;

import exceptions.ExceptionNomCluster;

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

	//boolean pour verifier qu'un critere a bien été sélectionné
	private boolean selectionAlbum1NonValide=false;
	private boolean selectionArtiste1NonValide=false;
	private boolean selectionChanson1NonValide=false;
	private boolean selectionAlbum2NonValide=false;
	private boolean selectionArtiste2NonValide=false;
	private boolean selectionChanson2NonValide=false;
	
	
	
	private Chanson chansonChoisie;
	private Album albumChoisi;
	private Artiste artisteChoisi;
	
	private ArrayList<Chanson> chansonsAlbumChoisi;
	private ArrayList<Album> albumsChansonChoisie;

	private LazyDataModel lazyModelAlbums1;
	private LazyDataModel lazyModelAlbums2;
	private LazyDataModel lazyModelAlbums3;
	
	private LazyDataModel lazyModelArtistes1;
	private LazyDataModel lazyModelArtistes2;
	private LazyDataModel lazyModelArtistes3;
	
	private LazyDataModel lazyModelChansons1;
	private LazyDataModel lazyModelChansons2;
	private LazyDataModel lazyModelChansons3;
	
	
	
	private String intituleAxe1Album;
	private String intituleAxe2Album;
	private String intituleAxe1Artiste;
	private String intituleAxe2Artiste;
	private String intituleAxe1Chanson;
	private String intituleAxe2Chanson;
	 

	
	



	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/


	public void init() {
		 
		clustersAlbumsNiveau1 = gestionnaireAffichageResultat.retournerClustersAlbumNiveau1(clustersAlbum);
		clustersArtistesNiveau1 = gestionnaireAffichageResultat.retournerClustersArtisteNiveau1(clustersArtiste);
		clustersChansonsNiveau1 = gestionnaireAffichageResultat.retournerClustersChansonNiveau1(clustersChanson);
		
		try{
			recupererIntitulesAxes();
		}catch(ExceptionNomCluster e){
			System.out.println(e.getTitre() +" "+ e.getMessage());
		}
	
		
		lazyModelAlbums1=null;
		lazyModelAlbums2=null;
		lazyModelAlbums3=null;
		lazyModelArtistes1=null;
		lazyModelArtistes2=null;
		lazyModelArtistes3=null;
		lazyModelChansons1=null;
		lazyModelChansons2=null;
		lazyModelChansons3=null;
		
		
		
		//pour chaque cluster, on cree un lazymodel, pour un affichage optimal 
		
		creerLazyModelAlbums1();
		creerLazyModelArtistes1();
		creerLazyModelChansons1();

 
		  
		  
		reinitialisationSelectionsNonValides();

		reinitialisationEtapes();
		etape1 = true;

	}



	/**
	 * methode executee quand l'utilisateur clique sur "affiner sa recherche" d'albums
	 */
	public void affinerRechercheAlbum1(){
		reinitialisationSelectionsNonValides();
		if(clusterAlbumNiveau1Choisi!=null){
			//on cree le cluster suivant et le lazy model correspondant 
			clustersAlbumsNiveau2 = gestionnaireAffichageResultat.retournerClustersAlbumNiveau2(clusterAlbumNiveau1Choisi);
			creerLazyModelAlbums2();
			//on passe a l'étape 2:
			reinitialisationEtapes();
			etape2album = true;
			addInfoAlbum1();
		}
		else{
			
			selectionAlbum1NonValide= true;
			addErrorEtape1Album();
		}
	}
	
	public void addInfoAlbum1() {   
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				  FacesMessage.SEVERITY_INFO,"Vous recherchez un album", 
				  "Votre 1er critère est "+intituleAxe1Album+ " = " + clusterAlbumNiveau1Choisi.getNomCluster()));   
	}   

	
	
	
	/**
	 * methode executee quand l'utilisateur clique sur "affiner sa recherche" d'artistes
	 */
	public void affinerRechercheArtiste1(){
		reinitialisationSelectionsNonValides();
		if(clusterArtisteNiveau1Choisi!=null){
			clustersArtistesNiveau2 = gestionnaireAffichageResultat.retournerClustersArtisteNiveau2(clusterArtisteNiveau1Choisi);
			creerLazyModelArtistes2();
			//on passe a l'étape 2:
			reinitialisationEtapes();
			etape2artiste = true;
			selectionArtiste1NonValide = false;
			addInfoArtiste1();
		}
		else{
			selectionArtiste1NonValide = true;
			addErrorEtape1Artiste();
		}
	}
	public void addInfoArtiste1() {   
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				  FacesMessage.SEVERITY_INFO,"Vous recherchez un artiste", 
				  "Votre 1er critère est "+intituleAxe1Artiste+ " = " + clusterArtisteNiveau1Choisi.getNomCluster()));   
	}   

	/**
	 * methode executee quand l'utilisateur clique sur "affiner sa recherche" de chansons
	 */
	public void affinerRechercheChanson1(){
		reinitialisationSelectionsNonValides();
		if(clusterChansonNiveau1Choisi!=null){
			clustersChansonsNiveau2 = gestionnaireAffichageResultat.retournerClustersChansonNiveau2(clusterChansonNiveau1Choisi);
			System.out.println("clustersChansonsNiveau2 vue"+clustersChansonsNiveau2);
			creerLazyModelChansons2();
			//on passe a l'étape 2:
			reinitialisationEtapes();
			etape2chanson = true;
			selectionChanson1NonValide = false;
			addInfoChanson1();
		}
		else{
			selectionChanson1NonValide = true;
			addErrorEtape1Chanson();
		}
	}
	public void addInfoChanson1() {   
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				  FacesMessage.SEVERITY_INFO,"Vous recherchez une chanson", 
				  "Votre 1er critère est "+intituleAxe1Chanson+ " = " + clusterChansonNiveau1Choisi.getNomCluster()));   
	}   



	public void affinerRechercheAlbum2(){
		reinitialisationSelectionsNonValides();
		if(clusterAlbumNiveau2Choisi!=null){
			resultatAlbums = gestionnaireAffichageResultat.retournerAlbums(clusterAlbumNiveau2Choisi);
			creerLazyModelAlbums3();
			//on passe a l'étape 3:
			reinitialisationEtapes();
			etape3album = true;
			selectionAlbum2NonValide = false;
			addInfoAlbum2();
		}
		else{
			selectionAlbum2NonValide = true;
			addErrorEtape2Album();
		}
	}
	public void addInfoAlbum2() {   
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				  FacesMessage.SEVERITY_INFO,"Vous recherchez un album", 
				  "Votre 1er critère est "+intituleAxe1Album+ " = " + clusterAlbumNiveau1Choisi.getNomCluster()+ 
				  ", votre 2ème critère est "+intituleAxe2Album+ " = " + clusterAlbumNiveau2Choisi.getNomCluster()));   
	}   

	
	
	public void affinerRechercheArtiste2(){
		reinitialisationSelectionsNonValides();
		if(clusterArtisteNiveau2Choisi!=null){
			resultatArtistes = gestionnaireAffichageResultat.retournerArtistes(clusterArtisteNiveau2Choisi);
			creerLazyModelArtistes3();
			//on passe a l'étape 3:
			reinitialisationEtapes();
			etape3artiste = true;
			selectionArtiste2NonValide = false;
			 addInfoArtiste2();
		}
		else{
			selectionArtiste2NonValide = true;
			addErrorEtape2Artiste();
		}
	}
	public void addInfoArtiste2() {   
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				  FacesMessage.SEVERITY_INFO,"Vous recherchez un artiste", 
				  "Votre 1er critère est "+intituleAxe1Artiste+ " = " + clusterArtisteNiveau1Choisi.getNomCluster()+
		  		  ", votre 2ème critère est "+intituleAxe2Artiste+ " = " + clusterArtisteNiveau2Choisi.getNomCluster()));   
	}
	
	
	public void affinerRechercheChanson2(){
		reinitialisationSelectionsNonValides();
		System.out.println("ùùùù "+clusterChansonNiveau2Choisi);
		if(clusterChansonNiveau2Choisi!=null){
			System.out.println("aa");
			resultatChansons = gestionnaireAffichageResultat.retournerChansons(clusterChansonNiveau2Choisi);
			System.out.println("bb");
			creerLazyModelChansons3();
			//on passe a l'étape 3:
			reinitialisationEtapes();
			etape3chanson = true;
			selectionChanson2NonValide = false;
			addInfoChanson2();
		}
		else{
			selectionChanson2NonValide = true;
			addErrorEtape2Chanson();
		}
	}
	public void addInfoChanson2() {   
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				  FacesMessage.SEVERITY_INFO,"Vous recherchez une chanson", 
				  "Votre 1er critère est "+intituleAxe1Chanson+ " = " + clusterChansonNiveau1Choisi.getNomCluster()+ 
				  ", votre 2ème critère est "+intituleAxe2Chanson+ " = " + clusterChansonNiveau2Choisi.getNomCluster()));   
	}

	

	public void reinitialisationEtapes(){
		etape1=false;
		etape2album = false;
		etape2artiste = false;
		etape2chanson = false;
		etape3album =false;
		etape3artiste = false;
		etape3chanson = false;
		chansonChoisie =null;
		artisteChoisi = null;
		albumChoisi = null;
		chansonsAlbumChoisi = null;
		albumsChansonChoisie = null;
	}
	
	public void reinitialisationSelectionsNonValides(){
		selectionAlbum1NonValide = false;
		selectionAlbum2NonValide = false;
		selectionArtiste1NonValide = false;
		selectionArtiste2NonValide = false;
		selectionChanson1NonValide = false;
		selectionChanson2NonValide = false;
	}
	
	
	
	
	
	

	
	/**
	 * on sait que le nom d'un cluster general est composé du type du premier
	 * axe selon lequel il est découpé, suivi du type du second axe selon
	 * lequel il est découpé, séparés par un ";"
	 * @param clusterGeneral
	 * @throws ExceptionNomCluster 
	 */
	public void recupererIntitulesAxes() throws ExceptionNomCluster{
		String[] deuxTypes;
		try{
			deuxTypes = clustersAlbum.getNomCluster().split(";");
			intituleAxe1Album = deuxTypes[0];
			intituleAxe2Album = deuxTypes[1];
		}catch (java.lang.ArrayIndexOutOfBoundsException e1){
			throw new ExceptionNomCluster(clustersAlbum.getNomCluster());
		}
		try{
			deuxTypes = clustersArtiste.getNomCluster().split(";");
			intituleAxe1Artiste = deuxTypes[0];
			intituleAxe2Artiste = deuxTypes[1];
		}catch (java.lang.ArrayIndexOutOfBoundsException e2){
			throw new ExceptionNomCluster(clustersArtiste.getNomCluster());
		}	
		try{
			deuxTypes = clustersChanson.getNomCluster().split(";");
			intituleAxe1Chanson = deuxTypes[0];
			intituleAxe2Chanson = deuxTypes[1];
		}catch (java.lang.ArrayIndexOutOfBoundsException e3){
			throw new ExceptionNomCluster(clustersChanson.getNomCluster());
		}	
		System.out.println(intituleAxe1Album + ", " + intituleAxe1Artiste
						+", " + intituleAxe1Chanson + ", " + intituleAxe2Album + 
						", " + intituleAxe2Artiste + ", " + intituleAxe2Chanson);
	}
		
		
		
		
	
	
	/**
	 * creation des LazyDataModel à partir des listes de clusters
	 */
	public void creerLazyModelAlbums1(){
		lazyModelAlbums1 = new LazyDataModel<Cluster>(){
		@Override
		public List<Cluster> load(int first, int pageSize, String sortField,
			boolean sortOrder, Map<String, String> filters) {
			//logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});
			List<Cluster> lasyClusterAlbums = new ArrayList<Cluster>();
			//on determine le nombre max de resultats par page:
			int size=clustersAlbumsNiveau1.size();
			if(clustersAlbumsNiveau1.subList(first,size).size()<pageSize){
				pageSize=clustersAlbumsNiveau1.subList(first,size).size();
			}
			lasyClusterAlbums = clustersAlbumsNiveau1.subList(first, first+pageSize);	
			return lasyClusterAlbums;
			}
		};
		lazyModelAlbums1.setRowCount(clustersAlbumsNiveau1.size());   
	}
	
	
	public void creerLazyModelArtistes1(){
		lazyModelArtistes1 = new LazyDataModel<Cluster>(){
			@Override
			public List<Cluster> load(int first, int pageSize, String sortField,
				boolean sortOrder, Map<String, String> filters) {
				//logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});
				List<Cluster> lasyClusterArtiste = new ArrayList<Cluster>();
				//on determine le nombre max de resultats par page:
				int size=clustersArtistesNiveau1.size();
				if(clustersArtistesNiveau1.subList(first,size).size()<pageSize){
					pageSize=clustersArtistesNiveau1.subList(first,size).size();
				}
				lasyClusterArtiste = clustersArtistesNiveau1.subList(first, first+pageSize);
				return lasyClusterArtiste;
				}
			};
			lazyModelArtistes1.setRowCount(clustersArtistesNiveau1.size());  
	}
	
	public void creerLazyModelChansons1(){
		lazyModelChansons1 = new LazyDataModel<Cluster>(){
			@Override
			public List<Cluster> load(int first, int pageSize, String sortField,
				boolean sortOrder, Map<String, String> filters) {
				//logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});
				List<Cluster> lasyClusterChanson = new ArrayList<Cluster>();
				//on determine le nombre max de resultats par page:
				int size=clustersChansonsNiveau1.size();
				if(clustersChansonsNiveau1.subList(first,size).size()<pageSize){
					pageSize=clustersChansonsNiveau1.subList(first,size).size();
				}
				lasyClusterChanson = clustersChansonsNiveau1.subList(first, first+pageSize);
				return lasyClusterChanson;
				}
			};
			lazyModelChansons1.setRowCount(clustersChansonsNiveau1.size());  
	}
	
	
	public void creerLazyModelAlbums2(){
		lazyModelAlbums2 = new LazyDataModel<Cluster>(){
		@Override
		public List<Cluster> load(int first, int pageSize, String sortField,
			boolean sortOrder, Map<String, String> filters) {
			//logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});
			List<Cluster> lasyClusterAlbums = new ArrayList<Cluster>();
			//on determine le nombre max de resultats par page:
			int size=clustersAlbumsNiveau2.size();
			if(clustersAlbumsNiveau2.subList(first,size).size()<pageSize){
				pageSize=clustersAlbumsNiveau2.subList(first,size).size();
			}
			lasyClusterAlbums = clustersAlbumsNiveau2.subList(first, first+pageSize);	
			return lasyClusterAlbums;
			}
		};
		lazyModelAlbums2.setRowCount(clustersAlbumsNiveau2.size());   
	}
	
	
	public void creerLazyModelArtistes2(){
		lazyModelArtistes2 = new LazyDataModel<Cluster>(){
			@Override
			public List<Cluster> load(int first, int pageSize, String sortField,
				boolean sortOrder, Map<String, String> filters) {
				//logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});
				List<Cluster> lasyClusterArtiste = new ArrayList<Cluster>();
				//on determine le nombre max de resultats par page:
				int size=clustersArtistesNiveau2.size();
				if(clustersArtistesNiveau2.subList(first,size).size()<pageSize){
					pageSize=clustersArtistesNiveau2.subList(first,size).size();
				}
				lasyClusterArtiste = clustersArtistesNiveau2.subList(first, first+pageSize);
				return lasyClusterArtiste;
				}
			};
			lazyModelArtistes2.setRowCount(clustersArtistesNiveau2.size());  
	}
	
	public void creerLazyModelChansons2(){
		lazyModelChansons2 = new LazyDataModel<Cluster>(){
			@Override
			public List<Cluster> load(int first, int pageSize, String sortField,
				boolean sortOrder, Map<String, String> filters) {
				//logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});
				List<Cluster> lasyClusterChanson = new ArrayList<Cluster>();
				//on determine le nombre max de resultats par page:
				int size=clustersChansonsNiveau2.size();
				if(clustersChansonsNiveau2.subList(first,size).size()<pageSize){
					pageSize=clustersChansonsNiveau2.subList(first,size).size();
				}
				lasyClusterChanson = clustersChansonsNiveau2.subList(first, first+pageSize);
				return lasyClusterChanson;
				}
			};
			lazyModelChansons2.setRowCount(clustersChansonsNiveau2.size());  
	}
	
	
	
	
	
	public void creerLazyModelAlbums3(){
		lazyModelAlbums3 = new LazyDataModel<Album>(){
			@Override
			public List<Album> load(int first, int pageSize, String sortField,
				boolean sortOrder, Map<String, String> filters) {
				//logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});
				List<Album> lasyCluster = new ArrayList<Album>();
				//on determine le nombre max de resultats par page:
				int size=resultatAlbums.size();
				if(resultatAlbums.subList(first,size).size()<pageSize){
					pageSize=resultatAlbums.subList(first,size).size();
				}
				lasyCluster = resultatAlbums.subList(first, first+pageSize);
				return lasyCluster;
				}
			};
			lazyModelAlbums3.setRowCount(resultatAlbums.size());  
	}
	
	public void creerLazyModelArtistes3(){
		lazyModelArtistes3 = new LazyDataModel<Artiste>(){
			@Override
			public List<Artiste> load(int first, int pageSize, String sortField,
				boolean sortOrder, Map<String, String> filters) {
				//logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});
				List<Artiste> lasyCluster = new ArrayList<Artiste>();
				//on determine le nombre max de resultats par page:
				int size=resultatArtistes.size();
				if(resultatArtistes.subList(first,size).size()<pageSize){
					pageSize=resultatArtistes.subList(first,size).size();
				}
				lasyCluster = resultatArtistes.subList(first, first+pageSize);
				return lasyCluster;
				}
			};
			lazyModelArtistes3.setRowCount(resultatArtistes.size());  
	}
	
	
	public void creerLazyModelChansons3(){
		lazyModelChansons3 = new LazyDataModel<Chanson>(){
			@Override
			public List<Chanson> load(int first, int pageSize, String sortField,
				boolean sortOrder, Map<String, String> filters) {
				//logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});
				List<Chanson> lasyCluster = new ArrayList<Chanson>();
				//on determine le nombre max de resultats par page:
				int size=resultatChansons.size();
				if(resultatChansons.subList(first,size).size()<pageSize){
					pageSize=resultatChansons.subList(first,size).size();
				}
				lasyCluster = resultatChansons.subList(first, first+pageSize);
				return lasyCluster;
				}
			};
			lazyModelChansons3.setRowCount(resultatChansons.size()); 
	}
	
	
	
	
	
	
	/**
	 *  methodes de navigation
	 */
	public void retourEtape1(){
		reinitialisationEtapes();
		etape1 = true;
	}

	
	public void retourEtapeArtiste2(){
		reinitialisationEtapes();
		addInfoArtiste1();
		etape2artiste = true;
	}
	
	
	public void retourEtapeAlbum2(){
		reinitialisationEtapes();
		etape2album = true;
		addInfoAlbum1();
	}

	public void retourEtapeChanson2(){
		reinitialisationEtapes();
		etape2chanson = true;
		addInfoChanson1();
	}

	
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

	

	public boolean isSelectionAlbum1NonValide() {
		return selectionAlbum1NonValide;
	}



	public void setSelectionAlbum1NonValide(boolean selectionAlbum1NonValide) {
		this.selectionAlbum1NonValide = selectionAlbum1NonValide;
	}



	public boolean isSelectionArtiste1NonValide() {
		return selectionArtiste1NonValide;
	}



	public void setSelectionArtiste1NonValide(boolean selectionArtiste1NonValide) {
		this.selectionArtiste1NonValide = selectionArtiste1NonValide;
	}



	public boolean isSelectionChanson1NonValide() {
		return selectionChanson1NonValide;
	}



	public void setSelectionChanson1NonValide(boolean selectionChanson1NonValide) {
		this.selectionChanson1NonValide = selectionChanson1NonValide;
	}



	public boolean isSelectionAlbum2NonValide() {
		return selectionAlbum2NonValide;
	}



	public void setSelectionAlbum2NonValide(boolean selectionAlbum2NonValide) {
		this.selectionAlbum2NonValide = selectionAlbum2NonValide;
	}



	public boolean isSelectionArtiste2NonValide() {
		return selectionArtiste2NonValide;
	}



	public void setSelectionArtiste2NonValide(boolean selectionArtiste2NonValide) {
		this.selectionArtiste2NonValide = selectionArtiste2NonValide;
	}



	public boolean isSelectionChanson2NonValide() {
		return selectionChanson2NonValide;
	}



	public void setSelectionChanson2NonValide(boolean selectionChanson2NonValide) {
		this.selectionChanson2NonValide = selectionChanson2NonValide;
	}



	public boolean isEtape2(){
		if(etape2album || etape2artiste || etape2chanson){
			return true;
		}else return false;
	}


	public String getLargeurSousPanels(){
		return "390px";
	}
	
	public String getLargeurDataTables1(){
		return "350px";
	}
	
	public String getLargeurDataTables2_3(){
		return "1100px";
	}
	
	public String getLargeurSousPanelSeul(){
		return "1150px";
	}



	public LazyDataModel getLazyModelAlbums1() {
		return lazyModelAlbums1;
	}



	public void setLazyModelAlbums1(LazyDataModel lazyModelAlbums1) {
		this.lazyModelAlbums1 = lazyModelAlbums1;
	}



	public LazyDataModel getLazyModelAlbums2() {
		return lazyModelAlbums2;
	}



	public void setLazyModelAlbums2(LazyDataModel lazyModelAlbums2) {
		this.lazyModelAlbums2 = lazyModelAlbums2;
	}



	public LazyDataModel getLazyModelAlbums3() {
		return lazyModelAlbums3;
	}



	public void setLazyModelAlbums3(LazyDataModel lazyModelAlbums3) {
		this.lazyModelAlbums3 = lazyModelAlbums3;
	}



	public LazyDataModel getLazyModelArtistes1() {
		return lazyModelArtistes1;
	}



	public void setLazyModelArtistes1(LazyDataModel lazyModelArtistes1) {
		this.lazyModelArtistes1 = lazyModelArtistes1;
	}



	public LazyDataModel getLazyModelArtistes2() {
		return lazyModelArtistes2;
	}



	public void setLazyModelArtistes2(LazyDataModel lazyModelArtistes2) {
		this.lazyModelArtistes2 = lazyModelArtistes2;
	}



	public LazyDataModel getLazyModelArtistes3() {
		return lazyModelArtistes3;
	}



	public void setLazyModelArtistes3(LazyDataModel lazyModelArtistes3) {
		this.lazyModelArtistes3 = lazyModelArtistes3;
	}



	public LazyDataModel getLazyModelChansons1() {
		return lazyModelChansons1;
	}



	public void setLazyModelChansons1(LazyDataModel lazyModelChansons1) {
		this.lazyModelChansons1 = lazyModelChansons1;
	}



	public LazyDataModel getLazyModelChansons2() {
		return lazyModelChansons2;
	}



	public void setLazyModelChansons2(LazyDataModel lazyModelChansons2) {
		this.lazyModelChansons2 = lazyModelChansons2;
	}



	public LazyDataModel getLazyModelChansons3() {
		return lazyModelChansons3;
	}



	public void setLazyModelChansons3(LazyDataModel lazyModelChansons3) {
		this.lazyModelChansons3 = lazyModelChansons3;
	}




	public Chanson getChansonChoisie() {
		return chansonChoisie;
	}



	public void setChansonChoisie(Chanson chansonChoisie) {
		this.chansonChoisie = chansonChoisie;
	}



	public Album getAlbumChoisi() {
		return albumChoisi;
	}



	public void setAlbumChoisi(Album albumChoisi) {
		this.albumChoisi = albumChoisi;
	}



	public Artiste getArtisteChoisi() {
		return artisteChoisi;
	}



	public void setArtisteChoisi(Artiste artisteChoisi) {
		this.artisteChoisi = artisteChoisi;
	}



	public ArrayList<Chanson> getChansonsAlbumChoisi() {
		return chansonsAlbumChoisi;
	}



	public void setChansonsAlbumChoisi(ArrayList<Chanson> chansonsAlbumChoisi) {
		this.chansonsAlbumChoisi = chansonsAlbumChoisi;
	}



	public ArrayList<Album> getAlbumsChansonChoisie() {
		return albumsChansonChoisie;
	}



	public void setAlbumsChansonChoisie(ArrayList<Album> albumsChansonChoisie) {
		this.albumsChansonChoisie = albumsChansonChoisie;
	}



	public String getIntituleAxe1Album() {
		return intituleAxe1Album;
	}



	public void setIntituleAxe1Album(String intituleAxe1Album) {
		this.intituleAxe1Album = intituleAxe1Album;
	}



	public String getIntituleAxe2Album() {
		return intituleAxe2Album;
	}



	public void setIntituleAxe2Album(String intituleAxe2Album) {
		this.intituleAxe2Album = intituleAxe2Album;
	}



	public String getIntituleAxe1Artiste() {
		return intituleAxe1Artiste;
	}



	public void setIntituleAxe1Artiste(String intituleAxe1Artiste) {
		this.intituleAxe1Artiste = intituleAxe1Artiste;
	}



	public String getIntituleAxe2Artiste() {
		return intituleAxe2Artiste;
	}



	public void setIntituleAxe2Artiste(String intituleAxe2Artiste) {
		this.intituleAxe2Artiste = intituleAxe2Artiste;
	}



	public String getIntituleAxe1Chanson() {
		return intituleAxe1Chanson;
	}



	public void setIntituleAxe1Chanson(String intituleAxe1Chanson) {
		this.intituleAxe1Chanson = intituleAxe1Chanson;
	}



	public String getIntituleAxe2Chanson() {
		return intituleAxe2Chanson;
	}



	public void setIntituleAxe2Chanson(String intituleAxe2Chanson) {
		this.intituleAxe2Chanson = intituleAxe2Chanson;
	}



	 


}
