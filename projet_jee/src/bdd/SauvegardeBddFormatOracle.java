package bdd;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import bdd.exceptions.ConnectionException;
import bdd.exceptions.UpdateException;

public class SauvegardeBddFormatOracle extends SauvegardeUnFormatPourLaBdd{
	private String requeteSQL;
	private PrintWriter fluxSortie;

	@Override
	public void ecrireEnTete() {
		try {fluxSortie = new PrintWriter(new FileWriter("requetesSQL.txt"));} 
		catch (IOException e1) {e1.printStackTrace();}
		try {SQLViaJDBC.connecter();}
		catch (ConnectionException e) {e.printStackTrace();}
		creerTables();
	}

	@Override
	public void ajouterLigne(String ligne) {
		//On envoie la requête au serveur Oracle en local
		try {SQLViaJDBC.executerRequeteSansRetour(ligne);} 
		catch (UpdateException e) {e.printStackTrace();}
		//Et on l'écrit en parallèle dans un fichier txt partagé, pour que les autres aussi puissent peupler leur bdd
		fluxSortie.println(ligne);
	}
	
	@Override
	public void ecrireConclusion() {
		try {SQLViaJDBC.fermerBDD();}
		catch (ConnectionException e) {e.printStackTrace();}
		fluxSortie.close();
	}

