package rechercheParFormulaire.vue;
	import java.util.ArrayList;

import javax.annotation.PostConstruct;
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
		@ManagedProperty(value="#{vueAffichageResultatXML}")
		private VueAffichageResultatXML vueAffichageResultatXML;
		private String retourChoisi;
		private ArrayList<String> retoursPossibles;  
		private String motCle;
		
		/********************************************************************/
		/************************      methodes      ************************/
		/********************************************************************/

		//methode qui s'execute � la cr�ation du javabean
		@PostConstruct
		public void init(){
			retoursPossibles= new ArrayList<String>();
			retoursPossibles.add("un fichier XML");
			retoursPossibles.add("une interface graphique");
			retourChoisi="";
		}

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
				if(!retourChoisi.equals("")){
					//si l'utilisateur a choisi de consulter les r�sultats via "un fichier XML"...
					if(retourChoisi.equals(retoursPossibles.get(0))){
						//on attribue les resultats � la page de resultats XML
						//pour chaque type d'oeuvre, on r�cupere le top 3 des clusters � partir du mot cl�
						vueAffichageResultatXML.setClustersAlbumTop3(gestionnaireFormulaire.lancerRechercheAlbum(motCle));
						vueAffichageResultatXML.setClustersArtisteTop3(gestionnaireFormulaire.lancerRechercheArtiste(motCle));
						vueAffichageResultatXML.setClustersChansonTop3(gestionnaireFormulaire.lancerRechercheChanson(motCle));
					
						
						vueAffichageResultatXML.init();
						return "xml";
					}
					//et s'il a choisi de consulter les r�sultats via "une interface graphique"...
					else {
						//on attribue les resultats � la page de resultats j2ee
						//pour chaque type d'oeuvre, on r�cupere le top 3 des clusters � partir du mot cl�
						vueAffichageResultat.setClustersAlbumTop3(gestionnaireFormulaire.lancerRechercheAlbum(motCle));
						vueAffichageResultat.setClustersArtisteTop3(gestionnaireFormulaire.lancerRechercheArtiste(motCle));
						vueAffichageResultat.setClustersChansonTop3(gestionnaireFormulaire.lancerRechercheChanson(motCle));
						
						vueAffichageResultat.init();
						return "j2ee";
					}
				}
				else{
					addErrorChoix();
					return "failure";
				}
				
			}
			else{
				addErrorMotCle();
				return "failure";
			}
		}
		
		
		/**
		 * methode qui ajoute un message d'erreur si l'utilisateur n'a pas saisi de mot cl�
		 */
		public void addErrorMotCle() {  
			FacesContext.getCurrentInstance().
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur","Veuillez saisir un mot cl�")); 
		}

		/**
		 * methode qui ajoute un message d'erreur si l'utilisateur n'a pas choisi un mode d'affichage
		 */
		public void addErrorChoix() {  
			FacesContext.getCurrentInstance().
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur","Veuillez choisir un mode d'affichage")); 
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




		public VueAffichageResultatXML getVueAffichageResultatXML() {
			return vueAffichageResultatXML;
		}


		public void setVueAffichageResultatXML(VueAffichageResultatXML vueAffichageResultatXML) {
			this.vueAffichageResultatXML = vueAffichageResultatXML;
		}
		
		
		public String getRetourChoisi() {
			return retourChoisi;
		}


		public void setRetourChoisi(String retourChoisi) {
			this.retourChoisi = retourChoisi;
		}


		public ArrayList<String> getRetoursPossibles() {
			return retoursPossibles;
		}


		public void setRetoursPossibles(ArrayList<String> retoursPossibles) {
			this.retoursPossibles = retoursPossibles;
		}
		
		
		
		
}
