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
	private GestionRecherche gestion;

	public int setObjectif(int ceQueRecherche) {
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
		else return 0;
		return ceQueRecherche;
	}
	
	public void addTrack(String search, boolean aVerifier){
		gestion.addTrack(search,aVerifier);
	}
	
	public void addAlbum(String search, boolean aVerifier){
		gestion.addAlbum(search,aVerifier);
	}
	
	public void addArtist(String search, boolean aVerifier){
		gestion.addArtist(search,aVerifier);
	}

	public void lancerRecherche() {
		gestion.lancerRecherche();
	}

}
