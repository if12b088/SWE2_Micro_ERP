package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy;

import java.util.List;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.InvoiceWasNotCreatedOrUpdatedException;

public interface IInvoiceProxy {
	
	public boolean CreateOrUpdateInvoice(InvoiceDto dto) throws InvoiceWasNotCreatedOrUpdatedException;
	public List<InvoiceDto> getInvoicesByContactId(int id);
	public List<InvoiceDto> getInvoicesBySearchstring(String searchString);
}
