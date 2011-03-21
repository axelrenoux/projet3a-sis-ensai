package comparaison;

import metier.ObjetAComparer;
import exceptions.JaroException;

/**
 *  
 * Les deux classes filles donnent les méthodes de comparaison utilisées en 2008-2009 pour le projet info 2A 
 * de Samuel Maistre, Nidal Ramadan, Alex Ramamourthy, Cyril Gicquiaux, Pierre Kramer et Axel Renoux
 * 
 * Ces méthodes servaient à trouver des doublons probables entre des chaines de caractères.
 * Hasard : les données comparées étaient du même type que celle que nous étudions aujourd'hui : album/chanson/artiste
 * 
 */
public abstract class FonctionDeRapprochement{
	private double seuil=0.7;
	
	/**
	 * Fonction de rapprochement donnant un score de similarité entre deux String
	 */
	public boolean sontSimilaires(ObjetAComparer ref,ObjetAComparer candidat){
		return sontSimilaires(ref.getName(),candidat.getName());
	}
	
	public boolean sontSimilaires(String ref,String candidat){
		try{
			return (appliquer(ref,candidat)>seuil);
		}
		catch (JaroException e) {
			return false;
		}
	}

	protected abstract float appliquer(String chaine1,String chaine2) throws JaroException;

	public double getSeuil() {
		return seuil;
	}

	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}
}
