package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.customControl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

	EventHandler<MouseEvent> handleSearchContactsDblClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if (event.getClickCount() == 2) {
				if (contactListView.getSelectionModel().getSelectedItem() != null) {
					WindowHelper.openContactInNewWindow(contactListView
							.getSelectionModel().getSelectedItem());
				}
				contactListView.getSelectionModel().clearSelection();
			}
		}
	};

	EventHandler<ActionEvent> handleSearchContactsButton = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			handleSearchContacts();
		}
	};
	
	EventHandler<KeyEvent> handleSearchContactsEnter = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent ke) {
			if (ke.getCode().equals(KeyCode.ENTER)) {
				if (searchContactTextField.isFocused()) {
					handleSearchContacts();
				}
			}
			
		}
	};
	
	EventHandler<ActionEvent> handleSearchCompaniesButton = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			System.out.println("comp");
			handleSearchCompanies();
		}
	};
	
	EventHandler<KeyEvent> handleSearchCompaniesEnter = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent ke) {
			if (ke.getCode().equals(KeyCode.ENTER)) {
				if (searchContactTextField.isFocused()) {
					handleSearchCompanies();
				}
			}
			
		}
	};

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
		contactListView.addEventHandler(MouseEvent.MOUSE_CLICKED,
				handleSearchContactsDblClick);
		setSearchModeToContacts();
	}

	/**
	 * @return the contactListView
	 */
	public ListView<ContactModel> getContactListView() {
		return contactListView;
	}

	/**
	 * @param handleSearchContactsDblClick
	 *            replaces the old behavior of the action from the
	 *            doubleclickevent on the Listview
	 */

	public void setHandleSearchContactsDblClick(EventHandler<MouseEvent> newHandleDblClick) {
		// remove old handler
		contactListView.removeEventHandler(MouseEvent.MOUSE_CLICKED,
				handleSearchContactsDblClick);
		// set new handler
		contactListView.addEventHandler(MouseEvent.MOUSE_CLICKED,
				newHandleDblClick);
	}

	@FXML
	public void handleNewContact() {
		WindowHelper.openContactInNewWindow(new ContactModel());
		// WindowHelper.openWindow();
	}
	
	public void handleSearchContacts() {
		List<ContactDto> contacts = null;
		try {
			contacts = ProxyFactory
					.createContactProxy()
					.getContactsBySearchString(searchContactTextField.getText());
		} catch (ConnectionProblemException e) {
			contactErrMsg.setText(e.getMessage());
		}
		putContactsIntoList(contacts);
	}

	public void handleSearchCompanies() {
		List<ContactDto> contacts = null;
		try {
			contacts = ProxyFactory
					.createContactProxy()
					.getCompanysByName(searchContactTextField.getText());
		} catch (ConnectionProblemException e) {
			contactErrMsg.setText(e.getMessage());
		}
		putContactsIntoList(contacts);
	}

	private void putContactsIntoList(List<ContactDto> contacts) {
		if (contacts != null) {
			List<ContactModel> contactModels = new ArrayList<>();

			for (ContactDto cDto : contacts) {
				ContactModel cModel = new ContactModel();
				cModel.setDto(cDto);
				contactModels.add(cModel);
			}

			contactItems.clear();
			contactItems.addAll(contactModels);
		}
	}

	public void disableAddButton() {
		addContactButton.setDisable(true);
	}

	public void setSearchModeToContacts(){
		searchContactButton.removeEventHandler(ActionEvent.ACTION, handleSearchCompaniesButton);
		searchContactTextField.removeEventHandler(KeyEvent.KEY_PRESSED, handleSearchCompaniesEnter);
		searchContactButton.addEventHandler(ActionEvent.ACTION, handleSearchContactsButton);
		searchContactTextField.addEventHandler(KeyEvent.KEY_PRESSED, handleSearchContactsEnter);
	}
	
	public void setSearchModeToCompany(){
		searchContactButton.removeEventHandler(ActionEvent.ACTION, handleSearchContactsButton);
		searchContactTextField.removeEventHandler(KeyEvent.KEY_PRESSED, handleSearchContactsEnter);
		searchContactButton.addEventHandler(ActionEvent.ACTION, handleSearchCompaniesButton);
		searchContactTextField.addEventHandler(KeyEvent.KEY_PRESSED, handleSearchCompaniesEnter);
	}
	

	public void setModels(List<ContactModel> models) {
		contactItems.clear();
		contactItems.addAll(models);
	}
}
