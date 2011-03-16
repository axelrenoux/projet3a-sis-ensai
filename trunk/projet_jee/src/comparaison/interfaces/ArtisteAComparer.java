package comparaison.interfaces;

import java.util.ArrayList;
import java.util.List;

import comparaison.ComparaisonArtisteTag;
import comparaison.FonctionDeRapprochement;
import controleur.Controleur;

import metier.Artiste;

public class ArtisteAComparer extends Artiste implements ObjetAComparE{
	private FonctionDeRapprochement fonctionDeRapprochement=new ComparaisonArtisteTag();

	/**
	 * La seule méthode ici qui doive être appelée de l'extérieur
	 */
	@Override
	public List<? extends ObjetAComparE> listeSimilaires(double seuil) {
		List<ArtisteAComparer> listeSimilaires=new ArrayList<ArtisteAComparer>();
		for(ObjetAComparE candidat:listeObjetsCeType()){
			if(fonctionDeRapprochement.appliquer(this, candidat)>seuil){
				listeSimilaires.add((ArtisteAComparer) candidat);
			}
		}
		return listeSimilaires;
	}

	@Override
	public List<? extends ObjetAComparE> listeObjetsCeType() {
		List<ArtisteAComparer> listeObjetsCeType=new ArrayList<ArtisteAComparer>();
		for(Artiste candidat:Controleur.getInstanceuniquecontroleur().getListeArtistes().values()){
			listeObjetsCeType.add((ArtisteAComparer) candidat);
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
