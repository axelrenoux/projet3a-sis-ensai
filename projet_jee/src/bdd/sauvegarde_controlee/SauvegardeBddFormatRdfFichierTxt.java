package bdd.sauvegarde_controlee;

/**
*@deprecated
*/
class SauvegardeBddFormatRdfFichierTxt extends SauvegardeUnFormatPourLaBdd{

	protected SauvegardeBddFormatRdfFichierTxt(){
		super();
	}
	
	@Override
	protected void ecrireEnTete(boolean tablesDejaExistantes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void ecrireConclusion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sauverWiki(String pk, String id_wiki, String dateprotectedation,
			String resume, String contenu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sauverAudimat(String pk, String listeners, String playcount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sauverArtiste(String pk, String coord_artiste,
			String pkImagesCetArtiste, String pkAudimatCetArtiste,
			String pkWikiCetArtiste) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sauverImages(String pkImagesCetArtiste, String imageSmall,
			String imageMedium, String imageLarge, String imageExtraLarge,
			String imageMega) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sauverSimilartist(String artiste1, String artiste2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sauverChanson(String pk, String coord_chanson, String duree,
			String pkImages, String pkAudimat, String pkWiki, String pkArtiste) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sauverArtisteTag(String artiste, String tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sauverChansonTag(String chanson, String tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sauverTag(String pk, String coord_tag, String reach, String tagging,
			String pkWiki) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sauverCoord(String pk, String id, String name, String url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sauverChansonAlbum(String album, String chanson) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sauverAlbum(String pk, String coord_album, String date_sortie,
			String pkImages, String pkAudimat, String pkWiki, String artiste) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void ajouterLigne(String ligne) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void creerTables() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void conserverDonneesExistantes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void reecrireDonneesExistantes() {
		// TODO Auto-generated method stub
		
	}

}
