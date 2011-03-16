package metier;

import java.util.HashMap;

public interface ComposantCluster {
	
	public HashMap<String,ComposantCluster> getContenu();
	public String getNom();

}
