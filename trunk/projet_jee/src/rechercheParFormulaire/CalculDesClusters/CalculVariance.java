package rechercheParFormulaire.CalculDesClusters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import metier.Cluster;
import metier.ComposantCluster;

public class CalculVariance {
	
	
	public ArrayList<Integer> tailleCluster(Cluster cluster){
		ArrayList<Integer> listeTaille = new ArrayList<Integer>();
		
		for(Entry<String, ComposantCluster> entry : cluster.getContenu().entrySet()) {
			HashMap<String, ComposantCluster> mapInter = entry.getValue().getContenu();
			for(Entry<String, ComposantCluster> entry2 : mapInter.entrySet()) {
			    listeTaille.add(entry2.getValue().getContenu().size());
			}
		}
		 
		return listeTaille;
	}
	
	public float varianceCluster(Cluster cluster){
		ArrayList<Integer> listeTaille = tailleCluster(cluster);
		int nbTotalOeuvre = 0;
		float moyenneTheorique = 0;
		float variance = 0;
		
		for (Integer i : listeTaille){
			nbTotalOeuvre+= listeTaille.get(i);
		}
		
		moyenneTheorique = nbTotalOeuvre / listeTaille.size();
		
		for (Integer i : listeTaille){
			variance+= ((listeTaille.get(i) - moyenneTheorique)*(listeTaille.get(i) - moyenneTheorique));
		}
		
		return variance;
		
	}

}
