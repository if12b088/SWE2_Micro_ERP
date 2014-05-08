package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeOperationsException;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.AddressDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceRowDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.enums.AddressType;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ConnectionProblemException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ContactWasNotCreatedOrUpdatedException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.IContactProxy;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.CreateOrUpdateContactRequest;

public class ContactProxyMock implements IContactProxy {

	@Override
	public void createOrUpdateContact(ContactDto dto)
			throws ConnectionProblemException,
			ContactWasNotCreatedOrUpdatedException {

	}

	@Override
	public List<ContactDto> getCompanysByName(String name)
			throws ConnectionProblemException {
		List<ContactDto> companys = new ArrayList<>();

		ContactDto company1 = new ContactDto();
		company1.setCompanyname("Ich GmbH");
		company1.setUid("12345");
		AddressDto address11 = new AddressDto();
		address11.setStreet("IchStrasse 1");
		address11.setZip("1234");
		address11.setCity("Entenhausen");
		AddressDto address12 = new AddressDto();
		address12.setStreet("DuStrasse 2");
		address12.setZip("1234");
		address12.setCity("Entenhausen");
		AddressDto address13 = new AddressDto();
		address13.setStreet("WirStrasse 3");
		address13.setZip("1234");
		address13.setCity("Entenhausen");
		company1.getAddresses().put(AddressType.PRIMARY, address11);
		company1.getAddresses().put(AddressType.SHIPPING, address12);
		company1.getAddresses().put(AddressType.INVOICE, address13);

		companys.add(company1);

		ContactDto company2 = new ContactDto();
		company2.setCompanyname("Du GmbH");
		company2.setUid("54321");
		AddressDto address21 = new AddressDto();
		address21.setStreet("DuStrasse 1");
		address21.setZip("4321");
		address21.setCity("QuakTown");
		AddressDto address22 = new AddressDto();
		address22.setStreet("DuUndIchStrasse 2");
		address22.setZip("1234");
		address22.setCity("Entenhausen");
		AddressDto address23 = new AddressDto();
		address23.setStreet("WirStrasse 3");
		address23.setZip("4321");
		address23.setCity("QuakTown");
		company2.getAddresses().put(AddressType.PRIMARY, address21);
		company2.getAddresses().put(AddressType.SHIPPING, address22);
		company2.getAddresses().put(AddressType.INVOICE, address23);

		companys.add(company2);

		return companys;
	}

	@Override
	public ContactDto getContactById(int id) throws ConnectionProblemException {

		ContactDto contact = new ContactDto();
		contact.setFirstname("Donald");
		contact.setLastname("Duck");
		contact.setSuffix("BSc");
		AddressDto address1 = new AddressDto();
		address1.setStreet("Loserstrasse 1");
		address1.setZip("1234");
		address1.setCity("Entenhausen");
		contact.getAddresses().put(AddressType.PRIMARY, address1);

		return contact;
	}

	@Override
	public List<ContactDto> getContactsBySearchString(String search)
			throws ConnectionProblemException {
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

	@Override
	public List<ContactDto> getContactsByName(String name)
			throws ConnectionProblemException {
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
