package bdd;


public class SauvegardeBddFormatRdfFichierTxt extends SauvegardeUnFormatPourLaBdd{

	public SauvegardeBddFormatRdfFichierTxt(){
		super();
	}
	
	@Override
	public void ecrireEnTete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ecrireConclusion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverWiki(String pk, String id_wiki, String datePublication,
			String resume, String contenu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverAudimat(String pk, String listeners, String playcount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverArtiste(String pk, String coord_artiste,
			String pkImagesCetArtiste, String pkAudimatCetArtiste,
			String pkWikiCetArtiste) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverImages(String pkImagesCetArtiste, String imageSmall,
			String imageMedium, String imageLarge, String imageExtraLarge,
			String imageMega) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverSimilartist(String artiste1, String artiste2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverChanson(String pk, String coord_chanson, String duree,
			String pkImages, String pkAudimat, String pkWiki, String pkArtiste) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverArtisteTag(String artiste, String tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverChansonTag(String chanson, String tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverTag(String pk, String coord_tag, String reach, String tagging,
			String pkWiki) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverCoord(String pk, String id, String name, String url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverChansonAlbum(String album, String chanson) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverAlbum(String pk, String coord_album, String date_sortie,
			String pkImages, String pkAudimat, String pkWiki, String artiste) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouterLigne(String ligne) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creerTables() {
		// TODO Auto-generated method stub
		
	}

}
