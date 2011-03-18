package calculsDesClusters.axe;

import metier.oeuvres.Oeuvre;

public class AxeArtiste implements Axe{

	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		return oeuvre.getNom();
	}

}
