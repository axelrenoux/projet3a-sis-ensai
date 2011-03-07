package vues;



import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import formation.jsf.gestionscolaire.metier.entite.CoursPlanifie;
import formation.jsf.gestionscolaire.metier.entite.Groupe;
import formation.jsf.gestionscolaire.metier.entite.Matiere;
import formation.jsf.gestionscolaire.metier.service.GestionCoursPlanifiesService;
import formation.jsf.gestionscolaire.metier.service.GestionGroupesService;
import formation.jsf.gestionscolaire.metier.service.GestionMatieresService;



/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 */
@ManagedBean
@SessionScoped
public class VueMonAgenda {
	
	/**************************   attributs  **************************/
	/**
	 * vueMonAgenda possede 4 managed property : 
	 * */
	@ManagedProperty(value="#{gestionCoursPlanifiesService}")
    private GestionCoursPlanifiesService gestionCoursPlanifies;
	@ManagedProperty(value="#{gestionGroupesService}")
    private GestionGroupesService gestionGroupes;
	@ManagedProperty(value="#{gestionMatieresService}")
    private GestionMatieresService gestionMatieres;
	@ManagedProperty(value="#{vueAccueil}")
	private VueAccueil vue;
	private List<Matiere> listeMatieres = null;
	private List<Groupe> listeGroupes = null;
	private List<CoursPlanifie> listeCoursPlanifie = null;
	private ScheduleEvent evenementCourant = new DefaultScheduleEvent(); 
	private ScheduleModel emploiDuTemps;
	
	
	/************************** methodes    **************************/

	/**
	 * methode executee a l'instanciation de la vue
	 */
	@PostConstruct
	public void init(){
		//on cree les evenements a partir de tous les cours planifies
		listeCoursPlanifie = gestionCoursPlanifies.rechercherTous();
		updateEmploiDuTemps();
	}


  
	
    /**
     * methode qui met a jour l'emploi du temps, 
     * le re-cree s'il existe déjà, le cree sinon
     * on cree des defaultscheduleEvent a partir des cours planifies de la bdd
     * 
     * si c'est un étudiant, on recupere les cours planifies de ses groupes
     * si c'est un professeur, on recupere les cours planifies de ses matieres
     */
    private void updateEmploiDuTemps(){	
       if(emploiDuTemps!=null) emploiDuTemps.clear();
       else emploiDuTemps = new DefaultScheduleModel();
       
       for(CoursPlanifie cp : listeCoursPlanifie){
    	   if (vue.getGroupesPersonne().contains(cp.getGroupe())){ //le cours est planifie pour un des groupes de la personne connectee
    		   DefaultScheduleEvent event = new DefaultScheduleEvent(cp.getMatiere().getIntitule(), cp.getDateDebut(), cp.getDateFin(), cp);
    		   event.setAllDay(false);
    		   emploiDuTemps.addEvent(event);
    	   }
    	   if (vue.getMatieresProfesseur().contains(cp.getMatiere())){ //le cours est planifie pour une des matieres du professeur connecté
    		   DefaultScheduleEvent event = new DefaultScheduleEvent(cp.getMatiere().getIntitule(), cp.getDateDebut(), cp.getDateFin(), cp);
    		   event.setAllDay(false);
    		   emploiDuTemps.addEvent(event);
    	   }
       }
    }
    
    
    /**
	 * methode qui s'execute quand on selectionne un evenement
	 * @param selectEvent
	 */
	public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {  
		evenementCourant = selectEvent.getScheduleEvent();
 	}  
	/************************** getters/setters **************************/

    /**
	 * @return retourne l'emploi du temps
	 */
	public ScheduleModel getEmploiDuTemps() {
		return emploiDuTemps;
	}

	/**
	 * @param emploiDuTemps
	 */
	public void setEmploiDuTemps(ScheduleModel emploiDuTemps) {//TODO (vraiment : comment prendre en compte ds la Bdd les changements?)
		this.emploiDuTemps = emploiDuTemps;
	}

	
	/**
	 * @return retourne l'événement courant
	 */
	public ScheduleEvent getEvenementCourant() {
		return evenementCourant;
	}

	/**
	 * nouvel evenement courant
	 * @param evenementCourant
	 */
	public void setEvenementCourant(ScheduleEvent evenementCourant) {
		this.evenementCourant = evenementCourant;
	}

	
	/**
	 * @return retourne la liste des cours planifies
	 */
	public List<CoursPlanifie> getListeCoursPlanifie() {
		return listeCoursPlanifie;
	}

	/**
	 * @param listeCoursPlanifie
	 */
	public void setListeCoursPlanifie(List<CoursPlanifie> listeCoursPlanifie) {
		this.listeCoursPlanifie = listeCoursPlanifie;
	}
	
	
	/**
	 * @return retourne la liste des matieres
	 */
	public List<Matiere> getListeMatieres(){
		if(listeMatieres ==null){
			listeMatieres = gestionMatieres.rechercherTous();
		}
		return listeMatieres;
	}
	
	/**
	 * @param listeMatieres
	 */
	public void setListeMatieres(List<Matiere> listeMatieres) {
		this.listeMatieres = listeMatieres;
	}

	/**
	 * @return listeGroupes
	 */
	public List<Groupe> getListeGroupes() {
		if(listeGroupes ==null){
			listeGroupes = gestionGroupes.rechercherTous();
		}
		return listeGroupes;
	}


	/**
	 * @param listeGroupes
	 */
	public void setListeGroupes(List<Groupe> listeGroupes) {
		this.listeGroupes = listeGroupes;
	}
	
	/**
	 * @return retourne gestionCoursPlanifies
	 */
	public GestionCoursPlanifiesService getGestionCoursPlanifies() {
		return gestionCoursPlanifies;
	}

	/**
	 * @param gestionCoursPlanifies
	 */
	public void setGestionCoursPlanifies(GestionCoursPlanifiesService gestionCoursPlanifies) {
		this.gestionCoursPlanifies = gestionCoursPlanifies;
	}

	/**
	 * @return retourne gestionMatieres
	 */
	public GestionMatieresService getGestionMatieres() {
		return gestionMatieres;
	}

	/**
	 * @param gestionMatieres
	 */
	public void setGestionMatieres(GestionMatieresService gestionMatieres) {
		this.gestionMatieres = gestionMatieres;
	}
	

	/**
	 * @return retourne gestionGroupes
	 */
	public GestionGroupesService getGestionGroupes() {
		return gestionGroupes;
	}

	/**
	 * @param gestionGroupes
	 */
	public void setGestionGroupes(GestionGroupesService gestionGroupes) {
		this.gestionGroupes = gestionGroupes;
	}

	
	

	/**
	 * @return vue
	 */
	public VueAccueil getVue() {
		return vue;
	}


	/**
	 * @param vue
	 */
	public void setVue(VueAccueil vue) {
		this.vue = vue;
	}





	
	
	
}


 