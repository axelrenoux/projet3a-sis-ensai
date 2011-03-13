package formulaire.recherches.vueOLD;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 */
@ManagedBean
@SessionScoped
public class VueConfirmationOld {
	private String compteRendu="Votre requete est :"+"/n";

	public String getCompteRendu() {
		return compteRendu;
	}

	public void setCompteRendu(String compteRendu) {
		this.compteRendu = compteRendu;
	}
}
