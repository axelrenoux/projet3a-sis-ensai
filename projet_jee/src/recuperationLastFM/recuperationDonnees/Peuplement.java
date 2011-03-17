package recuperationLastFM.recuperationDonnees;

import java.util.ArrayList;
import java.util.List;

import bdd.GestionBddJavaPourSauvegarde;
import bdd.sauvegarde_controlee.ControleSauvegardeBddFormatOracle;
import bdd.sauvegarde_controlee.ControleSauvegardeUnFormatPourLaBdd;
import controleur.Controleur;

public class Peuplement {
	
	public static void aFaireTournerTouteLaNuit(){
		List<String> recherche=new ArrayList<String>();
		recherche.add("rennes");
		recherche.add("sheffield");
		recherche.add("strapontin");
		recherche.add("ordinateur");
		recherche.add("believe");
		recherche.add("cher");
		recherche.add("hitmachine");
		recherche.add("toi");
		recherche.add("Christine");
		recherche.add("Tiny");
		recherche.add("Ronan");
		recherche.add("Renan");
		recherche.add("Axel");
		recherche.add("Axelle");
		recherche.add("Marie");
		recherche.add("Mary");
		recherche.add("love");
		recherche.add("black");
		recherche.add("best");
		recherche.add("free");
		recherche.add("rock");
		aFaireTournerTouteLaNuit(recherche);
	}
	
	public static void aFaireTournerTouteLaNuit(List<String> motsClef){
		for(String recherche:motsClef){
			viderControleur();
			RecupDonnees recup = new RecupDonnees();		recup.rechercher(recherche);
			boolean recreerLesTables=false;
			ControleSauvegardeUnFormatPourLaBdd formatSauvegarde=new ControleSauvegardeBddFormatOracle();
			GestionBddJavaPourSauvegarde.decomposerAvantSauvegardeGereePar(formatSauvegarde, recreerLesTables);
		}
	}
	

	private static void viderControleur(){
		Controleur.getInstanceuniquecontroleur().vider();
	}
}
