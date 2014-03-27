package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.ContactModel;

public class ContactController implements Initializable{
	
	//panes
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
	
	//company
	@FXML
	private TextField companyName;
	@FXML
	private TextField UID;
	
	//person
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField suffix;
	@FXML
	private TextField fkCompany;
	@FXML
	private TextField birthday;
	
	//address
	@FXML
	private TextField addressAddress;
	@FXML
	private TextField addressZIP;
	@FXML
	private TextField addressCity;
	
	//shippingaddress
	@FXML
	private TextField shippingAddressAddress;
	@FXML
	private TextField shippingAddressZIP;
	@FXML
	private TextField shippingAddressCity;
	
	//invoiceaddress
	@FXML
	private TextField invoiceAddressAddress;
	@FXML
	private TextField invoiceAddressZIP;
	@FXML
	private TextField invoiceAddressCity;
	

	@FXML
	private Button saveBtn;
	
	@FXML
	private void onSave(ActionEvent event){
		//TODO saveToDB
		System.out.println("saveBtn");
	}

	ContactModel model;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		model = new ContactModel();
		
		personPane.disableProperty().bind(model.disableEditPersonBinding());
		companyPane.disableProperty().bind(model.disableEditCompanyBinding());
		
		companyName.textProperty().bindBidirectional(model.companyNameProperty());
		UID.textProperty().bindBidirectional(model.UIDProperty());
		
		firstName.textProperty().bindBidirectional(model.firstNameProperty());
		lastName.textProperty().bindBidirectional(model.lastNameProperty());
		suffix.textProperty().bindBidirectional(model.suffixProperty());
		birthday.textProperty().bindBidirectional(model.birthDateProperty());
		fkCompany.textProperty().bindBidirectional(model.fkCompanyProperty());
		
		addressAddress.textProperty().bindBidirectional(model.addressAddressProperty());
		addressZIP.textProperty().bindBidirectional(model.addressZIPProperty());
		addressCity.textProperty().bindBidirectional(model.addressCityProperty());
		
		shippingAddressAddress.textProperty().bindBidirectional(model.shippingAddressAddressProperty());
		shippingAddressZIP.textProperty().bindBidirectional(model.shippingAddressZIPProperty());
		shippingAddressCity.textProperty().bindBidirectional(model.shippingAddressCityProperty());
		
		invoiceAddressAddress.textProperty().bindBidirectional(model.invoiceAddressAddressProperty());
		invoiceAddressZIP.textProperty().bindBidirectional(model.invoiveAddressZIPProperty());
		invoiceAddressCity.textProperty().bindBidirectional(model.invoiceAddressCityProperty());
		
//		saveBtn.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				
//				//TODO saveToDB
//				System.out.println("saveBtn");
//				
//			}
//		});
		
	}

	public void setDto(ContactDto dto) {
		model.setDto(dto);
		
	}
}
