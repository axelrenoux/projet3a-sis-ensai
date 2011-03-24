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

public class RechercheTagBDD {

	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	
	private final static RechercheTagBDD instance = new RechercheTagBDD();
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	private RechercheTagBDD() {
	}
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	
	/**
	 * methode qui recherche les tags d'une chanson
	 * @param chanson
	 * @return
	 * @throws ChargementException 
	 */
	public ArrayList<Tag> rechercherTagsChanson(Chanson chanson) throws ChargementException{
		ArrayList<Tag> tagrecherches = new ArrayList<Tag>();
		ResultSet resultat;
		String recherche=
			"SELECT DISTINCT inu.name as nameTag,      "+
			"  inu.url as url,      "+
			"  tag.reach as reach, "+
			"  tag.taggings as taggings, "+
			"  w.datepublication as dateWiki , "+     
			"  w.resume as resumeWiki ,      "+
			"  w.contenu as contenuWiki     "+
			"  FROM TAG tag    "+
			"  INNER JOIN WIKI w on tag.wiki = w.cle_primaire    "+ 
			"  INNER JOIN ID_NAME_URL inu on tag.id_name_url = inu.cle_primaire     "+ 
			"  INNER JOIN (CORRESP_CHANSON_TAG corresp    "+
			" 		INNER JOIN ID_NAME_URL inu2 on corresp.chanson = inu2.cle_primaire) "+
            "  on corresp.tag = tag.id_name_url "+
			"  WHERE UPPER(inu2.name) = UPPER('"+ chanson.getName()+"')";
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
				Tag tagcourant = new Tag(
								resultat.getString("nameTag"),
								resultat.getString("url"),
								resultat.getInt("reach"),
								resultat.getInt("taggings"),
								leWiki);
				tagrecherches.add(tagcourant);
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return tagrecherches;
	}
	
	
	/**
	 * methode qui retourne les tags d'un album
	 * @param album
	 * @return
	 * @throws ChargementException 
	 */
	public ArrayList<Tag> rechercherTagsAlbum(Album album) throws ChargementException{
		ArrayList<Tag> tagrecherches = new ArrayList<Tag>();
		ResultSet resultat;
		String recherche=
			"SELECT DISTINCT inu.name as nameTag,      "+
			"  inu.url as url,      "+
			"  tag.reach as reach, "+
			"  tag.taggings as taggings, "+
			"  w.datepublication as dateWiki , "+     
			"  w.resume as resumeWiki ,      "+
			"  w.contenu as contenuWiki     "+
			"  FROM TAG tag    "+
			"  INNER JOIN WIKI w on tag.wiki = w.cle_primaire    "+ 
			"  INNER JOIN ID_NAME_URL inu on tag.id_name_url = inu.cle_primaire     "+ 
			"  INNER JOIN (CORRESP_ALBUM_TAG corresp    "+
			" 		INNER JOIN ID_NAME_URL inu2 on corresp.album = inu2.cle_primaire) "+
            "  on corresp.tag = tag.id_name_url "+
			"  WHERE UPPER(inu2.name) LIKE UPPER('%"+ album.getName()+"%')";
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
				Tag tagcourant = new Tag(
								resultat.getString("nameTag"),
								resultat.getString("url"),
								resultat.getInt("reach"),
								resultat.getInt("taggings"),
								leWiki);
				tagrecherches.add(tagcourant);
				System.out.println("ooooooooooooon ajoute un tag");
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		System.out.println("oooooooooooooon retourne "+ tagrecherches);
		return tagrecherches;
	}
	
	/**
	 * methode qui retourne les tags d'un artiste
	 * @param artiste
	 * @return
	 * @throws ChargementException 
	 */
	public ArrayList<Tag> rechercherTagsArtiste(Artiste artiste) throws ChargementException{
		ArrayList<Tag> tagrecherches = new ArrayList<Tag>();
		ResultSet resultat;
		String recherche=
			"SELECT DISTINCT inu.name as nameTag,      "+
			"  inu.url as url,      "+
			"  tag.reach as reach, "+
			"  tag.taggings as taggings, "+
			"  w.datepublication as dateWiki , "+     
			"  w.resume as resumeWiki ,      "+
			"  w.contenu as contenuWiki     "+
			"  FROM TAG tag    "+
			"  INNER JOIN WIKI w on tag.wiki = w.cle_primaire    "+ 
			"  INNER JOIN ID_NAME_URL inu on tag.id_name_url = inu.cle_primaire     "+ 
			"  INNER JOIN (CORRESP_ARTISTE_TAG corresp    "+
			" 		INNER JOIN ID_NAME_URL inu2 on corresp.artiste = inu2.cle_primaire) "+
            "  on corresp.tag = tag.id_name_url "+
			"  WHERE UPPER(inu2.name) = UPPER('"+ artiste.getName()+"')";
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
				Tag tagcourant = new Tag(
								resultat.getString("nameTag"),
								resultat.getString("url"),
								resultat.getInt("reach"),
								resultat.getInt("taggings"),
								leWiki);
				tagrecherches.add(tagcourant);
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return tagrecherches;
	}
	
	
	
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	public static RechercheTagBDD getInstance() {
		return instance;
	}
	
	
	
}
