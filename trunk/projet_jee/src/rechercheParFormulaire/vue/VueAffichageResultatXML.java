package rechercheParFormulaire.vue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import metier.Cluster;
import metier.Tag;
import metier.Wiki;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import rechercheParFormulaire.gestionRecherche.GestionnaireAffichageResultat;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 */
@ManagedBean
@SessionScoped
public class VueAffichageResultatXML {

	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	@ManagedProperty(value="#{gestionnaireAffichageResultat}")
	private GestionnaireAffichageResultat gestionnaireAffichageResultat;


	private ArrayList<Cluster> clustersAlbumTop3;
	private ArrayList<Cluster> clustersArtisteTop3;
	private ArrayList<Cluster> clustersChansonTop3;

	
	private Cluster clustersAlbumCourant;
	private Cluster clustersArtisteCourant;
	private Cluster clustersChansonCourant;
	
	
	private int choixClustering=1;
	private ArrayList<Integer> listeChoixClustering;
	



	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/


	public void init() {
		//on affecte les clusters courant
		clustersAlbumCourant = clustersAlbumTop3.get(choixClustering-1);
		clustersArtisteCourant = clustersArtisteTop3.get(choixClustering-1);
		clustersChansonCourant = clustersChansonTop3.get(choixClustering-1);
		
		//on initialise les valeurs des choix de clustering
		initialisationChoixClustering();
	}
	
	
	

	public void initialisationChoixClustering(){
		listeChoixClustering = new ArrayList<Integer>();
		listeChoixClustering.add(1);
		listeChoixClustering.add(2);
		listeChoixClustering.add(3);
		
	}
	
	/**
	 * methode exécutee quand l'utilisateur choisit un autre niveau de clustering et relance la recherche
	 * on change de clustersAlbum/artiste/chanson courant
	 */
	public void changerClustering(){
		//clustersAlbumCourant = clustersAlbumTop3.get(choixClustering-1);
		//clustersArtisteCourant = clustersArtisteTop3.get(choixClustering-1);
		//clustersChansonCourant = clustersChansonTop3.get(choixClustering-1);
		init();
	}
	
	

	public void affichageAlbums(){
		affichage(clustersAlbumCourant,"album");
	}
	
	public void affichageArtistes(){
		affichage(clustersArtisteCourant,"artiste");
	}

	public void affichageChansons(){
		affichage(clustersChansonCourant,"chanson");
	}

