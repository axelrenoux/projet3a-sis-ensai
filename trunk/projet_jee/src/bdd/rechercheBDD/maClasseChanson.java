package bdd.rechercheBDD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import metier.Wiki;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;
import bdd.sqlviajdbc.ControlAccesSQLViaJDBC;
import exceptions.ChargementException;
import exceptions.QueryException;

public class maClasseChanson {
	
	private final static maClasseChanson instance = new maClasseChanson();
	
	private maClasseChanson() {
	}
	
	public ArrayList<Chanson> rechercherChansons(String motcle) throws ChargementException{
		String recherche=
			 " SELECT DISTINCT "+    
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
			 " WHERE UPPER(inu.name) LIKE UPPER('%"+motcle+"%') ";
		return communRechercherChansons(recherche);
	}
	
	public ArrayList<Chanson> rechercherChansonsEtendu(String motcle) throws ChargementException{
		String recherche=
			 " SELECT DISTINCT "+    
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
			 " WHERE inu.cle_primaire IN ("+ConditionsRechercherEtendu.getClesChanson(motcle)+") ";
		return communRechercherChansons(recherche);
	}
	
	private ArrayList<Chanson> communRechercherChansons(String recherche) throws ChargementException{
		ArrayList<Chanson> chansonsrecherchees = new ArrayList<Chanson>();
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
				chansonsrecherchees.add(chansonCourante);
			}
			for (Chanson ch : chansonsrecherchees){
				ch.setToptags(maClasseTag.getInstance().rechercherTagsChanson(ch));
				ch.setAlbums(maClasseAlbum.getInstance().rechercherAlbumsChanson(ch.getName()));
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return chansonsrecherchees;
	}	 

	//TODO a tester
	public ArrayList<Chanson> rechercherChansonAlbum(String album) throws ChargementException{
		String recherche="SELECT DISTINCT     "+  
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
		return communRechercherChansonAlbum(recherche);
	}
	
	public ArrayList<Chanson> rechercherChansonAlbumEtendu(String album) throws ChargementException{
		String recherche="SELECT DISTINCT     "+  
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
		"   WHERE inu3.cle_primaire IN("+ConditionsRechercherEtendu.getClesAlbum(album)+")  ";
		return communRechercherChansonAlbum(recherche);
	}
	
	private ArrayList<Chanson> communRechercherChansonAlbum(String recherche) throws ChargementException{
		ArrayList<Chanson> chansonsAlbumrecherchees = new ArrayList<Chanson>();
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
				chansonsAlbumrecherchees.add(chansonCourante);
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return chansonsAlbumrecherchees;
	}

	
	
	public static maClasseChanson getInstance() {
		return instance;
	}
	
	

}
