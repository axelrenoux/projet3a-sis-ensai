/**
 * 
 */
package handler.sax;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import controleur.Controleur;
import exceptions.ExceptionMiseAjour;

import metier.Artiste;
import metier.Chanson;
 

/**
 * @author Administrateur
 *
 */
public class ChansonSearchHandler extends DefaultHandler{




	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	//r�sultats de notre parsing
	private List<Chanson> lstChanson;
	   
	// personne courante pour chaque nouvelle balise Personne
	private Chanson currentChanson;
	private Artiste currentArtiste;
	
	//flags nous indiquant la position du parseur
	private boolean inTrack;
	private boolean inName;
	private boolean inArtiste;
	private boolean inURL;
	private boolean inListeners;
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
	public ChansonSearchHandler(){
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
		if(qName.equals("trackmatches")){
			lstChanson = new LinkedList<Chanson>();
		}else if(qName.equals("track")){
			currentChanson = new Chanson();
			inTrack = true;	
		}else if(qName.equals("name")){
			inName = true;	
		}else if(qName.equals("artist")){
			currentArtiste = new Artiste();
			inArtiste = true;	
		}else if(qName.equals("url")){
			inURL = true;	
		}else if(qName.equals("listeners")){
			inListeners = true;	
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
		if(qName.equals("track")){
			lstChanson.add(currentChanson);
			inTrack = false;	
		}else if(qName.equals("name")){
			inName = false;	
		}else if(qName.equals("artist")){
			inArtiste = false;	
			gererAjoutArtiste();
		}else if(qName.equals("url")){
			inURL = false;	
		}else if(qName.equals("listeners")){
			inListeners = false;	
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
			currentChanson.setName(lecture);
		}else if(inArtiste){
			currentArtiste.setName(lecture);
			//currentChanson.setArtiste(currentArtiste);
		}else if(inURL){
			currentChanson.setUrl(lecture);
		}else if(inListeners){
			currentChanson.setListeners(Double.parseDouble(lecture));
		}else if(inImageSmall){
			currentChanson.setImageSmall(lecture);
		}else if(inImageMedium){
			currentChanson.setImageMedium(lecture);
		}else if(inImageLarge){
			currentChanson.setImageLarge(lecture);
		}else if(inImageExtraLarge){
			currentChanson.setImageExtraLarge(lecture);
		}else if(inImageMega){
			currentChanson.setImageMega(lecture);
		}
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 * methode appellee au debut du parsing du fichier
	 */
	public void startDocument() throws SAXException {
		System.out.println("D�but du parsing");
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 * methode appellee a la fin du parsing du fichier
	 */
	public void endDocument() throws SAXException {
		System.out.println("Fin du parsing");
		 

	}
	
	/**
	 * Methode qui gere l'ajout d'un nouvel artiste dans une chanson
	 * il faut verifier que l'artiste n'existe pas deja dans le controleur
	 * et si c'est le cas, on propose une mise a jour de l'artiste
	 * dans le cas contraire, on doit ajouter l'artiste � la liste d'artistes
	 * du controleur
	 */
	public void gererAjoutArtiste(){
		//on veut ajouter un artiste � la chanson courante, 
		//on verifie que l'artiste n'existe pas deja:
		if(Controleur.getInstanceuniquecontroleur().existeDeja(currentArtiste)){
			//on propose une mise a jour de l'artiste deja existant � partir de l'artiste courant
			try {
				Controleur.getInstanceuniquecontroleur().
				getListeArtistes().get(currentArtiste.getName()).
				mettreAjour(currentArtiste);
			} catch (ExceptionMiseAjour e) {}
			//on ajoute l'artiste deja existant, � la chanson
			currentChanson.setArtiste(Controleur.getInstanceuniquecontroleur().
					getListeArtistes().get(currentArtiste.getName()));
		}
		//si l'artiste n'existait pas deja, on ajoute � la chanson
		//celui que l'on vient de creer
		else {
			currentChanson.setArtiste(currentArtiste);
			//et on ajoute l'artiste � la liste des artistes du controleur
			Controleur.getInstanceuniquecontroleur().ajouter(currentArtiste);
		}
	}

	
	 
	
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/
	

	public List<Chanson> getLstChanson() {
		return lstChanson;
	}

}
