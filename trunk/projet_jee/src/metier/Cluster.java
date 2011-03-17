package metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
	
	public ArrayList<Integer> tailleCluster(Cluster cluster){
		ArrayList<Integer> listeTaille = new ArrayList<Integer>();
		
		for(Entry<String, ComposantCluster> entry : cluster.getContenu().entrySet()) {
			HashMap<String, ComposantCluster> mapInter = entry.getValue().getContenu();
			for(Entry<String, ComposantCluster> entry2 : mapInter.entrySet()) {
			    listeTaille.add(entry2.getValue().getContenu().size());
			    System.out.println("####################################");
			    System.out.println(entry2.getValue().getContenu());
			}
		}
		 
		return listeTaille;
	}
	
	public float varianceCluster(Cluster cluster){
		ArrayList<Integer> listeTaille = tailleCluster(cluster);
		float nbTotalOeuvre = 0;
		float moyenneTheorique = 0;
		float variance = 0;
		
		for (Integer i : listeTaille){
			nbTotalOeuvre+= listeTaille.get(i);
			System.out.println(nbTotalOeuvre);
			System.out.println(listeTaille.get(i));
		}
		
		moyenneTheorique = nbTotalOeuvre / listeTaille.size();
		System.out.println(moyenneTheorique);
		
		for (Integer j : listeTaille){
			variance+= ((listeTaille.get(j) - moyenneTheorique)*(listeTaille.get(j) - moyenneTheorique));
		}
		
		return variance;
		
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
