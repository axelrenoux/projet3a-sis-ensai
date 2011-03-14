package formulaire.recherches.vue;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 */
@ManagedBean
@SessionScoped
public class VueAffichageResultat {
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	private ArrayList resultats;
	private String type;
	
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	

	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/
	public ArrayList getResultats() {
		return resultats;
	}

	public void setResultats(ArrayList resultats) {
		this.resultats = resultats;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
 
	
}
