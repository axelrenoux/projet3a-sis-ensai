package rechercheParFormulaire.gestionRecherche;

import java.util.ArrayList;

import metier.Cluster;
import metier.oeuvres.Album;
import calculsDesClusters.axe.Axe;
import calculsDesClusters.axe.AxeAnnee;
import calculsDesClusters.axe.AxeArtiste;
import calculsDesClusters.axe.AxeSaison;
import calculsDesClusters.calcul.CalculateurDeClustersAlbums;
import controleur.UtilitaireDate;
import exceptions.ExceptionDate;

public class RechercheAlbum {
	
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
	
	
	//
	public Cluster lancerRecherche(String motCle) {
		
		//traitement provisoire debut 
		resultats = new ArrayList<Album>();
		Album a1 = new Album();
		Album a2 = new Album();
		Album a3 = new Album();
		Album a4 = new Album();
		Album a5 = new Album();
		Album a6 = new Album();
		Album a7 = new Album();
		Album a8 = new Album();
		Album a9 = new Album();
		Album a10 = new Album();
		Album a11 = new Album();
		
		a1.setName("Sticky Fingers");
		a1.setImageLarge("http://userserve-ak.last.fm/serve/126/5085382x5.png");
		a2.setName("The score");
		a2.setImageLarge("http://userserve-ak.last.fm/serve/126/3257193z3.jpg");
		a3.setName("Nevermind");
		a3.setImageLarge("http://userserve-ak.last.fm/serve/126/463109h4c9.png");
		a4.setName("The score2");
		a4.setImageLarge("http://userserve-ak.last.fm/serve/126/325h71933.jpg");
		a5.setName("Sticky Fingers2");
		a5.setImageLarge("http://userserve-ak.last.fm/serve/126/50h853825.png");
		a6.setName("The score3");
		a6.setImageLarge("http://userserve-ak.last.fm/serve/126/325719g33.jpg");
		a7.setName("Sticky Fingers4");
		a7.setImageLarge("http://userserve-ak.last.fm/serve/126/50g853825.png");
		a8.setName("The score");
		a8.setImageLarge("http://userserve-ak.last.fm/serve/126/u32571933.jpg");
		a9.setName("Sticky Fingers");
		a9.setImageLarge("http://userserve-ak.last.fm/serve/126/508538g25.png");
		a10.setName("The score");
		a10.setImageLarge("http://userserve-ak.last.fm/serve/12u6/32571933.jpg");
		a11.setName("Test");
		a11.setImageLarge("http://userserve-ak.last.fm/serve/126/3257193ml3.jpg");
		
		try {
			a1.setDate(UtilitaireDate.getInstanceunique().
					transformerEnDateUneDateBDD("16/03/2011"));
			a2.setDate(UtilitaireDate.getInstanceunique().
					transformerEnDateUneDateBDD("16/08/2011"));
			a3.setDate(UtilitaireDate.getInstanceunique().
					transformerEnDateUneDateBDD("16/10/2005"));
			a4.setDate(UtilitaireDate.getInstanceunique().
					transformerEnDateUneDateBDD("16/04/2005"));
			a5.setDate(UtilitaireDate.getInstanceunique().
					transformerEnDateUneDateBDD("16/09/1999"));
			a6.setDate(UtilitaireDate.getInstanceunique().
					transformerEnDateUneDateBDD("16/03/1999"));
			a7.setDate(UtilitaireDate.getInstanceunique().
					transformerEnDateUneDateBDD("16/05/1984"));
			a8.setDate(UtilitaireDate.getInstanceunique().
					transformerEnDateUneDateBDD("16/06/1984"));
			a9.setDate(UtilitaireDate.getInstanceunique().
					transformerEnDateUneDateBDD("16/03/1970"));
			a10.setDate(UtilitaireDate.getInstanceunique().
					transformerEnDateUneDateBDD("16/03/1955"));
			a11.setDate(UtilitaireDate.getInstanceunique().
					transformerEnDateUneDateBDD("15/03/1999"));
			
		} catch (ExceptionDate e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		resultats.add(a1);
		resultats.add(a2);
		resultats.add(a3);
		resultats.add(a4);
		resultats.add(a5);
		resultats.add(a6);
		resultats.add(a7);
		resultats.add(a8);
		resultats.add(a9);
		resultats.add(a10);
		resultats.add(a11);
		
		
		//traitement provisoire debut
		
		
		//il faudra ici aller cherche en base les albums repondant au mot cle
		Axe axe1Date = new AxeAnnee();
		Axe axe2Saison = new AxeArtiste();
		
		return CalculateurDeClustersAlbums.getInstanceunique().
		calculerClustersAlbum(axe1Date,axe2Saison,resultats);
		
	}
	
	
	
	public String retournerTypeAffichage(){
		return "album";
	}
}
