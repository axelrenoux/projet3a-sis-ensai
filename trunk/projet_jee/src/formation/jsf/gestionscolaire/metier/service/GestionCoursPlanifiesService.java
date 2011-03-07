package formation.jsf.gestionscolaire.metier.service;




import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import formation.jsf.gestionscolaire.metier.entite.CoursPlanifie;


 
/**
 * on en fait un javabean pour pouvoir y acceder tout au long 
 * de la session (une seule instance)
 * @author sis1
 *
 */
@ManagedBean 
@SessionScoped
public class GestionCoursPlanifiesService extends Gestion{
	
	
	/************************** methodes    **************************/
	
	/**
	 * <p>
	 * Retourne l'ensemble des cours planifies gérés par le système.
	 * </p>
	 * 
	 * @return Retourne l'ensemble des groupes gérées par le système.
	 */
	public List<CoursPlanifie> rechercherTous() {
		return getBaseDeDonnees().getCoursPlanifies();
	}
	
	
	/**
	 * methode qui ajoute en base un nouveau cours planifie
	 * @param cp
	 */
	public void ajoutCoursPlanifie(CoursPlanifie cp){
		getBaseDeDonnees().ajoutCoursPlanifie(cp);
	}
}
