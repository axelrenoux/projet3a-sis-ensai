package rechercheParFormulaire.CalculDesClusters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import metier.Cluster;
import metier.ComposantCluster;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;


public class CalculQuartiles {

	//Pour les listeners
	//La méthode retourne les quartiles à 0,25 ; 0,5 ; 0,75 pour les Listeners d'albums
	public double[] QuartClusterAlbumLs (Cluster cluster) throws Exception{
		double[] ListeQuart = null;
		ArrayList<Double> ListeLs = new ArrayList<Double>();
		for(Entry<String, ComposantCluster> entry : cluster.getContenu().entrySet()) {
			HashMap<String, ComposantCluster> mapInter = entry.getValue().getContenu();
			for(Entry<String, ComposantCluster> entry2 : mapInter.entrySet()) {
				HashMap<String, ComposantCluster> mapInter2 = entry2.getValue().getContenu();
				for(Entry<String, ComposantCluster> entry3 : mapInter.entrySet()) {			
					ArrayList<Album> ClAlbum = (ArrayList<Album>) entry3.getValue();
					for(Album lalbum :ClAlbum){
						//ListePl.add(lalbum.getPlaycount());
						ListeLs.add(lalbum.getListeners());
					}
				}
			}
		}
		//TriListe(ListePl);
		Collections.sort(ListeLs);
		ListeQuart=Quartiles(ListeLs);
		return ListeQuart;
	}

	//La méthode retourne les quartiles à 0,25 ; 0,5 ; 0,75 pour les Listeners d'artistes
	public double[] QuartClusterArtisteLs (Cluster cluster) throws Exception{
		double[] ListeQuart = null;
		ArrayList<Double> ListeLs = new ArrayList<Double>();
		for(Entry<String, ComposantCluster> entry : cluster.getContenu().entrySet()) {
			HashMap<String, ComposantCluster> mapInter = entry.getValue().getContenu();
			for(Entry<String, ComposantCluster> entry2 : mapInter.entrySet()) {
				HashMap<String, ComposantCluster> mapInter2 = entry2.getValue().getContenu();
				for(Entry<String, ComposantCluster> entry3 : mapInter.entrySet()) {			
					ArrayList<Artiste> ClArtist = (ArrayList<Artiste>) entry3.getValue();
					for(Artiste lartist :ClArtist){
						//ListePl.add(lalbum.getPlaycount());
						ListeLs.add(lartist.getListeners());
					}
				}
			}
		}
		//TriListe(ListePl);
		Collections.sort(ListeLs);
		ListeQuart=Quartiles(ListeLs);
		return ListeQuart;
	}

	//La méthode retourne les quartiles à 0,25 ; 0,5 ; 0,75 pour les Listeners de chansons
	public double[] QuartClusterChansonLs (Cluster cluster) throws Exception{
		double[] ListeQuart = null;
		ArrayList<Double> ListeLs = new ArrayList<Double>();
		for(Entry<String, ComposantCluster> entry : cluster.getContenu().entrySet()) {
			HashMap<String, ComposantCluster> mapInter = entry.getValue().getContenu();
			for(Entry<String, ComposantCluster> entry2 : mapInter.entrySet()) {
				HashMap<String, ComposantCluster> mapInter2 = entry2.getValue().getContenu();
				for(Entry<String, ComposantCluster> entry3 : mapInter.entrySet()) {			
					ArrayList<Chanson> ClChanson = (ArrayList<Chanson>) entry3.getValue();
					for(Chanson lchanson :ClChanson){
						//ListePl.add(lalbum.getPlaycount());
						ListeLs.add(lchanson.getListeners());
					}
				}
			}
		}
		//TriListe(ListePl);
		Collections.sort(ListeLs);
		ListeQuart=Quartiles(ListeLs);
		return ListeQuart;
	}


	//Pour les Playcounts
	//La méthode retourne les quartiles à 0,25 ; 0,5 ; 0,75 pour les Playcounts d'albums
	public double[] QuartClusterAlbumPs (Cluster cluster) throws Exception{
		double[] ListeQuart = null;
		ArrayList<Double> ListePl = new ArrayList<Double>();
		for(Entry<String, ComposantCluster> entry : cluster.getContenu().entrySet()) {
			HashMap<String, ComposantCluster> mapInter = entry.getValue().getContenu();
			for(Entry<String, ComposantCluster> entry2 : mapInter.entrySet()) {
				HashMap<String, ComposantCluster> mapInter2 = entry2.getValue().getContenu();
				for(Entry<String, ComposantCluster> entry3 : mapInter.entrySet()) {			
					ArrayList<Album> ClAlbum = (ArrayList<Album>) entry3.getValue();
					for(Album lalbum :ClAlbum){
						ListePl.add(lalbum.getPlaycount());

					}
				}
			}
		}
		//TriListe(ListePl);
		Collections.sort(ListePl);
		Quartiles(ListePl);
		return ListeQuart;
	}

