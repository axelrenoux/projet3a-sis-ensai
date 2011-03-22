package main;

import metier.Cluster;
import rechercheParFormulaire.gestionRecherche.RechercheAlbum;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*System.out.println(Controleur.getInstanceuniquecontroleur().
				getListeAlbums().get("http://www.last.fm/music/Lunabee/Prenez+garde+aux+flots+bleus"));*/
		
		//test pour la creation des axes
		
		//RechercheAlbum r = new RechercheAlbum();
		//Cluster c = r.lancerRecherche("a");
		System.out.println("***************************");
		//System.out.println(c);
		//System.out.println(c.varianceCluster());
		//ChargementBDDOracleDepuisTxt.charger();
		
		//RechercheArtisteBDD reach = new RechercheArtisteBDD("cher");
		//System.out.println(reach.getAlbums());
		
		
		/*ArrayList<Album> c=null;
		RechercheAlbumBDD mc = RechercheAlbumBDD.getInstance();
		try {
			c = mc.rechercherAlbums("Complete New English Hymnal Vol. 13");
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(c);
		System.out.println(c.size());*/
		
		/*ArrayList<Artiste> ar=null;
		RechercheArtisteBDD mar = RechercheArtisteBDD.getInstance();
		try {
			ar = mar.rechercherArtistes("re");
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ar);
		System.out.println(ar.size());*/
		
		RechercheAlbum r = new RechercheAlbum();
		Cluster c = r.lancerRecherche("LIVE");

		/*ArrayList<Chanson> ch=null;
		RechercheChansonBDD mcc = RechercheChansonBDD.getInstance();
		try {
			ch = mcc.rechercherChansons("mar");
		} catch (ChargementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ch);
		System.out.println(ch.size());*/
		
		/*for (Album ch:c){
			if (ch.getDate()!=null){
				System.out.println(ch.getDate());
				System.out.println(ch.getDate().getDate() + " " +
						ch.getDate().getMonth()+ " " + 
						(ch.getDate().getYear()+1900));
			}
		}*/
		
	}


}
