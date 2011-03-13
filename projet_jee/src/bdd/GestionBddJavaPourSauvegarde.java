package bdd;

import handler.sax.AlbumSearchHandler;
import handler.sax.ArtisteHandler;
import handler.sax.ChansonHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import metier.Album;
import metier.Artiste;
import metier.Chanson;
import metier.Tag;
import metier.Wiki;

public class GestionBddJavaPourSauvegarde {
	//La classe gérant le format sous lequel on va enregistrer la BDD
	SauvegardeUnFormatPourLaBdd formatSauv;
	
	//Les endroits où se trouvent nos données
	private ArtisteHandler stockageArtistes;//FIXME à modifier
	private ChansonHandler stockageChansons;//FIXME à modifier
	private AlbumSearchHandler stockageAlbums;//FIXME à modifier
	
	//Nos données d'origine en Java
	List<Artiste> artistes=stockageArtistes.getLstArtistes();
	List<Chanson> chansons=stockageChansons.getLstChanson();
	List<Album> albums=stockageAlbums.getLstAlbums();
	
	//Des maps de clés primaire : savoir si on a déjà entré un artiste(etc...), si oui récupérer sa clé primaire, sinon le créer
	Map<Artiste,Integer> clesPrimairesArtistes=new HashMap<Artiste,Integer>();
	Map<Album,Integer> clesPrimairesAlbums=new HashMap<Album,Integer>();
	Map<Chanson, Integer> clesPrimairesChansons=new HashMap<Chanson,Integer>();
	Map<Tag, Integer> clesPrimairesTags=new HashMap<Tag,Integer>();
	
	//Variable servant à incrémenter les clés primaires, pour s'assurer qu'elles seront toutes différentes
	private int pk=0;
	
	private void incrementerClesPrimaires() {
		pk=pk+1;
	}
	
