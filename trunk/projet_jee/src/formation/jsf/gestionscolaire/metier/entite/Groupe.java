package formation.jsf.gestionscolaire.metier.entite;

 
import java.util.List;

/**
 * 
 * on choisit d'en faire une classe abstraite pour pouvoir implementer le pattern composite:
 * - la classe groupe a deux classes filles : groupeGlobal et sousGroupe
 * - un groupe-global englobe un à pls groupes
 * - chaque cours planifié correspond à un sous-groupe ou à un groupeGlobal,
 * - chaque sous-groupe à une liste de personnes 
 * @author sis1
 *
 */
public abstract class Groupe {
	
	/**************************   attributs  **************************/
	
	private static int SEQUENCE_ID = 0;
	private int id = -1;
	private String nom;

	/************************** constructeur **************************/
	
	/**
	 * <p>
	 * Constructeur.
	 * </p>
	 * 
	 * @param nom
	 */
	public Groupe(String nom) {
		super();
		id = SEQUENCE_ID++;
		this.nom = nom;
	}

	/************************** methodes    **************************/

	
	/**
	 * methode qui recupere les personnes appartenant au groupe global ou au sous groupe
	 * @return listePersonnes
	 */
	public abstract List<Personne> getListePersonnes();
	
	
	/**
	 * methode qui retourne la liste des groupes englobes par le groupe global
	 * ou null si c'est un sous groupe
	 * @return listeGroupes
	 */
	public abstract List<Groupe> getListeGroupes();
	
	
	
	
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
		Groupe other = (Groupe) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	
 
}
