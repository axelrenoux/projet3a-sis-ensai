package metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bdd.sqlviajdbc.ControlAccesSQLViaJDBC;

import comparaison.ComparaisonUtilisee;
import comparaison.FonctionDeRapprochement;
import exceptions.QueryException;

public abstract class ObjetAComparer {
	private FonctionDeRapprochement fonctionDeRapprochement;
	
	public ObjetAComparer(FonctionDeRapprochement fonctionDeRapprochement){
		super();
		this.fonctionDeRapprochement=fonctionDeRapprochement;
	}
	
	public ObjetAComparer(){
		this(new ComparaisonUtilisee());
	}
	
	public List<ObjetAComparer> getDoublonsProbables() {
		List<ObjetAComparer> listeSimilaires=new ArrayList<ObjetAComparer>();
		for(ObjetAComparer candidat:getObjetsDeCeType()){
			if(fonctionDeRapprochement.sontSimilaires(this, candidat)){
				listeSimilaires.add(candidat);
			}
		}
		return listeSimilaires;
	}

	public abstract Collection<? extends ObjetAComparer> getObjetsDeCeType();
	
	protected abstract String getNomTableBDD();

	private Map<String,Integer> getNomEtClefObjetsDeCeType() {
		Map<String, Integer> nomObjets = new HashMap<String,Integer>();
		String recherche=
			"SELECT DISTINCT " +
			"inu.name as name , " +
			"inu.cle_primaire as clef " +
			" FROM "+getNomTableBDD()+" obj , ID_NAME_URL inu "+
			" WHERE obj.id_name_url = inu.cle_primaire";
		try {
			ResultSet resultat = ControlAccesSQLViaJDBC.executerRequeteAvecRetour(recherche);
			while(resultat.next()){
				nomObjets.put(resultat.getString("name"),resultat.getInt("clef"));
			}
		} catch (QueryException e) {e.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
		return nomObjets;
	}
		
	public String preRecherchePrFctRapprochement(String motClef){
		String conditionsDuSelectIn="0";//Clef qui n'est pas attribuée
		Map<String,Integer> annuaire=getNomEtClefObjetsDeCeType();
		for(String nomCandidat:annuaire.keySet()){
			if(fonctionDeRapprochement.sontSimilaires(motClef,nomCandidat)){
				conditionsDuSelectIn=conditionsDuSelectIn+" , "+annuaire.get(nomCandidat);
			}
		}
		return conditionsDuSelectIn;
	}

	public abstract String getName();
}
