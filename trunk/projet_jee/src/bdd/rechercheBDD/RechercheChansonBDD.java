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

public class RechercheChansonBDD {
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	
	private final static RechercheChansonBDD instance = new RechercheChansonBDD();
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	private RechercheChansonBDD() {
	}
	
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	
	/**
	 * methode qui recherche les chansons dont le nom contient le mot clé saisi
	 * ou dont le nom de l'artiste contient le mot clé saisi
	 * @param motcle
	 * @return
	 * @throws ChargementException
	 */
	public ArrayList<Chanson> rechercherChansons(String motcle) throws ChargementException{
		ArrayList<Chanson> chansonsrecherchees = new ArrayList<Chanson>();
		ResultSet resultat;
		String recherche=
			 " SELECT DISTINCT "+   
			 " ch.id_name_url as inuChanson, "+
			 " ch.duree as duree,   "+
			 " inu.name as nameChanson ,    "+
			 " inu.url as url ,    "+
			 " inu2.name as nameArtiste ,  "+  
			 " i.imageLarge as iL ,    "+
			 " aud.listeners as list ,    "+
			 " aud.playcount as playc ,    "+
			 " w.datepublication as dateWiki ,   "+
			 " w.resume as resumeWiki ,   "+
			 " w.contenu as contenuWiki "+
			 " FROM CHANSON ch "+
			 " INNER JOIN WIKI w on ch.wiki = w.cle_primaire  "+ 
			 " INNER JOIN IMAGES i on ch.images = i.cle_primaire  "+
			 " INNER JOIN AUDIMAT aud on   ch.audimat = aud.cle_primaire "+
			 " INNER JOIN ID_NAME_URL inu on ch.id_name_url = inu.cle_primaire "+
			 "		INNER JOIN (ARTISTE art   INNER JOIN  ID_NAME_URL inu2 "+
			 "		on  art.id_name_url = inu2.cle_primaire  ) "+
			 "	on art.id_name_url = ch.artiste   "+
			 " WHERE UPPER(inu.name) LIKE UPPER('%"+motcle+"%') " +
			 		" or UPPER(inu2.name) LIKE UPPER('%"+motcle+"%')";
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
								resultat.getString("inuChanson"),
								resultat.getString("nameChanson"),
								resultat.getDouble("duree"),
								resultat.getString("url"),
								resultat.getString("iL"),
								artiste,
								resultat.getDouble("list"),
								resultat.getDouble("playc"),
								leWiki);
				if(chansonCourante.getPlaycount()==0){
					chansonCourante.genererPlaycount();
				}
				if(chansonCourante.getListeners()==0){
					chansonCourante.genererListeners();
				}
				if(chansonCourante.getDuree()==0){
					chansonCourante.genererDuree();
				}
				if(chansonCourante.getImageLarge().equals("") || chansonCourante.getImageLarge().equals("%27%27" )){
					chansonCourante.setImageLarge("http://mylene.net/mfpics/itunes_20010_002.png");
				}
				chansonsrecherchees.add(chansonCourante);
			}
			for (Chanson ch : chansonsrecherchees){
				ch.setToptags(RechercheTagBDD.getInstance().rechercherTagsChanson(ch));
				//ch.setAlbums(RechercheAlbumBDD.getInstance().rechercherAlbumsChanson(ch.getID()));
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return chansonsrecherchees;
	}
	
	 

	
	/**
	 * methode qui renvoie les chansons correspondant à l'album dont le nom
	 * est mis en paramètre
	 * @param album
	 * @return
	 * @throws ChargementException
	 */
	public ArrayList<Chanson> rechercherChansonAlbum(String album) throws ChargementException{
		ArrayList<Chanson> chansonsAlbumrecherchees = new ArrayList<Chanson>();
		ResultSet resultat;
		String recherche="SELECT DISTINCT     "+  
		 " ch.id_name_url as inuChanson, "+
		  " ch.duree as duree,     "+
		 "  inu.name as nameChanson , "+     
		  " inu.url as url ,      "+
		 "  inu2.name as nameArtiste ,"+      
		 "  i.imageLarge as iL ,      "+
		 "  aud.listeners as list ,     "+ 
		 "  aud.playcount as playc ,      "+
		 "  w.datepublication as dateWiki ,     "+
		 "  w.resume as resumeWiki ,     "+
		 "  w.contenu as contenuWiki   "+
		"   FROM CHANSON ch   "+
		 "  INNER JOIN WIKI w on ch.wiki = w.cle_primaire     "+
		"   INNER JOIN IMAGES i on ch.images = i.cle_primaire    "+
		 "  INNER JOIN AUDIMAT aud on   ch.audimat = aud.cle_primaire   "+
		 "  INNER JOIN ID_NAME_URL inu on ch.id_name_url = inu.cle_primaire   "+
		 " 		INNER JOIN (ARTISTE art   INNER JOIN  ID_NAME_URL inu2   "+
		"							on  art.id_name_url = inu2.cle_primaire  ) "+
		"		on art.id_name_url = ch.artiste "+
			" INNER JOIN ( "+
		"			CORRESP_CHANSON_ALBUM caa "+
		"				INNER JOIN ( "+
		"					ALBUM alb INNER JOIN id_name_url inu3 on inu3.cle_primaire=alb.id_name_url) "+
		"				on caa.album = alb.id_name_url) "+
		"			on caa.chanson = ch.id_name_url "+
		"   WHERE UPPER(inu3.name) LIKE UPPER('%"+album +"%')  ";
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
								resultat.getString("inuChanson"),
								resultat.getString("nameChanson"),
								resultat.getDouble("duree"),
								//Double.parseDouble(resultat.getFloat("duree")+""),
								resultat.getString("url"),
								resultat.getString("iL"),
								artiste,
								resultat.getDouble("list"),
								resultat.getDouble("playc"),
								leWiki);
				chansonsAlbumrecherchees.add(chansonCourante);
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return chansonsAlbumrecherchees;
	}

	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	public static RechercheChansonBDD getInstance() {
		return instance;
	}
	
	

}
