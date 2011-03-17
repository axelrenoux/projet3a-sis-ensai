package bdd.sauvegarde_controlee;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
*@deprecated
*/
class SauvegardeBddFormatXmlFichierTxt extends SauvegardeUnFormatPourLaBdd{
	//Comporte la liste d'imbrications de balises dans laquelles nous �crivont actuellement
	//L'identation (nb de tabulations en d�but de ligne) est donn�e par le nombre d'�l�ment dans cette liste
	private List<String> hierarchieDesBalises=new LinkedList<String>();
	private PrintWriter fluxSortie;

	protected SauvegardeBddFormatXmlFichierTxt(){
		super();
	}
	
	@Override
	protected void ajouterLigne(String ligne){
		for(@SuppressWarnings("unused") String nonLu:hierarchieDesBalises){
			ligne="	"+ligne;//C'est ici que l'on g�re l'identation, en fonction du nombre de balises ouvertes
		}
		fluxSortie.println(ligne);
	}
	
	protected void ajouterLigne(String acronymeBalise, 
							String contenuEnAttributSousFormeDef,
							String contenuHorsBalise,
							boolean contientAutresBalises){
		if((contenuHorsBalise.isEmpty())&&(!contientAutresBalises)){
			ajouterLigne("<"+acronymeBalise+" "+contenuEnAttributSousFormeDef+"/>");
		}else{
			agrandirHierarchie(acronymeBalise, contenuHorsBalise, contenuEnAttributSousFormeDef);
		}
	}

	protected void agrandirHierarchie(String acronymeBalise,
									String contenuHorsBalise,
									String contenuEnAttributSousFormeDef){
		ajouterLigne("<"+acronymeBalise+" "+contenuEnAttributSousFormeDef+">");
		hierarchieDesBalises.add(acronymeBalise);
		ajouterLigne("<xsl:text>"+contenuHorsBalise+"</xsl:text>");
	}
	
	protected void diminuerHierarchie(){
		int positionDerniereBalise=hierarchieDesBalises.size()-1;
		String acronymeBalise=hierarchieDesBalises.remove(positionDerniereBalise);
		ajouterLigne("</"+acronymeBalise+">");
	}
	
	@Override
	protected void ecrireEnTete(boolean lesTablesExistent) {
		//XXX le boolean lesTablesExistent n'est pas trait� pour l'instant ici
		try {fluxSortie = new PrintWriter(new FileWriter("XMLenCours.xml"));} 
		catch (IOException e1) {e1.printStackTrace();}
		/* TODO : 
		 * - on �crit l'ent�te, les r�f�rence � la DTD
		 * - ne pas oublier le xlmns:xsl
		 * - puis :
		 */
		ajouterLigne("BDD","","",true);//<BDD>
	}

	@Override
	protected void ecrireConclusion() {
		diminuerHierarchie();//</BDD>
		fluxSortie.close();
	}

	@Override
	protected void sauverWiki(String pk, String id_wiki, String dateprotectedation,
			String resume, String contenu) {
		ajouterLigne("WIKI","","",true);
		//TODO : ajouter le contenu du WIKI
		//XXX : ouais, mais l� on n'est pas vraiment en train de mettre le WIKI � l'int�rieur de ce � quoi il correspond
		diminuerHierarchie();
	}

	@Override
	protected void creerTables() {
		//rien : le fichier txt existe d�j�
		//�ventuellement : charger la DTD? Je ne suis pas certain que ce soit le lieu appropri�...
	}

	@Override
	protected void sauverAudimat(String pk, String listeners, String playcount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sauverArtiste(String coord_artiste,
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
	protected void sauverChanson(String coord_chanson, String duree,
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
	protected void sauverTag(String coord_tag, String reach,
			String tagging, String pkWiki) {
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
	protected void sauverAlbum(String coord_album, String string,
			String pkImages, String pkAudimat, String pkWiki, String artiste) {
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

	@Override
	public void sauverAlbumTag(String album, String tag) {
		// TODO Auto-generated method stub
		
	}
}
