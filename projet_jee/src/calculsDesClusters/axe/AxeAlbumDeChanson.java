package calculsDesClusters.axe;

import java.util.ArrayList;

import metier.oeuvres.Chanson;
import metier.oeuvres.Oeuvre;

public class AxeAlbumDeChanson implements Axe {
	
	private ArrayList<Oeuvre> oeuvres;

	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		Chanson c = (Chanson) oeuvre;
		String res="";
		if (c.getAlbums() != null && c.getAlbums().size() != 0){
			res = c.getAlbums().get(0).getNom();
		}
		else res = "Pas d'album";
		return res;
	}

	@Override
	public String getType() {
		return "Album de la chanson";
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
