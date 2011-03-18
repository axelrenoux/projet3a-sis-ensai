package comparaison;

import java.util.LinkedList;

import exceptions.ChaineVideException;
import exceptions.JaroException;

public class ComparaisonArtisteTag extends FonctionDeRapprochement {
	
	@Override
	protected float appliquer(String chaine1, String chaine2) throws JaroException {		
		try{
			LinkedList<String> listeMots1 = UtilitaireMethodesDeComparaison.creerListeDeMotsEnMinuscules(chaine1);
			LinkedList<String> listeMots2 = UtilitaireMethodesDeComparaison.creerListeDeMotsEnMinuscules(chaine2);
			String motChaine1;
			String motLePlusProcheDeMotChaine1DansChaine2;
			LinkedList<String> motsNonUtilisés = new LinkedList<String>();
			LinkedList<String[]> listeCouplesMots = new LinkedList<String[]>();
			while(!listeMots1.isEmpty() && !listeMots2.isEmpty()){
				while(!listeMots1.isEmpty()){
					motChaine1 = listeMots1.getLast();
					motLePlusProcheDeMotChaine1DansChaine2 = UtilitaireMethodesDeComparaison.motLePlusProche(motChaine1,listeMots2);
					if(!motLePlusProcheDeMotChaine1DansChaine2.equals("") && UtilitaireMethodesDeComparaison.motLePlusProche(motLePlusProcheDeMotChaine1DansChaine2, listeMots1).equals(motChaine1)){
						String[] couple =  new String[2];
						couple[0] = motChaine1;
						couple[1] = motLePlusProcheDeMotChaine1DansChaine2;
						listeCouplesMots.add(couple);
						listeMots1.removeLast();
						listeMots2.remove(motLePlusProcheDeMotChaine1DansChaine2);
					}
					else{
						motsNonUtilisés.add(listeMots1.getLast());
						listeMots1.removeLast();
					}
				}
				while(!motsNonUtilisés.isEmpty()){
					listeMots1.add(motsNonUtilisés.getLast());
					motsNonUtilisés.removeLast();
				}
			}
			double valeurChaine1 = 0;
			double valeurChaine2 = 0;
			int longueurChaine1 = 0;
			int longueurChaine2 = 0;
			for(String[] couple : listeCouplesMots){
				valeurChaine1 += UtilitaireMethodesDeComparaison.similitudeJaro(couple[0], couple[1]) * couple[0].length();
				longueurChaine1 += couple[0].length();
				valeurChaine2 += UtilitaireMethodesDeComparaison.similitudeJaro(couple[0], couple[1]) * couple[1].length();
				longueurChaine2 += couple[1].length();
			}
			for(String mot1 : listeMots1){
				longueurChaine1 += mot1.length();
			}
			for(String mot2 : listeMots2){
				longueurChaine2 += mot2.length();
			}
			return (float) ((valeurChaine1 / (longueurChaine1+1) ) + (valeurChaine2 / (longueurChaine2+1) ) ) / 2;
		}
		catch(ChaineVideException e){
			return 1;
		}
	}
}
