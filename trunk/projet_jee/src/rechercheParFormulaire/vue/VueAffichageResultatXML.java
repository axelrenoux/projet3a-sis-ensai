package rechercheParFormulaire.vue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;

import metier.Cluster;
import metier.Tag;
import metier.Wiki;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;
import rechercheParFormulaire.gestionRecherche.GestionnaireAffichageResultat;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
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


	public void affichageAlbums(){
		affichage(clustersAlbum,"album");
	}
	
	public void affichageArtistes(){
		affichage(clustersArtiste,"artiste");
	}

	public void affichageChansons(){
		affichage(clustersChanson,"chanson");
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
			FacesContext ctx = FacesContext.getCurrentInstance();
			final HttpServletResponse resp = (HttpServletResponse)ctx.getExternalContext().getResponse();
			resp.setContentType("text/xml");
			ServletOutputStream outputStream = resp.getOutputStream();
			//outputStream.write( "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>".getBytes() );//Seule ligne en plus
			/*String xml=*/stream.toXML(cluster,outputStream);
			
			/*SAXBuilder builder=new SAXBuilder();
			Document document=builder.build(xml);
			
			
			SAXBuilder sxb = new SAXBuilder();
			try{
				document = sxb.build(new File("sortieXML.xml"));
			}catch(Exception e){}

			System.out.println(reorganiserXML(document,typeObjetAComparer).toString());
			
			//stream.toXML(document, outputStream);
			
			Format format = Format.getPrettyFormat();
			format.setEncoding("ISO-8859-15");
			XMLOutputter sortie = new XMLOutputter(format);

			//outputter.output(document, outputStream);
			sortie.output(reorganiserXML(document,typeObjetAComparer),outputStream); */
			
			outputStream.flush();
			outputStream.close();
			ctx.responseComplete();
		}catch(IOException e){e.printStackTrace();} 
		//catch (JDOMException e) {e.printStackTrace();}
	}

	
	public Document reorganiserXML(Document document,String typeObjetAComparer){
		Element clusterNiv0 = document.getRootElement();  
		Element contenuNiv0=clusterNiv0.getChild("contenu");
		List listEntryNiv1 = contenuNiv0.getChildren("entry");
		Iterator iEntry1 = listEntryNiv1.iterator();
		while(iEntry1.hasNext()){
			Element entryNiv1 = (Element)iEntry1.next();
			//On supprime le string : on l'a déjà dans nomCluster
			entryNiv1.removeChild("string");
			Element clusterNiv1 = entryNiv1.getChild("cluster");
			Element contenuNiv1 = clusterNiv1.getChild("contenu");
			List listEntryNiv2 = contenuNiv1.getChildren("entry");
			Iterator iEntry2 = listEntryNiv2.iterator();
			while(iEntry2.hasNext()){
				Element entryNiv2 = (Element)iEntry2.next();
				//On supprime le string : on l'a déjà dans nomCluster
				entryNiv2.removeChild("string");
				Element clusterNiv2 = entryNiv2.getChild("cluster");
				Element contenuNiv2 = clusterNiv2.getChild("contenu");
				List listEntryNiv3 = contenuNiv2.getChildren("entry");
				Iterator iEntry3 = listEntryNiv3.iterator();
				while(iEntry3.hasNext()){
					Element entryNiv3 = (Element)iEntry3.next();
					//On ne conserve pas le string : on l'a déjà dans nomCluster
					//entryNiv3.removeChild("string");
					Element artisteAlbumChanson=entryNiv3.getChild(typeObjetAComparer);
					//On supprime la fonction de rapprochement
					artisteAlbumChanson.removeChild("fonctionDeRapprochement");
					clusterNiv2.addContent(artisteAlbumChanson);
				}
				//Element nomClusterNiv2=clusterNiv2.getChild("nomCluster");
				clusterNiv2.removeChild("contenu");
				clusterNiv1.addContent(clusterNiv2);
			}
			//Element nomClusterNiv1=clusterNiv1.getChild("nomCluster");
			clusterNiv1.removeChild("contenu");
			clusterNiv0.addContent(clusterNiv1);
		}
		//Element nomClusterNiv0=clusterNiv0.getChild("nomCluster");
		clusterNiv0.removeChild("contenu");
		document.setRootElement(clusterNiv0);
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
