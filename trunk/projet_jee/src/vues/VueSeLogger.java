package vues;

import java.awt.event.ActionEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import formation.jsf.gestionscolaire.metier.service.GestionPersonnesService;

/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 */
@ManagedBean
@SessionScoped
public class VueSeLogger {
	
	/**************************   attributs  **************************/

	/**
	 * vueSeLogger possede 2 managed property : 
	 * */
	@ManagedProperty(value="#{gestionPersonnesService}")
	private GestionPersonnesService gestion;
	@ManagedProperty(value="#{vueAccueil}")
	private VueAccueil vueAccueil;
	private String login;
	private String mdp;
	
	
	/************************** methodes    **************************/



	/**
	 * methode appelee par le bouton valider de la vue
	 * appelle addError en cas de mauvais authentification
	 * si l'authentification est bonne, 
	 * 			l'utilisateur est envoyé à la page d'accueil
	 * @return
	 */
	public String accederAccueil(){
		if(gestion.verifierMDP(login, mdp)) {
			vueAccueil.setPersonneConnectee(gestion.validerConnection(login));
			return "success";
		}
		else{
			setLogin(null);
			addError();
			return "failure";
		}
	}
	
	
	/**
	 * methode qui ajoute un message d'erreur
	 */
	public void addError() {  
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur", "Erreur de login/mot de passe")); 
	}  
	

	
	
	/************************** getters/setters **************************/
	




	/**
	 * @return login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return mdp
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * @param mdp
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


	/**
	 * @return gestion
	 */
	public GestionPersonnesService getGestion() {
		return gestion;
	}

	/**
	 * @param gestion
	 */
	public void setGestion(GestionPersonnesService gestion) {
		this.gestion = gestion;
	}

	/**
	 * @return vueAccueil
	 */
	public VueAccueil getVueAccueil() {
		return vueAccueil;
	}




	/**
	 * @param vueAccueil
	 */
	public void setVueAccueil(VueAccueil vueAccueil) {
		this.vueAccueil = vueAccueil;
	}
	
	
}
