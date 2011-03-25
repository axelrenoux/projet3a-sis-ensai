package calculsDesClusters.axe;

import java.util.ArrayList;

import metier.oeuvres.Chanson;
import metier.oeuvres.Oeuvre;

public class AxeDuree implements Axe{
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	
	private ArrayList<Oeuvre> oeuvres;
	//la duree est exprimee en millisecondes

	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		Chanson c = (Chanson) oeuvre;
		double duree = c.getDuree();
		String classe = "";
		
		if (duree == 0){
			classe = "Durée inconnue";
		}
		else if (duree < 100000){
			classe = "Moins de 1 minute";
		}
		else if (duree >=100000 && duree < 300000){
			classe = "De 1 à 3 minutes";
		}
		else if (duree >= 300000 && duree < 500000){
			classe = "De 3 à 5 minutes";
		}
		else if (duree >= 500000){
			classe = "Plus de 5 minutes";
		}
		
		return classe;
	}
	
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	@Override
	public String getType() {
		return "Durée";
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
