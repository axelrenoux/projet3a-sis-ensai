package vues;



import java.util.List;

 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

 

 


 
 
import formation.jsf.gestionscolaire.metier.entite.Matiere;
import formation.jsf.gestionscolaire.metier.entite.Professeur;
 
import formation.jsf.gestionscolaire.metier.service.GestionMatieresService;

/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 */
@ManagedBean
@SessionScoped
public class VueGererMatieres {
	@ManagedProperty(value="#{gestionMatieresService}")
	private GestionMatieresService gestion;
	
	private boolean afficherMatiere;
	
	private List<Matiere> listeMatieres = null;
	private List<String> listeLangues = null;
	private List<Professeur> listeProfesseurs = null;

	private Matiere matiereChoisie ;	
	
	

	public String validerRechercheMatiere(){
		setAfficherMatiere(true);
		addWarn();
		return "matiereSelectionnee";
	}
 
	
	public void addWarn() {  
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Attention : non administrateur", "Vos modifications ne seront pas prises en compte")); 
	}
	
	
	public List<Matiere> getListeMatieres(){
		if(listeMatieres ==null){
			listeMatieres = gestion.rechercherTous();
		}
		return listeMatieres;
	}
			
	public List<String> getListeLangues(){
		if(listeLangues ==null){
			listeLangues = gestion.rechercherLangues();
		}
	
		return listeLangues;
	}	
	
	public List<Professeur> getListeProfesseurs(){
		if(listeProfesseurs ==null){
			listeProfesseurs = gestion.rechercherProfesseurs();
		}
	
		return listeProfesseurs;
	}	
	 


	public Matiere getMatiereChoisie() {
		return matiereChoisie;
	}



	public void setMatiereChoisie(Matiere matiereChoisie) {
		this.matiereChoisie = matiereChoisie;
	}


	
	public boolean isAfficherMatiere() {
		return afficherMatiere;
	}



	public void setAfficherMatiere(boolean afficherMatiere) {
		this.afficherMatiere = afficherMatiere;
	}


	public GestionMatieresService getGestion() {
		return gestion;
	}


	public void setGestion(GestionMatieresService gestion) {
		this.gestion = gestion;
	}











	
	
}