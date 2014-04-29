package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.ContactModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.InvoiceModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.InvoiceRowModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.ProxyFactory;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.tableCells.ButtonCell;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.tableCells.DoubleCell;

import com.sun.prism.impl.Disposer.Record;

public class InvoiceController {

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
																				// injected
																				// by
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
																				// by

	// FXMLLoader

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

	private InvoiceModel model;

	@FXML
	// This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert invoiceRowName != null : "fx:id=\"invoiceRowName\" was not injected: check your FXML file 'Invoice.fxml'.";
		assert invoiceRowAmount != null : "fx:id=\"invoiceRowAmount\" was not injected: check your FXML file 'Invoice.fxml'.";
		assert invoiceRowPriceTableColumn != null : "fx:id=\"invoiceRowPriceTableColumn\" was not injected: check your FXML file 'Invoice.fxml'.";
		assert invoiceComment != null : "fx:id=\"invoiceComment\" was not injected: check your FXML file 'Invoice.fxml'.";
		assert invoiceRowAmountTableColumn != null : "fx:id=\"invoiceRowAmountTableColumn\" was not injected: check your FXML file 'Invoice.fxml'.";
		assert invoiceRowPrice != null : "fx:id=\"invoiceRowPrice\" was not injected: check your FXML file 'Invoice.fxml'.";
		assert invoiceRowRemoveButton != null : "fx:id=\"invoiceRowRemoveButton\" was not injected: check your FXML file 'Invoice.fxml'.";
		assert invoiceRowUstTableColumn != null : "fx:id=\"invoiceRowUstTableColumn\" was not injected: check your FXML file 'Invoice.fxml'.";
		assert invoiceInformation != null : "fx:id=\"invoiceInformation\" was not injected: check your FXML file 'Invoice.fxml'.";
		assert invoiceRowTableView != null : "fx:id=\"invoiceRowTableView\" was not injected: check your FXML file 'Invoice.fxml'.";
		assert invoiceRowAddButton != null : "fx:id=\"invoiceRowAddButton\" was not injected: check your FXML file 'Invoice.fxml'.";
		assert invoiceRowNameTableColumn != null : "fx:id=\"invoiceRowNameTableColumn\" was not injected: check your FXML file 'Invoice.fxml'.";
		assert invoiceNumber != null : "fx:id=\"invoiceNumber\" was not injected: check your FXML file 'Invoice.fxml'.";
		assert invoiceRowUst != null : "fx:id=\"invoiceRowUst\" was not injected: check your FXML file 'Invoice.fxml'.";

	}

	public void setModel(final InvoiceModel model) {

		this.model = model;

		InvoiceRowModel row1 = new InvoiceRowModel("Posten 1", 3.0, 20.0, 30.0);
		InvoiceRowModel row2 = new InvoiceRowModel("Posten 2", 10.0, 20.0, 45.0);
		InvoiceRowModel row3 = new InvoiceRowModel("Posten 3", 1.0, 20.0, 130.0);
		InvoiceRowModel row4 = new InvoiceRowModel("Posten 4", 5.0, 20.0, 50.0);

		this.model.getRows().add(row1);
		this.model.getRows().add(row2);
		this.model.getRows().add(row3);
		this.model.getRows().add(row4);

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
	//Add Row
	@FXML
	public void handleAddRow() throws IOException {
		InvoiceRowModel row = new InvoiceRowModel();
		
		if(invoiceRowName.getText().isEmpty()){
			row.setName("");
		}else{
			row.setName(invoiceRowName.getText());
		}
		
		if(invoiceRowAmount.getText().isEmpty()){
			row.setAmount(0d);
		}else{
			row.setAmount(Double.parseDouble(invoiceRowAmount.getText()));
		}
		
		if(invoiceRowUst.getText().isEmpty()){
			row.setUst(0d);
		}else{
			row.setUst(Double.parseDouble(invoiceRowUst.getText()));
		}
		
		if(invoiceRowPrice.getText().isEmpty()){
			row.setPrice(0d);
		}else{
			row.setPrice(Double.parseDouble(invoiceRowPrice.getText()));
		}
		
		model.getRows().add(row);
	}
	
}
