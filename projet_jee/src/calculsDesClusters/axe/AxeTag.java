package calculsDesClusters.axe;

import java.util.ArrayList;

import metier.oeuvres.Oeuvre;

public class AxeTag implements Axe {
	
	private ArrayList<Oeuvre> oeuvres;

	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		if (oeuvre.getToptags() != null && oeuvre.getToptags().size() != 0){
			return oeuvre.getToptags().get(0).getName();
		}else return "Pas de tag";
	}


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
