/**
 * 
 */
package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl.helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ConnectionProblemException;

/**
 * @author richie
 * 
 */
public class ServiceHelper {

	public static String sendRequest(String req, String uri)
			throws ConnectionProblemException {
		try {

			// Load Properties
			Properties prop = new Properties();
			InputStream input = null;

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post;

			// post = new HttpPost("http://localhost:8080/micro/"
			// + Uris.CONTACTS_BY_SEARCHSTRING);

			// //Constans
			// post = new HttpPost(new URI(Constants.SCHEME, null,
			// Constants.HOST,
			// Constants.PORT, Constants.PATH + uri, null, "anchor"));
			// Properies
			post = new HttpPost(new URI(prop.getProperty("SCHEME"), null,
					prop.getProperty("HOST"), Integer.parseInt(prop
							.getProperty("PORT")), prop.getProperty("PATH")
							+ uri, null, "anchor"));

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("request", req));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			StringBuilder sb = new StringBuilder();

			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();

			return sb.toString();
		} catch (IOException | URISyntaxException e) {
			throw new ConnectionProblemException(
					"Es konnte keine Verbindung zum Server hergestellt werden");

		}
	}
}
