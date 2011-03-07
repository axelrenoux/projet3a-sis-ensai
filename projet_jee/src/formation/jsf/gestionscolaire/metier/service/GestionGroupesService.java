package formation.jsf.gestionscolaire.metier.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import formation.jsf.gestionscolaire.metier.entite.CoursPlanifie;
import formation.jsf.gestionscolaire.metier.entite.Groupe;
 


/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 *
 */
@ManagedBean  
@SessionScoped
public class GestionGroupesService extends Gestion{
	
	
	
	/************************** methodes    **************************/
	
	
	/**
	 * <p>
	 * Retourne l'ensemble des groupes gérés par le système.
	 * </p>
	 * 
	 * @return Retourne l'ensemble des groupes gérés par le système.
	 */
	public List<Groupe> rechercherTous() {
		return getBaseDeDonnees().getGroupes();
	}
	 
 
	/**
	 * <p>
	 * Retourne l'ensemble des groupes n'étant pas englobés par d'autres groupes
	 * (les racines de l'arbre des groupes)
	 * </p>
	 * 
	 * @return
	 */
	public List<Groupe> rechercherTousGroupesGlobaux() {
		List<Groupe> groupesGlobaux = new ArrayList<Groupe>();
		for (Groupe gg : getBaseDeDonnees().getGroupes()){
			if(!estEnglobe(gg)){
				groupesGlobaux.add(gg);
			}
		}
		return groupesGlobaux;
	}
	
	/**
	 * <p>
	 * Retourne l'ensemble des groupes n'étant pas englobés par d'autres groupes
	 * (les racines de l'arbre des groupes) 
	 * si le groupe n'a pas de sous-groupes, on verifie qu'il a au moins un cours de 
	 * planifié 
	 * </p>
	 * 
	 * @return
	 */
	public List<Groupe> rechercherTousGroupesGlobauxAvecCours() {
		List<Groupe> groupesGlobaux = new ArrayList<Groupe>();
		for (Groupe gg : getBaseDeDonnees().getGroupes()){
			if(!estEnglobe(gg)){
				if(gg.getListeGroupes()==null){
					if(possedeDesCours(gg)){
						groupesGlobaux.add(gg);
					}
				}else groupesGlobaux.add(gg);
			}
		}
		return groupesGlobaux;
	}
	
	
	
 
	
	
	/**
	 * methode qui verifie si un groupe est englobe par au moins un groupe global
	 * @param groupe
	 * @return true or false
	 */
	public boolean estEnglobe(Groupe groupe){
		for(Groupe gg : getBaseDeDonnees().getGroupesGlobaux()){
			if(gg.getListeGroupes().contains(groupe)){
				//si le groupe mis en parametre est englobé par au moins un groupe global
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * methode qui verifie qu'un groupe a des cours de planifie
	 * @param groupe
	 * @return
	 */
	public boolean possedeDesCours(Groupe groupe){
		for (CoursPlanifie cp : getBaseDeDonnees().getCoursPlanifies()){
			if(cp.getGroupe().equals(groupe)){
				//si au moins un cours est planifie pour ce groupe
				return true;
			}
		}
		return false;
	}
	 
}
