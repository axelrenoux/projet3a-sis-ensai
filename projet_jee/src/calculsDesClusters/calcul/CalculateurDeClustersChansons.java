package calculsDesClusters.calcul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import metier.Cluster;
import metier.ComposantCluster;
import metier.oeuvres.Chanson;



public class CalculateurDeClustersChansons {
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private static final CalculateurDeClustersChansons instanceUnique = new CalculateurDeClustersChansons();

	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	private CalculateurDeClustersChansons(){
	}



	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/

	public Cluster calculerClustersChanson(ArrayList<Chanson> chansons){
		Cluster clusterGeneral = new Cluster();
		HashMap<ComposantCluster,ArrayList<Chanson>> affectationChansonSousCluster = new HashMap<ComposantCluster,ArrayList<Chanson>>();
		//affectationChansonSousCluster va permettre l'affectation des Chansons dans les sous-clusters
		
		//premier decoupage
		//on affecte chaque Chanson a un sous-cluster de cluster general
		//et on crée au fur et a mesure les sous-clusters de cluster general
		for(Chanson a : chansons){
			//selon le 1er axe: exemple 
			String valeurAxe = a.getDuree()+"";
			//si le clusterGeneral ne contient aucun cluster pour cette valeur
			if(!clusterGeneral.getContenu().containsKey(valeurAxe)){
				//on cree le sous-cluster de niveau 1
				ComposantCluster c = new Cluster(valeurAxe);
				clusterGeneral.getContenu().put(valeurAxe, c);
				//on cree une nouvelle liste de chansons à affecter à ce nouveau sous_cluster
				//a laquelle on ajoute la chanson courante
				ArrayList<Chanson> sesChansons= new ArrayList<Chanson>();
				sesChansons.add(a);
				affectationChansonSousCluster.put(c, sesChansons);
			}else{
				ComposantCluster c = clusterGeneral.getContenu().get(valeurAxe);
				//sinon, on ajoute juste la chanson à la liste des chansons  à affecter à ce cluster
				affectationChansonSousCluster.get(c).add(a);
			}
		}
		
		//on cree les sous-clusters de niveau 2 pour chaque sous-cluster de niveau 1
		for(Entry<ComposantCluster, ArrayList<Chanson>> currentEntry : affectationChansonSousCluster.entrySet()){
			calculerClustersChansonNiveau2(currentEntry.getKey(),currentEntry.getValue());
		}
		
		return clusterGeneral;
	}
	
	
	public void calculerClustersChansonNiveau2(ComposantCluster sousCluster1,ArrayList<Chanson> chansons){
		
		//deuxieme decoupage
		//on ajoute chaque chanson au contenu d'un sous-cluster de sous-cluster1
		//et on créé on fur et a mesure les sous-clusters de sous-cluster1
		for(Chanson ch : chansons){
			//selon le 2eme axe: 
			String valeurAxe = ch.getListeners()+"";
			//si le clusterGeneral ne contient aucun cluster pour cette valeur
			if(!sousCluster1.getContenu().containsKey(valeurAxe)){
				//on cree le sous-cluster de niveau 2
				ComposantCluster c = new Cluster(valeurAxe);
				sousCluster1.getContenu().put(valeurAxe, c);
			}
			//on ajoute la chanson au sous-cluster de niveau 2 qui correspond
			ComposantCluster sousClusterNiveau2 = sousCluster1.getContenu().get(valeurAxe);
			sousClusterNiveau2.getContenu().put(ch.getUrl(),ch);
		}
	}
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	 
	public static CalculateurDeClustersChansons getInstanceunique() {
		return instanceUnique;
	}
	
}
