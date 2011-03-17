package comparaison;

/**
 * Adaptation de l'algorithme de Needleman-Wunsch, vu en cours de bioinformatique
 * L'algorithme de Needleman-Wunsch sert à mesurer la proximité entre des séquences d'adn
 * On l'utilise ici pour mesurer la proximité entre des chaines de caractères 
 * 
 * @author adapté à partir de http://www25.brinkster.com/denshade/NeedlemanWunsch.java.htm
 *
 */
public class ComparaisonUtilisee extends FonctionDeRapprochement {

	private static int coutAbsence=-1;
	
	@Override
	protected double appliquer(String chaine1, String chaine2){
          System.out.println(bonusEstIdentique(0,0));
          System.out.println(bonusEstIdentique(1,0));
          int[][] ar = calculateMatrix(chaine1.toCharArray(), chaine2.toCharArray());
          System.out.println("Matrice de correspondances entre '"+chaine1+"' et '"+chaine2+"' :");
          for (int y = 0; y < ar.length; y++){
              System.out.println("");
              for (int x = 0; x < ar[y].length; x++) System.out.print(ar[y][x] +"\t ");
          }
          System.out.println("");
          getAlignments(ar, chaine1.toCharArray(), chaine2.toCharArray(), chaine1, chaine2);
	      return ar[chaine1.length()+1][chaine2.length()+1];
	  }
	  public static void getAlignments(int[][] ar, char[] A, char[] B, String sA, String sB){
	      String alA = "";
	      String alB = "";        
	      int i = sA.length();
	      int j = sB.length();
	      while (i > 0 && j > 0){
	          int score = ar[i][j];
	          int scorediag = ar[i-1][j-1];
	          int scoreup = ar[i][j-1];
	          int scoreleft = ar[i-1][j];
	          if (score == scorediag + bonusEstIdentique(A[i-1], B[j-1])){
	              alA = sA.charAt(i-1) + alA;
	              alB = sB.charAt(j-1) + alB;
	              i--;j--;                
	          }else if (score == scoreleft + coutAbsence){
	              alA = sA.charAt(i-1) + alA;
	              alB = "-" + alB;
	              i--;
	          }else if(score == scoreup + coutAbsence){
	              alA = "-" + alA;
	              alB = sB.charAt(j-1) + alB;
	              j--;
	          }
	      }
	      while(i > 0){
	          alA = sA.charAt(i - 1) + alA;
	          alB = "-" + alB;
	          i--;            
	      }while(j > 0){
	          alA = "-" + alA;
	          alB = sB.charAt(j - 1) + alB;
	          j--;            
	      }
	      System.out.println(alA+"\n");
	      System.out.println(alB+"\n");
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
		  if(first==second) bonusEstIdentique=3;
	      return bonusEstIdentique;
	  }
}
