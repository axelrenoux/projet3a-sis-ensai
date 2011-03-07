package controleur;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import formation.jsf.gestionscolaire.metier.bdd.BaseDeDonnees;
import formation.jsf.gestionscolaire.metier.entite.Groupe;


@FacesConverter(forClass = formation.jsf.gestionscolaire.metier.entite.Groupe.class)
public class GroupeConverter implements Converter{

	
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent arg1, String nom) {
		// TODO Auto-generated method stub
		if (nom == null) {
			return null;
		}

		// Création d'une ValueExpression afin de récupérer une instance de BaseDeDonnees
		ValueExpression va = ctx.getApplication().getExpressionFactory()
				.createValueExpression(ctx.getELContext(), "#{baseDeDonnees}", BaseDeDonnees.class);
		BaseDeDonnees bdd = (BaseDeDonnees) va.getValue(ctx.getELContext());

		Groupe groupe = bdd.recupGroupe(nom);

		return groupe;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object groupe) {
		if (groupe == null) {
			return null;
		}
		return ((Groupe) groupe).getNom();
	}

}
