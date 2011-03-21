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


	
	
	/**************************    artistes    ****************************/
	

	
 	 

	
	/****************** valeurs des differents axes   ************************/
	
	public String miseEnClassesAnnees(Date date){
		String classe = "";
		String year = date.getYear()+1900+"";
		String yearArrondiString = year.substring(0,3)+"0";
		//on n'a plus que 2010,2000,1990,1980...
		int yearArrondi = Integer.parseInt(yearArrondiString);
		
		switch (yearArrondi) {
		case 2010: 
			classe = "années 2010";
			break;
		case 2000: 
			classe = "années 2000";
			break;
		case 1990: 
			classe = "années 90";
			break;
		case 1980: 
			classe = "années 80";
			break;
		case 1970: 
			classe = "années 70";
			break;
		case 1960: 
			classe = "années 60";
			break;
		default: 
			classe = "année inconnue";
			break;
		}
		
		if(yearArrondi<1960){
			classe="avant les années 60";
		}

		
		return  classe;
	}
	
	
	/**
	 * on definit "été" pour les dates du 1er avril au 30septembre
	 * on definit "hiver" pour les dates du 1er octobre au 31 mars
	 * @param d
	 * @return
	 */
	public String miseEnSaison(Date d){
		if(d.getMonth()+1<10 && d.getMonth()+1>3) return "été";
		else return "hiver";
	}
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	 
	public static CalculateurDeClusters getInstanceunique() {
		return instanceUnique;
	}
	
}
