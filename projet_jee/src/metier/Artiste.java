 package metier;

import java.util.ArrayList;

import exceptions.ExceptionMiseAjour;



public class Artiste {


	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private String ID;
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
	
	
	
	/**
	 * methode qui met a jour un artiste à partir d'un autre pour completer
	 * @param albumPropose
	 * @throws ExceptionMiseAjour
	 */
	public void mettreAjour(Artiste artistePropose) throws ExceptionMiseAjour{
		//on commence par verifier la coherence de la mise a jour: 
		if(!verifierCoherence(this, artistePropose))throw new ExceptionMiseAjour(this,artistePropose);
				
		if(this.name==null) this.name = artistePropose.getName();
		if(this.ID==null)this.ID = artistePropose.getID();  
		if(this.url==null)this.url = artistePropose.getUrl();
		if(this.imageSmall==null) this.imageSmall = artistePropose.getImageSmall();
		if(this.imageMedium==null) this.imageMedium=artistePropose.getImageMedium();
		if(this.imageLarge==null) this.imageLarge=artistePropose.getImageLarge();
		if(this.imageExtraLarge==null)this.imageExtraLarge=artistePropose.getImageExtraLarge();
		if(this.imageMega==null) this.imageMega=artistePropose.getImageMega();
		if(this.listeners==0.0)this.listeners = artistePropose.getListeners();
		if(this.playcount==0.0)this.playcount = artistePropose.getPlaycount();
		if(this.artistesSimilaires ==null) this.artistesSimilaires = artistePropose.getArtistesSimilaires();
		if(this.toptags==null)this.toptags = artistePropose.getToptags();
		if(this.wiki==null)this.wiki = artistePropose.getWiki();
		
	}
	
	/**
	 * methode qui verifie la concordance entre 2 artistes:
	 * si les url ne sont pas nulles, elles doivent correspondre,
	 * sinon ce sont les noms qui doivent correspondre
	 * @param a1
	 * @param a2
	 * @return
	 */
	private boolean verifierCoherence(Artiste a1, Artiste a2){
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
		+ " url " +  url+ "\n "
		+ "listeners "+ listeners+ "\n "
		+ "playcount " + playcount + "\n "
		+ "image mega " + imageMega + "\n ";
		
		try{
				descrip+="nb artistes sim "+ artistesSimilaires.size()+ "\n ";
				descrip+="nb tags "+ toptags.size()+ "\n ";
				descrip+="resume wiki "+ wiki.getResume()+ "\n ";
		}
		catch (NullPointerException e){
		}
		return descrip;	
	}
/*"Artiste [name=" + name + ", url=" + url + ", imageSmall="
				+ imageSmall + ", imageMedium=" + imageMedium + ", imageLarge="
				+ imageLarge + ", imageExtraLarge=" + imageExtraLarge
				+ ", imageMega=" + imageMega + ", listeners=" + listeners
				+ ", playcount=" + playcount + ", artistesSimilaires="
				+ artistesSimilaires + ", toptags=" + toptags + ", wiki=" + wiki
				+ "]";
	}*/


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



	public String getID() {
		return ID;
	}






	public void setID(String iD) {
		ID = iD;
	}

}
