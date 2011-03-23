 package metier.oeuvres;

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
 
public class Chanson extends ObjetAComparer implements Oeuvre{


	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private String ID;
	private String name;
	private int duree;
	private String url;
	private Artiste artiste;
	private String imageSmall;
	private String imageMedium;
	private String imageLarge;
	private String imageExtraLarge;
	private String imageMega;
	private int listeners;//nb de personnes ayant écouté la chanson
	private int playcount;//nb de fois où la chanson a été écoutée
	private ArrayList<Album> albums;
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
	}
	
	/**
	 * pour utiliser une autre fonction de rapprochement
	 */
	public Chanson(FonctionDeRapprochement fct) {
		super(fct);
	}
	
	
	public Chanson(String inu, String name, int duree, String url, String imageLarge, 
			Artiste artiste,int listeners,int playcount,Wiki wiki) {
		this.ID = inu;
		this.name = name;
		this.duree = duree;
		this.url=url;
		this.imageLarge = imageLarge;
		this.artiste = artiste;
		this.listeners=listeners; 
		this.playcount=playcount; 
		this.wiki=wiki; 
	}
	
	/**
	 * constructeur avec tous les parametres
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
	public Chanson(String name, int duree, String url,
			Artiste artiste,int listeners,int playcount,
			ArrayList<Album> albums,
			ArrayList<Tag> toptags,Wiki wiki) {
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
		if(this.duree==0)this.duree = chansonProposee.getDuree();  
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
		
		if(artiste!=null){
			descrip+="artiste "+ artiste.getName()+ "\n ";
		}
		if(albums!=null && albums.size()>0){
			descrip+="nb albums "+ albums.size()+" ";
			for(Album a : albums){
				descrip+=a.getName() + ", ";
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
		return descrip;
	}
	
	
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
		if(this.duree==0){
			int value = (int) (Math.random()*400000 + 1000);
			this.setDuree(value);
		}
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






	public int getDuree() {
		return duree;
	}






	public void setDuree(int duree) {
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





 




	public ArrayList<Album> getAlbums() {
		return albums;
	}






	public void setAlbums(ArrayList<Album> albums) {
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


	@Override
	public Collection<Chanson> getObjetsDeCeType() {
		return Controleur.getInstanceuniquecontroleur().getListeChansons().values();
	}
	

	@Override
	protected String getNomTableBDD(){return "CHANSON";}
}