	public void affichage(Cluster cluster, String typeObjetAComparer){
		XStream stream= new XStream(new DomDriver("ISO-8859-15"));//XStream stream= new XStream();
		stream.alias("cluster",Cluster.class);
		stream.alias("album",Album.class);
		stream.alias("artiste",Artiste.class);
		stream.alias("chanson",Chanson.class);
		stream.alias("tag",Tag.class);
		stream.alias("wiki",Wiki.class);
		try {
			//On transforme l'arborescence de nos clusters en XML
			String stringXMLbrut=stream.toXML(cluster);
			//On altère certains caractères spéciaux non-gérés par XML
			stringXMLbrut.replaceAll("%19","_");
			//on enregistre le XML sous XMLbrut.xml
			PrintWriter fluxSortie = new PrintWriter(new FileWriter("XMLbrut.xml"));
			fluxSortie.println( "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>");
			fluxSortie.println(stringXMLbrut);
			fluxSortie.close();
			
			//On récupère le XML de XMLbrut.xml 
			SAXBuilder sax=new SAXBuilder();
			Document document = sax.build(new File("XMLbrut.xml"));
			
			//on réorganise le XML
			document=reorganiserXML(document,typeObjetAComparer);
			//On l'enregistre sous sortieXML.xml
			enregistre(document,"sortieXML.xml");
			
			//On récupère le OutputStream de J2E
			FacesContext ctx = FacesContext.getCurrentInstance();
			final HttpServletResponse resp = (HttpServletResponse)ctx.getExternalContext().getResponse();
			resp.setContentType("text/xml");
			ServletOutputStream outputStream = resp.getOutputStream();
			
			//On crée un XMLOutputter
			//OutputStreamWriter writer = new OutputStreamWriter(outputStream);
			//writer.write(document.toString());
			Format format = Format.getPrettyFormat();
			format.setEncoding("ISO-8859-15");
			XMLOutputter sortie = new XMLOutputter(format);

			//Le XMLOutputter écrit notre document dans le OutputStream
			sortie.output(document,outputStream);
			
			//on ferme
			outputStream.flush();
			outputStream.close();
			ctx.responseComplete();
		}catch(IOException e){e.printStackTrace();} 
		catch (JDOMException e) {e.printStackTrace();}
	}
	
	
	public void enregistre(Document document,String fichier){
		try{
			XMLOutputter sortie=new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document,new FileOutputStream(fichier));
		}catch(IOException e){}
	}
	
	@SuppressWarnings("rawtypes")
	public Document reorganiserXML(Document document,String typeObjetAComparer){
		Element clusterNiv0 = document.getRootElement();
		Element newclusterNiv0=new Element("cluster");
		Iterator iEntry1 = clusterNiv0.getChild("contenu").getChildren("entry").iterator();
		while(iEntry1.hasNext()){
			Element clusterNiv1 = ((Element)iEntry1.next()).getChild("cluster");
			//On reconstruit une arborescence similaire
			Element newclusterNiv1=new Element("cluster");
			newclusterNiv0.addContent(newclusterNiv1);
			//On continue à parcourir l'arborescence
			Iterator iEntry2 = clusterNiv1.getChild("contenu").getChildren("entry").iterator();
			while(iEntry2.hasNext()){
				Element clusterNiv2 = ((Element)iEntry2.next()).getChild("cluster");
				//On reconstruit une arborescence similaire
				Element newclusterNiv2=new Element("cluster");
				newclusterNiv1.addContent(newclusterNiv2);
				//On continue à parcourir l'arborescence
				Iterator iEntry3 = clusterNiv2.getChild("contenu").getChildren("entry").iterator();
				while(iEntry3.hasNext()){
					//On duplique l'objet qui nous intéresse
					Element artisteAlbumChanson=(Element) ((Element)iEntry3.next()).getChild(typeObjetAComparer).clone();
					//On supprime la fonction de rapprochement
					artisteAlbumChanson.removeChild("fonctionDeRapprochement");
					newclusterNiv2.addContent(artisteAlbumChanson);
				}
				//On duplique le nom du cluster, et on l'ajoute
				String nomClusterNiv2=clusterNiv2.getChild("nomCluster").getText();
				Attribute nomCluster=new Attribute("nomCluster", nomClusterNiv2);
				newclusterNiv2.setAttribute(nomCluster);
			}
			//On duplique le nom du cluster, et on l'ajoute
			String nomClusterNiv1=clusterNiv1.getChild("nomCluster").getText();
			Attribute nomCluster=new Attribute("nomCluster", nomClusterNiv1);
			newclusterNiv1.setAttribute(nomCluster);
		}
		String nomClusterNiv0=clusterNiv0.getChild("nomCluster").getText();
		Attribute nomCluster=new Attribute("nomCluster", nomClusterNiv0);
		newclusterNiv0.setAttribute(nomCluster);
		document=new Document(newclusterNiv0);
		return document;
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


	public Cluster getClustersAlbumCourant() {
		return clustersAlbumCourant;
	}


	public void setClustersAlbumCourant(Cluster clustersAlbumCourant) {
		this.clustersAlbumCourant = clustersAlbumCourant;
	}


	public Cluster getClustersArtisteCourant() {
		return clustersArtisteCourant;
	}


	public void setClustersArtisteCourant(Cluster clustersArtisteCourant) {
		this.clustersArtisteCourant = clustersArtisteCourant;
	}


	public Cluster getClustersChansonCourant() {
		return clustersChansonCourant;
	}


	public void setClustersChansonCourant(Cluster clustersChansonCourant) {
		this.clustersChansonCourant = clustersChansonCourant;
	}




	public ArrayList<Cluster> getClustersAlbumTop3() {
		return clustersAlbumTop3;
	}




	public void setClustersAlbumTop3(ArrayList<Cluster> clustersAlbumTop3) {
		this.clustersAlbumTop3 = clustersAlbumTop3;
	}




	public ArrayList<Cluster> getClustersArtisteTop3() {
		return clustersArtisteTop3;
	}




	public void setClustersArtisteTop3(ArrayList<Cluster> clustersArtisteTop3) {
		this.clustersArtisteTop3 = clustersArtisteTop3;
	}




	public ArrayList<Cluster> getClustersChansonTop3() {
		return clustersChansonTop3;
	}




	public void setClustersChansonTop3(ArrayList<Cluster> clustersChansonTop3) {
		this.clustersChansonTop3 = clustersChansonTop3;
	}




	public int getChoixClustering() {
		return choixClustering;
	}




	public void setChoixClustering(int choixClustering) {
		this.choixClustering = choixClustering;
	}




	public ArrayList<Integer> getListeChoixClustering() {
		return listeChoixClustering;
	}




	public void setListeChoixClustering(ArrayList<Integer> listeChoixClustering) {
		this.listeChoixClustering = listeChoixClustering;
	}

 
}
