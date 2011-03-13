package formulaire.reponses.gestion;

public abstract class GestionRecherche {
	
	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/
	
	private String artist=null;
	private String album=null;
	private String track=null;
	private String tag=null;
	private String motCle=null;

	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/

	public void lancerRecherche() {
		//TODO
	}
	
	
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
	

	

	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	
}
