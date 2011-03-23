/**
 * 
 */
package recuperationLastFM.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import controleur.Controleur;
import controleur.UtilitaireDate;
import exceptions.ExceptionDate;
import exceptions.ExceptionMiseAjour;

import metier.Tag;
import metier.Wiki;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;


/**
 * @author Administrateur
 *
 */
public class ChansonInfoHandler extends DefaultHandler{

	/*<track>
	<id>1019817</id> 
	<name>Believe</name> 
	<url>http://www.last.fm/music/Cher/_/Believe</url> 
	<duration>239000</duration> 
	<listeners>211017</listeners> 
	<playcount>832830</playcount> 
	<artist>
	   <name>Cher</name> 
	   <url>http://www.last.fm/music/Cher</url> 
	</artist>
	<album position="1">
	   <artist>Cher</artist> 
	   <title>The Very Best of Cher</title> 
	   <url>http://www.last.fm/music/Cher/The+Very+Best+of+Cher</url> 
	   <image size="small">http://userserve-ak.last.fm/serve/64s/41161483.png</image> 
	   <image size="medium">http://userserve-ak.last.fm/serve/126/41161483.png</image> 
	   <image size="large">http://userserve-ak.last.fm/serve/174s/41161483.png</image> 
	   <image size="extralarge">http://userserve-ak.last.fm/serve/300x300/41161483.png</image> 
    </album>
	<toptags>
	   <tag>
	   		<name>pop</name> 
	   		<url>http://www.last.fm/tag/pop</url> 
	   </tag>
	</toptags>
	<wiki>
	   <published>Tue, 17 Nov 2009 15:02:18 +0000</published> 
	   <summary> </summary>
	   <content> </content>
	</wiki>
</track>
	 */

	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/

	//résultats de notre parsing
	private Chanson chanson;

	// objets courants 
	//private Artiste currentArtisteChanson;
	private Artiste currentArtisteAlbum;
	private Album currentAlbum;
	private boolean premierAlbum=true;
	private ArrayList<Album> listeAlbums;
	private String rangCurrentAlbum;
	private Tag currentTag;
	private ArrayList<Tag> listeTags;
	private Wiki currentWiki;



	//flags nous indiquant la position du parseur
	private boolean inID;
	private boolean inListeners;
	private boolean inPlaycount;
	private boolean inDuration;
	private boolean inArtisteChanson;
	private boolean inURLArtisteChanson;
	private boolean inAlbum;
	private boolean inArtisteAlbum;
	private boolean inTitleAlbum;
	private boolean inURLAlbum;
	private boolean inImageSmallAlbum;
	private boolean inImageMediumAlbum;
	private boolean inImageLargeAlbum;
	private boolean inImageExtraLargeAlbum;
	private boolean inImageMegaAlbum;
	private boolean inTopTags;
	private boolean inTag;
	private boolean inNameTag;
	private boolean inUrlTag;
	private boolean inWiki;
	private boolean inPublished;
	private boolean inSummary;
	private boolean inContent;


	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/

