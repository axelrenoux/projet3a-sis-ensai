package bdd;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
*@deprecated
*/
public class SauvegardeBddFormatXmlFichierTxt extends SauvegardeUnFormatPourLaBdd{
	//Comporte la liste d'imbrications de balises dans laquelles nous écrivont actuellement
	//L'identation (nb de tabulations en début de ligne) est donnée par le nombre d'élément dans cette liste
	private List<String> hierarchieDesBalises=new LinkedList<String>();
	private PrintWriter fluxSortie;

	public SauvegardeBddFormatXmlFichierTxt(){
		super();
	}
	
	@Override
	public void ajouterLigne(String ligne){
		for(@SuppressWarnings("unused") String nonLu:hierarchieDesBalises){
			ligne="	"+ligne;//C'est ici que l'on gère l'identation, en fonction du nombre de balises ouvertes
		}
		fluxSortie.println(ligne);
	}
	
	public void ajouterLigne(String acronymeBalise, 
							String contenuEnAttributSousFormeDef,
							String contenuHorsBalise,
							boolean contientAutresBalises){
		if((contenuHorsBalise.isEmpty())&&(!contientAutresBalises)){
			ajouterLigne("<"+acronymeBalise+" "+contenuEnAttributSousFormeDef+"/>");
		}else{
			agrandirHierarchie(acronymeBalise, contenuHorsBalise, contenuEnAttributSousFormeDef);
		}
	}

	public void agrandirHierarchie(String acronymeBalise,
									String contenuHorsBalise,
									String contenuEnAttributSousFormeDef){
		ajouterLigne("<"+acronymeBalise+" "+contenuEnAttributSousFormeDef+">");
		hierarchieDesBalises.add(acronymeBalise);
		ajouterLigne("<xsl:text>"+contenuHorsBalise+"</xsl:text>");
	}
	
	public void diminuerHierarchie(){
		int positionDerniereBalise=hierarchieDesBalises.size()-1;
		String acronymeBalise=hierarchieDesBalises.remove(positionDerniereBalise);
		ajouterLigne("</"+acronymeBalise+">");
	}
	
	@Override
	public void ecrireEnTete(boolean lesTablesExistent) {
		//XXX le boolean lesTablesExistent n'est pas traité pour l'instant ici
		try {fluxSortie = new PrintWriter(new FileWriter("XMLenCours.xml"));} 
		catch (IOException e1) {e1.printStackTrace();}
		/* TODO : 
		 * - on écrit l'entête, les référence à la DTD
		 * - ne pas oublier le xlmns:xsl
		 * - puis :
		 */
		ajouterLigne("BDD","","",true);//<BDD>
	}

	@Override
	public void ecrireConclusion() {
		diminuerHierarchie();//</BDD>
		fluxSortie.close();
	}

	@Override
	public void sauverWiki(String pk, String id_wiki, String datePublication,
			String resume, String contenu) {
		ajouterLigne("WIKI","","",true);
		//TODO : ajouter le contenu du WIKI
		//XXX : ouais, mais là on n'est pas vraiment en train de mettre le WIKI à l'intérieur de ce à quoi il correspond
		diminuerHierarchie();
	}

	@Override
	public void creerTables() {
		//rien : le fichier txt existe déjà
		//éventuellement : charger la DTD? Je ne suis pas certain que ce soit le lieu approprié...
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
	public void sauverTag(String pk, String coord_tag, String reach,
			String tagging, String pkWiki) {
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
	public void sauverAlbum(String pk, String coord_album, String string,
			String pkImages, String pkAudimat, String pkWiki, String artiste) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void conserverDonneesExistantes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reecrireDonneesExistantes() {
		// TODO Auto-generated method stub
		
	}
}
