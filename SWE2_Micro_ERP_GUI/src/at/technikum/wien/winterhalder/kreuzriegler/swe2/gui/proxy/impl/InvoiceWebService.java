package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl;

import java.util.List;

import com.google.gson.Gson;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.Uris;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ConnectionProblemException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.InvoiceWasNotCreatedOrUpdatedException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.IInvoiceProxy;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl.helper.ServiceHelper;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.CreateOrUpdateInvoiceRequest;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.GetContactsBySearchstringRequest;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.GetInvoicesByContactIdRequest;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.GetInvoicesBySearchstringRequest;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.response.CreateOrUpdateInvoiceResponse;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.response.GetContactsBySearchstringResponse;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.response.GetInvoicesByContactIdResponse;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.response.GetInvoicesBySearchstringResponse;

public class InvoiceWebService implements IInvoiceProxy {

	@Override
	public void CreateOrUpdateInvoice(InvoiceDto dto)
			throws ConnectionProblemException,
			InvoiceWasNotCreatedOrUpdatedException {
		// Obj -> JSON
		Gson gson = new Gson();
		String request = gson.toJson(new CreateOrUpdateInvoiceRequest(dto));

		String responseJSON = ServiceHelper.sendRequest(request,
				Uris.CREATE_OR_UPDATE_INVOICE);

		CreateOrUpdateInvoiceResponse response = gson.fromJson(responseJSON,
				CreateOrUpdateInvoiceResponse.class);

		if (!response.isStatus()) {
			throw new InvoiceWasNotCreatedOrUpdatedException(
					"Die Rechnung konnte nicht eingetragen oder bearbeitet werden");
		}
	}

	@Override
	public List<InvoiceDto> getInvoicesByContactId(long id)
			throws ConnectionProblemException {
		// Obj -> JSON
		Gson gson = new Gson();
		String request = gson.toJson(new GetInvoicesByContactIdRequest(id));

		String responseJSON = ServiceHelper.sendRequest(request,
				Uris.INVOICES_BY_CONTACTID);

		GetInvoicesByContactIdResponse response = gson.fromJson(responseJSON,
				GetInvoicesByContactIdResponse.class);

		return response.getInvoices();
	}

	@Override
	public List<InvoiceDto> getInvoicesBySearchstring(String contact,
			Long dateFrom, Long dateTo, Double amountFrom, Double amountTo)
			throws ConnectionProblemException {
		// Obj -> JSON
		Gson gson = new Gson();
		String request = gson.toJson(new GetInvoicesBySearchstringRequest(
				contact, dateFrom, dateTo, amountFrom, amountTo));

		String responseJSON = ServiceHelper.sendRequest(request,
				Uris.INVOICES_BY_SEARCHSTRING);

		GetInvoicesBySearchstringResponse response = gson.fromJson(
				responseJSON, GetInvoicesBySearchstringResponse.class);

		return response.getInvoices();
	}

}
