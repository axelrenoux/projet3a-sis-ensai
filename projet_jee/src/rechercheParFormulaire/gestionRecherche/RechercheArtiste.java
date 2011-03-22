package rechercheParFormulaire.gestionRecherche;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import bdd.rechercheBDD.maClasseAlbum;
import bdd.rechercheBDD.maClasseArtiste;

import calculsDesClusters.axe.CoupleAxe;
import calculsDesClusters.calcul.CalculateurDeClusters;
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
		resultats = new ArrayList<Artiste>();
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
		resultats.add(a4);
		
		/*maClasseArtiste ma = new maClasseArtiste();
		try {
			resultats = ma.rechercherArtistes(motCle);
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		

		HashMap<CoupleAxe,Cluster> listeClusterPossible = CalculateurDeClustersArtistes.getInstanceunique().calculEnsembleClustersArtistes(resultats);
		Cluster meilleurCluster = new Cluster();
		
		for(Entry<CoupleAxe, Cluster> entry : listeClusterPossible.entrySet()) {
			if (entry.getKey().getVariance() < meilleurCluster.varianceCluster()){
				meilleurCluster = entry.getValue();
			}
		}
			
		return meilleurCluster;


	}
	
	public String retournerTypeAffichage(){
		return "artiste";
	}

}
