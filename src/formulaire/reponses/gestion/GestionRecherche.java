package formulaire.reponses.gestion;

public abstract class GestionRecherche {
	String artist=null;
	String album=null;
	String track=null;

	public void addTrack(String track, boolean aVerifier) {
		this.track=track;
	}

	public void addArtist(String artist, boolean aVerifier) {
		this.artist=artist;
	}

	public void addAlbum(String album, boolean aVerifier) {
		this.album=album;
	}

	public void lancerRecherche() {
		
	}
	
}
