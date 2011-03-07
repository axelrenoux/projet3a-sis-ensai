package formation.jsf.gestionscolaire.metier.bdd;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


import formation.jsf.gestionscolaire.metier.entite.Administrateur;
import formation.jsf.gestionscolaire.metier.entite.CoursPlanifie;
import formation.jsf.gestionscolaire.metier.entite.Etudiant;
import formation.jsf.gestionscolaire.metier.entite.Groupe;
import formation.jsf.gestionscolaire.metier.entite.GroupeGlobal;
import formation.jsf.gestionscolaire.metier.entite.Matiere;
import formation.jsf.gestionscolaire.metier.entite.Personne;
import formation.jsf.gestionscolaire.metier.entite.Professeur;
import formation.jsf.gestionscolaire.metier.entite.SousGroupe;
 

/**
 * <p>
 * Représente la base de données.
 * </p>
 * 
 * @author sgringoire
 */

/*
 * on ajoute ces 2 lignes afin que la bdd soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 */
@ManagedBean //ou par défaut son nom avec une minuscule
@ApplicationScoped
public class BaseDeDonnees {
	public final static String LANGUE_FRANCAIS = "Français";
	public final static String LANGUE_ANGLAIS = "Anglais";

	public static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	private List<Matiere> cacheMatieres;
	private List<String> cacheLangues;
	private List<Professeur> cacheProfesseurs;
	private List<Groupe> cacheGroupes;
	private List<CoursPlanifie> cacheCoursPlanifies;
	private List<Etudiant> cacheEtudiants;
	private List<Administrateur> cacheAdministrateurs;
	private List<Personne> cacheEtudiants1A_anglais1Maths;
	private List<Personne> cacheEtudiants1A_anglais1IES;
	private List<Personne> cacheEtudiants1A_anglais2Maths;
	private List<Personne> cacheEtudiants1A_anglais2IES;
	private List<Personne> cacheEtudiants2A_anglais1;
	private List<Personne> cacheEtudiants2A_anglais2;	
	private List<Personne> cacheGroupeProf;
	private List<Personne> cacheGroupeAdmin;
	
	private Groupe groupe1AAnglais1Maths;
	private Groupe groupe1AAnglais2Maths;
	private Groupe groupe1AAnglais1IES;
	private Groupe groupe1AAnglais2IES;
	private Groupe groupe2AAnglais1;
	private Groupe groupe2AAnglais2;
	private Groupe groupe1AMaths;
	private Groupe groupe1AIES;
	private Groupe groupe1AAnglais1;
	private Groupe groupe1AAnglais2;
	
