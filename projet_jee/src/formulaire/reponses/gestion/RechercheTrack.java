package formulaire.reponses.gestion;

import java.util.ArrayList;

import metier.Artiste;
import metier.Chanson;

public class RechercheTrack extends Recherche {
	
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
		c2.setName("Ready or Not");
		
		resultats.add(c1);
		resultats.add(c2);
		
		return resultats;
		
	}
}
