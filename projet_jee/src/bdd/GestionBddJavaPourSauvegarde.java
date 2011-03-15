package bdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import metier.Album;
import metier.Artiste;
import metier.Chanson;
import metier.Tag;
import metier.Wiki;
import controleur.Controleur;

public class GestionBddJavaPourSauvegarde {
	//La classe gérant le format sous lequel on va enregistrer la BDD
	private static ControleSauvegardeUnFormatPourLaBdd formatSauv;
	
	//Nos données d'origine en Java
	private static List<Artiste> artistes=new ArrayList<Artiste>();
	private static List<Chanson> chansons=new ArrayList<Chanson>();
	private static List<Album> albums=new ArrayList<Album>();
	
	//Des maps de clés primaire : savoir si on a déjà entré un artiste(etc...), si oui récupérer sa clé primaire, sinon le créer
	private static Map<Artiste,Integer> clesPrimairesArtistes=new HashMap<Artiste,Integer>();
	private static Map<Album,Integer> clesPrimairesAlbums=new HashMap<Album,Integer>();
	private static Map<Chanson, Integer> clesPrimairesChansons=new HashMap<Chanson,Integer>();
	private static Map<Tag, Integer> clesPrimairesTags=new HashMap<Tag,Integer>();
	
	//Variable servant à incrémenter les clés primaires, pour s'assurer qu'elles seront toutes différentes
	private static int primarykey=0;
	
	private static int incrementerClesPrimaires() {
		primarykey=primarykey+1;
		return primarykey;
	}
	
	private static void init(){
		for(Artiste a:Controleur.getInstanceuniquecontroleur().getListeArtistes().values()){
			artistes.add(a);
		}
		for(Album a:Controleur.getInstanceuniquecontroleur().getListeAlbums().values()){
			albums.add(a);
		}
		for(Chanson a:Controleur.getInstanceuniquecontroleur().getListeChansons().values()){
			chansons.add(a);
		}
	}
	
	public static void decomposerAvantSauvegardeGereePar(ControleSauvegardeUnFormatPourLaBdd gestionnaireDeFormatdeSauvegarde){
		init();
		formatSauv=gestionnaireDeFormatdeSauvegarde;
		formatSauv.ecrireEnTete();
		for(Artiste lArtiste:artistes){//Pour chaque artiste
			sauver(lArtiste);
		}
		for(Chanson laChanson:chansons){//Pour chaque chanson
			sauver(laChanson);//Pré-sauvegarde seulement : au cas où l'on verrait s'ajouter d'autres chansons par la suite
		}
		for(Album lAlbum:albums){
			sauver(lAlbum);
		}
		formatSauv.ecrireConclusion();
	}

