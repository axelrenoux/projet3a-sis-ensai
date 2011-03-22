package calculsDesClusters.axe;

import metier.oeuvres.Oeuvre;

public class AxeTag implements Axe {

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
}
