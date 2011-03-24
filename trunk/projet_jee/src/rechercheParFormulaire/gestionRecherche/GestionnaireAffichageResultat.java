package rechercheParFormulaire.gestionRecherche;

import java.util.ArrayList;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import metier.Cluster;
import metier.ComposantCluster;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;
import bdd.rechercheBDD.RechercheAlbumBDD;
import bdd.rechercheBDD.RechercheChansonBDD;
import exceptions.ChargementException;

/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 *
 */
@ManagedBean
@SessionScoped
public class GestionnaireAffichageResultat {
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	 
	private RechercheAlbum rechercheAlbum = new RechercheAlbum();
	private RechercheArtiste rechercheArtiste = new RechercheArtiste();
	private RechercheChanson rechercheChanson = new RechercheChanson();

	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	

	public ArrayList<Cluster> retournerClustersAlbumNiveau1(Cluster clustersAlbum) {
		ArrayList<Cluster> clustersAlbumsNiveau1 = new ArrayList<Cluster>();
		for(Entry<String,ComposantCluster> currentEntry : 
			clustersAlbum.getContenu().entrySet()){
			clustersAlbumsNiveau1.add((Cluster)currentEntry.getValue());
		}
		return clustersAlbumsNiveau1;
	}
	
	public ArrayList<Cluster> retournerClustersArtisteNiveau1(Cluster clustersArtiste) {
		ArrayList<Cluster> clustersArtistesNiveau1= new ArrayList<Cluster>();
		for(Entry<String,ComposantCluster> currentEntry : 
			clustersArtiste.getContenu().entrySet()){
			clustersArtistesNiveau1.add((Cluster)currentEntry.getValue());
		}
		return clustersArtistesNiveau1;
	}
	
	public ArrayList<Cluster> retournerClustersChansonNiveau1(Cluster clustersChanson) {
		ArrayList<Cluster> clustersChansonsNiveau1= new ArrayList<Cluster>();
		for(Entry<String,ComposantCluster> currentEntry : 
			clustersChanson.getContenu().entrySet()){
			clustersChansonsNiveau1.add((Cluster)currentEntry.getValue());
		}
		return clustersChansonsNiveau1;
	}
	
	
	
	public ArrayList<Cluster>  retournerClustersAlbumNiveau2(Cluster clusterAlbumNiveau1Choisi){
		ArrayList<Cluster> clustersAlbumsNiveau2= new ArrayList<Cluster>();
		for(Entry<String,ComposantCluster> currentEntry : 
			clusterAlbumNiveau1Choisi.getContenu().entrySet()){
			clustersAlbumsNiveau2.add((Cluster)currentEntry.getValue());
		}
		return clustersAlbumsNiveau2;
	}
	
	public ArrayList<Cluster>  retournerClustersArtisteNiveau2(Cluster clusterArtisteNiveau1Choisi){
		ArrayList<Cluster> clustersArtistesNiveau2= new ArrayList<Cluster>();
		for(Entry<String,ComposantCluster> currentEntry : 
			clusterArtisteNiveau1Choisi.getContenu().entrySet()){
			clustersArtistesNiveau2.add((Cluster)currentEntry.getValue());
		}
		return clustersArtistesNiveau2;
	}
	
	public ArrayList<Cluster>  retournerClustersChansonNiveau2(Cluster clusterChansonNiveau1Choisi){
		ArrayList<Cluster> clustersChansonsNiveau2= new ArrayList<Cluster>();
		for(Entry<String,ComposantCluster> currentEntry : 
			clusterChansonNiveau1Choisi.getContenu().entrySet()){
			clustersChansonsNiveau2.add((Cluster)currentEntry.getValue());
		}
		return clustersChansonsNiveau2;
	}
	
	
	public ArrayList<Album> retournerAlbums(Cluster clusterAlbumNiveau2Choisi){
		ArrayList<Album> resultatAlbums= new ArrayList<Album>();
		for(Entry<String,ComposantCluster> currentEntry : 
			clusterAlbumNiveau2Choisi.getContenu().entrySet()){
			try{
				resultatAlbums.add((Album)currentEntry.getValue());
			}
			catch(ClassCastException c){
					System.out.println("problème de cast dans le clusters de d'albums");
				}
		}
		return resultatAlbums;
	}
	
	public ArrayList<Artiste> retournerArtistes(Cluster clusterArtisteNiveau2Choisi){
		ArrayList<Artiste> resultatArtistes= new ArrayList<Artiste>();
		for(Entry<String,ComposantCluster> currentEntry : 
			clusterArtisteNiveau2Choisi.getContenu().entrySet()){
			try{
				resultatArtistes.add((Artiste)currentEntry.getValue());				
			}
			catch(ClassCastException c){
					System.out.println("problème de cast dans le clusters de d'artistes");
			}
			
		}
		return resultatArtistes;
	}


	public ArrayList<Chanson> retournerChansons(Cluster clusterChansonNiveau2Choisi){
		System.out.println("clusterChansonNiveau2Choisi "+clusterChansonNiveau2Choisi);
		ArrayList<Chanson> resultatChansons= new ArrayList<Chanson>();
		for(Entry<String,ComposantCluster> currentEntry : 
			clusterChansonNiveau2Choisi.getContenu().entrySet()){
			try{
			resultatChansons.add((Chanson)currentEntry.getValue());
			}
			catch(ClassCastException c){
				System.out.println("problème de cast dans le clusters de chansons");
			}
		}
		return resultatChansons;
	}
	

	public ArrayList<Chanson> recupererLesChansons(Album album){
		ArrayList<Chanson> chansons = null;
		try {
			chansons= RechercheChansonBDD.getInstance().rechercherChansonAlbum(album.getName());
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chansons;
	}
	
	public ArrayList<Album> recupererLesAlbums(Chanson chanson){
		ArrayList<Album> albums = null;
		try {
			albums= RechercheAlbumBDD.getInstance().rechercherAlbumsChanson(chanson.getID());
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return albums;
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
