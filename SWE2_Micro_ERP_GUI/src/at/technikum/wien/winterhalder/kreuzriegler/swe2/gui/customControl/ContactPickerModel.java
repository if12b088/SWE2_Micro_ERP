package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.customControl;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.ContactModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class ContactPickerModel {
	private ContactModel selectedContact = new ContactModel();

	private StringProperty text = new SimpleStringProperty();
	private ObjectProperty<Image> image = new SimpleObjectProperty<>();

	private static final Image imageOK = new Image(
			ContactPicker.class
					.getResourceAsStream("../icons/ok.png"));
	private static final Image imageErr = new Image(
			ContactPicker.class
			.getResourceAsStream("../icons/err.png"));

	/**
	 * @return the text
	 */
	public String getText() {
		return text.get();
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text.set(text);
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image.get();
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(Image image) {
		this.image.set(image);
	}

	/**
	 * @return the selectedContact
	 */
	public ContactModel getSelectedContact() {
		return selectedContact;
	}

	/**
	 * @param selectedContact
	 *            the selectedContact to set
	 */
	public void setSelectedContact(ContactModel selectedContact) {
		this.selectedContact = selectedContact;
	}

	// Properties
	public final StringProperty textProperty() {
		return text;
	}

	public final ObjectProperty<Image> imageProperty() {
		return image;
	}
	
	public void setOk(){
		image.set(imageOK);
	}
	public void setErr(){
		image.set(imageErr);
	}
}
