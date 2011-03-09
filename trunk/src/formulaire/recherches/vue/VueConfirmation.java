package formulaire.recherches.vue;

import java.util.ArrayList;

 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 */
@ManagedBean
@SessionScoped
public class VueConfirmation {
	private String compteRendu="Votre requete est :"+"/n";

	public String getCompteRendu() {
		return compteRendu;
	}

	public void setCompteRendu(String compteRendu) {
		this.compteRendu = compteRendu;
	}
}
