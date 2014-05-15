package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * TODO
 * Rechnung Drucken, Pfad?
 * NullChecks
 * Bei Speichern Fenster schliessen
 * Bei Abbrechen Fenster Schliessen
 * Alle DTOs Checken (Testen) Speichern und laden
 * Rechnung BRUTTO + gesammtpreis beim Tippen
 * Constants in Properties File Matthias auch
 * Doku
 * CodeCoverage
 * CodeSyle Checkstyle
 * */

public class Main extends Application {
	
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	
	@Override
	public void start(Stage primaryStage) {
		try {
//			logger.entering(Main.class.getName(), "start");
//			logger.log(Level.INFO, "buh");
//			logger.severe("huhu");
//			logger.exiting(Main.class.getName(), "start");
			
			// Load Properties
			Properties prop = new Properties();
			InputStream input = null;

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("PORT"));
			System.out.println(prop.getProperty("HOST"));
			System.out.println(prop.getProperty("PATH"));
			System.out.println(prop.getProperty("SCHEME"));

			Parent root = (Parent) FXMLLoader.load(getClass().getResource(
					"view/Main.fxml"));
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Micro ERP");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
