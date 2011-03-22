package calculsDesClusters.calcul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import calculsDesClusters.axe.Axe;
import calculsDesClusters.axe.AxeAlbumDeChanson;
import calculsDesClusters.axe.AxeAnnee;
import calculsDesClusters.axe.AxeDuree;
import calculsDesClusters.axe.AxeListener;
import calculsDesClusters.axe.AxePlaycount;
import calculsDesClusters.axe.AxeSaison;
import calculsDesClusters.axe.AxeTag;
import calculsDesClusters.axe.CoupleAxe;

import metier.Cluster;
import metier.ComposantCluster;
import metier.oeuvres.Album;
import metier.oeuvres.Chanson;
import metier.oeuvres.Oeuvre;



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

	public Cluster calculerClustersChanson(Axe axe1, Axe axe2,ArrayList<Chanson> chansons){
		Cluster clusterGeneral = new Cluster();
		HashMap<ComposantCluster,ArrayList<Chanson>> affectationChansonSousCluster = new HashMap<ComposantCluster,ArrayList<Chanson>>();
		ArrayList<Oeuvre> listeOeuvre = new ArrayList<Oeuvre>();
		listeOeuvre.addAll(chansons);
		axe1.setOeuvres(listeOeuvre);
		axe2.setOeuvres(listeOeuvre);
		
		//affectationChansonSousCluster va permettre l'affectation des Chansons dans les sous-clusters
		//premier decoupage
		//on affecte chaque Chanson a un sous-cluster de cluster general
		//et on crée au fur et a mesure les sous-clusters de cluster general
		for(Chanson a : chansons){
			//selon le 1er axe: exemple 
			String valeurAxe = axe1.CalculAxe(a);
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
			calculerClustersChansonNiveau2(axe2,currentEntry.getKey(),currentEntry.getValue());
		}
		
		return clusterGeneral;
	}
	
	
	public void calculerClustersChansonNiveau2(Axe axe, ComposantCluster sousCluster1,ArrayList<Chanson> chansons){
		//deuxieme decoupage
		//on ajoute chaque chanson au contenu d'un sous-cluster de sous-cluster1
		//et on créé on fur et a mesure les sous-clusters de sous-cluster1
		for(Chanson ch : chansons){
			//selon le 2eme axe: exemple saison
			String valeurAxe = axe.CalculAxe(ch);
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
	
	public HashMap<CoupleAxe,Cluster> calculEnsembleClustersChansons(ArrayList<Chanson> chansons){

		HashMap<CoupleAxe, Cluster> listeCluster = new HashMap<CoupleAxe, Cluster>();
		
		Axe axeListeners = new AxeListener();
		Axe axePlaycount = new AxePlaycount();
		//Axe axeAlbumDeChanson = new AxeAlbumDeChanson();
		Axe axeDuree = new AxeDuree();
		//Axe axeTag = new AxeTag();

		ArrayList<Axe> listePremierAxe = new ArrayList<Axe>();
		listePremierAxe.add(axeListeners);
		listePremierAxe.add(axePlaycount);
		//listePremierAxe.add(axeAlbumDeChanson);
		listePremierAxe.add(axeDuree);
		//listePremierAxe.add(axeTag);
		ArrayList<Axe> listeDeuxiemeAxe = new ArrayList<Axe>();
		listeDeuxiemeAxe = listePremierAxe;

		
		for(int i=0;i<listePremierAxe.size()-1;i++){
			for(int j=i+1;j<listeDeuxiemeAxe.size();j++){
				Axe a = listePremierAxe.get(i);
				Axe b = listeDeuxiemeAxe.get(j);
				listeCluster.put(new CoupleAxe(a, b), calculerClustersChanson(a,b,chansons));		
			}
		}
		
		for(Entry<CoupleAxe, Cluster> entry : listeCluster.entrySet()) {
			entry.getKey().setVariance(entry.getValue().varianceCluster());
			System.out.println("§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§");
			System.out.println(entry.getKey().getAxe1());
			System.out.println(entry.getKey().getAxe2());
			System.out.println(entry.getKey().getVariance());
		}
		return listeCluster;
	}
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	 
	public static CalculateurDeClustersChansons getInstanceunique() {
		return instanceUnique;
	}
	
}
