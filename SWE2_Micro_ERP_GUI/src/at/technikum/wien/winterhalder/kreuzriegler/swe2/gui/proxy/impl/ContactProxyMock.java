package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl;

import java.util.ArrayList;
import java.util.List;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.AddressDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.enums.AddressType;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.IContactProxy;

public class ContactProxyMock implements IContactProxy {

	@Override
	public List<ContactDto> getContactBySearchString(String search) {
		List<ContactDto> contacts = new ArrayList<>();

		ContactDto contact1 = new ContactDto();
		contact1.setFirstname("Donald");
		contact1.setLastname("Duck");
		contact1.setSuffix("BSc");
		AddressDto address1 = new AddressDto();
		address1.setStreet("Loserstrasse 1");
		address1.setZip("1234");
		address1.setCity("Entenhausen");
		contact1.getAddresses().put(AddressType.PRIMARY, address1);

		contacts.add(contact1);

		ContactDto contact2 = new ContactDto();
		contact2.setFirstname("Dagobert");
		contact2.setLastname("Duck");
		contact2.setSuffix("MSc");
		contacts.add(contact2);
		AddressDto address2 = new AddressDto();
		address2.setStreet("Richroad 2");
		address2.setZip("1234");
		address2.setCity("Entenhausen");
		contact2.getAddresses().put(AddressType.SHIPPING, address2);

		ContactDto contact3 = new ContactDto();
		contact3.setFirstname("Gustav");
		contact3.setLastname("Gans");
		contact3.setSuffix("Nix");
		contacts.add(contact3);
		AddressDto address3 = new AddressDto();
		address3.setStreet("Luckypalace 3");
		address3.setZip("1234");
		address3.setCity("Entenhausen");
		contact3.getAddresses().put(AddressType.PRIMARY, address3);

		ContactDto contact4 = new ContactDto();
		contact4.setFirstname("Daisy");
		contact4.setLastname("Duck");
		contact4.setSuffix("BSc");
		contacts.add(contact4);
		AddressDto address4 = new AddressDto();
		address4.setStreet("Beautystreet 12");
		address4.setZip("1234");
		address4.setCity("Entenhausen");
		contact4.getAddresses().put(AddressType.PRIMARY, address4);

		ContactDto contact5 = new ContactDto();
		contact5.setCompanyname("Ich GmbH");
		contact5.setUid("12345");
		contacts.add(contact5);
		AddressDto address51 = new AddressDto();
		address51.setStreet("IchStrasse 1");
		address51.setZip("1234");
		address51.setCity("Entenhausen");
		AddressDto address52 = new AddressDto();
		address52.setStreet("DuStrasse 2");
		address52.setZip("1234");
		address52.setCity("Entenhausen");
		AddressDto address53 = new AddressDto();
		address53.setStreet("WirStrasse 3");
		address53.setZip("1234");
		address53.setCity("Entenhausen");
		contact5.getAddresses().put(AddressType.PRIMARY, address51);
		contact5.getAddresses().put(AddressType.SHIPPING, address52);
		contact5.getAddresses().put(AddressType.INVOICE, address53);

		return contacts;
	}

}
