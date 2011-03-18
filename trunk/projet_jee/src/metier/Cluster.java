package metier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import metier.oeuvres.Album;
import metier.oeuvres.Chanson;
import metier.oeuvres.Oeuvre;

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

	/**
	 * methode qui renvoie un tableau contenant les effectifs des clusters finaux
	 * 
	 */
	public ArrayList<Integer> tailleCluster(){
		ArrayList<Integer> listeTaille = new ArrayList<Integer>();

		for(Entry<String, ComposantCluster> entry : this.getContenu().entrySet()) {
			HashMap<String, ComposantCluster> mapInter = entry.getValue().getContenu();
			for(Entry<String, ComposantCluster> entry2 : mapInter.entrySet()) {
				listeTaille.add(entry2.getValue().getContenu().size());
			}
		}
		System.out.println(listeTaille);
		return listeTaille;

	}

	/**
	 * methode qui renvoie la variance d'un tableau de données
	 * 
	 */
	public double varianceCluster(){
		//on recupere les tailles des clusters sur lesquelles on veut calculer une variance
		ArrayList<Integer> listeTaille = this.tailleCluster();
		
		long n = 0;
		double mean = 0;
		double s = 0.0;

		//permet de calculer la variance de taille entre les clusters
		for (double x : listeTaille) {
			n++;
			double delta = x - mean;
			mean += delta / n;
			s += delta * (x - mean);
		}
		
		return (s / n);


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
