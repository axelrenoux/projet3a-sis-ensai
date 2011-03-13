 package metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Tag {


	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private String name;
	private String url;
	private Double reach;//nombre de recherche de ce tag effectuees
	private Double tagging;//nombre d'objets ayant ce tag
	private Wiki wiki;
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	/**
	 * constructeur vide
	 */
	public Tag() {
		super();
		// TODO Auto-generated constructor stub
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
			double reach,double tagging,Wiki wiki) {
		super();
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




	public Double getReach() {
		return reach;
	}




	public void setReach(Double reach) {
		this.reach = reach;
	}




	public Double getTagging() {
		return tagging;
	}




	public void setTagging(Double tagging) {
		this.tagging = tagging;
	}




	public Wiki getWiki() {
		return wiki;
	}




	public void setWiki(Wiki wiki) {
		this.wiki = wiki;
	}
	
	
	
	
	
}
