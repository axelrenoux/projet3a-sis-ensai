package calculsDesClusters.axe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;

import metier.ComposantCluster;
import metier.oeuvres.Oeuvre;

public class AxePlaycount implements Axe{

	//La méthode retourne les quartiles à 0,25 ; 0,5 ; 0,75 pour les Playcount de chansons
	public double[] QuartClusterOeuvrePl (ArrayList<Oeuvre> ClOeuvre) throws Exception{
		double[] ListeQuart = null;
		ArrayList<Double> ListePl = new ArrayList<Double>();
					for(Oeuvre loeuvre :ClOeuvre){
						ListePl.add(loeuvre.getPlaycount());
					}
				Collections.sort(ListePl);
				ListeQuart=Quartiles(ListePl);
				return ListeQuart;
			}
	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		// TODO Auto-generated method stub
		return null;
	}
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
