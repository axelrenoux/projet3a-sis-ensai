
package recuperationLastFM.appelHttp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
 

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author Administrateur
 *
 */
public class AppelHTTP {

	
	
	/**
	 * @return InputStream input_page 
	 */
	public static InputStream recupererDonnees(HttpGet requeteGet){
		
		 
		HttpClient client = new DefaultHttpClient();
		
		
		//HttpGet requeteGet = new HttpGet("http://ws.audioscrobbler.com/2.0/?method=album.search&album=believe&api_key=ca33590ba46941a9186c4777b5046445"); 
		
		
		requeteGet.addHeader("Content-type", "text/xml");

		requeteGet.addHeader("Accept", "application/xml");


	 
		BufferedReader buffer = null;
		InputStream input_page = null;
		
		try{
			
			HttpResponse reponseHTTP = client.execute(requeteGet);
			int reponse = reponseHTTP.getStatusLine().getStatusCode();
			
			if(HttpStatus.SC_OK == reponse) {
				System.out.println("Reponse OK");
			} else {
				System.out.println("Reponse KO");
			}
			
			System.out.println(reponse);
			System.out.println(reponseHTTP.getStatusLine().getReasonPhrase());
			
			HttpEntity entity = reponseHTTP.getEntity();
			if(entity != null){
				
				BufferedHttpEntity bufferedEntity = new BufferedHttpEntity(entity);
				
				//on récupère l'input stream
				input_page = bufferedEntity.getContent();
				
				//on lit le code source de la page récupéréé
				buffer = new BufferedReader(new InputStreamReader(bufferedEntity.getContent()));
				
				String readLine = null;
				
				while(((readLine = buffer.readLine()) != null)) {
					System.out.println(readLine);
				} // fin du while
			} // fin du if
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			// fermer connexion HTTP
			if(buffer != null){
				try {
					buffer.close();
				} catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		return input_page;
	}
	

}
