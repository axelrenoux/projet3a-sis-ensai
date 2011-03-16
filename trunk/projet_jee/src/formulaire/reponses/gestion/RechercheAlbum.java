package formulaire.reponses.gestion;

import java.util.ArrayList;

import metier.Album;
import metier.Artiste;

public class RechercheAlbum extends Recherche {
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	private ArrayList<Album> resultats;
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/

	//en attendant on met un mock
	public ArrayList lancerRecherche() {
		resultats = new ArrayList<Album>();
		Album a1 = new Album();
		Album a2 = new Album();
		
		a1.setName("Sticky Fingers");
		a1.setImageLarge("http://userserve-ak.last.fm/serve/126/50853825.png");
		a2.setName("The score");
		a2.setImageLarge("http://userserve-ak.last.fm/serve/126/32571933.jpg");
		
		resultats.add(a1);
		resultats.add(a2);
		
		return resultats;
		
	}
	
	
	public String retournerTypeAffichage(){
		return "album";
	}
}
