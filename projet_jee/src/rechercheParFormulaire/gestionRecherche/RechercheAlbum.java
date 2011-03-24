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
	
	public Cluster lancerRechercheDeuxi�meMeilleurCluster(String motCle){
		
		ArrayList<Album> ar=null;
		RechercheAlbumBDD mar = RechercheAlbumBDD.getInstance();
		try {
			ar = mar.rechercherAlbums(motCle);
		} catch (ChargementException e) {
			e.printStackTrace();
		}

		HashMap<CoupleAxe,Cluster> listeClusterPossible = CalculateurDeClustersAlbums.getInstanceunique().calculEnsembleClustersAlbums(ar);
		listeClusterPossible.remove(lancerRechercheMeilleurCluster(motCle));

		Cluster deuxi�meMeilleurCluster = new Cluster();
		
		for(Entry<CoupleAxe, Cluster> entry : listeClusterPossible.entrySet()) {
			deuxi�meMeilleurCluster = entry.getValue();
			deuxi�meMeilleurCluster.setNomCluster(entry.getKey().getAxe1().getType() + ";" + entry.getKey().getAxe2().getType());
			break;
		}
		
		for(Entry<CoupleAxe, Cluster> entry2 : listeClusterPossible.entrySet()) {

			if (entry2.getKey().getVariance() < deuxi�meMeilleurCluster.varianceCluster()){
				deuxi�meMeilleurCluster = entry2.getValue();
				deuxi�meMeilleurCluster.setNomCluster(entry2.getKey().getAxe1().getType() + ";" + entry2.getKey().getAxe2().getType());
			}
		}
		System.out.println(" Deuxi�me meilleur cluster : " + deuxi�meMeilleurCluster.varianceCluster());
		System.out.println(deuxi�meMeilleurCluster.tailleCluster());
		System.out.println(deuxi�meMeilleurCluster.getNomCluster());
		return deuxi�meMeilleurCluster;
	}
	
	public Cluster lancerRechercheTroisi�meMeilleurCluster(String motCle){
		
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

		Cluster troisi�meMeilleurCluster = new Cluster();
		
		for(Entry<CoupleAxe, Cluster> entry : listeClusterPossible.entrySet()) {
			troisi�meMeilleurCluster = entry.getValue();
			troisi�meMeilleurCluster.setNomCluster(entry.getKey().getAxe1().getType() + ";" + entry.getKey().getAxe2().getType());
			break;
		}
		
		for(Entry<CoupleAxe, Cluster> entry2 : listeClusterPossible.entrySet()) {

			if (entry2.getKey().getVariance() < troisi�meMeilleurCluster.varianceCluster()){
				troisi�meMeilleurCluster = entry2.getValue();
				troisi�meMeilleurCluster.setNomCluster(entry2.getKey().getAxe1().getType() + ";" + entry2.getKey().getAxe2().getType());
			}
		}
		System.out.println(" Deuxi�me meilleur cluster : " + troisi�meMeilleurCluster.varianceCluster());
		System.out.println(troisi�meMeilleurCluster.tailleCluster());
		System.out.println(troisi�meMeilleurCluster.getNomCluster());
		return troisi�meMeilleurCluster;
	}
	
	
	
	public String retournerTypeAffichage(){
		return "album";
	}
}
