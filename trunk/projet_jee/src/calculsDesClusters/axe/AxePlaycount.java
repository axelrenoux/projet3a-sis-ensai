package calculsDesClusters.axe;

import java.util.ArrayList;
import java.util.Collections;

import metier.oeuvres.Album;
import metier.oeuvres.Oeuvre;

public class AxePlaycount implements Axe{

	private ArrayList<Integer> mesQuarts = new ArrayList<Integer>();
	private ArrayList<Oeuvre> oeuvres;

	public ArrayList<Integer> getMesQuarts() {
		return mesQuarts;
	}
	public void setMesQuarts(ArrayList<Integer> mesQuarts) {
		this.mesQuarts = mesQuarts;
	}
	//La méthode retourne les quartiles à 0,25 ; 0,5 ; 0,75 pour les Playcount de chansons
	public ArrayList<Integer> QuartClusterOeuvrePl (ArrayList<Oeuvre> ClOeuvre){
		ArrayList<Integer> ListeQuart = null;
		ArrayList<Integer> ListePl = new ArrayList<Integer>();
		for(Oeuvre loeuvre :ClOeuvre){
			ListePl.add(loeuvre.getPlaycount());
		}
		Collections.sort(ListePl);
		ListeQuart=Quartiles(ListePl);
		setMesQuarts(ListeQuart);
		return ListeQuart;
	}

	public static int Median(ArrayList<Integer> values)
	{
		Collections.sort(values);

		if (values.size() % 2 == 1)
			return values.get((values.size()+1)/2-1);
		else
		{
			int lower = values.get(values.size()/2-1);
			int upper = values.get(values.size()/2);

			return (lower + upper) / 2;
		}	
	} 

	//fonction pour le calcul des quartiles
	public static ArrayList<Integer> Quartiles(ArrayList<Integer> values){
		ArrayList<Integer> Sol = new ArrayList<Integer>(); 

		if (values.size() == 2){
			Sol.add(values.get(0));
			Sol.add((values.get(0) + values.get(1))/2);
			Sol.add(values.get(1));
		}else if (values.size() == 1){
			Sol.add(values.get(0));
			int a = 0;
			Sol.add(a);
			Sol.add(a);
		}else{
			int median = Median(values);

			ArrayList<Integer> lowerHalf = GetValuesLessThan(values, median, true);
			ArrayList<Integer> upperHalf = GetValuesGreaterThan(values, median, true);

			Sol.add(Median(lowerHalf));
			Sol.add(median);
			Sol.add(Median(upperHalf));
		}
		return Sol;
	}

	//fonction récupérant les valeurs supérieures à la médiane
	public static ArrayList<Integer> GetValuesGreaterThan(ArrayList<Integer> values, int limit, boolean orEqualTo)
	{
		ArrayList<Integer> modValues = new ArrayList<Integer>();

		for (int value : values)
			if (value > limit || (value == limit && orEqualTo))
				modValues.add(value);

		return modValues;
	}

	//fonction récupérant les valeurs inférieures à la médiane
	public static ArrayList<Integer> GetValuesLessThan(ArrayList<Integer> values, int limit, boolean orEqualTo)
	{
		ArrayList<Integer> modValues = new ArrayList<Integer>();

		for (int value : values)
			if (value < limit || (value == limit && orEqualTo))
				modValues.add(value);

		return modValues;
	}

	public String CalculAxe(Oeuvre oeuvre) {
		String classe =" ";
		int List=oeuvre.getPlaycount();
		if (List<=this.mesQuarts.get(0)){
			classe="entre 0 et " +this.mesQuarts.get(0) ;
		}else if(this.mesQuarts.get(0)<List && List<=this.mesQuarts.get(1)){
			classe = "entre "+this.mesQuarts.get(0) + " et " +this.mesQuarts.get(1) ;
		}else if(this.mesQuarts.get(1)<List && List<=this.mesQuarts.get(2)){
			classe = "entre "+this.mesQuarts.get(1) + " et " +this.mesQuarts.get(2) ;
		}else if (List>this.mesQuarts.get(2)){
			classe = "Supérieur à " +this.mesQuarts.get(2);
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
		ArrayList<Integer>  my= axe.QuartClusterOeuvrePl(monoeuvre);

		System.out.println(my);
		System.out.println(axe.CalculAxe(f));

	}*/
	
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Nombre d'écoutes";
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
