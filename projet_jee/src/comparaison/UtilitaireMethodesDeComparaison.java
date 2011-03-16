package comparaison;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import comparaison.exceptions.ChaineVideException;
import comparaison.exceptions.JaroException;

/**
 * @author Samuel Maistre, bureau 259
 */
public class UtilitaireMethodesDeComparaison {
	
	private static ArrayList<ArrayList<Character>> listeCaracteresEquivalents = equivalences();
	
	
	/**
	 * Donne une liste de listes d'équivalences entre caractères
	 * @return ArrayList<ArrayList<Character>> 
	 */
	public static ArrayList<ArrayList<Character>> equivalences(){
		ArrayList<ArrayList<Character>> equiv = new ArrayList<ArrayList<Character>>();
		
		ArrayList<Character> listeE = new ArrayList<Character>();
		listeE.add('e');
		listeE.add('é');
		listeE.add('è');
		listeE.add('ê');
		listeE.add('ë');
		listeE.add('€');
		equiv.add(listeE);
		
		ArrayList<Character> listeA = new ArrayList<Character>();
		listeA.add('a');
		listeA.add('à');
		listeA.add('â');
		listeA.add('ä');
		listeA.add('@');
		equiv.add(listeA);
		
		ArrayList<Character> listeO = new ArrayList<Character>();
		listeO.add('o');
		listeO.add('ô');
		listeO.add('ö');
		listeO.add('0');
		listeO.add('°');
		listeO.add('¤');
		equiv.add(listeO);
		
		ArrayList<Character> listeU = new ArrayList<Character>();
		listeU.add('u');
		listeU.add('ù');
		listeU.add('û');
		listeU.add('µ');
		equiv.add(listeU);
		
		ArrayList<Character> listeI = new ArrayList<Character>();
		listeI.add('i');
		listeI.add('î');
		listeI.add('ï');
		equiv.add(listeI);
		
		ArrayList<Character> listeC = new ArrayList<Character>();
		listeC.add('c');
		listeC.add('ç');
		equiv.add(listeC);

		ArrayList<Character> listeL = new ArrayList<Character>();
		listeL.add('l');
		listeL.add('£');
		equiv.add(listeL);
		
		ArrayList<Character> listeS = new ArrayList<Character>();
		listeS.add('s');
		listeS.add('$');
		equiv.add(listeS);

		ArrayList<Character> listeN = new ArrayList<Character>();
		listeN.add('n');
		listeN.add('ñ');
		equiv.add(listeN);

		ArrayList<Character> liste2 = new ArrayList<Character>();
		liste2.add('²');
		liste2.add('2');
		equiv.add(liste2);
		
		ArrayList<Character> listeSeparateurs = new ArrayList<Character>();
		listeSeparateurs.add(' ');
		listeSeparateurs.add('.');
		listeSeparateurs.add('_');
		listeSeparateurs.add('-');
		listeSeparateurs.add('+');
		listeSeparateurs.add(';');
		listeSeparateurs.add(':');
		listeSeparateurs.add(',');
		listeSeparateurs.add('?');
		listeSeparateurs.add('!');
		listeSeparateurs.add('*');
		listeSeparateurs.add('%');
		listeSeparateurs.add('#');
		listeSeparateurs.add('~');
		listeSeparateurs.add('§');
		listeSeparateurs.add('	');
		listeSeparateurs.add('&');
		equiv.add(listeSeparateurs);

		ArrayList<Character> listeOuvrant = new ArrayList<Character>();
		listeOuvrant.add('(');
		listeOuvrant.add('[');
		listeOuvrant.add('{');
		listeOuvrant.add('<');
		equiv.add(listeOuvrant);
		
		ArrayList<Character> listeFermant = new ArrayList<Character>();
		listeFermant.add(')');
		listeFermant.add(']');
		listeFermant.add('}');
		listeFermant.add('>');
		equiv.add(listeFermant);
		
		return equiv;
	}

	
	/**
	 * Renvoie le mot le plus proche du mot motAtrouver au sens de Jaro parmi la liste listeMotsCandidats
	 * @param motAtrouver
	 * @param listeMotsCandidats
	 * @return
	 * @throws JaroException
	 */
	
