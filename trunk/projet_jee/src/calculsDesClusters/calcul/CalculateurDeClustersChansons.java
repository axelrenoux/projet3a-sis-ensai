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

	
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	 
	public static CalculateurDeClustersChansons getInstanceunique() {
		return instanceUnique;
	}
	
}
