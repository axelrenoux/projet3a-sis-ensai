package bdd.sauvegarde_controlee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import bdd.sqlviajdbc.ControlAccesSQLViaJDBC;

import exceptions.ConnectionException;
import exceptions.UpdateException;

/**
 * Mettre les String entre guillemets et vérifier le format des dates a été vérifié au niveau du ControleSauvegardeBddFormatOracle
 */
class SauvegardeBddFormatOracle extends SauvegardeUnFormatPourLaBdd{
	private String requeteSQL;
	private PrintWriter fluxSortie;

	protected SauvegardeBddFormatOracle(){
		super();
	}
	
	@Override
	protected void ecrireEnTete(boolean lesTablesExistent) {
		if(lesTablesExistent){
			conserverDonneesExistantes();
		}
		try {fluxSortie = new PrintWriter(new FileWriter("requetesSQL.txt"));} 
		catch (IOException e1) {e1.printStackTrace();}
		try {ControlAccesSQLViaJDBC.connecter();}
		catch (ConnectionException e) {e.printStackTrace();}
		if(lesTablesExistent){
			reecrireDonneesExistantes();
		}
		else{
			creerTables();
		}
	}

	@Override
	protected void ajouterLigne(String ligne) {
		//On envoie la requête au serveur Oracle en local
		try {ControlAccesSQLViaJDBC.executerRequeteSansRetour(ligne);} 
		catch (UpdateException e) {e.printStackTrace();}
		//Et on l'écrit en parallèle dans un fichier txt partagé, pour que les autres aussi puissent peupler leur bdd
		fluxSortie.println(ligne);
	}
	
	@Override
	protected void ecrireConclusion() {
		try {ControlAccesSQLViaJDBC.fermerBDD();}
		catch (ConnectionException e) {e.printStackTrace();}
		fluxSortie.close();
	}
	
	@Override
	protected void sauverWiki(String pk, String id_wiki, String dateprotectedation,
			String resume, String contenu) {
		requeteSQL="INSERT INTO WIKI(cle_primaire, id_wiki, dateprotectedation, resume, contenu)"+
				"values("+pk+","+id_wiki+","+dateprotectedation+","+resume+","+contenu+")";
		ajouterLigne(requeteSQL);
		
	}