	//La méthode retourne les quartiles à 0,25 ; 0,5 ; 0,75 pour les Playcounts d'artistes
	public double[] QuartClusterArtistePl (Cluster cluster) throws Exception{
		double[] ListeQuart = null;
		ArrayList<Double> ListePl = new ArrayList<Double>();
		for(Entry<String, ComposantCluster> entry : cluster.getContenu().entrySet()) {
			HashMap<String, ComposantCluster> mapInter = entry.getValue().getContenu();
			for(Entry<String, ComposantCluster> entry2 : mapInter.entrySet()) {
				HashMap<String, ComposantCluster> mapInter2 = entry2.getValue().getContenu();
				for(Entry<String, ComposantCluster> entry3 : mapInter.entrySet()) {			
					ArrayList<Artiste> ClArtist = (ArrayList<Artiste>) entry3.getValue();
					for(Artiste lartist :ClArtist){
						ListePl.add(lartist.getPlaycount());

					}
				}
			}
		}
		//TriListe(ListePl);
		Collections.sort(ListePl);
		ListeQuart=Quartiles(ListePl);
		return ListeQuart;
	}


	//La méthode retourne les quartiles à 0,25 ; 0,5 ; 0,75 pour les Playcount de chansons
	public double[] QuartClusterChansonPl (Cluster cluster) throws Exception{
		double[] ListeQuart = null;
		ArrayList<Double> ListePl = new ArrayList<Double>();
		for(Entry<String, ComposantCluster> entry : cluster.getContenu().entrySet()) {
			HashMap<String, ComposantCluster> mapInter = entry.getValue().getContenu();
			for(Entry<String, ComposantCluster> entry2 : mapInter.entrySet()) {
				HashMap<String, ComposantCluster> mapInter2 = entry2.getValue().getContenu();
				for(Entry<String, ComposantCluster> entry3 : mapInter.entrySet()) {			
					ArrayList<Chanson> ClChanson = (ArrayList<Chanson>) entry3.getValue();
					for(Chanson lchanson :ClChanson){
						ListePl.add(lchanson.getPlaycount());

					}
				}
			}
		}
		//TriListe(ListePl);
		Collections.sort(ListePl);
		ListeQuart=Quartiles(ListePl);
		return ListeQuart;
	}



	//Méthode de Calcul	
	//Méthode pour récupérer la médiane
	public static double Median(ArrayList<Double> values)
	{
		Collections.sort(values);

		if (values.size() % 2 == 1)
			return values.get((values.size()+1)/2-1);
		else
		{
			double lower = values.get(values.size()/2-1);
			double upper = values.get(values.size()/2);

			return (lower + upper) / 2.0;
		}	
	} 

	//fonction pour le calcul des quartiles
	public static double[] Quartiles(ArrayList<Double> values) throws Exception
	{
		if (values.size() < 3)
			throw new Exception("This method is not designed to handle lists with fewer than 3 elements.");

		double median = Median(values);

		ArrayList<Double> lowerHalf = GetValuesLessThan(values, median, true);
		ArrayList<Double> upperHalf = GetValuesGreaterThan(values, median, true);

		return new double[] {Median(lowerHalf), median, Median(upperHalf)};
	}

	//fonction récupérant les valeurs supérieures à la médiane
	public static ArrayList<Double> GetValuesGreaterThan(ArrayList<Double> values, double limit, boolean orEqualTo)
	{
		ArrayList<Double> modValues = new ArrayList<Double>();

		for (double value : values)
			if (value > limit || (value == limit && orEqualTo))
				modValues.add(value);

		return modValues;
	}

	//fonction récupérant les valeurs inférieures à la médiane
	public static ArrayList<Double> GetValuesLessThan(ArrayList<Double> values, double limit, boolean orEqualTo)
	{
		ArrayList<Double> modValues = new ArrayList<Double>();

		for (double value : values)
			if (value < limit || (value == limit && orEqualTo))
				modValues.add(value);

		return modValues;
	}
}