 package metier;

import java.util.ArrayList;
import java.util.HashMap;
 
public class Chanson {


	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private String name;
	private Double duree;
	private String url;
	private Artiste artiste;
	private String imageSmall;
	private String imageMedium;
	private String imageLarge;
	private String imageExtraLarge;
	private String imageMega;
	private double listeners;//nb de personnes ayant �cout� la chanson
	private double playcount;//nb de fois o� la chanson a �t� �cout�e
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
	public Chanson(String name, Double duree,String url,
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
	
	 
	@Override
	public String toString() {
		return "Chanson [name=" + name + ", duree=" + duree + ", url=" + url
				+ ", artiste=" + artiste + ", listeners=" + listeners
				+ ", playcount=" + playcount + ", albums=" + albums
				+ ", toptags=" + toptags + ", wiki=" + wiki + "]";
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




	


}