package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.customControl.ContactPicker;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ConnectionProblemException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.InvoiceWasNotCreatedOrUpdatedException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.InvoiceModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.InvoiceRowModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.ProxyFactory;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.tableCells.ButtonCell;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.tableCells.DoubleCell;

public class InvoiceController {

	@FXML
	private TitledPane metaPane;
	@FXML
	private TitledPane rowPane;

	@FXML
	// ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;
	@FXML
	// URL location of the FXML file that was given to the FXMLLoader
	private URL location;
	@FXML
	// fx:id="invoiceRowName"
	private TextField invoiceRowName; // Value injected by FXMLLoader
	@FXML
	private ContactPicker contactPicker;
	@FXML
	// fx:id="invoiceRowAmount"
	private TextField invoiceRowAmount; // Value injected by FXMLLoader
	@FXML
	// fx:id="invoiceComment"
	private TextArea invoiceComment; // Value injected by FXMLLoader
	@FXML
	// fx:id="invoiceRowNameTableColumn"
	private TableColumn<InvoiceRowModel, String> invoiceRowNameTableColumn; // Value
																			// injected
																			// by
	// FXMLLoader
	@FXML
	// fx:id="invoiceRowAmountTableColumn"
	private TableColumn<InvoiceRowModel, Double> invoiceRowAmountTableColumn; // Value
	// FXMLLoader
	@FXML
	// fx:id="invoiceRowUstTableColumn"
	private TableColumn<InvoiceRowModel, Double> invoiceRowUstTableColumn; // Value
																			// injected
																			// by
	// FXMLLoader
	@FXML
	// fx:id="invoiceRowPriceTableColumn"
	private TableColumn<InvoiceRowModel, Double> invoiceRowPriceTableColumn; // Value
																				// injected
																				// by
	@FXML
	// fx:id="invoiceRowDeleteTableColumn"
	private TableColumn<InvoiceRowModel, Boolean> invoiceRowDeleteTableColumn; // Value
																				// injected
	@FXML
	// fx:id="invoiceRowPrice"
	private TextField invoiceRowPrice; // Value injected by FXMLLoader

	@FXML
	// fx:id="invoiceRowRemoveButton"
	private Button invoiceRowRemoveButton; // Value injected by FXMLLoader

	@FXML
	// fx:id="invoiceInformation"
	private TextArea invoiceInformation; // Value injected by FXMLLoader

	@FXML
	// fx:id="invoiceRowTableView"
	private TableView<InvoiceRowModel> invoiceRowTableView; // Value injected by
															// FXMLLoader

	@FXML
	// fx:id="invoiceRowAddButton"
	private Button invoiceRowAddButton; // Value injected by FXMLLoader

	@FXML
	// fx:id="invoiceNumber"
	private TextField invoiceNumber; // Value injected by FXMLLoader

	@FXML
	// fx:id="invoiceRowUst"
	private TextField invoiceRowUst; // Value injected by FXMLLoader

	@FXML
	private Button saveInvoice;
	@FXML
	private Button abortInvoice;
	@FXML
	private Button printInvoice;

	@FXML
	private Label errMsg;

	private InvoiceModel model;

