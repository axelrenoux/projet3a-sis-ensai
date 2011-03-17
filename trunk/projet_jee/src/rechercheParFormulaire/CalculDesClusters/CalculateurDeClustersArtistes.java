package rechercheParFormulaire.CalculDesClusters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import metier.Cluster;
import metier.ComposantCluster;
import metier.oeuvres.Artiste;



public class CalculateurDeClustersArtistes {
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/

	private static final CalculateurDeClustersArtistes instanceUnique = new CalculateurDeClustersArtistes();


	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	private CalculateurDeClustersArtistes(){
	}



	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/

	public Cluster calculerClustersArtiste(ArrayList<Artiste> artistes){
		Cluster clusterGeneral = new Cluster();
		HashMap<ComposantCluster,ArrayList<Artiste>> affectationArtisteSousCluster = new HashMap<ComposantCluster,ArrayList<Artiste>>();
		//affectationArtisteSousCluster va permettre l'affectation des artistes dans les sous-clusters

		//premier decoupage
		//on affecte chaque artiste a un sous-cluster de cluster general
		//et on crée au fur et a mesure les sous-clusters de cluster general
		for(Artiste a : artistes){
			//selon le 1er axe: exemple 
			String valeurAxe = a.getListeners()+"";
			//si le clusterGeneral ne contient aucun cluster pour cette valeur
			if(!clusterGeneral.getContenu().containsKey(valeurAxe)){
				//on cree le sous-cluster de niveau 1
				ComposantCluster c = new Cluster(valeurAxe);
				clusterGeneral.getContenu().put(valeurAxe, c);
				//on cree une nouvelle liste d'artistes à affecter à ce nouveau sous_cluster
				//a laquelle on ajoute l'artiste courant
				ArrayList<Artiste> sesArtistes= new ArrayList<Artiste>();
				sesArtistes.add(a);
				affectationArtisteSousCluster.put(c, sesArtistes);
			}else{
				ComposantCluster c = clusterGeneral.getContenu().get(valeurAxe);
				//sinon, on ajoute juste l'artiste à la liste des artistes  à affecter à ce cluster
				affectationArtisteSousCluster.get(c).add(a);
			}
		}

		//on cree les sous-clusters de niveau 2 pour chaque sous-cluster de niveau 1
		for(Entry<ComposantCluster, ArrayList<Artiste>> currentEntry : affectationArtisteSousCluster.entrySet()){
			calculerClustersArtisteNiveau2(currentEntry.getKey(),currentEntry.getValue());
		}

		return clusterGeneral;
	}


	public void calculerClustersArtisteNiveau2(ComposantCluster sousCluster1,ArrayList<Artiste> artistes){

		//deuxieme decoupage
		//on ajoute chaque artiste au contenu d'un sous-cluster de sous-cluster1
		//et on créé on fur et a mesure les sous-clusters de sous-cluster1
		for(Artiste a : artistes){
			//selon le 2eme axe: exemple  
			String valeurAxe = a.getPlaycount()+"";
			//si le clusterGeneral ne contient aucun cluster pour cette valeur
			if(!sousCluster1.getContenu().containsKey(valeurAxe)){
				//on cree le sous-cluster de niveau 2
				ComposantCluster c = new Cluster(valeurAxe);
				sousCluster1.getContenu().put(valeurAxe, c);
			}
			//on ajoute l'artiste au sous-cluster de niveau 2 qui correspond
			ComposantCluster sousClusterNiveau2 = sousCluster1.getContenu().get(valeurAxe);
			sousClusterNiveau2.getContenu().put(a.getUrl(),a);
		}
	}

	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/


	public static CalculateurDeClustersArtistes getInstanceunique() {
		return instanceUnique;
	}

}
