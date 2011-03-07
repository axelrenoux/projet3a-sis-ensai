package vues;



import java.util.ArrayList;
import java.util.List;


 
import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
 

import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.TreeNode;

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
public class VueAgendaGroupe {
	
	/**************************   attributs  **************************/
	
	/**
	 * vueAgendaGroupe possede 3 managed property : 
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
	private Groupe groupeChoisi;
	private ScheduleEvent evenementCourant = new DefaultScheduleEvent(); 
	private ScheduleModel emploiDuTemps;
	private TreeNode arbre;  
	private TreeNode selectedNode;  
	private boolean affichageComplet;
	private List<Groupe> groupesEnglobes = new ArrayList<Groupe>();
	
	
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
    	   if (cp.getGroupe().equals(groupeChoisi)){ 
    		   //le cours est planifie pour le groupe choisi
    	   DefaultScheduleEvent event = new DefaultScheduleEvent(cp.getMatiere().getIntitule(), cp.getDateDebut(), cp.getDateFin(), cp);
           event.setAllDay(false);
           emploiDuTemps.addEvent(event);
    	   }
       }
    }
    
    
    /**
     * version dans laquelle on inclut la totalité des cours des 
     * sous groupes englobés par le groupe choisi
     */
    private void updateEmploiDuTempsVersionGlobale(){	
        if(emploiDuTemps!=null) emploiDuTemps.clear();
        else emploiDuTemps = new DefaultScheduleModel();
        
        for(CoursPlanifie cp : listeCoursPlanifie){
     	   if (groupesEnglobes.contains(cp.getGroupe())//le cours est planifie pour l'un des groupes englobes par le groupe choisi
     	     	   | cp.getGroupe().equals(groupeChoisi)){ //ou pour le groupe choisi directement
     		DefaultScheduleEvent event = new DefaultScheduleEvent(cp.getMatiere().getIntitule(), cp.getDateDebut(), cp.getDateFin(), cp);
            event.setAllDay(false);
            emploiDuTemps.addEvent(event);
     	   }
        }
     }
     
    
    
	/**
	 * methode appelee pour mettre a jour l'emploi du temps
	 * @return
	 */
	public String validerRechercheGroupe(){
		if (!affichageComplet) updateEmploiDuTemps();	
		else updateEmploiDuTempsVersionGlobale();
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
		if (!affichageComplet) updateEmploiDuTemps();	
		else updateEmploiDuTempsVersionGlobale();
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
		        
	
	 
	
	
	
	/**
	 * methode qui crée un arbre de groupes et sous groupes
	 * 
	 * @param node0
	 * @param groupe
	 */
	public void creerNode(TreeNode node0, Groupe groupe){
		for (Groupe g1 : groupe.getListeGroupes()){
        	TreeNode node1=new DefaultTreeNode(g1, node0);
        	if(g1.getListeGroupes()!=null){
        		creerNode(node1,g1);
        	}
		}
	}
	
	
	/**
	 * methode qui sexecute quand on selectionne un element de l'arbre (un groupe)
	 * @param event
	 */
	public void onNodeSelect(NodeSelectEvent event) {  
		groupeChoisi = (Groupe)selectedNode.getData();
		groupesEnglobes.clear();
		//on recupere les groupes englobes par le groupe choisi
		for (TreeNode t : selectedNode.getChildren()){
			groupesEnglobes.add((Groupe) t.getData());
		}
		validerRechercheGroupe();
	}  
	
	 
	
	
	/************************** getters/setters **************************/
	
	
	/**
	 * @return retourne l'arbre des groupes
	 */
	public TreeNode getArbre() {  
		TreeNode root;
		
		root = new DefaultTreeNode("Choisir groupe", null); 
		for (Groupe g0 : gestionGroupes.rechercherTousGroupesGlobauxAvecCours()){
			TreeNode node0 = new DefaultTreeNode(g0, root);
			if(g0.getListeGroupes()!=null)creerNode(node0,g0);	
		}
		return root;
    } 
	

	/**
	 * @param arbre
	 */
	public void setArbre(TreeNode arbre) {
		this.arbre = arbre;
	}
	
	
	
	/**
	 * @return retourne l'element de l'arbre selectionné
	 */
	public TreeNode getSelectedNode() {
		return selectedNode;
	}




	/**
	 * @param selectedNode
	 */
	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}



	 

	/**
	 * @return retourne le groupe choisi dans la vue
	 */
	public Groupe getGroupeChoisi() {
		return groupeChoisi;
	}

	/**
	 * nouveau groupe choisi
	 * @param groupeChoisi
	 */
	public void setGroupeChoisi(Groupe groupeChoisi) {
		this.groupeChoisi = groupeChoisi;
		updateEmploiDuTemps();
	}

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
	 * @return retourne gestionGroupe
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
	 * @return true si l'utilisateur a coché la checkbox pour que tous les cours
	 * de tous les sous groupes englobés par le groupe selectionné soient affichés
	 */
	public boolean isAffichageComplet() {
		return affichageComplet;
	}




	/**
	 * modifie la valeur de affichageComplet quand l'utilisateur coche la checkbox
	 * @param affichageComplet
	 */
	public void setAffichageComplet(boolean affichageComplet) {
		this.affichageComplet = affichageComplet;
	}
	
	
}


 