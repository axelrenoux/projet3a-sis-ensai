package bdd.rechercheBDD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.ChargementException;
import exceptions.QueryException;

import metier.Tag;
import metier.Wiki;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import bdd.sqlviajdbc.ControlAccesSQLViaJDBC;

public class maClasseAlbum {

	public maClasseAlbum(){

	}



	//resultat = ControlAccesSQLViaJDBC.executerRequeteAvecRetour(recherche);




	public ArrayList<Album> rechercherAlbums(String motcle) throws ChargementException{
		ArrayList<Album> albumsrecherches = new ArrayList<Album>();
		ResultSet resultat;
		String recherche=
			"SELECT DISTINCT " +
			"inu.name as nameAlbum , " +
			"inu.url as url , " +
			"inu2.name as nameArtiste , " +
			"alb.date_sortie as datesortie , "+
			"i.imageLarge as iL , " +
			"aud.listeners as list , " +
			"aud.playcount as playc , " +
			"w.datepublication as dateWiki , "+
			"w.resume as resumeWiki , "+
			"w.contenu as contenuWiki"+
			" FROM ALBUM alb , WIKI w , IMAGES i , AUDIMAT aud , " +
			" ID_NAME_URL inu, ID_NAME_URL inu2, ARTISTE art "+
			" WHERE alb.id_name_url = inu.cle_primaire" +
			" and alb.images = i.cle_primaire" +
			" and alb.audimat = aud.cle_primaire" +
			" and alb.wiki = w.cle_primaire" +
			" and art.id_name_url = inu2.cle_primaire" +
			" and art.id_name_url = alb.artiste" +
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
				Album albumcourant = new Album(
								resultat.getString("nameAlbum"),
								resultat.getString("url"),
								resultat.getDate("datesortie"),
								resultat.getString("iL"),
								resultat.getDouble("list"),
								resultat.getDouble("playc"),
								leWiki,
								artiste);
				albumcourant.setToptags(rechercherTagsAlbum(albumcourant));
				albumsrecherches.add(albumcourant);
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return albumsrecherches;
	}

	//TODO
	public ArrayList<Tag> rechercherTagsAlbum(Album album){
		ArrayList<Tag> tags = new ArrayList<Tag>();
		return null;
	}



}
