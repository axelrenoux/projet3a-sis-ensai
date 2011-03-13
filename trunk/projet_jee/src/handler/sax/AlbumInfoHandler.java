/**
 * 
 */
package handler.sax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;



import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import controleur.Controleur;
import exceptions.ExceptionMiseAjour;

import metier.Album;
import metier.Artiste;
import metier.Chanson;
import metier.Tag;
import metier.Wiki;
 

/**
 * @author Administrateur
 *
 */
public class AlbumInfoHandler extends DefaultHandler{

/*
	  <album>
	  		<name>Believe</name> 
	  		<artist>Cher</artist> 
	  		<id>2026126</id> 
	  		<url>http://www.last.fm/music/Cher/Believe</url> 
	  		<releasedate>6 Apr 1999, 00:00</releasedate> 
	  		<image size="small">http://userserve-ak.last.fm/serve/34s/42806845.png</image> 
	  		<image size="medium">http://userserve-ak.last.fm/serve/64s/42806845.png</image> 
			<image size="large">http://userserve-ak.last.fm/serve/174s/42806845.png</image> 
			<image size="extralarge">http://userserve-ak.last.fm/serve/300x300/42806845.png</image> 
			<image size="mega">http://userserve-ak.last.fm/serve/_/42806845/Believe++600++600+PNG.png</image> 
			<listeners>175689</listeners> 
			<playcount>793010</playcount> 
			<tracks>
				<track rank="1">
					<name>Believe</name> 
				    <duration>239</duration> 
				    <url>http://www.last.fm/music/Cher/_/Believe</url> 
				    <artist>
					    <name>Cher</name> 
					    <url>http://www.last.fm/music/Cher</url> 
					</artist>
			    </track>
		    </tracks>
	        <toptags>
			  <tag>
				  <name>pop</name> 
				  <url>http://www.last.fm/tag/pop</url> 
			  </tag>
			</toptags>
			<wiki>
				  <published>Sat, 6 Mar 2010 16:48:03 +0000</published> 
				  <summary></summary>
				  <content></content>
		    </wiki>
		</album>
*/

	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	//résultats de notre parsing
	private Album album;
	   
	// objets courants  
	private Chanson currentChanson;
	private ArrayList<Chanson> listeChansons;
	private Artiste currentArtisteChanson;
	private Tag currentTag;
	private ArrayList<Tag> listeTags;
	private Wiki currentWiki;
	
	//flags nous indiquant la position du parseur
	private boolean inAlbum;
	private boolean inListeners;
	private boolean inPlaycount;
	private boolean inTracks;
	private boolean inTrack;
	private boolean inNameChanson;
	private boolean inDuration;
	private boolean inUrlChanson;
	private boolean inArtisteChanson;
	private boolean inNameArtisteChanson;
	private boolean inURLArtisteChason;
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
	public AlbumInfoHandler(Album album){
		super();
		this.album = album;
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
		if(qName.equals("album")){
			inAlbum = true;
		}else if(qName.equals("listeners")){
			inListeners = true;	
		}else if(qName.equals("playcount")){
			inPlaycount = true;	
		}else if(qName.equals("tracks")){
			listeChansons = new ArrayList<Chanson>();
			inTracks = true;	
		}else if(qName.equals("track")){
			inTrack = true;	
			currentChanson = new Chanson();
		}else if(qName.equals("name") && inTrack && !inArtisteChanson){
			inNameChanson = true;
		}else if(qName.equals("duration")){
			inDuration = true;
		}else if(qName.equals("url") && inTrack){
			inUrlChanson = true;
		}else if(qName.equals("artist") && inTrack){
			inArtisteChanson = true;
			currentArtisteChanson = new Artiste();
		}else if(qName.equals("name") && inArtisteChanson){
			inNameArtisteChanson = true;
		}else if(qName.equals("url") && inArtisteChanson){
			inURLArtisteChason = true;	
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
		}else if(qName.equals("name")){
		}else{
	    	  //System.out.println("Balise non traitee pour le moment : " + qName);
		}
	}


