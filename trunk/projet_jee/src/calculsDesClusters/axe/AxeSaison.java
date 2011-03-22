package calculsDesClusters.axe;

import java.sql.Date;
import java.util.ArrayList;

import metier.oeuvres.Album;
import metier.oeuvres.Oeuvre;

public class AxeSaison implements Axe{

	
	private ArrayList<Oeuvre> oeuvres;
	
	/**
	 * on definit "été" pour les dates du 1er avril au 30septembre
	 * on definit "hiver" pour les dates du 1er octobre au 31 mars
	 * @param d
	 * @return
	 */
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
	
	@Override
	public ArrayList<Oeuvre> getOeuvres() {
		return oeuvres;
	}
	
	@Override
	public void setOeuvres(ArrayList<Oeuvre> oeuvres) {
		this.oeuvres = oeuvres;	
	}

}
