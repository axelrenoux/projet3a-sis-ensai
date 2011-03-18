package comparaison;

import metier.ObjetAComparer;
import exceptions.JaroException;

/**
 *  
 * Les deux classes filles donnent les m�thodes de comparaison utilis�es en 2008-2009 pour le projet info 2A 
 * de Samuel Maistre, Nidal Ramadan, Alex Ramamourthy, Cyril Gicquiaux, Pierre Kramer et Axel Renoux
 * 
 * Ces m�thodes servaient � trouver des doublons probables entre des chaines de caract�res.
 * Hasard : les donn�es compar�es �taient du m�me type que celle que nous �tudions aujourd'hui : album/chanson/artiste
 * 
 */
public abstract class FonctionDeRapprochement{
	
	public float appliquer(ObjetAComparer a,ObjetAComparer b){
		try{
			return appliquer(a.getName(),b.getName());
		}
		catch (JaroException e) {
			return 0;
		}
	}
	
	/**
	 * Fonction de rapprochement donnant un score de similarit� entre deux String
	 */
	protected abstract float appliquer(String chaine1,String chaine2) throws JaroException;
}
