package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui;
	
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
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("view/Main.fxml"));
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Micro ERP");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
