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
	 * Cette m�thode n'est pas utiliser pour charger la base de donn�e.
	 * C'est une m�thode de controle permettant de charger juste le liste des URL.
	 * Ceci est ind�pendant du chargement de la base de donn�e.
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
		*XXX il est suppos� que si l'on arrive pas � se connecter � la base de donn�e,
		*	cela vaudra aussi bien pour enregistrer les donn�es que pour v�rifier les url
		*	
		*TODO v�rifier que ce pr�-suppos� n'est pas trop risqu�
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
	 * Regarde si l'url est d�j� pr�sente dans la base. Si oui, renvoie la cl� correspondante
	 * 
	 * @see UrlReserveeException : controle suppl�mentaire, au cas o�...
	 */
	public static int verifierDispoUrl(String url,int cle){
		if(urlExistantes.isEmpty()){
			chargerUrl();
		}
		if(urlExistantes.containsKey(url)){
			cle=urlExistantes.get(url);
		}
		/*
		 * XXX v�rifier si contains prends en compte le contenu ou l'adresse m�moire.
		 * Si c'est le contenu, il devrait pouvoir reconnaitre deux String �gaux.
		 * Si c'est l'adresse m�moire, ce controle sera inneficient !
		 * Et le second controle aussi vu qu'il s'appuie aussi sur cette m�thode
		 */
		return cle;
	}
}
