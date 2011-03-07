package vues;



 
import java.util.List;


 
import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
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
public class VueAgendaMatiere {
	
	/**************************   attributs  **************************/
	/**
	 * vueAgendaMatiere possede 3 managed property : 
	 * */
	@ManagedProperty(value="#{gestionCoursPlanifiesService}")
    private GestionCoursPlanifiesService gestionCoursPlanifies;
	@ManagedProperty(value="#{gestionGroupesService}")
    private GestionGroupesService gestionGroupes;
	@ManagedProperty(value="#{gestionMatieresService}")
    private GestionMatieresService gestionMatieres;
	private List<Groupe> listeGroupes = null;
	private List<Matiere> listeMatieres = null;
	private List<CoursPlanifie> listeCoursPlanifie = null;
	private Matiere matiereChoisie;
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
	}


  
	
    /**
     * methode qui met a jour l'emploi du temps, 
     * le re-cree s'il existe déjà, le cree sinon
     * on cree des defaultscheduleEvent a partir des cours planifies de la bdd
     */
    private void updateEmploiDuTemps(){	
       if(emploiDuTemps!=null) emploiDuTemps.clear();
       else emploiDuTemps = new DefaultScheduleModel();
       
       for(CoursPlanifie cp : listeCoursPlanifie){
    	   if (cp.getMatiere().equals(matiereChoisie)){ //le cours est planifie pour la matiere choisie
    	   DefaultScheduleEvent event = new DefaultScheduleEvent(cp.getMatiere().getIntitule(), cp.getDateDebut(), cp.getDateFin(), cp);
           event.setAllDay(false);
           emploiDuTemps.addEvent(event);
    	   }
       }
    }
    
    
    
     
    
    
	/**
	 * methode appelee quand l'utilisateur choisit une matiere
	 * @return
	 */
	public String validerRechercheMatiere(){
		updateEmploiDuTemps();	
		addWarn();
		return null;
	}

	/**
	 * methode qui affiche un message de warning si la personne n'est pas un administrateur
	 */
	public void addWarn() {  
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Attention : non administrateur", "Vos modifications ne seront pas prises en compte")); 
	}
 
		
		
	 
	        
	
	
	/**
	 * methode qui verifie si un evenement existe deja dans l'emploi du temps
	 * @param emploiDuTemps
	 * @param evenement
	 * @return true si est présent, false sinon
	 */
	/*public boolean isEventInEmploiDuTemps(ScheduleModel emploiDuTemps,ScheduleEvent evenement){
          boolean estPresent=false;
          for(ScheduleEvent evCourant:emploiDuTemps.getEvents()){
                  if(evCourant.equals(evenement)){
                          estPresent=true;
                          break;
                  }
          }
          return estPresent;
	 }*/
	
	
	/**
	 * methode qui verifie si un evenement existe deja dans l'emploi du temps
	 * @param emploiDuTemps
	 * @param evenement
	 * @return true si est présent, false sinon
	 */
	public boolean isEventInEmploiDuTemps(ScheduleModel emploiDuTemps,ScheduleEvent evenement){
          boolean estPresent=false;
          for(ScheduleEvent evCourant:emploiDuTemps.getEvents()){
                  if(evCourant.equals(evenement)){
                          estPresent=true;
                          break;
                  }
          }
          return estPresent;
	 }
	
	
	

	
	/**
	 * methode qui cree ou met a jour un cours planifie
	 * a partir de l'evenement courant en cours
	 */
	public void addEvent() {  
		//on recupere le cours planifie cree ou mis a jour
		CoursPlanifie cp = (CoursPlanifie) evenementCourant.getData();
		
		((DefaultScheduleEvent) evenementCourant).setStartDate(cp.getDateDebut());
		((DefaultScheduleEvent) evenementCourant).setEndDate(cp.getDateFin());
		
		if(!isEventInEmploiDuTemps(emploiDuTemps,evenementCourant)){	
			emploiDuTemps.addEvent(evenementCourant); 
			gestionCoursPlanifies.ajoutCoursPlanifie(cp);
		}
    	updateEmploiDuTemps();
        evenementCourant = new DefaultScheduleEvent();
        
	}  
	
	
	
	/**
	 * methode qui s'execute quand on selectionne un evenement
	 * @param selectEvent
	 */
	public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {  
		evenementCourant = selectEvent.getScheduleEvent();
 	}  
		    
	/**
	 * methode qui s'execute quand on selectionne une date de l'agenda
	 * @param selectEvent
	 */
	public void onDateSelect(DateSelectEvent selectEvent) {
		evenementCourant = new DefaultScheduleEvent(null,selectEvent.getDate(), selectEvent.getDate(), new CoursPlanifie());  
		((CoursPlanifie)evenementCourant.getData()).setDateDebut(selectEvent.getDate());
		((CoursPlanifie)evenementCourant.getData()).setDateFin(selectEvent.getDate());
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
	 * @param evenementCourant
	 */
	public void setEvenementCourant(ScheduleEvent evenementCourant) {
		this.evenementCourant = evenementCourant;
	}


	/**
	 * @return retourne la matiere choisie par l'utilisateur
	 */
	public Matiere getMatiereChoisie() {
		return matiereChoisie;
	}


	/**
	 * nouvelle matiere
	 * @param matiereChoisie
	 */
	public void setMatiereChoisie(Matiere matiereChoisie) {
		this.matiereChoisie = matiereChoisie;
	}
	
	
	/**
	 * @return listeGroupes
	 */
	public List<Groupe> getListeGroupes(){
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
	 * @return retourne gestionCoursPlanifies
	 */
	public GestionCoursPlanifiesService getGestionCoursPlanifies() {
		return gestionCoursPlanifies;
	}
	
	
	/**
	 * @param gestionCoursPlanifies
	 */
	public void setGestionCoursPlanifies(
			GestionCoursPlanifiesService gestionCoursPlanifies) {
		this.gestionCoursPlanifies = gestionCoursPlanifies;
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


}


 