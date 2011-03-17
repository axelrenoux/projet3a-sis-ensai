package main;

import java.util.ArrayList;

import metier.Cluster;

import rechercheParFormulaire.CalculDesClusters.CalculateurDeClusters;
import rechercheParFormulaire.gestionRecherche.RechercheAlbum;
import recuperationLastFM.recuperationDonnees.RecupDonnees;
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
		
		/*System.out.println(Controleur.getInstanceuniquecontroleur().
				getListeAlbums().get("http://www.last.fm/music/Lunabee/Prenez+garde+aux+flots+bleus"));*/
		
		//test pour la creation des axes
		
		RechercheAlbum r = new RechercheAlbum();
		Cluster c = r.lancerRecherche("a");
		System.out.println("***************************");
		System.out.println(c);
		
	}


}
