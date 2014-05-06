/**
 * 
 */
package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.customControl;

/**
 * @author richie
 *
 */
import java.io.IOException;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CustomControl extends HBox {
	@FXML
	private ImageView customImgaeView;
	@FXML
	private TextField customTextField;
	@FXML
	private Button customButtonSearch;
	@FXML
	private Button customButtonRemove;

	public CustomControl() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"CustomControl.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
//		Image i = new Image("ok.png");
//		customImgaeView.setImage(i);
		
	//	customButtonSearch.setText("Search");
	//	customButtonRemove.setText("Remove");

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public String getText() {
		return textProperty().get();
	}

	public void setText(String value) {
		textProperty().set(value);
	}

	public StringProperty textProperty() {
		return customTextField.textProperty();
	}

	@FXML
	protected void doSomething() {
		System.out.println("The button was clicked!");
	}
}