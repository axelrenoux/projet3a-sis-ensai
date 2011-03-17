package rechercheParFormulaire.gestionRecherche;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import metier.Cluster;

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
	
	private Cluster clustersAlbum;
	private Cluster clustersArtiste;
	private Cluster clustersChanson;
	
	

	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	/**
	 * methode qui s'execute a la creation du javabean
	 */
	@PostConstruct
	public void init() {
		//on remplit les listes de titres de clusters de niveaux 1 ET 2 
		//‡ partir des clusters
		System.out.println("‡‡‡‡‡‡‡‡");
		
	}
	
	

	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

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
