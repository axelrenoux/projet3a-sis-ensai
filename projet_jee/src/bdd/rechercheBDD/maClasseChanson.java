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

public class maClasseChanson {
	
	
	
	public ArrayList<Chanson> rechercherChansons(String motcle) throws ChargementException{
		ArrayList<Chanson> chansonsrecherchees = new ArrayList<Chanson>();
		ResultSet resultat;
		String recherche=
			"SELECT DISTINCT " +
			"ch.duree as duree, "+
			"inu.name as nameChanson , " +
			"inu.url as url , " +
			"inu2.name as nameArtiste , " +
			"i.imageLarge as iL , " +
			"aud.listeners as list , " +
			"aud.playcount as playc , " +
			"w.datepublication as dateWiki , "+
			"w.resume as resumeWiki , "+
			"w.contenu as contenuWiki"+
			" FROM CHANSON ch , WIKI w , IMAGES i , AUDIMAT aud , " +
			" ID_NAME_URL inu, ID_NAME_URL inu2, ARTISTE art "+
			" WHERE ch.id_name_url = inu.cle_primaire" +
			" and ch.images = i.cle_primaire" +
			" and ch.audimat = aud.cle_primaire" +
			" and ch.wiki = w.cle_primaire" +
			" and art.id_name_url = inu2.cle_primaire" +
			" and art.id_name_url = ch.artiste" +
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
				Artiste artiste = new Artiste(resultat.getString("nameArtiste"));
				Chanson chansonCourante = new Chanson(
								resultat.getString("nameChanson"),
								resultat.getDouble("duree"),
								//Double.parseDouble(resultat.getFloat("duree")+""),
								resultat.getString("url"),
								resultat.getString("iL"),
								artiste,
								resultat.getDouble("list"),
								resultat.getDouble("playc"),
								leWiki);
				chansonCourante.setToptags(rechercherTagsChanson(chansonCourante));
				chansonsrecherchees.add(chansonCourante);
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return chansonsrecherchees;
	}
	
	 
	public ArrayList<Tag> rechercherTagsChanson(Chanson chanson){
		return null;
	}
	
	
	
	

}
