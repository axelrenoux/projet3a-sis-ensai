package comparaison;

import java.util.LinkedList;

import exceptions.ChaineVideException;
import exceptions.JaroException;

public class ComparaisonAlbumChanson extends FonctionDeRapprochement {

	@Override
	protected double appliquer(String chaine1, String chaine2) throws JaroException{
		try{
			LinkedList<String> listeMots1 = UtilitaireMethodesDeComparaison.creerListeDeMotsEnMinuscules(chaine1);
			LinkedList<String> listeMots2 = UtilitaireMethodesDeComparaison.creerListeDeMotsEnMinuscules(chaine2);
			int longueur = Math.min(listeMots1.size(), listeMots2.size()) / 2 + 1;
			double seuil = 0.9;
			LinkedList<LinkedList<String>> listeQgrams1 = UtilitaireMethodesDeComparaison.creerQGrams(listeMots1, longueur);
			LinkedList<LinkedList<String>> listeQgrams2 = UtilitaireMethodesDeComparaison.creerQGrams(listeMots2, longueur);
			int maxQGramsCommuns = Math.min(listeQgrams1.size(), listeQgrams2.size());
			int nbQGramsCommuns = 0;
			double distance;
			boolean equivalents;
			for(LinkedList<String> qGram1 : listeQgrams1){
				LinkedList<LinkedList<String>> listeQGrams2NonAssociés = new LinkedList<LinkedList<String>>();
				while(!listeQgrams2.isEmpty()){
					equivalents = true;
					for(int i = 0; i<longueur; i++){
						distance = UtilitaireMethodesDeComparaison.minDistanceJaroPrefixeSuffixe(qGram1.get(i), listeQgrams2.getLast().get(i));
						equivalents &= (distance > seuil);
					}
					if(equivalents){
						nbQGramsCommuns++;
					}
					else{
						listeQGrams2NonAssociés.add(listeQgrams2.getLast());
					}
					listeQgrams2.removeLast();
				}
				listeQgrams2 = listeQGrams2NonAssociés;
			}
	        return nbQGramsCommuns / (float) maxQGramsCommuns;
		}
        catch(ChaineVideException e){
			return 1.;
		}
	}
	
	//modifie
}
