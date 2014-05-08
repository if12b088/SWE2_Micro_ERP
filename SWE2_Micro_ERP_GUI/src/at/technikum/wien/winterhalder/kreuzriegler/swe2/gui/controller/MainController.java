package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ConnectionProblemException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.ContactModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.InvoiceModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.ProxyFactory;

public class MainController extends AbstractController {

	@FXML
	// fx:id="searchDateFromTextField"
	private TextField searchDateFromTextField; // Value injected by FXMLLoader

	@FXML
	// fx:id="contactListView"
	private ListView<ContactModel> contactListView; // Value injected by
													// FXMLLoader

	@FXML
	// fx:id="searchInvoiceButton"
	private Button searchInvoiceButton; // Value injected by FXMLLoader

	@FXML
	// fx:id="addContactButton"
	private Button addContactButton; // Value injected by FXMLLoader

	@FXML
	// fx:id="searchContactTextField"
	private TextField searchContactTextField; // Value injected by FXMLLoader

	@FXML
	// fx:id="searchInvoiceTextField"
	private TextField searchInvoiceTextField; // Value injected by FXMLLoader

	@FXML
	// fx:id="searchAmountFromTextField"
	private TextField searchAmountFromTextField; // Value injected by FXMLLoader

	@FXML
	// fx:id="searchAmountToTextField"
	private TextField searchAmountToTextField; // Value injected by FXMLLoader

	@FXML
	// fx:id="searchDateToTextField"
	private TextField searchDateToTextField; // Value injected by FXMLLoader

	@FXML
	// fx:id="addInvoiceButton"
	private Button addInvoiceButton; // Value injected by FXMLLoader

	@FXML
	// fx:id="invoiceListView"
	private ListView<InvoiceModel> invoiceListView; // Value injected by
													// FXMLLoader

	@FXML
	// fx:id="searchContactButton"
	private Button searchContactButton; // Value injected by FXMLLoader

	@FXML
	private Label contactErrMsg;

	@FXML
	private Label invoiceErrMsg;

