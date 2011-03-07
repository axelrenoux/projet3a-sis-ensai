package formation.jsf.gestionscolaire.metier.service;



import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import formation.jsf.gestionscolaire.metier.entite.Groupe;
import formation.jsf.gestionscolaire.metier.entite.Matiere;
import formation.jsf.gestionscolaire.metier.entite.Personne;


/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 *
 */
@ManagedBean //ou par défaut son nom avec une minuscule
@SessionScoped
public class GestionPersonnesService extends Gestion{

	/************************** methodes    **************************/
	
	/**
	 * 
	 * methode qui verifie que le mot de passe et le login mis en parametres
	 * correspondent bien a une personne de la base de donnees 
	 * 
	 * @param login
	 * @param mdp
	 * @return true si oui, false sinon
	 */
	public boolean verifierMDP(String login, String mdp){
		//on recupere la personne correspondant au login (etudiant ou prof)
		Personne p = getBaseDeDonnees().recupPersonne(login);
		if(p!=null){
			if((p.getMotDePasse()).equals(mdp)) return true;
			else return false;
		}
		else return false;
	}
	
	
	
	/**
	 * methode qui retourne la personne correspondant au login saisi
	 * @param login
	 * @return
	 */
	public Personne validerConnection(String login){
		return getBaseDeDonnees().recupPersonne(login);
	}

	
	/**
	 * @param personne
	 * @return l'ensemble des groupes dans lesquels la personne mise en parametre appartient
	 */
	public ArrayList<Groupe> groupesPersonne(Personne personne){
		ArrayList<Groupe> groupes = new ArrayList<Groupe>();
		for(Groupe g : getBaseDeDonnees().getGroupes()){
			if ((g.getListePersonnes()).contains(personne)){
				groupes.add(g);
			}
		}
		return groupes;
	}
	
	
	
	
	/**
	 * methode qui retourne l'ensemble des matieres d'un professeur
	 * @param personne
	 * @return liste de matieres
	 */
	public ArrayList<Matiere> matieresProfesseur(Personne personne){
		ArrayList<Matiere> matieres = new ArrayList<Matiere>();
		for(Matiere m : getBaseDeDonnees().getMatieres()){
			if ((m.getProfesseur()).equals(personne)){
				matieres.add(m);
			}
		}
		return matieres;
	}

	
}