	public static String motLePlusProche(String motAtrouver, LinkedList<String> listeMotsCandidats) throws JaroException{
		double similitudeMax = -0.1;
		String motLePlusProche = "";
		for(String motCandidat : listeMotsCandidats){
			if(minDistanceJaroPrefixeSuffixe(motAtrouver, motCandidat) > similitudeMax){
				motLePlusProche = motCandidat;
				similitudeMax = similitudeJaro(motAtrouver, motCandidat);
			}
		}
		return motLePlusProche;
	}


	/**
	 * @return the listeCaractèresEquivalents
	 */
	public static ArrayList<ArrayList<Character>> getListeCaracteresEquivalents() {
		return listeCaracteresEquivalents;
	}
	
	/**
	 * Renvoie true si caractère1 et caractère2 appartiennent à une même liste de caractères équivalents
	 * @param caractère1
	 * @param caractère2
	 * @return
	 */
	public static boolean sontEquivalentsCaracteres(Character caractere1, Character caractere2){
		boolean sontEquivalents = false;
		if(caractere1.equals(caractere2)){
			sontEquivalents = true;
		}
		else{
			for (ArrayList<Character> listeCarateres : listeCaracteresEquivalents) {
				sontEquivalents = sontEquivalents || (listeCarateres.contains(Character.toLowerCase(caractere1)) && listeCarateres.contains(Character.toLowerCase(caractere2)));
			}
		}
		return sontEquivalents;
	}
	
	/**
	 * Renvoie true si chaine1 et chaine2 ont des caractères équivalents
	 * @param caractère1
	 * @param caractère2
	 * @return
	 */
	public static boolean sontEquivalentesChaines(String chaine1, String chaine2){
		boolean sontEquivalentes = true;
		if(chaine1.length() == chaine2.length()){
			for (int i = 0; i < chaine1.length() ; i++) {
				sontEquivalentes = sontEquivalentes && sontEquivalentsCaracteres(chaine1.charAt(i), chaine2.charAt(i));
			}
			return sontEquivalentes;
		}
		else{
			return false;
		}
	}
	
	/**
     * Donne la distance minimum parmi la distance de Jaro entre chaine1 et chaine2 et la distance de Jaro entre l'inverse de chaine1 et l'inverse de chaine2
     * @param chaine1 première chaine
     * @param chaine2 seconde chaine
     * @return distance comprise entre 0 et 1
	 * @throws JaroException 
     */
	
	 public static double minDistanceJaroPrefixeSuffixe(String string1, String string2) throws JaroException {
		 //return Math.min(distanceJaroPréfixe(string1, string2), distanceJaroSuffixe(string1, string2));
		 return Math.max(similitudeJaro(string1, string2),similitudeJaro(miroir(string1), miroir(string2)));
	 }
	
    /**
     * Donne la distance de Jaro entre chaine1 et chaine2
     * @param chaine1 première chaine
     * @param chaine2 seconde chaine
     * @return distance comprise entre 0 et 1
     * @throws JaroException 
     */
    public static double similitudeJaro(String string1, String string2) throws JaroException  {
    	int eloignementMaximum = ((Math.max(string1.length(), string2.length())) / 2);
        StringBuffer communs1 = getCaracteresCommuns(string1, string2, eloignementMaximum);
        StringBuffer communs2 = getCaracteresCommuns(string2, string1, eloignementMaximum);
        if(communs1.length() == 0){
        	return 0.;
        }
        else{
        	if(communs1.length() != communs2.length()){
        		throw new JaroException("problème Jaro");
        	}
        	else{
		        int transpositions = 0;
		        for (int i = 0; i < communs1.length(); i++) {
		            if (!sontEquivalentsCaracteres(communs1.charAt(i), communs2.charAt(i)))
		                transpositions++;
		        }
		        return 	
		        		   (
		        				(
		        					communs1.length() / ((double) string1.length()) +
		        					communs2.length() / ((double) string2.length()) +
		        					(communs1.length() - (transpositions / 2.0)) / ((double) communs1.length())
		        				) / 3.0
		        			);
        	}
        }
    }
    