	@Override
	public void sauverWiki(int pk, String id_wiki, Date datePublication,
			String resume, String contenu) {
		//FIXME gérer la date JAVA vs SQL
		requeteSQL="INSERT INTO WIKI(cle_primaire, id_wiki, datePublication, resume, contenu)"+
					"values("+pk+","+id_wiki+","+datePublication+","+resume+","+contenu+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	public void sauverAudimat(int pk, double listeners, double playcount) {
		requeteSQL="INSERT INTO AUDIMAT(cle_primaire, listeners, playcount)"+
					"values("+pk+","+listeners+","+playcount+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	public void sauverArtiste(int pk, int coord_artiste,
			int images, int audimat, int wiki) {
		requeteSQL="INSERT INTO ARTISTE(cle_primaire,id_name_url,images,audimat,wiki)"+
				"values("+pk+","+coord_artiste+","+images+","+audimat+","+wiki+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	public void sauverImages(int pk, String imageSmall,
			String imageMedium, String imageLarge, String imageExtraLarge,
			String imageMega) {
		requeteSQL="INSERT INTO IMAGES(cle_primaire,imageSmall,imageMedium,imageLarge,imageExtraLarge,imageMega)"+
				"values("+pk+","+imageSmall+","+imageMedium+","+imageLarge+","+imageExtraLarge+","+imageMega+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	public void sauverSimilartist(Integer artiste1, Integer artiste2) {
		requeteSQL="INSERT INTO ARTISTES_SIMILAIRES(artiste1,artiste2)"+
				"values("+artiste1+","+artiste2+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	public void sauverChanson(int pk, int coord_chanson, Double duree,
			int pkImages, int pkAudimat, int pkWiki, int pkArtiste) {
		requeteSQL="INSERT INTO CHANSON(cle_primaire,id_name_url,duree,images,audimat,wiki,artiste)"+
				"values("+pk+","+coord_chanson+","+duree+","+pkImages+","+pkAudimat+","+pkWiki+","+pkArtiste+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	public void sauverArtisteTag(Integer artiste, Integer tag) {
		requeteSQL="INSERT INTO CORRESP_ARTISTE_TAG(artiste,tag)"+
				"values("+artiste+","+tag+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	public void sauverChansonTag(Integer chanson, Integer tag) {
		requeteSQL="INSERT INTO CORRESP_CHANSON_TAG(chanson,tag)"+
				"values("+chanson+","+tag+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	public void sauverTag(int pk, int coord_tag, Double reach, Double tagging,
			int pkWiki) {
		requeteSQL="INSERT INTO TAG(cle_primaire,id_name_url,reach,taggings,wiki)"+
				"values("+pk+","+coord_tag+","+reach+","+tagging+","+pkWiki+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	public void sauverCoord(int pk, String id, String name, String url) {
		requeteSQL="INSERT INTO ID_NAME_URL(cle_primaire,id,name,url)"+
				"values("+pk+","+id+","+name+","+url+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	public void sauverChansonAlbum(Integer album, Integer chanson) {
		requeteSQL="INSERT INTO CORRESP_CHANSON_ALBUM(album,chanson)"+
				"values("+album+","+chanson+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	public void sauverAlbum(int pk, int coord_album, Date date_sortie,
			int pkImages, int pkAudimat, int pkWiki, Integer artiste) {
		//FIXME gérer la date JAVA vs SQL
		requeteSQL="INSERT INTO ALBUM(cle_primaire,id_name_url,date_sortie,images,audimat,wiki,artiste)"+
				"values("+pk+","+coord_album+","+date_sortie+","+pkImages+","+pkAudimat+","+pkWiki+","+artiste+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	public void creerTables(){
		ajouterLigne("drop table ARTISTES_SIMILAIRES");
		ajouterLigne("drop table CORRESP_ARTISTE_TAG");
		ajouterLigne("drop table CORRESP_CHANSON_TAG");
		ajouterLigne("drop table CORRESP_ALBUM_TAG");
		ajouterLigne("drop table CORRESP_CHANSON_ALBUM");
		
		ajouterLigne("drop table CHANSON");
		ajouterLigne("drop table ALBUM");
		ajouterLigne("drop table TAG");
		
		ajouterLigne("drop table ARTISTE");
		
		ajouterLigne("drop table AUDIMAT");
		ajouterLigne("drop table IMAGES");
		ajouterLigne("drop table WIKI");
		ajouterLigne("drop table ID_NAME_URL");
		
		ajouterLigne(
		"create table ID_NAME_URL(cle_primaire INTEGER,"+
						"id VARCHAR2(256),"+
						"name VARCHAR2(128),"+
						"url VARCHAR2(256),"+
						"PRIMARY KEY(cle_primaire))");
		
		ajouterLigne(
		"create table AUDIMAT(cle_primaire INTEGER,"+
						"listeners INTEGER,"+
						"playcount INTEGER," +
						"PRIMARY KEY(cle_primaire))");
		ajouterLigne(
		"create table IMAGES(cle_primaire INTEGER,"+
						"imageSmall VARCHAR2(256),"+
						"imageMedium VARCHAR2(256),"+
						"imageLarge VARCHAR2(256),"+
						"imageExtraLarge VARCHAR2(256),"+
						"imageMega VARCHAR2(256)," +
						"PRIMARY KEY(cle_primaire))");
		ajouterLigne(
		"create table WIKI(cle_primaire INTEGER,"+
						"id_wiki VARCHAR2(256),"+
						"datePublication date,"+
						"resume VARCHAR2(512),"+
						"contenu VARCHAR2(2048)," +
						"PRIMARY KEY(cle_primaire))");
		ajouterLigne(
		"create table ARTISTE(cle_primaire INTEGER,"+
							"id_name_url INTEGER references ID_NAME_URL(cle_primaire)," +
							"images INTEGER references IMAGES(cle_primaire),"+
							"audimat INTEGER references AUDIMAT(cle_primaire),"+
							"wiki INTEGER references WIKI(cle_primaire)," +
							"PRIMARY KEY(cle_primaire))");
		ajouterLigne(
		"create table CHANSON(cle_primaire INTEGER,"+
							"id_name_url INTEGER references ID_NAME_URL(cle_primaire)," +
							"duree FLOAT,"+
							"images INTEGER references IMAGES(cle_primaire),"+
							"audimat INTEGER references AUDIMAT(cle_primaire),"+
							"wiki INTEGER references WIKI(cle_primaire),"+
							"artiste INTEGER references ARTISTE(cle_primaire),"+
							"PRIMARY KEY(cle_primaire))");
		ajouterLigne(
		"create table ALBUM(cle_primaire INTEGER,"+
							"id_name_url INTEGER references ID_NAME_URL(cle_primaire)," +
							"date_sortie date,"+//le mot-clé 'date' étant réservé, notre colonne ne peut pas s'appeler ainsi
							"images INTEGER references IMAGES(cle_primaire),"+
							"audimat INTEGER references AUDIMAT(cle_primaire),"+
							"wiki INTEGER references WIKI(cle_primaire)," +
							"artiste INTEGER references ARTISTE(cle_primaire),"+
							"PRIMARY KEY(cle_primaire))");
		ajouterLigne(
		"create table TAG(cle_primaire INTEGER,"+
							"id_name_url INTEGER references ID_NAME_URL(cle_primaire)," +
							"reach FLOAT,"+
							"taggings FLOAT," +
							"wiki INTEGER references WIKI(cle_primaire),"+
							"PRIMARY KEY(cle_primaire))");
		ajouterLigne(
		"create table ARTISTES_SIMILAIRES(artiste1 INTEGER references ARTISTE(cle_primaire),"+
										"artiste2 INTEGER references ARTISTE(cle_primaire))");
		ajouterLigne(
		"create table CORRESP_ARTISTE_TAG(artiste INTEGER references ARTISTE(cle_primaire),"+
										"tag INTEGER references TAG(cle_primaire))");
		ajouterLigne(
		"create table CORRESP_CHANSON_TAG(chanson INTEGER references CHANSON(cle_primaire),"+
										"tag INTEGER references TAG(cle_primaire))");
		ajouterLigne(
		"create table CORRESP_ALBUM_TAG(tag INTEGER references TAG(cle_primaire),"+
										"album INTEGER references ALBUM(cle_primaire))");
		ajouterLigne(
		"create table CORRESP_CHANSON_ALBUM(album INTEGER references ALBUM(cle_primaire),"+
										"chanson INTEGER references CHANSON(cle_primaire))");
	}
}
