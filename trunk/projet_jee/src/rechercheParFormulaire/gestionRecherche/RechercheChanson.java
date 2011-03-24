package rechercheParFormulaire.gestionRecherche;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;




import bdd.rechercheBDD.RechercheArtisteBDD;
import bdd.rechercheBDD.RechercheChansonBDD;


import calculsDesClusters.axe.CoupleAxe;
import calculsDesClusters.calcul.CalculateurDeClustersArtistes;
import calculsDesClusters.calcul.CalculateurDeClustersChansons;
import exceptions.ChargementException;


import metier.Cluster;
import metier.Wiki;

import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;

public class RechercheChanson{
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	private ArrayList<Chanson> resultats;
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/


	/**
	 * methode qui renvoie la liste totale des chansons repondant au mot clé
	 * @param motCle
	 * @return
	 */
	public ArrayList<Cluster> lancerRecherche(String motCle) {
	
		ArrayList<Chanson> listeChansons=null;
		RechercheChansonBDD recherche = RechercheChansonBDD.getInstance();
		try {
			listeChansons = recherche.rechercherChansons(motCle);
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return rechercherMeilleursClusters(listeChansons);
		
	}
	

	
	
	
	
	/**
	 * methode qui renvoie le meilleur cluster, le 2ème meilleur et le 3ème...
	 * 
	 * @param listeChansons
	 * @param niveau
	 * @return
	 */
	private ArrayList<Cluster> rechercherMeilleursClusters(ArrayList<Chanson> listeChansons){
		ArrayList<Cluster> top3clusters = new ArrayList<Cluster>();
		CoupleAxe ca = null;

		HashMap<CoupleAxe,Cluster> listeClustersPossibles = CalculateurDeClustersChansons.
									getInstanceunique().calculEnsembleClustersChansons(listeChansons);

		//1) on récupère le meilleur
		
		Cluster meilleurCluster = new Cluster();
		//on attribue par défaut la meilleure place au premier element de la hashmap
		for(Entry<CoupleAxe, Cluster> entry : listeClustersPossibles.entrySet()) {			
			meilleurCluster = entry.getValue();
			meilleurCluster.setNomCluster(entry.getKey().getAxe1().getType() + ";" + entry.getKey().getAxe2().getType());
			break;
		}
		
		//on parcourt la hashmap pour trouver le cluster qui a la plus petite variance
		for(Entry<CoupleAxe, Cluster> entry2 : listeClustersPossibles.entrySet()) {
			if (entry2.getKey().getVariance() < meilleurCluster.varianceCluster()){
				meilleurCluster = entry2.getValue();
				ca = entry2.getKey();
				meilleurCluster.setNomCluster(entry2.getKey().getAxe1().getType() + ";" + entry2.getKey().getAxe2().getType());
			}
		}
		System.out.println("%%%%%%%%% this is the meilleur cluster 1%%" + meilleurCluster.getNom());
		
		top3clusters.add(meilleurCluster);
		
		//2) on recupère le deuxième meilleur cluster
		
		//on supprime le "1er meilleur"
		listeClustersPossibles.remove(ca);
		
		Cluster meilleurCluster2 = new Cluster();
		//on attribue par défaut la meilleure place au premier element de la hashmap
		for(Entry<CoupleAxe, Cluster> entry : listeClustersPossibles.entrySet()) {			
			meilleurCluster2 = entry.getValue();
			meilleurCluster2.setNomCluster(entry.getKey().getAxe1().getType() + ";" + entry.getKey().getAxe2().getType());
			break;
		}
		
		//on parcourt la hashmap pour trouver le cluster qui a la plus petite variance
		for(Entry<CoupleAxe, Cluster> entry2 : listeClustersPossibles.entrySet()) {
			if (entry2.getKey().getVariance() < meilleurCluster2.varianceCluster()){
				meilleurCluster2 = entry2.getValue();
				ca = entry2.getKey();
				meilleurCluster2.setNomCluster(entry2.getKey().getAxe1().getType() + ";" + entry2.getKey().getAxe2().getType());
			}
		}
		System.out.println("%%%%%%%%% this is the meilleur cluster 2%%" + meilleurCluster2.getNom());
		
		top3clusters.add(meilleurCluster2);
		
		
		//3) on recupère le troisieme meilleur cluster
		
		//on supprime le "2eme meilleur"
		listeClustersPossibles.remove(ca);
		
		Cluster meilleurCluster3 = new Cluster();
		//on attribue par défaut la meilleure place au premier element de la hashmap
		for(Entry<CoupleAxe, Cluster> entry : listeClustersPossibles.entrySet()) {			
			meilleurCluster3 = entry.getValue();
			meilleurCluster3.setNomCluster(entry.getKey().getAxe1().getType() + ";" + entry.getKey().getAxe2().getType());
			break;
		}
		
		//on parcourt la hashmap pour trouver le cluster qui a la plus petite variance
		for(Entry<CoupleAxe, Cluster> entry2 : listeClustersPossibles.entrySet()) {
			if (entry2.getKey().getVariance() < meilleurCluster3.varianceCluster()){
				meilleurCluster3 = entry2.getValue();
				meilleurCluster3.setNomCluster(entry2.getKey().getAxe1().getType() + ";" + entry2.getKey().getAxe2().getType());
			}
		}
		System.out.println("%%%%%%%%% this is the meilleur cluster 3 %%" + meilleurCluster3.getNom());
		
		top3clusters.add(meilleurCluster3);
 		
		return top3clusters;
	}
	
	
	
	
	
	public String retournerTypeAffichage(){
		return "chanson";
	}
}
