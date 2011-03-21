package calculsDesClusters.axe;

import java.util.ArrayList;
import java.util.Collections;

import metier.oeuvres.Album;
import metier.oeuvres.Oeuvre;

public class AxeListener implements Axe {

	private ArrayList<Double> mesQuarts;

	public ArrayList<Double> getMesQuarts() {
		return mesQuarts;
	}

	public void setMesQuarts(ArrayList<Double> mesQuarts) {
		this.mesQuarts = mesQuarts;
	}

	/**
	 * M�thodes pour calculer les quartiles des oeuvres
	 */

	public ArrayList<Double> QuartClusterOeuvreLs (ArrayList<Oeuvre> ClOeuvre) throws Exception{

		ArrayList<Double> ListeQuart = null;
		ArrayList<Double> ListeLs = new ArrayList<Double>();
		for(Oeuvre loeuvre :ClOeuvre){
			ListeLs.add(loeuvre.getListeners());
		}
		Collections.sort(ListeLs);
		ListeQuart=Quartiles(ListeLs);
		setMesQuarts(ListeQuart);
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
	public static ArrayList<Double> Quartiles(ArrayList<Double> values) throws Exception
	{
		if (values.size() < 3)
			throw new Exception("This method is not designed to handle lists with fewer than 3 elements.");

		double median = Median(values);

		ArrayList<Double> lowerHalf = GetValuesLessThan(values, median, true);
		ArrayList<Double> upperHalf = GetValuesGreaterThan(values, median, true);

		ArrayList<Double> Sol = new ArrayList<Double>(); 
		Sol.add(Median(lowerHalf));
		Sol.add(median);
		Sol.add(Median(upperHalf));

		return Sol;
	}

	//fonction r�cup�rant les valeurs sup�rieures � la m�diane
	public static ArrayList<Double> GetValuesGreaterThan(ArrayList<Double> values, double limit, boolean orEqualTo)
	{
		ArrayList<Double> modValues = new ArrayList<Double>();

		for (double value : values)
			if (value > limit || (value == limit && orEqualTo))
				modValues.add(value);

		return modValues;
	}

	//fonction r�cup�rant les valeurs inf�rieures � la m�diane
	public static ArrayList<Double> GetValuesLessThan(ArrayList<Double> values, double limit, boolean orEqualTo)
	{
		ArrayList<Double> modValues = new ArrayList<Double>();

		for (double value : values)
			if (value < limit || (value == limit && orEqualTo))
				modValues.add(value);

		return modValues;
	}
	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		String classe =" ";
		double List=oeuvre.getListeners();
		if (List<=this.mesQuarts.get(0)){
			classe="entre 0 et " +this.mesQuarts.get(0) ;
		}else if(this.mesQuarts.get(0)<List && List<=this.mesQuarts.get(1)){
			classe = "entre "+this.mesQuarts.get(0) + " et " +this.mesQuarts.get(1) ;
		}else if(this.mesQuarts.get(1)<List && List<=this.mesQuarts.get(2)){
			classe = "entre "+this.mesQuarts.get(1) + " et " +this.mesQuarts.get(2) ;
		}else if (List>this.mesQuarts.get(2)){
			classe = "Sup�rieur � " +this.mesQuarts.get(2);
		}	
		return classe;
	}

	/*public static void main(String[] args) throws Exception {

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
			Album f = new Album();
			f.setListeners(7);

			ArrayList<Oeuvre> monoeuvre = new ArrayList<Oeuvre>();
			monoeuvre.add(a);
			monoeuvre.add(b);
			monoeuvre.add(c);
			monoeuvre.add(d);
			monoeuvre.add(e);
			monoeuvre.add(f);

			AxeListener axe = new AxeListener();
			ArrayList<Double>  my= axe.QuartClusterOeuvreLs(monoeuvre);

			System.out.println(my);
			System.out.println(axe.CalculAxe(f));

		}*/

}
