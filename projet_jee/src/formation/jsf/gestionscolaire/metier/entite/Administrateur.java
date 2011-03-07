package formation.jsf.gestionscolaire.metier.entite;

public class Administrateur extends Personne {
	
	/************************** constructeur  **************************/
	

	/**
	 * @param nom
	 * @param prenom
	 * @param login
	 * @param mdp
	 * @param photo
	 */
	public Administrateur(String nom, String prenom, String login, String mdp, String photo){
		super(nom,prenom,login,mdp,photo);
	}

	/**************************    methodes   **************************/
	
	/*
	 * retourne vrai car l'administrateur est autorisé à faire des modifications 
	 */
	public boolean isAutorisee() {
		return true;
	}
	
	
}
