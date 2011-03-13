package exceptions;

import controleur.Controleur;

public class ExceptionMiseAjour extends Exception{
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	
	private Object obj1;
	private Object obj2;
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	
	public ExceptionMiseAjour(Object obj1, Object obj2){
		this.obj1 = obj1;
		this.obj2 = obj2;
		Controleur.getInstanceuniquecontroleur().
			ajouterProbleme("Probleme de coherence" + "\n ", "Probleme de coherence pour la mise à jour de l'objet " 
					+ obj1.toString() + " à partir de l'objet " + obj2.toString()
					+ "objet de type " + obj1.getClass() + " " + obj2.getClass());
	}
	
	

}
