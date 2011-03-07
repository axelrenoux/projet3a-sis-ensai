package formation.jsf.gestionscolaire.metier.service;

import javax.faces.bean.ManagedProperty;

import formation.jsf.gestionscolaire.metier.bdd.BaseDeDonnees;


public class Gestion {
	
	/**************************   attributs  **************************/
	
	
	/**
	 * Gestion possede un managedbean baseDedonnnes, 
	 * pour y avoir accès tout au long de la session
	 */
	@ManagedProperty(value="#{baseDeDonnees}")
	private BaseDeDonnees baseDeDonnees;
	

	/************************** getters/setters **************************/
	
	/**
	 * <p>
	 * Retourne la valeur de baseDeDonnees.
	 * </p>
	 * 
	 * @return Retourne la valeur de baseDeDonnees.
	 */
	public BaseDeDonnees getBaseDeDonnees() {
		return baseDeDonnees;
	}

	/**
	 * <p>
	 * Initialise la valeur de baseDeDonnees.
	 * </p>
	 * 
	 * @param baseDeDonnees
	 *            Nouvelle valeur de baseDeDonnees.
	 */
	public void setBaseDeDonnees(BaseDeDonnees baseDeDonnees) {
		this.baseDeDonnees = baseDeDonnees;
	}
	

}
