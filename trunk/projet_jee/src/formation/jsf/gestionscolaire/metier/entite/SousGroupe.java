package formation.jsf.gestionscolaire.metier.entite;

import java.util.ArrayList;
import java.util.List;



/**
 * on crée cette classe pour pouvoir englober un sous-groupe dans un groupe global
 * un cours correspondra obligatoirement à un sous-groupe, 
 * et quand on affichera les cours ou personnes d'un groupe global, 
 * cela affichera les cours ou personnes de tous ses sous-groupes
 * 
 * @author sis1
 *
 */
public class SousGroupe extends Groupe{
	
	/**************************   attributs  **************************/
	private List<Personne> listePersonnes;
	
	
	/************************** constructeur **************************/
	
	/**
	 * @param nom
	 * @param listePersonnes
	 */
	public SousGroupe(String nom, List<Personne> listePersonnes) {
		super(nom);
		this.listePersonnes = listePersonnes;
	}

	/************************** methodes    **************************/
	

	
	/* 
	 * recupere l'ensemble des personnes presentes dans le sous groupe
	 */
	public List<Personne> getListePersonnes() {
		return listePersonnes;
	}

	public List<Groupe> getListeGroupes() {
		return null;
	}

	/************************** getters/setters **************************/
	
	
	public void setListePersonnes(ArrayList<Personne> listePersonnes) {
		this.listePersonnes = listePersonnes;
	}
	
	
	
	
}
