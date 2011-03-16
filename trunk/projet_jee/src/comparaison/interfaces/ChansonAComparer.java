package comparaison.interfaces;

import java.util.ArrayList;
import java.util.List;

import comparaison.ComparaisonAlbumChanson;
import comparaison.FonctionDeRapprochement;
import controleur.Controleur;

import metier.Chanson;

public class ChansonAComparer extends Chanson implements ObjetAComparE{
	private FonctionDeRapprochement fonctionDeRapprochement=new ComparaisonAlbumChanson();

	/**
	 * La seule méthode ici qui doive être appelée de l'extérieur
	 */
	@Override
	public List<? extends ObjetAComparE> listeSimilaires(double seuil) {
		List<ChansonAComparer> listeSimilaires=new ArrayList<ChansonAComparer>();
		for(ObjetAComparE candidat:listeObjetsCeType()){
			if(fonctionDeRapprochement.appliquer(this, candidat)>seuil){
				listeSimilaires.add((ChansonAComparer) candidat);
			}
		}
		return listeSimilaires;
	}

	@Override
	public List<? extends ObjetAComparE> listeObjetsCeType() {
		List<ChansonAComparer> listeObjetsCeType=new ArrayList<ChansonAComparer>();
		for(Chanson candidat:Controleur.getInstanceuniquecontroleur().getListeChansons().values()){
			listeObjetsCeType.add((ChansonAComparer) candidat);
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
