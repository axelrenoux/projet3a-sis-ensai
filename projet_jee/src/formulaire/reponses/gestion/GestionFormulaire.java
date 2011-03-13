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
	
	private GestionRecherche gestion;


	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/

	
	public void setObjectif(int ceQueRecherche) {
		//item1Chanson item2Artiste item3Album
		if(ceQueRecherche==1){
			gestion=new GestionRechercheTrack();
		}
		else if(ceQueRecherche==2){
			gestion=new GestionRechercheArtist();
		}
		else if(ceQueRecherche==3){
			gestion=new GestionRechercheAlbum();
		}
	}
	
	public void addTrack(String search){
		gestion.addTrack(search);
	}
	
	public void addAlbum(String search){
		gestion.addAlbum(search);
	}
	
	public void addArtist(String search){
		gestion.addArtist(search);
	}

	public void addMotCle(String search){
		gestion.addMotCle(search);
	}
	
	public void addTag(String search){
		gestion.addTag(search);
	}
	
	public void lancerRecherche() {
		gestion.lancerRecherche();
	}
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	

	public GestionRecherche getGestion() {
		return gestion;
	}

	public void setGestion(GestionRecherche gestion) {
		this.gestion = gestion;
	}
	
	

}
