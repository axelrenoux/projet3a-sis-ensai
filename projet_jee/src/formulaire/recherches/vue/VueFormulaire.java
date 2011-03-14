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
		
		private String artist;
		private String album;
		private String track;
		private String tag;
		private String motcle;
		private int ceQueRecherche;
		
		/********************************************************************/
		/************************      methodes      ************************/
		/********************************************************************/


		/**
		 * 
		 */
		public String lancerRecherche(){
			
			boolean success = false;
			gestionFormulaire.setObjectif(ceQueRecherche);
			
			if(!track.isEmpty()){
				vueConfirmation.setCompteRendu(vueConfirmation.getCompteRendu()+"Chanson : "+track+"\n ");
				gestionFormulaire.getRecherche().setTrack(track);
				success = true;
			}
			if(!artist.isEmpty()){
				vueConfirmation.setCompteRendu(vueConfirmation.getCompteRendu()+"Groupe : "+artist+"\n ");
				gestionFormulaire.getRecherche().setArtist(artist);
				success = true;
			}
			if(!album.isEmpty()){
				vueConfirmation.setCompteRendu(vueConfirmation.getCompteRendu()+"Album : "+album+"\n ");
				gestionFormulaire.getRecherche().setAlbum(album);
				success = true;
			}
			if(!motcle.isEmpty()){
				vueConfirmation.setCompteRendu(vueConfirmation.getCompteRendu()+"Album : "+album+"\n ");
				gestionFormulaire.getRecherche().setMotCle(motcle);
				success = true;
			}
			if(!tag.isEmpty()){
				vueConfirmation.setCompteRendu(vueConfirmation.getCompteRendu()+"Album : "+album+"\n ");
				gestionFormulaire.getRecherche().setTag(tag);
			}
			
			
			
			if(success){
				//on attribue les resultats à la page de resultats
				vueAffichageResultat.setResultats(gestionFormulaire.lancerRecherche());
				vueAffichageResultat.setType(gestionFormulaire.retournerTypeAffichage());
				System.out.println(vueAffichageResultat.getType());
				//return "confirmation";
				return vueAffichageResultat.getType();
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

		public String getArtist() {
			return artist;
		}


		public void setArtist(String artist) {
			this.artist = artist;
		}



		public String getAlbum() {
			return album;
		}


		public void setAlbum(String album) {
			this.album = album;
		}



		public String getTrack() {
			return track;
		}


		public void setTrack(String track) {
			this.track = track;
		}


		public int getCeQueRecherche() {
			return ceQueRecherche;
		}


		public void setCeQueRecherche(int ceQueRecherche) {
			this.ceQueRecherche = ceQueRecherche;
		}


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


		public String getTag() {
			return tag;
		}


		public void setTag(String tag) {
			this.tag = tag;
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
