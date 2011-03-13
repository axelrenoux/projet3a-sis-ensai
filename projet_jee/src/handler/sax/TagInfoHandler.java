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
public class TagInfoHandler extends DefaultHandler{



/*
	<tag>
		<name>pop</name> 
		<url>http://www.last.fm/tag/pop</url> 
		<reach>169345</reach> 
		<taggings>1414658</taggings> 
		<wiki>
			<published>Sat, 3 Jul 2010 18:59:46 +0000</published> 
			<summary></summary>
			<content></content>
		</wiki>
	</tag>
*/

	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/

	//résultats de notre parsing
	private Tag tag;

	// objets courants 
	private Wiki currentWiki;



	//flags nous indiquant la position du parseur
	private boolean inReach;
	private boolean inTaggings;
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
	public TagInfoHandler(Tag tag){
		super();
		this.tag = tag;
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
		if(qName.equals("reach")){
			inReach = true;	
		}else if(qName.equals("taggings")){
			inTaggings = true;	
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
		if(qName.equals("reach")){
			inReach = false;	
		}else if(qName.equals("taggings")){
			inTaggings = false;	
		}else if(qName.equals("wiki")){
			inWiki = false;
			tag.setWiki(currentWiki);
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
		if(inReach){
			tag.setReach(Double.parseDouble(lecture));
		}else if(inTaggings){
			tag.setTagging(Double.parseDouble(lecture));
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
		System.out.println("Début du parsing get info tag");
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 * methode appellee a la fin du parsing du fichier
	 */
	public void endDocument() throws SAXException {
		System.out.println("Fin du parsing get info tag");
	}


	

	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	public Tag getTag() {
		return tag;
	}

 

}
