package comparaison;

/**
 * Adaptation de l'algorithme de Needleman-Wunsch, vu en cours de bioinformatique
 * L'algorithme de Needleman-Wunsch sert � mesurer la proximit� entre des s�quences d'adn
 * On l'utilise ici pour mesurer la proximit� entre des chaines de caract�res 
 * 
 * @author adapt� � partir de http://www25.brinkster.com/denshade/NeedlemanWunsch.java.htm
 *
 *****************	TESTS	******************
 *	Taux proximit� rap % ra� : 0.6666667
 *	Taux proximit� rap % rock : 0.30555555
 *
 *	Taux proximit� rock ind�pendant % ROCK INDEPENDANT : 1.0
 *	Taux proximit� rock % rock : 1.0
 *
 *	Taux proximit� pop rock % rock : 0.9166667
 *	Taux proximit� rock ind�pendant % rock : 0.75
 *	Taux proximit� rock % rock psych�d�lique : 0.7083333
 *
 *	Taux proximit� rock ind�pendant % rock psych�d�lique : 0.4375
 *	Taux proximit� pop rock % ska rock : 0.625
 */
public class ComparaisonUtilisee extends FonctionDeRapprochement {

	private static int coutAbsence=-1;
	
	protected float appliquer(String chaine1, String chaine2){
          int[][] ar = calculateMatrix(chaine1.toCharArray(), chaine2.toCharArray());
          /*System.out.println("Matrice de correspondances entre '"+chaine1+"' et '"+chaine2+"' :");
          for (int y = 0; y < ar.length; y++){
              System.out.println("");
              for (int x = 0; x < ar[y].length; x++) System.out.print(ar[y][x] +"\t ");
          }
          System.out.println("");*/
          float similitude=((float) ar[chaine1.length()][chaine2.length()])/(12*Math.min(chaine1.length(),chaine2.length()));
          System.out.println("Taux proximit� "+chaine1+" % "+chaine2+" : "+similitude);
	      return similitude;
	  }
	  
	  public static int[][] calculateMatrix(char[] source, char[] dest){
	      int[][] res = new int[source.length+1][dest.length+1];
	      for (int y = 0; y < source.length; y++) res[y][0] = coutAbsence * y;
	      for (int x = 0; x < dest.length; x++) res[0][x] = coutAbsence * x;
	      for (int y = 1; y < source.length + 1; y++){
	          for (int x = 1; x < dest.length +1; x++){
	                  int k = res[y-1][x-1] + bonusEstIdentique(source[y-1] , dest[x-1]);
	                  int l = res[y-1][x] + coutAbsence;
	                  int m = res[y][x-1] + coutAbsence;
	                  k = Math.max(k,l);
	                  res[y][x] = Math.max(k,m);
	           }
	      }
	      return res;
	  }
	  
	  public static int bonusEstIdentique(int first, int second){
		  int bonusEstIdentique=0;
		  if(first==second	||	UtilitaireMethodesDeComparaison.sontEquivalentsCaracteres((char)first,(char)second)){
			  bonusEstIdentique=12;
		  }
	      return bonusEstIdentique;
	  }
}
