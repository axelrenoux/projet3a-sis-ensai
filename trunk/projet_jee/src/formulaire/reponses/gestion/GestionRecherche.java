package formulaire.reponses.gestion;

public abstract class GestionRecherche {
	String artist=null;
	String album=null;
	String track=null;

	public void addTrack(String track, boolean aVerifier) {
		this.track=track;
		//TODO
	}

	public void addArtist(String artist, boolean aVerifier) {
		this.artist=artist;
		//TODO
	}

	public void addAlbum(String album, boolean aVerifier) {
		this.album=album;
		//TODO
	}

	public void lancerRecherche() {
		//TODO
	}
	
}
