package formulaire.recherche.controleur;

public class RechercheChanson extends Recherche {
	public String getAdresseRequete(String motClef){
		return getAdresseRequete("track",motClef);
	}
}