package calculsDesClusters.calcul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import metier.Cluster;
import metier.ComposantCluster;
import metier.oeuvres.Album;
import calculsDesClusters.axe.Axe;
import calculsDesClusters.axe.AxeAnnee;
import calculsDesClusters.axe.AxeListener;
import calculsDesClusters.axe.AxePlaycount;
import calculsDesClusters.axe.AxeSaison;
import calculsDesClusters.axe.CoupleAxe;




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


	public HashMap<CoupleAxe,Cluster> calculEnsembleClustersAlbums(ArrayList<Album> albums){

		HashMap<CoupleAxe, Cluster> listeCluster = new HashMap<CoupleAxe, Cluster>();
		CoupleAxe currentCouple = new CoupleAxe();
		Cluster currentCluster = new Cluster();
		
		Axe axeAnnee = new AxeAnnee();
		Axe axeSaison = new AxeSaison();
		Axe axeListeners = new AxeListener();
		Axe axePlaycount = new AxePlaycount();

		ArrayList<Axe> listePremierAxe = new ArrayList<Axe>();
		listePremierAxe.add(axeAnnee);	
		listePremierAxe.add(axeSaison);
		listePremierAxe.add(axeListeners);
		listePremierAxe.add(axePlaycount);
		ArrayList<Axe> listeDeuxiemeAxe = new ArrayList<Axe>();

		for (Axe a : listePremierAxe){
			listeDeuxiemeAxe = listePremierAxe;
			listeDeuxiemeAxe.remove(a);
			for (Axe b : listeDeuxiemeAxe){
				currentCluster = calculerClustersAlbum(a,b,albums);
				currentCouple.setAxe1(a);
				currentCouple.setAxe2(b);
				currentCouple.setVariance(currentCluster.varianceCluster());
				listeCluster.put(currentCouple, currentCluster);
			}
		}

		return listeCluster;
	}






	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/


	public static CalculateurDeClustersAlbums getInstanceunique() {
		return instanceUnique;
	}

}
