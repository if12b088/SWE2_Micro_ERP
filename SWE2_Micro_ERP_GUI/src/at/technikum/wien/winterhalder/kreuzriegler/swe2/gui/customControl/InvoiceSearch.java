/**
 * 
 */
package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.customControl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ConnectionProblemException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.helper.DateHelper;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.helper.WindowHelper;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.InvoiceModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.ProxyFactory;

/**
 * @author richie
 * 
 */
public class InvoiceSearch extends AnchorPane {

	@FXML
	private Label invoiceErrMsg;
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
	// fx:id="searchDateFromTextField"
	private TextField searchDateFromTextField; // Value injected by FXMLLoader
	@FXML
	// fx:id="searchDateToTextField"
	private TextField searchDateToTextField; // Value injected by FXMLLoader
	@FXML
	// fx:id="searchInvoiceButton"
	private Button searchInvoiceButton; // Value injected by FXMLLoader
	@FXML
	// fx:id="addInvoiceButton"
	private Button addInvoiceButton; // Value injected by FXMLLoader

	@FXML
	// fx:id="invoiceListView"
	private ListView<InvoiceModel> invoiceListView; // Value injected by
													// FXMLLoader
	ObservableList<InvoiceModel> invoiceItems = FXCollections
			.observableArrayList();

	public InvoiceSearch() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"InvoiceSearch.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		initialize();

	}

	public void initialize() {
		invoiceListView.setItems(invoiceItems);
	}

	@FXML
	public void handleNewInvoice() {
		WindowHelper.openInvoiceInNewWindow(new InvoiceModel());
	}

	@FXML
	public void handleSearchInvoice() {
		List<InvoiceDto> invoices = null;
		Long dateFromInMilli = null;
		Long dateToInMilli = null;
		Double amountFrom = null;
		Double amountTo = null;

		// dateFrom
		if (!searchDateFromTextField.getText().isEmpty()) {
			try {
				dateFromInMilli = DateHelper.validateDate(
						searchDateFromTextField.getText()).getTime();
			} catch (ParseException e) {
				invoiceErrMsg
						.setText("Das von Datum wurde nicht korrekt eingetragen");
				return;
			}
		}
		// dateTo
		if (!searchDateToTextField.getText().isEmpty()) {
			try {
				dateToInMilli = DateHelper.validateDate(
						searchDateToTextField.getText()).getTime();
			} catch (ParseException e) {
				invoiceErrMsg
						.setText("Das bis Datum wurde nicht korrekt eingetragen");
				return;
			}
		}
		// amountFrom
		if (!searchAmountFromTextField.getText().isEmpty()) {
			try {
				amountFrom = Double.parseDouble(searchAmountFromTextField
						.getText());
			} catch (NumberFormatException e) {
				invoiceErrMsg.setText("Der von Betrag ist keine Zahl");
			}
		}
		// amountTp
		if (!searchAmountToTextField.getText().isEmpty()) {
			try {
				amountTo = Double
						.parseDouble(searchAmountToTextField.getText());
			} catch (NumberFormatException e) {
				invoiceErrMsg.setText("Der bis Betrag ist keine Zahl");
			}
		}

		try {
			invoices = ProxyFactory.createInvoiceProxy()
					.getInvoicesBySearchstring(
							searchInvoiceTextField.getText(), dateFromInMilli,
							dateToInMilli, amountFrom, amountTo);
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
	public void handleDblClick(MouseEvent me) {
		if (me.getClickCount() == 2) {
			System.out.println("clicked on "
					+ invoiceListView.getSelectionModel().getSelectedItem());

			if (invoiceListView.getSelectionModel().getSelectedItem() != null) {
				 WindowHelper.openInvoiceInNewWindow(invoiceListView.getSelectionModel()
				 .getSelectedItem());
			}
			invoiceListView.getSelectionModel().clearSelection();
		}
	}

	@FXML
	public void handleEnter(KeyEvent ke) throws IOException {
		if (ke.getCode().equals(KeyCode.ENTER)) {
			if (searchInvoiceTextField.isFocused()
					|| searchDateFromTextField.isFocused()
					|| searchDateToTextField.isFocused()
					|| searchAmountFromTextField.isFocused()
					|| searchAmountToTextField.isFocused()) {
				handleSearchInvoice();
			}
		}
	}

}
