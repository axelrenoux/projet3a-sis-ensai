package bdd;

import java.util.Date;
import java.util.List;

public class SauvegardeBddFormatXmlFichierTxt extends SauvegardeUnFormatPourLaBdd{
	//Comporte la liste d'imbrications de balises dans laquelles nous �crivont actuellement
	//L'identation (nb de tabulations en d�but de ligne) est donn�e par le nombre d'�l�ment dans cette liste
	private List<String> hierarchieDesBalises;

	public SauvegardeBddFormatXmlFichierTxt(){
		super();
	}
	
	@Override
	public void ajouterLigne(String ligne){
		/*TODO : la m�thode qui �crit dans le fichier
		* elle g�re l'identation en regardant la taille de la liste hierarchieDesBalises
		*/
	}
	
	public void ajouterLigne(String acronymeBalise, 
							String contenuEnAttributSousFormeDef,
							String contenuHorsBalise,
							boolean contientAutresBalises){
		if((contenuHorsBalise.isEmpty())&&(!contientAutresBalises)){
			// TODO : on l'�crit avec le "/>" � la fin
		}else{
			agrandirHierarchie(acronymeBalise, contenuHorsBalise, contenuEnAttributSousFormeDef);
		}
	}

	public void agrandirHierarchie(String acronymeBalise,
									String contenuHorsBalise,
									String contenuEnAttributSousFormeDef){
		/*TODO : 
		 * - en faire une balise ouvrante, et l'�crire avec le contenuEnAttibutSousFormeDef
		 * - ajouter l'acronyme � la liste hierarchieDesBalises
		 * - on augmente l'identation (g�r� automatiquement par le fait d'ajouter � hierarchieDesBalises)
		 * - on �crit le contenuHorsBalise
		 */
	}
	
	public void diminuerHierarchie(){
		/*TODO : 
		 * - r�cup�rer l'acronyme de la derni�re balise de la liste hierarchieDesBalises
		 * - supprimer de la liste hierarchieDesBalises (ce qui r�duira l'identation)
		 * - en faire une balise fermante, et l'�crire en r�duisant l'identation
		 */
	}
	
	@Override
	public void ecrireEnTete() {
		/* TODO : 
		 * - on �crit l'ent�te, les r�f�rence � la DTD
		 */
		ajouterLigne("BDD","","",true);
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
	public void creerTables() {
		//rien : le fichier txt existe d�j�
		//�ventuellement : charger la DTD? Je ne suis pas certain que ce soit le lieu appropri�...
	}
}
