 package metier;

import java.util.ArrayList;
import java.util.Collection;

import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;

import comparaison.FonctionDeRapprochement;

import controleur.Controleur;

public class Tag extends ObjetAComparer{


	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private String name;
	private String url;
	private int reach;//nombre de recherche de ce tag effectuees
	private int tagging;//nombre d'objets ayant ce tag
	private Wiki wiki;
	private ArrayList<Artiste> listeArtistes;
	private ArrayList<Album> listeAlbums;
	private ArrayList<Chanson> listeChansons;
	
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	/**
	 * constructeur vide
	 */
	public Tag() {
		super();
		listeArtistes = new ArrayList<Artiste>();
		listeAlbums = new ArrayList<Album>();
		listeChansons = new ArrayList<Chanson>();
	}
	
	/**
	 * pour utiliser une autre fonction de rapprochement
	 */
	public Tag(FonctionDeRapprochement fct) {
		super(fct);
		listeArtistes = new ArrayList<Artiste>();
		listeAlbums = new ArrayList<Album>();
		listeChansons = new ArrayList<Chanson>();
	}
	
	
	 
	/**
	 * constructeur avec les paramètres
	 * @param name
	 * @param url
	 * @param reach
	 * @param tagging
	 * @param wiki
	 */
	public Tag(String name, String url,
			int reach,int tagging,Wiki wiki) {
		this();
		this.name = name;
		this.url=url;
		this.reach=reach; 
		this.tagging=tagging; 
		this.wiki=wiki; 
	}




	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
 
 

	public String toString() {
		String descrip;
		descrip = "\n " + "name " + name + "\n " 
		+ " url " +  url+ "\n "
		+ "reach "+ reach+ "\n "
		+ "tagging " + tagging + "\n ";
		try{
				descrip+="resume wiki "+ wiki.getResume()+ "\n ";
				descrip+="LISTE artistes taille "+ listeArtistes.size() + "\n ";
				descrip+="LISTE chansons taille "+ listeChansons.size() + "\n ";
				descrip+="LISTE albums taille "+ listeAlbums.size() + "\n ";
				
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




	public String getUrl() {
		return url;
	}




	public void setUrl(String url) {
		this.url = url;
	}




	public int getReach() {
		return reach;
	}




	public void setReach(int reach) {
		this.reach = reach;
	}




	public int getTagging() {
		return tagging;
	}




	public void setTagging(int tagging) {
		this.tagging = tagging;
	}




	public Wiki getWiki() {
		return wiki;
	}




	public void setWiki(Wiki wiki) {
		this.wiki = wiki;
	}




	public ArrayList<Artiste> getListeArtistes() {
		return listeArtistes;
	}




	public void setListeArtistes(ArrayList<Artiste> listeArtistes) {
		this.listeArtistes = listeArtistes;
	}




	public ArrayList<Album> getListeAlbums() {
		return listeAlbums;
	}




	public void setListeAlbums(ArrayList<Album> listeAlbums) {
		this.listeAlbums = listeAlbums;
	}




	public ArrayList<Chanson> getListeChansons() {
		return listeChansons;
	}




	public void setListeChansons(ArrayList<Chanson> listeChansons) {
		this.listeChansons = listeChansons;
	}




	@Override
	public Collection<Tag> getObjetsDeCeType() {
		return Controleur.getInstanceuniquecontroleur().getListeTags().values();
	}

	
	
	@Override
	protected String getNomTableBDD(){return "TAG";}
}
