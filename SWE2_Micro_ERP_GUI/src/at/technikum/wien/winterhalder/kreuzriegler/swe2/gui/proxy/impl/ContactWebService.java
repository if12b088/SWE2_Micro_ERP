package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.Uris;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.Constants;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.IContactProxy;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.GetContactsBySearchstringRequest;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.response.GetContactsBySearchstringResponse;

import com.google.gson.Gson;

public class ContactWebService implements IContactProxy {

	@Override
	public List<ContactDto> getContactBySearchString(String search) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post;

		post = new HttpPost("http://localhost:8080/micro/"+ Uris.CONTACTS_BY_SEARCHSTRING);
		
		//post = new HttpPost(new URI(null, null, Constants.HOST, Constants.PORT,
				//Constants.PATH, null, null));

		// Obj -> JSON
		Gson gson = new Gson();

		String req = gson.toJson(new GetContactsBySearchstringRequest(search));

		try {
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

			GetContactsBySearchstringResponse resp = gson.fromJson(
					sb.toString(), GetContactsBySearchstringResponse.class);
			return resp.getContacts();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
