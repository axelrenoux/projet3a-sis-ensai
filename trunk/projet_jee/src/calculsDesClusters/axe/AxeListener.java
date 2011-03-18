package calculsDesClusters.axe;

import java.util.ArrayList;
import java.util.Collections;

import metier.Cluster;
import metier.oeuvres.Album;
import metier.oeuvres.Oeuvre;
import rechercheParFormulaire.gestionRecherche.RechercheAlbum;

public class AxeListener implements Axe {

	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Méthodes pour calculer les quartiles des oeuvres
	 */

		public double[] QuartClusterOeuvreLs (ArrayList<Oeuvre> ClOeuvre) throws Exception{
			double[] ListeQuart = null;
			ArrayList<Double> ListeLs = new ArrayList<Double>();
						for(Oeuvre loeuvre :ClOeuvre){
							ListeLs.add(loeuvre.getListeners());
						}
			Collections.sort(ListeLs);
			ListeQuart=Quartiles(ListeLs);
		
			return ListeQuart;
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
		public static void main(String[] args) throws Exception {
			
			Album a = new Album();
			a.setListeners(10);
			Album b = new Album();
			b.setListeners(10);
			Album c = new Album();
			c.setListeners(20);
			Album d = new Album();
			d.setListeners(15);
			Album e = new Album();
			e.setListeners(20);
		
			ArrayList<Oeuvre> monoeuvre = new ArrayList<Oeuvre>();
			monoeuvre.add(a);
			monoeuvre.add(b);
			monoeuvre.add(c);
			monoeuvre.add(d);
			monoeuvre.add(e);
			
			AxeListener axe = new AxeListener();
			double[] my= axe.QuartClusterOeuvreLs(monoeuvre);
			for (int i=0;i<3 ; i++){
			System.out.println(my[i]);
			}
		}

}
