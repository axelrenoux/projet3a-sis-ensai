package calculsDesClusters.axe;

import metier.oeuvres.Oeuvre;

public class AxeOeuvre implements Axe{

	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		return oeuvre.getNom();
	}

}
