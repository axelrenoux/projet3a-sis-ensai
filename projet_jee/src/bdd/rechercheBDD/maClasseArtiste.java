package bdd.rechercheBDD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bdd.sqlviajdbc.ControlAccesSQLViaJDBC;
import exceptions.ChargementException;
import exceptions.QueryException;

import metier.Tag;
import metier.Wiki;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;

public class maClasseArtiste {

	public maClasseArtiste() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ArrayList<Artiste> rechercherArtistes(String motcle) throws ChargementException{
		ArrayList<Artiste> artistesrecherches = new ArrayList<Artiste>();
		ResultSet resultat;
		String recherche=
			"SELECT DISTINCT " +
			"inu.name as nameArtiste , " +
			"inu.url as url , " +
			"i.imageLarge as iL , " +
			"aud.listeners as list , " +
			"aud.playcount as playc , " +
			"w.datepublication as dateWiki , "+
			"w.resume as resumeWiki , "+
			"w.contenu as contenuWiki"+
			" FROM WIKI w , IMAGES i , AUDIMAT aud , " +
			" ID_NAME_URL inu, ARTISTE art "+
			" WHERE art.id_name_url = inu.cle_primaire" +
			" and art.images = i.cle_primaire" +
			" and art.audimat = aud.cle_primaire" +
			" and art.wiki = w.cle_primaire" +
			" and UPPER(inu.name) LIKE '%"+motcle+"%'";
		try {
			resultat = ControlAccesSQLViaJDBC.executerRequeteAvecRetour(recherche);
		} catch (QueryException e1) {
			throw new ChargementException(e1);
		}
		try {
			while(resultat.next()){
				Wiki leWiki=new Wiki(resultat.getDate("dateWiki"),
						resultat.getString("resumeWiki"),
						resultat.getString("contenuWiki"));
				Artiste artistecourant = new Artiste(
								resultat.getString("nameArtiste"),
								resultat.getString("url"),
								resultat.getString("iL"),
								resultat.getDouble("list"),
								resultat.getDouble("playc"),
								leWiki);
				artistecourant.setToptags(rechercherTagsArtiste(artistecourant));
				artistesrecherches.add(artistecourant);
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return artistesrecherches;
		
	}
	
	public ArrayList<Tag> rechercherTagsArtiste(Artiste artiste){
		return null;
	}
	
	

}
