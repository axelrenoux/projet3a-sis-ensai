package exceptions;

import controleur.Controleur;

public class ExceptionDate extends Exception{
	
	
	/********************************************************************/
	/************************      attributs      ***********************/
	/********************************************************************/
	private String titre;
	private String message;
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	
	public ExceptionDate(String dateString){
		this.titre="Probleme de format de date";
		this.message="Probleme de format de date : " + dateString;
	}

	
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/
	

	public String getTitre() {
		return titre;
	}

	public String getMessage() {
		return message;
	}


	
}
