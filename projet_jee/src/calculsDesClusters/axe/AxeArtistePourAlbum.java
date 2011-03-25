package calculsDesClusters.axe;

import java.util.ArrayList;

import metier.oeuvres.Album;
import metier.oeuvres.Oeuvre;

public class AxeArtistePourAlbum implements Axe {

	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	
	private ArrayList<Oeuvre> oeuvres;
	

	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	@Override
	public String CalculAxe(Oeuvre oeuvre) {
		Album c = (Album) oeuvre;
		return c.getArtiste().getName();
	}
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	
	@Override
	public String getType() {
		return "Artiste";
	}

	@Override
	public void setOeuvres(ArrayList<Oeuvre> oeuvres) {
		this.oeuvres = oeuvres;	
	}
	
	@Override
	public ArrayList<Oeuvre> getOeuvres() {
		return oeuvres;
	}
	

}
