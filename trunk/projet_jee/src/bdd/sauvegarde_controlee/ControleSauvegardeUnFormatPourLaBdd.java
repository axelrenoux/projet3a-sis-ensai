package bdd.sauvegarde_controlee;

import java.sql.Date;

import bdd.chargement.ChargementEtControleUrlExistantes;

import controleur.UtilitaireDate;
import exceptions.UrlReserveeException;


/**
 * Par défaut, toutes les méthodes publiques retourneront la méthode correspondante de la SauvegardeUnFormatPourLaBdd correspondante 
 */
public abstract class ControleSauvegardeUnFormatPourLaBdd {
	private SauvegardeUnFormatPourLaBdd sauveur;
	
	public ControleSauvegardeUnFormatPourLaBdd() {
	}
	
	public String controle(String s){
		if(s==null){
			return "''";
		}else{
			return s;
		}
	}
	
	public String controle(Date d){
		if(d==null){
			return "''";
		}else{
			return UtilitaireDate.getInstanceunique().transformeEnString(d);
		}
	}
	
	public String controle(Integer i) {
		if(i==null){
			return "''";
		}else{	
			return i.toString();
		}
	}
	
	public String controle(Double d) {
		if(d==null){
			return "''";
		}else{	
			return d.toString();
		}
	}
	
	public String controle(Float f) {
		if(f==null){
			return "''";
		}else{	
			return f.toString();
		}
	}
	
	public void creerTables(){
		sauveur.creerTables();
	}

	public void ajouterLigne(String ligne){
		sauveur.ajouterLigne(controle(ligne));
	}
	
	public void ecrireEnTete(boolean lesTablesExistent){
		sauveur.ecrireEnTete(lesTablesExistent);
	}

	public void ecrireConclusion(){
		sauveur.ecrireConclusion();
	}

	public void sauverWiki(int pk, 
							String id_wiki, 
							Date datePublication,
							String resume,
							String contenu){
		sauveur.sauverWiki(controle(pk),controle(id_wiki),controle(datePublication),controle(resume),controle(contenu));
	}

	public void sauverAudimat(int pk, 
									double listeners,
									double playcount){
		sauveur.sauverAudimat(controle(pk),controle(listeners),controle(playcount));
	}

	public void sauverArtiste(int coord_artiste,
									int pkImagesCetArtiste, 
									int pkAudimatCetArtiste,
									int pkWikiCetArtiste) {
		sauveur.sauverArtiste(controle(coord_artiste),controle(pkImagesCetArtiste),controle(pkAudimatCetArtiste),controle(pkWikiCetArtiste));
	}

	public void sauverImages(int pkImagesCetArtiste,
									String imageSmall,
									String imageMedium, 
									String imageLarge, 
									String imageExtraLarge,
									String imageMega){
		sauveur.sauverImages(controle(pkImagesCetArtiste),controle(imageSmall),controle(imageMedium),controle(imageLarge),controle(imageExtraLarge),controle(imageMega));
	}

	public void sauverSimilartist(Integer artiste1, Integer artiste2){
		sauveur.sauverSimilartist(controle(artiste1),controle(artiste2));
	}

	public void sauverChanson(int coord_chanson,
									Double duree, 
									int pkImages, 
									int pkAudimat, 
									int pkWiki,
									int pkArtiste){
		sauveur.sauverChanson(controle(coord_chanson),controle(duree),controle(pkImages),controle(pkAudimat),controle(pkWiki),controle(pkArtiste));
	}

	public void sauverArtisteTag(Integer artiste, Integer tag){
		sauveur.sauverArtisteTag(controle(artiste),controle(tag));
	}

	public void sauverChansonTag(Integer chanson, Integer tag){
		sauveur.sauverChansonTag(controle(chanson),controle(tag));
	}

	public void sauverTag(int coord_tag,
									Double reach,
									Double tagging,
									int pkWiki){
		sauveur.sauverTag(controle(coord_tag),controle(reach),controle(tagging),controle(pkWiki));
	}

	public void sauverCoord(int pk, 
									String id, 
									String name, 
									String url) throws UrlReserveeException{
		ChargementEtControleUrlExistantes.registerUrl(url,pk);
		sauveur.sauverCoord(controle(pk),controle(id),controle(name),controle(url));
	}

	public void sauverChansonAlbum(Integer album, Integer chanson){
		sauveur.sauverChansonAlbum(controle(album),controle(chanson));
	}

	public void sauverAlbum(int coord_album, 
									Date date_sortie,
									int pkImages, 
									int pkAudimat, 
									int pkWiki, 
									Integer artiste){
		sauveur.sauverAlbum(controle(coord_album),controle(date_sortie),controle(pkImages),controle(pkAudimat),controle(pkWiki),controle(artiste));
	}

	public SauvegardeUnFormatPourLaBdd getSauveur() {
		return sauveur;
	}

	public void setSauveur(SauvegardeUnFormatPourLaBdd sauveur) {
		this.sauveur = sauveur;
	}

	public abstract int getNbObsExistantes(boolean recreerLesTables);
	
	public abstract void setNbObsExistantes(int primarykey);

	public void sauverAlbumTag(Integer album, Integer tag){
		sauveur.sauverAlbumTag(controle(album),controle(tag));
	}
}