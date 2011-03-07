package vues;

 
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

 

import formation.jsf.gestionscolaire.metier.entite.Groupe;
import formation.jsf.gestionscolaire.metier.entite.Personne;
import formation.jsf.gestionscolaire.metier.service.GestionGroupesService;


/**
 * on ajoute ces 2 lignes afin que la classe soit instanciee automatiquement (managedBean)
 * et qu'il n'y ait qu'une seule instance tout au long de la session (sessionScoped)
 * @author sis1
 */
@ManagedBean
@SessionScoped
public class VueTrombiGroupe {
	
	/**************************   attributs  **************************/
	
	/**
	 * vueTrombiGroupe possede 1 managed property : 
	 * */
	@ManagedProperty(value="#{gestionGroupesService}")
	private GestionGroupesService gestionGroupes;
	private List<Groupe> listeGroupes = null;
	private Groupe groupeChoisi;	
	private TreeNode arbre;  
	private TreeNode selectedNode;  
	private String effect = "flash"; 

	/************************** methodes    **************************/
	
	
	/**
	 * methode qui crée un arbre de groupes et sous groupes
	 * 
	 * @param node0
	 * @param groupe
	 */
	public void creerNode(TreeNode node0, Groupe groupe){
		for (Groupe g1 : groupe.getListeGroupes()){
        	TreeNode node1=new DefaultTreeNode(g1, node0);
        	if(g1.getListeGroupes()!=null){
        		creerNode(node1,g1);
        	}
		}
	}
	
	/**
	 * methode qui sexecute quand on selectionne un element de l'arbre (un groupe)
	 * @param event
	 */
	public void onNodeSelect(NodeSelectEvent event) {  
		groupeChoisi = (Groupe)selectedNode.getData();
	}  
	
	

	
	/************************** getters/setters **************************/
	
	
	/**
	 * @return l'arbre des groupes
	 */
	public TreeNode getArbre() {  
		TreeNode root;
		
		root = new DefaultTreeNode("Choisir groupe", null); 
		for (Groupe g0 : gestionGroupes.rechercherTousGroupesGlobaux()){
			TreeNode node0 = new DefaultTreeNode(g0, root); 
			if(g0.getListeGroupes()!=null)creerNode(node0,g0);
		}
		return root;
    } 
	
	
	
	
	/**
	 * @return l'element de l'arbre selectionné
	 */
	public TreeNode getSelectedNode() {
		return selectedNode;
	}




	/**
	 * @param selectedNode
	 */
	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}




	/**
	 * @param arbre
	 */
	public void setArbre(TreeNode arbre) {
		this.arbre = arbre;
	}

	
	
	/**
	 * @return retourne le groupe choisi
	 */
	public Groupe getGroupeChoisi() {
		return groupeChoisi;
	}



	/**
	 * @param groupeChoisi
	 */
	public void setGroupeChoisi(Groupe groupeChoisi) {
		this.groupeChoisi = groupeChoisi;
	}



	/**
	 * @return listeGroupes
	 */
	public List<Groupe> getListeGroupes() {
		if(listeGroupes==null){
			listeGroupes = gestionGroupes.rechercherTous();
		}
		
		return listeGroupes;
	}



	/**
	 * @param listeGroupes
	 */
	public void setListeGroupes(List<Groupe> listeGroupes) {
		this.listeGroupes = listeGroupes;
	}



	


	/**
	 * @return gestionGroupes
	 */
	public GestionGroupesService getGestionGroupes() {
		return gestionGroupes;
	}



	/**
	 * @param gestionGroupes
	 */
	public void setGestionGroupes(GestionGroupesService gestionGroupes) {
		this.gestionGroupes = gestionGroupes;
	}


	/**
	 * @return gestionGroupes
	 */
	public GestionGroupesService getGestion() {
		return gestionGroupes;
	}



	/**
	 * @param gestion
	 */
	public void setGestion(GestionGroupesService gestion) {
		this.gestionGroupes = gestion;
	}

	 
	


	/**
	 * @return
	 */
	public String getEffect() {
		return effect;
	}



	/**
	 * @param effect
	 */
	public void setEffect(String effect) {
		this.effect = effect;
	}


}
