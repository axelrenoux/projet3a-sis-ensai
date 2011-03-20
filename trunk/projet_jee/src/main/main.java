package main;

import java.util.ArrayList;

import exceptions.ChargementException;

import bdd.chargement.ChargementBDDOracleDepuisTxt;
import bdd.rechercheBDD.RechercheAlbumBDD;
import bdd.rechercheBDD.RechercheArtisteBDD;
import bdd.rechercheBDD.maClasseAlbum;
import bdd.rechercheBDD.maClasseArtiste;
import bdd.rechercheBDD.maClasseChanson;
import metier.Cluster;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;
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
		
		//RechercheArtisteBDD reach = new RechercheArtisteBDD("cher");
		//System.out.println(reach.getAlbums());
		
		
		ArrayList<Chanson> c=null;
		maClasseChanson mc = new maClasseChanson();
		try {
			c = mc.rechercherChansons("LIVE");
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(c);
	}


}
