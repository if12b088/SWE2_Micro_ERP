package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.customControl.ContactSearch;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.customControl.InvoiceSearch;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.helper.WindowHelper;

public class MainController extends AbstractController {

	@FXML
	private ContactSearch contactPane;
	@FXML
	private InvoiceSearch invoicePane;

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		super.initialize(url, resources);

//		contactPane.setDblClickHandler(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				System.out.println("baba");
//				if (event.getClickCount() == 2) {
//					if (contactPane.getContactListView().getSelectionModel()
//							.getSelectedItem() != null) {
//						WindowHelper.openContactInNewWindow(contactPane
//								.getContactListView().getSelectionModel()
//								.getSelectedItem());
//					}
//					contactPane.getContactListView().getSelectionModel()
//							.clearSelection();
//				}
//			}
//		});
	}
}
