package bdd.sqlviajdbc;


/**
 * Lieu de passage obligé et de contrôle pour toutes les requetes vers SQLViaJDBC.
 * 
 * Note : la possibilité  de choisir le canal n'a pas été laissée à l'utilisateur
 */

import java.sql.ResultSet;

import exceptions.ConnectionException;
import exceptions.QueryException;
import exceptions.TransactionException;
import exceptions.UpdateException;

public class ControlAccesSQLViaJDBC{
	private static boolean estConnecte=false;
	
	//Constructeur
	public ControlAccesSQLViaJDBC() {}

	private static void connecter() throws ConnectionException{
		SQLViaJDBC.connecter();
		estConnecte=true;
	}
	
	public static void fermerBDD() {
		try{
			SQLViaJDBC.fermerBDD();
			estConnecte=false;
		}catch(ConnectionException e){e.printStackTrace();}
	}
	/**
	 * On aurait voulu forcer l'utilisateur à passer par des méthodes spécifiques pour créer ou supprimer des tables
	 * Mais cela pose des problèmes pour le chargement par fichier texte. Donc on oublie.
	 * 
	 * @param requeteSQL
	 * @throws UpdateException
	 * @throws MethodeDeRequeteErroneeException
	 */
	public static void executerRequeteSansRetour(final String requeteSQL) throws UpdateException{
		/*String reqVerif=requeteSQL.toLowerCase().replaceAll("{2,}"," ");
		if (reqVerif.contains("drop table")){
			throw new MethodeDeRequeteErroneeException("Veuillez la fonction supprimerTable pour cela");
		}
		else if (reqVerif.contains("create table")){
			throw new MethodeDeRequeteErroneeException("Veuillez la fonction creerTable pour cela");
		}else{*/
			if(!estConnecte){try{connecter();}catch(ConnectionException e){e.printStackTrace();}}
			SQLViaJDBC.executerRequeteSansRetour(requeteSQL);
		//}
	}
	
	public static ResultSet executerRequeteAvecRetour(final String requeteSQL) throws QueryException {
		System.out.println("iiiiiiiiiiiiiiiiiiiiii " +estConnecte);
		if(!estConnecte){try{connecter();}catch(ConnectionException e){e.printStackTrace();}}
		return SQLViaJDBC.executerRequeteAvecRetour(requeteSQL);
	}
	
	public static void commit() throws TransactionException {
		if(!estConnecte){try{connecter();}catch(ConnectionException e){e.printStackTrace();}}
		SQLViaJDBC.commit();
	}
	
	public static void rollBack() throws TransactionException {
		if(!estConnecte){try{connecter();}catch(ConnectionException e){e.printStackTrace();}}
		SQLViaJDBC.rollBack();
	}
}