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
public class ArtisteInfoHandler extends DefaultHandler{




	/*
<artist>
	<name>Cher</name> 
	<url>http://www.last.fm/music/Cher</url> 
	<stats>
		<listeners>633215</listeners> 
		<playcount>5943460</playcount> 
	</stats>
	<similar>
		<artist>
			<name>Sonny & Cher</name> 
			<url>http://www.last.fm/music/Sonny%2B%2526%2BCher</url> 
			<image size="small">http://userserve-ak.last.fm/serve/34/4987472.jpg</image> 
			<image size="medium">http://userserve-ak.last.fm/serve/64/4987472.jpg</image> 
			<image size="large">http://userserve-ak.last.fm/serve/126/4987472.jpg</image> 
			<image size="extralarge">http://userserve-ak.last.fm/serve/252/4987472.jpg</image> 
			<image size="mega">http://userserve-ak.last.fm/serve/500/4987472/Sonny++Cher.jpg</image> 
		</artist>
	</similar>
	<tags>
		<tag>
	    	<name>pop</name> 
	    	<url>http://www.last.fm/tag/pop</url> 
	    </tag>
	</tags>
	<bio>
		<published>Sat, 11 Dec 2010 23:49:01 +0000</published> 
	  	<summary>
	  		<![CDATA[ Cher (born Cherilyn Sarkisian on May 20, 1946, later adopted by Gilbert LaPierre.) is an American singer, actress, songwriter, author and entertainer. Among her many career accomplishments in music, television and film, she has won an Academy Award, a Grammy Award, an Emmy Award and three Golden Globe Awards among others.  Referred to as the Goddess of Pop, Cher first rose to prominence in 1965 as one half of the pop/rock duo Sonny &amp; Cher.]]> 
	    </summary>
	  	<content>
	    </content>
	 </bio>
</artist>
	 */

	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/

	//résultats de notre parsing
	private Artiste artiste;

	// objets courants 
	private Artiste currentArtistSimilaire;
	private ArrayList<Artiste> listeArtistesSimilaires;
	private Tag currentTag;
	private ArrayList<Tag> listeTags;
	private Wiki currentWiki;



