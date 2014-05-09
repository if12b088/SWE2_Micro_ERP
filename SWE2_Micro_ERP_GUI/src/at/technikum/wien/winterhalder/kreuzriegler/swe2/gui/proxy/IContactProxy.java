package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy;

import java.util.List;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ContactWasNotCreatedOrUpdatedException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ConnectionProblemException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.CreateOrUpdateContactRequest;

public interface IContactProxy {

	public void createOrUpdateContact(ContactDto dto)
			throws ConnectionProblemException, ContactWasNotCreatedOrUpdatedException;

	public List<ContactDto> getCompanysByName(String name)
			throws ConnectionProblemException;

	public ContactDto getContactById(long id)
			throws ConnectionProblemException;

	public List<ContactDto> getContactsByName(String name)
			throws ConnectionProblemException;

	public List<ContactDto> getContactsBySearchString(String searchString)
			throws ConnectionProblemException;

}
