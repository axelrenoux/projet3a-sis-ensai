/**
 * 
 */
package recuperationLastFM.handler;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import controleur.Controleur;
import exceptions.ExceptionMiseAjour;

import metier.oeuvres.Album;
import metier.oeuvres.Artiste;


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
	private boolean inID;
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
		}else if(qName.equals("id")){
			inID = true;	
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
		if(qName.equals("album")){
			lstAlbums.add(currentAlbum);
			inAlbum = false;	
		}else if(qName.equals("name")){
			inName = false;	
		}else if(qName.equals("artist")){
			inArtiste = false;	
		}else if(qName.equals("url")){
			inURL = false;	
		}else if(qName.equals("id")){
			inID = false;	
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
			gererAjoutArtiste();
		}else if(inURL){
			currentAlbum.setUrl(lecture);
		}else if(inID){
			currentAlbum.setID(lecture);
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
		System.out.println("Début du parsing search");
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 * methode appellee a la fin du parsing du fichier
	 */
	public void endDocument() throws SAXException {
		System.out.println("Fin du parsing search");

	}


	/**
	 * Methode qui gere l'ajout d'un nouvel artiste dans un album
	 * il faut verifier que l'artiste n'existe pas deja dans le controleur
	 * et si c'est le cas, on propose une mise a jour de l'artiste
	 * dans le cas contraire, on doit ajouter l'artiste à la liste d'artistes
	 * du controleur
	 */
	public void gererAjoutArtiste(){
		//on veut ajouter un artiste à l'album courant, 
		//on verifie que l'artiste n'existe pas deja:
		if(Controleur.getInstanceuniquecontroleur().existeDeja(currentArtist)){
			//on propose une mise a jour de l'artiste deja existant à partir de l'artiste courant
			try {
				Controleur.getInstanceuniquecontroleur().
				getListeArtistes().get(currentArtist.getName()).
				mettreAjour(currentArtist);
			} catch (ExceptionMiseAjour e) {
				Controleur.getInstanceuniquecontroleur().
				ajouterProbleme(e.getTitre(),e.getMessage());				
			}
			//on ajoute l'artiste deja existant, à l'album
			currentAlbum.setArtiste(Controleur.getInstanceuniquecontroleur().
					getListeArtistes().get(currentArtist.getName()));
		}
		//si l'artiste n'existait pas deja, on ajoute à l'album celui que l'on vient de creer
		else {
			currentAlbum.setArtiste(currentArtist);
			//et on ajoute l'artiste à la liste des artistes du controleur
			Controleur.getInstanceuniquecontroleur().ajouter(currentArtist);
		}
	}





		/********************************************************************/
		/******************      getters / setters       ********************/
		/********************************************************************/


		public List<Album> getLstAlbums() {
			return lstAlbums;
		}

	}
