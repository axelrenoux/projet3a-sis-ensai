package rechercheParFormulaire.gestionRecherche;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import bdd.rechercheBDD.RechercheAlbumBDD;
import bdd.rechercheBDD.RechercheArtisteBDD;

import calculsDesClusters.axe.AxeListener;
import calculsDesClusters.axe.AxePlaycount;
import calculsDesClusters.axe.CoupleAxe;
import rechercheParFormulaire.CalculDesClusters.CalculateurDeClusters;
import calculsDesClusters.calcul.CalculateurDeClustersAlbums;
import calculsDesClusters.calcul.CalculateurDeClustersArtistes;
import exceptions.ChargementException;


import metier.Cluster;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;

public class RechercheArtiste{
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	private ArrayList<Artiste> resultats;
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/

	
	
	public Cluster lancerRecherche(String motCle) {
		/*resultats = new ArrayList<Artiste>();
		Artiste a1 = new Artiste();
		Artiste a2 = new Artiste();
		Artiste a3 = new Artiste();
		Artiste a4 = new Artiste();
		
		a1.setName("The Rolling Stones");
		a1.setImageLarge("http://userserve-ak.last.fm/serve/126/207811.jpg");
		a1.setUrl("1");
		a1.setListeners(10);
		a1.setPlaycount(20);
		a2.setName("The fugees");
		a2.setImageLarge("http://userserve-ak.last.fm/serve/126/32571933.jpg");	
		a2.setUrl("2");
		a2.setListeners(11);
		a2.setPlaycount(22);
		a3.setName("The fugeesqqqqq");
		a3.setImageLarge("http://userserve-ak.last.fm/serve/126/32571933.jpg");	
		a3.setUrl("3");
		a3.setListeners(10);
		a3.setPlaycount(20);
		a4.setName("The fugeesdddff");
		a4.setImageLarge("http://userserve-ak.last.fm/serve/126/32571933.jpg");	
		a4.setUrl("4");
		a4.setListeners(10);
		a4.setPlaycount(23);
		
		
		resultats.add(a1);
		resultats.add(a2);
		resultats.add(a3);
		resultats.add(a4);*/
		
		/*maClasseArtiste ma = new maClasseArtiste();
		try {
			resultats = ma.rechercherArtistes(motCle);
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		ArrayList<Artiste> ar=null;
		RechercheArtisteBDD mar = RechercheArtisteBDD.getInstance();
		try {
			ar = mar.rechercherArtistes("ar");
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(ar);
		//System.out.println(ar.size());
		

<<<<<<< .mine
		HashMap<CoupleAxe,Cluster> listeClusterPossible = CalculateurDeClustersArtistes.getInstanceunique().calculEnsembleClustersArtistes(ar);
=======
		/*HashMap<CoupleAxe,Cluster> listeClusterPossible = CalculateurDeClustersArtistes.getInstanceunique().calculEnsembleClustersArtistes(resultats);
>>>>>>> .r312
		Cluster meilleurCluster = new Cluster();
		
		for(Entry<CoupleAxe, Cluster> entry : listeClusterPossible.entrySet()) {
			meilleurCluster = entry.getValue();
			break;
		}
		for(Entry<CoupleAxe, Cluster> entry2 : listeClusterPossible.entrySet()) {
			
<<<<<<< .mine
			if (entry2.getKey().getVariance() < meilleurCluster.varianceCluster()){
				meilleurCluster = entry2.getValue();
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				System.out.println(entry2.getKey().getVariance());
			}
		}
		//System.out.println(meilleurCluster);
		
		return meilleurCluster;

=======
		return meilleurCluster;*/
		
		return CalculateurDeClusters.getInstanceunique().calculerClustersArtiste(resultats);
>>>>>>> .r312

		
	}
	
	public String retournerTypeAffichage(){
		return "artiste";
	}

}
