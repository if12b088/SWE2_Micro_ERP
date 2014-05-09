package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.customControl;

import java.io.IOException;
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
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ConnectionProblemException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.helper.WindowHelper;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.ContactModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.ProxyFactory;

public class ContactSearch extends AnchorPane {

	@FXML
	private Label contactErrMsg;
	@FXML
	// fx:id="searchContactTextField"
	private TextField searchContactTextField; // Value injected by FXMLLoader
	@FXML
	// fx:id="searchContactButton"
	private Button searchContactButton; // Value injected by FXMLLoader
	@FXML
	// fx:id="addContactButton"
	private Button addContactButton; // Value injected by FXMLLoader
	@FXML
	// fx:id="contactListView"
	private ListView<ContactModel> contactListView; // Value injected by
													// FXMLLoader

	ObservableList<ContactModel> contactItems = FXCollections
			.observableArrayList();
	
	public ContactSearch() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"ContactSearch.fxml"));
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
		contactListView.setItems(contactItems);
	}

	@FXML
	public void handleNewContact() {
		 WindowHelper.openContactInNewWindow(new ContactModel());
//		 WindowHelper.openWindow();
	}
//
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
	public void handleDblClick(MouseEvent me) {
		if (me.getClickCount() == 2) {
			if (contactListView.getSelectionModel().getSelectedItem() != null) {
				 WindowHelper.openContactInNewWindow(contactListView.getSelectionModel()
				 .getSelectedItem());
			}
			contactListView.getSelectionModel().clearSelection();
		}
	}
//	
	@FXML
	public void handleEnter(KeyEvent ke) throws IOException {
		if (ke.getCode().equals(KeyCode.ENTER)) {
			if (searchContactTextField.isFocused()) {
				handleSearchContact();
			} 
		}
	}
	
	public void disableAddButton(){
		addContactButton.setDisable(true);
	}
	
	public void setModels(List<ContactModel> models){
		contactItems.clear();
		contactItems.addAll(models);
	}
}
