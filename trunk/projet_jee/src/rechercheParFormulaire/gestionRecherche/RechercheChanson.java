package rechercheParFormulaire.gestionRecherche;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import rechercheParFormulaire.CalculDesClusters.CalculateurDeClusters;


import bdd.rechercheBDD.RechercheChansonBDD;


import calculsDesClusters.axe.CoupleAxe;
import calculsDesClusters.calcul.CalculateurDeClustersChansons;
import exceptions.ChargementException;


import metier.Cluster;
import metier.Wiki;

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
		c1.setWiki(new Wiki(new Date(11111111),"aaa","bbbbb"));
		c2.setName("Ready or Not");
		c2.setImageLarge("http://userserve-ak.last.fm/serve/126/32571933.jpg");
		c2.setUrl("2");
		c2.setDuree(5.0);
		c2.setListeners(10);
		c2.setWiki(new Wiki(new Date(11111111),"aaa","bbbbb"));
		c3.setName("Paint it black333");
		c3.setImageLarge("http://userserve-ak.last.fm/serve/126/8747357.jpg");
		c3.setUrl("3");
		c3.setDuree(5.0);
		c3.setListeners(12);
		c3.setWiki(new Wiki(new Date(11111111),"aaa","bbbbb"));
		c4.setName("Ready or Not4444");
		c4.setImageLarge("http://userserve-ak.last.fm/serve/126/32571933.jpg");
		c4.setUrl("4");
		c4.setDuree(15.0);
		c4.setListeners(10);
		c4.setWiki(new Wiki(new Date(11111111),"aaa","bbbbb"));
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
		
		
		/*ArrayList<Chanson> ar=null;
		RechercheChansonBDD mar = RechercheChansonBDD.getInstance();
		try {
			ar = mar.rechercherChansons(motCle);
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap<CoupleAxe,Cluster> listeClusterPossible = CalculateurDeClustersChansons.getInstanceunique().calculEnsembleClustersChansons(ar);


		Cluster meilleurCluster = new Cluster();
		
		for(Entry<CoupleAxe, Cluster> entry : listeClusterPossible.entrySet()) {
			meilleurCluster = entry.getValue();
			meilleurCluster.setNomCluster(entry.getKey().getAxe1().getType() + ";" + entry.getKey().getAxe2().getType());
			break;
		}
		for(Entry<CoupleAxe, Cluster> entry2 : listeClusterPossible.entrySet()) {

			if (entry2.getKey().getVariance() < meilleurCluster.varianceCluster()){
				meilleurCluster = entry2.getValue();
				meilleurCluster.setNomCluster(entry2.getKey().getAxe1().getType() + ";" + entry2.getKey().getAxe2().getType());
			}
		}
		System.out.println("meilleur cluster : " + meilleurCluster.varianceCluster());
		System.out.println(meilleurCluster.tailleCluster());
		return meilleurCluster;
		*/
		
		return CalculateurDeClusters.getInstanceunique().calculerClustersChanson(resultats);
	}
	
	public String retournerTypeAffichage(){
		return "chanson";
	}
}
