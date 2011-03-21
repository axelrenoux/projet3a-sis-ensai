package bdd.rechercheBDD;

import metier.Tag;
import metier.oeuvres.Album;
import metier.oeuvres.Artiste;
import metier.oeuvres.Chanson;

public class ConditionsRechercherEtendu {
	private String clesChanson=null;
	private String clesAlbum=null;
	private String clesArtiste=null;
	private String clesTag=null;
	private String motClef;
	static final ConditionsRechercherEtendu instanceUnique=new ConditionsRechercherEtendu();
	
	private static void init(String motClef){
		instanceUnique.setMotClef(motClef);
		instanceUnique.setClesChanson((new Chanson()).preRecherchePrFctRapprochement(motClef));
		instanceUnique.setClesArtiste((new Artiste()).preRecherchePrFctRapprochement(motClef));
		instanceUnique.setClesAlbum((new Album()).preRecherchePrFctRapprochement(motClef));;
		instanceUnique.setClesTag((new Tag()).preRecherchePrFctRapprochement(motClef));
	}

	public static String getClesChanson(String motClef) {
		if(instanceUnique.getClesChanson()==null||instanceUnique.getMotClef()!=motClef){
			init(motClef);
		}
		return instanceUnique.getClesChanson();
	}

	public static String getClesAlbum(String motClef) {
		if(instanceUnique.getClesAlbum()==null||instanceUnique.getMotClef()!=motClef){
			init(motClef);
		}
		return instanceUnique.getClesAlbum();
	}

	public static String getClesArtiste(String motClef) {
		if(instanceUnique.getClesArtiste()==null||instanceUnique.getMotClef()!=motClef){
			init(motClef);
		}
		return instanceUnique.getClesChanson();
	}

	public static String getClesTag(String motClef) {
		if(instanceUnique.getClesTag()==null||instanceUnique.getMotClef()!=motClef){
			init(motClef);
		}
		return instanceUnique.getClesTag();
	}
	
	private String getClesChanson() {
		return clesChanson;
	}

	private String getClesAlbum() {
		return clesAlbum;
	}

	private String getClesArtiste() {
		return clesArtiste;
	}

	private String getClesTag() {
		return clesTag;
	}
	
	private String getMotClef() {
		return motClef;
	}

	private void setClesChanson(String clesChanson) {
		this.clesChanson = clesChanson;
	}

	private void setClesAlbum(String clesAlbum) {
		this.clesAlbum = clesAlbum;
	}

	private void setClesArtiste(String clesArtiste) {
		this.clesArtiste = clesArtiste;
	}

	private void setClesTag(String clesTag) {
		this.clesTag = clesTag;
	}

	private void setMotClef(String motClef) {
		this.motClef = motClef;
	}
}
