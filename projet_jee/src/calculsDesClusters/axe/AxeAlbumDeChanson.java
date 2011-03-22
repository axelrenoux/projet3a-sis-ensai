package calculsDesClusters.axe;

import metier.oeuvres.Chanson;
import metier.oeuvres.Oeuvre;

public class AxeAlbumDeChanson implements Axe {

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

}