	public void decomposerAvantSauvegardeGereePar(SauvegardeUnFormatPourLaBdd gestionnaireDeFormatdeSauvegarde){
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

	private void sauver(Chanson laChanson) {
		if(!clesPrimairesChansons.containsKey(laChanson)){//Pour eviter les doublons
			
			//On sauvegarde le wiki
			Wiki leWiki=laChanson.getWiki();
			
			incrementerClesPrimaires();
			int pkWiki=pk;
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
			
			incrementerClesPrimaires();
			int pkAudimat=pk;
			formatSauv.sauverAudimat(pkAudimat,
					laChanson.getListeners(),
					laChanson.getPlaycount());
			/*AUDIMAT(cle_primaire INTEGER,
						listeners INTEGER,
						playcount INTEGER)*/
			
			//On sauvegarde les images
			
			incrementerClesPrimaires();
			int pkImages=pk;
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
			sauver(lArtiste);
			
			//On attribue une clé à la chanson
			incrementerClesPrimaires();
			clesPrimairesChansons.put(laChanson,pk);
			
			//On sauvegarde les coordonnées de la chanson
			incrementerClesPrimaires();
			int pkCoord=pk;
			formatSauv.sauverCoord(pkCoord,
							String.valueOf(clesPrimairesChansons.get(laChanson)),
							laChanson.getName(),
							laChanson.getUrl());
			/*ID_NAME_URL(cle_primaire INTEGER,
			 			id VARCHAR2(256),
						name VARCHAR2(128),
						url VARCHAR2(256))*/
			
			//on sauvegarde la chanson
			formatSauv.sauverChanson(clesPrimairesChansons.get(laChanson),
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
			
			for(Tag leTag:laChanson.getToptags()){
				sauver(leTag);
				formatSauv.sauverChansonTag(clesPrimairesChansons.get(laChanson),
											clesPrimairesTags.get(leTag));
				/*CORRESP_CHANSON_TAG(chanson INTEGER references CHANSON(cle_primaire),
									tag INTEGER references TAG(cle_primaire))*/
			}//endFor
			for(Album lAlbum:laChanson.getAlbums().values()){
				sauver(lAlbum);
				formatSauv.sauverChansonAlbum(clesPrimairesAlbums.get(lAlbum),
											clesPrimairesChansons.get(laChanson));
				/*CORRESP_CHANSON_ALBUM(album INTEGER references ALBUM(cle_primaire),
										chanson INTEGER references CHANSON(cle_primaire))*/
			}//endFor
		}//endIf	
	}//finSauver(Chanson)
	

	private void sauver(Artiste lArtiste){
		if(!clesPrimairesArtistes.containsKey(lArtiste)){//Pour eviter les doublons
			//On sauvegarde le wiki
			Wiki leWiki=lArtiste.getWiki();
			
			incrementerClesPrimaires();
			int pkWikiCetArtiste=pk;
			formatSauv.sauverWiki(pkWikiCetArtiste,
					String.valueOf(pkWikiCetArtiste),
					leWiki.getDatePublication(),
					leWiki.getResume(),
					leWiki.getContenu());
			/*WIKI(cle_primaire INTEGER,
					id_wiki VARCHAR2(256),
					datePublication date,
					resume VARCHAR2(512),
					contenu VARCHAR2(2048))*/
			
			//On sauvegarde l'audimat
			
			incrementerClesPrimaires();
			int pkAudimatCetArtiste=pk;
			formatSauv.sauverAudimat(pkAudimatCetArtiste,
					lArtiste.getListeners(),
					lArtiste.getPlaycount());
			/*AUDIMAT(cle_primaire INTEGER,
						listeners INTEGER,
						playcount INTEGER)*/
			
			//On sauvegarde les images
			
			incrementerClesPrimaires();
			int pkImagesCetArtiste=pk;
			formatSauv.sauverImages(pkImagesCetArtiste,
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
			
			
			
			//On attribue une clé à l'artiste
			incrementerClesPrimaires();
			clesPrimairesArtistes.put(lArtiste,pk);
			//Pour pouvoir retrouver l'artiste pour les correspondances avec albums etc
			
			//On sauvegarde les coordonnées de la chanson
			incrementerClesPrimaires();
			int pkCoord=pk;
			formatSauv.sauverCoord(pkCoord,
							String.valueOf(clesPrimairesArtistes.get(lArtiste)),
							lArtiste.getName(),
							lArtiste.getUrl());
			/*ID_NAME_URL(cle_primaire INTEGER,
			 			id VARCHAR2(256),
						name VARCHAR2(128),
						url VARCHAR2(256))*/
			
			//Et on sauvegarde l'artiste
			formatSauv.sauverArtiste(clesPrimairesChansons.get(lArtiste),
									pkCoord,
									pkImagesCetArtiste,
									pkAudimatCetArtiste,
									pkWikiCetArtiste);
			/*ARTISTE(cle_primaire INTEGER,
					id_artiste VARCHAR2(256),
					name VARCHAR2(128),
					url VARCHAR2(256),
					images INTEGER references IMAGES(cle_primaire),
					audimat INTEGER references AUDIMAT(cle_primaire),
					wiki INTEGER references WIKI(cle_primaire))*/
			for(Artiste autreArtiste:lArtiste.getArtistesSimilaires()){
				sauver(autreArtiste);
				//Ainsi les deux artistes sont FORCEMENT déjà enregistré quand on arrive à ce point.
				formatSauv.sauverSimilartist(clesPrimairesArtistes.get(lArtiste),
											clesPrimairesArtistes.get(autreArtiste));
				/*ARTISTES_SIMILAIRES(artiste1 INTEGER references ARTISTE(cle_primaire),
									artiste2 INTEGER references ARTISTE(cle_primaire))*/
			}//endFor
			for(Tag leTag:lArtiste.getToptags()){
				sauver(leTag);
				formatSauv.sauverArtisteTag(clesPrimairesArtistes.get(lArtiste),
											clesPrimairesTags.get(leTag));
				/*CORRESP_ARTISTE_TAG(artiste INTEGER references ARTISTE(cle_primaire),
									tag INTEGER references TAG(cle_primaire))*/
			}//endFor
		}//endIf
	}//endSauver(Artiste)

	private void sauver(Tag leTag) {
		if(!clesPrimairesTags.containsKey(leTag)){//Pour eviter les doublons
			//On sauvegarde le wiki
			Wiki leWiki=leTag.getWiki();
			
			incrementerClesPrimaires();
			int pkWiki=pk;
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
			
			//On attribue une clé au tag
			incrementerClesPrimaires();
			clesPrimairesTags.put(leTag,pk);
			
			//On sauvegarde les coordonnées du tag
			incrementerClesPrimaires();
			int pkCoord=pk;
			formatSauv.sauverCoord(pkCoord,
							String.valueOf(clesPrimairesTags.get(leTag)),
							leTag.getName(),
							leTag.getUrl());
			/*ID_NAME_URL(cle_primaire INTEGER,
			 			id VARCHAR2(256),
						name VARCHAR2(128),
						url VARCHAR2(256))*/			
			
			//On sauvegarde le tag
			formatSauv.sauverTag(clesPrimairesTags.get(leTag),
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
	
	private void sauver(Album lAlbum) {
		if(!clesPrimairesAlbums.containsKey(lAlbum)){//Pour eviter les doublons
			//On sauvegarde le wiki
			Wiki leWiki=lAlbum.getWiki();
			
			incrementerClesPrimaires();
			int pkWiki=pk;
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
			
			incrementerClesPrimaires();
			int pkAudimat=pk;
			formatSauv.sauverAudimat(pkAudimat,
					lAlbum.getListeners(),
					lAlbum.getPlaycount());
			/*AUDIMAT(cle_primaire INTEGER,
						listeners INTEGER,
						playcount INTEGER)*/
			
			//On sauvegarde les images
			
			incrementerClesPrimaires();
			int pkImages=pk;
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
			sauver(lArtiste);
			
			//On attribue une clé à l'album
			incrementerClesPrimaires();
			clesPrimairesAlbums.put(lAlbum,pk);
			
			//On sauvegarde les coordonnées de la chanson
			incrementerClesPrimaires();
			int pkCoord=pk;
			formatSauv.sauverCoord(pkCoord,
							lAlbum.getID(),
							lAlbum.getName(),
							lAlbum.getUrl());
			/*ID_NAME_URL(cle_primaire INTEGER,
			 			id VARCHAR2(256),
						name VARCHAR2(128),
						url VARCHAR2(256))*/
			
			//on sauvegarde la chanson
			formatSauv.sauverAlbum(clesPrimairesAlbums.get(lAlbum),
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
			//XXX Un album n'a pas de tags?!?
		}//endIf	
	}//finSauver(Album)
}
