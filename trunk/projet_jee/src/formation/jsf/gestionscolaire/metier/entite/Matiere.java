package formation.jsf.gestionscolaire.metier.entite;

/**
 * <p>
 * Représente une matière.
 * </p>
 * 
 * @author sgringoire
 */
public class Matiere {
	
	/**************************   attributs  **************************/
	
	/** Intitulé (String, Obligatoire). */
	private String intitule;
	/** Langue (String), parmi la liste des langues gérée par le système. */
	private String langue;
	/** Professeur (Obligatoire), parmi la liste des professeurs enregistrés sur le système. */
	private Professeur professeur;
	/** Coefficient (Double), entre 1 et 5, un seul chiffre après la virgule. */
	private Double coefficient;
	/** Nombre d’heures de cours (Integer). */
	private Integer nbHeuresCours;
	/** Nombre d’heures d’atelier (Integer). */
	private Integer nbHeuresAtelier;
	/** Nombre d’heures de projet (Integer). */
	private Integer nbHeuresProjet;
	
	
	/************************** constructeur **************************/
	
	
	/**
	 * <p>
	 * Constructeur.
	 * </p>
	 */
	public Matiere() {
		super();
	}

	/**
	 * <p>
	 * Constructeur.
	 * </p>
	 * 
	 * @param intitule
	 * @param langue
	 * @param professeur
	 * @param coefficient
	 * @param nbHeuresCours
	 */
	public Matiere(String intitule, String langue, Professeur professeur, Double coefficient, Integer nbHeuresCours) {
		super();
		this.intitule = intitule;
		this.langue = langue;
		this.professeur = professeur;
		this.coefficient = coefficient;
		this.nbHeuresCours = nbHeuresCours;
	}

	/************************** getters/setters **************************/
	
	/**
	 * <p>
	 * Retourne la valeur de intitule.
	 * </p>
	 * 
	 * @return Retourne la valeur de intitule.
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * <p>
	 * Initialise la valeur de intitule.
	 * </p>
	 * 
	 * @param intitule
	 *            Nouvelle valeur de intitule.
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	/**
	 * <p>
	 * Retourne la valeur de langue.
	 * </p>
	 * 
	 * @return Retourne la valeur de langue.
	 */
	public String getLangue() {
		return langue;
	}

	/**
	 * <p>
	 * Initialise la valeur de langue.
	 * </p>
	 * 
	 * @param langue
	 *            Nouvelle valeur de langue.
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}

	/**
	 * <p>
	 * Retourne la valeur de professeur.
	 * </p>
	 * 
	 * @return Retourne la valeur de professeur.
	 */
	public Professeur getProfesseur() {
		return professeur;
	}

	/**
	 * <p>
	 * Initialise la valeur de professeur.
	 * </p>
	 * 
	 * @param professeur
	 *            Nouvelle valeur de professeur.
	 */
	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}

	/**
	 * <p>
	 * Retourne la valeur de coefficient.
	 * </p>
	 * 
	 * @return Retourne la valeur de coefficient.
	 */
	public Double getCoefficient() {
		return coefficient;
	}

	/**
	 * <p>
	 * Initialise la valeur de coefficient.
	 * </p>
	 * 
	 * @param coefficient
	 *            Nouvelle valeur de coefficient.
	 */
	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

	/**
	 * <p>
	 * Retourne la valeur de nbHeuresCours.
	 * </p>
	 * 
	 * @return Retourne la valeur de nbHeuresCours.
	 */
	public Integer getNbHeuresCours() {
		return nbHeuresCours;
	}

	/**
	 * <p>
	 * Initialise la valeur de nbHeuresCours.
	 * </p>
	 * 
	 * @param nbHeuresCours
	 *            Nouvelle valeur de nbHeuresCours.
	 */
	public void setNbHeuresCours(Integer nbHeuresCours) {
		this.nbHeuresCours = nbHeuresCours;
	}

	/**
	 * <p>
	 * Retourne la valeur de nbHeuresAtelier.
	 * </p>
	 * 
	 * @return Retourne la valeur de nbHeuresAtelier.
	 */
	public Integer getNbHeuresAtelier() {
		return nbHeuresAtelier;
	}

	/**
	 * <p>
	 * Initialise la valeur de nbHeuresAtelier.
	 * </p>
	 * 
	 * @param nbHeuresAtelier
	 *            Nouvelle valeur de nbHeuresAtelier.
	 */
	public void setNbHeuresAtelier(Integer nbHeuresAtelier) {
		this.nbHeuresAtelier = nbHeuresAtelier;
	}

	/**
	 * <p>
	 * Retourne la valeur de nbHeuresProjet.
	 * </p>
	 * 
	 * @return Retourne la valeur de nbHeuresProjet.
	 */
	public Integer getNbHeuresProjet() {
		return nbHeuresProjet;
	}

	/**
	 * <p>
	 * Initialise la valeur de nbHeuresProjet.
	 * </p>
	 * 
	 * @param nbHeuresProjet
	 *            Nouvelle valeur de nbHeuresProjet.
	 */
	public void setNbHeuresProjet(Integer nbHeuresProjet) {
		this.nbHeuresProjet = nbHeuresProjet;
	}
	
	

	/************************** surcharge des methodes
								hashCode et equals **************************/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((intitule == null) ? 0 : intitule.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matiere other = (Matiere) obj;
		if (intitule == null) {
			if (other.intitule != null)
				return false;
		} else if (!intitule.equals(other.intitule))
			return false;
		return true;
	}

	

}
