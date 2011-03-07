package formation.jsf.gestionscolaire.metier.entite;

public class Etudiant extends Personne {
	
	
	/************************** constructeur  **************************/
	
	/**
	 * @param nom
	 * @param prenom
	 * @param login
	 * @param mdp
	 * @param photo
	 */
	public Etudiant(String nom, String prenom, String login, String mdp, String photo){
		super(nom,prenom,login,mdp,photo);
	}

	/**************************    methodes   **************************/
	
	/*
	 * retourne false car les etudiants ne sont pas autorises à faire des modifications
	 */
	public boolean isAutorisee() {
		return false;
	}

	
}
