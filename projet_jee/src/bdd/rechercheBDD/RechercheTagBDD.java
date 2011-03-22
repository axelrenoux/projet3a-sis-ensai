package bdd.rechercheBDD;

import java.util.ArrayList;

import metier.Tag;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;

public class RechercheTagBDD {

	
	private final static RechercheTagBDD instance = new RechercheTagBDD();
	
	private RechercheTagBDD() {
	}
	
	
	
	public ArrayList<Tag> rechercherTagsChanson(Chanson chanson){
		return null;
	}
	
	//TODO
	public ArrayList<Tag> rechercherTagsAlbum(Album album){
		ArrayList<Tag> tags = new ArrayList<Tag>();
		return null;
	}
	
	public ArrayList<Tag> rechercherTagsArtiste(Artiste artiste){
		return null;
	}
	
	public static RechercheTagBDD getInstance() {
		return instance;
	}
	
	
	
}
