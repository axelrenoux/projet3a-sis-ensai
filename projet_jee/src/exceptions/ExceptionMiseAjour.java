package exceptions;

import controleur.Controleur;

public class ExceptionMiseAjour extends Exception{
	
	/********************************************************************/
	/************************      attributs      ***********************/
	/********************************************************************/
	private Object obj1;
	private Object obj2;
	private String titre;
	private String message;
	
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	
	public ExceptionMiseAjour(Object obj1, Object obj2){
		this.obj1 = obj1;
		this.obj2 = obj2;
		this.titre = "Probleme de coherence" + "\n ";
		this.message = "Probleme de coherence pour la mise � jour de l'objet " 
			+ obj1.toString() + " � partir de l'objet " + obj2.toString()
			+ "objet de type " + obj1.getClass() + " " + obj2.getClass();
			
	}


	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}



	
}
