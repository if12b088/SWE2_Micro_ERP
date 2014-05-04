package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl;

import java.util.List;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.InvoiceWasNotCreatedOrUpdatedException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.IInvoiceProxy;

public class InvoiceWebService implements IInvoiceProxy {

	@Override
	public boolean CreateOrUpdateInvoice(InvoiceDto dto)
			throws InvoiceWasNotCreatedOrUpdatedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<InvoiceDto> getInvoicesByContactId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InvoiceDto> getInvoicesBySearchstring(String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

}
