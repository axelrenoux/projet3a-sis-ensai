package recuperationLastFM.recuperationDonnees;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.conn.tsccm.WaitingThread;

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
		recherche.add("serviette");
		recherche.add("bonhomme");
		recherche.add("mousse");
		recherche.add("ordinateur");*/
		recherche.add("south+park");
		recherche.add("taupe");
		recherche.add("zelda");
		recherche.add("donjon");
		recherche.add("");
		recherche.add("mousse");
		recherche.add("ordinateur");
		recherche.add("rennes");
		recherche.add("sheffield");
		recherche.add("strapontin");
		recherche.add("serviette");
		recherche.add("bonhomme");
		recherche.add("mousse");
		recherche.add("ordinateur");
		aFaireTournerTouteLaNuit(recherche);
	}
	
	public static void aFaireTournerTouteLaNuit(List<String> motsClef){
		//On initialise les tables
		//viderControleur();
		RecupDonnees recup = new RecupDonnees();
		boolean recreerLesTables=true;
		ControleSauvegardeUnFormatPourLaBdd formatSauvegarde=new ControleSauvegardeBddFormatOracle();
		//GestionBddJavaPourSauvegarde.decomposerAvantSauvegardeGereePar(formatSauvegarde, recreerLesTables);
		
		//On effectue successivement toutes les recherches demandées
		for(String recherche:motsClef){
			//viderControleur();
			recup = new RecupDonnees();
			recup.rechercher(recherche);
			//recreerLesTables=false;
			formatSauvegarde=new ControleSauvegardeBddFormatOracle();
			GestionBddJavaPourSauvegarde.decomposerAvantSauvegardeGereePar(formatSauvegarde, recreerLesTables);
			//On fait une pose de 20 minutes entre chaque mot-clé recherché
			try {
				Thread.sleep(2*60000);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	

	private static void viderControleur(){
		Controleur.getInstanceuniquecontroleur().vider();
	}
}
