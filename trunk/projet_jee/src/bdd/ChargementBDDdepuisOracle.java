 package bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import metier.Album;
import metier.Artiste;
import metier.Chanson;
import metier.Tag;
import metier.Wiki;
import bdd.exceptions.ChargementException;
import bdd.exceptions.QueryException;
import controleur.Controleur;

public class ChargementBDDdepuisOracle extends ChargementBDD {
	//Les endroits où charger nos données
	private static Controleur leControleur=Controleur.getInstanceuniquecontroleur();
	
	//Nos données en Java, associées à leurs clés primaires
	private static Map<Integer,Artiste> artistes=new HashMap<Integer,Artiste>();
	private static Map<Integer,Chanson> chansons=new HashMap<Integer,Chanson>();
	private static Map<Integer,Album> albums=new HashMap<Integer,Album>();
	private static Map<Integer,Tag> tags=new HashMap<Integer,Tag>();

	public static void charger(){
			try {
				chargerListeTags();
				chargerListeArtistes();
				chargerListeAlbums();
				chargerListeChansons();
				chargerCorrespondances();
			} catch (ChargementException e) {e.printStackTrace();}
	}

	public static void chargerListeArtistes() throws ChargementException{
		ResultSet resultat;
		String recherche="SELECT DISTINCT art.cle_primaire as clef," +
								"inu.name as name," +
								"inu.url as url," +
								"i.imageSmall as iS," +
								"i.imageMedium as iMd," +
								"i.imageLarge as iL," +
								"i.imageExtraLarge as iEL," +
								"i.imageMega as iMg," +
								"aud.listeners as list," +
								"aud.playcount as playc," +
								"w.datepublication as dateWiki,"+
								"w.resume as resumeWiki,"+
								"w.contenu as contenuWiki"+
				"FROM ARTISTE as art, WIKI as w, IMAGES as i, AUDIMAT as aud, ID_NAME_URL as inu"+
				"WHERE(art.id_name_url=inu.cle_primaire" +
					"and art.images=i.cle_primaire" +
					"and art.audimat=aud.cle_primaire" +
					"and art.wiki=w.cle_primaire)";
		try {
			resultat = SQLViaJDBC.executerRequeteAvecRetour(recherche);
		} catch (QueryException e1) {
			throw new ChargementException(e1);
		}
		try {
			while(resultat.next()){
				Wiki leWiki=new Wiki(resultat.getDate("dateWiki"),
									resultat.getString("resumeWiki"),
									resultat.getString("contenuWiki"));
				/**	public Artiste(String name, String url,
									String imageSmall,String imageMedium,String imageLarge,
									String imageExtraLarge,String imageMega,double listeners,
									double playcount,ArrayList<Artiste> artistesSimilaires,
									ArrayList<Tag> toptags,Wiki wiki)*/
				//XXX Modifier ici si le constructeur d'Artiste est modifié
				Artiste lArtiste=new Artiste(resultat.getString("name"),
						resultat.getString("url"),
						resultat.getString("iS"),
						resultat.getString("iMd"),
						resultat.getString("iL"),
						resultat.getString("iEL"),
						resultat.getString("iMg"),
						resultat.getDouble("list"),
						resultat.getDouble("playc"),
						new ArrayList<Artiste>(),
						new ArrayList<Tag>(),
						leWiki);
				artistes.put(resultat.getInt("clef"),lArtiste);//pour les correspondances
				leControleur.getListeArtistes().put(resultat.getString("clef"),lArtiste);//pour charger
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
	}
	
	public static void chargerListeAlbums() throws ChargementException{
		ResultSet resultat;
		String recherche="SELECT DISTINCT alb.cle_primaire as clef," +
								"inu.name as name," +
								"c.artiste as clefArtiste," +
								"inu.id as id," +
								"inu.url as url," +
								"alb.date_sortie as datesortie,"+
								"i.imageSmall as iS," +
								"i.imageMedium as iMd," +
								"i.imageLarge as iL," +
								"i.imageExtraLarge as iEL," +
								"i.imageMega as iMg," +
								"aud.listeners as list," +
								"aud.playcount as playc," +
								"w.datepublication as dateWiki,"+
								"w.resume as resumeWiki,"+
								"w.contenu as contenuWiki"+
				"FROM ALBUM as alb, WIKI as w, IMAGES as i, AUDIMAT as aud, ID_NAME_URL as inu"+
				"WHERE(alb.id_name_url=inu.cle_primaire" +
					"and alb.images=i.cle_primaire" +
					"and alb.audimat=aud.cle_primaire" +
					"and alb.wiki=w.cle_primaire)";
		try {
			resultat = SQLViaJDBC.executerRequeteAvecRetour(recherche);
		} catch (QueryException e1) {
			throw new ChargementException(e1);
		}
		try {
			while(resultat.next()){
				Wiki leWiki=new Wiki(resultat.getDate("dateWiki"),
									resultat.getString("resumeWiki"),
									resultat.getString("contenuWiki"));
				Artiste lArtiste=artistes.get(resultat.getInt("clefArtiste"));
				/**	public Album(String name, Artiste artiste, String ID, String url,Date date,
								String imageSmall,String imageMedium,String imageLarge,
								String imageExtraLarge,String imageMega,double listeners,
								double playcount,ArrayList<Chanson> chansons,
								ArrayList<Tag> toptags,Wiki wiki)*/
				//XXX Modifier ici si le constructeur d'Album est modifié
				Album lAlbum=new Album(resultat.getString("name"),
						lArtiste,
						resultat.getString("id"),
						resultat.getString("url"),
						resultat.getDate("date_sortie"),
						resultat.getString("iS"),
						resultat.getString("iMd"),
						resultat.getString("iL"),
						resultat.getString("iEL"),
						resultat.getString("iMg"),
						resultat.getDouble("list"),
						resultat.getDouble("playc"),
						new ArrayList<Chanson>(),
						new ArrayList<Tag>(),
						leWiki);
				albums.put(resultat.getInt("clef"),lAlbum);//pour les correspondances
				leControleur.getListeAlbums().put(resultat.getString("clef"),lAlbum);//pour charger
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
	}
	
	public static void chargerListeChansons() throws ChargementException{
		ResultSet resultat;
		String recherche="SELECT DISTINCT c.cle_primaire as clef," +
								"inu.name as name," +
								"inu.url as url," +
								"c.duree as duree"+
								"i.imageSmall as iS," +
								"i.imageMedium as iMd," +
								"i.imageLarge as iL," +
								"i.imageExtraLarge as iEL," +
								"i.imageMega as iMg," +
								"aud.listeners as list," +
								"aud.playcount as playc," +
								"c.artiste as clefArtiste," +
								"w.datepublication as dateWiki," +
								"w.resume as resumeWiki," +
								"w.contenu as contenuWiki" +
				"FROM CHANSON as c, WIKI as w, IMAGES as i, AUDIMAT as aud, ID_NAME_URL as inu"+
				"WHERE(c.id_name_url=inu.cle_primaire" +
					"and c.images=i.cle_primaire" +
					"and c.audimat=aud.cle_primaire" +
					"and c.wiki=w.cle_primaire)";
		try {
			resultat = SQLViaJDBC.executerRequeteAvecRetour(recherche);
		} catch (QueryException e1) {
			throw new ChargementException(e1);
		}
		try {
			while(resultat.next()){
				Wiki leWiki=new Wiki(resultat.getDate("dateWiki"),
									resultat.getString("resumeWiki"),
									resultat.getString("contenuWiki"));
				Artiste lArtiste=artistes.get(resultat.getString("clefArtiste"));
				/**	public Chanson(String name, Double duree, String url,
						Artiste artiste,double listeners,double playcount,
						ArrayList<Album> albums,
						ArrayList<Tag> toptags,Wiki wiki) */
				//XXX Modifier ici si le constructeur de Chanson est modifié
				Chanson laChanson=new Chanson(resultat.getString("name"),
						resultat.getDouble("duree"),
						resultat.getString("url"),
						lArtiste,
						resultat.getDouble("list"),
						resultat.getDouble("playc"),
						new ArrayList<Album>(),
						new ArrayList<Tag>(),
						leWiki);
				chansons.put(resultat.getInt("clef"),laChanson);//pour les correspondances
				leControleur.getListeChansons().put(resultat.getString("clef"),laChanson);//pour charger
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
	}
	
	public static void chargerListeTags() throws ChargementException{
		ResultSet resultat;
		String recherche="SELECT DISTINCT t.cle_primaire as clef," +
								"inu.name as name," +
								"inu.url as url," +
								"t.reach as reach," +
								"t.taggings as taggings," +
								"w.datepublication as dateWiki,"+
								"w.resume as resumeWiki,"+
								"w.contenu as contenuWiki"+
				"FROM TAG as t, WIKI as w, ID_NAME_URL as inu"+
				"WHERE(t.id_name_url=inu.cle_primaire" +
					"and t.wiki=w.cle_primaire)";
		try {
			resultat = SQLViaJDBC.executerRequeteAvecRetour(recherche);
		} catch (QueryException e1) {
			throw new ChargementException(e1);
		}
		try {
			while(resultat.next()){
				Wiki leWiki=new Wiki(resultat.getDate("dateWiki"),
									resultat.getString("resumeWiki"),
									resultat.getString("contenuWiki"));
				/**	public Tag(String name, String url,
							double reach,double tagging,Wiki wiki) */
				//XXX Modifier ici si le constructeur de Tag est modifié
				Tag leTag=new Tag(resultat.getString("name"),
									resultat.getString("url"),
									resultat.getDouble("reach"),
									resultat.getDouble("taggings"),
									leWiki);
				tags.put(resultat.getInt("clef"),leTag);//pour les correspondances
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
	}
	
	public static void chargerCorrespondances() throws ChargementException{
		ResultSet resultat;
		String rechercheSimArtiste="SELECT DISTINCT " +
				"artiste1, artiste2 from ARTISTES_SIMILAIRES";
		String rechercheTagArtiste="SELECT DISTINCT " +
				"artiste, tag from CORRESP_ARTISTE_TAG";
		String rechercheTagAlbum="SELECT DISTINCT " +
				"album, tag from CORRESP_ALBUM_TAG";
		String rechercheTagChanson="SELECT DISTINCT " +
				"chanson, tag from CORRESP_CHANSON_TAG";
		String rechercheChansonAlbum="SELECT DISTINCT " +
				"chanson, album from CORRESP_CHANSON_ALBUM";
		try {
			resultat = SQLViaJDBC.executerRequeteAvecRetour(rechercheSimArtiste);
		} catch (QueryException e1) {
			throw new ChargementException(e1);
		}
		try {
			while(resultat.next()){
				Artiste lArtiste=artistes.get(resultat.getInt("artiste1"));
				Artiste autreArtiste=artistes.get(resultat.getInt("artiste2"));
				lArtiste.getArtistesSimilaires().add(autreArtiste);
				autreArtiste.getArtistesSimilaires().add(lArtiste);
				/*FIXME Du coup on devrait avoir systématiquement des doublons
				je ne corrige pas afin de pouvoir tester si notre repérage de doublons fonctionne*/
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		try {
			resultat = SQLViaJDBC.executerRequeteAvecRetour(rechercheTagArtiste);
		} catch (QueryException e1) {
			throw new ChargementException(e1);
		}
		try {
			while(resultat.next()){
				Artiste lArtiste=artistes.get(resultat.getInt("artiste"));
				lArtiste.getToptags().add(tags.get(resultat.getInt("tag")));
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		try {
			resultat = SQLViaJDBC.executerRequeteAvecRetour(rechercheTagAlbum);
		} catch (QueryException e1) {
			throw new ChargementException(e1);
		}
		try {
			while(resultat.next()){
				Album lAlbum=albums.get(resultat.getInt("album"));
				lAlbum.getToptags().add(tags.get(resultat.getInt("tag")));
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		try {
			resultat = SQLViaJDBC.executerRequeteAvecRetour(rechercheTagChanson);
		} catch (QueryException e1) {
			throw new ChargementException(e1);
		}
		try {
			while(resultat.next()){
				Chanson laChanson=chansons.get(resultat.getInt("chanson"));
				laChanson.getToptags().add(tags.get(resultat.getInt("tag")));
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		try {
			resultat = SQLViaJDBC.executerRequeteAvecRetour(rechercheChansonAlbum);
		} catch (QueryException e1) {
			throw new ChargementException(e1);
		}
		try {
			while(resultat.next()){
				Chanson laChanson=chansons.get(resultat.getInt("chanson"));
				Album lAlbum=albums.get(resultat.getInt("album"));
				lAlbum.getChansons().add(laChanson);
				laChanson.getAlbums().add(lAlbum);
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
	}
}
