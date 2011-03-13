package formulaire.reponses.gestion;

public abstract class GestionRecherche {
	String artist=null;
	String album=null;
	String track=null;
	String tag=null;
	String motCle=null;

	public void addTrack(String track) {
		this.track=track;
		//TODO
	}

	public void addArtist(String artist) {
		this.artist=artist;
		//TODO
	}

	public void addAlbum(String album) {
		this.album=album;
		//TODO
	}

	public void addMotCle(String motCle) {
		this.motCle=motCle;
		//TODO
	}
	public void addTag(String tag) {
		this.tag=tag;
		//TODO
	}
	
	public void lancerRecherche() {
		//TODO
	}
	
}
