package vues;

import java.util.ArrayList;

 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
import formation.jsf.gestionscolaire.metier.entite.Groupe;
import formation.jsf.gestionscolaire.metier.entite.Matiere;
import formation.jsf.gestionscolaire.metier.entite.Personne;
import formation.jsf.gestionscolaire.metier.service.GestionPersonnesService;



/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 */
@ManagedBean
@SessionScoped
public class VueAccueil {

	/**************************   attributs  **************************/
	/**
	 * VueAccueil possede un managedbean gestionPersonneService, 
	 * pour y avoir accès tout au long de la session
	 */
	@ManagedProperty(value="#{gestionPersonnesService}")
	private GestionPersonnesService gestion;
	private Personne personneConnectee;
	
	
	/************************** methodes    **************************/
	
	
	/**
	 * les methodes suivantes permettent la navigation vers les différentes vues 
	 * depuis la vue accueil, elle renvoient toutes un outcome "success" 
	 * @return 
	 */
	public String allerSurGererMatiere(){
		return "success";
	}
	public String allerSurMonAgenda(){
		return "success";
	}
	public String allerSurAgendaGroupe(){
		return "success";
	}
	public String allerSurAgendaMatiere(){
		return "success";
	}
	public String allerSurTrombiGroupe(){
		return "success";
	}

	/**
	 * @return
	 */
	public String seDeconnecter(){
		 //Pour fermer la session
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.invalidate();
        //Pour dire à faces-config de nous renvoyer à vueSeLogger
        return "seDeconnecter";
	}




	public GestionPersonnesService getGestion() {
		return gestion;
	}




	public void setGestion(GestionPersonnesService gestion) {
		this.gestion = gestion;
	}

	public Personne getPersonneConnectee() {
		return personneConnectee;
	}

	public void setPersonneConnectee(Personne personneConnectee) {
		this.personneConnectee = personneConnectee;
	}

	public ArrayList<Groupe> getGroupesPersonne() {
		return gestion.groupesPersonne(personneConnectee);
	}

	public ArrayList<Matiere> getMatieresProfesseur(){
		return gestion.matieresProfesseur(personneConnectee);
	}

	
}
