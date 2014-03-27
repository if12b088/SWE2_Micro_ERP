package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy;

import java.util.List;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;

public interface IContactProxy {

	public List<ContactDto> getContactBySearchString(String search);
}
