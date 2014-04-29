package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeOperationsException;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.AddressDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceRowDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.enums.AddressType;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.IContactProxy;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.CreateContactRequest;

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

	@Override
	public void createContact(CreateContactRequest create) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<InvoiceDto> getInvoiceByContactId(int id) {
		List<InvoiceDto> invoices = new ArrayList<>();

		//Invoice 1
		InvoiceDto invoice1 = new InvoiceDto();
		invoice1.setNr("123");
		invoice1.setInformation("Rechnungsinformation....");
		invoice1.setComment("Kommentar .....");
		
		List<InvoiceRowDto> rows1 = new ArrayList<>();
		InvoiceRowDto row1 = new InvoiceRowDto();
		row1.setName("Zeile 1");
		row1.setAmount(3.0);
		row1.setUst(20.0);
		row1.setPrice(12.0);
		InvoiceRowDto row2 = new InvoiceRowDto();
		row2.setName("Zeile 2");
		row2.setAmount(1.0);
		row2.setUst(19.0);
		row2.setPrice(85.0);
		InvoiceRowDto row3 = new InvoiceRowDto();
		row3.setName("Zeile 3");
		row3.setAmount(13.0);
		row3.setUst(20.0);
		row3.setPrice(120.0);
		rows1.add(row1);
		rows1.add(row2);
		rows1.add(row3);

		invoice1.setRows(rows1);
		
		//Invoice 2
		InvoiceDto invoice2 = new InvoiceDto();
		invoice2.setNr("4345");
		invoice2.setInformation("Dieser Text ist sicher laenger als 20 zeichen daher sollte er abgeschitten sein....");
		invoice2.setComment("Kommentar dieser Rechnung.....");
		
		List<InvoiceRowDto> rows2 = new ArrayList<>();
		InvoiceRowDto row4 = new InvoiceRowDto();
		row4.setName("Zeile 1");
		row4.setAmount(3.0);
		row4.setUst(20.0);
		row4.setPrice(1200.0);
		rows2.add(row4);
		
		invoice2.setRows(rows2);

		invoices.add(invoice1);
		invoices.add(invoice2);
		return invoices;
	}

}
