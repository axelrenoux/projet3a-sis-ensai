package bdd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import exceptions.ConnectionException;
import exceptions.UpdateException;


public abstract class ChargementBDDOracleDepuisTxt {
	private static String requeteSQL;
	private static BufferedReader fluxEntree;
	
	public static void charger(){
		try {fluxEntree = new BufferedReader(new FileReader("requetesSQL.txt"));} 
		catch (IOException e) {e.printStackTrace();}
		try {SQLViaJDBC.connecter();}
		catch (ConnectionException e) {e.printStackTrace();}
		try {
			while (fluxEntree.ready()) {
				try {requeteSQL = fluxEntree.readLine();} 
				catch (IOException e) {e.printStackTrace();}
				try {SQLViaJDBC.executerRequeteSansRetour(requeteSQL);}
				catch (UpdateException e) {e.printStackTrace();}
			}
		} 
		catch (IOException e) {e.printStackTrace();}
		try {SQLViaJDBC.fermerBDD();}
		catch (ConnectionException e) {e.printStackTrace();}
		try {fluxEntree.close();}
		catch (IOException e) {e.printStackTrace();}
	}
}
