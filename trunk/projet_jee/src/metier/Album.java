 package metier;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import exceptions.ExceptionMiseAjour;

public class Album {


	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private String name=null;
	private Artiste artiste=null;
	private String ID=null;
	private String url=null;
	private Date date=null;
	private String imageSmall=null;
	private String imageMedium=null;
	private String imageLarge=null;
	private String imageExtraLarge=null;
	private String imageMega=null;
	private double listeners;//nb de personnes ayant écouté l'album
	private double playcount;//nb de fois où l'album a été écouté
	private ArrayList<Chanson> chansons = null;
	private ArrayList<Tag> toptags=null;
	private Wiki wiki=null;
	
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
	 * @param ID
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
	public Album(String name, Artiste artiste, String ID, String url,Date date,
			String imageSmall,String imageMedium,String imageLarge,
			String imageExtraLarge,String imageMega,double listeners,
			double playcount,ArrayList<Chanson> chansons,
			ArrayList<Tag> toptags,Wiki wiki) {
		super();
		this.name = name;
		this.artiste=artiste;
		this.ID = ID;
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
	
	
	/**
	 * methode qui met a jour un album à partir d'un autre pour completer
	 * d'eventuels attributs nuls
	 * @param albumPropose
	 * @throws ExceptionMiseAjour 
	 */
	public void mettreAjour(Album albumPropose) throws ExceptionMiseAjour{
		//on commence par verifier la coherence de la mise a jour: 
		if(!verifierCoherence(this, albumPropose))throw new ExceptionMiseAjour(this,albumPropose);
				
		if(this.name==null) this.name = albumPropose.getName();
		if(this.artiste==null) this.artiste = albumPropose.getArtiste(); 
		if(this.ID==null)this.ID = albumPropose.getID();  
		if(this.url==null)this.url = albumPropose.getUrl();
		if(this.date==null)this.date = albumPropose.getDate();
		if(this.imageSmall==null) this.imageSmall = albumPropose.getImageSmall();
		if(this.imageMedium==null) this.imageMedium=albumPropose.getImageMedium();
		if(this.imageLarge==null) this.imageLarge=albumPropose.getImageLarge();
		if(this.imageExtraLarge==null)this.imageExtraLarge=albumPropose.getImageExtraLarge();
		if(this.imageMega==null) this.imageMega=albumPropose.getImageMega();
		if(this.listeners==0.0)this.listeners = albumPropose.getListeners();
		if(this.playcount==0.0)this.playcount = albumPropose.getPlaycount();
		if(this.chansons==null)this.chansons = albumPropose.getChansons();
		if(this.toptags==null)this.toptags = albumPropose.getToptags();
		if(this.wiki==null)this.wiki = albumPropose.getWiki();
		
	}
	
	/**
	 * methode qui verifie la concordance entre 2 albums:
	 * si les url ne sont pas nulles, elles doivent correspondre,
	 * sinon ce sont les noms qui doivent correspondre
	 * @param a1
	 * @param a2
	 * @return
	 */
	private boolean verifierCoherence(Album a1, Album a2){
		boolean retour=false;
		if(a1.getUrl()!=null && a2.getUrl()!=null){
			if (a1.getUrl().equals(a2.getUrl())){
				retour=true;
			}else retour= false;
		}
		else if(a1.getName()!=null && a2.getName()!=null){
			if(a1.getName().equals(a2.getName())){
				retour=true;
			}else retour= false;
		}
		return retour;
	}
	
	public String toString() {
		String descrip;
		descrip = "\n " + "name " + name + "\n " 
		+ " date " + date + "\n "
		+ "url " + url + "\n "
		+ "listeners "+ listeners+ "\n "
		+ "playcount " + playcount + "\n "
		+ "id " + ID +"\n ";
		try{
				descrip+="nb chansons "+ chansons.size()+ "\n ";
				descrip+="nom artiste "+ artiste.getName()+ "\n ";
				descrip+="nb tags "+ toptags.size()+ "\n ";
				descrip+="resume wiki "+ wiki.getResume()+ "\n ";
		}
		catch (NullPointerException e){
		}
		return descrip;
	}

	/*"Album [name=" + name + ", artiste=" + artiste + ", url=" + url
	+ ", date=" + date + ", imageSmall=" + imageSmall
	+ ", imageMedium=" + imageMedium + ", imageLarge=" + imageLarge
	+ ", imageExtraLarge=" + imageExtraLarge + ", imageMega="
	+ imageMega + ", listeners=" + listeners + ", playcount="
	+ playcount + ", chansons=" + chansons 
	+ ", wiki=" + wiki + "]";*/

	
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



	 

	 



	public ArrayList<Chanson> getChansons() {
		return chansons;
	}



	public void setChansons(ArrayList<Chanson> chansons) {
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



	public String getID() {
		return ID;
	}



	public void setID(String iD) {
		ID = iD;
	}
	
	
	
	
	
	
	
}