	/**
	 * constructeur vide
	 */
	public ChansonInfoHandler(Chanson chanson){
		super();
		this.chanson = chanson;
	}


	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 * methode appellee pour chaque nouvelle balise xml
	 */
	public void startElement(String uri,
			String localName,
			String qName,
			Attributes attributes)
	throws SAXException{
		if(qName.equals("id")){
			inID = true;	
		}else if(qName.equals("listeners")){
			inListeners = true;	
		}else if(qName.equals("playcount")){
			inPlaycount = true;	
		}else if(qName.equals("duration")){
			inDuration = true;	
		}else if(qName.equals("artist")&& !inAlbum){
			inArtisteChanson = true;
			//currentArtisteChanson = new Artiste();
		}else if(qName.equals("url") && inArtisteChanson){
			inURLArtisteChanson = true;
		}else if(qName.equals("album")){
			inAlbum = true;
			if(premierAlbum) listeAlbums = new ArrayList<Album>();
			premierAlbum = false;
			currentAlbum = new Album();
			rangCurrentAlbum = attributes.getValue(0);
		}else if(qName.equals("artist") && inAlbum){
			inArtisteAlbum = true;
			currentArtisteAlbum = new Artiste();
		}else if(qName.equals("title") && inAlbum){
			inTitleAlbum = true;
		}else if(qName.equals("url") && inAlbum){
			inURLAlbum = true;	
		}else if(qName.equals("image") && inAlbum){
			if(attributes.getValue(0).equals("small")){
				inImageSmallAlbum = true;
			}else if (attributes.getValue(0).equals("medium")){
				inImageMediumAlbum = true;
			}else if (attributes.getValue(0).equals("large")){
				inImageLargeAlbum = true;
			}else if (attributes.getValue(0).equals("extralarge")){
				inImageExtraLargeAlbum = true;
			}else if (attributes.getValue(0).equals("mega")){
				inImageMegaAlbum = true;
			}
		}else if(qName.equals("toptags")){
			inTopTags = true;	
			listeTags = new ArrayList<Tag>();
		}else if(qName.equals("tag")){
			inTag = true;	
			currentTag = new Tag();
		}else if(qName.equals("name") && inTag){
			inNameTag = true;	
		}else if(qName.equals("url") && inTag){
			inUrlTag = true;
		}else if(qName.equals("wiki")){
			inWiki = true;
			currentWiki = new Wiki();
		}else if(qName.equals("published")){
			inPublished = true;	
		}else if(qName.equals("summary")){
			inSummary = true;
		}else if(qName.equals("content")){
			inContent = true;
		}else{
			//System.out.println("Balise non traitee pour le moment : " + qName);
		}
	}
		

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 * methode appellee a la fermeture d une balise
	 */
	public void endElement(String uri,
			String localName,
			String qName)
	throws SAXException{
		if(qName.equals("id")){
			inID = false;	
		}else if(qName.equals("listeners")){
			inListeners = false;	
		}else if(qName.equals("playcount")){
			inPlaycount = false;	
		}else if(qName.equals("duration")){
			inDuration = false;	
		}else if(qName.equals("artist")&& !inArtisteAlbum){
			inArtisteChanson = false;//TODO
		}else if(qName.equals("url") && inArtisteChanson){
			inURLArtisteChanson = false;
		}else if(qName.equals("album")){
			inAlbum = false;
			//listeAlbums.add(currentAlbum);
			gererAjoutAlbum();
			chanson.setAlbums(listeAlbums);
		}else if(qName.equals("artist") && inAlbum){
			inArtisteAlbum = false;
			gererAjoutArtisteAlbum();//TODO
			//currentAlbum.setArtiste(currentArtisteAlbum);
		}else if(qName.equals("title") && inAlbum){
			inTitleAlbum = false;
		}else if(qName.equals("url") && inAlbum){
			inURLAlbum = false;	
		}else if(qName.equals("image") && inAlbum){
			inImageSmallAlbum = false;
			inImageMediumAlbum = false;
			inImageLargeAlbum = false;
			inImageExtraLargeAlbum = false;
			inImageMegaAlbum = false;
		}else if(qName.equals("toptags")){
			inTopTags = false;
			chanson.setToptags(listeTags);
		}else if(qName.equals("tag")){
			inTag = false;	
			//listeTags.add(currentTag);
			gererAjoutTag();
		}else if(qName.equals("name") && inTag){
			inNameTag = false;	
		}else if(qName.equals("url") && inTag){
			inUrlTag = false;
		}else if(qName.equals("wiki")){
			inWiki = false;
			chanson.setWiki(currentWiki);
		}else if(qName.equals("published")){
			inPublished = false;	
		}else if(qName.equals("summary")){
			inSummary = false;
		}else if(qName.equals("content")){
			inContent = false;
		}
	}






	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 * detection de caracteres
	 */
	public void characters(char[] ch,
			int start,
			int length)
	throws SAXException{
		String lecture = new String(ch,start,length);
		if(inID){
			chanson.setID(lecture);
		}else if(inListeners){
			chanson.setListeners(Integer.parseInt(lecture));
		}else if(inPlaycount){
			chanson.setPlaycount(Integer.parseInt(lecture));
		}else if(inDuration){
			chanson.setDuree(Integer.parseInt(lecture));
		}else if(inURLArtisteChanson){
			//la chanson a necessairement deja un artiste, on le met a jour ici
			chanson.getArtiste().setUrl(lecture);
		}else if(inArtisteAlbum){
			currentArtisteAlbum.setName(lecture);
		}else if(inTitleAlbum){
			currentAlbum.setName(lecture);
		}else if(inURLAlbum){
			currentAlbum.setUrl(lecture);
		}else if(inImageSmallAlbum){
			currentAlbum.setImageSmall(lecture);
		}else if(inImageMediumAlbum){
			currentAlbum.setImageMedium(lecture);
		}else if(inImageLargeAlbum){
			currentAlbum.setImageLarge(lecture);
		}else if(inImageExtraLargeAlbum){
			currentAlbum.setImageExtraLarge(lecture);
		}else if(inImageMegaAlbum){
			currentAlbum.setImageMega(lecture);
		}else if(inNameTag){
			currentTag.setName(lecture);
		}else if(inUrlTag){
			currentTag.setUrl(lecture);
		}else if(inPublished){
			Date d;
			try {
				d = UtilitaireDate.getInstanceunique().
					transformerEnDateWiki(lecture);
				currentWiki.setDatePublication(d);
			} catch (ExceptionDate e) {
				Controleur.getInstanceuniquecontroleur().
				ajouterProbleme(e.getTitre()+Controleur.getInstanceuniquecontroleur().
						getListeProblemesRencontres().size(), e.getMessage());
			}	 	
		}else if(inSummary){
			currentWiki.setResume(lecture);
		}else if(inContent){
			currentWiki.setContenu(lecture);
		}
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 * methode appellee au debut du parsing du fichier
	 */
	public void startDocument() throws SAXException {
		System.out.println("Début du parsing get info chanson");
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 * methode appellee a la fin du parsing du fichier
	 */
	public void endDocument() throws SAXException {
		System.out.println("Fin du parsing get info chanson");
	}




	/**
	 * Methode qui gere l'ajout d'un nouvel artiste dans l'album d'une chanson
	 * il faut verifier que l'artiste n'existe pas deja dans le controleur
	 * et si c'est le cas, on propose une mise a jour de l'artiste
	 * dans le cas contraire, on doit ajouter l'artiste à la liste d'artistes
	 * du controleur
	 */
	public void gererAjoutArtisteAlbum(){
		//on veut ajouter un artiste à un album de la chanson courante, 
		//on verifie que l'artiste n'existe pas deja:
		if(Controleur.getInstanceuniquecontroleur().existeDeja(currentArtisteAlbum)){
			//on propose une mise a jour de l'artiste deja existant à partir de l'artiste courant
			try {
				Controleur.getInstanceuniquecontroleur().
				getListeArtistes().get(currentArtisteAlbum.getName()).
				mettreAjour(currentArtisteAlbum);
			} catch (ExceptionMiseAjour e) {
				Controleur.getInstanceuniquecontroleur().
				ajouterProbleme(e.getTitre(),e.getMessage());
			}
			//on ajoute l'artiste deja existant à l'album de la chanson
			currentAlbum.setArtiste(Controleur.getInstanceuniquecontroleur().
					getListeArtistes().get(currentArtisteAlbum.getName()));
		}
		//si l'artiste n'existait pas deja, on ajoute à l'album de la chanson
		//celui que l'on vient de creer
		else {
			currentAlbum.setArtiste(currentArtisteAlbum);
			//et on ajoute l'artiste à la liste des artistes du controleur
			Controleur.getInstanceuniquecontroleur().ajouter(currentArtisteAlbum);
		}
		
		//currentAlbum.setArtiste(Controleur.getInstanceuniquecontroleur().
		//		getListeArtistes().get(currentArtisteAlbum.getName()));
		

		Controleur.getInstanceuniquecontroleur()
		.ajouterProbleme("ùùùù2","@@@@@@@@@" + 
				currentArtisteAlbum + 
				currentAlbum +Controleur.getInstanceuniquecontroleur().
				getListeArtistes().get(currentArtisteAlbum.getName())+ 
				currentAlbum.getArtiste());
		
		Controleur.getInstanceuniquecontroleur()
		.ajouterProbleme("ùùùù2","@@@@@@@@@" + currentArtisteAlbum + currentAlbum +Controleur.getInstanceuniquecontroleur().
				getListeArtistes().get(currentArtisteAlbum.getName()));
	}


	/**
	 * Methode qui gere l'ajout d'un nouvel album dans une chanson
	 * il faut verifier que l'album n'existe pas deja dans le controleur
	 * et si c'est le cas, on propose une mise a jour de l'album
	 * dans le cas contraire, on doit ajouter l'album à la liste d'albums
	 * du controleur
	 */
	public void gererAjoutAlbum(){
		//on veut ajouter un album à la liste d'albums de la chanson courante, 
		//on verifie que l'album n'existe pas deja:
		if(Controleur.getInstanceuniquecontroleur().existeDeja(currentAlbum)){
			//on propose une mise a jour de l'album deja existant à partir de l'album courant
			try {
				Controleur.getInstanceuniquecontroleur().
				getListeAlbums().get(currentAlbum.getUrl()).
				mettreAjour(currentAlbum);
			} catch (ExceptionMiseAjour e) {
				Controleur.getInstanceuniquecontroleur().
				ajouterProbleme(e.getTitre(),e.getMessage());
			}
			//on ajoute l'album deja existant à la liste des albums de la chanson
			listeAlbums.add(Controleur.getInstanceuniquecontroleur().
					getListeAlbums().get(currentAlbum.getUrl()));
		}
		//si l'artiste n'existait pas deja, on ajoute à la liste d'albums
		//de la chanson celui que l'on vient de creer
		else {
			listeAlbums.add(currentAlbum);
			//et on ajoute l'album à la liste des artistes du controleur
			Controleur.getInstanceuniquecontroleur().ajouter(currentAlbum);
		}
	}



	/**
	 * Methode qui gere l'ajout d'un nouveau tag dans la liste de tags
	 * de la chanson courante
	 * il faut verifier que le tag n'existe pas deja dans le controleur
	 * dans le cas contraire, on doit ajouter le tag à la liste de tags
	 * du controleur
	 */
	public void gererAjoutTag(){
		//on veut ajouter un tag à la liste de tags de la chanson courante, 
		//on verifie que le tag n'existe pas deja:
		if(Controleur.getInstanceuniquecontroleur().existeDeja(currentTag)){
			listeTags.add(Controleur.getInstanceuniquecontroleur().
					getListeTags().get(currentTag.getUrl()));
		}
		//si le tag n'existait pas deja, on ajoute à la liste de
		//tags de l'album celui que l'on vient de creer
		else {
			listeTags.add(currentTag);
			//et on ajoute le tag à la liste des tags du controleur
			Controleur.getInstanceuniquecontroleur().ajouter(currentTag);
		}
	}

	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	public Chanson getChanson() {
		return chanson;
	}



}
