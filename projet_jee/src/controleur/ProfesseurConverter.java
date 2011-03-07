package controleur;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import formation.jsf.gestionscolaire.metier.bdd.BaseDeDonnees;
import formation.jsf.gestionscolaire.metier.entite.Professeur;

@FacesConverter(forClass = formation.jsf.gestionscolaire.metier.entite.Professeur.class)
public class ProfesseurConverter implements Converter{


        @Override
        public Object getAsObject(FacesContext ctx, UIComponent arg1, String prenomNom) {
                // TODO Auto-generated method stub
                //gestion.recupMatiere(intitule);
                if (prenomNom == null) {
                        return null;
                }

                // Création d'une ValueExpression afin de récupérer une instance de BaseDeDonnees
                ValueExpression va = ctx.getApplication().getExpressionFactory()
                                .createValueExpression(ctx.getELContext(), "#{baseDeDonnees}", BaseDeDonnees.class);
                BaseDeDonnees bdd = (BaseDeDonnees) va.getValue(ctx.getELContext());

                Professeur prof = bdd.recupProfesseur(prenomNom);

                return prof;
        }

        @Override
        public String getAsString(FacesContext arg0, UIComponent arg1, Object prof) {
                if (prof == null) {
                        return null;
                }
                return (((Professeur) prof).getPrenom())+" "+(((Professeur) prof).getNom());
        }

}