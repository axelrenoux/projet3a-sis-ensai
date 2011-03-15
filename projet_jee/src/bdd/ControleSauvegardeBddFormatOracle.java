package bdd;


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
		//On s'assure (pour le contenu@Wiki) que la longueur n'est pas trop grande
		input=input.substring(0,Math.min(input.length(),3997));
		//On veut du SQL : les VARCHAR2 doivent être entre guillemets
		input="'"+input+"'";
		return input;
	}
}
