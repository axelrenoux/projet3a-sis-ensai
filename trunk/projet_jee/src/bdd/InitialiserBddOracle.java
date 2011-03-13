package bdd;

import java.util.LinkedList;
import java.util.List;

import bdd.exceptions.ConnectionException;
import bdd.exceptions.UpdateException;

public class InitialiserBddOracle {
	//Oracle n'accepte pas des string comme primary key
	public static void creerTables(){
		List<String> requetes=new LinkedList<String>();
		requetes.add("drop table ARTISTES_SIMILAIRES");
		requetes.add("drop table CORRESP_ARTISTE_TAG");
		requetes.add("drop table CORRESP_CHANSON_TAG");
		requetes.add("drop table CORRESP_ALBUM_TAG");
		requetes.add("drop table CORRESP_CHANSON_ALBUM");
		
		requetes.add("drop table CHANSON");
		requetes.add("drop table ALBUM");
		requetes.add("drop table TAG");
		
		requetes.add("drop table ARTISTE");
		
		requetes.add("drop table AUDIMAT");
		requetes.add("drop table IMAGES");
		requetes.add("drop table WIKI");
		requetes.add("drop table ID_NAME_URL");
		
		requetes.add(
		"create table ID_NAME_URL(cle_primaire INTEGER,"+
						"id VARCHAR2(256),"+
						"name VARCHAR2(128),"+
						"url VARCHAR2(256),"+
						"PRIMARY KEY(cle_primaire))");
		
		requetes.add(
		"create table AUDIMAT(cle_primaire INTEGER,"+
						"listeners INTEGER,"+
						"playcount INTEGER," +
						"PRIMARY KEY(cle_primaire))");
		requetes.add(
		"create table IMAGES(cle_primaire INTEGER,"+
						"imageSmall VARCHAR2(256),"+
						"imageMedium VARCHAR2(256),"+
						"imageLarge VARCHAR2(256),"+
						"imageExtraLarge VARCHAR2(256),"+
						"imageMega VARCHAR2(256)," +
						"PRIMARY KEY(cle_primaire))");
		requetes.add(
		"create table WIKI(cle_primaire INTEGER,"+
						"id_wiki VARCHAR2(256),"+
						"datePublication date,"+
						"resume VARCHAR2(512),"+
						"contenu VARCHAR2(2048)," +
						"PRIMARY KEY(cle_primaire))");
		requetes.add(
		"create table ARTISTE(cle_primaire INTEGER,"+
							"id_name_url INTEGER references ID_NAME_URL(cle_primaire)," +
							"images INTEGER references IMAGES(cle_primaire),"+
							"audimat INTEGER references AUDIMAT(cle_primaire),"+
							"wiki INTEGER references WIKI(cle_primaire)," +
							"PRIMARY KEY(cle_primaire))");
		requetes.add(
		"create table CHANSON(cle_primaire INTEGER,"+
							"id_name_url INTEGER references ID_NAME_URL(cle_primaire)," +
							"duree FLOAT,"+
							"images INTEGER references IMAGES(cle_primaire),"+
							"audimat INTEGER references AUDIMAT(cle_primaire),"+
							"wiki INTEGER references WIKI(cle_primaire),"+
							"artiste INTEGER references ARTISTE(cle_primaire),"+
							"PRIMARY KEY(cle_primaire))");
		requetes.add(
		"create table ALBUM(cle_primaire INTEGER,"+
							"id_name_url INTEGER references ID_NAME_URL(cle_primaire)," +
							"date_sortie date,"+//le mot-clé 'date' étant réservé, notre colonne ne peut pas s'appeler ainsi
							"images INTEGER references IMAGES(cle_primaire),"+
							"audimat INTEGER references AUDIMAT(cle_primaire),"+
							"wiki INTEGER references WIKI(cle_primaire)," +
							"artiste INTEGER references ARTISTE(cle_primaire),"+
							"PRIMARY KEY(cle_primaire))");
		requetes.add(
		"create table TAG(cle_primaire INTEGER,"+
							"id_name_url INTEGER references ID_NAME_URL(cle_primaire)," +
							"reach FLOAT,"+
							"taggings FLOAT," +
							"wiki INTEGER references WIKI(cle_primaire),"+
							"PRIMARY KEY(cle_primaire))");
		requetes.add(
		"create table ARTISTES_SIMILAIRES(artiste1 INTEGER references ARTISTE(cle_primaire),"+
										"artiste2 INTEGER references ARTISTE(cle_primaire))");
		requetes.add(
		"create table CORRESP_ARTISTE_TAG(artiste INTEGER references ARTISTE(cle_primaire),"+
										"tag INTEGER references TAG(cle_primaire))");
		requetes.add(
		"create table CORRESP_CHANSON_TAG(chanson INTEGER references CHANSON(cle_primaire),"+
										"tag INTEGER references TAG(cle_primaire))");
		requetes.add(
		"create table CORRESP_ALBUM_TAG(tag INTEGER references TAG(cle_primaire),"+
										"album INTEGER references ALBUM(cle_primaire))");
		requetes.add(
		"create table CORRESP_CHANSON_ALBUM(album INTEGER references ALBUM(cle_primaire),"+
										"chanson INTEGER references CHANSON(cle_primaire))");
		try{
			SQLViaJDBC.connecter();
			for(String requeteCourante:requetes){
				try {
					SQLViaJDBC.executerRequeteSansRetour(requeteCourante);
					System.out.println("Requête executée : "+requeteCourante);
				} catch (UpdateException e) {
					System.out.println("Echec de la requête : "+requeteCourante);
				}
			}
			SQLViaJDBC.fermerBDD();	
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
	}	
}