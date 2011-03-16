package bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bdd.sqlviajdbc.ControlAccesSQLViaJDBC;


import exceptions.ConnectionException;
import exceptions.QueryException;
import exceptions.UrlReserveeException;

public class ChargementEtControleUrlExistantes extends ChargementBDD{
	private static List<String> urlExistantes=new ArrayList<String>();
	
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
			ControlAccesSQLViaJDBC.connecter();
			String recherche="SELECT DISTINCT url FROM ID_NAME_URL";
			resultat = ControlAccesSQLViaJDBC.executerRequeteAvecRetour(recherche);
			while(resultat.next()){
				urlExistantes.add(resultat.getString("url"));
			}
			ControlAccesSQLViaJDBC.fermerBDD();
		}/*
		*XXX il est supposé que si l'on arrive pas à se connecter à la base de donnée,
		*	cela vaudra aussi bien pour enregistrer les données que pour vérifier les url
		*	
		*TODO vérifier que ce pré-supposé n'est pas trop risqué
		*/ 
		catch (ConnectionException e) {e.printStackTrace();} 
		catch (QueryException e) {e.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}

	/**
	 * @see ControleSauvegardeUnFormatPourLaBdd.sauverCoord(...)
	 */
	public static void registerUrl(String newUrl) throws UrlReserveeException{
		if(!verifierDispoUrl(newUrl)){
			throw new UrlReserveeException(newUrl);
		}else{
			
		}
	}
	
	/**
	 * @see UrlReserveeException : controle supplémentaire, au cas où...
	 */
	public static boolean verifierDispoUrl(String url){
		if(urlExistantes.isEmpty()){
			chargerUrl();
		}
		/*
		 * XXX vérifier si contains prends en compte le contenu ou l'adresse mémoire.
		 * Si c'est le contenu, il devrait pouvoir reconnaitre deux String égaux.
		 * Si c'est l'adresse mémoire, ce controle sera inneficient !
		 * Mais ce n'est pas si grave vu qu'on a un second controle... 
		 */
		return !urlExistantes.contains(url);
	}
}
