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

	 
	
	
	public Cluster lancerRechercheMeilleurCluster(String motCle) {
		
		ArrayList<Album> ar=null;
		RechercheAlbumBDD mar = RechercheAlbumBDD.getInstance();
		try {
			ar = mar.rechercherAlbums(motCle);
		} catch (ChargementException e) {
			e.printStackTrace();
		}

		HashMap<CoupleAxe,Cluster> listeClusterPossible = CalculateurDeClustersAlbums.getInstanceunique().calculEnsembleClustersAlbums(ar);


		Cluster meilleurCluster = new Cluster();
		
		for(Entry<CoupleAxe, Cluster> entry : listeClusterPossible.entrySet()) {
			meilleurCluster = entry.getValue();
			meilleurCluster.setNomCluster(entry.getKey().getAxe1().getType() + ";" + entry.getKey().getAxe2().getType());
			break;
		}
		for(Entry<CoupleAxe, Cluster> entry2 : listeClusterPossible.entrySet()) {

			if (entry2.getKey().getVariance() < meilleurCluster.varianceCluster()){
				meilleurCluster = entry2.getValue();
				meilleurCluster.setNomCluster(entry2.getKey().getAxe1().getType() + ";" + entry2.getKey().getAxe2().getType());
			}
		}
		System.out.println("meilleur cluster : " + meilleurCluster.varianceCluster());
		System.out.println(meilleurCluster.tailleCluster());
		System.out.println(meilleurCluster.getNomCluster());
		return meilleurCluster;
	}
	
	public Cluster lancerRechercheDeuxièmeMeilleurCluster(String motCle){
		
		ArrayList<Album> ar=null;
		RechercheAlbumBDD mar = RechercheAlbumBDD.getInstance();
		try {
			ar = mar.rechercherAlbums(motCle);
		} catch (ChargementException e) {
			e.printStackTrace();
		}

		HashMap<CoupleAxe,Cluster> listeClusterPossible = CalculateurDeClustersAlbums.getInstanceunique().calculEnsembleClustersAlbums(ar);
		listeClusterPossible.remove(lancerRechercheMeilleurCluster(motCle));

		Cluster deuxièmeMeilleurCluster = new Cluster();
		
		for(Entry<CoupleAxe, Cluster> entry : listeClusterPossible.entrySet()) {
			deuxièmeMeilleurCluster = entry.getValue();
			deuxièmeMeilleurCluster.setNomCluster(entry.getKey().getAxe1().getType() + ";" + entry.getKey().getAxe2().getType());
			break;
		}
		
		for(Entry<CoupleAxe, Cluster> entry2 : listeClusterPossible.entrySet()) {

			if (entry2.getKey().getVariance() < deuxièmeMeilleurCluster.varianceCluster()){
				deuxièmeMeilleurCluster = entry2.getValue();
				deuxièmeMeilleurCluster.setNomCluster(entry2.getKey().getAxe1().getType() + ";" + entry2.getKey().getAxe2().getType());
			}
		}
		System.out.println(" Deuxième meilleur cluster : " + deuxièmeMeilleurCluster.varianceCluster());
		System.out.println(deuxièmeMeilleurCluster.tailleCluster());
		System.out.println(deuxièmeMeilleurCluster.getNomCluster());
		return deuxièmeMeilleurCluster;
	}
	
	public Cluster lancerRechercheTroisièmeMeilleurCluster(String motCle){
		
		ArrayList<Album> ar=null;
		RechercheAlbumBDD mar = RechercheAlbumBDD.getInstance();
		try {
			ar = mar.rechercherAlbums(motCle);
		} catch (ChargementException e) {
			e.printStackTrace();
		}

		HashMap<CoupleAxe,Cluster> listeClusterPossible = CalculateurDeClustersAlbums.getInstanceunique().calculEnsembleClustersAlbums(ar);
		listeClusterPossible.remove(lancerRechercheMeilleurCluster(motCle));
		listeClusterPossible.remove(lancerRechercheMeilleurCluster(motCle));

		Cluster troisièmeMeilleurCluster = new Cluster();
		
		for(Entry<CoupleAxe, Cluster> entry : listeClusterPossible.entrySet()) {
			troisièmeMeilleurCluster = entry.getValue();
			troisièmeMeilleurCluster.setNomCluster(entry.getKey().getAxe1().getType() + ";" + entry.getKey().getAxe2().getType());
			break;
		}
		
		for(Entry<CoupleAxe, Cluster> entry2 : listeClusterPossible.entrySet()) {

			if (entry2.getKey().getVariance() < troisièmeMeilleurCluster.varianceCluster()){
				troisièmeMeilleurCluster = entry2.getValue();
				troisièmeMeilleurCluster.setNomCluster(entry2.getKey().getAxe1().getType() + ";" + entry2.getKey().getAxe2().getType());
			}
		}
		System.out.println(" Deuxième meilleur cluster : " + troisièmeMeilleurCluster.varianceCluster());
		System.out.println(troisièmeMeilleurCluster.tailleCluster());
		System.out.println(troisièmeMeilleurCluster.getNomCluster());
		return troisièmeMeilleurCluster;
	}
	
	
	
	public String retournerTypeAffichage(){
		return "album";
	}
}
