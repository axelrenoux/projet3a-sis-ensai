package main;

import java.util.ArrayList;

import recuperationDonnees.RecupDonnees;
import bdd.ChargementBDDOracleDepuisTxt;
import bdd.ChargementBDDdepuisOracle;
import bdd.ControleSauvegardeBddFormatOracle;
import bdd.GestionBddJavaPourSauvegarde;
import controleur.Controleur;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		/*	POUR TESTER LA GESTION BDD ORACLE
		 */ 

		//boolean recreerLesTables=true;
		//ChargementBDDdepuisOracle.charger();
		//GestionBddJavaPourSauvegarde.decomposerAvantSauvegardeGereePar(new ControleSauvegardeBddFormatOracle(),recreerLesTables);
		//ChargementBDDOracleDepuisTxt.charger();
		//GestionBddJavaPourSauvegarde.decomposerAvantSauvegardeGereePar(new ControleSauvegardeBddFormatOracle(),recreerLesTables);
		/*RecupDonnees recup = new RecupDonnees();
		ArrayList<String> recherche=new ArrayList<String>();
		recherche.add("Marie");
		recherche.add("Mary");
		recherche.add("Christine");
		recherche.add("Tiny");
		recherche.add("Mary");
		recherche.add("Ronan");
		recherche.add("Renan");
		recherche.add("Axel");
		recherche.add("Axelle");
		recherche.add("love");
		recherche.add("black");
		recherche.add("best");
		recherche.add("hitmachine");
		recherche.add("free");
		recherche.add("toi");
		recherche.add("rock");
		recup.rechercher(recherche);
		boolean recreerLesTables=false;
		GestionBddJavaPourSauvegarde.decomposerAvantSauvegardeGereePar(new ControleSauvegardeBddFormatOracle(),recreerLesTables);
		//ChargementBDDOracleDepuisTxt.charger();
		 
		
		/*System.out.println(Controleur.getInstanceuniquecontroleur().
				getListeAlbums().get("http://www.last.fm/music/Lunabee/Prenez+garde+aux+flots+bleus"));*/
		
	
	}


}
