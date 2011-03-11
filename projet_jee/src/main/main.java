package main;

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
		//ParsingAlbum parsing = new ParsingAlbum();
		//ParsingArtiste parsing = new ParsingArtiste();
		RecupDonnees recup = new RecupDonnees();		
		recup.recupererDonnees();
	}

}
