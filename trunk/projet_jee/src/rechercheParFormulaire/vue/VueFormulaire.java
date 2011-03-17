package rechercheParFormulaire.vue;
	import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import rechercheParFormulaire.gestionRecherche.GestionnaireFormulaire;


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
		

		@ManagedProperty(value="#{gestionnaireFormulaire}")
		private GestionnaireFormulaire gestionnaireFormulaire;
		@ManagedProperty(value="#{vueAffichageResultat}")
		private VueAffichageResultat vueAffichageResultat;
		
		private String motCle;
		
		/********************************************************************/
		/************************      methodes      ************************/
		/********************************************************************/


		/**
		 * 
		 */
		public String lancerRecherche(){
			
			boolean success = false;			

			if(!motCle.isEmpty()){
				/*gestionFormulaire.getRechercheAlbum().setMotCle(motcle);
				gestionFormulaire.getRechercheArtiste().setMotCle(motcle);
				gestionFormulaire.getRechercheChanson().setMotCle(motcle);*/
				success = true;
			}

			if(success){
				//on attribue les resultats à la page de resultats
				vueAffichageResultat.getGestionnaireAffichageResultat().setClustersAlbum(gestionnaireFormulaire.lancerRechercheAlbum(motCle));;
				vueAffichageResultat.getGestionnaireAffichageResultat().setClustersArtiste(gestionnaireFormulaire.lancerRechercheArtiste(motCle));;
				vueAffichageResultat.getGestionnaireAffichageResultat().setClustersChanson(gestionnaireFormulaire.lancerRechercheChanson(motCle));;
				
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
			FacesContext.getCurrentInstance().
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erreur","Veuillez saisir un mot clé")); 
		}


		
		
		/********************************************************************/
		/******************      getters / setters       ********************/
		/********************************************************************/



		 

	 


		public GestionnaireFormulaire getGestionnaireFormulaire() {
			return gestionnaireFormulaire;
		}


		public void setGestionnaireFormulaire(
				GestionnaireFormulaire gestionnaireFormulaire) {
			this.gestionnaireFormulaire = gestionnaireFormulaire;
		}




		public String getMotCle() {
			return motCle;
		}

		public void setMotCle(String motCle) {
			this.motCle = motCle;
		}


		public VueAffichageResultat getVueAffichageResultat() {
			return vueAffichageResultat;
		}


		public void setVueAffichageResultat(VueAffichageResultat vueAffichageResultat) {
			this.vueAffichageResultat = vueAffichageResultat;
		}
		
		
		
}
