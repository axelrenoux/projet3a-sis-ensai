package bdd.rechercheBDD;




import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import oracle.jdbc.pool.OracleDataSource;

public class Essai {

	static private Connection c;
	static private String idUtilisateur;
	static private String motPasse;

	// avec un constructeur "private"
	// comme la Connection est static private, on ne pourra avoir qu'une seule connexion à la fois
	// le constructeur ne pourra être appelé en dehors de la classe
	// ici on ne fait pas appel au constructeur dans la classe, elle n'est donc pas instanciable
	
	private Essai(){

	}


	/**
	 * Connexion au serveur Oracle.
	 * @throws ClassNotFoundException
	 */
	
	private static void connecter() throws ClassNotFoundException{
		// création d'une connexion à une base de données		

		
		String url = ResourceBundle.getBundle("main.parametre").getString("parisEnLigne.driverBaseDeDonnees");
		try {
			// Create a OracleDataSource instance and set properties
			OracleDataSource ods = new OracleDataSource();
			ods.setUser(idUtilisateur);
			ods.setPassword(motPasse);
			ods.setURL(url);


			// Connect to the database
			c = ods.getConnection();
			c.setAutoCommit(false);
		}
		catch (SQLException e){
			System.out.println("Echec de la tentative de connexion : " + e.getMessage());
		}

	}

	/**
	 * Fermeture de la connexion
	 */	
	public static void fermer() throws SQLException{
		c.close();
	}

	/**
	 * Ouverture de la connexion
	 * @throws SQLException, ClassNotFoundException
	 */	
	public static void ouvrir() throws SQLException,ClassNotFoundException{
		try  {
			if (c.isClosed()){
				// ce n'est pas la 1ere connexion au cours du programme : la connexion precedente a ete fermee
				connecter();
			}
		}
		catch (NullPointerException e){
			// c'est  la 1ere connexion a la BD au cours du programme
			// demander identifiant et mot de passe ici...
			connecter();
		}
	}


	/**
	 * Création d'instructions (statements) - redéfinition de la méthode
	 * 
	 * @return st l'instruction créée
	 */
	public static Statement createStatement(){
		Statement st = null;
		try{
			st = c.createStatement();
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative de création d'instruction : " + e.getMessage());
		}
		return st;
	}

	
	//getters and setters
	
	public static Connection getC(){
		return c;
	}

	public static void setIdUtilisateur(String idU){
		idUtilisateur = idU;
	}

	public static void setMotDePasse(String mdp){
		motPasse = mdp;
	}

}
