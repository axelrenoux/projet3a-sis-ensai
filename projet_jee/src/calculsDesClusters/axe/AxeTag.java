package calculsDesClusters.axe;

import java.util.ArrayList;

import metier.oeuvres.Oeuvre;

public class AxeTag implements Axe {
	
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	
	private ArrayList<Oeuvre> oeuvres;
	
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/


	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		String res="";
		if (oeuvre.getToptags() != null && oeuvre.getToptags().size() != 0){
			res = oeuvre.getToptags().get(0).getName();
		}else res = "Pas de tag";
		return res;
	}

	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Tag";
	}
	
	@Override
	public ArrayList<Oeuvre> getOeuvres() {
		return oeuvres;
	}
	
	@Override
	public void setOeuvres(ArrayList<Oeuvre> oeuvres) {
		this.oeuvres = oeuvres;	
	}
}
