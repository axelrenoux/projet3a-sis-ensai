package formulaire.recherches.vue;
	import java.awt.event.ActionEvent;

	import javax.faces.application.FacesMessage;
	import javax.faces.bean.ManagedBean;
	import javax.faces.bean.ManagedProperty;
	import javax.faces.bean.SessionScoped;
	import javax.faces.context.FacesContext;

	/**
	 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
	 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
	 * @author axel
	 */
	@ManagedBean
	@SessionScoped
	public class VueFormulaire {
		
		/**************************   attributs  **************************/

		@ManagedProperty(value="#{vueConfirmation}")
		private VueConfirmation vueConfirmation;
		@ManagedProperty(value="#{gestionRecherche}")
		private GestionFormulaire gestionFormulaire;
		private String artist;
		private boolean orthoArtist;
		private String album;
		private boolean orthoAlbum;
		private String track;
		private boolean orthoTrack;
		private int ceQueRecherche;
		
		/************************** methodes    **************************/

		/**
		 * 
		 */
		public String lancerRecherche(){
			boolean succes=false;
			int saitCeQuIlVeut=gestionFormulaire.setObjectif(ceQueRecherche);
			//item1Chanson item2Artiste item3Album
			if(!track.isEmpty()){
				vueConfirmation.setCompteRendu(vueConfirmation.getCompteRendu()+"Chanson : "+track+"/n");
				gestionFormulaire.addTrack(track,orthoTrack);
				succes=true;
			}
			if(!artist.isEmpty()){
				vueConfirmation.setCompteRendu(vueConfirmation.getCompteRendu()+"Groupe : "+artist+"/n");
				gestionFormulaire.addArtist(artist,orthoArtist);
				succes=true;
			}
			if(!album.isEmpty()){
				vueConfirmation.setCompteRendu(vueConfirmation.getCompteRendu()+"Album : "+album+"/n");
				gestionFormulaire.addAlbum(album,orthoAlbum);
				succes=true;
			}
			if(saitCeQuIlVeut==0) succes=false;
			if(succes){
				gestionFormulaire.lancerRecherche();
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

  
		

		
		
		/************************** getters/setters **************************/
		public String getArtist() {
			return artist;
		}


		public void setArtist(String artist) {
			this.artist = artist;
		}


		public boolean isOrthoArtist() {
			return orthoArtist;
		}


		public void setOrthoArtist(boolean orthoArtist) {
			this.orthoArtist = orthoArtist;
		}


		public String getAlbum() {
			return album;
		}


		public void setAlbum(String album) {
			this.album = album;
		}


		public boolean isOrthoAlbum() {
			return orthoAlbum;
		}


		public void setOrthoAlbum(boolean orthoAlbum) {
			this.orthoAlbum = orthoAlbum;
		}


		public String getTrack() {
			return track;
		}


		public void setTrack(String track) {
			this.track = track;
		}


		public boolean isOrthoTrack() {
			return orthoTrack;
		}


		public void setOrthoTrack(boolean orthoTrack) {
			this.orthoTrack = orthoTrack;
		}


		public int getCeQueRecherche() {
			return ceQueRecherche;
		}


		public void setCeQueRecherche(int ceQueRecherche) {
			this.ceQueRecherche = ceQueRecherche;
		}
}
