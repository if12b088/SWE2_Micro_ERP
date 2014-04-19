package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.controller;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class InvoiceController implements Initializable{

    @FXML // fx:id="invoiceRowName"
    private TextField invoiceRowName; // Value injected by FXMLLoader

    @FXML // fx:id="invoiceRowPrice"
    private TextField invoiceRowPrice; // Value injected by FXMLLoader

    @FXML // fx:id="invoiceRowRemoveButton"
    private Button invoiceRowRemoveButton; // Value injected by FXMLLoader

    @FXML // fx:id="invoiceInformation"
    private TextArea invoiceInformation; // Value injected by FXMLLoader

    @FXML // fx:id="invoiceRowListView"
    private ListView<?> invoiceRowListView; // Value injected by FXMLLoader

    @FXML // fx:id="invoiceRowAmount"
    private TextField invoiceRowAmount; // Value injected by FXMLLoader

    @FXML // fx:id="invoiceRowAddButton"
    private Button invoiceRowAddButton; // Value injected by FXMLLoader

    @FXML // fx:id="invoiceNumber"
    private TextField invoiceNumber; // Value injected by FXMLLoader

    @FXML // fx:id="invoiceRowUst"
    private TextField invoiceRowUst; // Value injected by FXMLLoader

    @FXML // fx:id="invoiceComment"
    private TextArea invoiceComment; // Value injected by FXMLLoader

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        assert invoiceRowName != null : "fx:id=\"invoiceRowName\" was not injected: check your FXML file 'Invoice.fxml'.";
        assert invoiceRowPrice != null : "fx:id=\"invoiceRowPrice\" was not injected: check your FXML file 'Invoice.fxml'.";
        assert invoiceRowRemoveButton != null : "fx:id=\"invoiceRowRemoveButton\" was not injected: check your FXML file 'Invoice.fxml'.";
        assert invoiceInformation != null : "fx:id=\"invoiceInformation\" was not injected: check your FXML file 'Invoice.fxml'.";
        assert invoiceRowListView != null : "fx:id=\"invoiceRowListView\" was not injected: check your FXML file 'Invoice.fxml'.";
        assert invoiceRowAmount != null : "fx:id=\"invoiceRowAmount\" was not injected: check your FXML file 'Invoice.fxml'.";
        assert invoiceRowAddButton != null : "fx:id=\"invoiceRowAddButton\" was not injected: check your FXML file 'Invoice.fxml'.";
        assert invoiceNumber != null : "fx:id=\"invoiceNumber\" was not injected: check your FXML file 'Invoice.fxml'.";
        assert invoiceRowUst != null : "fx:id=\"invoiceRowUst\" was not injected: check your FXML file 'Invoice.fxml'.";
        assert invoiceComment != null : "fx:id=\"invoiceComment\" was not injected: check your FXML file 'Invoice.fxml'.";

    }
}

