package calculsDesClusters.axe;

import java.util.ArrayList;

import metier.oeuvres.Chanson;
import metier.oeuvres.Oeuvre;

public class AxeArtistePourChanson implements Axe {

	private ArrayList<Oeuvre> oeuvres;
	
	@Override
	public ArrayList<Oeuvre> getOeuvres() {
		return oeuvres;
	}
	
	@Override
	public void setOeuvres(ArrayList<Oeuvre> oeuvres) {
		this.oeuvres = oeuvres;	
	}

	@Override
	public String getType() {
		return "Artiste";
	}

	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		Chanson c = (Chanson) oeuvre;
		return c.getArtiste().getName();
	}

}
