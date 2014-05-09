/**
 * 
 */
package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.controller.AbstractController;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.controller.ContactController;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.controller.InvoiceController;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.ContactModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.InvoiceModel;

/**
 * @author richie
 * 
 */
public class WindowHelper {


	public static void openContactInNewWindow(ContactModel model) {
		ContactController controller = openInNewWindow("../view/Contact.fxml");
		controller.setModel(model);
	}

	public static void openInvoiceInNewWindow(InvoiceModel model) {
		InvoiceController controller = openInNewWindow("../view/Invoice.fxml");
		controller.setModel(model);
	}
	
	public static <TCONTROLLER> TCONTROLLER openInNewWindow(String resource) {
		Stage newStage = new Stage();
		try {
			// Load the fxml file and set into the center of the main layout
			FXMLLoader loader = new FXMLLoader(
					AbstractController.class.getResource(resource));
			Parent root = (Parent) loader.load();

			// Give the controller access to the main app
			TCONTROLLER controller = loader.getController();
			
			Scene scene = new Scene(root, 600, 800);
			newStage.setScene(scene);
			newStage.setTitle("Micro ERP");
			newStage.show();
			
			return controller;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	public static void openWindow() {
//		Stage newStage = new Stage();
//		try {
//			// Load the fxml file and set into the center of the main layout
//			FXMLLoader loader = new FXMLLoader(
//					AbstractController.class.getResource("../view/Contact.fxml"));
//			Parent root = (Parent) loader.load();
//
//			// Give the controller access to the main app
//			ContactController controller = loader.getController();
//			
//			Scene sc = new Scene(root, 600, 800);
//			newStage.setScene(sc);
//			newStage.show();
//			
//		} catch (IOException e) {
//			// Exception gets thrown if the fxml file could not be loaded
//			e.printStackTrace();
//		}
//	}

}
