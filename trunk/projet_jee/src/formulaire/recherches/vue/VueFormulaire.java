package formulaire.recherches.vue;
	import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import formulaire.reponses.gestion.GestionFormulaire;

	/**
	 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
	 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
	 * @author axel
	 */
	@ManagedBean
	@SessionScoped
	public class VueFormulaire {
		
		/********************************************************************/
		/*************************      attributs       *********************/
		/********************************************************************/
		
		@ManagedProperty(value="#{vueConfirmation}")
		private VueConfirmation vueConfirmation;
		@ManagedProperty(value="#{gestionFormulaire}")
		private GestionFormulaire gestionFormulaire;
		@ManagedProperty(value="#{vueAffichageResultat}")
		private VueAffichageResultat vueAffichageResultat;
		
		private String motcle;
		
		/********************************************************************/
		/************************      methodes      ************************/
		/********************************************************************/


		/**
		 * 
		 */
		public String lancerRecherche(){
			
			boolean success = false;			

			if(!motcle.isEmpty()){
				/*gestionFormulaire.getRechercheAlbum().setMotCle(motcle);
				gestionFormulaire.getRechercheArtiste().setMotCle(motcle);
				gestionFormulaire.getRechercheChanson().setMotCle(motcle);*/
				success = true;
			}

			if(success){
				//on attribue les resultats à la page de resultats
				vueAffichageResultat.setResultatsAlbum(gestionFormulaire.lancerRechercheAlbum());;
				vueAffichageResultat.setResultatsArtiste(gestionFormulaire.lancerRechercheArtiste());;
				vueAffichageResultat.setResultatsChanson(gestionFormulaire.lancerRechercheChanson());;
				
				//return "confirmation";
				return "success";
			}
			else{
				addError();
				return "failure";
			}
		}
		
		
		/**
		 * methode qui ajoute un message d'erreur
		 */
		public void addError() {  
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Erreur de requetage : requete vide")); 
		}


		
		
		/********************************************************************/
		/******************      getters / setters       ********************/
		/********************************************************************/



		public VueConfirmation getVueConfirmation() {
			return vueConfirmation;
		}


		public void setVueConfirmation(VueConfirmation vueConfirmation) {
			this.vueConfirmation = vueConfirmation;
		}


		public GestionFormulaire getGestionFormulaire() {
			return gestionFormulaire;
		}


		public void setGestionFormulaire(GestionFormulaire gestionFormulaire) {
			this.gestionFormulaire = gestionFormulaire;
		}




		public String getMotcle() {
			return motcle;
		}


		public void setMotcle(String motcle) {
			this.motcle = motcle;
		}


		public VueAffichageResultat getVueAffichageResultat() {
			return vueAffichageResultat;
		}


		public void setVueAffichageResultat(VueAffichageResultat vueAffichageResultat) {
			this.vueAffichageResultat = vueAffichageResultat;
		}
		
		
		
}
