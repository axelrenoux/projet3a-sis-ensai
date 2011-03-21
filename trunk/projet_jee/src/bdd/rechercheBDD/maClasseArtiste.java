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

public class maClasseArtiste {

	private final static maClasseArtiste instance = new maClasseArtiste();
	
	
	
	private maClasseArtiste() {
	}
	
	
	public ArrayList<Artiste> rechercherArtistes(String motcle) throws ChargementException{
		ArrayList<Artiste> artistesrecherches = new ArrayList<Artiste>();
		ResultSet resultat;
		String recherche=
			" SELECT DISTINCT    "+
			" inu.name as nameArtiste ,    "+
			" inu.url as url ,   "+ 
			" i.imageLarge as iL ,   "+ 
			" aud.listeners as list ,    "+
			" aud.playcount as playc ,    "+
			" w.datepublication as dateWiki ,   "+
			" w.resume as resumeWiki , "+  
			" w.contenu as contenuWiki  "+
			" FROM ARTISTE art "+
			" INNER JOIN WIKI w on w.cle_primaire = art.wiki " +
			" INNER JOIN IMAGES i on i.cle_primaire = art.imageS "+
			" INNER JOIN AUDIMAT aud on aud.cle_primaire = art.audimat "+
			" INNER JOIN ID_NAME_URL inu ON art.id_name_url = inu.cle_primaire "+  
			" WHERE UPPER(inu.name) LIKE UPPER('%"+motcle+"%')";
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
				Artiste artistecourant = new Artiste(
								resultat.getString("nameArtiste"),
								resultat.getString("url"),
								resultat.getString("iL"),
								resultat.getDouble("list"),
								resultat.getDouble("playc"),
								leWiki);
				System.out.println("#");
				System.out.println("artistecourant avant la fin" + artistecourant);
				artistesrecherches.add(artistecourant);
			}
			//on met a jour les listes de tags et d'artistes similaires
			for(Artiste a: artistesrecherches){
				a.setToptags(maClasseTag.getInstance().rechercherTagsArtiste(a));
				a.setArtistesSimilaires(rechercherArtistesSimilaires(a.getName()));
			}
			
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return artistesrecherches;
	}
	

	
	
	public ArrayList<Artiste> rechercherArtistesSimilaires(String artiste1) throws ChargementException{
		System.out.println("rechrcher des arts sim de "+artiste1);
		ArrayList<Artiste> artistesSimilairesRecherches = new ArrayList<Artiste>();
		ResultSet resultat;
		String recherche="SELECT DISTINCT inu2.name as nameArtiste2,    " +
				" inu2.url as url ,  " +
				" i.imageLarge as iL , " +
				" aud.listeners as list ," +
				" aud.playcount as playc , " +
				" w.datepublication as dateWiki , " +
				" w.resume as resumeWiki ,  " +
				" w.contenu as contenuWiki " +
				" FROM ARTISTE art " +
				" INNER JOIN WIKI w on art.wiki = w.cle_primaire" +
				" INNER JOIN IMAGES i on art.images = i.cle_primaire" +
				" INNER JOIN AUDIMAT aud on art.audimat = aud.cle_primaire" +
				" INNER JOIN ID_NAME_URL inu2 on art.id_name_url = inu2.cle_primaire" +
				" INNER JOIN ARTISTES_SIMILAIRES arts on arts.artiste2 = art.id_name_url" +
				" INNER JOIN ID_NAME_URL inu1 on arts.artiste1 = inu1.cle_primaire" +
				" WHERE UPPER(inu1.name) = UPPER('" + artiste1 + "') ";
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
				Artiste artisteSimcourant = new Artiste(
								resultat.getString("nameArtiste2"),
								resultat.getString("url"),
								resultat.getString("iL"),
								resultat.getDouble("list"),
								resultat.getDouble("playc"),
								leWiki);
				artistesSimilairesRecherches.add(artisteSimcourant);
				
			}
		} catch (SQLException e) {
			throw new ChargementException(e);
		}
		return artistesSimilairesRecherches;
	}
	
	
	
	public static maClasseArtiste getInstance() {
		return instance;
	}

}
