package formulaire.recherche.controleurOLD;

public abstract class Recherche {
	public String getAdresseRequete(String type, String motClef){
		motClef.replace(" ","_");
		return "http://ws.audioscrobbler.com/2.0/?method=".concat(type).concat(".search&").concat(type).concat("=").concat(motClef).concat("&api_key=25da7440d0a2aeea12ee03316d582abd");
	}
}
