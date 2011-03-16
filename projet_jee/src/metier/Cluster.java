package metier;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Administrateur
 *
 */
public class Cluster implements ComposantCluster {
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	//un cluster est composé de clusters ou d'oeuvres (chanson, album ou artiste)
	private HashMap<String,ComposantCluster> contenu = new HashMap<String, ComposantCluster>();
	private String nomCluster;
	
	
	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	
	public Cluster(){
		super();
		this.nomCluster = "";
	}

	public Cluster(String nomCluster){
		super();
		this.nomCluster = nomCluster;
	}

	
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/

 
	public String getNom(){
		return nomCluster;
	}
	
	public String toString(){
		String str = "cluster " + nomCluster + " \n "+
		"sous-clusters: " + " \n "
		+ this.getContenu();
		return str;
	}
	
	
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/
	public HashMap<String,ComposantCluster> getContenu() {
		return contenu;
	}


	public void setContenu(HashMap<String,ComposantCluster>  contenu) {
		this.contenu = contenu;
	}

	
	public String getNomCluster() {
		return nomCluster;
	}


	public void setNomCluster(String nomCluster) {
		this.nomCluster = nomCluster;
	}



	

	
	

}
