package calculsDesClusters.axe;

import java.util.ArrayList;

import metier.oeuvres.Oeuvre;

public interface Axe {
	
	
	public ArrayList<Oeuvre> getOeuvres();
	public void setOeuvres(ArrayList<Oeuvre> oeuvres);
	
	public String getType();
	
	public String CalculAxe(Oeuvre oeuvre);
	

}
