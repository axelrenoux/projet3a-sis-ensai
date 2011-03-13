 package metier;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.ExceptionMiseAjour;
 
public class Chanson {


	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private String ID;
	private String name;
	private Double duree;
	private String url;
	private Artiste artiste;
	private String imageSmall;
	private String imageMedium;
	private String imageLarge;
	private String imageExtraLarge;
	private String imageMega;
	private double listeners;//nb de personnes ayant écouté la chanson
	private double playcount;//nb de fois où la chanson a été écoutée
	private HashMap<String,Album> albums;//contient chaque album contenant la chanson, et son rang
	private ArrayList<Tag> toptags;
	private Wiki wiki;
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	/**
	 * constructeur vide
	 */
	public Chanson() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
 
	
	
	/**
	 * constructeur avec parametres
	 * @param name
	 * @param duree
	 * @param url
	 * @param artiste
	 * @param listeners
	 * @param playcount
	 * @param albums
	 * @param toptags
	 * @param wiki
	 */
	public Chanson(String name, Double duree, String url,
			Artiste artiste,double listeners,double playcount,
			HashMap<String,Album> albums,
			ArrayList<Tag> toptags,Wiki wiki) {
		super();
		this.name = name;
		this.duree = duree;
		this.url=url;
		this.artiste = artiste;
		this.listeners=listeners; 
		this.playcount=playcount; 
		this.albums = albums;
		this.toptags=toptags;
		this.wiki=wiki; 
	}








	
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	 
	
	/**
	 * methode qui met a jour une chanson à partir d'une autre pour la completer
	 * @param chansonProposee
	 * @throws ExceptionMiseAjour
	 */
	public void mettreAjour(Chanson chansonProposee) throws ExceptionMiseAjour{
		//on commence par verifier la coherence de la mise a jour: 
		if(!verifierCoherence(this, chansonProposee))throw new ExceptionMiseAjour(this,chansonProposee);
				
		if(this.name==null) this.name = chansonProposee.getName();
		if(this.ID==null)this.ID = chansonProposee.getID();  
		if(this.duree==null)this.duree = chansonProposee.getDuree();  
		if(this.url==null)this.url = chansonProposee.getUrl();
		if(this.artiste==null)this.artiste = chansonProposee.getArtiste();
		if(this.imageSmall==null) this.imageSmall = chansonProposee.getImageSmall();
		if(this.imageMedium==null) this.imageMedium=chansonProposee.getImageMedium();
		if(this.imageLarge==null) this.imageLarge=chansonProposee.getImageLarge();
		if(this.imageExtraLarge==null)this.imageExtraLarge=chansonProposee.getImageExtraLarge();
		if(this.imageMega==null) this.imageMega=chansonProposee.getImageMega();
		if(this.listeners==0.0)this.listeners = chansonProposee.getListeners();
		if(this.playcount==0.0)this.playcount = chansonProposee.getPlaycount();
		if(this.albums ==null) this.albums= chansonProposee.getAlbums();
		if(this.toptags==null)this.toptags = chansonProposee.getToptags();
		if(this.wiki==null)this.wiki = chansonProposee.getWiki();
		
	}
	
	/**
	 * methode qui verifie la concordance entre 2 artistes:
	 * si les url ne sont pas nulles, elles doivent correspondre,
	 * sinon ce sont les noms qui doivent correspondre
	 * @param a1
	 * @param a2
	 * @return
	 */
	private boolean verifierCoherence(Chanson c1, Chanson c2){
		boolean retour=false;
		if(c1.getUrl()!=null && c2.getUrl()!=null){
			if (c1.getUrl().equals(c2.getUrl())){
				retour=true;
			}else retour= false;
		}
		else if(c1.getName()!=null && c2.getName()!=null){
			if(c1.getName().equals(c2.getName())){
				retour=true;
			}else retour= false;
		}
		return retour;
	}
	
	
	public String toString() {
		String descrip;
		descrip = "\n " + "name " + name + "\n " 
		+ " DUREE " +  duree + "\n "
		+ " url " +  url+ "\n "
		+ "listeners "+ listeners+ "\n "
		+ "playcount " + playcount + "\n "
		+ "image mega " + imageMega + "\n ";
		
		
		try{
				descrip+="artiste "+ artiste.getName()+ "\n ";
				descrip+="nb albums "+ albums.size()+ "\n ";
				descrip+="nb tags "+ toptags.size()+ "\n ";
				descrip+="resume wiki "+ wiki.getResume()+ "\n ";
		}
		catch (NullPointerException e){
		}
		return descrip;	
	}
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/
	




	public String getName() {
		return name;
	}






	public void setName(String name) {
		this.name = name;
	}






	public Double getDuree() {
		return duree;
	}






	public void setDuree(Double duree) {
		this.duree = duree;
	}






	public String getUrl() {
		return url;
	}






	public void setUrl(String url) {
		this.url = url;
	}






	public Artiste getArtiste() {
		return artiste;
	}






	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}






	public double getListeners() {
		return listeners;
	}






	public void setListeners(double listeners) {
		this.listeners = listeners;
	}






	public double getPlaycount() {
		return playcount;
	}






	public void setPlaycount(double playcount) {
		this.playcount = playcount;
	}






	public HashMap<String, Album> getAlbums() {
		return albums;
	}






	public void setAlbums(HashMap<String, Album> albums) {
		this.albums = albums;
	}






	public ArrayList<Tag> getToptags() {
		return toptags;
	}






	public void setToptags(ArrayList<Tag> toptags) {
		this.toptags = toptags;
	}






	public Wiki getWiki() {
		return wiki;
	}






	public void setWiki(Wiki wiki) {
		this.wiki = wiki;
	}






	public String getImageSmall() {
		return imageSmall;
	}






	public void setImageSmall(String imageSmall) {
		this.imageSmall = imageSmall;
	}






	public String getImageMedium() {
		return imageMedium;
	}






	public void setImageMedium(String imageMedium) {
		this.imageMedium = imageMedium;
	}






	public String getImageLarge() {
		return imageLarge;
	}






	public void setImageLarge(String imageLarge) {
		this.imageLarge = imageLarge;
	}






	public String getImageExtraLarge() {
		return imageExtraLarge;
	}






	public void setImageExtraLarge(String imageExtraLarge) {
		this.imageExtraLarge = imageExtraLarge;
	}






	public String getImageMega() {
		return imageMega;
	}






	public void setImageMega(String imageMega) {
		this.imageMega = imageMega;
	}






	public String getID() {
		return ID;
	}






	public void setID(String iD) {
		ID = iD;
	}










	


}
