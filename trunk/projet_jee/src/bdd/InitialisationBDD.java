package bdd;

import bdd.exceptions.CreationTableException;





public class InitialisationBDD {

	/**
	 * Initialise les fichiers de configuration
	 */
	public static void initialiserTables() {
		try {
			SQLViaJDBC.creerTable("TYPES_DONNEES_PRINCIPAUX", "id_type_donnees_principal VARCHAR(32), NOM_TYPE_DONNEES_PRINCIPAL VARCHAR(32), " +
						"nom_methode_de_rapprochement VARCHAR(32)," +
						"constraint pk_TYPES_DONNEES_PRINCIPAUX primary key (id_type_donnees_principal)");
		} catch (CreationTableException e) {
			e.printStackTrace();
		}
	}
}
