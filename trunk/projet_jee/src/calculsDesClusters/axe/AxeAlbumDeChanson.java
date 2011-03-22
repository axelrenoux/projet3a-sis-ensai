package calculsDesClusters.axe;

import java.util.ArrayList;

import metier.oeuvres.Chanson;
import metier.oeuvres.Oeuvre;

public class AxeAlbumDeChanson implements Axe {
	
	private ArrayList<Oeuvre> oeuvres;

	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		Chanson c = (Chanson) oeuvre;
		if (c.getAlbums() != null && c.getAlbums().size() != 0){
			return c.getAlbums().get(0).getNom();
		}
		else return "Pas d'album";
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