	//flags nous indiquant la position du parseur
	private boolean inListeners;
	private boolean inPlaycount;
	private boolean inSimilar;
	private boolean inArtisteSimilaire;
	private boolean inNameArtisteSimilaire;
	private boolean inURLArtisteSimilaire;
	private boolean inImageSmallArtisteSim;
	private boolean inImageMediumArtisteSim;
	private boolean inImageLargeArtisteSim;
	private boolean inImageExtraLargeArtisteSim;
	private boolean inImageMegaArtisteSim;
	private boolean inTags;
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
	public ArtisteInfoHandler(Artiste artiste){
		super();
		this.artiste = artiste;
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
		if(qName.equals("listeners")){
			inListeners = true;	
		}else if(qName.equals("playcount")){
			inPlaycount = true;	
		}else if(qName.equals("similar")){
			inSimilar = true;
			listeArtistesSimilaires = new ArrayList<Artiste>();
		}else if(qName.equals("artist") && inSimilar){
			inArtisteSimilaire = true;
			currentArtistSimilaire = new Artiste();
		}else if(qName.equals("name") && inArtisteSimilaire){
			inNameArtisteSimilaire = true;
		}else if(qName.equals("url") && inArtisteSimilaire){
			inURLArtisteSimilaire = true;	
		}else if(qName.equals("image") && inArtisteSimilaire){
			if(attributes.getValue(0).equals("small")){
				inImageSmallArtisteSim = true;
			}else if (attributes.getValue(0).equals("medium")){
				inImageMediumArtisteSim = true;
			}else if (attributes.getValue(0).equals("large")){
				inImageLargeArtisteSim = true;
			}else if (attributes.getValue(0).equals("extralarge")){
				inImageExtraLargeArtisteSim = true;
			}else if (attributes.getValue(0).equals("mega")){
				inImageMegaArtisteSim = true;
			}
		}else if(qName.equals("tags")){
			inTags = true;	
			listeTags = new ArrayList<Tag>();
		}else if(qName.equals("tag")){
			inTag = true;	
			currentTag = new Tag();
		}else if(qName.equals("name") && inTag){
			inNameTag = true;	
		}else if(qName.equals("url") && inTag){
			inUrlTag = true;
		}else if(qName.equals("bio")){
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


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 * methode appellee a la fermeture d une balise
	 */
	public void endElement(String uri,
			String localName,
			String qName)
	throws SAXException{
		if(qName.equals("listeners")){
			inListeners = false;	
		}else if(qName.equals("playcount")){
			inPlaycount = false;	
		}else if(qName.equals("similar")){
			inSimilar = false;
			artiste.setArtistesSimilaires(listeArtistesSimilaires);
		}else if(qName.equals("artist") && inSimilar){
			inArtisteSimilaire = false;
			gererAjoutArtisteSimilaire();
			//listeArtistesSimilaires.add(currentArtistSimilaire);
		}else if(qName.equals("name") && inArtisteSimilaire){
			inNameArtisteSimilaire = false;
		}else if(qName.equals("url") && inArtisteSimilaire){
			inURLArtisteSimilaire = false;
		}else if(qName.equals("image") && inArtisteSimilaire){
			inImageSmallArtisteSim = false;
			inImageMediumArtisteSim = false;
			inImageLargeArtisteSim = false;
			inImageExtraLargeArtisteSim = false;
			inImageMegaArtisteSim = false;
		}else if(qName.equals("tags")){
			inTags = false;
			artiste.setToptags(listeTags);
		}else if(qName.equals("tag")){
			inTag = false;	
			//listeTags.add(currentTag);
			gererAjoutTag();
		}else if(qName.equals("name") && inTag){
			inNameTag = false;	
		}else if(qName.equals("url") && inTag){
			inUrlTag = false;
		}else if(qName.equals("bio")){
			inWiki = false;
			artiste.setWiki(currentWiki);
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
			artiste.setListeners(Double.parseDouble(lecture));
		}else if(inPlaycount){
			artiste.setPlaycount(Double.parseDouble(lecture));
		}else if(inNameArtisteSimilaire){
			currentArtistSimilaire.setName(lecture);
		}else if(inURLArtisteSimilaire){
			currentArtistSimilaire.setUrl(lecture);
		}else if(inImageSmallArtisteSim){
			currentArtistSimilaire.setImageSmall(lecture);
		}else if(inImageMediumArtisteSim){
			currentArtistSimilaire.setImageMedium(lecture);
		}else if(inImageLargeArtisteSim){
			currentArtistSimilaire.setImageLarge(lecture);
		}else if(inImageExtraLargeArtisteSim){
			currentArtistSimilaire.setImageExtraLarge(lecture);
		}else if(inImageMegaArtisteSim){
			currentArtistSimilaire.setImageMega(lecture);
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
		System.out.println("Début du parsing get info artiste");
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 * methode appellee a la fin du parsing du fichier
	 */
	public void endDocument() throws SAXException {
		System.out.println("Fin du parsing get info artiste");
	}

	
	
	/**
	 * Methode qui gere l'ajout d'un nouvel artiste dans la liste des
	 * artistes similaires de l'artiste
	 * il faut verifier que l'artiste similaire n'existe pas deja dans 
	 * le controleur et si c'est le cas, on propose une mise a jour de l'artiste
	 * dans le cas contraire, on doit ajouter l'artiste à la liste d'artistes
	 * du controleur
	 */
	public void gererAjoutArtisteSimilaire(){
		//on veut ajouter un artiste similaire à la liste d'artistes similaires
		//de l'artiste courant
		//on verifie que l'artiste n'existe pas deja:
		if(Controleur.getInstanceuniquecontroleur().existeDeja(currentArtistSimilaire)){
			//on propose une mise a jour de l'artiste deja existant à partir de l'artiste courant
			try {
				Controleur.getInstanceuniquecontroleur().
				getListeArtistes().get(currentArtistSimilaire.getName()).
				mettreAjour(currentArtistSimilaire);
			} catch (ExceptionMiseAjour e) {}
			//on ajoute l'artiste deja existant dans la liste d'artistes similaires
			listeArtistesSimilaires.add(Controleur.getInstanceuniquecontroleur().
					getListeArtistes().get(currentArtistSimilaire.getName()));
		}
		//si l'artiste n'existait pas deja, on ajoute dans la liste d'artistes sim
		//celui que l'on vient de creer
		else {
			listeArtistesSimilaires.add(currentArtistSimilaire);
			//et on ajoute l'artiste à la liste des artistes du controleur
			Controleur.getInstanceuniquecontroleur().ajouter(currentArtistSimilaire);
		}
	}

	/**
	 * Methode qui gere l'ajout d'un nouveau tag dans la liste de tags
	 * de l'artiste courant
	 * il faut verifier que le tag n'existe pas deja dans le controleur
	 * dans le cas contraire, on doit ajouter le tag à la liste de tags
	 * du controleur
	 */
	public void gererAjoutTag(){
		//on veut ajouter un tag à la liste de tags de l'artiste courant, 
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

	public Artiste getArtiste() {
		return artiste;
	}



}
