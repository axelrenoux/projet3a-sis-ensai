package bdd;

public abstract class SauvegardeUnFormatPourLaBdd {
	
	public SauvegardeUnFormatPourLaBdd() {
	}
	
	public abstract void creerTables();

	public abstract void ajouterLigne(String ligne);
	
	public abstract void ecrireEnTete();

	public abstract void ecrireConclusion();

	public abstract void sauverWiki(String pk, 
									String id_wiki, 
									String string,
									String resume,
									String contenu);

	public abstract void sauverAudimat(String pk, 
									String listeners,
									String playcount);

	public abstract void sauverArtiste(String pk,
									String coord_artiste,
									String pkImagesCetArtiste, 
									String pkAudimatCetArtiste,
									String pkWikiCetArtiste) ;

	public abstract void sauverImages(String pkImagesCetArtiste,
									String imageSmall,
									String imageMedium, 
									String imageLarge, 
									String imageExtraLarge,
									String imageMega);

	public abstract void sauverSimilartist(String artiste1, String artiste2);

	public abstract void sauverChanson(String pk, 
									String coord_chanson,
									String duree, 
									String pkImages, 
									String pkAudimat, 
									String pkWiki,
									String pkArtiste);

	public abstract void sauverArtisteTag(String artiste, String tag);

	public abstract void sauverChansonTag(String chanson, String tag);

	public abstract void sauverTag(String pk,
									String coord_tag,
									String reach,
									String tagging,
									String pkWiki);

	public abstract void sauverCoord(String pk, 
									String id, 
									String name, 
									String url);

	public abstract void sauverChansonAlbum(String album, String chanson);

	public abstract void sauverAlbum(String pk, 
									String coord_album, 
									String string,
									String pkImages, 
									String pkAudimat, 
									String pkWiki, 
									String artiste);
}