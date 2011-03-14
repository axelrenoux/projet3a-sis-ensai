package formulaire.recherche.controleurOLD;

public class RechercheArtiste extends Recherche {
	public String getAdresseRequete(String motClef){
		return getAdresseRequete("artist",motClef);
	}
}
