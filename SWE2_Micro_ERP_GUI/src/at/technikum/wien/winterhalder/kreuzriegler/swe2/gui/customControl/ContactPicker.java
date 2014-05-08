/**
 * 
 */
package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.customControl;

/**
 * @author richie
 *
 */
import java.io.IOException;
import java.util.List;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ContactPicker extends HBox {
	@FXML
	private ImageView contactPickerImageView;
	@FXML
	private TextField contactPickerTextField;
	@FXML
	private Button contactPickerButtonSearch;
	@FXML
	private Button contactPickerButtonRemove;

	public ContactPicker() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"ContactPicker.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public void setcontactPickerButtonRemoveText(String name) {
		contactPickerButtonRemove.setText(name);
	}

	public String getText() {
		return textProperty().get();
	}

	public void setText(String value) {
		textProperty().set(value);
	}

	public StringProperty textProperty() {
		return contactPickerTextField.textProperty();
	}

	public ImageView getContactPickerImageView() {
		return contactPickerImageView;
	}

	public Button getContactPickerButtonSearch() {
		return contactPickerButtonSearch;
	}

	public void openPopup(List<ContactDto> contacts) {
		Parent root;
		try {
			root = FXMLLoader
					.load(getClass()
							.getClassLoader()
							.getResource(
									"/at/technikum/wien/winterhalder/kreuzriegler/swe2/gui/customControl/ContactPickerPopup.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Suche Kontakt");
			stage.setScene(new Scene(root, 600, 400));
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected void handleCompanySearch() {
		System.out.println("The button was clicked!");
	}
}