package controleur;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import formation.jsf.gestionscolaire.metier.bdd.BaseDeDonnees;
import formation.jsf.gestionscolaire.metier.entite.Matiere;

@FacesConverter(forClass = formation.jsf.gestionscolaire.metier.entite.Matiere.class)
public class MatiereConverter implements Converter{

	
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent arg1, String intitule) {
		// TODO Auto-generated method stub
		//gestion.recupMatiere(intitule);
		if (intitule == null) {
			return null;
		}

		// Création d'une ValueExpression afin de récupérer une instance de BaseDeDonnees
		ValueExpression va = ctx.getApplication().getExpressionFactory()
				.createValueExpression(ctx.getELContext(), "#{baseDeDonnees}", BaseDeDonnees.class);
		BaseDeDonnees bdd = (BaseDeDonnees) va.getValue(ctx.getELContext());

		Matiere matiere = bdd.recupMatiere(intitule);

		return matiere;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object matiere) {
		if (matiere == null) {
			return null;
		}
		return ((Matiere) matiere).getIntitule();
	}

}
