package bdd.sauvegarde_controlee;

abstract class SauvegardeUnFormatPourLaBdd {
	
	protected SauvegardeUnFormatPourLaBdd() {}
	
	protected abstract void ecrireEnTete(boolean lesTablesExistent);
	
	protected abstract void creerTables();

	protected abstract void conserverDonneesExistantes();
	
	protected abstract void reecrireDonneesExistantes();

	protected abstract void ajouterLigne(String ligne);

	protected abstract void ecrireConclusion();

	protected abstract void sauverWiki(String pk, 
									String id_wiki, 
									String string,
									String resume,
									String contenu);

	protected abstract void sauverAudimat(String pk, 
									String listeners,
									String playcount);

	protected abstract void sauverArtiste(String coord_artiste,
									String pkImagesCetArtiste, 
									String pkAudimatCetArtiste,
									String pkWikiCetArtiste) ;

	protected abstract void sauverImages(String pkImagesCetArtiste,
									String imageSmall,
									String imageMedium, 
									String imageLarge, 
									String imageExtraLarge,
									String imageMega);

	protected abstract void sauverSimilartist(String artiste1, String artiste2);

	protected abstract void sauverChanson(String coord_chanson,
									String duree, 
									String pkImages, 
									String pkAudimat, 
									String pkWiki,
									String pkArtiste);

	protected abstract void sauverArtisteTag(String artiste, String tag);

	protected abstract void sauverChansonTag(String chanson, String tag);

	protected abstract void sauverTag(String coord_tag,
									String reach,
									String tagging,
									String pkWiki);

	protected abstract void sauverCoord(String pk, 
									String id, 
									String name, 
									String url);

	protected abstract void sauverChansonAlbum(String album, String chanson);

	protected abstract void sauverAlbum(String coord_album, 
									String string,
									String pkImages, 
									String pkAudimat, 
									String pkWiki, 
									String artiste);

	public abstract void sauverAlbumTag(String album, String tag);
}