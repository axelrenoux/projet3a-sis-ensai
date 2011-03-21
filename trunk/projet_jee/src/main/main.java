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
		
		
		ArrayList<Album> c=null;
		maClasseAlbum mc = maClasseAlbum.getInstance();
		try {
			c = mc.rechercherAlbums("LIVE");
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(c);
		System.out.println(c.size());
		
		ArrayList<Artiste> ar=null;
		maClasseArtiste mar = maClasseArtiste.getInstance();
		try {
			ar = mar.rechercherArtistes("re");
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ar);
		
		
		ArrayList<Chanson> ch=null;
		maClasseChanson mcc = maClasseChanson.getInstance();
		try {
			ch = mcc.rechercherChansons("re");
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ch);
		
		/*for (Album ch:c){
			if (ch.getDate()!=null){
				System.out.println(ch.getDate());
				System.out.println(ch.getDate().getDate() + " " +
						ch.getDate().getMonth()+ " " + 
						(ch.getDate().getYear()+1900));
			}
		}*/
		
	}


}
