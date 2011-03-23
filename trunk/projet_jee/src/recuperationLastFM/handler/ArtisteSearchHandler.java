/**
 * 
 */
package recuperationLastFM.handler;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
 

/**
 * @author Administrateur
 *
 */
public class ArtisteSearchHandler extends DefaultHandler{


	    
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	//résultats de notre parsing
	private List<Artiste> lstArtistes;

	   
	// personne courante pour chaque nouvelle balise Personne
	private Artiste currentArtist;
	
	//flags nous indiquant la position du parseur
	private boolean inArtistmatches;
	private boolean inArtiste;
	private boolean inName;
	private boolean inListeners;
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
	public ArtisteSearchHandler(){
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
		if(qName.equals("artistmatches")){
			lstArtistes = new LinkedList<Artiste>();
		}else if(qName.equals("artist")){
			currentArtist = new Artiste();
			inArtiste = true;	
		}else if(qName.equals("name")){
			inName = true;	
		}else if(qName.equals("listeners")){
			inListeners = true;	
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
		if(qName.equals("artist")){
			lstArtistes.add(currentArtist);
			inArtiste = false;	
		}else if(qName.equals("name")){
			inName = false;	
		}else if(qName.equals("listeners")){
			inListeners = false;	
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
			currentArtist.setName(lecture);
		}else if(inListeners){
			currentArtist.setListeners(Integer.parseInt(lecture));
		}else if(inURL){
			currentArtist.setUrl(lecture);
		}else if(inImageSmall){
			currentArtist.setImageSmall(lecture);
		}else if(inImageMedium){
			currentArtist.setImageMedium(lecture);
		}else if(inImageLarge){
			currentArtist.setImageLarge(lecture);
		}else if(inImageExtraLarge){
			currentArtist.setImageExtraLarge(lecture);
		}else if(inImageMega){
			currentArtist.setImageMega(lecture);
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
		

	}
	
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/
	

	public List<Artiste> getLstArtistes() {
		return lstArtistes;
	}

}
