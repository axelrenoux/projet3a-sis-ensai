package formation.jsf.gestionscolaire.metier.entite;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * on crée cette classe pour pouvoir englober un sous-groupe dans un groupe global
 * un cours correspondra obligatoirement à un sous-groupe, 
 * et quand on affichera les cours ou personnes d'un groupe global, 
 * cela affichera les cours ou personnes de tous ses sous-groupes

 * @author sis1
 *
 */
public class GroupeGlobal extends Groupe {
	
	/**************************   attributs  **************************/
	private List<Groupe> listeGroupes;
	
	
	/************************** constructeur **************************/
	
	/**
	 * @param nom
	 * @param listeGroupe
	 */
	public GroupeGlobal(String nom, List<Groupe> listeGroupes) {
		super(nom);
		this.listeGroupes = listeGroupes;
	}


	/************************** methodes    **************************/
	
	
	/* 
	 * on recupere l'ensemble des personnes presentes dans chaque groupe
	 * (en verifiant de ne pas prendre pls fois la meme personne si elle
	 * est presente dans pls sous-groupes)
	 */
	public List<Personne> getListePersonnes() {
		List<Personne> personnes = new ArrayList<Personne>();
		for (Groupe g : getListeGroupes()){
			for (Personne p : g.getListePersonnes()){
				if(personnes.contains(p)==false) personnes.add(p);
			}	
		}
		return personnes;
	}

	
	

	public List<Groupe> getListeGroupes() {
		return listeGroupes;
	}
	
	
	

	 
	/************************** getters/setters **************************/
	
	


	/**
	 * @param listeGroupe
	 */
	public void setListeGroupes(List<Groupe> listeGroupe) {
		this.listeGroupes = listeGroupe;
	}
	
	 




}
