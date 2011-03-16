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
	
	//Constructeur
	public ControlAccesSQLViaJDBC() {}

	public static void connecter() throws ConnectionException{
		SQLViaJDBC.connecter();
	}
	
	public static void fermerBDD() throws ConnectionException{
		SQLViaJDBC.fermerBDD();
	}
	/**
	 * On aurait voulu forcer l'utilisateur à passer par des méthodes spécifiques pour créer ou supprimer des tables
	 * Mais cela pose des problèmes pour le chargement par fichier texte. Donc on oublie.
	 * 
	 * @param requeteSQL
	 * @throws UpdateException
	 * @throws MethodeDeRequeteErroneeException
	 */
	public static void executerRequeteSansRetour(String requeteSQL) throws UpdateException{
		/*String reqVerif=requeteSQL.toLowerCase().replaceAll("{2,}"," ");
		if (reqVerif.contains("drop table")){
			throw new MethodeDeRequeteErroneeException("Veuillez la fonction supprimerTable pour cela");
		}
		else if (reqVerif.contains("create table")){
			throw new MethodeDeRequeteErroneeException("Veuillez la fonction creerTable pour cela");
		}else{*/
			SQLViaJDBC.executerRequeteSansRetour(requeteSQL);
		//}
	}
	
	public static ResultSet executerRequeteAvecRetour(final String requeteSQL) throws QueryException {
		return SQLViaJDBC.executerRequeteAvecRetour(requeteSQL);
	}
	
	public static void commit() throws TransactionException {
		SQLViaJDBC.commit();
	}
	
	public static void rollBack() throws TransactionException {
		SQLViaJDBC.rollBack();
	}
}