	private static void sauver(Chanson laChanson) {
		if(laChanson==null){
			laChanson=new Chanson();
		}
		if(!clesPrimairesChansons.containsKey(laChanson)){//Pour eviter les doublons
			//On attribue une clé à la chanson
			int clefCetteChanson=incrementerClesPrimaires();
			clesPrimairesChansons.put(laChanson,clefCetteChanson);
			
			//On sauvegarde le wiki
			Wiki leWiki=laChanson.getWiki();
			if(leWiki==null){
				leWiki=new Wiki();
			}
			int pkWiki=incrementerClesPrimaires();
			formatSauv.sauverWiki(pkWiki,
					String.valueOf(pkWiki),
					leWiki.getDatePublication(),
					leWiki.getResume(),
					leWiki.getContenu());
			/*WIKI(cle_primaire INTEGER,
					id_wiki VARCHAR2(256),
					datePublication date,
					resume VARCHAR2(512),
					contenu VARCHAR2(2048))*/
			
			//On sauvegarde l'audimat
			int pkAudimat=incrementerClesPrimaires();
			formatSauv.sauverAudimat(pkAudimat,
					laChanson.getListeners(),
					laChanson.getPlaycount());
			/*AUDIMAT(cle_primaire INTEGER,
						listeners INTEGER,
						playcount INTEGER)*/
			
			//On sauvegarde les images
			int pkImages=incrementerClesPrimaires();
			formatSauv.sauverImages(pkImages,
					laChanson.getImageSmall(),
					laChanson.getImageMedium(),
					laChanson.getImageLarge(),
					laChanson.getImageExtraLarge(),
					laChanson.getImageMega());
			/*IMAGES(cle_primaire INTEGER,
					imageSmall VARCHAR2(256),
					imageMedium VARCHAR2(256),
					imageLarge VARCHAR2(256),
					imageExtraLarge VARCHAR2(256),
					imageMega VARCHAR2(256))*/
			
			//On sauvegarde l'artiste
			Artiste lArtiste=laChanson.getArtiste();
			if(lArtiste==null){
				lArtiste=new Artiste();
			}
			sauver(lArtiste);
			
			
			//On sauvegarde les coordonnées de la chanson
			int pkCoord=incrementerClesPrimaires();
			formatSauv.sauverCoord(pkCoord,
							laChanson.getID(),
							laChanson.getName(),
							laChanson.getUrl());
			/*ID_NAME_URL(cle_primaire INTEGER,
			 			id VARCHAR2(256),
						name VARCHAR2(128),
						url VARCHAR2(256))*/
			
			//on sauvegarde la chanson
			formatSauv.sauverChanson(clefCetteChanson,
									pkCoord,
									laChanson.getDuree(),
									pkImages,
									pkAudimat,
									pkWiki,
									clesPrimairesArtistes.get(lArtiste));
			/*CHANSON(cle_primaire INTEGER,
					id_chanson VARCHAR2(256),
					name VARCHAR2(128),
					url VARCHAR2(256),
					duree FLOAT,
					images INTEGER references IMAGES(cle_primaire),
					audimat INTEGER references AUDIMAT(cle_primaire),
					wiki INTEGER references WIKI(cle_primaire),
					artiste INTEGER references ARTISTE(cle_primaire))*/
			if(laChanson.getToptags()!=null){
				for(Tag leTag:laChanson.getToptags()){
					if(leTag==null){
						leTag=new Tag();
					}
					sauver(leTag);
					formatSauv.sauverChansonTag(clefCetteChanson,
												clesPrimairesTags.get(leTag));
					/*CORRESP_CHANSON_TAG(chanson INTEGER references CHANSON(cle_primaire),
										tag INTEGER references TAG(cle_primaire))*/
				}//endFor
			}//endIf
			if(laChanson.getAlbums()!=null){
				for(Album lAlbum:laChanson.getAlbums()){
					if(lAlbum==null){
						lAlbum=new Album();
					}
					sauver(lAlbum);
					formatSauv.sauverChansonAlbum(clesPrimairesAlbums.get(lAlbum),
												clefCetteChanson);
					/*CORRESP_CHANSON_ALBUM(album INTEGER references ALBUM(cle_primaire),
											chanson INTEGER references CHANSON(cle_primaire))*/
				}//endFor
			}//endIf
		}//endIf	
	}//finSauver(Chanson)
	

