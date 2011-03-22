package calculsDesClusters.calcul;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import controleur.Controleur;
import metier.Cluster;
import metier.ComposantCluster;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;
import metier.oeuvres.Oeuvre;



public class CalculateurDeClusters {
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private static final CalculateurDeClusters instanceUnique = new CalculateurDeClusters();

	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	private CalculateurDeClusters(){
	}



	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/



	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	 
	public static CalculateurDeClusters getInstanceunique() {
		return instanceUnique;
	}
	
}
