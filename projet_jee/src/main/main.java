package main;

import bdd.chargement.ChargementBDDOracleDepuisTxt;
import bdd.rechercheBDD.RechercheAlbumBDD;
import metier.Cluster;
import rechercheParFormulaire.gestionRecherche.RechercheAlbum;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*System.out.println(Controleur.getInstanceuniquecontroleur().
				getListeAlbums().get("http://www.last.fm/music/Lunabee/Prenez+garde+aux+flots+bleus"));*/
		
		//test pour la creation des axes
		
		//RechercheAlbum r = new RechercheAlbum();
		//Cluster c = r.lancerRecherche("a");
		System.out.println("***************************");
		//System.out.println(c);
		//System.out.println(c.varianceCluster());
		//ChargementBDDOracleDepuisTxt.charger();
		
		RechercheAlbumBDD reach = new RechercheAlbumBDD("ordinateur");
		System.out.println(reach.getAlbums());
		
	}


}
