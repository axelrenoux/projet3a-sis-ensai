package calculsDesClusters.axe;

import java.util.ArrayList;

import metier.oeuvres.Oeuvre;

public class AxeOeuvre implements Axe{

	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	
	private ArrayList<Oeuvre> oeuvres;
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		return oeuvre.getNom();
	}
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return " ";
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
