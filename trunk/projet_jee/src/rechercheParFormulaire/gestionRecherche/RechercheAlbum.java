package rechercheParFormulaire.gestionRecherche;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import bdd.rechercheBDD.RechercheAlbumBDD;
import bdd.rechercheBDD.RechercheArtisteBDD;

import com.thoughtworks.xstream.XStream;


import metier.Cluster;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import calculsDesClusters.axe.Axe;
import calculsDesClusters.axe.AxeAnnee;
import calculsDesClusters.axe.AxeOeuvre;
import calculsDesClusters.axe.AxeSaison;
import calculsDesClusters.axe.CoupleAxe;
import calculsDesClusters.calcul.CalculateurDeClustersAlbums;
import calculsDesClusters.calcul.CalculateurDeClustersArtistes;
import controleur.UtilitaireDate;
import exceptions.ChargementException;
import exceptions.ExceptionDate;

public class RechercheAlbum {
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	private ArrayList<Album> resultats;
	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/

	 
	
	
	/**
	 * methode qui renvoie la liste totale des albums repondant au mot clé
	 * @param motCle
	 * @return
	 */
	public ArrayList<Cluster> lancerRecherche(String motCle) {
		ArrayList<Album> listeAlbums=null;
		RechercheAlbumBDD recherche = RechercheAlbumBDD.getInstance();
		try {
			listeAlbums = recherche.rechercherAlbums(motCle);
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rechercherMeilleursClusters(listeAlbums);	
	}
	
	/**
	 * methode qui renvoie le meilleur cluster, le 2ème meilleur et le 3ème...
	 * 
	 * @param listeAlbums
	 * @param niveau
	 * @return
	 */
	private ArrayList<Cluster> rechercherMeilleursClusters(ArrayList<Album> listeAlbums){
		ArrayList<Cluster> top3clusters = new ArrayList<Cluster>();
		CoupleAxe ca = null;

		HashMap<CoupleAxe,Cluster> listeClustersPossibles = CalculateurDeClustersAlbums.
									getInstanceunique().calculEnsembleClustersAlbums(listeAlbums);

		//1) on récupère le meilleur
		
		Cluster meilleurCluster = new Cluster();
		//on attribue par défaut la meilleure place au premier element de la hashmap
		for(Entry<CoupleAxe, Cluster> entry : listeClustersPossibles.entrySet()) {			
			meilleurCluster = entry.getValue();
			ca = entry.getKey();//on recupere la cle du meilleur clusteur mis par defaut
			meilleurCluster.setNomCluster(entry.getKey().getAxe1().getType() + ";" + entry.getKey().getAxe2().getType());
			break;
		}
		
		//on parcourt la hashmap pour trouver le cluster qui a la plus petite variance
		for(Entry<CoupleAxe, Cluster> entry2 : listeClustersPossibles.entrySet()) {
			if (entry2.getKey().getVariance() < meilleurCluster.varianceCluster()){
				meilleurCluster = entry2.getValue();
				ca = entry2.getKey();
				meilleurCluster.setNomCluster(entry2.getKey().getAxe1().getType() + ";" + entry2.getKey().getAxe2().getType());
			}
		}
		
		
		top3clusters.add(meilleurCluster);
		
		//2) on recupère le deuxième meilleur cluster
		
		//on supprime le "1er meilleur" a partir de la cle
		listeClustersPossibles.remove(ca);
		
		Cluster meilleurCluster2 = new Cluster();
		//on attribue par défaut la meilleure place au premier element de la hashmap
		for(Entry<CoupleAxe, Cluster> entry : listeClustersPossibles.entrySet()) {			
			meilleurCluster2 = entry.getValue();
			ca = entry.getKey();//on recupere la cle du meilleur clusteur mis par defaut
			meilleurCluster2.setNomCluster(entry.getKey().getAxe1().getType() + ";" + entry.getKey().getAxe2().getType());
			break;
		}
		
		//on parcourt la hashmap pour trouver le cluster qui a la plus petite variance
		for(Entry<CoupleAxe, Cluster> entry2 : listeClustersPossibles.entrySet()) {
			if (entry2.getKey().getVariance() < meilleurCluster2.varianceCluster()){
				meilleurCluster2 = entry2.getValue();
				ca = entry2.getKey();
				meilleurCluster2.setNomCluster(entry2.getKey().getAxe1().getType() + ";" + entry2.getKey().getAxe2().getType());
			}
		}
		 
		top3clusters.add(meilleurCluster2);
		
		
		//3) on recupère le troisieme meilleur cluster
		
		//on supprime le "2eme meilleur"
		listeClustersPossibles.remove(ca);
		
		Cluster meilleurCluster3 = new Cluster();
		//on attribue par défaut la meilleure place au premier element de la hashmap
		for(Entry<CoupleAxe, Cluster> entry : listeClustersPossibles.entrySet()) {			
			meilleurCluster3 = entry.getValue();
			meilleurCluster3.setNomCluster(entry.getKey().getAxe1().getType() + ";" + entry.getKey().getAxe2().getType());
			break;
		}
		
		//on parcourt la hashmap pour trouver le cluster qui a la plus petite variance
		for(Entry<CoupleAxe, Cluster> entry2 : listeClustersPossibles.entrySet()) {
			if (entry2.getKey().getVariance() < meilleurCluster3.varianceCluster()){
				meilleurCluster3 = entry2.getValue();
				meilleurCluster3.setNomCluster(entry2.getKey().getAxe1().getType() + ";" + entry2.getKey().getAxe2().getType());
			}
		}
		top3clusters.add(meilleurCluster3);
		
		
		return top3clusters;
	}
	
	
	
	public String retournerTypeAffichage(){
		return "album";
	}
}
