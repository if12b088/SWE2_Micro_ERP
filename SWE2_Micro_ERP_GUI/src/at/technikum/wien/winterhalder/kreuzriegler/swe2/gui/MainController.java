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

    @FXML // fx:id="searchDateFromTextField"
    private TextField searchDateFromTextField; // Value injected by FXMLLoader

    @FXML // fx:id="contactListView"
    private ListView<ContactDto> contactListView; // Value injected by FXMLLoader

    @FXML // fx:id="searchInvoiceButton"
    private Button searchInvoiceButton; // Value injected by FXMLLoader

    @FXML // fx:id="addContactButton"
    private Button addContactButton; // Value injected by FXMLLoader

    @FXML // fx:id="searchContactTextField"
    private TextField searchContactTextField; // Value injected by FXMLLoader

    @FXML // fx:id="searchInvoiceTextField"
    private TextField searchInvoiceTextField; // Value injected by FXMLLoader

    @FXML // fx:id="searchAmountFromTextField"
    private TextField searchAmountFromTextField; // Value injected by FXMLLoader

    @FXML // fx:id="searchAmountToTextField"
    private TextField searchAmountToTextField; // Value injected by FXMLLoader

    @FXML // fx:id="searchDateToTextField"
    private TextField searchDateToTextField; // Value injected by FXMLLoader

    @FXML // fx:id="addInvoiceButton"
    private Button addInvoiceButton; // Value injected by FXMLLoader

    @FXML // fx:id="invoiceListView"
    private ListView<?> invoiceListView; // Value injected by FXMLLoader

    @FXML // fx:id="searchContactButton"
    private Button searchContactButton; // Value injected by FXMLLoader

	ObservableList<ContactDto> items = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resources) {

		assert searchDateFromTextField != null : "fx:id=\"searchDateFromTextField\" was not injected: check your FXML file 'Main.fxml'.";
		assert contactListView != null : "fx:id=\"contactListView\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchInvoiceButton != null : "fx:id=\"searchInvoiceButton\" was not injected: check your FXML file 'Main.fxml'.";
		assert addContactButton != null : "fx:id=\"addContactButton\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchContactTextField != null : "fx:id=\"searchContactTextField\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchInvoiceTextField != null : "fx:id=\"searchInvoiceTextField\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchAmountFromTextField != null : "fx:id=\"searchAmountFromTextField\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchAmountToTextField != null : "fx:id=\"searchAmountToTextField\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchDateToTextField != null : "fx:id=\"searchDateToTextField\" was not injected: check your FXML file 'Main.fxml'.";
		assert addInvoiceButton != null : "fx:id=\"addInvoiceButton\" was not injected: check your FXML file 'Main.fxml'.";
		assert invoiceListView != null : "fx:id=\"invoiceListView\" was not injected: check your FXML file 'Main.fxml'.";
		assert searchContactButton != null : "fx:id=\"searchContactButton\" was not injected: check your FXML file 'Main.fxml'.";

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
				.getContactBySearchString(searchContactTextField.getText());

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
			openContactInNewWindow(contactListView.getSelectionModel()
					.getSelectedItem());
		}
	}

}
