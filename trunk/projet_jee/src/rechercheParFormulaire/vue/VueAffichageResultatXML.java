package rechercheParFormulaire.vue;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import metier.Cluster;
import rechercheParFormulaire.gestionRecherche.GestionnaireAffichageResultat;

import com.thoughtworks.xstream.XStream;


/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 */
/**
 * @author Administrateur
 *
 */
/**
 * @author Administrateur
 *
 */
@ManagedBean
@SessionScoped
public class VueAffichageResultatXML {

	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	@ManagedProperty(value="#{gestionnaireAffichageResultat}")
	private GestionnaireAffichageResultat gestionnaireAffichageResultat;


	private Cluster clustersAlbum;
	private Cluster clustersArtiste;
	private Cluster clustersChanson;
	



	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/


	public void init() {}


	public void affichage(Cluster cluster){
		XStream stream= new XStream();
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			final HttpServletResponse resp = (HttpServletResponse)ctx.getExternalContext().getResponse();
			resp.setContentType("text/xml");
			stream.toXML(cluster, resp.getOutputStream());
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
			ctx.responseComplete();
		}catch(IOException e){e.printStackTrace();}
	}

	public void affichageAlbums(){
		affichage(clustersAlbum);
	}
	
	public void affichageArtistes(){
		affichage(clustersArtiste);
	}

	public void affichageChansons(){
		affichage(clustersChanson);
	}
	

	
	/**
	 *  methodes de navigation
	 */
	public String retourDebut(){
		return "success";
	}

	
	

	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	public GestionnaireAffichageResultat getGestionnaireAffichageResultat() {
		return gestionnaireAffichageResultat;
	}

	public void setGestionnaireAffichageResultat(
			GestionnaireAffichageResultat gestionnaireAffichageResultat) {
		this.gestionnaireAffichageResultat = gestionnaireAffichageResultat;
	}

	public Cluster getClustersAlbum() {
		return clustersAlbum;
	}

	public void setClustersAlbum(Cluster clustersAlbum) {
		this.clustersAlbum = clustersAlbum;
	}

	public Cluster getClustersArtiste() {
		return clustersArtiste;
	}

	public void setClustersArtiste(Cluster clustersArtiste) {
		this.clustersArtiste = clustersArtiste;
	}

	public Cluster getClustersChanson() {
		return clustersChanson;
	}

	public void setClustersChanson(Cluster clustersChanson) {
		this.clustersChanson = clustersChanson;
	}
}
