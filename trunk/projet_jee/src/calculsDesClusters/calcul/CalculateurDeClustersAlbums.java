package calculsDesClusters.calcul;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import metier.Cluster;
import metier.ComposantCluster;
import metier.oeuvres.Album;
import calculsDesClusters.axe.Axe;




public class CalculateurDeClustersAlbums {
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private static final CalculateurDeClustersAlbums instanceUnique = new CalculateurDeClustersAlbums();

	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	private CalculateurDeClustersAlbums(){
	}



	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/


	/**************************    albums    ****************************/
	
	public Cluster calculerClustersAlbum(Axe axe1, Axe axe2, ArrayList<Album> albums){
		Cluster clusterGeneral = new Cluster();
		HashMap<ComposantCluster,ArrayList<Album>> affectationAlbumSousCluster = new HashMap<ComposantCluster,ArrayList<Album>>();
		//affectationAlbumSousCluster va permettre l'affectation des albums dans les sous-clusters
		
		//premier decoupage
		//on affecte chaque album a un sous-cluster de cluster general
		//et on crée au fur et a mesure les sous-clusters de cluster general
		for(Album a : albums){
			//selon le 1er axe: exemple annee
			String valeurAxe = axe1.CalculAxe(a);
			//si le clusterGeneral ne contient aucun cluster pour cette valeur
			if(!clusterGeneral.getContenu().containsKey(valeurAxe)){
				//on cree le sous-cluster de niveau 1
				ComposantCluster c = new Cluster(valeurAxe);
				clusterGeneral.getContenu().put(valeurAxe, c);
				//on cree une nouvelle liste d'album à affecter à ce nouveau sous_cluster
				//a laquelle on ajoute l'album courant
				ArrayList<Album> sesAlbums= new ArrayList<Album>();
				sesAlbums.add(a);
				affectationAlbumSousCluster.put(c, sesAlbums);
			}else{
				ComposantCluster c = clusterGeneral.getContenu().get(valeurAxe);
				//sinon, on ajoute juste l'album à la liste des albums  à affecter à ce cluster
				affectationAlbumSousCluster.get(c).add(a);
			}
		}
		
		//on cree les sous-clusters de niveau 2 pour chaque sous-cluster de niveau 1
		for(Entry<ComposantCluster, ArrayList<Album>> currentEntry : affectationAlbumSousCluster.entrySet()){
			calculerClustersAlbumNiveau2(axe2, currentEntry.getKey(),currentEntry.getValue());
		}
		
		return clusterGeneral;
	}
	
	
	public void calculerClustersAlbumNiveau2(Axe axe, ComposantCluster sousCluster1,ArrayList<Album> albums){
		
		//deuxieme decoupage
		//on ajoute chaque album au contenu d'un sous-cluster de sous-cluster1
		//et on crée au fur et a mesure les sous-clusters de sous-cluster1
		for(Album a : albums){
			//selon le 2eme axe: exemple saison
			String valeurAxe = axe.CalculAxe(a);
			//si le clusterGeneral ne contient aucun cluster pour cette valeur
			if(!sousCluster1.getContenu().containsKey(valeurAxe)){
				//on cree le sous-cluster de niveau 2
				ComposantCluster c = new Cluster(valeurAxe);
				sousCluster1.getContenu().put(valeurAxe, c);
			}
			//on ajoute l'album au sous-cluster de niveau 2 qui correspond
			ComposantCluster sousClusterNiveau2 = sousCluster1.getContenu().get(valeurAxe);
			sousClusterNiveau2.getContenu().put(a.getUrl(),a);//TODO je change par url
		}
	}
	
	/****************** valeurs des differents axes   ************************/
	
	public String miseEnClassesAnnees(Date date){
		String classe = "";
		String year = date.getYear()+1900+"";
		String yearArrondiString = year.substring(0,3)+"0";
		//on n'a plus que 2010,2000,1990,1980...
		int yearArrondi = Integer.parseInt(yearArrondiString);
		
		switch (yearArrondi) {
		case 2010: 
			classe = "années 2010";
			break;
		case 2000: 
			classe = "années 2000";
			break;
		case 1990: 
			classe = "années 90";
			break;
		case 1980: 
			classe = "années 80";
			break;
		case 1970: 
			classe = "années 70";
			break;
		case 1960: 
			classe = "années 60";
			break;
		default: 
			classe = "année inconnue";
			break;
		}
		
		if(yearArrondi<1960){
			classe="avant les années 60";
		}

		
		return classe;
	}
	
	
	/**
	 * on definit "été" pour les dates du 1er avril au 30septembre
	 * on definit "hiver" pour les dates du 1er octobre au 31 mars
	 * @param d
	 * @return
	 */
	public String miseEnSaison(Date d){
		if(d.getMonth()+1<10 && d.getMonth()+1>3) return "été";
		else return "hiver";
	}
	
	
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	 
	public static CalculateurDeClustersAlbums getInstanceunique() {
		return instanceUnique;
	}
	
}
