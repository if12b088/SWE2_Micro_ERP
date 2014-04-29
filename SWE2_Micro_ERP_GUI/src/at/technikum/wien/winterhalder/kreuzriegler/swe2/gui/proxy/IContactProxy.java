package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy;

import java.util.List;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.UserWasNotCreatedException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.CreateContactRequest;

public interface IContactProxy {

	public List<ContactDto> getContactBySearchString(String search);
	public void createContact(CreateContactRequest create) throws UserWasNotCreatedException;
	public List<InvoiceDto> getInvoiceByContactId(int id);
}
