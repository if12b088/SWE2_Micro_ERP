package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.AddressDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.Utils;

public class ContactModel {

	//Dto
	ContactDto contactDto = new ContactDto();
	AddressDto addressDto = new AddressDto();
	AddressDto shippingAddressDto = new AddressDto();
	AddressDto invoiceAddressDto = new AddressDto();
	
	//Company
	private StringProperty companyName = new SimpleStringProperty();
	private StringProperty UID = new SimpleStringProperty();
	//Person
	private StringProperty firstName = new SimpleStringProperty();
	private StringProperty lastName = new SimpleStringProperty();
	private StringProperty suffix = new SimpleStringProperty();
	private StringProperty fkCompany = new SimpleStringProperty();
	private StringProperty birthDate = new SimpleStringProperty();
	//address
	private StringProperty addressAddress = new SimpleStringProperty();
	private StringProperty addressZIP = new SimpleStringProperty();
	private StringProperty addressCity = new SimpleStringProperty();
	//Shippingaddress
	private StringProperty shippingAddressAddress = new SimpleStringProperty();
	private StringProperty shippingAddressZIP = new SimpleStringProperty();
	private StringProperty shippingAddressCity = new SimpleStringProperty();
	//Invoiceaddress
	private StringProperty invoiceAddressAddress = new SimpleStringProperty();
	private StringProperty invoiceAddressZIP = new SimpleStringProperty();
	private StringProperty invoiceAddressCity = new SimpleStringProperty();
	
	
	private BooleanBinding isCompany = new BooleanBinding() {
		@Override
		protected boolean computeValue() {
			return !Utils.isNullOrEmpty(getCompanyName());
		}
	};
	
	private BooleanBinding disableEditPerson = new BooleanBinding() {
		@Override
		protected boolean computeValue() {
			return !Utils.isNullOrEmpty(getCompanyName())
					&& Utils.isNullOrEmpty(getFirstName())
					&& Utils.isNullOrEmpty(getLastName());
		}
	};

	private BooleanBinding disableEditCompany = new BooleanBinding() {
		@Override
		protected boolean computeValue() {
			return Utils.isNullOrEmpty(getCompanyName())
					&& (!Utils.isNullOrEmpty(getFirstName()) || !Utils
							.isNullOrEmpty(getLastName()));
		}
	};
	
	//Constructor
	public ContactModel() {
		ChangeListener<String> canEditListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				isCompany.invalidate();
				disableEditPerson.invalidate();
				disableEditCompany.invalidate();
			}
		};
		companyName.addListener(canEditListener);
		firstName.addListener(canEditListener);
		lastName.addListener(canEditListener);
	}
	
	//Properties
	//company
	public final StringProperty companyNameProperty() {
		return companyName;
	}

	public final StringProperty UIDProperty() {
		return UID;
	}
	//person
	public final StringProperty firstNameProperty() {
		return firstName;
	}

	public final StringProperty lastNameProperty() {
		return lastName;
	}
	
	public final StringProperty suffixProperty() {
		return suffix;
	}
	
	public final StringProperty fkCompanyProperty() {
		return fkCompany;
	}
	
	public final StringProperty birthDateProperty() {
		return birthDate;
	}
	//address
	public final StringProperty addressAddressProperty() {
		return addressAddress;
	}
	
	public final StringProperty addressZIPProperty() {
		return addressZIP;
	}
	
	public final StringProperty addressCityProperty() {
		return addressCity;
	}
	//shippingaddress
	public final StringProperty shippingAddressAddressProperty() {
		return shippingAddressAddress;
	}
	
	public final StringProperty shippingAddressZIPProperty() {
		return shippingAddressZIP;
	}
	
	public final StringProperty shippingAddressCityProperty() {
		return shippingAddressCity;
	}
	//invoiceAdrees
	public final StringProperty invoiceAddressAddressProperty() {
		return invoiceAddressAddress;
	}
	
	public final StringProperty invoiveAddressZIPProperty() {
		return invoiceAddressZIP;
	}
	
	public final StringProperty invoiceAddressCityProperty() {
		return invoiceAddressCity;
	}
	
	//Getter and Setter
	//company
	public String getCompanyName() {
		return companyName.get();
	}

	public String getUID() {
		return UID.get();
	}

	public void setUID(String uID) {
		UID.set(uID);
	}
	public String getFirstName() {
		return firstName.get();
	}

	//person
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	//other
	public boolean isCompany() {
		return isCompany.get();
	}

	public boolean disableEditPerson() {
		return disableEditPerson.get();
	}

	public boolean disableEditCompany() {
		return disableEditCompany.get();
	}
	
	public BooleanBinding disableEditPersonBinding() {
		return disableEditPerson;
	}

	public BooleanBinding disableEditCompanyBinding() {
		return disableEditCompany;
	}
	//get Dtos
	public ContactDto getContactDto(){
		copyProperties();
		return contactDto;
	}
	
	public AddressDto getAddressDto(){
		copyProperties();
		return addressDto;
	}

	public AddressDto getShippingAddressDto(){
		copyProperties();
		return shippingAddressDto;
	}
	
	public AddressDto getInvoiceAddressDto(){
		copyProperties();
		return invoiceAddressDto;
	}
	
	private void copyProperties(){
		contactDto.setCompanyname(companyName.get());
		contactDto.setUid(UID.get());
		contactDto.setFirstname(firstName.get());
		contactDto.setLastname(lastName.get());
		contactDto.setSuffix(suffix.get());
	//	contactDto.setBirthday(birthDate.get());
	//	contactDto.setCompanyId(fkCompany);
		
		addressDto.setAdress(addressAddress.get());
		addressDto.setZip(addressZIP.get());
		addressDto.setCity(addressCity.get());
		
		shippingAddressDto.setAdress(shippingAddressAddress.get());
		shippingAddressDto.setZip(shippingAddressZIP.get());
		shippingAddressDto.setCity(shippingAddressAddress.get());
		
		invoiceAddressDto.setAdress(invoiceAddressAddress.get());
		invoiceAddressDto.setZip(invoiceAddressZIP.get());
		invoiceAddressDto.setCity(invoiceAddressCity.get());
	}
}