	ObservableList<ContactModel> contactItems = FXCollections
			.observableArrayList();
	ObservableList<InvoiceModel> invoiceItems = FXCollections
			.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resources) {

		assert searchDateFromTextField != null : "fx:id=\"searchDateFromTextField\" was not injected: check your FXML file 'Main.fxml'.";
		assert contactListView != null : "fx:id=\"contactListView\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchInvoiceButton != null : "fx:id=\"searchInvoiceButton\" was not injected: check your FXML file 'Main.fxml'.";
		assert addContactButton != null : "fx:id=\"addContactButton\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchContactTextField != null : "fx:id=\"searchContactTextField\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchInvoiceTextField != null : "fx:id=\"searchInvoiceTextField\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchAmountFromTextField != null : "fx:id=\"searchAmountFromTextField\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchAmountToTextField != null : "fx:id=\"searchAmountToTextField\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchDateToTextField != null : "fx:id=\"searchDateToTextField\" was not injected: check your FXML file 'Main.fxml'.";
		assert addInvoiceButton != null : "fx:id=\"addInvoiceButton\" was not injected: check your FXML file 'Main.fxml'.";
		assert invoiceListView != null : "fx:id=\"invoiceListView\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchContactButton != null : "fx:id=\"searchContactButton\" was not injected: check your FXML file 'Main.fxml'.";

		super.initialize(url, resources);
		contactListView.setItems(contactItems);
		invoiceListView.setItems(invoiceItems);
	}

	@FXML
	public void handleNewContact() {
		openContactInNewWindow(new ContactModel());
	}

	@FXML
	public void handleSearchContact() {
		List<ContactDto> contacts = null;
		try {
			contacts = ProxyFactory
					.createContactProxy()
					.getContactsBySearchString(searchContactTextField.getText());
		} catch (ConnectionProblemException e) {
			contactErrMsg.setText(e.getMessage());
		}

		if (contacts != null) {
			List<ContactModel> contactModels = new ArrayList<>();

			for (ContactDto cDto : contacts) {
				ContactModel cModel = new ContactModel();
				cModel.setDto(cDto);
				contactModels.add(cModel);
			}

			contactItems.clear();
			contactItems.addAll(contactModels);
			System.out.println("search");
		}
	}

	@FXML
	public void handleSearchInvoice() {
		List<InvoiceDto> invoices = null;
		Long dateFromInMilli = null;
		Long dateToInMilli = null;
		Double amountFrom = null;
		Double amountTo = null;

		//dateFrom
		if (!searchDateFromTextField.getText().isEmpty()) {
			try {
				dateFromInMilli = validateDate(
						searchDateFromTextField.getText()).getTime();
			} catch (ParseException e) {
				invoiceErrMsg
						.setText("Das von Datum wurde nicht korrekt eingetragen");
				return;
			}
		}
		//dateTo
		if (!searchDateToTextField.getText().isEmpty()) {
			try {
				dateToInMilli = validateDate(searchDateToTextField.getText())
						.getTime();
			} catch (ParseException e) {
				invoiceErrMsg
						.setText("Das bis Datum wurde nicht korrekt eingetragen");
				return;
			}
		}
		//amountFrom
		if (!searchAmountFromTextField.getText().isEmpty()) {
			try {
			amountFrom = Double
					.parseDouble(searchAmountFromTextField.getText());
			}catch (NumberFormatException e) {
				invoiceErrMsg.setText("Der von Betrag ist keine Zahl");
			}
		}
		//amountTp
		if (!searchAmountToTextField.getText().isEmpty()) {
			try {
				amountTo = Double
						.parseDouble(searchAmountToTextField.getText());
			}catch (NumberFormatException e) {
				invoiceErrMsg.setText("Der bis Betrag ist keine Zahl");
			}
		}

		try {
			invoices = ProxyFactory.createInvoiceProxy()
					.getInvoicesBySearchstring(
							searchInvoiceTextField.getText(),
							dateFromInMilli,
							dateToInMilli,
							amountFrom,
							amountTo);
		} catch (ConnectionProblemException e) {
			invoiceErrMsg.setText(e.getMessage());
		}

		if (invoices != null) {
			List<InvoiceModel> invoiceModels = new ArrayList<>();

			for (InvoiceDto iDto : invoices) {
				InvoiceModel iModel = new InvoiceModel();
				iModel.setDto(iDto);
				invoiceModels.add(iModel);
			}
			invoiceItems.clear();
			invoiceItems.addAll(invoiceModels);
		}
	}

	@FXML
	public void handleEnter(KeyEvent ke) throws IOException {
		if (ke.getCode().equals(KeyCode.ENTER)) {
			if (searchContactTextField.isFocused()) {
				handleSearchContact();
			} else if (searchInvoiceTextField.isFocused()
					|| searchDateFromTextField.isFocused()
					|| searchDateToTextField.isFocused()
					|| searchAmountFromTextField.isFocused()
					|| searchAmountToTextField.isFocused()) {
				handleSearchInvoice();
			}
		}
	}

	@FXML
	public void handleDblClick(MouseEvent me) {
		if (me.getClickCount() == 2) {
			System.out.println("clicked on "
					+ contactListView.getSelectionModel().getSelectedItem());
			System.out.println("clicked on "
					+ invoiceListView.getSelectionModel().getSelectedItem());
			if (contactListView.getSelectionModel().getSelectedItem() != null) {
				openContactInNewWindow(contactListView.getSelectionModel()
						.getSelectedItem());
			}
			if (invoiceListView.getSelectionModel().getSelectedItem() != null) {
				openInvoiceInNewWindow(invoiceListView.getSelectionModel()
						.getSelectedItem());
			}

			contactListView.getSelectionModel().clearSelection();
			invoiceListView.getSelectionModel().clearSelection();
		}
	}

	@FXML
	public void handleNewInvoice() {
		openInvoiceInNewWindow(new InvoiceModel());
	}

}
