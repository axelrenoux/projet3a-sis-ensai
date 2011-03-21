package bdd.sqlviajdbc;


/**
 * -Gère les connexions et les transactions
 * -Toutes les requetes passent par ici
 * -C'est par elle qu'il faut passer pour :
 * 			creer les tables, 
 * 			les supprimer, 
 * 			récupérer les noms de colonne
 * -Oracle 10g
 * Cf fichier de documentation dans les download du google code
 * 
 * @warning : afin de controler l'acces à la BDD, 
 * la classe et ses methodes ne sont accessibles que depuis le package bdd.sqlviajdbc
 * ce package ne doit rien contenir d'autre que ControlAccesSQLViaJDBC
 * toutes les requetes devront donc transiter par cette classe
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import exceptions.CanalException;
import exceptions.ConnectionException;
import exceptions.CreationTableException;
import exceptions.QueryException;
import exceptions.SuppressionTableException;
import exceptions.TransactionException;
import exceptions.UpdateException;

class SQLViaJDBC{
	//attributs
	private static Connection connection;
	private static Statement instruction;
	private static String userIDAcessBDD;
	private static String passWordAccess;
	private static String serveurBDD="jdbc:oracle:thin:@localhost:1521:XE";
	private static Map<String,Statement> listeCanaux;
	
	//Constructeur
	protected SQLViaJDBC() {
		
	}

	/**
	 * Connecte l'application à Oracle avec un utilisateur Oracle par défaut
	 * @throws ConnectionException
	 */
	protected static void connecter() throws ConnectionException{
		System.out.println("connecter()");
		userIDAcessBDD = "id2853";
		passWordAccess = "id2853";
		connecter(userIDAcessBDD,passWordAccess);
	}
	
	/**
	 * Connecte l'application à Oracle avec un utilisateur donné
	 * @param id
	 * @param mdp
	 * @throws ConnectionException
	 */
	protected static void connecter(String id, String mdp) throws ConnectionException {
		try {
			//TODO plante ici depuis j2EE
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		} catch (SQLException e) {
			throw new ConnectionException(e);
		}
		try {
			connection = DriverManager.getConnection(serveurBDD,id,mdp);
		} catch (SQLException e) {
			throw new ConnectionException(e);
		}
		try {
			instruction=connection.createStatement();
			
		} catch (SQLException e) {
			throw new ConnectionException(e);
		}
		listeCanaux = new HashMap<String, Statement>();
	}
	/**
	 * Ferme la connection
	 * @throws ConnectionException
	 */
	protected static void fermerBDD() throws ConnectionException{
		for(String s : listeCanaux.keySet()){
			listeCanaux.remove(s);
		}
		try {
			instruction.close();
			connection.close();
		} catch (SQLException e) {
			throw new ConnectionException(e);
		}
	}
	
	/**
	 * Execute une requete sans retour sur l'instruction par défaut
	 * @param requeteSQL
	 * @throws UpdateException
	 * @see executerRequeteSansRetour(final String requeteSQL, String canal)
	 */
	protected static void executerRequeteSansRetour(final String requeteSQL) throws UpdateException{
		System.out.println(requeteSQL);
		try {
			instruction.executeUpdate(requeteSQL);
		} catch (SQLException e) {
			throw new UpdateException(e);
		}
	}
	
	/**
	 * Execute une requete avec retour sur l'instruction par défaut
	 * @param requeteSQL
	 * @return
	 * @throws QueryException
	 * @see executerRequeteAvecRetour(final String requeteSQL, String canal)
	 */
	protected static ResultSet executerRequeteAvecRetour(final String requeteSQL) throws QueryException {
		ResultSet resultat = null;
		System.out.println(requeteSQL);
		try {
			resultat = instruction.executeQuery(requeteSQL);
		} catch (SQLException e) {
			throw new QueryException(e);
		}
		return resultat;
	}
	
	/**
	 * Ouvre une nouvelle instruction
	 * @param canal
	 * @throws CanalException
	 * 
	 * @deprecated
	 */
	
	protected static void ouvrirInstruction(String canal) throws CanalException {
		try {
			listeCanaux.put(canal, connection.createStatement());
		} catch (SQLException e) {
			throw new CanalException(e);
		}
	}
	
	/**
	 * Ferme une instruction
	 * @param canal
	 * 
	 * @deprecated
	 */
	
	protected static void fermerInstruction(String canal) {
		listeCanaux.remove(canal);
	}
	
	/**
	 * Execute une requete sans retour sur l'instruction donnée en paramètre
	 * @param requeteSQL
	 * @param canal
	 * @throws UpdateException
	 * @see executerRequeteAvecRetour(final String requeteSQL)
	 * 
	 * @deprecated
	 */
	protected static void executerRequeteSansRetour(final String requeteSQL, String canal) throws UpdateException {
		System.out.println(requeteSQL);
		try {
			listeCanaux.get(canal).executeUpdate(requeteSQL);
		} catch (SQLException e) {
			throw new UpdateException(e);
		}
	}
	
	/**
	 * Execute une requete avec retour sur l'instruction donnée en paramètre
	 * @param requeteSQL
	 * @param canal
	 * @return
	 * @throws QueryException
	 * @see executerRequeteAvecRetour(final String requeteSQL)
	 * 
	 * @deprecated
	 */
	protected static ResultSet executerRequeteAvecRetour(final String requeteSQL, String canal) throws QueryException {
		System.out.println(requeteSQL);
		ResultSet resultat = null;
		try {
			resultat = listeCanaux.get(canal).executeQuery(requeteSQL);
		} catch (SQLException e) {
			throw new QueryException(e);
		}
		return resultat;
	}
	
	/**
	 * Effectue un commit
	 * @throws TransactionException
	 */
	protected static void commit() throws TransactionException {
		try {
			executerRequeteSansRetour("COMMIT");
		} catch (UpdateException e) {
			throw new TransactionException(e);
		}
	}
	
	/**
	 * Effectue un rollback
	 * @throws TransactionException
	 */
	protected static void rollBack() throws TransactionException {
		try {
			executerRequeteSansRetour("ROLLBACK");
		} catch (UpdateException e) {
			throw new TransactionException(e);
		}
	}
	
	/**
	 * Crée une table dans le fichier de configuration
	 * @param nomTable
	 * @param variables
	 * @throws CreationTableException
	 * 
	 * @deprecated
	 */
	protected static void creerTable(String nomTable, String variables) throws CreationTableException {
		try {
			executerRequeteSansRetour("CREATE TABLE " + nomTable + " ( " + variables + " )");
		} catch (UpdateException e) {
			throw new CreationTableException(e);
		}
	}
	
	/**
	 * Supprime une table du fichier de configuration
	 * @param nomTable
	 * @throws SuppressionTableException
	 * 
	 * @deprecated
	 */
	protected static void supprimerTable(String nomTable) throws SuppressionTableException {
		try {
			executerRequeteSansRetour("DROP TABLE " + nomTable);
		} catch (UpdateException e) {
			throw new SuppressionTableException(nomTable);
		}
	}
}