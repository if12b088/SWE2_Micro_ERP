package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.customControl.CustomControl;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.ContactModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.InvoiceModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.ProxyFactory;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.request.CreateOrUpdateContactRequest;

public class ContactController extends AbstractController {

	//tabs	
	@FXML
	private TabPane tabs;
	@FXML 
	private Tab basisdaten;
	@FXML
	private Tab rechnungen;

	// panes
	@FXML
	private TitledPane personPane;
	@FXML
	private TitledPane companyPane;
	@FXML
	private TitledPane addressPane;
	@FXML
	private TitledPane shippingAddressPane;
	@FXML
	private TitledPane invoiceAddressPane;

	// company
	@FXML
	private TextField companyName;
	@FXML
	private TextField UID;

	// person
	@FXML
	private TextField title;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField suffix;
//	@FXML
//	private TextField fkCompany;
	@FXML
	private HBox customHbox;
	
	@FXML
	private TextField birthday;

	// address
	@FXML
	private TextField addressAddress;
	@FXML
	private TextField addressZIP;
	@FXML
	private TextField addressCity;

	// shippingaddress
	@FXML
	private TextField shippingAddressAddress;
	@FXML
	private TextField shippingAddressZIP;
	@FXML
	private TextField shippingAddressCity;

	// invoiceaddress
	@FXML
	private TextField invoiceAddressAddress;
	@FXML
	private TextField invoiceAddressZIP;
	@FXML
	private TextField invoiceAddressCity;
	@FXML
	private Button saveBtn;
	
	// Invoice
	@FXML
	private ListView<InvoiceModel> invoiceListView;

	ContactModel model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setModel(new ContactModel());
	}

//	public void setDto(ContactDto dto) {
//		model.setDto(dto);
//	}
	
	ObservableList<InvoiceModel> invoices = FXCollections.observableArrayList();
	
	public void setModel(ContactModel model){
		this.model = model;
		
		personPane.disableProperty().bind(model.disableEditPersonBinding());
		companyPane.disableProperty().bind(model.disableEditCompanyBinding());

//		 shippingAddressPane.expandedProperty().bind(model.hasShippingAddressBinding());
//		 invoiceAddressPane.expandedProperty().bind(model.hasInvoiceAddressBinding());
//	     shippingAddressPane.expandedProperty().bindBidirectional((Property<Boolean>) model.hasShippingAddressBinding());
		
		companyName.textProperty().bindBidirectional(
				model.companyNameProperty());
		UID.textProperty().bindBidirectional(model.UIDProperty());

		firstName.textProperty().bindBidirectional(model.firstNameProperty());
		lastName.textProperty().bindBidirectional(model.lastNameProperty());
		suffix.textProperty().bindBidirectional(model.suffixProperty());
		birthday.textProperty().bindBidirectional(model.birthDateProperty());
	//	fkCompany.textProperty().bindBidirectional(model.fkCompanyProperty());

		addressAddress.textProperty().bindBidirectional(
				model.addressAddressProperty());
		addressZIP.textProperty().bindBidirectional(model.addressZIPProperty());
		addressCity.textProperty().bindBidirectional(
				model.addressCityProperty());

		shippingAddressAddress.textProperty().bindBidirectional(
				model.shippingAddressAddressProperty());
		shippingAddressZIP.textProperty().bindBidirectional(
				model.shippingAddressZIPProperty());
		shippingAddressCity.textProperty().bindBidirectional(
				model.shippingAddressCityProperty());

		invoiceAddressAddress.textProperty().bindBidirectional(
				model.invoiceAddressAddressProperty());
		invoiceAddressZIP.textProperty().bindBidirectional(
				model.invoiveAddressZIPProperty());
		invoiceAddressCity.textProperty().bindBidirectional(
				model.invoiceAddressCityProperty());
		
		invoiceListView.setItems(invoices);
	}

	@FXML
	private void onSave(ActionEvent event) {
		ContactDto cDto = model.getContactDto();
		
		System.out.println("saveBtn");
	}
	
	@FXML
	private void loadInvoices(){
		List<InvoiceDto> invoiceDtos = ProxyFactory.createInvoiceProxy().getInvoicesByContactId(1);
		
		for(InvoiceDto dto : invoiceDtos){
			InvoiceModel IModel = new InvoiceModel();
			IModel.setDto(dto);
			invoices.add(IModel);
		}
		
		
		model.setInvoices(invoices);
		
		System.out.println("Tabed");
	}
	
	@FXML
	public void handleDblClick(MouseEvent me) {
		if (me.getClickCount() == 2) {
			System.out.println("clicked on "
					+ invoiceListView.getSelectionModel().getSelectedItem());
			openInvoiceInNewWindow(invoiceListView.getSelectionModel()
					.getSelectedItem());
		}
	}
}