	private static void sauver(Artiste lArtiste){
		if(lArtiste==null){
			lArtiste=new Artiste();
		}
		if(!clesPrimairesArtistes.containsKey(lArtiste)){//Pour eviter les doublons
			//On attribue une clé à l'artiste
			int clefCetArtiste=incrementerClesPrimaires();
			clesPrimairesArtistes.put(lArtiste,clefCetArtiste);
			//Pour pouvoir retrouver l'artiste pour les correspondances avec albums etc
			//On sauvegarde le wiki
			Wiki leWiki=lArtiste.getWiki();
			if(leWiki==null){
				leWiki=new Wiki();
			}
			int pkWiki=incrementerClesPrimaires();
			formatSauv.sauverWiki(pkWiki,
					String.valueOf(pkWiki),
					leWiki.getDatePublication(),
					leWiki.getResume(),
					leWiki.getContenu());
			/*WIKI(cle_primaire INTEGER,
					id_wiki VARCHAR2(256),
					datePublication date,
					resume VARCHAR2(512),
					contenu VARCHAR2(2048))*/
			
			//On sauvegarde l'audimat
			int pkAudimat=incrementerClesPrimaires();
			formatSauv.sauverAudimat(pkAudimat,
					lArtiste.getListeners(),
					lArtiste.getPlaycount());
			/*AUDIMAT(cle_primaire INTEGER,
						listeners INTEGER,
						playcount INTEGER)*/
			
			//On sauvegarde les images
			int pkImages=incrementerClesPrimaires();
			formatSauv.sauverImages(pkImages,
					lArtiste.getImageSmall(),
					lArtiste.getImageMedium(),
					lArtiste.getImageLarge(),
					lArtiste.getImageExtraLarge(),
					lArtiste.getImageMega());
			/*IMAGES(cle_primaire INTEGER,
					imageSmall VARCHAR2(256),
					imageMedium VARCHAR2(256),
					imageLarge VARCHAR2(256),
					imageExtraLarge VARCHAR2(256),
					imageMega VARCHAR2(256))*/
			
			//On sauvegarde les coordonnées de la chanson
			int pkCoord=incrementerClesPrimaires();
			formatSauv.sauverCoord(pkCoord,
							lArtiste.getID(),
							lArtiste.getName(),
							lArtiste.getUrl());
			/*ID_NAME_URL(cle_primaire INTEGER,
			 			id VARCHAR2(256),
						name VARCHAR2(128),
						url VARCHAR2(256))*/
			
			//Et on sauvegarde l'artiste
			formatSauv.sauverArtiste(clefCetArtiste,
									pkCoord,
									pkImages,
									pkAudimat,
									pkWiki);
			/*ARTISTE(cle_primaire INTEGER,
					id_artiste VARCHAR2(256),
					name VARCHAR2(128),
					url VARCHAR2(256),
					images INTEGER references IMAGES(cle_primaire),
					audimat INTEGER references AUDIMAT(cle_primaire),
					wiki INTEGER references WIKI(cle_primaire))*/
			if(lArtiste.getArtistesSimilaires()!=null){
				for(Artiste autreArtiste:lArtiste.getArtistesSimilaires()){
					if(autreArtiste==null){
						autreArtiste=new Artiste();
					}
					sauver(autreArtiste);
					//Ainsi les deux artistes sont FORCEMENT déjà enregistré quand on arrive à ce point.
					formatSauv.sauverSimilartist(clefCetArtiste,
												clesPrimairesArtistes.get(autreArtiste));
					/*ARTISTES_SIMILAIRES(artiste1 INTEGER references ARTISTE(cle_primaire),
										artiste2 INTEGER references ARTISTE(cle_primaire))*/
				}//endFor
			}//endif
			if(lArtiste.getToptags()!=null){
				for(Tag leTag:lArtiste.getToptags()){
					if(leTag==null){
						leTag=new Tag();
					}
					sauver(leTag);
					formatSauv.sauverArtisteTag(clefCetArtiste,
												clesPrimairesTags.get(leTag));
					/*CORRESP_ARTISTE_TAG(artiste INTEGER references ARTISTE(cle_primaire),
										tag INTEGER references TAG(cle_primaire))*/
				}//endFor
			}//endif
		}//endIf
	}//endSauver(Artiste)

	private static void sauver(Tag leTag) {
		if(leTag==null){
			leTag=new Tag();
		}
		if(!clesPrimairesTags.containsKey(leTag)){//Pour eviter les doublons
			//On attribue une clé au tag
			int clefCeTag=incrementerClesPrimaires();
			clesPrimairesTags.put(leTag,clefCeTag);
			
			//On sauvegarde le wiki
			Wiki leWiki=leTag.getWiki();
			if(leWiki==null){
				leWiki=new Wiki();
			}
			
			int pkWiki=incrementerClesPrimaires();
			formatSauv.sauverWiki(pkWiki,
					String.valueOf(pkWiki),
					leWiki.getDatePublication(),
					leWiki.getResume(),
					leWiki.getContenu());
			/*WIKI(cle_primaire INTEGER,
					id_wiki VARCHAR2(256),
					datePublication date,
					resume VARCHAR2(512),
					contenu VARCHAR2(2048))*/
			
			//On sauvegarde les coordonnées du tag
			int pkCoord=incrementerClesPrimaires();
			formatSauv.sauverCoord(pkCoord,
							String.valueOf(clefCeTag),
							leTag.getName(),
							leTag.getUrl());
			/*ID_NAME_URL(cle_primaire INTEGER,
			 			id VARCHAR2(256),
						name VARCHAR2(128),
						url VARCHAR2(256))*/			
			
			//On sauvegarde le tag
			formatSauv.sauverTag(clefCeTag,
								pkCoord,
								leTag.getReach(),
								leTag.getTagging(),
								pkWiki);
			/*create table TAG(cle_primaire INTEGER,
							id_tag VARCHAR2(256),
							name VARCHAR2(128),
							url VARCHAR2(256),
							reach FLOAT,
							taggings FLOAT,
							wiki INTEGER references WIKI(cle_primaire))*/
		}//finIf
	}//finSauver(Tag)
	
