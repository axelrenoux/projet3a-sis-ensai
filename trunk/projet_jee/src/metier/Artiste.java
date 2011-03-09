 package metier;

import java.util.ArrayList;



public class Artiste {


	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private String name;
	private String url;
	private String imageSmall;
	private String imageMedium;
	private String imageLarge;
	private String imageExtraLarge;
	private String imageMega;
	private double listeners;//nb de personnes ayant écouté la chanson
	private double playcount;//nb de fois où la chanson a été écoutée
	private ArrayList<Artiste> artistesSimilaires;
	private ArrayList<Tag> toptags;
	private Wiki wiki;
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	/**
	 * constructeur vide
	 */
	public Artiste() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
 
	
	
	/**
	 * constructeur avec les parametres
	 * @param name
	 * @param url
	 * @param imageSmall
	 * @param imageMedium
	 * @param imageLarge
	 * @param imageExtraLarge
	 * @param imageMega
	 * @param listeners
	 * @param playcount
	 * @param artistesSimilaires
	 * @param toptags
	 * @param wiki
	 */
	public Artiste(String name, String url,
			String imageSmall,String imageMedium,String imageLarge,
			String imageExtraLarge,String imageMega,double listeners,
			double playcount,ArrayList<Artiste> artistesSimilaires,
			ArrayList<Tag> toptags,Wiki wiki) {
		super();
		this.name = name;
		this.url=url;
		this.imageSmall=imageSmall;
		this.imageMedium=imageMedium;
		this.imageLarge=imageLarge;
		this.imageExtraLarge=imageExtraLarge;
		this.imageMega=imageMega;
		this.listeners=listeners; 
		this.playcount=playcount; 
		this.artistesSimilaires = artistesSimilaires;
		this.toptags=toptags;
		this.wiki=wiki; 
	}




	
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	@Override
	public String toString() {
		return "Artiste [name=" + name + ", url=" + url + ", imageSmall="
				+ imageSmall + ", imageMedium=" + imageMedium + ", imageLarge="
				+ imageLarge + ", imageExtraLarge=" + imageExtraLarge
				+ ", imageMega=" + imageMega + ", listeners=" + listeners
				+ ", playcount=" + playcount + ", artistesSimilaires="
				+ artistesSimilaires + ", toptags=" + toptags + ", wiki=" + wiki
				+ "]";
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






	public String getUrl() {
		return url;
	}






	public void setUrl(String url) {
		this.url = url;
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






	public ArrayList<Artiste> getArtistesSimilaires() {
		return artistesSimilaires;
	}






	public void setArtistesSimilaires(ArrayList<Artiste> artistesSimilaires) {
		this.artistesSimilaires = artistesSimilaires;
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



}
