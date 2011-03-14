 package bdd;

import handler.sax.AlbumSearchHandler;
import handler.sax.ArtisteSearchHandler;
import handler.sax.ChansonSearchHandler;

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
import bdd.exceptions.CanalException;
import bdd.exceptions.ChargementException;
import bdd.exceptions.QueryException;

public class ChargementBDDdepuisOracle extends ChargementBDD {
	//Les endroits où charger nos données
	private ArtisteSearchHandler stockageArtistes;//FIXME à modifier
	private ChansonSearchHandler stockageChansons;//FIXME à modifier
	private AlbumSearchHandler stockageAlbums;//FIXME à modifier
	
	//Nos données en Java, associées à leurs clés primaires
	private Map<Integer,Artiste> artistes=new HashMap<Integer,Artiste>();
	private Map<Integer,Chanson> chansons=new HashMap<Integer,Chanson>();
	private Map<Integer,Album> albums=new HashMap<Integer,Album>();
	private Map<Integer,Tag> tags=new HashMap<Integer,Tag>();

	public void charger() throws CanalException, ChargementException {
		chargerListeTags();
		chargerListeArtistes();
		chargerListeAlbums();
		chargerListeChansons();
		chargerCorrespondances();
	}

	public void chargerListeArtistes() throws ChargementException{
		ResultSet resultat;
		String recherche="SELECT DISTINCT art.cle_primaire as clef," +
								"inu.name as val1," +
								"inu.url as val2," +
								"i.imageSmall as val3," +
								"i.imageMedium as val4," +
								"i.imageLarge as val5," +
								"i.imageExtraLarge as val6," +
								"i.imageMega as val7," +
								"aud.listeners as val8," +
								"aud.playcount as val9," +
								"w.datepublication as val12,"+
								"w.resume as val13,"+
								"w.contenu as val14"+
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
				Wiki leWiki=new Wiki(resultat.getDate("val12"),
									resultat.getString("val13"),
									resultat.getString("val14"));
				/**Artiste(String name, String url,
					String imageSmall,String imageMedium,String imageLarge,
					String imageExtraLarge,String imageMega,double listeners,
					double playcount,ArrayList<Artiste> artistesSimilaires,
					ArrayList<Tag> toptags,Wiki wiki)*/
				Artiste lArtiste=new Artiste(resultat.getString("val1"),
						resultat.getString("val2"),
						resultat.getString("val3"),
						resultat.getString("val4"),
						resultat.getString("val5"),
						resultat.getString("val6"),
						resultat.getString("val7"),
						resultat.getDouble("val8"),
						resultat.getDouble("val9"),
						new ArrayList<Artiste>(),
						new ArrayList<Tag>(),
						leWiki);
				artistes.put(resultat.getInt("clef"),lArtiste);//pour les correspondances
				stockageArtistes.getLstArtistes().add(lArtiste);//pour charger
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
	}
	
	public void chargerListeAlbums() throws ChargementException{
		ResultSet resultat;
		String recherche="SELECT DISTINCT alb.cle_primaire as clef," +
								"inu.name as val1," +
								"c.artiste as clefArtiste," +
								"inu.id as val1_5," +
								"inu.url as val2," +
								"alb.date_sortie as val2_5,"+
								"i.imageSmall as val3," +
								"i.imageMedium as val4," +
								"i.imageLarge as val5," +
								"i.imageExtraLarge as val6," +
								"i.imageMega as val7," +
								"aud.listeners as val8," +
								"aud.playcount as val9," +
								"w.datepublication as val12,"+
								"w.resume as val13,"+
								"w.contenu as val14"+
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
				Wiki leWiki=new Wiki(resultat.getDate("val12"),
									resultat.getString("val13"),
									resultat.getString("val14"));
				Artiste lArtiste=artistes.get(resultat.getInt("clefArtiste"));
				Album lAlbum=new Album(resultat.getString("val1"),
						lArtiste,
						resultat.getString("val1_5"),
						resultat.getString("val2"),
						resultat.getDate("val2_5"),
						resultat.getString("val3"),
						resultat.getString("val4"),
						resultat.getString("val5"),
						resultat.getString("val6"),
						resultat.getString("val7"),
						resultat.getDouble("val8"),
						resultat.getDouble("val9"),
						new ArrayList<Chanson>(),
						new ArrayList<Tag>(),
						leWiki);
				albums.put(resultat.getInt("clef"),lAlbum);//pour les correspondances
				stockageAlbums.getLstAlbums().add(lAlbum);//pour charger
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
	}
	
	public void chargerListeChansons() throws ChargementException{
		ResultSet resultat;
		String recherche="SELECT DISTINCT c.cle_primaire as clef," +
								"inu.name as val1," +
								"inu.url as val2," +
								"c.duree as val2_5,"+
								"i.imageSmall as val3," +
								"i.imageMedium as val4," +
								"i.imageLarge as val5," +
								"i.imageExtraLarge as val6," +
								"i.imageMega as val7," +
								"aud.listeners as val8," +
								"aud.playcount as val9," +
								"c.artiste as clefArtiste," +
								"w.datepublication as val12," +
								"w.resume as val13," +
								"w.contenu as val14" +
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
				Wiki leWiki=new Wiki(resultat.getDate("val12"),
									resultat.getString("val13"),
									resultat.getString("val14"));
				Artiste lArtiste=artistes.get(resultat.getString("clefArtiste"));
				Chanson laChanson=new Chanson(resultat.getString("val1"),
						resultat.getDouble("val2_5"),
						resultat.getString("val2"),
						lArtiste,
						resultat.getDouble("val8"),
						resultat.getDouble("val9"),
						new ArrayList<Album>(),
						new ArrayList<Tag>(),
						leWiki);
				chansons.put(resultat.getInt("clef"),laChanson);//pour les correspondances
				stockageChansons.getLstChanson().add(laChanson);//pour charger
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
	}
	
	public void chargerListeTags() throws ChargementException{
		ResultSet resultat;
		String recherche="SELECT DISTINCT t.cle_primaire as clef," +
								"inu.name as val1," +
								"inu.url as val2," +
								"t.reach as val3," +
								"t.taggings as val4," +
								"w.datepublication as val12,"+
								"w.resume as val13,"+
								"w.contenu as val14"+
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
				Wiki leWiki=new Wiki(resultat.getDate("val12"),
									resultat.getString("val13"),
									resultat.getString("val14"));
				Tag leTag=new Tag(resultat.getString("val1"),
									resultat.getString("val2"),
									resultat.getDouble("val3"),
									resultat.getDouble("val4"),
									leWiki);
				tags.put(resultat.getInt("clef"),leTag);//pour les correspondances
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
	}
	
	public void chargerCorrespondances() throws ChargementException{
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
