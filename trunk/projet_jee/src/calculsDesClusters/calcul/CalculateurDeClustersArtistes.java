package calculsDesClusters.calcul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import metier.Cluster;
import metier.ComposantCluster;
import metier.oeuvres.Artiste;



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

	

	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/


	public static CalculateurDeClustersArtistes getInstanceunique() {
		return instanceUnique;
	}

}
