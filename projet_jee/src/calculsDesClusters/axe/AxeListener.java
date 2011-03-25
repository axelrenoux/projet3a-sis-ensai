package calculsDesClusters.axe;

import java.util.ArrayList;
import java.util.Collections;

import metier.oeuvres.Album;
import metier.oeuvres.Oeuvre;

public class AxeListener implements Axe {

	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	
	private ArrayList<Integer> mesQuarts = new ArrayList<Integer>();
	private ArrayList<Oeuvre> oeuvres;

	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	/**
	 * Méthodes pour calculer les quartiles des oeuvres
	 */

	public ArrayList<Integer> QuartClusterOeuvreLs (ArrayList<Oeuvre> ClOeuvre) throws Exception{

		ArrayList<Integer> ListeQuart = null;
		ArrayList<Integer> ListeLs = new ArrayList<Integer>();
		for(Oeuvre loeuvre :ClOeuvre){
			ListeLs.add(loeuvre.getListeners());
		}
		Collections.sort(ListeLs);
		int i=0;
		for (int d : ListeLs){
			if (d==0){
				i++;
			}
		}
		ListeQuart=Quartiles(ListeLs);
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


	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		String classe =" ";

		int List=oeuvre.getListeners();


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



	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/



	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Nombre d'auditeurs";
	}

	@Override
	public ArrayList<Oeuvre> getOeuvres() {
		return oeuvres;
	}

	@Override
	public void setOeuvres(ArrayList<Oeuvre> oeuvres) {
		this.oeuvres = oeuvres;	
		try {
			this.mesQuarts = QuartClusterOeuvreLs(oeuvres);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Integer> getMesQuarts() {
		return mesQuarts;
	}

	public void setMesQuarts(ArrayList<Integer> mesQuarts) {
		this.mesQuarts = mesQuarts;
	}

}
