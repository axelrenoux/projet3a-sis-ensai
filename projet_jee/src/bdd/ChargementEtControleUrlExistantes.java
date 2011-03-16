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
	 * Cette m�thode n'est pas utiliser pour charger la base de donn�e.
	 * C'est une m�thode de controle permettant de charger juste le liste des URL.
	 * Ceci est ind�pendant du chargement de la base de donn�e.
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
		*XXX il est suppos� que si l'on arrive pas � se connecter � la base de donn�e,
		*	cela vaudra aussi bien pour enregistrer les donn�es que pour v�rifier les url
		*	
		*TODO v�rifier que ce pr�-suppos� n'est pas trop risqu�
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
	 * @see UrlReserveeException : controle suppl�mentaire, au cas o�...
	 */
	public static boolean verifierDispoUrl(String url){
		if(urlExistantes.isEmpty()){
			chargerUrl();
		}
		/*
		 * XXX v�rifier si contains prends en compte le contenu ou l'adresse m�moire.
		 * Si c'est le contenu, il devrait pouvoir reconnaitre deux String �gaux.
		 * Si c'est l'adresse m�moire, ce controle sera inneficient !
		 * Mais ce n'est pas si grave vu qu'on a un second controle... 
		 */
		return !urlExistantes.contains(url);
	}
}
