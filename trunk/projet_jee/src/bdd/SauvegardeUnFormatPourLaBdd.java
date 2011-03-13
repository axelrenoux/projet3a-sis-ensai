package bdd;

import java.util.Date;



public abstract class SauvegardeUnFormatPourLaBdd {
	public abstract void ecrireEnTete();

	public abstract void ecrireConclusion();

	public abstract void sauverWiki(int pk, 
									String id_wiki, 
									Date datePublication,
									String resume,
									String contenu);

	public abstract void sauverAudimat(int pk, 
									double listeners,
									double playcount);

	public abstract void sauverArtiste(int pk,
									int coord_artiste,
									int pkImagesCetArtiste, 
									int pkAudimatCetArtiste,
									int pkWikiCetArtiste) ;

	public abstract void sauverImages(int pkImagesCetArtiste,
									String imageSmall,
									String imageMedium, 
									String imageLarge, 
									String imageExtraLarge,
									String imageMega);

	public abstract void sauverSimilartist(Integer artiste1, Integer artiste2);

	public abstract void sauverChanson(int pk, 
									int coord_chanson,
									Double duree, 
									int pkImages, 
									int pkAudimat, 
									int pkWiki,
									int pkArtiste);

	public abstract void sauverArtisteTag(Integer artiste, Integer tag);

	public abstract void sauverChansonTag(Integer chanson, Integer tag);

	public abstract void sauverTag(int pk,
									int coord_tag,
									Double reach,
									Double tagging,
									int pkWiki);

	public abstract void sauverCoord(int pk, 
									String id, 
									String name, 
									String url);

	public abstract void sauverChansonAlbum(Integer album, Integer chanson);

	public abstract void sauverAlbum(int pk, 
									int coord_album, 
									Date date_sortie,
									int pkImages, 
									int pkAudimat, 
									int pkWiki, 
									Integer artiste);
}