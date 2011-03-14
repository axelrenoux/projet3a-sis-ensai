package main;

import bdd.ChargementBDDOracleDepuisTxt;
import bdd.ChargementBDDdepuisOracle;
import bdd.GestionBddJavaPourSauvegarde;
import bdd.SauvegardeBddFormatOracle;
import controleur.Controleur;
import exceptions.ExceptionMiseAjour;
import metier.Album;
import metier.Artiste;
import metier.Chanson;
import metier.Tag;
import parsing.sax.ParsingAlbum;
import parsing.sax.ParsingChanson;
import parsing.sax.ParsingArtiste;
import parsing.sax.ParsingChanson;
import recuperationDonnees.RecupDonnees;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/*Chanson a = new Chanson();
		a.setName("a");
		//a.setUrl("aaa");
		Chanson b = new Chanson();
		b.setUrl("aa");
		b.setName("a");
		try {
			a.mettreAjour(b);
		} catch (ExceptionMiseAjour e) {}
		
		System.out.println(" a : "+ a);
		System.out.println(" b : "+ b);
		
		Album ab = new Album();
		
		Chanson c  = new Chanson();
		c.setUrl("http://www.last.fm/music/Sophie+Meriem+Rockwell/_/Mon+ami+l%27ordinateur");
		c.setImageMega("ca marche 2");
		
		Artiste a = new Artiste();
		a.setName("Slipknot");
		a.setImageMega("ca marche aaaaaa");
		Controleur.getInstanceuniquecontroleur().ajouter(c);
		Controleur.getInstanceuniquecontroleur().ajouter(a);
		Tag t = new Tag();
		t.setName("iiiiiiiiiiiiiiii");
		t.setUrl("http://www.last.fm/tag/post-industrial");
		Controleur.getInstanceuniquecontroleur().ajouter(t);*/
		
		
		RecupDonnees recup = new RecupDonnees();		
		//recup.recupererDonneesChansons();
		recup.recupererDonneesArtistes();
		//recup.recupererInfosComplementairesPlus();
		//recup.recupererDonneesAlbums();
		//recup.recupererDonneesComplementairesTags();
		//recup.recupererListesDesTags();
		recup.affichageDonneesRecuperees();
		
		//attention à l'ordre TODO
		
		
		/*	POUR TESTER LA GESTION BDD ORACLE
		 */ 
		/*System.out.println("Sauvegarder nos classes java sous Oracle en mettant à jour le fichier txt");
		GestionBddJavaPourSauvegarde.decomposerAvantSauvegardeGereePar(new SauvegardeBddFormatOracle());
		System.out.println("Charger sous Java nos données enregistrées sous Oracle");
		ChargementBDDdepuisOracle.charger();
		System.out.println("Charger sous Oracle à partir du fichier txt, puis charger sous Java les données ainsi enregistrées sous Oracle");
		ChargementBDDOracleDepuisTxt.charger();
		ChargementBDDdepuisOracle.charger();*/
		
	}

}
