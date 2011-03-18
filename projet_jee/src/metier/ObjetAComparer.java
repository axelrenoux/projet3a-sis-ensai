package metier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import comparaison.ComparaisonUtilisee;
import comparaison.FonctionDeRapprochement;

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

	public abstract String getName();
}
