package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl;

import java.util.ArrayList;
import java.util.List;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.IContactProxy;

public class ProxyMock implements IContactProxy{

	@Override
	public List<ContactDto> getContactBySearchString(String search) {
		List<ContactDto> contacts = new ArrayList<>();
		
		ContactDto contact1 = new ContactDto();
		contact1.setFirstname("Donald");
		contact1.setLastname("Duck");
		contact1.setSuffix("BSc");
		contacts.add(contact1);
		
		ContactDto contact2 = new ContactDto();
		contact2.setFirstname("Dagobert");
		contact2.setLastname("Duck");
		contact2.setSuffix("MSc");
		contacts.add(contact2);
		
		ContactDto contact3 = new ContactDto();
		contact3.setFirstname("Gustav");
		contact3.setLastname("Gans");
		contact3.setSuffix("Nix");
		contacts.add(contact3);
		
		ContactDto contact4 = new ContactDto();
		contact4.setFirstname("Daisy");
		contact4.setLastname("Duck");
		contact4.setSuffix("BSc");
		contacts.add(contact4);
		
		ContactDto contact5 = new ContactDto();
		contact5.setCompanyname("Ich GmbH");
		contact5.setUid("12345");
		contacts.add(contact5);
		
		return contacts;
	}

}
