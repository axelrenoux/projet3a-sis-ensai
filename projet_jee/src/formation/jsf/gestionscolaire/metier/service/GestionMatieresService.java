package formation.jsf.gestionscolaire.metier.service;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

 
import formation.jsf.gestionscolaire.metier.entite.Matiere;
import formation.jsf.gestionscolaire.metier.entite.Professeur;

/**
 * 
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * <p>
 * Services de gestion des matières.
 * </p>
 * 
 * @author sgringoire
 */

@ManagedBean //ou par défaut son nom avec une minuscule
@SessionScoped
public class GestionMatieresService extends Gestion{
	
	/************************** methodes    **************************/
	
	/**
	 * <p>
	 * Retourne l'ensemble des matières gérées par le système.
	 * </p>
	 * 
	 * @return Retourne l'ensemble des matières gérées par le système.
	 */
	public List<Matiere> rechercherTous() {
		return getBaseDeDonnees().getMatieres();
	}
	

	/**
	 * @return retourne l'ensemble des langues gérées par le système
	 */
	public List<String> rechercherLangues() {
		return getBaseDeDonnees().getLangues();
	}
	
	/**
	 * @return retourne l'ensemble des professeurs gérés par le système
	 */ 
	public List<Professeur> rechercherProfesseurs() {
		return getBaseDeDonnees().getProfesseurs();
	}
	
	
}
