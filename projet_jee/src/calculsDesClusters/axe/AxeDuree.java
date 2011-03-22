package calculsDesClusters.axe;

import metier.oeuvres.Chanson;
import metier.oeuvres.Oeuvre;

public class AxeDuree implements Axe{
	
	//la duree est exprimee en millisecondes

	@Override
	public String getType() {
		return "Durée";
	}

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

}
