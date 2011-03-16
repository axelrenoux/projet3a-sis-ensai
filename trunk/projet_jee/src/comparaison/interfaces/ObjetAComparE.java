package comparaison.interfaces;

import java.util.Collection;
import java.util.List;

import comparaison.FonctionDeRapprochement;

public interface ObjetAComparE {
	public List<? extends ObjetAComparE> listeSimilaires(double seuil);
	
	public Collection<? extends ObjetAComparE> listeObjetsCeType();

	public String getName();
	
	public void setFonctionDeRapprochement(FonctionDeRapprochement fonctionDeRapprochement);
	
	public FonctionDeRapprochement getFonctionDeRapprochement();
}
