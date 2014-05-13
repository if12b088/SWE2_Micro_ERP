/**
 * 
 */
package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.customControl;

/**
 * @author richie
 *
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.enums.ContactPickerSearchType;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ConnectionProblemException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.ContactModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.ProxyFactory;

public class ContactPicker extends HBox {
	@FXML
	private ImageView contactPickerImageView;
	@FXML
	private TextField contactPickerTextField;
	@FXML
	private Button contactPickerButtonSearch;
	@FXML
	private Button contactPickerButtonRemove;
	@FXML
	private ListView<ContactDto> contactPickerListView;

	private ContactPickerModel model = new ContactPickerModel();

	private ContactPickerSearchType searchType = ContactPickerSearchType.CONTACTS;

	public ContactPicker() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"ContactPicker.fxml"));
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
		contactPickerTextField.textProperty().bindBidirectional(
				model.textProperty());
		contactPickerImageView.imageProperty().bindBidirectional(
				model.imageProperty());
		
	}

	public ContactPickerModel getModel() {
		return this.model;
	}

	public void openPopup(List<ContactModel> contactModels) {
		ContactSearch contactPane = new ContactSearch();
		contactPane.setSearchModeToSelect();
		if (contactModels == null) {
			contactModels = new ArrayList<>();
		}

		final Stage newStage = new Stage();
		
		if(searchType == ContactPickerSearchType.CONTACTS){
			contactPane.setSearchTypeToContacts();
		}
		if(searchType == ContactPickerSearchType.COMPANIES){
			contactPane.setSearchTypeToCompany();			
		}

		contactPane.setModels(contactModels);
		contactPane.disableAddButton();
		contactPane.setStage(newStage);
		Scene scene = new Scene(contactPane, 800, 600);
		newStage.setScene(scene);
		newStage.setTitle("Suche Kontakt");
		newStage.showAndWait();
		model.setSelectedContact(contactPane.getModel().getSelectedModel());
		contactPane = null;

	}

	@FXML
	public void handleSearch() {

		if (model.getText() == null || model.getText() == "") {
			openPopup(null);
		} else {
			List<ContactDto> contacts = null;
			try {
				if(searchType == ContactPickerSearchType.CONTACTS){
					contacts = ProxyFactory.createContactProxy()
							.getContactsByName(model.getText());
				}
				if(searchType == ContactPickerSearchType.COMPANIES){
					contacts = ProxyFactory.createContactProxy()
							.getCompanysByName(model.getText());
				}
			} catch (ConnectionProblemException e) {
				// errMsg.setText(e.getMessage());
			}
			if (contacts != null) {

				List<ContactModel> contactModels = new ArrayList<>();

				for (ContactDto cDto : contacts) {
					ContactModel cModel = new ContactModel();
					cModel.setDto(cDto);
					contactModels.add(cModel);
				}
				if (contactModels.size() == 0) {
					openPopup(null);
				} else if (contactModels.size() == 1) {
					model.setSelectedContact(contactModels.get(0));
				} else {
					openPopup(contactModels);
				}
			}
		}
	}

	@FXML
	public void handleRemove() {
		model.setErr();
		model.setText("");
		model.setSelectedContact(null);
	}
	
	public void setSearchTypeToContacts(){
		this.searchType = ContactPickerSearchType.CONTACTS;
	}
	public void setSearchTypeToCompanies(){
		this.searchType = ContactPickerSearchType.COMPANIES;
	}

}