	private List<Groupe> listeGroupes1A_Anglais1;
	private List<Groupe> listeGroupes1A_Anglais2;
	private List<Groupe> listeGroupes1A_Maths;
	private List<Groupe> listeGroupes1A_IES;
	private List<Groupe> listeGroupes1A;
	private List<Groupe> listeGroupes2A;
	
	
	
	
	/**
	 * <p>
	 * Initialisation de la base de données: charge un ensemble de données par défaut.
	 * </p>
	 */
	@PostConstruct
	public void init() {
		/** Création des langues. */
		cacheLangues = new ArrayList<String>();
		cacheLangues.add(LANGUE_FRANCAIS);
		cacheLangues.add(LANGUE_ANGLAIS);

		/** Création des Professeurs. */
		cacheProfesseurs = new ArrayList<Professeur>();
		cacheProfesseurs.add(new Professeur("Flanders", "Ned", "ned", "mdp","ned.jpg"));
		cacheProfesseurs.add(new Professeur("Simpson", "Homer","homer", "mdp","homer.gif"));
		cacheProfesseurs.add(new Professeur("Skinner", "Seymour","seymour", "mdp","skinner.png"));
		 
		/**Création des administrateurs. */
		cacheAdministrateurs = new ArrayList<Administrateur>();
		cacheAdministrateurs.add(new Administrateur("Charles","Montgomery Burns","MrBurns","mdp","MrBurns.jpg" ));
		
		
		/** Création des matières. */
		cacheMatieres = new ArrayList<Matiere>();
		cacheMatieres.add(new Matiere("Bonne conduite", LANGUE_FRANCAIS, cacheProfesseurs.get(0), 3.5, 10));
		cacheMatieres.add(new Matiere("Physique nucléaire", LANGUE_FRANCAIS, cacheProfesseurs.get(1), 1.5, 10));
		cacheMatieres.add(new Matiere("Anglais", LANGUE_FRANCAIS, cacheProfesseurs.get(2), 1.5, 10));

		
		
		/** Création des etudiants */
		cacheEtudiants = new ArrayList<Etudiant>();
		cacheEtudiants.add(new Etudiant("Renoux","Axel", "axel", "mdp","axel.jpg"));//0
		cacheEtudiants.add(new Etudiant("Perrot","Marie",  "marie", "mdp","marge.png"));//1
		cacheEtudiants.add(new Etudiant("Simpson","Lisa",  "lisa", "mdp","lisa.gif"));//2
		cacheEtudiants.add(new Etudiant("Simpson","Bart",  "bart", "mdp","bart.jpg"));//3
		cacheEtudiants.add(new Etudiant("Simpson","Maggie",  "maggie", "mdp","maggie.gif"));//4
		cacheEtudiants.add(new Etudiant("Muntz","Nelson",  "nelson", "mdp","nelson.gif"));//5
		cacheEtudiants.add(new Etudiant("Van Houten","Milhouse",  "milhouse", "mdp","milhouse.png"));//6
		cacheEtudiants.add(new Etudiant("Gumble","Barney",  "barney", "mdp","barney.jpg"));//7
		cacheEtudiants.add(new Etudiant("Bob","Tahiti",  "tahitibob", "mdp","tahitibob.jpg")); //8
		cacheEtudiants.add(new Etudiant("Flanders","Todd",  "todd", "mdp","todd.png")); //9
		cacheEtudiants.add(new Etudiant("Flanders","Rod",  "rodd", "mdp","rod.png")); //10
		cacheEtudiants.add(new Etudiant("Bouvier","Patty",  "patty", "mdp","patty.jpg")); //11
		cacheEtudiants.add(new Etudiant("Bouvier","Selma",  "selma", "mdp","selma.png")); //12
		
		
		/** création des listes de membres de sous-groupes */
		
		cacheEtudiants1A_anglais1Maths= new ArrayList<Personne>();
		cacheEtudiants1A_anglais1Maths.add(cacheEtudiants.get(0));
		cacheEtudiants1A_anglais1Maths.add(cacheEtudiants.get(1));
		
		cacheEtudiants1A_anglais1IES= new ArrayList<Personne>();
		cacheEtudiants1A_anglais1IES.add(cacheEtudiants.get(2));
		cacheEtudiants1A_anglais1IES.add(cacheEtudiants.get(3));
		
		cacheEtudiants1A_anglais2Maths= new ArrayList<Personne>();
		cacheEtudiants1A_anglais2Maths.add(cacheEtudiants.get(4));
		cacheEtudiants1A_anglais2Maths.add(cacheEtudiants.get(5));
		
		cacheEtudiants1A_anglais2IES= new ArrayList<Personne>();
		cacheEtudiants1A_anglais2IES.add(cacheEtudiants.get(6));
		cacheEtudiants1A_anglais2IES.add(cacheEtudiants.get(7));
			
		cacheEtudiants2A_anglais1= new ArrayList<Personne>();
		cacheEtudiants2A_anglais1.add(cacheEtudiants.get(8));
		cacheEtudiants2A_anglais1.add(cacheEtudiants.get(9));
		
		cacheEtudiants2A_anglais2= new ArrayList<Personne>();
		cacheEtudiants2A_anglais2.add(cacheEtudiants.get(10));
		cacheEtudiants2A_anglais2.add(cacheEtudiants.get(11));
		cacheEtudiants2A_anglais2.add(cacheEtudiants.get(12));
		
		cacheGroupeProf = new ArrayList<Personne>();
		cacheGroupeProf.addAll(cacheProfesseurs);

		cacheGroupeAdmin= new ArrayList<Personne>();
		cacheGroupeAdmin.add(cacheAdministrateurs.get(0));
		
		/** Création des groupes. 
		 * chaque sous-groupe possede une liste d etudiants ou de professeurs ou d'administrateurs
		 * chaque groupe-global possede une liste de sous-groupes 
		 */
		
		/**les sous-groupes*/
		groupe1AAnglais1IES = new SousGroupe("1A Anglais1 IES", cacheEtudiants1A_anglais1Maths);
		groupe1AAnglais1Maths = new SousGroupe("1A Anglais1 Maths", cacheEtudiants1A_anglais1IES);
		groupe1AAnglais2IES = new SousGroupe("1A Anglais2 IES", cacheEtudiants1A_anglais2IES);
		groupe1AAnglais2Maths = new SousGroupe("1A Anglais2 Maths", cacheEtudiants1A_anglais2Maths);
		
		listeGroupes1A_Anglais1 = new ArrayList<Groupe>();
		listeGroupes1A_Anglais1.add(groupe1AAnglais1IES);
		listeGroupes1A_Anglais1.add(groupe1AAnglais1Maths);
		listeGroupes1A_Anglais2= new ArrayList<Groupe>();
		listeGroupes1A_Anglais2.add(groupe1AAnglais2Maths);
		listeGroupes1A_Anglais2.add(groupe1AAnglais2IES);
		
		listeGroupes1A_Maths = new ArrayList<Groupe>();
		listeGroupes1A_Maths.add(groupe1AAnglais1Maths);
		listeGroupes1A_Maths.add(groupe1AAnglais2Maths);
		
		listeGroupes1A_IES = new ArrayList<Groupe>();
		listeGroupes1A_IES.add(groupe1AAnglais1IES);
		listeGroupes1A_IES.add(groupe1AAnglais2IES);
		
		
		/**les groupes globaux*/
		groupe1AMaths = new GroupeGlobal("1A Maths", listeGroupes1A_Maths);
		groupe1AIES = new GroupeGlobal("1A IES", listeGroupes1A_IES);
		groupe1AAnglais1 =new GroupeGlobal("1A Anglais1",listeGroupes1A_Anglais1);
		groupe1AAnglais2 =new GroupeGlobal("1A Anglais2",listeGroupes1A_Anglais2);
		
		
		
		listeGroupes1A = new ArrayList<Groupe>();
		listeGroupes1A.add(groupe1AMaths);
		listeGroupes1A.add(groupe1AIES);
		listeGroupes1A.add(groupe1AAnglais1);
		listeGroupes1A.add(groupe1AAnglais2);
		
		
		groupe2AAnglais1 = new SousGroupe("2A Anglais1", cacheEtudiants2A_anglais1);
		groupe2AAnglais2 = new SousGroupe("2A Anglais2", cacheEtudiants2A_anglais2);
		
		listeGroupes2A = new ArrayList<Groupe>();
		listeGroupes2A.add(groupe2AAnglais1);
		listeGroupes2A.add(groupe2AAnglais2);
		
		
		
		
		
		cacheGroupes = new ArrayList<Groupe>();
		cacheGroupes.add(new GroupeGlobal("1ère année", listeGroupes1A));//0
		cacheGroupes.add(new GroupeGlobal("2ème année", listeGroupes2A));//1
		cacheGroupes.add(groupe1AMaths);//2
		cacheGroupes.add(groupe1AIES);//3
		cacheGroupes.add(groupe1AAnglais1);//4
		cacheGroupes.add(groupe1AAnglais2);//5
		cacheGroupes.add(groupe1AAnglais1IES);//6
		cacheGroupes.add(groupe1AAnglais1Maths);//7
		cacheGroupes.add(groupe1AAnglais2Maths);//8
		cacheGroupes.add(groupe1AAnglais2IES);//9
		cacheGroupes.add(groupe2AAnglais1);//10
		cacheGroupes.add(groupe2AAnglais2);//11
		cacheGroupes.add(new SousGroupe("Professeurs", cacheGroupeProf));//12
		cacheGroupes.add(new SousGroupe("Administrateurs", cacheGroupeAdmin));//13
	
		
		/** Création du planning des cours. */
		cacheCoursPlanifies = new ArrayList<CoursPlanifie>();
		try {
			/**cours generaux des 1A*/
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(0), cacheGroupes.get(0), dateFormat
					.parse("29/11/2010 10:00"), dateFormat.parse("29/11/2010 12:00")));
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(1), cacheGroupes.get(0), dateFormat
					.parse("29/11/2010 14:00"), dateFormat.parse("29/11/2010 16:00")));
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(0), cacheGroupes.get(0), dateFormat
					.parse("29/11/2010 16:00"), dateFormat.parse("29/11/2010 18:00")));

			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(1), cacheGroupes.get(0), dateFormat
					.parse("30/11/2010 8:00"), dateFormat.parse("30/11/2010 10:00")));
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(0), cacheGroupes.get(0), dateFormat
					.parse("30/11/2010 14:00"), dateFormat.parse("30/11/2010 16:00")));

			/**cours generaux des 2A*/
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(1), cacheGroupes.get(1), dateFormat
					.parse("29/11/2010 10:00"), dateFormat.parse("29/11/2010 12:00")));
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(0), cacheGroupes.get(1), dateFormat
					.parse("29/11/2010 14:00"), dateFormat.parse("29/11/2010 16:00")));
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(1), cacheGroupes.get(1), dateFormat
					.parse("02/12/2010 16:00"), dateFormat.parse("02/12/2010 18:00")));

			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(0), cacheGroupes.get(1), dateFormat
					.parse("03/12/2010 8:00"), dateFormat.parse("03/12/2010 10:00")));
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(1), cacheGroupes.get(1), dateFormat
					.parse("03/12/2010 14:00"), dateFormat.parse("03/12/2010 16:00")));
			
			/**cours d'anglais */
			/*pour tous les 1A anglais1*/
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(2), cacheGroupes.get(4), dateFormat
					.parse("02/12/2010 14:00"), dateFormat.parse("02/12/2010 16:00")));
			/*pour tous les 1A anglais2*/
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(2), cacheGroupes.get(5), dateFormat
					.parse("02/12/2010 14:00"), dateFormat.parse("02/12/2010 16:00")));
			/*pour tous les 1A anglais1 IES*/
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(2), cacheGroupes.get(6), dateFormat
					.parse("07/12/2010 15:00"), dateFormat.parse("07/12/2010 16:15")));
			/*pour tous les 1A anglais1 Maths*/
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(2), cacheGroupes.get(7), dateFormat
					.parse("07/12/2010 15:00"), dateFormat.parse("07/12/2010 16:15")));
			/*pour tous les 1A anglais2 Maths*/
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(2), cacheGroupes.get(8), dateFormat
					.parse("08/12/2010 10:00"), dateFormat.parse("08/12/2010 11:30")));
			/*pour tous les 1A anglais2 IES*/
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(2), cacheGroupes.get(9), dateFormat
					.parse("09/12/2010 10:00"), dateFormat.parse("09/12/2010 11:30")));
			/*pour tous les 2A anglais 1*/
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(2), cacheGroupes.get(10), dateFormat
					.parse("02/12/2010 14:00"), dateFormat.parse("02/12/2010 16:00")));
			/*pour tous les 2A anglais 2*/
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(2), cacheGroupes.get(11), dateFormat
					.parse("02/12/2010 14:00"), dateFormat.parse("02/12/2010 16:00")));
			
			
			/**cours pour les 1A maths*/
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(1), cacheGroupes.get(2), dateFormat
					.parse("07/12/2010 8:00"), dateFormat.parse("07/12/2010 12:00")));
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(1), cacheGroupes.get(2), dateFormat
					.parse("08/12/2010 8:00"), dateFormat.parse("08/12/2010 12:00")));
			
			/**cours pour les 1A ies*/
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(2), cacheGroupes.get(3), dateFormat
					.parse("09/12/2010 10:00"), dateFormat.parse("10/12/2010 14:00")));
			cacheCoursPlanifies.add(new CoursPlanifie(cacheMatieres.get(2), cacheGroupes.get(3), dateFormat
					.parse("10/12/2010 10:00"), dateFormat.parse("10/12/2010 14:00")));
		
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}

	/**
	 * <p>
	 * Retourne la liste des langues gérées.
	 * </p>
	 * 
	 * @return Retourne la liste des langues gérées.
	 */
	public List<String> getLangues() {
		return cacheLangues;
	}

	/**
	 * <p>
	 * Retourne l'ensemble des matières gérées sur le système.
	 * </p>
	 * 
	 * @return
	 */
	public List<Matiere> getMatieres() {
		return cacheMatieres;
	}

	/**
	 * <p>
	 * Retourne la lsite des professeurs.
	 * </p>
	 * 
	 * @return Retourne la lsite des professeurs.
	 */
	public List<Professeur> getProfesseurs() {
		return cacheProfesseurs;
	}

	public Professeur findProfesseurById(int id) {
		Professeur prof = null;
		for (Professeur professeur : getProfesseurs()) {
			if (professeur.getId() == id) {
				prof = professeur;
				break;
			}
		}

		return prof;
	}
	
	/**
	 * @return retourne la liste des administrateurs
	 */
	public List<Administrateur> getAdministrateurs(){
		return cacheAdministrateurs;
	}

	/**
	 * <p>
	 * Retourne la valeur de cacheGroupes.
	 * </p>
	 * 
	 * @return Retourne la valeur de cacheGroupes.
	 */
	public List<Groupe> getGroupes() {
		return cacheGroupes;
	}
	
	
	/**
	 * <p>
	 * Retourne la valeur de cacheGroupes.
	 * </p>
	 * 
	 * @return Retourne la valeur de cacheGroupes.
	 */
	public List<Groupe> getGroupesGlobaux() {
		List<Groupe> groupes = new ArrayList<Groupe>();
		for (Groupe g : cacheGroupes){
			try{
				groupes.add((GroupeGlobal) g);
			}catch (ClassCastException e){
				//TODO
			}
		}
		return groupes;
	}
	
	

	/**
	 * <p>
	 * Retourne la valeur de cacheCoursPlanifies.
	 * </p>
	 * 
	 * @return Retourne la valeur de cacheCoursPlanifies.
	 */
	public List<CoursPlanifie> getCoursPlanifies() {
		return cacheCoursPlanifies;
	}
	
	
	

	/**
	 * @return retourne la liste des etudiants
	 */
	public List<Etudiant> getEtudiants(){
		return cacheEtudiants;
	}

	
	
	 
	/**
	 * <p>
	 * Retourne la liste des cours planifiés pour le groupe donné.
	 * </p>
	 * 
	 * @param groupe
	 * @return Retourne la liste des cours planifiés pour le groupe donné.
	 */
	public List<CoursPlanifie> getCoursPlanifies(Groupe groupe) {
		List<CoursPlanifie> res = new ArrayList<CoursPlanifie>();

		if (groupe == null) {
			return res;
		}

		for (CoursPlanifie coursPlanifie : cacheCoursPlanifies) {
			if (groupe.equals(coursPlanifie.getGroupe())) {
				res.add(coursPlanifie);
			}
		}

		return res;
	}
	

	/**
	 * <p>
	 * Retourne la liste des cours planifiés pour la matière donnée.
	 * </p>
	 * 
	 * @param groupe
	 * @return Retourne la liste des cours planifiés pour la matière donnée.
	 * @deprecated : cf GestionCoursPlanifie 
	 */
	public List<CoursPlanifie> getCoursPlanifies(Matiere matiere) {//TODO
		List<CoursPlanifie> res = new ArrayList<CoursPlanifie>();

		if (matiere == null) {
			return res;
		}

		for (CoursPlanifie coursPlanifie : cacheCoursPlanifies) {
			if (matiere.equals(coursPlanifie.getMatiere())) {
				res.add(coursPlanifie);
			}
		}

		return res;
	}
	
	
	/**
	 * @param intituleMatiere
	 * @return retourne la matiere correspondant a l intitule mis en parametre
	 */
	public Matiere recupMatiere(String intituleMatiere){
		for (Matiere m : getMatieres()){
			if(m.getIntitule().equals(intituleMatiere)){
				return m;
			}
		}
		return null;
	}
	
	/**
	 * @param prenomNom
	 * @return retourne le prof qui correspond au couple (prenom nom) mis en parametre
	 */
	public Professeur recupProfesseur(String prenomNom){
		for (Professeur p : getProfesseurs()){
			if((p.getPrenom() + " " + p.getNom()).equals(prenomNom)){
				return p;
			}
		}
		return null;
	}
	
	
	/**
	 * @param login
	 * @return retourne la personne qui correspond au login mis en parametre
	 * (ca peut etre un etudiant, un professeur ou un administrateur
	 */
	public Personne recupPersonne(String login){
		for (Etudiant e : getEtudiants()){
			if((e.getLogin()).equals(login)){
				return e;
			}
		}
		for (Professeur p : getProfesseurs()){
			if((p.getLogin()).equals(login)){
				return p;
			}
		}
		for (Administrateur a : getAdministrateurs()){
			if((a.getLogin()).equals(login)){
				return a;
			}
		}
		return null;
	}
	
	/**
	 * @param nom
	 * @return retourne le groupe qui correspond au nom mis en parametre
	 */
	public Groupe recupGroupe(String nom){
		for (Groupe g : getGroupes()){
			if((g.getNom()).equals(nom)){
				return g;
			}
		}
		return null;
	}
	
	
	/**
	 * methode qui permet d'ajouter un nouveau cours planifié dans le cache coursPlanifies
	 * @param cp
	 */
	public void ajoutCoursPlanifie(CoursPlanifie cp){
		cacheCoursPlanifies.add(cp);
	}
	
	
	
}
