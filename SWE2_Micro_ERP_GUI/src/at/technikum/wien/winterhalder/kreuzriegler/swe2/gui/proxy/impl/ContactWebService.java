package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl;

import java.util.List;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.Uris;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ContactWasNotCreatedOrUpdatedException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ConnectionProblemException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.IContactProxy;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl.helper.ServiceHelper;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.CreateOrUpdateContactRequest;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.GetCompanysByNameRequest;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.GetContactByIdRequest;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.GetContactsByNameRequest;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.GetContactsBySearchstringRequest;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.response.CreateOrUpdateContactResponse;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.response.GetCompanysByNameResponse;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.response.GetContactByIdResponse;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.response.GetContactsByNameResponse;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.response.GetContactsBySearchstringResponse;

import com.google.gson.Gson;

public class ContactWebService implements IContactProxy {

	@Override
	public void createOrUpdateContact(ContactDto dto)
			throws ConnectionProblemException,
			ContactWasNotCreatedOrUpdatedException {

		// Obj -> JSON
		Gson gson = new Gson();
		String request = gson.toJson(new CreateOrUpdateContactRequest(dto));

		String responseJSON = ServiceHelper.sendRequest(request,
				Uris.CREATE_OR_UPDATE_CONTACT);

		CreateOrUpdateContactResponse response = gson.fromJson(responseJSON,
				CreateOrUpdateContactResponse.class);

		if (!response.isStatus()) {
			throw new ContactWasNotCreatedOrUpdatedException(
					"Der Kontakt konnte nicht eingetragen oder bearbeitet werden");
		}

	}

	@Override
	public List<ContactDto> getCompanysByName(String name)
			throws ConnectionProblemException {
		// Obj -> JSON
		Gson gson = new Gson();
		String request = gson.toJson(new GetCompanysByNameRequest(name));

		String responseJSON = ServiceHelper.sendRequest(request,
				Uris.COMPANIES_BY_NAME);

		GetCompanysByNameResponse response = gson.fromJson(responseJSON,
				GetCompanysByNameResponse.class);

		return response.getCompanies();
	}

	@Override
	public ContactDto getContactById(long id) throws ConnectionProblemException {
		// Obj -> JSON
		Gson gson = new Gson();
		String request = gson.toJson(new GetContactByIdRequest(id));

		String responseJSON = ServiceHelper.sendRequest(request,
				Uris.CONTACT_BY_ID);

		GetContactByIdResponse response = gson.fromJson(responseJSON,
				GetContactByIdResponse.class);

		return response.getContact();
	}

	@Override
	public List<ContactDto> getContactsByName(String name)
			throws ConnectionProblemException {
		// Obj -> JSON
		Gson gson = new Gson();
		String request = gson.toJson(new GetContactsByNameRequest(name));

		String responseJSON = ServiceHelper.sendRequest(request,
				Uris.CONTACTS_BY_NAME);

		GetContactsByNameResponse response = gson.fromJson(responseJSON,
				GetContactsByNameResponse.class);

		return response.getContacts();
	}

	@Override
	public List<ContactDto> getContactsBySearchString(String search)
			throws ConnectionProblemException {

		// Obj -> JSON
		Gson gson = new Gson();
		String request = gson.toJson(new GetContactsBySearchstringRequest(
				search));

		String responseJSON = ServiceHelper.sendRequest(request,
				Uris.CONTACTS_BY_SEARCHSTRING);

		GetContactsBySearchstringResponse response = gson.fromJson(
				responseJSON, GetContactsBySearchstringResponse.class);

		return response.getContacts();

	}

	// public List<ContactDto> getContactsBySearchString2(String search) {
	// try {
	// HttpClient client = HttpClientBuilder.create().build();
	// HttpPost post;
	//
	// // post = new HttpPost("http://localhost:8080/micro/"
	// // + Uris.CONTACTS_BY_SEARCHSTRING);
	//
	// post = new HttpPost(new URI(Constants.SCHEME, null, Constants.HOST,
	// Constants.PORT, Constants.PATH
	// + Uris.CONTACTS_BY_SEARCHSTRING, null, "anchor"));
	//
	// // Obj -> JSON
	// Gson gson = new Gson();
	//
	// String req = gson.toJson(new GetContactsBySearchstringRequest(
	// search));
	//
	// List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	// nameValuePairs.add(new BasicNameValuePair("request", req));
	// post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	//
	// HttpResponse response = client.execute(post);
	// BufferedReader rd = new BufferedReader(new InputStreamReader(
	// response.getEntity().getContent()));
	// String line = "";
	// StringBuilder sb = new StringBuilder();
	//
	// while ((line = rd.readLine()) != null) {
	// sb.append(line);
	// }
	// rd.close();
	//
	// GetContactsBySearchstringResponse resp = gson.fromJson(
	// sb.toString(), GetContactsBySearchstringResponse.class);
	// return resp.getContacts();
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (URISyntaxException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return null;
	// }
}
