package calculsDesClusters.axe;

import java.sql.Date;

import metier.oeuvres.Album;
import metier.oeuvres.Oeuvre;

public class AxeAnnee implements Axe {
	


	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		Album a = (Album) oeuvre;
		String classe = "";
		if(a.getDate()!=null){
			Date date = a.getDate();
			String year = date.getYear()+1900+"";
			String yearArrondiString = year.substring(0,3)+"0";
			//on n'a plus que 2010,2000,1990,1980...
			int yearArrondi = Integer.parseInt(yearArrondiString);
			
			switch (yearArrondi) {
			case 2010: 
				classe = "années 2010";
				break;
			case 2000: 
				classe = "années 2000";
				break;
			case 1990: 
				classe = "années 90";
				break;
			case 1980: 
				classe = "années 80";
				break;
			case 1970: 
				classe = "années 70";
				break;
			case 1960: 
				classe = "années 60";
				break;
			default: 
				classe = "année inconnue";
				break;
			}
			
			if(yearArrondi<1960){
				classe="avant les années 60";
			}
		}
		
		else{
			classe = "année inconnue";
		}
		

	
		return classe;
		
	}
	
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Année";
	}

}
