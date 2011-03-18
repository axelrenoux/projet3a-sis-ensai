package calculsDesClusters.axe;

import metier.oeuvres.Oeuvre;

public class AxeTag implements Axe {

	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		return oeuvre.getToptags().get(0).getName();
	}

}
