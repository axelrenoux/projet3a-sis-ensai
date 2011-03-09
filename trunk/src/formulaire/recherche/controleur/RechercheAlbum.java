package formulaire.recherche.controleur;

public class RechercheAlbum extends Recherche {
	public String getAdresseRequete(String motClef){
		return getAdresseRequete("album",motClef);
	}
}