	@FXML
	// This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		contactPicker.setSearchTypeToContacts();
	}

	public void setModel(final InvoiceModel model) {

		this.model = model;

		if (model.getContact() != null) {
			contactPicker.getModel().setSelectedContact(model.getContact());
		}
		metaPane.disableProperty().bind(model.isLockedBinding());
		rowPane.disableProperty().bind(model.isLockedBinding());

		errMsg.textProperty().bind(model.errorMsgProperty());
		invoiceNumber.textProperty().bindBidirectional(model.nrProperty());
		invoiceInformation.textProperty().bindBidirectional(
				model.informationProperty());
		invoiceComment.textProperty()
				.bindBidirectional(model.commentProperty());

		// TableColumn NAME
		invoiceRowNameTableColumn
				.setCellValueFactory(new PropertyValueFactory<InvoiceRowModel, String>(
						"name"));
		invoiceRowNameTableColumn.setCellFactory(TextFieldTableCell
				.<InvoiceRowModel> forTableColumn());
		invoiceRowNameTableColumn
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<InvoiceRowModel, String>>() {

					@Override
					public void handle(
							CellEditEvent<InvoiceRowModel, String> event) {
						((InvoiceRowModel) event.getTableView().getItems()
								.get(event.getTablePosition().getRow()))
								.setName(event.getNewValue());
					}
				});

		// TableColumn AMOUNT
		invoiceRowAmountTableColumn
				.setCellValueFactory(new PropertyValueFactory<InvoiceRowModel, Double>(
						"amount"));
		invoiceRowAmountTableColumn
				.setCellFactory(new Callback<TableColumn<InvoiceRowModel, Double>, TableCell<InvoiceRowModel, Double>>() {

					@Override
					public TableCell<InvoiceRowModel, Double> call(
							TableColumn<InvoiceRowModel, Double> param) {
						return new DoubleCell();
					}
				});
		invoiceRowAmountTableColumn
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<InvoiceRowModel, Double>>() {

					@Override
					public void handle(
							CellEditEvent<InvoiceRowModel, Double> event) {
						((InvoiceRowModel) event.getTableView().getItems()
								.get(event.getTablePosition().getRow()))
								.setAmount(event.getNewValue());
					}
				});

		// TableColumn UST
		invoiceRowUstTableColumn
				.setCellValueFactory(new PropertyValueFactory<InvoiceRowModel, Double>(
						"ust"));
		invoiceRowUstTableColumn
				.setCellFactory(new Callback<TableColumn<InvoiceRowModel, Double>, TableCell<InvoiceRowModel, Double>>() {

					@Override
					public TableCell<InvoiceRowModel, Double> call(
							TableColumn<InvoiceRowModel, Double> param) {
						return new DoubleCell();
					}
				});
		invoiceRowUstTableColumn
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<InvoiceRowModel, Double>>() {

					@Override
					public void handle(
							CellEditEvent<InvoiceRowModel, Double> event) {
						((InvoiceRowModel) event.getTableView().getItems()
								.get(event.getTablePosition().getRow()))
								.setUst(event.getNewValue());
					}
				});

		// TableColumn PRICE
		invoiceRowPriceTableColumn
				.setCellValueFactory(new PropertyValueFactory<InvoiceRowModel, Double>(
						"price"));
		invoiceRowPriceTableColumn
				.setCellFactory(new Callback<TableColumn<InvoiceRowModel, Double>, TableCell<InvoiceRowModel, Double>>() {

					@Override
					public TableCell<InvoiceRowModel, Double> call(
							TableColumn<InvoiceRowModel, Double> param) {
						return new DoubleCell();
					}
				});
		invoiceRowPriceTableColumn
				.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<InvoiceRowModel, Double>>() {

					@Override
					public void handle(
							CellEditEvent<InvoiceRowModel, Double> event) {
						((InvoiceRowModel) event.getTableView().getItems()
								.get(event.getTablePosition().getRow()))
								.setPrice(event.getNewValue());
					}
				});

		// TableColumn DELETE
		invoiceRowDeleteTableColumn.setSortable(false);
		invoiceRowDeleteTableColumn
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<InvoiceRowModel, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(
							TableColumn.CellDataFeatures<InvoiceRowModel, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		invoiceRowDeleteTableColumn
				.setCellFactory(new Callback<TableColumn<InvoiceRowModel, Boolean>, TableCell<InvoiceRowModel, Boolean>>() {

					@Override
					public TableCell<InvoiceRowModel, Boolean> call(
							TableColumn<InvoiceRowModel, Boolean> p) {
						return new ButtonCell(model.getRows());
					}

				});

		invoiceRowTableView.setEditable(true);
		invoiceRowTableView.setItems(model.getRows());
	}

	// Add Row
	@FXML
	public void handleAddRow() throws IOException {
		InvoiceRowModel row = new InvoiceRowModel();

		if (invoiceRowName.getText().isEmpty()) {
			row.setName("");
		} else {
			row.setName(invoiceRowName.getText());
		}

		if (invoiceRowAmount.getText().isEmpty()) {
			row.setAmount(0d);
		} else {
			row.setAmount(Double.parseDouble(invoiceRowAmount.getText()));
		}

		if (invoiceRowUst.getText().isEmpty()) {
			row.setUst(0d);
		} else {
			row.setUst(Double.parseDouble(invoiceRowUst.getText()));
		}

		if (invoiceRowPrice.getText().isEmpty()) {
			row.setPrice(0d);
		} else {
			row.setPrice(Double.parseDouble(invoiceRowPrice.getText()));
		}

		model.getRows().add(row);
	}

	@FXML
	public void onSaveInvoice() {
		try {
			InvoiceDto iDto = model.getinvoiceDto();
			ProxyFactory.createInvoiceProxy().CreateOrUpdateInvoice(iDto);
		} catch (ConnectionProblemException
				| InvoiceWasNotCreatedOrUpdatedException e) {
			model.setErrorMsg(e.getMessage());
		}
	}

	@FXML
	public void handlePrintInvoice() {
		model.printInvoice();
	}

}
