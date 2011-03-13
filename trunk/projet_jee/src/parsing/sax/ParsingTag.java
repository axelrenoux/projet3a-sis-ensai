package parsing.sax;


import handler.sax.TagInfoHandler;

import java.io.IOException;
import java.io.InputStream;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import metier.Tag;

import org.apache.http.client.methods.HttpGet;
import org.xml.sax.SAXException;

import controleur.Controleur;

import appelHttp.AppelHTTP;


public class ParsingTag extends Parsing{

	
	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	
	public Tag parserInfos(Tag tag) {

		String marequete="";
		try{
			// cr�ation d'une fabrique de parseurs SAX
			SAXParserFactory fabrique = SAXParserFactory.newInstance();

			// cr�ation d'un parseur SAX
			SAXParser parseur = fabrique.newSAXParser();

		 


			//on cr�e la requete avec le nom du tag
			marequete = "http://ws.audioscrobbler.com/2.0/?method=tag.getinfo&api_key=ca33590ba46941a9186c4777b5046445&tag="+tag.getName();
			
			
			marequete = transformationURL(marequete);
			
			
			HttpGet requeteGet = new HttpGet(marequete);


			//on recupere sous forme d'input stream le resultat de la requete
			InputStream input = AppelHTTP.recupererDonnees(requeteGet);

			//on cree un gestionnaire d'albums pour parser le resultat de la requete
			TagInfoHandler gestionnaireTag = new TagInfoHandler(tag);
			parseur.parse(input, gestionnaireTag);


			//TODO semble pas tr�s propre de recup�rer ainsi l'album...
			//on r�cup�re le tag mis � jour apr�s avoir pars� les infos compl�mentaires
			tag = gestionnaireTag.getTag();


			//System.out.println("fin du traitement");
						


		}catch(ParserConfigurationException pce){
			System.out.println("Erreur de configuration du parseur");
			System.out.println("Lors de l'appel � SAXParser.newSAXParser()");
		}catch(SAXException se){
			System.out.println("Erreur de parsing");
			System.out.println("Lors de l'appel � parse()");
			se.printStackTrace();
		}catch(IOException ioe){
			System.out.println("Erreur d'entr�e/sortie");
			System.out.println("Lors de l'appel � parse()");
		}catch(IllegalArgumentException iae){
			System.out.println("Erreur dans l'expression de la requete");
			System.out.println("Lors de l'appel � httpGet");
			Controleur.getInstanceuniquecontroleur().
			ajouterProbleme("Probleme " + + Controleur.getInstanceuniquecontroleur().getListeProblemesRencontres().size(),
					"Erreur dans l'expression de la requete lors de l'appel � httpGet " + "\n " +
					"pb requete: " + marequete + "\n " +
					"pb tag: " + tag.getName()+"\n ");	
		}


		return tag;
	}


}
