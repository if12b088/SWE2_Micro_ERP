/**
 * 
 */
package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.customControl;

/**
 * @author richie
 *
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.Constants;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.exceptions.ConnectionProblemException;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.helper.WindowHelper;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.ContactModel;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.ProxyFactory;

public class ContactPicker extends HBox {
	@FXML
	private ImageView contactPickerImageView;
	@FXML
	private TextField contactPickerTextField;
	@FXML
	private Button contactPickerButtonSearch;
	@FXML
	private Button contactPickerButtonRemove;
	@FXML
	private ListView<ContactDto> contactPickerListView;

	private ContactPickerModel model = new ContactPickerModel();

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

		initialize();

	}

	public void initialize() {
		contactPickerTextField.textProperty().bindBidirectional(
				model.textProperty());
		contactPickerImageView.imageProperty().bindBidirectional(
				model.imageProperty());
	}

	@FXML
	public void handleDblClick(MouseEvent me) {

	}

	public void openPopup(List<ContactModel> companyModels) {
		WindowHelper.openContactSearchInNewWindow(companyModels);

	}

	@FXML
	public void handleCompanySearch() {

		if (model.getText() == null) {
			openPopup(null);
		} else {
			List<ContactDto> companies = null;
			try {
				companies = ProxyFactory.createContactProxy()
						.getCompanysByName(model.getText());
			} catch (ConnectionProblemException e) {
				// errMsg.setText(e.getMessage());
			}
			if (companies != null) {

				List<ContactModel> companyModels = new ArrayList<>();

				for (ContactDto cDto : companies) {
					ContactModel cModel = new ContactModel();
					cModel.setDto(cDto);
					companyModels.add(cModel);
				}
				if (companyModels.size() == 0) {
					openPopup(null);
				} else if (companyModels.size() == 1) {
					model.setText(companyModels.get(0).toString());
					model.setOk();
				} else {
					openPopup(companyModels);
				}
			}
		}
	}

	@FXML
	public void handleCompanyRemove() {

	}
}