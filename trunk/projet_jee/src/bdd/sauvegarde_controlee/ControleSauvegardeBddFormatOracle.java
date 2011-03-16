package bdd.sauvegarde_controlee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class ControleSauvegardeBddFormatOracle extends ControleSauvegardeUnFormatPourLaBdd{

	public ControleSauvegardeBddFormatOracle(){
		super();
		setSauveur(new SauvegardeBddFormatOracle());
	}
	
	@Override
	public String controle(String input){
		//On vérifie si l'input nul
		input=super.controle(input);
		//On retire les apostrophes intempestives
		input=input.replace("'","%27");
		//On remplace les sauts de ligne par des tabulations
		input=input.replaceAll("[\r\n]+","	");
		//On s'assure (pour le contenu@Wiki) que la longueur n'est pas trop grande
		input=input.substring(0,Math.min(input.length(),3997));
		//On veut du SQL : les VARCHAR2 doivent être entre guillemets
		input="'"+input+"'";
		return input;
	}

	
	@Override
	public int getNbObsExistantes(boolean recreerLesTables) {
		int clefDebut=0;
		if(!recreerLesTables){
			try {
				BufferedReader fluxEntree = new BufferedReader(new FileReader("valDerniereClefPrimaireSQL.txt"));
				if (fluxEntree.ready()) {
					clefDebut=  Integer.parseInt(fluxEntree.readLine());
				}
				fluxEntree.close();
			}catch (IOException e1) {e1.printStackTrace();}
		}
		return clefDebut;
	}

	@Override
	public void setNbObsExistantes(int primarykey) {
		try {
			PrintWriter fluxSortie = new PrintWriter(new FileWriter("valDerniereClefPrimaireSQL.txt"));
			fluxSortie.println(primarykey);
			fluxSortie.close(); 
		}catch (IOException e1) {e1.printStackTrace();}
	}
}
