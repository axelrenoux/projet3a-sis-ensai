package formulaire.reponses.gestion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
	}
	
	
	
	public void lancerRecherche() {
		recherche.lancerRecherche();
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
