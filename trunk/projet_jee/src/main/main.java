package main;

import java.sql.Date;

import bdd.ChargementBDDOracleDepuisTxt;
import bdd.ChargementBDDdepuisOracle;
import bdd.ControleSauvegardeBddFormatOracle;
import bdd.ControleSauvegardeUnFormatPourLaBdd;
import bdd.GestionBddJavaPourSauvegarde;
import bdd.SauvegardeBddFormatOracle;
import controleur.Controleur;
import controleur.UtilitaireDate;
import exceptions.ExceptionDate;
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
		
	
		
		RecupDonnees recup = new RecupDonnees();		
		recup.recupererDonneesChansons();
		recup.recupererDonneesArtistes();
		recup.recupererInfosComplementairesPlus();
		recup.recupererDonneesAlbums();
		recup.recupererDonneesComplementairesTags();
		recup.recupererListesDesTags();
		recup.affichageDonneesRecuperees();
		
		//attention à l'ordre TODO
		
		
		/*	POUR TESTER LA GESTION BDD ORACLE
		 */ 
		
		//System.out.println("Sauvegarder nos classes java sous Oracle en mettant à jour le fichier txt");
		GestionBddJavaPourSauvegarde.decomposerAvantSauvegardeGereePar(new ControleSauvegardeBddFormatOracle());
		//System.out.println("Charger sous Java nos données enregistrées sous Oracle");
		//ChargementBDDdepuisOracle.charger();
		//System.out.println("Charger sous Oracle à partir du fichier txt, puis charger sous Java les données ainsi enregistrées sous Oracle");
		//ChargementBDDOracleDepuisTxt.charger();
		//ChargementBDDdepuisOracle.charger();
		 
		
		System.out.println(Controleur.getInstanceuniquecontroleur().
				getListeAlbums().get("http://www.last.fm/music/Lunabee/Prenez+garde+aux+flots+bleus"));
		
	
	}


}
