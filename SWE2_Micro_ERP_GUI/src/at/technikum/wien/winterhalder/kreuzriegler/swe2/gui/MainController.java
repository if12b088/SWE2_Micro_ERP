package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController extends AbstractController {

	@FXML
	private Pane mainWindow;
	
	@FXML
	public void handleNewContactMenu() throws IOException {

		openInNewWindow("view/Contact.fxml");
	}
}
