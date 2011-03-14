package bdd;

import java.util.Date;

public class SauvegardeBddFormatRdfFichierTxt extends SauvegardeUnFormatPourLaBdd{

	@Override
	public void ecrireEnTete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ecrireConclusion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverWiki(int pk, String id_wiki, Date datePublication,
			String resume, String contenu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverAudimat(int pk, double listeners, double playcount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverArtiste(int pk, int coord_artiste,
			int pkImagesCetArtiste, int pkAudimatCetArtiste,
			int pkWikiCetArtiste) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverImages(int pkImagesCetArtiste, String imageSmall,
			String imageMedium, String imageLarge, String imageExtraLarge,
			String imageMega) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverSimilartist(Integer artiste1, Integer artiste2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverChanson(int pk, int coord_chanson, Double duree,
			int pkImages, int pkAudimat, int pkWiki, int pkArtiste) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverArtisteTag(Integer artiste, Integer tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverChansonTag(Integer chanson, Integer tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverTag(int pk, int coord_tag, Double reach, Double tagging,
			int pkWiki) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverCoord(int pk, String id, String name, String url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverChansonAlbum(Integer album, Integer chanson) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sauverAlbum(int pk, int coord_album, Date date_sortie,
			int pkImages, int pkAudimat, int pkWiki, Integer artiste) {
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