	@Override
	protected void sauverAudimat(String pk, String listeners, String playcount) {
		requeteSQL="INSERT INTO AUDIMAT(cle_primaire, listeners, playcount)"+
					"values("+pk+","+listeners+","+playcount+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	protected void sauverArtiste(String pk, String coord_artiste,
			String images, String audimat, String wiki) {
		requeteSQL="INSERT INTO ARTISTE(cle_primaire,id_name_url,images,audimat,wiki)"+
				"values("+pk+","+coord_artiste+","+images+","+audimat+","+wiki+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	protected void sauverImages(String pk, String imageSmall,
			String imageMedium, String imageLarge, String imageExtraLarge,
			String imageMega) {
		requeteSQL="INSERT INTO IMAGES(cle_primaire,imageSmall,imageMedium,imageLarge,imageExtraLarge,imageMega)"+
				"values("+pk+","+imageSmall+","+imageMedium+","+imageLarge+","+imageExtraLarge+","+imageMega+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	protected void sauverSimilartist(String artiste1, String artiste2) {
		requeteSQL="INSERT INTO ARTISTES_SIMILAIRES(artiste1,artiste2)"+
				"values("+artiste1+","+artiste2+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	protected void sauverChanson(String pk, String coord_chanson, String duree,
			String pkImages, String pkAudimat, String pkWiki, String pkArtiste) {
		requeteSQL="INSERT INTO CHANSON(cle_primaire,id_name_url,duree,images,audimat,wiki,artiste)"+
				"values("+pk+","+coord_chanson+","+duree+","+pkImages+","+pkAudimat+","+pkWiki+","+pkArtiste+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	protected void sauverArtisteTag(String artiste, String tag) {
		requeteSQL="INSERT INTO CORRESP_ARTISTE_TAG(artiste,tag)"+
				"values("+artiste+","+tag+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	protected void sauverChansonTag(String chanson, String tag) {
		requeteSQL="INSERT INTO CORRESP_CHANSON_TAG(chanson,tag)"+
				"values("+chanson+","+tag+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	protected void sauverTag(String pk, String coord_tag, String reach, String tagging,
			String pkWiki) {
		requeteSQL="INSERT INTO TAG(cle_primaire,id_name_url,reach,taggings,wiki)"+
				"values("+pk+","+coord_tag+","+reach+","+tagging+","+pkWiki+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	protected void sauverCoord(String pk, String id, String name, String url) {
		requeteSQL="INSERT INTO ID_NAME_URL(cle_primaire,id,name,url)"+
				"values("+pk+","+id+","+name+","+url+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	protected void sauverChansonAlbum(String album, String chanson) {
		requeteSQL="INSERT INTO CORRESP_CHANSON_ALBUM(album,chanson)"+
				"values("+album+","+chanson+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	protected void sauverAlbum(String pk, String coord_album, String date_sortie,
			String pkImages, String pkAudimat, String pkWiki, String artiste) {
		requeteSQL="INSERT INTO ALBUM(cle_primaire,id_name_url,date_sortie,images,audimat,wiki,artiste)"+
				"values("+pk+","+coord_album+","+date_sortie+","+pkImages+","+pkAudimat+","+pkWiki+","+artiste+")";
		ajouterLigne(requeteSQL);
	}

	@Override
	protected void creerTables(){
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
						"id VARCHAR2(4000),"+
						"name VARCHAR2(4000),"+
						"url VARCHAR2(4000),"+
						"PRIMARY KEY(cle_primaire))");
		
		ajouterLigne(
		"create table AUDIMAT(cle_primaire INTEGER,"+
						"listeners INTEGER,"+
						"playcount INTEGER," +
						"PRIMARY KEY(cle_primaire))");
		ajouterLigne(
		"create table IMAGES(cle_primaire INTEGER,"+
						"imageSmall VARCHAR2(4000),"+
						"imageMedium VARCHAR2(4000),"+
						"imageLarge VARCHAR2(4000),"+
						"imageExtraLarge VARCHAR2(4000),"+
						"imageMega VARCHAR2(4000)," +
						"PRIMARY KEY(cle_primaire))");
		ajouterLigne(
		"create table WIKI(cle_primaire INTEGER,"+
						"id_wiki VARCHAR2(256),"+
						"dateprotectedation date,"+
						"resume VARCHAR2(4000),"+
						"contenu VARCHAR2(4000)," +
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

	@Override
	protected void conserverDonneesExistantes() {
		try {
			String ligneDevantEtreRecopiee;
			PrintWriter fluxSortie2 = new PrintWriter(new FileWriter("requetesSQLinter.txt"));
			BufferedReader fluxEntree = new BufferedReader(new FileReader("requetesSQL.txt"));
			while (fluxEntree.ready()) {
				ligneDevantEtreRecopiee = fluxEntree.readLine();
				fluxSortie2.println(ligneDevantEtreRecopiee);
			}
			fluxEntree.close();
			fluxSortie2.close(); 
		}catch (IOException e1) {e1.printStackTrace();}
	}

	@Override
	protected void reecrireDonneesExistantes() {
		try {
			String ligneDevantEtreRecopiee;
			BufferedReader fluxEntree2 = new BufferedReader(new FileReader("requetesSQLinter.txt"));
			while (fluxEntree2.ready()) {
				ligneDevantEtreRecopiee = fluxEntree2.readLine();
				fluxSortie.println(ligneDevantEtreRecopiee);
			}
			fluxEntree2.close();
		}catch (IOException e1) {e1.printStackTrace();}
	}
}
