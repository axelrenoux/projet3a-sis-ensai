 package metier.oeuvres;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import comparaison.FonctionDeRapprochement;

import metier.ComposantCluster;
import metier.ObjetAComparer;
import metier.Tag;
import metier.Wiki;

import controleur.Controleur;
import exceptions.ExceptionMiseAjour;

public class Album extends ObjetAComparer implements Oeuvre {


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
	private int listeners;//nb de personnes ayant écouté l'album
	private int playcount;//nb de fois où l'album a été écouté
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
	}
	
	/**
	 * pour utiliser une autre fonction de rapprochement
	 */
	public Album(FonctionDeRapprochement fct) {
		super(fct);
	}

//TODO ajouté
	/**
	 * @param name
	 * @param url
	 * @param date
	 * @param imageLarge
	 * @param listeners
	 * @param playcount
	 * @param wiki
	 * @param artiste
	 */
	public Album(String name, String url,Date date,
			String imageLarge,int listeners,
			int playcount,Wiki wiki, Artiste artiste) {
		this.name = name;
		this.url=url;
		this.date=date;
		this.artiste = artiste;
		this.imageLarge=imageLarge;
		this.listeners=listeners; 
		this.playcount=playcount; 
		this.wiki=wiki; 
	}
	
	/**
	 * constructeur avec tous les parametres
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
			String imageExtraLarge,String imageMega,int listeners,
			int playcount,ArrayList<Chanson> chansons,
			ArrayList<Tag> toptags,Wiki wiki) {
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
		if(this.listeners==0)this.listeners = albumPropose.getListeners();
		if(this.playcount==0)this.playcount = albumPropose.getPlaycount();
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
		
		if(artiste!=null){
			descrip+="nom artiste "+ artiste.getName()+ "\n ";
		}
		if(chansons!=null && chansons.size()>0){
			descrip+="nb chansons "+ chansons.size()+ " ";
			for(Chanson ch : chansons){
				descrip+=ch.getName() + ", ";
			}
			descrip+=" \n ";
		}
		if(toptags!=null && toptags.size()>0){
			descrip+="nb tags "+ toptags.size()+ " ";
			for(Tag t : toptags){
				descrip+=t.getName() + ", ";
			}
			descrip+=" \n ";
		}
		if(wiki!=null){
			descrip+="resume wiki "+ wiki.getResume()+ "\n ";
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

	
	
	@Override
	public HashMap<String, ComposantCluster> getContenu() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

	/**
	 * methode qui affecte une valeur aléatoire à playcount et listeners et image si vides
	 */
	public void gererVides(){
		if(this.getPlaycount() ==0
				&& this.getListeners()==0){
			int value1 = (int)(Math.random()*10000);
			int value2 = (int)(value1*0.8);
			this.setPlaycount(value1);
			this.setListeners(value2);
		}
		if(this.getImageLarge()==null 
				||this.getImageLarge().equals("") 
				|| this.getImageLarge().equals("%27%27" )){
			this.setImageLarge("http://mylene.net/mfpics/itunes_20010_002.png");
		}
	}
	
	
	
	
	@Override
	public int getEffectif() {
		return 1;
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




	public int getListeners() {
		return listeners;
	}

	public void setListeners(int listeners) {
		this.listeners = listeners;
	}

	public int getPlaycount() {
		return playcount;
	}

	public void setPlaycount(int playcount) {
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
	

	@Override
	public Collection<Album> getObjetsDeCeType() {
		return Controleur.getInstanceuniquecontroleur().getListeAlbums().values();
	}



	@Override
	protected String getNomTableBDD(){return "ALBUM";}
}