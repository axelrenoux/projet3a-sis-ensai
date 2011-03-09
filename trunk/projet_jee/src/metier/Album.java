 package metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Album {


	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private String name;
	private Artiste artiste;
	private String url;
	private Date date;
	private String imageSmall;
	private String imageMedium;
	private String imageLarge;
	private String imageExtraLarge;
	private String imageMega;
	private double listeners;//nb de personnes ayant écouté l'album
	private double playcount;//nb de fois où l'album a été écouté
	private HashMap<String,Chanson> chansons;//contient chaque chanson et son rang
	private ArrayList<Tag> toptags;
	private Wiki wiki;
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	/**
	 * constructeur vide
	 */
	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * constructeur avec les parametres
	 * @param name
	 * @param artiste
	 * @param url
	 * @param date
	 * @param imageSmall
	 * @param imageMedium
	 * @param imageLarge
	 * @param imageExtraLarge
	 * @param imageMega
	 * @param listeners
	 * @param playcount
	 * @param chansons
	 * @param toptags
	 * @param wiki
	 */
	public Album(String name, Artiste artiste, String url,Date date,
			String imageSmall,String imageMedium,String imageLarge,
			String imageExtraLarge,String imageMega,double listeners,
			double playcount,HashMap<String,Chanson> chansons,
			ArrayList<Tag> toptags,Wiki wiki) {
		super();
		this.name = name;
		this.artiste=artiste;
		this.url=url;
		this.date=date;
		this.imageSmall=imageSmall;
		this.imageMedium=imageMedium;
		this.imageLarge=imageLarge;
		this.imageExtraLarge=imageExtraLarge;
		this.imageMega=imageMega;
		this.listeners=listeners; 
		this.playcount=playcount; 
		this.chansons=chansons; 
		this.toptags=toptags;
		this.wiki=wiki; 
	}




	
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	@Override
	public String toString() {
		return "Album [name=" + name + ", artiste=" + artiste + ", url=" + url
				+ ", date=" + date + ", imageSmall=" + imageSmall
				+ ", imageMedium=" + imageMedium + ", imageLarge=" + imageLarge
				+ ", imageExtraLarge=" + imageExtraLarge + ", imageMega="
				+ imageMega + ", listeners=" + listeners + ", playcount="
				+ playcount + ", chansons=" + chansons + ", toptags=" + toptags
				+ ", wiki=" + wiki + "]";
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



	public Artiste getArtiste() {
		return artiste;
	}



	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
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



	public HashMap<String, Chanson> getChansons() {
		return chansons;
	}



	public void setChansons(HashMap<String, Chanson> chansons) {
		this.chansons = chansons;
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