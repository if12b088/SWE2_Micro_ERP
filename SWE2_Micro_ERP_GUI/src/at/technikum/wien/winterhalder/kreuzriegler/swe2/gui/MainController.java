package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.ProxyFactory;

public class MainController extends AbstractController {

	@FXML
	private Pane mainWindow;

	@FXML
	private Button searchButton;

	@FXML
	private Button addContactButton;

	@FXML
	private TextField searchKontaktTextField;

	@FXML
	private ListView<ContactDto> contactListView;

	ObservableList<ContactDto> items = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resources) {

		assert contactListView != null : "fx:id=\"contactListView\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchKontaktTextField != null : "fx:id=\"searchKontaktTextField\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'Main.fxml'.";
		assert addContactButton != null : "fx:id=\"addKontaktButton\" was not injected: check your FXML file 'Main.fxml'.";

		super.initialize(url, resources);
		contactListView.setItems(items);
	}

	@FXML
	public void handleNewContact() throws IOException {
		openContactInNewWindow(new ContactDto());
	}

	@FXML
	public void handleSearch() throws IOException {
		List<ContactDto> contacts = ProxyFactory.createContactProxy()
				.getContactBySearchString(searchKontaktTextField.getText());

		items.clear();
		items.addAll(contacts);
		System.out.println("search");
	}

	@FXML
	public void handleEnter(KeyEvent ke) throws IOException {
		if (ke.getCode().equals(KeyCode.ENTER)) {
			handleSearch();
		}
	}

	@FXML
	public void handleDblClick(MouseEvent me) {
		if (me.getClickCount() == 2) {
			System.out.println("clicked on "
					+ contactListView.getSelectionModel().getSelectedItem());
			openContactInNewWindow(contactListView.getSelectionModel().getSelectedItem());
		}
	}

}
