package formulaire.reponses.gestion;

import java.util.ArrayList;

import metier.Album;
import metier.Artiste;

public class RechercheArtiste extends Recherche {
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	private ArrayList<Artiste> resultats;
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/

	
	//en attendant on met un mock
	public ArrayList lancerRecherche() {
		resultats = new ArrayList<Artiste>();
		Artiste a1 = new Artiste();
		Artiste a2 = new Artiste();
		
		a1.setName("The Rolling Stones");
		a2.setName("The fugees");
		
		resultats.add(a1);
		resultats.add(a2);
		
		return resultats;
	}
	
	public String retournerTypeAffichage(){
		return "artiste";
	}

}
