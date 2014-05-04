package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy;

import java.util.List;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ContactWasNotCreatedOrUpdatedException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.CreateOrUpdateContactRequest;

public interface IContactProxy {

	public boolean createOrUpdateContact(ContactDto dto) throws ContactWasNotCreatedOrUpdatedException;
	public List<ContactDto> getCompanysByName(String name);
	public List<ContactDto> getContactById(int id);
	public List<ContactDto> getContactsByName(String name);
	public List<ContactDto> getContactsBySearchString(String searchString);

}
