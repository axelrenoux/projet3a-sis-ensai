package formation.jsf.gestionscolaire.metier.entite;

 

public abstract class Personne {
	
	/**************************   attributs  **************************/
	
	private static int SEQUENCE_ID = 0;
	private int id = -1;
	private String nom;
	private String prenom;
	private String login;//supposé unique dans la base de données
	private String motDePasse;
	private String photo;
	
	/************************** constructeurs **************************/
	
	/**
	 * <p>
	 * Constructeur.
	 * </p>
	 */
	public Personne() {
		super();
		id = SEQUENCE_ID++;
	}

	/**
	 * <p>
	 * Constructeur.
	 * </p>
	 * 
	 * @param nom
	 * @param prenom
	 */
	public Personne(String nom, String prenom) {
		super();
		id = SEQUENCE_ID++;
		this.nom = nom;
		this.prenom = prenom;
	}

	
	/**
	 * @param nom
	 * @param prenom
	 * @param login
	 * @param mdp
	 */
	public Personne(String nom, String prenom, String login, String mdp, String photo){
		super();
		id = SEQUENCE_ID++;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.motDePasse = mdp;
		this.photo = photo;
	}
	
	/************************** methodes    **************************/

	
	/**
	 * @return true si c'est un admin et false sinon
	 */
	public abstract boolean isAutorisee();
	
	
	/************************** getters/setters **************************/
	
	/**
	 * <p>
	 * Retourne la valeur de nom.
	 * </p>
	 * 
	 * @return Retourne la valeur de nom.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * <p>
	 * Initialise la valeur de nom.
	 * </p>
	 * 
	 * @param nom
	 *            Nouvelle valeur de nom.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * <p>
	 * Retourne la valeur de prenom.
	 * </p>
	 * 
	 * @return Retourne la valeur de prenom.
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * <p>
	 * Initialise la valeur de prenom.
	 * </p>
	 * 
	 * @param prenom
	 *            Nouvelle valeur de prenom.
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
	
	

	/**
	 * @return la valeur de login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * nouvelle valeur de login
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return la valeur de mot de passe
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * nouvelle valeur de mot de passe
	 * @param motDePasse
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * @return la valeur de photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * nouvelle valeur de photo
	 * @param photo
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
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
		Personne other = (Personne) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}


 
	
	
}
