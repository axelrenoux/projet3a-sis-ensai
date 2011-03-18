package rechercheParFormulaire.gestionRecherche;

import java.util.ArrayList;

import calculsDesClusters.calcul.CalculateurDeClusters;


import metier.Cluster;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;

public class RechercheArtiste{
	
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
		a1.setImageLarge("http://userserve-ak.last.fm/serve/126/207811.jpg");
		a2.setName("The fugees");
		a2.setImageLarge("http://userserve-ak.last.fm/serve/126/32571933.jpg");
		
		resultats.add(a1);
		resultats.add(a2);
		
		return resultats;
	}
	
	
	public Cluster lancerRecherche(String motCle) {
		resultats = new ArrayList<Artiste>();
		Artiste a1 = new Artiste();
		Artiste a2 = new Artiste();
		
		a1.setName("The Rolling Stones");
		a1.setImageLarge("http://userserve-ak.last.fm/serve/126/207811.jpg");
		a2.setName("The fugees");
		a2.setImageLarge("http://userserve-ak.last.fm/serve/126/32571933.jpg");
		
		resultats.add(a1);
		resultats.add(a2);
		
		return CalculateurDeClusters.getInstanceunique().
		calculerClustersArtiste(resultats);
	}
	
	public String retournerTypeAffichage(){
		return "artiste";
	}

}
