package rechercheParFormulaire.gestionRecherche;

import java.util.ArrayList;

import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;

public class RechercheChanson extends Recherche {
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	private ArrayList<Chanson> resultats;
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/

	//en attendant on met un mock
	public ArrayList lancerRecherche() {
		resultats = new ArrayList<Chanson>();
		Chanson c1 = new Chanson();
		Chanson c2 = new Chanson();
		
		c1.setName("Paint it black");
		c1.setImageLarge("http://userserve-ak.last.fm/serve/126/8747357.jpg");
		c2.setName("Ready or Not");
		c2.setImageLarge("http://userserve-ak.last.fm/serve/126/32571933.jpg");
		
		resultats.add(c1);
		resultats.add(c2);
		
		return resultats;
		
	}
	
	public ArrayList lancerRecherche(String motCle) {
		resultats = new ArrayList<Chanson>();
		Chanson c1 = new Chanson();
		Chanson c2 = new Chanson();
		
		c1.setName("Paint it black");
		c1.setImageLarge("http://userserve-ak.last.fm/serve/126/8747357.jpg");
		c2.setName("Ready or Not");
		c2.setImageLarge("http://userserve-ak.last.fm/serve/126/32571933.jpg");
		
		resultats.add(c1);
		resultats.add(c2);
		
		return resultats;
		
	}
	
	public String retournerTypeAffichage(){
		return "chanson";
	}
}
