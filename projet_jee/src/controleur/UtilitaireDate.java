package controleur;

import java.sql.Date;
import java.util.HashMap;

import exceptions.ExceptionDate;


/**
 * @author Administrateur
 *
 */
/**
 * @author Administrateur
 *
 */
public class UtilitaireDate {

	/********************************************************************/
	/*************************      attributs       *********************/
	/********************************************************************/

	private HashMap<String,String> correspondancesMois;
	private static final UtilitaireDate instanceUnique = new UtilitaireDate();

	/********************************************************************/
	/**********************      constructeurs      *********************/
	/********************************************************************/
	private UtilitaireDate(){
		correspondancesMois = new HashMap<String, String>();
		correspondancesMois.put("Jan","0");
		correspondancesMois.put("Feb","1");
		correspondancesMois.put("Mar","2");
		correspondancesMois.put("Apr","3");
		correspondancesMois.put("May","4");
		correspondancesMois.put("Jun","5");
		correspondancesMois.put("Jul","6");
		correspondancesMois.put("Aug","7");
		correspondancesMois.put("Sep","8");
		correspondancesMois.put("Oct","9");
		correspondancesMois.put("Nov","10");
		correspondancesMois.put("Dec","11");
	}


	/********************************************************************/
	/************************      methodes      ************************/
	/********************************************************************/
	
	
	
	
	/**Methodes qui permettent de cr�er une date � partir des resultats de lastfm**/

	/**
	 * la date des WIKI est 2 formes possibles selon si le jour est sur 1 ou 2 chiffres 
	 * Sat, 6 Mar 2010 16:48:03 +0000   ou Sat, 16 Mar 2010 16:48:03 +0000
	 * 
	 * apres suppression des vides, on a ceci:
	 * index  : 	0 .1 .2 .3 .4 .5 .6 .7 .8 .9 .10.11.12.13.14.15.16.17.18.19.20.21.22.23.24.25
	 * date 1 : 	S .a .t ., .6 .M .a .r .2 .0 .1 .0 .1 .6 .: .4 .8 .: .0 .3 .+ .0 .0 .0 .0 
	 * date 2 : 	S .a .t ., .1 .6 .M .a .r .2 .0 .1 .0 .1 .6 .: .4 .8 .: .0 .3 .+ .0 .0 .0 .0  
	 * 
	 * les mois: Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec
	 * 	
	 * @param s
	 * @return
	 * @throws ExceptionDate 
	 */
	public Date transformerEnDateWiki(String s) throws ExceptionDate{
		s=eliminerVide(s);
		int year=0, month=0, day=0;
		String month_string;
		Date d = null;
		try{
			if(s.length()==25){//le jour est sur 1 chiffre
				day=Integer.parseInt(s.substring(4, 5));
				month_string = s.substring(5, 8);
				month = Integer.parseInt(correspondancesMois.get(month_string));
				year = Integer.parseInt(s.substring(8,12))-1900;
			}else if(s.length()==26){//le jour est sur 2 chiffres
				day=Integer.parseInt(s.substring(4, 6));
				month_string = s.substring(6, 9);
				month = Integer.parseInt(correspondancesMois.get(month_string));
				year = Integer.parseInt(s.substring(9,13)) -1900;
			}
			if(!(day==0 && month==0 && year==0)){
				d = new Date(year,month,day);	
			}else throw new ExceptionDate(s);
		}catch(Exception e){
			throw new ExceptionDate(s); 
		}
		Controleur.getInstanceuniquecontroleur().
		ajouter(d, s);
		return d;
	}

	
	/**
	 * la date des ALBUMS est 2 formes possibles selon si le jour est sur 1 ou 2 chiffres 
	 * 6 Apr 1999, 00:00 ou 30 Nov 2004, 00:00
	 * 
	 * apres suppression des vides, on a ceci:
	 * index  : 	0 .1 .2 .3 .4 .5 .6 .7 .8 .9 .10.11.12.13.14
	 * date 1 : 	6 .A .p .r .1 .9 .9 .9 ., .0 .0 .: .0 .0 
	 * date 2 : 	3 .0 .N .o .v .2 .0 .0 .4 ., .0 .0 .: .0 .0 
	 * 
	 * les mois: Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec
	 * 	
	 * @param s
	 * @return
	 * @throws ExceptionDate 
	 */
	public Date transformerEnDate(String s) throws ExceptionDate{
		s=eliminerVide(s);
		int year=0, month=0, day=0;
		String month_string;
		Date d = null;
		try{
			if(s.length()==14){//le jour est sur 1 chiffre
				day=Integer.parseInt(s.substring(0, 1));
				month_string = s.substring(1, 4);
				month = Integer.parseInt(correspondancesMois.get(month_string));
				year = Integer.parseInt(s.substring(4,8))-1900;
			}else if(s.length()==15){//le jour est sur 2 chiffres
				day=Integer.parseInt(s.substring(0, 2));
				month_string = s.substring(2, 5);
				month = Integer.parseInt(correspondancesMois.get(month_string));
				year = Integer.parseInt(s.substring(5,9)) -1900;	
			}
			if(!(day==0 && month==0 && year==0)){
				d = new Date(year,month,day);	
			}else throw new ExceptionDate(s);
		}catch(Exception e){
			throw new ExceptionDate(s); 
		}
		Controleur.getInstanceuniquecontroleur().
		ajouter(d, s);
		return d;
	}

	public String eliminerVide(String s){
		String ssansvide = s.replace(" ", "");
		return ssansvide;
	}

	
	/** methodes qui permettent de passer du format data � string et <--> pour la BDD**/
	
	/**
	 * ecrit en format string une date
	 * @param d
	 * @return
	 */
	public String transformeEnString(Date d){
		String s = "";
		int j = d.getDate();
		int m = d.getMonth()+1;
		int y = d.getYear()+1900;
		
		if(j<10) s="'0"+j+"/";
		else s="'"+j+"/";
		
		if(m<10) s+="0"+m+"/";
		else s+=m+"/";
		
		s+=y+"'";
		return s;
	}
	
	
	/**
	 * les dates dans la BDD sont de cette forme : 06/04/1999 (6 avril)
	 * 
	 * 
	 * index : 	0 .1 .2 .3 .4 .5 .6 .7 .8 .9
	 * date  : 	0 .6 ./ .0 .4 ./ .1 .9 .9 .9 
	 * 
	 * 	
	 * @param s
	 * @return
	 * @throws ExceptionDate 
	 */
	public Date transformerEnDateUneDateBDD(String s) throws ExceptionDate{
		int year=0, month=0, day=0;
		String month_string;
		Date d = null;
		try{
			if(s.length()==10){
				day=Integer.parseInt(s.substring(0, 2));
				month = Integer.parseInt(s.substring(3, 5))-1;
				year = Integer.parseInt(s.substring(6,10))-1900;
			}
			if(!(day==0 && month==0 && year==0)){
				d = new Date(year,month,day);	
			}else throw new ExceptionDate(s);
		}catch(Exception e){
			throw new ExceptionDate(s); 
		}
		Controleur.getInstanceuniquecontroleur().
		ajouter(d, s);
		return d;
	}

	
	
	/********************************************************************/
	/******************      getters / setters       ********************/
	/********************************************************************/

	public static UtilitaireDate getInstanceunique() {
		return instanceUnique;
	}




}
