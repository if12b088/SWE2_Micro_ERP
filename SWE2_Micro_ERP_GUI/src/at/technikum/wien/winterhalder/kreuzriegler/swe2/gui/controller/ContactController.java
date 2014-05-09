package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.Constants;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.customControl.ContactPicker;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ConnectionProblemException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ContactWasNotCreatedOrUpdatedException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.helper.DateHelper;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.ContactModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.InvoiceModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.ProxyFactory;

public class ContactController extends AbstractController {

	// tabs
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
	// @FXML
	// private TextField fkCompany;
	@FXML
	private TextField birthday;
	@FXML
	private ContactPicker contactPicker;

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
	@FXML
	private Button abortBtn;
	@FXML
	private Label errMsg;

	// Invoice
	@FXML
	private ListView<InvoiceModel> invoiceListView;

	ContactModel model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setModel(new ContactModel());
	}

	// public void setDto(ContactDto dto) {
	// model.setDto(dto);
	// }

	ObservableList<InvoiceModel> invoices = FXCollections.observableArrayList();

	public void setModel(final ContactModel model) {
		this.model = model;

		personPane.disableProperty().bind(model.disableEditPersonBinding());
		companyPane.disableProperty().bind(model.disableEditCompanyBinding());

		// shippingAddressPane.expandedProperty().bind(model.hasShippingAddressBinding());
		// invoiceAddressPane.expandedProperty().bind(model.hasInvoiceAddressBinding());
		// shippingAddressPane.expandedProperty().bindBidirectional((Property<Boolean>)
		// model.hasShippingAddressBinding());

		companyName.textProperty().bindBidirectional(
				model.companyNameProperty());
		UID.textProperty().bindBidirectional(model.UIDProperty());

		title.textProperty().bindBidirectional(model.titleProperty());
		firstName.textProperty().bindBidirectional(model.firstNameProperty());
		lastName.textProperty().bindBidirectional(model.lastNameProperty());
		suffix.textProperty().bindBidirectional(model.suffixProperty());
		birthday.textProperty().bindBidirectional(model.birthDateProperty());

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

		// contactPicker.getContactPickerImageView().setImage(
		// new Image(Constants.IMAGE_ERR));
		//
		// contactPicker.getContactPickerButtonSearch().setOnAction(
		// new EventHandler<ActionEvent>() {
		//
		// @Override
		// public void handle(ActionEvent event) {
		// List<ContactDto> companies = null;
		// try {
		// companies = ProxyFactory.createContactProxy()
		// .getCompanysByName(contactPicker.getText());
		// } catch (ConnectionProblemException e) {
		// errMsg.setText(e.getMessage());
		// }
		// if (companies != null) {
		// if (companies.size() == 1) {
		// model.setCompanyReference(companies.get(0));
		// contactPicker.setText(model
		// .getCompanyReference().toString());
		// contactPicker
		// .getContactPickerImageView()
		// .setImage(new Image(Constants.IMAGE_OK));
		// } else {
		// contactPicker.openPopup(companies, model);
		// }
		// }
		// }
		// });
	}

	@FXML
	private void onSaveContact(ActionEvent event) {
		ContactDto cDto = model.getContactDto();

		if (birthday.getText() != null) {
			try {
				cDto.setBirthday(DateHelper.validateDate(birthday.getText()).getTime());
			} catch (ParseException e) {
				errMsg.setText("Dieses Datum ist nicht Korrekt");
				return;
			}

		}
		try {
			ProxyFactory.createContactProxy().createOrUpdateContact(cDto);
		} catch (ConnectionProblemException
				| ContactWasNotCreatedOrUpdatedException e) {
			errMsg.setText(e.getMessage());
		}
	}

	@FXML
	private void loadInvoices() {
		List<InvoiceDto> invoiceDtos = null;
		try {
			invoiceDtos = ProxyFactory.createInvoiceProxy()
					.getInvoicesByContactId(1);
		} catch (ConnectionProblemException e) {
			errMsg.setText(e.getMessage());
		}

		if (invoiceDtos != null) {
			for (InvoiceDto dto : invoiceDtos) {
				InvoiceModel IModel = new InvoiceModel();
				IModel.setDto(dto);
				invoices.add(IModel);
			}
			model.setInvoices(invoices);
		}

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
