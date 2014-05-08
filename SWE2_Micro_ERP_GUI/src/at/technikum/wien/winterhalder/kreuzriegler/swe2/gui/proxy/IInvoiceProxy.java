package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy;

import java.util.List;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ConnectionProblemException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.InvoiceWasNotCreatedOrUpdatedException;

public interface IInvoiceProxy {

	public void CreateOrUpdateInvoice(InvoiceDto dto)
			throws ConnectionProblemException,
			InvoiceWasNotCreatedOrUpdatedException;

	public List<InvoiceDto> getInvoicesByContactId(long id)
			throws ConnectionProblemException;

	public List<InvoiceDto> getInvoicesBySearchstring(String contact,
			Long dateFrom, Long dateTo, Double amountFrom, Double amountTo)
			throws ConnectionProblemException;
}
