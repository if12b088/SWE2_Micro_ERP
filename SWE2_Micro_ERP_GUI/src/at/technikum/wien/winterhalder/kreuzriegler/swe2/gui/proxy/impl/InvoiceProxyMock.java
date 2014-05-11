package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl;

import java.util.ArrayList;
import java.util.List;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceRowDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ConnectionProblemException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ContactWasNotCreatedOrUpdatedException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.InvoiceWasNotCreatedOrUpdatedException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.IInvoiceProxy;

public class InvoiceProxyMock implements IInvoiceProxy {

	@Override
	public void CreateOrUpdateInvoice(InvoiceDto dto)
			throws ConnectionProblemException,InvoiceWasNotCreatedOrUpdatedException {
		throw new InvoiceWasNotCreatedOrUpdatedException("Rechnung wurde nicht gespeichert!");
	}

	@Override
	public List<InvoiceDto> getInvoicesByContactId(long id) throws ConnectionProblemException {
		List<InvoiceDto> invoices = new ArrayList<>();

		// Invoice 1
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

		// Invoice 2
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

	@Override
	public List<InvoiceDto> getInvoicesBySearchstring(String contact,
			Long dateFrom, Long dateTo, Double amountFrom, Double amountTo) throws ConnectionProblemException{
		List<InvoiceDto> invoices = new ArrayList<>();

		// Invoice 1
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

		// Invoice 2
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
		
		//throw new ConnectionProblemException("Keine Verbindung moeglich");
		
		return invoices;
	}

}
