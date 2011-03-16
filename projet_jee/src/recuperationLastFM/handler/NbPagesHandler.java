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
public class NbPagesHandler extends DefaultHandler{




	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	//résultats de notre parsing
	private int nbPagesMax;
	private int nbResultatsTotal;
	private int nbItemsParPage;
	   
	
	//flags nous indiquant la position du parseur
	private boolean inTotalResults;
	private boolean inItemsPerPage;
	
	
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/

	/**
	 * constructeur vide
	 */
	public NbPagesHandler(){
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
		if(qName.equals("opensearch:totalResults")){
			inTotalResults = true;
		}else if(qName.equals("opensearch:itemsPerPage")){
			inItemsPerPage = true;
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
		if(qName.equals("opensearch:totalResults")){
			inTotalResults = false;	
		}else if(qName.equals("opensearch:itemsPerPage")){
			inItemsPerPage = false;	
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
		if(inTotalResults){
			 nbResultatsTotal = Integer.parseInt(lecture);
		}else if(inItemsPerPage){
			nbItemsParPage = Integer.parseInt(lecture);
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
	
	/**
	 * methode qui retourne le nombre de pages de résultats "completes"
	 * @return
	 */
	public int getNbPagesMax() {
		if(nbItemsParPage!=0){
			nbPagesMax = (int) Math.ceil((float)nbResultatsTotal/nbItemsParPage);
		}
		else{
			nbPagesMax = 0;
		}
		return nbPagesMax;
	}

 

}
