package rechercheParFormulaire.gestionRecherche;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import bdd.rechercheBDD.maClasseAlbum;
import bdd.rechercheBDD.maClasseChanson;

import calculsDesClusters.axe.CoupleAxe;
import calculsDesClusters.calcul.CalculateurDeClusters;
import calculsDesClusters.calcul.CalculateurDeClustersArtistes;
import calculsDesClusters.calcul.CalculateurDeClustersChansons;
import exceptions.ChargementException;


import metier.Cluster;
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

	//en attendant on met un mock
	public ArrayList lancerRecherche() {
		resultats = new ArrayList<Chanson>();
		Chanson c1 = new Chanson();
		Chanson c2 = new Chanson();
		Chanson c3 = new Chanson();
		Chanson c4 = new Chanson();
		
		
		c1.setName("Paint it black");
		c1.setImageLarge("http://userserve-ak.last.fm/serve/126/8747357.jpg");
		c1.setUrl("1");
		c2.setName("Ready or Not");
		c2.setImageLarge("http://userserve-ak.last.fm/serve/126/32571933.jpg");
		c2.setUrl("2");
		c3.setName("Paint it black333");
		c3.setImageLarge("http://userserve-ak.last.fm/serve/126/8747357.jpg");
		c3.setUrl("3");
		c4.setName("Ready or Not4444");
		c4.setImageLarge("http://userserve-ak.last.fm/serve/126/32571933.jpg");
		c4.setUrl("4");
		resultats.add(c1);
		resultats.add(c2);
		resultats.add(c3);
		resultats.add(c4);
		return resultats;
		
	}
	
	public Cluster lancerRecherche(String motCle) {
		resultats = new ArrayList<Chanson>();
		Chanson c1 = new Chanson();
		Chanson c2 = new Chanson();
		Chanson c3 = new Chanson();
		Chanson c4 = new Chanson();
		
		c1.setName("Paint it black");
		c1.setImageLarge("http://userserve-ak.last.fm/serve/126/8747357.jpg");
		c1.setUrl("1");
		c1.setDuree(5.0);
		c1.setListeners(10);
		c2.setName("Ready or Not");
		c2.setImageLarge("http://userserve-ak.last.fm/serve/126/32571933.jpg");
		c2.setUrl("2");
		c2.setDuree(5.0);
		c2.setListeners(10);
		c3.setName("Paint it black333");
		c3.setImageLarge("http://userserve-ak.last.fm/serve/126/8747357.jpg");
		c3.setUrl("3");
		c3.setDuree(5.0);
		c3.setListeners(12);
		c4.setName("Ready or Not4444");
		c4.setImageLarge("http://userserve-ak.last.fm/serve/126/32571933.jpg");
		c4.setUrl("4");
		c4.setDuree(15.0);
		c4.setListeners(10);
		resultats.add(c1);
		resultats.add(c2);
		resultats.add(c3);
		resultats.add(c4);
		

		/*maClasseChanson ma = new maClasseChanson();
		try {
			resultats = ma.rechercherChansons(motCle);
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		HashMap<CoupleAxe,Cluster> listeClusterPossible = CalculateurDeClustersChansons.getInstanceunique().calculEnsembleClustersChansons(resultats);
		Cluster meilleurCluster = new Cluster();
		
		for(Entry<CoupleAxe, Cluster> entry : listeClusterPossible.entrySet()) {
			if (entry.getKey().getVariance() < meilleurCluster.varianceCluster()){
				meilleurCluster = entry.getValue();
			}
		}
			
		return meilleurCluster;
		
	}
	
	public String retournerTypeAffichage(){
		return "chanson";
	}
}
