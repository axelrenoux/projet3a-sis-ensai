package metier.oeuvres;

import java.util.ArrayList;

import metier.ComposantCluster;
import metier.Tag;

public interface Oeuvre extends ComposantCluster {

	public int getListeners();
	public int getPlaycount();
	public ArrayList<Tag> getToptags();

}
