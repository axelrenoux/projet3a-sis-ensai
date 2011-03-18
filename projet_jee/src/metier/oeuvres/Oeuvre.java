package metier.oeuvres;

import java.util.ArrayList;

import metier.ComposantCluster;
import metier.Tag;

public interface Oeuvre extends ComposantCluster {

	public double getListeners();
	public double getPlaycount();
	public ArrayList<Tag> getToptags();

}
