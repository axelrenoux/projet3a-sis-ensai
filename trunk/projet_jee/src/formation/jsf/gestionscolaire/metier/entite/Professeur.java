package formation.jsf.gestionscolaire.metier.entite;

/**
 * <p>
 * Représente un professeur.
 * </p>
 * 
 * @author sgringoire
 */
public class Professeur extends Personne {
	
	/************************** constructeurs  **************************/

	/**
	 * <p>
	 * Constructeur.
	 * </p>
	 */
	public Professeur() {
		super();
	}

	/**
	 * <p>
	 * Constructeur.
	 * </p>
	 * 
	 * @param nom
	 * @param prenom
	 */
	public Professeur(String nom, String prenom) {
		super(nom, prenom);
	}

	/**
	 * @param nom
	 * @param prenom
	 * @param login
	 * @param mdp
	 * @param photo
	 */
	public Professeur(String nom, String prenom,String login, String mdp, String photo) {
		super(nom, prenom, login, mdp, photo);
	}

	

	/**************************    methodes   **************************/

	/*
	 * retourne false car les professeurs ne sont pas autorises à faire des modifications
	 */
	public boolean isAutorisee() {
		return false;
	}
	
}
