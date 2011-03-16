package comparaison.interfaces;

import java.util.ArrayList;
import java.util.List;

import comparaison.ComparaisonAlbumChanson;
import comparaison.FonctionDeRapprochement;
import controleur.Controleur;

import metier.Album;

public class AlbumAComparer extends Album implements ObjetAComparE{
	private FonctionDeRapprochement fonctionDeRapprochement=new ComparaisonAlbumChanson();

	/**
	 * La seule méthode ici qui doive être appelée de l'extérieur
	 */
	@Override
	public List<? extends ObjetAComparE> listeSimilaires(double seuil) {
		List<AlbumAComparer> listeSimilaires=new ArrayList<AlbumAComparer>();
		for(ObjetAComparE candidat:listeObjetsCeType()){
			if(fonctionDeRapprochement.appliquer(this, candidat)>seuil){
				listeSimilaires.add((AlbumAComparer) candidat);
			}
		}
		return listeSimilaires;
	}

	@Override
	public List<? extends ObjetAComparE> listeObjetsCeType() {
		List<AlbumAComparer> listeObjetsCeType=new ArrayList<AlbumAComparer>();
		for(Album candidat:Controleur.getInstanceuniquecontroleur().getListeAlbums().values()){
			listeObjetsCeType.add((AlbumAComparer) candidat);
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
