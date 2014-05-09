package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.customControl.ContactPicker;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.ContactModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.InvoiceModel;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class AbstractController implements Initializable {
	private Stage stage;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage temp) {
		stage = temp;
	}

	@Override
	public void initialize(URL url, ResourceBundle resources) {
	}

	public void openInSameWindow(String resource, Pane mainWindow) {
		// remove existing content
		mainWindow.getChildren().clear();

		try {
			Pane child = FXMLLoader.load(this.getClass().getResource(resource));
			if (child != null) {
				mainWindow.getChildren().add(child);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openContactInNewWindow(ContactModel model) {
		ContactController controller = openInNewWindow("../view/Contact.fxml");
		controller.setModel(model);
	}

	public void openInvoiceInNewWindow(InvoiceModel model) {
		InvoiceController controller = openInNewWindow("../view/Invoice.fxml");
		controller.setModel(model);
	}

	public <TCONTROLLER> TCONTROLLER openInNewWindow(String resource) {
		Stage newStage = new Stage();
		try {
			FXMLLoader fl = new FXMLLoader();
			fl.setLocation(getClass().getResource(resource));
			fl.load();
			Parent root = fl.getRoot();
			// Pane root = (Pane) fl.load(getClass().getResource(resource));

			TCONTROLLER controller = fl.getController();

			Scene scene = new Scene(root, 600, 800);
			scene.getStylesheets().add(
					getClass().getResource("../application.css")
							.toExternalForm());
			newStage.setScene(scene);
			newStage.setTitle("Micro ERP");
			newStage.show();

			return controller;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}