	//a la fin de chaque balise, on ajoute les elements où il faut
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 * methode appellee a la fermeture d une balise
	 */
	public void endElement(String uri,
			String localName,
			String qName)
	throws SAXException{
		if(qName.equals("album")){
			inAlbum = false;
		}else if(qName.equals("listeners")){
			inListeners = false;	
		}else if(qName.equals("playcount")){
			inPlaycount = false;	
		}else if(qName.equals("tracks")){
			inTracks = false;	
			album.setChansons(listeChansons);
		}else if(qName.equals("track")){
			inTrack = false;	
			gererAjoutChanson();
			//listeChansons.add(currentChanson);//TODO
		}else if(qName.equals("name") && inTrack && !inArtisteChanson){
			inNameChanson = false;
		}else if(qName.equals("duration")){
			inDuration = false;
		}else if(qName.equals("url") && inTrack){
			inUrlChanson = false;
		}else if(qName.equals("artist") && inTrack){
			inArtisteChanson = false;
			
			gererAjoutArtisteChanson();
			//currentChanson.setArtiste(currentArtisteChanson);
		}else if(qName.equals("name") && inArtisteChanson){
			inNameArtisteChanson = false;
		}else if(qName.equals("url") && inArtisteChanson){
			inURLArtisteChason = false;	
		}else if(qName.equals("toptags")){
			inTopTags = false;
			album.setToptags(listeTags);
		}else if(qName.equals("tag")){
			inTag = false;	
			gererAjoutTag();
			//listeTags.add(currentTag);
		}else if(qName.equals("name") && inTag){
			inNameTag = false;	
		}else if(qName.equals("url") && inTag){
			inUrlTag = false;
		}else if(qName.equals("wiki")){
			inWiki = false;
			album.setWiki(currentWiki);
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
		if(inListeners){
			album.setListeners(Double.parseDouble(lecture));
		}else if(inPlaycount){
			album.setPlaycount(Double.parseDouble(lecture)); 
		}else if(inNameChanson){
			currentChanson.setName(lecture);
		}else if(inDuration){
			currentChanson.setDuree(Double.parseDouble(lecture));
		}else if(inUrlChanson){
			currentChanson.setUrl(lecture);
		}else if(inNameArtisteChanson){
			 currentArtisteChanson.setName(lecture);
		}else if(inURLArtisteChason){
			 currentArtisteChanson.setUrl(lecture);	
		}else if(inNameTag){
			currentTag.setName(lecture);
		}else if(inUrlTag){
			  currentTag.setUrl(lecture);
		}else if(inPublished){
			currentWiki.setDatePublication(lecture);	 	
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
		System.out.println("Début du parsing get info");
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 * methode appellee a la fin du parsing du fichier
	 */
	public void endDocument() throws SAXException {
		System.out.println("Fin du parsing get info");
	}

	
	
	
	/**
	 * Methode qui gere l'ajout d'un nouvel artiste dans une chanson d'album
	 * il faut verifier que l'artiste n'existe pas deja dans le controleur
	 * et si c'est le cas, on propose une mise a jour de l'artiste
	 * dans le cas contraire, on doit ajouter l'artiste à la liste d'artistes
	 * du controleur
	 */
	public void gererAjoutArtisteChanson(){
		//on veut ajouter un artiste à une chanson de l'album courant, 
		//on verifie que l'artiste n'existe pas deja:
		if(Controleur.getInstanceuniquecontroleur().existeDeja(currentArtisteChanson)){
			//on propose une mise a jour de l'artiste deja existant à partir de l'artiste courant
			try {
				Controleur.getInstanceuniquecontroleur().
				getListeArtistes().get(currentArtisteChanson.getName()).
				mettreAjour(currentArtisteChanson);
			} catch (ExceptionMiseAjour e) {}
			//on ajoute l'artiste deja existant, à la chanson de l'album
			currentChanson.setArtiste(Controleur.getInstanceuniquecontroleur().
					getListeArtistes().get(currentArtisteChanson.getName()));
		}
		//si l'artiste n'existait pas deja, on ajoute à 
		//la chanson de l'album celui que l'on vient de creer
		else {
			currentChanson.setArtiste(currentArtisteChanson);
			//et on ajoute l'artiste à la liste des artistes du controleur
			Controleur.getInstanceuniquecontroleur().ajouter(currentArtisteChanson);
		}
	}
	
	
	/**
	 * Methode qui gere l'ajout d'une nouvelle chanson dans la
	 * liste de chansons de l'album courant
	 * il faut verifier que la chanson n'existe pas deja dans le controleur
	 * et si c'est le cas, on propose une mise a jour de la chanson
	 * dans le cas contraire, on doit ajouter la chanson à la liste de chansons
	 * du controleur
	 */
	public void gererAjoutChanson(){
		//on veut ajouter une chanson à la liste de chansons de l'album courant, 
		//on verifie que la chanson n'existe pas deja:
		if(Controleur.getInstanceuniquecontroleur().existeDeja(currentChanson)){
			//on propose une mise a jour de la chanson deja existante
			// à partir de la chanson courante
			try {
				Controleur.getInstanceuniquecontroleur().
				getListeChansons().get(currentChanson.getUrl()).
				mettreAjour(currentChanson);
			} catch (ExceptionMiseAjour e) {}
			//on ajoute la chanson deja existante à la liste 
			//de chansons de l'album
			listeChansons.add(Controleur.getInstanceuniquecontroleur().
					getListeChansons().get(currentChanson.getUrl()));
		}
		//si la chanson n'existait pas deja, on ajoute à la liste de
		//chansons de l'album la chanson que l'on vient de creer
		else {
			listeChansons.add(currentChanson);
			//et on ajoute la chanson à la liste de chansons du controleur
			Controleur.getInstanceuniquecontroleur().ajouter(currentChanson);
		}
	}

	
	
	/**
	 * Methode qui gere l'ajout d'un nouveau tag dans la liste de tags
	 * de l'album courant
	 * il faut verifier que le tag n'existe pas deja dans le controleur
	 * dans le cas contraire, on doit ajouter le tag à la liste de tags
	 * du controleur
	 */
	public void gererAjoutTag(){
		//on veut ajouter un tag à la liste de tags de l'album courant, 
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
	
	public Album getAlbum() {
		return album;
	}



}
