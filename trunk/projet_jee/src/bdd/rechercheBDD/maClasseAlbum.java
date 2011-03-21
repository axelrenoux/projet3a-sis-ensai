package bdd.rechercheBDD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import metier.Wiki;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import bdd.sqlviajdbc.ControlAccesSQLViaJDBC;
import exceptions.ChargementException;
import exceptions.QueryException;

public class maClasseAlbum {

	private final static maClasseAlbum instance = new maClasseAlbum();
	
	private maClasseAlbum(){
	}

	


	//resultat = ControlAccesSQLViaJDBC.executerRequeteAvecRetour(recherche);

	public ArrayList<Album> rechercherAlbums(String motcle) throws ChargementException{
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
			return communRechercherAlbums(recherche);
		}
	
	public ArrayList<Album> rechercherAlbumsEtendu(String motcle) throws ChargementException{
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
			"  WHERE (inu.cle_primaire IN ("+ConditionsRechercherEtendu.getClesAlbum(motcle)+")    "+
			"      or inu2.cle_primaire IN ("+ConditionsRechercherEtendu.getClesAlbum(motcle)+")) ";
			return communRechercherAlbums(recherche);
		}


	private ArrayList<Album> communRechercherAlbums(String recherche) throws ChargementException{
		ArrayList<Album> albumsrecherches = new ArrayList<Album>();
		ResultSet resultat;
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
				albumsrecherches.add(albumcourant);
			}
			for(Album a : albumsrecherches){
				a.setToptags(maClasseTag.getInstance().rechercherTagsAlbum(a));
				a.setChansons(maClasseChanson.getInstance().rechercherChansonAlbum(a.getName()));
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return albumsrecherches;
	}

	
	
	
	//TODO a tester sur une chanson dont on sait que l'album est en base
	/**
	 * methode qui renvoie les albums d'une chanson
	 * @param chanson
	 * @return
	 * @throws ChargementException
	 */
	public ArrayList<Album> rechercherAlbumsChanson(String chanson) throws ChargementException{
		String recherche=
			" SELECT DISTINCT      "+
			" inu.name as nameAlbum ,  "+   
			" inu.url as url ,      "+
			" inu2.name as nameArtiste ,      "+
			" alb.date_sortie as datesortie ,    "+ 
			" i.imageLarge as iL ,      "+
			" aud.listeners as list ,      "+
			" aud.playcount as playc ,      "+
			" w.datepublication as dateWiki ,     "+
			" w.resume as resumeWiki ,     "+
			" w.contenu as contenuWiki    "+
			" FROM ALBUM alb   "+
			" INNER JOIN WIKI w on alb.wiki = w.cle_primaire     "+
			" INNER JOIN IMAGES i ON alb.images = i.cle_primaire     "+
			" INNER JOIN AUDIMAT aud on  alb.audimat = aud.cle_primaire   "+
			" INNER JOIN ID_NAME_URL inu on alb.id_name_url = inu.cle_primaire     "+ 
			" INNER JOIN (ARTISTE art    "+
			"	INNER JOIN ID_NAME_URL inu2 on art.id_name_url = inu2.cle_primaire)   "+
			"	on art.id_name_url = alb.artiste     "+
			" INNER JOIN (CORRESP_CHANSON_ALBUM cca    "+
			"	INNER JOIN (CHANSON ch    "+
			"		INNER JOIN id_name_url inu3 on inu3.cle_primaire = ch.id_name_url)   "+ 
			"	on ch.id_name_url = cca.chanson)    "+
			" on cca.album = alb.id_name_url   "+
			" WHERE UPPER(inu3.name)  = UPPER('%"+chanson+"%')";
		return communRechercherAlbumsChanson(recherche);
	}
	
	public ArrayList<Album> rechercherAlbumsChansonEtendu(String chanson) throws ChargementException{
		String recherche=
			" SELECT DISTINCT      "+
			" inu.name as nameAlbum ,  "+   
			" inu.url as url ,      "+
			" inu2.name as nameArtiste ,      "+
			" alb.date_sortie as datesortie ,    "+ 
			" i.imageLarge as iL ,      "+
			" aud.listeners as list ,      "+
			" aud.playcount as playc ,      "+
			" w.datepublication as dateWiki ,     "+
			" w.resume as resumeWiki ,     "+
			" w.contenu as contenuWiki    "+
			" FROM ALBUM alb   "+
			" INNER JOIN WIKI w on alb.wiki = w.cle_primaire     "+
			" INNER JOIN IMAGES i ON alb.images = i.cle_primaire     "+
			" INNER JOIN AUDIMAT aud on  alb.audimat = aud.cle_primaire   "+
			" INNER JOIN ID_NAME_URL inu on alb.id_name_url = inu.cle_primaire     "+ 
			" INNER JOIN (ARTISTE art    "+
			"	INNER JOIN ID_NAME_URL inu2 on art.id_name_url = inu2.cle_primaire)   "+
			"	on art.id_name_url = alb.artiste     "+
			" INNER JOIN (CORRESP_CHANSON_ALBUM cca    "+
			"	INNER JOIN (CHANSON ch    "+
			"		INNER JOIN id_name_url inu3 on inu3.cle_primaire = ch.id_name_url)   "+ 
			"	on ch.id_name_url = cca.chanson)    "+
			" on cca.album = alb.id_name_url   "+
			" WHERE inu3.cle_primaire IN ("+ConditionsRechercherEtendu.getClesChanson(chanson)+")";
		return communRechercherAlbumsChanson(recherche);
	}
	
	private ArrayList<Album> communRechercherAlbumsChanson(String recherche) throws ChargementException{
		ArrayList<Album> albumsChansonrecherches = new ArrayList<Album>();
		ResultSet resultat;
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
				albumsChansonrecherches.add(albumcourant);
			}
			 
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return albumsChansonrecherches;
	}
	
	
	
	//TODO a tester 
	/**
	 * methode qui renvoie les albums d'un artiste? 
	 * @param chanson
	 * @return
	 * @throws ChargementException
	 */
	public ArrayList<Album> rechercherAlbumsArtiste(String nomartiste) throws ChargementException{
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
		return communRechercherAlbumsArtiste(recherche);
	}
	
	public ArrayList<Album> rechercherAlbumsArtisteEtendu(String nomartiste) throws ChargementException{
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
			"  WHERE inu2.cle_primaire IN ("+ ConditionsRechercherEtendu.getClesArtiste(nomartiste) +")) ";
		return communRechercherAlbumsArtiste(recherche);
	}
	
	private ArrayList<Album> communRechercherAlbumsArtiste(String recherche) throws ChargementException{
		ArrayList<Album> albumsChansonrecherches = new ArrayList<Album>();
		ResultSet resultat;
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
				albumsChansonrecherches.add(albumcourant);
			}
			 
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return albumsChansonrecherches;
	}
	
	
	public static maClasseAlbum getInstance() {
		return instance;
	}
	
	
	
	
	


}
