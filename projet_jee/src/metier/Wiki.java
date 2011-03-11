 package metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Wiki {


	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private Date datePublication;
	private String resume;
	private String contenu;
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	/**
	 * constructeur vide
	 */
	public Wiki() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * constructeur avec parametres
	 * @param datePublication
	 * @param resume
	 * @param contenu
	 */
	public Wiki(Date datePublication, String resume, String contenu) {
		super();
		this.datePublication = datePublication;
		this.resume = resume;
		this.contenu = contenu;
	}






	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	@Override
	public String toString() {
		return "Wiki [datePublication=" + datePublication + ", resume="
				+ resume + ", contenu=" + contenu + "]";
	}


	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/
	
 
	
	
	public Date getDatePublication() {
		return datePublication;
	}

	/**
	 * TODO à coder
	 * @param datePublication
	 */
	public void setDatePublication(String datePublication) {
		//transformer en date
		//Date d = new Date(datePublication);
		//this.datePublication = d;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	
	
	 

	
	
}
