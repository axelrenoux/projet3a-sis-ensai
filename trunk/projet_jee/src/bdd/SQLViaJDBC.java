package bdd;


/**
 * -Gère les connexions et les transactions
 * -Toutes les requetes passent par ici
 * -C'est par elle qu'il faut passer pour :
 * 			creer les tables, 
 * 			les supprimer, 
 * 			récupérer les noms de colonne
 * -Oracle MySQL€
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import bdd.exceptions.CanalException;
import bdd.exceptions.ChargementException;
import bdd.exceptions.ConnectionException;
import bdd.exceptions.CreationTableException;
import bdd.exceptions.DescriptionTableException;
import bdd.exceptions.QueryException;
import bdd.exceptions.SuppressionTableException;
import bdd.exceptions.TransactionException;
import bdd.exceptions.UpdateException;

public class SQLViaJDBC{
	//attributs
	private static Connection connection;
	private static Statement instruction;
	private static String userIDAcessBDD;
	private static String passWordAccess;
	private static Map<String,Statement> listeCanaux;
	
	//Constructeur
	private SQLViaJDBC() {
		
	}

	/**
	 * Connecte l'application à Oracle avec un utilisateur Oracle par défaut
	 * @throws ConnectionException
	 */
	public static void connecter() throws ConnectionException{
		//Class.forName("com.mysql.jdbc.Driver").newInstance();
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e1) {
			throw new ConnectionException(e1);
		}
		userIDAcessBDD = "id2853";
		passWordAccess = "id2853";
		try {
			//connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
			//connection = DriverManager.getConnection("jdbc:oracle:thin:id2954/id2954@//localhost:1521/XE");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@oraens10g:1521:ORAENS",userIDAcessBDD,passWordAccess);
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
	 * Connecte l'application à Oracle avec un utilisateur donné
	 * @param id
	 * @param mdp
	 * @throws ConnectionException
	 */
	public static void connecter(String id, String mdp) throws ConnectionException {
		try {
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			throw new ConnectionException(e);
		}
		try {
			//connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
			//connection = DriverManager.getConnection("jdbc:oracle:thin:id2954/id2954@//localhost:1521/XE");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@oraens10g:1521:ORAENS",id,mdp);
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
	public static void fermerBDD() throws ConnectionException{
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
	public static void executerRequeteSansRetour(final String requeteSQL) throws UpdateException{
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
	public static ResultSet executerRequeteAvecRetour(final String requeteSQL) throws QueryException {
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
	 */
	
	public static void ouvrirInstruction(String canal) throws CanalException {
		try {
			listeCanaux.put(canal, connection.createStatement());
		} catch (SQLException e) {
			throw new CanalException(e);
		}
	}
	
	/**
	 * Ferme une instruction
	 * @param canal
	 */
	
	public static void fermerInstruction(String canal) {
		listeCanaux.remove(canal);
	}
	
	/**
	 * Execute une requete sans retour sur l'instruction donnée en paramètre
	 * @param requeteSQL
	 * @param canal
	 * @throws UpdateException
	 * @see executerRequeteAvecRetour(final String requeteSQL)
	 */
	public static void executerRequeteSansRetour(final String requeteSQL, String canal) throws UpdateException {
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
	 */
	public static ResultSet executerRequeteAvecRetour(final String requeteSQL, String canal) throws QueryException {
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
	public static void commit() throws TransactionException {
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
	public static void rollBack() throws TransactionException {
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
	 * XXX Variables séparées par des virgules
	 */
	public static void creerTable(String nomTable, String variables) throws CreationTableException {
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
	 */
	public static void supprimerTable(String nomTable) throws SuppressionTableException {
		try {
			executerRequeteSansRetour("DROP TABLE " + nomTable);
		} catch (UpdateException e) {
			throw new SuppressionTableException(nomTable);
		}
	}
	
	/**
	 * Décrit les colonnes d'une table
	 * @param nomTable
	 * @return
	 * @throws CanalException
	 * @throws ChargementException
	 * @throws DescriptionTableException
	 */
	public static List<String> obtenirColonnes(String nomTable) throws CanalException, ChargementException, DescriptionTableException {
		String canal = "canalObtenirColonnes";
		ouvrirInstruction(canal);
		//String nomVue = "vueColonnes";
		//executerRequeteSansRetour("CREATE VIEW " + nomVue + " AS Select * From DESCRIBE " + nomTable, canal);
		ResultSet resultat;
		try {
			//resultat = executerRequeteAvecRetour("SHOW COLUMNS from " + nomTable, canal);
			resultat = executerRequeteAvecRetour("Select COLUMN_NAME from USER_TAB_COLUMNS where TABLE_NAME = '" + nomTable +"'", canal);
		} catch (QueryException e1) {
			throw new DescriptionTableException(e1);
		}
		//ResultSet resultat = executerRequeteAvecRetour("SELECT * FROM " + nomVue);
		List<String> listeColonnes = new LinkedList<String>();
		try {
			while(resultat.next()){
				listeColonnes.add(resultat.getString(1));
			}
		} catch (SQLException e) {
			throw new DescriptionTableException(e);
		}
		if(listeColonnes.isEmpty()){
			throw new DescriptionTableException();
		}
		fermerInstruction(canal);
		return listeColonnes;
		
		/*
		LinkedList<String> liste = new LinkedList<String>();
		liste.add("hjkhlkh");
		liste.add("hjkhlkh2");
		liste.add("hjkhlkh3");
		liste.add("hjkhlkh4");
		return liste;
		*/
	}
}