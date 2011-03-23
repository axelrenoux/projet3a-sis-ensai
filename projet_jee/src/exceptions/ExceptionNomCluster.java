package exceptions;

public class ExceptionNomCluster extends Exception{
	
	
	/********************************************************************/
	/************************      attributs      ***********************/
	/********************************************************************/
	private String titre;
	private String message;
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	
	public ExceptionNomCluster(String nomCluster){
		this.titre="Probleme de nom de cluster";
		this.message="Probleme de nom de cluster : " + nomCluster;
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
