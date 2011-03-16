package comparaison.interfaces;

import java.util.ArrayList;
import java.util.List;

import comparaison.ComparaisonArtisteTag;
import comparaison.FonctionDeRapprochement;
import controleur.Controleur;

import metier.Tag;

public class TagAComparer extends Tag implements ObjetAComparE{
	private FonctionDeRapprochement fonctionDeRapprochement=new ComparaisonArtisteTag();

	/**
	 * La seule méthode ici qui doive être appelée de l'extérieur
	 */
	@Override
	public List<? extends ObjetAComparE> listeSimilaires(double seuil) {
		List<TagAComparer> listeSimilaires=new ArrayList<TagAComparer>();
		for(ObjetAComparE candidat:listeObjetsCeType()){
			if(fonctionDeRapprochement.appliquer(this, candidat)>seuil){
				listeSimilaires.add((TagAComparer) candidat);
			}
		}
		return listeSimilaires;
	}

	@Override
	public List<? extends ObjetAComparE> listeObjetsCeType() {
		List<TagAComparer> listeObjetsCeType=new ArrayList<TagAComparer>();
		for(Tag candidat:Controleur.getInstanceuniquecontroleur().getListeTags().values()){
			listeObjetsCeType.add((TagAComparer) candidat);
		}
		return listeObjetsCeType;
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void setFonctionDeRapprochement(FonctionDeRapprochement fonctionDeRapprochement) {
		this.fonctionDeRapprochement = fonctionDeRapprochement;
	}

	@Override
	public FonctionDeRapprochement getFonctionDeRapprochement() {
		return fonctionDeRapprochement;
	}
}