	private static void sauver(Album lAlbum) {
		if(lAlbum==null){
			lAlbum=new Album();
		}
		if(!clesPrimairesAlbums.containsKey(lAlbum)){//Pour eviter les doublons
			//On attribue une clé à l'album
			int clefCetAlbum=incrementerClesPrimaires();
			clesPrimairesAlbums.put(lAlbum,clefCetAlbum);
			
			//On sauvegarde le wiki
			Wiki leWiki=lAlbum.getWiki();
			if(leWiki==null){
				leWiki=new Wiki();
			}
			
			int pkWiki=incrementerClesPrimaires();
			formatSauv.sauverWiki(pkWiki,
					String.valueOf(pkWiki),
					leWiki.getDatePublication(),
					leWiki.getResume(),
					leWiki.getContenu());
			/*WIKI(cle_primaire INTEGER,
					id_wiki VARCHAR2(256),
					datePublication date,
					resume VARCHAR2(512),
					contenu VARCHAR2(2048))*/
			
			//On sauvegarde l'audimat
			
			int pkAudimat=incrementerClesPrimaires();
			formatSauv.sauverAudimat(pkAudimat,
					lAlbum.getListeners(),
					lAlbum.getPlaycount());
			/*AUDIMAT(cle_primaire INTEGER,
						listeners INTEGER,
						playcount INTEGER)*/
			
			//On sauvegarde les images
			int pkImages=incrementerClesPrimaires();
			formatSauv.sauverImages(pkImages,
					lAlbum.getImageSmall(),
					lAlbum.getImageMedium(),
					lAlbum.getImageLarge(),
					lAlbum.getImageExtraLarge(),
					lAlbum.getImageMega());
			/*IMAGES(cle_primaire INTEGER,
					imageSmall VARCHAR2(256),
					imageMedium VARCHAR2(256),
					imageLarge VARCHAR2(256),
					imageExtraLarge VARCHAR2(256),
					imageMega VARCHAR2(256))*/
			
			//On sauvegarde l'artiste
			Artiste lArtiste=lAlbum.getArtiste();
			if(lArtiste==null){
				lArtiste=new Artiste();
			}
			sauver(lArtiste);
			
			//On sauvegarde les coordonnées de la chanson
			int pkCoord=incrementerClesPrimaires();
			formatSauv.sauverCoord(pkCoord,
							lAlbum.getID(),
							lAlbum.getName(),
							lAlbum.getUrl());
			/*ID_NAME_URL(cle_primaire INTEGER,
			 			id VARCHAR2(256),
						name VARCHAR2(128),
						url VARCHAR2(256))*/
			
			//on sauvegarde la chanson
			formatSauv.sauverAlbum(clefCetAlbum,
									pkCoord,
									lAlbum.getDate(),
									pkImages,
									pkAudimat,
									pkWiki,
									clesPrimairesArtistes.get(lArtiste));
			/*ALBUM(cle_primaire INTEGER,
							id_name_url INTEGER references ID_NAME_URL(cle_primaire),
							date_sortie date,
							images INTEGER references IMAGES(cle_primaire),
							audimat INTEGER references AUDIMAT(cle_primaire),
							wiki INTEGER references WIKI(cle_primaire),
							artiste INTEGER references ARTISTE(cle_primaire),
							PRIMARY KEY(cle_primaire))*/
			//XXX Un album n'a pas de tags?
		}//endIf	
	}//finSauver(Album)
}
