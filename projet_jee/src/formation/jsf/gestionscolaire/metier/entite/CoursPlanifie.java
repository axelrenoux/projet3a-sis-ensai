package formation.jsf.gestionscolaire.metier.entite;

import java.util.Date;

/**
 * <p>
 * Représente un cours planifié.
 * </p>
 * 
 * @author sgringoire
 */
public class CoursPlanifie {
	
	/**************************   attributs  **************************/
	
	private static int SEQUENCE_ID = 0;
	private int id = -1;

	private Matiere matiere;
	private Groupe groupe;
	private Date dateDebut;
	private Date dateFin;

	/************************** constructeurs  **************************/
	
	
	/**
	 * <p>
	 * Constructeur.
	 * </p>
	 * 
	 * @param matiere
	 * @param groupe
	 * @param dateDebut
	 * @param dateFin
	 */
	public CoursPlanifie(Matiere matiere, Groupe groupe, Date dateDebut, Date dateFin) {
		super();
		id = SEQUENCE_ID++;
		this.matiere = matiere;
		this.groupe = groupe;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	
	public CoursPlanifie(){
	}
	
	/*********************** getters/setters  **********************/
	
	/**
	 * <p>
	 * Retourne la valeur de matiere.
	 * </p>
	 * 
	 * @return Retourne la valeur de matiere.
	 */
	public Matiere getMatiere() {
		return matiere;
	}

	/**
	 * <p>
	 * Initialise la valeur de matiere.
	 * </p>
	 * 
	 * @param matiere
	 *            Nouvelle valeur de matiere.
	 */
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	/**
	 * <p>
	 * Retourne la valeur de groupe.
	 * </p>
	 * 
	 * @return Retourne la valeur de groupe.
	 */
	public Groupe getGroupe() {
		return groupe;
	}

	/**
	 * <p>
	 * Initialise la valeur de groupe.
	 * </p>
	 * 
	 * @param groupe
	 *            Nouvelle valeur de groupe.
	 */
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	/**
	 * <p>
	 * Retourne la valeur de dateDebut.
	 * </p>
	 * 
	 * @return Retourne la valeur de dateDebut.
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * <p>
	 * Initialise la valeur de dateDebut.
	 * </p>
	 * 
	 * @param dateDebut
	 *            Nouvelle valeur de dateDebut.
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * <p>
	 * Retourne la valeur de dateFin.
	 * </p>
	 * 
	 * @return Retourne la valeur de dateFin.
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * <p>
	 * Initialise la valeur de dateFin.
	 * </p>
	 * 
	 * @param dateFin
	 *            Nouvelle valeur de dateFin.
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * <p>
	 * Retourne la valeur de id.
	 * </p>
	 * 
	 * @return Retourne la valeur de id.
	 */
	public int getId() {
		return id;
	}

	
	/************************** surcharge des methodes
								hashCode et equals **************************/

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CoursPlanifie other = (CoursPlanifie) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	 
	
}
