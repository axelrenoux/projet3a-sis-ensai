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
import metier.oeuvres.Chanson;
import bdd.sqlviajdbc.ControlAccesSQLViaJDBC;

public class RechercheAlbumBDD {

	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	
	private final static RechercheAlbumBDD instance = new RechercheAlbumBDD();
	
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	private RechercheAlbumBDD(){
	}

	 

	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	
	/**
	 * methode qui recherche en bdd tous les albums dont le nom contient
	 * le mot clé saisi ou dont le nom de l'artiste contient le mot clé saisi 
	 * 
	 * @param motcle
	 * @return
	 * @throws ChargementException
	 */
	public ArrayList<Album> rechercherAlbums(String motcle) throws ChargementException{
		ArrayList<Album> albumsrecherches = new ArrayList<Album>();
		ResultSet resultat;
		String recherche=
			"SELECT DISTINCT  "+  
			" inu.name as nameAlbum ,   "+ 
			" inu.url as url ,    "+
			" inu2.name as nameArtiste ,    "+
			" alb.date_sortie as datesortie ,   "+
			" i.imageLarge as iL ,    "+
			" aud.listeners as list ,    "+
			" aud.playcount as playc ,    "+
			" w.datepublication as dateWiki ,   "+
			" w.resume as resumeWiki ,   "+
			" w.contenu as contenuWiki  "+
			" FROM ALBUM alb "+
			"  INNER JOIN WIKI w on alb.wiki = w.cle_primaire   "+
			"  INNER JOIN IMAGES i ON alb.images = i.cle_primaire   "+
			"  INNER JOIN AUDIMAT aud on  alb.audimat = aud.cle_primaire "+
			"  INNER JOIN ID_NAME_URL inu on alb.id_name_url = inu.cle_primaire    "+
			"  INNER JOIN (ARTISTE art  "+
			"		 INNER JOIN ID_NAME_URL inu2 on art.id_name_url = inu2.cle_primaire) "+
			"		on art.id_name_url = alb.artiste  "+
			"  WHERE (UPPER(inu.name) LIKE UPPER('%"+ motcle +"%')    "+
			"      or UPPER(inu2.name) LIKE UPPER('%"+ motcle +"%')) ";
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
								resultat.getInt("list"),
								resultat.getInt("playc"),
								leWiki,
								artiste);
				albumcourant.gererVides();
				albumsrecherches.add(albumcourant);
			}
			for(Album a : albumsrecherches){
				a.setToptags(RechercheTagBDD.getInstance().rechercherTagsAlbum(a));
				a.setChansons(RechercheChansonBDD.getInstance().rechercherChansonAlbum(a.getName()));
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return albumsrecherches;
	}

	
	
	
	/**
	 * methode qui renvoie les albums d'une chanson
	 * @param chanson
	 * @return
	 * @throws ChargementException
	 */
	public ArrayList<Album> rechercherAlbumsChanson(String id_chanson) throws ChargementException{
		ArrayList<Album> albumsChansonrecherches = new ArrayList<Album>();
		ResultSet resultat;
		String recherche=
			" SELECT DISTINCT    "+   
			" inu.name as nameAlbum ,   "+
			" inu.url as url ,       "+
			" inu2.name as nameArtiste ,   "+    
			" alb.date_sortie as datesortie ,     "+
			" i.imageLarge as iL ,   "+    
			" aud.listeners as list ,  "+     
			" aud.playcount as playc ,       "+
			" w.datepublication as dateWiki ,   "+   
			" w.resume as resumeWiki ,  "+    
			" w.contenu as contenuWiki     "+
			" FROM ALBUM alb    "+
			" INNER JOIN WIKI w on alb.wiki = w.cle_primaire   "+   
			" INNER JOIN IMAGES i ON alb.images = i.cle_primaire     "+ 
			" INNER JOIN AUDIMAT aud on  alb.audimat = aud.cle_primaire  "+  
			" INNER JOIN ID_NAME_URL inu on alb.id_name_url = inu.cle_primaire   "+   
			" INNER JOIN (ARTISTE art    	"+
			" INNER JOIN ID_NAME_URL inu2 on art.id_name_url = inu2.cle_primaire) on art.id_name_url = alb.artiste  "+    
			" INNER JOIN CORRESP_CHANSON_ALBUM cca on cca.album=alb.id_name_url "+
			" WHERE cca.chanson = '"+id_chanson+"'";
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
								resultat.getInt("list"),
								resultat.getInt("playc"),
								leWiki,
								artiste);
				albumcourant.gererVides();
				albumsChansonrecherches.add(albumcourant);
			}
			 
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return albumsChansonrecherches;
	}
	
	
	
	
	//TODO pas utilisee pour le moment
	/**
	 * methode qui renvoie les albums d'un artiste? 
	 * @param chanson
	 * @return
	 * @throws ChargementException
	 */
	public ArrayList<Album> rechercherAlbumsArtiste(String nomartiste) throws ChargementException{
		ArrayList<Album> albumsChansonrecherches = new ArrayList<Album>();
		ResultSet resultat;
		String recherche=
			"SELECT DISTINCT  "+  
			" inu.name as nameAlbum ,   "+ 
			" inu.url as url ,    "+
			" inu2.name as nameArtiste ,    "+
			" alb.date_sortie as datesortie ,   "+
			" i.imageLarge as iL ,    "+
			" aud.listeners as list ,    "+
			" aud.playcount as playc ,    "+
			" w.datepublication as dateWiki ,   "+
			" w.resume as resumeWiki ,   "+
			" w.contenu as contenuWiki  "+
			" FROM ALBUM alb "+
			"  INNER JOIN WIKI w on alb.wiki = w.cle_primaire   "+
			"  INNER JOIN IMAGES i ON alb.images = i.cle_primaire   "+
			"  INNER JOIN AUDIMAT aud on  alb.audimat = aud.cle_primaire "+
			"  INNER JOIN ID_NAME_URL inu on alb.id_name_url = inu.cle_primaire    "+
			"  INNER JOIN (ARTISTE art  "+
			"		 INNER JOIN ID_NAME_URL inu2 on art.id_name_url = inu2.cle_primaire) "+
			"		on art.id_name_url = alb.artiste  "+
			"  WHERE UPPER(inu2.name) = UPPER('%"+ nomartiste +"%')) ";
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
								resultat.getInt("list"),
								resultat.getInt("playc"),
								leWiki,
								artiste);
				albumcourant.gererVides();
				albumsChansonrecherches.add(albumcourant);
			}
			 
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return albumsChansonrecherches;
	}
	
	
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	public static RechercheAlbumBDD getInstance() {
		return instance;
	}
	
	
	
	
	


}
