package formulaire.reponses.gestion;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import metier.Artiste;
/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 *
 */
@ManagedBean
@SessionScoped
public class GestionFormulaire {
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private Recherche recherche;
	private ArrayList resultats;


	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/

	
	public void setObjectif(int ceQueRecherche) {
		//item1Chanson item2Artiste item3Album
		if(ceQueRecherche==1){
			recherche=new RechercheTrack();
		}
		else if(ceQueRecherche==2){
			recherche=new RechercheArtist();
		}
		else if(ceQueRecherche==3){
			recherche=new RechercheAlbum();
		}
		else if(ceQueRecherche==4){
			recherche=new RechercheGenerale();
		}
	}
	
	
	
	public ArrayList lancerRecherche() {
		resultats = recherche.lancerRecherche();
		return resultats;
		
	}

	


	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	
	public Recherche getRecherche() {
		return recherche;
	}



	public void setRecherche(Recherche recherche) {
		this.recherche = recherche;
	}
	
	 
	

}
