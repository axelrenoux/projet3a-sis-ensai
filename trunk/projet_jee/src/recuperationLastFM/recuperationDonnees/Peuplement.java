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
		/*recherche.add("rennes");
		recherche.add("sheffield");
		recherche.add("strapontin");
		recherche.add("ordinateur");*/
		recherche.add("serviette");
		recherche.add("bonhomme");
		recherche.add("mousse");
		recherche.add("hotel");
		recherche.add("trust");
		recherche.add("mozart");
		recherche.add("carnaval");
		recherche.add("dance");
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
		//On initialise les tables
		viderControleur();
		RecupDonnees recup = new RecupDonnees();
		boolean recreerLesTables=true;
		ControleSauvegardeUnFormatPourLaBdd formatSauvegarde=new ControleSauvegardeBddFormatOracle();
		//GestionBddJavaPourSauvegarde.decomposerAvantSauvegardeGereePar(formatSauvegarde, recreerLesTables);
		
		//On effectue successivement toutes les recherches demandées
		for(String recherche:motsClef){
			viderControleur();
			recup = new RecupDonnees();
			recup.rechercher(recherche);
			recreerLesTables=false;
			formatSauvegarde=new ControleSauvegardeBddFormatOracle();
			GestionBddJavaPourSauvegarde.decomposerAvantSauvegardeGereePar(formatSauvegarde, recreerLesTables);
		}
	}
	

	private static void viderControleur(){
		Controleur.getInstanceuniquecontroleur().vider();
	}
}
