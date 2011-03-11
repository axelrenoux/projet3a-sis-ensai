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




	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	//résultats de notre parsing
	private Album album;
	   
	// objets courants pour chaque nouvelle balise "Objet"
	private Chanson currentChanson;
	private HashMap<String,Chanson> listeChansons;
	private String rangCurrentChanson;
	private Artiste currentArtisteChanson;
	private Tag currentTag;
	private ArrayList<Tag> listeTags;
	private Wiki currentWiki;
	
	//flags nous indiquant la position du parseur
	private boolean inAlbum;
	private boolean inNameAlbum;
	private boolean inArtisteAlbum;
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
			listeChansons = new HashMap<String,Chanson>();
			inTracks = true;	
		}else if(qName.equals("track")){
			inTrack = true;	
			currentChanson = new Chanson();
			rangCurrentChanson = attributes.getValue(0);
		}else if(qName.equals("name") && inTrack){
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
		}else{
	    	  System.out.println("Balise non traitee pour le moment : " + qName);
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
			listeChansons.put(rangCurrentChanson,currentChanson);
		}else if(qName.equals("name") && inTrack){
			inNameChanson = false;
		}else if(qName.equals("duration")){
			inDuration = false;
		}else if(qName.equals("url") && inTrack){
			inUrlChanson = false;
		}else if(qName.equals("artist") && inTrack){
			inArtisteChanson = false;
			currentChanson.setArtiste(currentArtisteChanson);
		}else if(qName.equals("name") && inArtisteChanson){
			inNameArtisteChanson = false;
		}else if(qName.equals("url") && inArtisteChanson){
			inURLArtisteChason = false;	
		/*}else if(qName.equals("toptags")){
			inTopTags = false;
			album.setToptags(listeTags);*/
		}else if(qName.equals("tag")){
			inTag = false;	
			listeTags.add(currentTag);
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
		System.out.println("Début du parsing");
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 * methode appellee a la fin du parsing du fichier
	 */
	public void endDocument() throws SAXException {
		System.out.println("Fin du parsing");
		System.out.println("Resultats du parsing");

		/*for (Film monFilm : lstFilm) {
			System.out.println(monFilm);
	   	  }*/

	}

	
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/
	
	public Album getAlbum() {
		return album;
	}



}
