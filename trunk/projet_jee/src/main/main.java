package main;

import controleur.Controleur;
import exceptions.ExceptionMiseAjour;
import metier.Album;
import metier.Artiste;
import metier.Chanson;
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
		System.out.println(" b : "+ b);*/
		
		/*Artiste a  = new Artiste();
		a.setName("Dorothée1");
		a.setImageMega("ca marche");
		
		Controleur.getInstanceuniquecontroleur().ajouterArtiste(a);
		
		System.out.println(Controleur.getInstanceuniquecontroleur().getListeArtistes());
		*/
		
		RecupDonnees recup = new RecupDonnees();		
		recup.recupererDonnees();
	

	}

}
