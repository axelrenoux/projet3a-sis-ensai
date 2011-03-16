package recuperationLastFM.recuperationDonnees;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import metier.Tag;

import controleur.Controleur;

import bdd.ChargementBDDdepuisOracle;
import bdd.GestionBddJavaPourSauvegarde;
import bdd.sauvegarde_controlee.ControleSauvegardeBddFormatOracle;
import bdd.sauvegarde_controlee.ControleSauvegardeUnFormatPourLaBdd;

public class Peuplement {
	
	public static void aFaireTournerTouteLaNuit(){
		List<String> recherche=new ArrayList<String>();
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
		aFaireTournerTouteLaNuit(recherche);
	}
	
	public static void aFaireTournerTouteLaNuit(List<String> motsClef){
		ControleSauvegardeUnFormatPourLaBdd formatSauvegarde=new ControleSauvegardeBddFormatOracle();
		boolean recreerLesTables=true;
		ChargementBDDdepuisOracle.charger();
		GestionBddJavaPourSauvegarde.decomposerAvantSauvegardeGereePar(formatSauvegarde, recreerLesTables);
		recreerLesTables=false;
		RecupDonnees recup = new RecupDonnees();
		for(String recherche:motsClef){
			viderControleur();
			recup.rechercher(recherche);
			//GestionBddJavaPourSauvegarde fait appel à ChargementEtControleUrlExistantes pour éviter les doublons
			GestionBddJavaPourSauvegarde.decomposerAvantSauvegardeGereePar(formatSauvegarde, recreerLesTables);
		}
	}
	

	private static void viderControleur(){
		Controleur.getInstanceuniquecontroleur().vider();
	}
}
