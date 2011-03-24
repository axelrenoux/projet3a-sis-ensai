package calculsDesClusters.calcul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import calculsDesClusters.axe.Axe;
import calculsDesClusters.axe.AxeAnnee;
import calculsDesClusters.axe.AxeListener;
import calculsDesClusters.axe.AxePlaycount;
import calculsDesClusters.axe.AxeSaison;
import calculsDesClusters.axe.AxeTag;
import calculsDesClusters.axe.CoupleAxe;

import metier.Cluster;
import metier.ComposantCluster;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Oeuvre;



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



	public Cluster calculerClustersArtiste(Axe axe1, Axe axe2,ArrayList<Artiste> artistes){
		Cluster clusterGeneral = new Cluster();
		HashMap<ComposantCluster,ArrayList<Artiste>> affectationArtisteSousCluster = new HashMap<ComposantCluster,ArrayList<Artiste>>();
		ArrayList<Oeuvre> listeOeuvre = new ArrayList<Oeuvre>();
		listeOeuvre.addAll(artistes);
		axe1.setOeuvres(listeOeuvre);
		
		//affectationArtisteSousCluster va permettre l'affectation des artistes dans les sous-clusters

		//premier decoupage
		//on affecte chaque artiste a un sous-cluster de cluster general
		//et on crée au fur et a mesure les sous-clusters de cluster general
		for(Artiste a : artistes){
			//selon le 1er axe: exemple 
			String valeurAxe = axe1.CalculAxe(a);
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
			calculerClustersArtisteNiveau2(axe2, currentEntry.getKey(),currentEntry.getValue());
		}

		return clusterGeneral;
	}


	public void calculerClustersArtisteNiveau2(Axe axe2, ComposantCluster sousCluster1,ArrayList<Artiste> artistes){
		ArrayList<Oeuvre> listeOeuvre = new ArrayList<Oeuvre>();
		listeOeuvre.addAll(artistes);
		axe2.setOeuvres(listeOeuvre);
		//deuxieme decoupage
		//on ajoute chaque artiste au contenu d'un sous-cluster de sous-cluster1
		//et on créé on fur et a mesure les sous-clusters de sous-cluster1
		for(Artiste a : artistes){
			//selon le 2eme axe: exemple  
			String valeurAxe = axe2.CalculAxe(a);
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

	public HashMap<CoupleAxe,Cluster> calculEnsembleClustersArtistes(ArrayList<Artiste> artistes){

		HashMap<CoupleAxe, Cluster> listeCluster = new HashMap<CoupleAxe, Cluster>();
		Axe axeListeners = new AxeListener();
		Axe axePlaycount = new AxePlaycount();
		//Axe axeTag = new AxeTag();

		ArrayList<Axe> listePremierAxe = new ArrayList<Axe>();

		listePremierAxe.add(axeListeners);
		listePremierAxe.add(axePlaycount);
		//listePremierAxe.add(axeTag);

		ArrayList<Axe> listeDeuxiemeAxe = new ArrayList<Axe>();
		listeDeuxiemeAxe = listePremierAxe;

		for(int i=0;i<listePremierAxe.size()-1;i++){
			for(int j=i+1;j<listeDeuxiemeAxe.size();j++){
				Axe a = listePremierAxe.get(i);
				Axe b = listeDeuxiemeAxe.get(j);
				listeCluster.put(new CoupleAxe(a, b), calculerClustersArtiste(a,b,artistes));
				
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


	public static CalculateurDeClustersArtistes getInstanceunique() {
		return instanceUnique;
	}

}
