package bdd;

import java.util.Date;

import bdd.exceptions.ConnectionException;
import bdd.exceptions.UpdateException;

public class SauvegardeBddFormatOracle extends SauvegardeUnFormatPourLaBdd{
	private String requeteSQL;

	@Override
	public void ecrireEnTete() {
		InitialiserBddOracle.creerTables();
		try {SQLViaJDBC.connecter();}
		catch (ConnectionException e) {e.printStackTrace();}
	}

	@Override
	public void ecrireConclusion() {
		try {SQLViaJDBC.fermerBDD();}
		catch (ConnectionException e) {e.printStackTrace();}
	}

	@Override
	public void sauverWiki(int pk, String id_wiki, Date datePublication,
			String resume, String contenu) {
		//FIXME gérer la date JAVA vs SQL
		requeteSQL="INSERT INTO WIKI(cle_primaire, id_wiki, datePublication, resume, contenu)"+
					"values("+pk+","+id_wiki+","+datePublication+","+resume+","+contenu+")";
		try {SQLViaJDBC.executerRequeteSansRetour(requeteSQL);} 
		catch (UpdateException e) {e.printStackTrace();}
	}

	@Override
	public void sauverAudimat(int pk, double listeners, double playcount) {
		requeteSQL="INSERT INTO AUDIMAT(cle_primaire, listeners, playcount)"+
					"values("+pk+","+listeners+","+playcount+")";
		try {SQLViaJDBC.executerRequeteSansRetour(requeteSQL);} 
		catch (UpdateException e) {e.printStackTrace();}
	}

	@Override
	public void sauverArtiste(int pk, int coord_artiste,
			int images, int audimat, int wiki) {
		requeteSQL="INSERT INTO ARTISTE(cle_primaire,id_name_url,images,audimat,wiki)"+
				"values("+pk+","+coord_artiste+","+images+","+audimat+","+wiki+")";
		try {SQLViaJDBC.executerRequeteSansRetour(requeteSQL);} 
		catch (UpdateException e) {e.printStackTrace();}
	}

	@Override
	public void sauverImages(int pk, String imageSmall,
			String imageMedium, String imageLarge, String imageExtraLarge,
			String imageMega) {
		requeteSQL="INSERT INTO IMAGES(cle_primaire,imageSmall,imageMedium,imageLarge,imageExtraLarge,imageMega)"+
				"values("+pk+","+imageSmall+","+imageMedium+","+imageLarge+","+imageExtraLarge+","+imageMega+")";
		try {SQLViaJDBC.executerRequeteSansRetour(requeteSQL);} 
		catch (UpdateException e) {e.printStackTrace();}
	}

	@Override
	public void sauverSimilartist(Integer artiste1, Integer artiste2) {
		requeteSQL="INSERT INTO ARTISTES_SIMILAIRES(artiste1,artiste2)"+
				"values("+artiste1+","+artiste2+")";
		try {SQLViaJDBC.executerRequeteSansRetour(requeteSQL);} 
		catch (UpdateException e) {e.printStackTrace();}
	}

	@Override
	public void sauverChanson(int pk, int coord_chanson, Double duree,
			int pkImages, int pkAudimat, int pkWiki, int pkArtiste) {
		requeteSQL="INSERT INTO CHANSON(cle_primaire,id_name_url,duree,images,audimat,wiki,artiste)"+
				"values("+pk+","+coord_chanson+","+duree+","+pkImages+","+pkAudimat+","+pkWiki+","+pkArtiste+")";
		try {SQLViaJDBC.executerRequeteSansRetour(requeteSQL);} 
		catch (UpdateException e) {e.printStackTrace();}
	}

	@Override
	public void sauverArtisteTag(Integer artiste, Integer tag) {
		requeteSQL="INSERT INTO CORRESP_ARTISTE_TAG(artiste,tag)"+
				"values("+artiste+","+tag+")";
		try {SQLViaJDBC.executerRequeteSansRetour(requeteSQL);} 
		catch (UpdateException e) {e.printStackTrace();}
	}

	@Override
	public void sauverChansonTag(Integer chanson, Integer tag) {
		requeteSQL="INSERT INTO CORRESP_CHANSON_TAG(chanson,tag)"+
				"values("+chanson+","+tag+")";
		try {SQLViaJDBC.executerRequeteSansRetour(requeteSQL);} 
		catch (UpdateException e) {e.printStackTrace();}
	}

	@Override
	public void sauverTag(int pk, int coord_tag, Double reach, Double tagging,
			int pkWiki) {
		requeteSQL="INSERT INTO TAG(cle_primaire,id_name_url,reach,taggings,wiki)"+
				"values("+pk+","+coord_tag+","+reach+","+tagging+","+pkWiki+")";
		try {SQLViaJDBC.executerRequeteSansRetour(requeteSQL);} 
		catch (UpdateException e) {e.printStackTrace();}
	}

	@Override
	public void sauverCoord(int pk, String id, String name, String url) {
		requeteSQL="INSERT INTO ID_NAME_URL(cle_primaire,id,name,url)"+
				"values("+pk+","+id+","+name+","+url+")";
		try {SQLViaJDBC.executerRequeteSansRetour(requeteSQL);} 
		catch (UpdateException e) {e.printStackTrace();}
	}

	@Override
	public void sauverChansonAlbum(Integer album, Integer chanson) {
		requeteSQL="INSERT INTO CORRESP_CHANSON_ALBUM(album,chanson)"+
				"values("+album+","+chanson+")";
		try {SQLViaJDBC.executerRequeteSansRetour(requeteSQL);} 
		catch (UpdateException e) {e.printStackTrace();}
	}

	@Override
	public void sauverAlbum(int pk, int coord_album, Date date_sortie,
			int pkImages, int pkAudimat, int pkWiki, Integer artiste) {
		//FIXME gérer la date JAVA vs SQL
		requeteSQL="INSERT INTO ALBUM(cle_primaire,id_name_url,date_sortie,images,audimat,wiki,artiste)"+
				"values("+pk+","+coord_album+","+date_sortie+","+pkImages+","+pkAudimat+","+pkWiki+","+artiste+")";
		try {SQLViaJDBC.executerRequeteSansRetour(requeteSQL);} 
		catch (UpdateException e) {e.printStackTrace();}
	}
}
