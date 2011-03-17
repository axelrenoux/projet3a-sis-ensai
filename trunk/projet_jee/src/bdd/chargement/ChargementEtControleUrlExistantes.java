package bdd.chargement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import bdd.sqlviajdbc.ControlAccesSQLViaJDBC;
import exceptions.QueryException;
import exceptions.UrlReserveeException;

public class ChargementEtControleUrlExistantes extends ChargementBDD{
	private static Map<String,Integer> urlExistantes=new HashMap<String,Integer>();
	
	/**
	 * Cette méthode n'est pas utiliser pour charger la base de donnée.
	 * C'est une méthode de controle permettant de charger juste le liste des URL.
	 * Ceci est indépendant du chargement de la base de donnée.
	 * 
	 * @see GestionBddJavaPourSauvegarde.init();
	 */
	public static void chargerUrl() {
		ResultSet resultat;
		try {
			String recherche="SELECT DISTINCT url,cle_primaire FROM ID_NAME_URL";
			resultat = ControlAccesSQLViaJDBC.executerRequeteAvecRetour(recherche);
			while(resultat.next()){
				urlExistantes.put(resultat.getString("url"),resultat.getInt("cle_primaire"));
			}
		}/*
		*XXX il est supposé que si l'on arrive pas à se connecter à la base de donnée,
		*	cela vaudra aussi bien pour enregistrer les données que pour vérifier les url
		*	
		*TODO vérifier que ce pré-supposé n'est pas trop risqué
		*/ 
		catch (QueryException e) {e.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}

	/**
	 * @see ControleSauvegardeUnFormatPourLaBdd.sauverCoord(...)
	 */
	public static void registerUrl(String newUrl,int cle) throws UrlReserveeException{
		if(cle!=verifierDispoUrl(newUrl,cle)){
			throw new UrlReserveeException(newUrl);
		}else{
			
		}
	}
	
	/**
	 * Regarde si l'url est déjà présente dans la base. Si oui, renvoie la clé correspondante
	 * 
	 * @see UrlReserveeException : controle supplémentaire, au cas où...
	 */
	public static int verifierDispoUrl(String url,int cle){
		if(urlExistantes.isEmpty()){
			chargerUrl();
		}
		if(urlExistantes.containsKey(url)){
			cle=urlExistantes.get(url);
		}
		/*
		 * XXX vérifier si contains prends en compte le contenu ou l'adresse mémoire.
		 * Si c'est le contenu, il devrait pouvoir reconnaitre deux String égaux.
		 * Si c'est l'adresse mémoire, ce controle sera inneficient !
		 * Et le second controle aussi vu qu'il s'appuie aussi sur cette méthode
		 */
		return cle;
	}
}
