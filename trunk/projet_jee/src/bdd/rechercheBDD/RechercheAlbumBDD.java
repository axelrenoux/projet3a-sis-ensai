 package bdd.rechercheBDD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bdd.sqlviajdbc.ControlAccesSQLViaJDBC;

import metier.Tag;
import metier.Wiki;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;
import controleur.Controleur;
import exceptions.ChargementException;
import exceptions.QueryException;

public class RechercheAlbumBDD extends RechercheBDD{
	
	public RechercheAlbumBDD(String ch){
		super();
		charger("",ch,"","");
	}	
	
	@Override
 	protected void chargerListeArtistes(String nomCherche) throws ChargementException{
		ResultSet resultat;
		String recherche="SELECT DISTINCT inu.cle_primaire as clef , " +
								"inu.name as name , " +
								"inu.url as url , " +
								"i.imagesmall as iSmall , " +
								"i.imagemedium as iMd , " +
								"i.imagelarge as iL , " +
								"i.imageextralarge as iEL , " +
								"i.imagemega as iMg , " +
								"aud.listeners as list , " +
								"aud.playcount as playc , " +
								"w.datepublication as dateWiki , "+
								"w.resume as resumeWiki , "+
								"w.contenu as contenuWiki"+
				" FROM ARTISTE art , WIKI w , IMAGES i , AUDIMAT aud , ID_NAME_URL inu , ALBUM alb , ID_NAME_URL inu2" +
				" ID_NAME_URL inu2 , ARTISTES_SIMILAIRES s" +
				" WHERE art.id_name_url = inu.cle_primaire" +
					" and art.images = i.cle_primaire" +
					" and art.audimat = aud.cle_primaire" +
					" and art.wiki = w.cle_primaire"+
					
					" and alb.artiste = inu.cle_primaire " +
					" and alb.id_name_url = inu2.cle_primaire " +
					" and inu2.name='"+nomCherche+"' ";
		
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
				add(resultat.getInt("clef"),
					new Artiste(resultat.getString("name"),
						resultat.getString("url"),
						resultat.getString("iSmall"),
						resultat.getString("iMd"),
						resultat.getString("iL"),
						resultat.getString("iEL"),
						resultat.getString("iMg"),
						resultat.getDouble("list"),
						resultat.getDouble("playc"),
						new ArrayList<Artiste>(),
						new ArrayList<Tag>(),
						leWiki));
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
	}
	
 	@Override
 	protected void chargerListeAlbums(String nomCherche) throws ChargementException{
		ResultSet resultat;
		String recherche="SELECT DISTINCT inu.cle_primaire as clef , " +
								"inu.name as name , " +
								"alb.artiste as clefArtiste , " +
								"inu.id as id , " +
								"inu.url as url , " +
								"alb.date_sortie as datesortie , "+
								"i.imageSmall as iSmall , " +
								"i.imageMedium as iMd , " +
								"i.imageLarge as iL , " +
								"i.imageExtraLarge as iEL , " +
								"i.imageMega as iMg , " +
								"aud.listeners as list , " +
								"aud.playcount as playc , " +
								"w.datepublication as dateWiki , "+
								"w.resume as resumeWiki , "+
								"w.contenu as contenuWiki"+
				" FROM ALBUM alb , WIKI w , IMAGES i , AUDIMAT aud , ID_NAME_URL inu "+
				" WHERE alb.id_name_url = inu.cle_primaire" +
					" and alb.images = i.cle_primaire" +
					" and alb.audimat = aud.cle_primaire" +
					" and alb.wiki = w.cle_primaire" +
					
					" and inu.name = '"+nomCherche+"'";
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
				
				add(resultat.getInt("clef"),
					new Album(resultat.getString("name"),
						getArtistes().get(resultat.getInt("clefArtiste")),
						resultat.getString("id"),
						resultat.getString("url"),
						resultat.getDate("datesortie"),
						resultat.getString("iSmall"),
						resultat.getString("iMd"),
						resultat.getString("iL"),
						resultat.getString("iEL"),
						resultat.getString("iMg"),
						resultat.getDouble("list"),
						resultat.getDouble("playc"),
						new ArrayList<Chanson>(),
						new ArrayList<Tag>(),
						leWiki));
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
	}
 	
 	@Override	
 	protected void chargerListeChansons(String nomCherche) throws ChargementException{
 		ResultSet resultat;
		String recherche="SELECT DISTINCT inu.cle_primaire as clef , " +
								"inu.name as name , " +
								"inu.url as url , " +
								"c.duree as duree , " +
								"aud.listeners as list , " +
								"aud.playcount as playc , " +
								"c.artiste as clefArtiste , " +
								"w.datepublication as dateWiki , " +
								"w.resume as resumeWiki , " +
								"w.contenu as contenuWiki" +
				" FROM CHANSON c , WIKI w , AUDIMAT aud , ID_NAME_URL inu , " +
				" ID_NAME_URL inu2 , CORRESP_CHANSON_ALBUM corr"+
				" WHERE c.id_name_url = inu.cle_primaire" +
					" and c.audimat = aud.cle_primaire" +
					" and c.wiki = w.cle_primaire"+

					" and corr.chanson = inu.cle_primaire" +
					" and corr.album = inu2.cle_primaire" +
					" and inu2.name = '"+nomCherche+"'";
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
				add(resultat.getInt("clef"),
						new Chanson(resultat.getString("name"),
						resultat.getDouble("duree"),
						resultat.getString("url"),
						getArtistes().get(resultat.getString("clefArtiste")),
						resultat.getDouble("list"),
						resultat.getDouble("playc"),
						new ArrayList<Album>(),
						new ArrayList<Tag>(),
						leWiki));
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
	}
 	
 	@Override	
 	protected void chargerListeTags(String nomCherche) throws ChargementException{
		ResultSet resultat;
		String recherche="SELECT DISTINCT inu.cle_primaire as clef , " +
								"inu.name as name , " +
								"inu.url as url , " +
								"t.reach as reach , " +
								"t.taggings as taggings , " +
								"w.datepublication as dateWiki , "+
								"w.resume as resumeWiki , "+
								"w.contenu as contenuWiki"+
						" FROM TAG t, WIKI w, ID_NAME_URL inu , ID_NAME_URL inu2 , CORRESP_ARTISTE_TAG as corr"+
						" WHERE t.id_name_url = inu.cle_primaire" +
						" AND t.wiki = w.cle_primaire " +
						
						" AND corr.tag = inu.cle_primaire " +
						" AND corr.album = inu2.cle_primaire" +
						" AND inu2.name = '"+nomCherche+"'";
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
				add(resultat.getInt("clef"),
						new Tag(resultat.getString("name"),
									resultat.getString("url"),
									resultat.getDouble("reach"),
									resultat.getDouble("taggings"),
									leWiki));
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
	}
	

	
}
