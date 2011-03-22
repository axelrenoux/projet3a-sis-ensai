package calculsDesClusters.axe;

import java.sql.Date;

import metier.oeuvres.Album;
import metier.oeuvres.Oeuvre;

public class AxeSaison implements Axe{

	/**
	 * on definit "été" pour les dates du 1er avril au 30septembre
	 * on definit "hiver" pour les dates du 1er octobre au 31 mars
	 * @param d
	 * @return
	 */
	public String miseEnSaison(Date d){
		if(d.getMonth()+1<10 && d.getMonth()+1>3) return "été";
		else return "hiver";
	}

	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		Album a = (Album) oeuvre;
		if (a.getDate() != null){
			Date d = a.getDate();
			if(d.getMonth()+1<10 && d.getMonth()+1>3) return "été";
			else return "hiver";
		}else return "Saison inconnue";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "saison";
	}

}