    /**
     * Renvoie les caractères communs à deux chaînes de caractères si leurs positions ne sont pas distantes de plus de distanceMax
     * @param chaine1
     * @param chaine2
     * @param distanceMax
     * @return buffer de caractères comprenant les caractères de chaine1 que l'on retrouve dans chaine2 si leur position dans chacune des chaines
     *         est inférieur à distanceDep
     */
    private static StringBuffer getCaracteresCommuns(String chaine1,String chaine2,int distanceMax) {
        StringBuffer communs = new StringBuffer();
        StringBuffer copieChaine2 = new StringBuffer(chaine2);
        for (int i = 0; i < chaine1.length(); i++) {
            final char ch = chaine1.charAt(i);
            boolean trouve = false;
            for (int j = Math.max(0, i - distanceMax); !trouve && j <= Math.min(i + distanceMax, chaine2.length() - 1); j++) {
                if (sontEquivalentsCaracteres(copieChaine2.charAt(j), ch)) {
                    trouve = true;
                    communs.append(ch);
                    copieChaine2.setCharAt(j,(char) 0);
                }
            }
        }
        return communs;
    }
    
    /**
     * Renvoie l'inverse de chaine
     * @param chaine
     * @return
     */
    public static String miroir(String chaine){
    	String miroir = "";
    	for (int i = chaine.length() - 1; i >= 0  ; i--) {
			miroir += chaine.charAt(i);
		}
    	return miroir;
    }
    
    /**
     * Renvoie la liste des sous-listes de mots de longueur 'longueur' contenus dans listeMots
     * @param listeMots
     * @param longueur
     * @return
     */
    public static LinkedList<LinkedList<String>> creerQGrams(LinkedList<String> listeMots ,int longueur){
    	LinkedList<LinkedList<String>> listeQGrams = new LinkedList<LinkedList<String>>();
    	int n = listeMots.size();
    	for(int i = 0; i < longueur; i++){
    		listeMots.add(listeMots.get(i));
    	}
    	for (int i = 0; i < n; i++) {
    		listeQGrams.add(new LinkedList<String>(listeMots.subList(i, i + longueur)));
		}
		return listeQGrams;
    }


	/**
	 * Crée la liste des mots en minuscules contenus dans une chaine de caractères
	 * @parameter String : chaine à décomposer
	 * @return LinkedList<String>
	 * @throws ChaineVideException 
	 */
	public static LinkedList<String> creerListeDeMotsEnMinuscules (String chaine) throws ChaineVideException{
		LinkedList<String> listeDeMots = new LinkedList<String>();
		int i = 0;
		try{
			for (int j = 0; j <= chaine.length() - 1; j++) {
				if (sontEquivalentsCaracteres(chaine.charAt(j), ' ')){
					String aAjouter = new String(chaine.substring(i, j).toLowerCase());
					if(!aAjouter.equals("")){
						listeDeMots.add(aAjouter);
					}
					i = j + 1;
				}
			}
		}
		catch(NullPointerException e){
			throw new ChaineVideException(e);
		}
		listeDeMots.add(new String(chaine.substring(i, chaine.length()).toLowerCase()));
		return listeDeMots;
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	public static List<List<String>> resultSetToLinkedList(ResultSet result) {
		List<List<String>> listeResultat = new LinkedList<List<String>>();
		try {
			while(result.next()){
				LinkedList<String> ligneCourante = new LinkedList<String>();
				int i = 1;
				boolean aLaFin = false;
				while(!aLaFin){
					try{
						ligneCourante.add(result.getString(i));
					}
					catch(SQLException e){
						aLaFin = true;
					}
					i++;
				}
				listeResultat.add(ligneCourante);
			}
		} catch (SQLException e) {
		}
		return listeResultat;
	}
}