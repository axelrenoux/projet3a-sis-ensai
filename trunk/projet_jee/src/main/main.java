package main;

import parsing.sax.ParsingChanson;
import parsing.sax.ParsingArtiste;
import parsing.sax.ParsingChanson;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ParsingAlbum parsing = new ParsingAlbum();
		//ParsingArtiste parsing = new ParsingArtiste();
		ParsingChanson parsing = new ParsingChanson();
		parsing.parser();
	}

}
