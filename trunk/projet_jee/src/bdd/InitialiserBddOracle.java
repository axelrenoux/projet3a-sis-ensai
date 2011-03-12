package bdd;

import bdd.exceptions.UpdateException;

public class InitialiserBddOracle {
	public static void init() throws UpdateException{
		String requeteCreationTables=
		"create table AUDIMAT(id_audimat INTEGER PRIMARY KEY,"+
		"				listeners DOUBLE,"+
		"				playcount DOUBLE);"+
		"create table IMAGES(id_images INTEGER PRIMARY KEY,"+
		"				imageSmall VARCHAR2,"+
		"				imageMedium VARCHAR2,"+
		"				imageLarge VARCHAR2,"+
		"				imageExtraLarge VARCHAR2,"+
		"				imageMega VARCHAR2);"+
		"create table WIKI(id_wiki VARCHAR2 PRIMARY KEY,"+
		"				datePublication DATE,"+
		"				resume VARCHAR2,"+
		"				contenu VARCHAR2);"+
		"create table CHANSON(id_chanson VARCHAR2 PRIMARY KEY,"+
		"					name VARCHAR2,"+
		"					url VARCHAR2,"+
		"					duree DOUBLE,"+
		"					id_images INTEGER references IMAGES(id_images),"+
		"					id_audimat INTEGER references AUDIMAT(id_audimat),"+
		"					idWiki VARCHAR2 references WIKI(id_wiki));"+
		"create table ALBUM(id_album VARCHAR2 PRIMARY KEY,"+
		"					name VARCHAR2,"+
		"					url VARCHAR2,"+
		"					date DATE,"+
		"					id_images INTEGER references IMAGES(id_images),"+
		"					id_audimat INTEGER references AUDIMAT(id_audimat),"+
		"					id_wiki VARCHAR2 references WIKI(id_wiki));"+
		"create table TAG(id_tag VARCHAR2 PRIMARY KEY,"+
		"					name VARCHAR2,"+
		"					url VARCHAR2,"+
		"					reach DOUBLE,"+
		"					taggings DOUBLE);"+
		"create table ARTISTE(id_artiste VARCHAR2 PRIMARY KEY,"+
		"					name VARCHAR2,"+
		"					url VARCHAR2,"+
		"					id_images INTEGER references IMAGES(id_images),"+
		"					id_audimat INTEGER references AUDIMAT(id_audimat),"+
		"					id_wiki STRING references WIKI(id_wiki));"+
		"create table ARTISTES_SIMILAIRES(id_artiste1 VARCHAR2 references ARTISTE(id_artiste),"+
		"								id_artiste2 VARCHAR2 references ARTISTE(id_artiste));"+
		"create table CORRESP_ARTISTE_TAG(id_artiste VARCHAR2 references ARTISTE(id_artiste),"+
		"								id_tag VARCHAR2 references TAG(id_tag));"+
		"create table CORRESP_CHANSON_TAG(id_chanson VARCHAR2 references CHANSON(id_chanson),"+
		"								id_tag VARCHAR2 references TAG(id_tag));"+
		"create table CORRESP_ALBUM_TAG(id_tag VARCHAR2 references TAG(id_tag),"+
		"								id_album VARCHAR2 references ALBUM(id_album));"+
		"create table CORRESP_CHANSON_ALBUM(id_album VARCHAR2 references ALBUM(id_album),"+
		"								id_chanson VARCHAR2 references CHANSON(id_chanson));";
		SQLViaJDBC.executerRequeteSansRetour(requeteCreationTables);
	}	
}