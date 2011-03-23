package calculsDesClusters.axe;

import java.util.ArrayList;
import java.util.Collections;

import metier.oeuvres.Album;
import metier.oeuvres.Oeuvre;

public class AxePlaycount implements Axe{

	private ArrayList<Double> mesQuarts = new ArrayList<Double>();
	private ArrayList<Oeuvre> oeuvres;

	public ArrayList<Double> getMesQuarts() {
		return mesQuarts;
	}
	public void setMesQuarts(ArrayList<Double> mesQuarts) {
		this.mesQuarts = mesQuarts;
	}
	//La m�thode retourne les quartiles � 0,25 ; 0,5 ; 0,75 pour les Playcount de chansons
	public ArrayList<Double> QuartClusterOeuvrePl (ArrayList<Oeuvre> ClOeuvre) throws Exception{
		ArrayList<Double> ListeQuart = null;
		ArrayList<Double> ListePl = new ArrayList<Double>();
		for(Oeuvre loeuvre :ClOeuvre){
			ListePl.add(loeuvre.getPlaycount());
		}
		Collections.sort(ListePl);
		ListeQuart=Quartiles(ListePl);
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
	public static ArrayList<Double> Quartiles(ArrayList<Double> values){
		ArrayList<Double> Sol = new ArrayList<Double>(); 

		if (values.size() == 2){
			Sol.add(values.get(0));
			Sol.add((values.get(0) + values.get(1))/2);
			Sol.add(values.get(1));
		}else if (values.size() == 1){
			Sol.add(values.get(0));
			double a = 0;
			Sol.add(a);
			Sol.add(a);
		}else{
			double median = Median(values);

			ArrayList<Double> lowerHalf = GetValuesLessThan(values, median, true);
			ArrayList<Double> upperHalf = GetValuesGreaterThan(values, median, true);

			Sol.add(Median(lowerHalf));
			Sol.add(median);
			Sol.add(Median(upperHalf));
		}
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

	public String CalculAxe(Oeuvre oeuvre) {
		String classe =" ";
		double List=oeuvre.getPlaycount();
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
		a.setPlaycount(10);
		Album b = new Album();
		b.setPlaycount(10);
		Album c = new Album();
		c.setPlaycount(20);
		Album d = new Album();
		d.setPlaycount(15);
		Album e = new Album();
		e.setPlaycount(20);
		Album f = new Album();
		f.setPlaycount(17);

		ArrayList<Oeuvre> monoeuvre = new ArrayList<Oeuvre>();
		monoeuvre.add(a);
		monoeuvre.add(b);
		monoeuvre.add(c);
		monoeuvre.add(d);
		monoeuvre.add(e);
		monoeuvre.add(f);

		AxePlaycount axe = new AxePlaycount();
		ArrayList<Double>  my= axe.QuartClusterOeuvrePl(monoeuvre);

		System.out.println(my);
		System.out.println(axe.CalculAxe(f));

	}*/
	
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Nombre d'�coutes";
	}
	
	@Override
	public ArrayList<Oeuvre> getOeuvres() {
		return oeuvres;
	}
	
	@Override
	public void setOeuvres(ArrayList<Oeuvre> oeuvres) {
		this.oeuvres = oeuvres;	
		try {
			this.mesQuarts = QuartClusterOeuvrePl(oeuvres);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
