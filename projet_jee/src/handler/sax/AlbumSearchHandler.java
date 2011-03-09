/**
 * 
 */
package handler.sax;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import metier.Album;
import metier.Artiste;
 

/**
 * @author Administrateur
 *
 */
public class AlbumSearchHandler extends DefaultHandler{




	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	//résultats de notre parsing
	private List<Album> lstAlbums;
	   
	// personne courante pour chaque nouvelle balise Personne
	private Album currentAlbum;
	private Artiste currentArtist;
	
	//flags nous indiquant la position du parseur
	private boolean inAlbummatches;
	private boolean inAlbum;
	private boolean inName;
	private boolean inArtiste;
	private boolean inURL;
	private boolean inImageSmall;
	private boolean inImageMedium;
	private boolean inImageLarge;
	private boolean inImageExtraLarge;
	private boolean inImageMega;
	
	  
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/

	/**
	 * constructeur vide
	 */
	public AlbumSearchHandler(){
		super();
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
		if(qName.equals("albummatches")){
			lstAlbums = new LinkedList<Album>();
		}else if(qName.equals("album")){
			currentAlbum = new Album();
			inAlbum = true;	
		}else if(qName.equals("name")){
			inName = true;	
		}else if(qName.equals("artist")){
			currentArtist = new Artiste();
			inArtiste = true;	
		}else if(qName.equals("url")){
			inURL = true;	
		}else if(qName.equals("image")){
			if(attributes.getValue(0).equals("small")){
				inImageSmall = true;
			}else if (attributes.getValue(0).equals("medium")){
				inImageMedium = true;
			}else if (attributes.getValue(0).equals("large")){
				inImageLarge = true;
			}else if (attributes.getValue(0).equals("extralarge")){
				inImageExtraLarge = true;
			}else if (attributes.getValue(0).equals("mega")){
				inImageMega = true;
			}
		}else{
	    	  System.out.println("Balise non traitee pour le moment : " + qName);
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
		if(qName.equals("album")){
			lstAlbums.add(currentAlbum);
			inAlbum = false;	
		}else if(qName.equals("name")){
			inName = false;	
		}else if(qName.equals("artist")){
			inArtiste = false;	
		}else if(qName.equals("url")){
			inURL = false;	
		}else if(qName.equals("image")){
			inImageSmall = false;
			inImageMedium = false;
			inImageLarge = false;
			inImageExtraLarge = false;
			inImageMega = false;
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
		if(inName){
			currentAlbum.setName(lecture);
		}else if(inArtiste){
			currentArtist.setName(lecture);
			currentAlbum.setArtiste(currentArtist);
		}else if(inURL){
			currentAlbum.setUrl(lecture);
		}else if(inImageSmall){
			currentAlbum.setImageSmall(lecture);
		}else if(inImageMedium){
			currentAlbum.setImageMedium(lecture);
		}else if(inImageLarge){
			currentAlbum.setImageLarge(lecture);
		}else if(inImageExtraLarge){
			currentAlbum.setImageExtraLarge(lecture);
		}else if(inImageMega){
			currentAlbum.setImageMega(lecture);
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
	

	public List<Album> getLstAlbums() {
		return lstAlbums;
	}

}
