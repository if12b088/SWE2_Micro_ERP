package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.AddressDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.ContactDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.enums.AddressType;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.Utils;

public class ContactModel {

	// bool
	private boolean hasShippingAddressBool = false;
	private boolean hasInvoiceAddresssBool = false;

	// Dto
	ContactDto contactDto = new ContactDto();
	AddressDto addressDto = new AddressDto();
	AddressDto shippingAddressDto = new AddressDto();
	AddressDto invoiceAddressDto = new AddressDto();

	// Invoices
	List<InvoiceModel> invoiceses = new ArrayList<>();

	// Company
	private StringProperty companyName = new SimpleStringProperty();
	private StringProperty uid = new SimpleStringProperty();
	// Person
	private StringProperty title = new SimpleStringProperty();
	private StringProperty firstName = new SimpleStringProperty();
	private StringProperty lastName = new SimpleStringProperty();
	private StringProperty suffix = new SimpleStringProperty();
	private StringProperty fkCompany = new SimpleStringProperty();
	private StringProperty birthDate = new SimpleStringProperty();
	// address
	private StringProperty addressAddress = new SimpleStringProperty();
	private StringProperty addressZIP = new SimpleStringProperty();
	private StringProperty addressCity = new SimpleStringProperty();
	// Shippingaddress
	private StringProperty shippingAddressAddress = new SimpleStringProperty();
	private StringProperty shippingAddressZIP = new SimpleStringProperty();
	private StringProperty shippingAddressCity = new SimpleStringProperty();
	// Invoiceaddress
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
					&& (!Utils.isNullOrEmpty(getFirstName())
							|| !Utils.isNullOrEmpty(getLastName()) || !Utils
								.isNullOrEmpty(getTitle()));
		}
	};

	private BooleanBinding hasShippingAddress = new BooleanBinding() {

		@Override
		protected boolean computeValue() {
			if (hasShippingAddressBool) {
				hasShippingAddressBool = false;
			} else {
				hasShippingAddressBool = true;
			}
			return hasShippingAddressBool;
		}
	};

	private BooleanBinding hasInvoiceAddress = new BooleanBinding() {

		@Override
		protected boolean computeValue() {
			if (hasInvoiceAddresssBool) {
				hasInvoiceAddresssBool = false;
			} else {
				hasInvoiceAddresssBool = true;
			}
			return hasInvoiceAddresssBool;
		}
	};

	// Constructor
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
		title.addListener(canEditListener);
		companyName.addListener(canEditListener);
		firstName.addListener(canEditListener);
		lastName.addListener(canEditListener);
	}

	// Properties
	// company
	public final StringProperty companyNameProperty() {
		return companyName;
	}

	public final StringProperty UIDProperty() {
		return uid;
	}

	// person
	public final StringProperty titleProperty() {
		return title;
	}

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

	// address
	public final StringProperty addressAddressProperty() {
		return addressAddress;
	}

	public final StringProperty addressZIPProperty() {
		return addressZIP;
	}

	public final StringProperty addressCityProperty() {
		return addressCity;
	}

	// shippingaddress
	public final StringProperty shippingAddressAddressProperty() {
		return shippingAddressAddress;
	}

	public final StringProperty shippingAddressZIPProperty() {
		return shippingAddressZIP;
	}

	public final StringProperty shippingAddressCityProperty() {
		return shippingAddressCity;
	}

	// invoiceAdrees
	public final StringProperty invoiceAddressAddressProperty() {
		return invoiceAddressAddress;
	}

	public final StringProperty invoiveAddressZIPProperty() {
		return invoiceAddressZIP;
	}

	public final StringProperty invoiceAddressCityProperty() {
		return invoiceAddressCity;
	}

	// Getter and Setter
	// company
	public String getCompanyName() {
		return companyName.get();
	}

	public String getUid() {
		return uid.get();
	}

	public void setUid(String uID) {
		uid.set(uID);
	}

	// person
	public void setTitle(String title) {
		this.title.set(title);
	}

	public String getTitle() {
		return title.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public String getLastName() {
		return lastName.get();
	}

	// other
	public boolean isCompany() {
		return isCompany.get();
	}

	public boolean disableEditPerson() {
		return disableEditPerson.get();
	}

	public boolean disableEditCompany() {
		return disableEditCompany.get();
	}

	public boolean hasShippingAddress() {
		return hasShippingAddress.get();
	}

	public boolean hasInvoiceAddress() {
		return hasInvoiceAddress.get();
	}

	public BooleanBinding disableEditPersonBinding() {
		return disableEditPerson;
	}

	public BooleanBinding disableEditCompanyBinding() {
		return disableEditCompany;
	}

	public BooleanBinding hasShippingAddressBinding() {
		return hasShippingAddress;
	}

	public BooleanBinding hasInvoiceAddressBinding() {
		return hasInvoiceAddress;
	}

	// get Dtos
	public ContactDto getContactDto() {
		copyPropertiesToDto();
		return contactDto;
	}

	public AddressDto getAddressDto() {
		copyPropertiesToDto();
		return addressDto;
	}

	public AddressDto getShippingAddressDto() {
		copyPropertiesToDto();
		return shippingAddressDto;
	}

	public AddressDto getInvoiceAddressDto() {
		copyPropertiesToDto();
		return invoiceAddressDto;
	}

	private void copyPropertiesToDto() {
		contactDto.setCompanyname(companyName.get());
		contactDto.setUid(uid.get());
		contactDto.setTitle(title.get());
		contactDto.setFirstname(firstName.get());
		contactDto.setLastname(lastName.get());
		contactDto.setSuffix(suffix.get());
		// contactDto.setBirthday(birthDate.get());
		// contactDto.setCompanyId(fkCompany);

		if (contactDto.getAddresses().containsKey(AddressType.PRIMARY)) {
			addressDto = contactDto.getAddresses().get(AddressType.PRIMARY);
		} else if (addressAddress.get() != null && addressZIP.get() != null
				&& addressCity.get() != null) {
			contactDto.getAddresses().put(AddressType.PRIMARY, addressDto);
		}

		addressDto.setType(AddressType.PRIMARY);
		addressDto.setStreet(addressAddress.get());
		addressDto.setZip(addressZIP.get());
		addressDto.setCity(addressCity.get());

		if (contactDto.getAddresses().containsKey(AddressType.SHIPPING)) {
			shippingAddressDto = contactDto.getAddresses().get(
					AddressType.SHIPPING);
		} else if (shippingAddressAddress.get() != null
				&& shippingAddressZIP.get() != null
				&& shippingAddressCity.get() != null) {
			contactDto.getAddresses().put(AddressType.SHIPPING,
					shippingAddressDto);
		}

		shippingAddressDto.setType(AddressType.SHIPPING);
		shippingAddressDto.setStreet(shippingAddressAddress.get());
		shippingAddressDto.setZip(shippingAddressZIP.get());
		shippingAddressDto.setCity(shippingAddressAddress.get());

		if (contactDto.getAddresses().containsKey(AddressType.INVOICE)) {
			invoiceAddressDto = contactDto.getAddresses().get(
					AddressType.INVOICE);
		} else if (invoiceAddressAddress.get() != null
				&& invoiceAddressZIP.get() != null
				&& invoiceAddressCity.get() != "") {
			contactDto.getAddresses().put(AddressType.INVOICE,
					invoiceAddressDto);
		}

		invoiceAddressDto.setType(AddressType.INVOICE);
		invoiceAddressDto.setStreet(invoiceAddressAddress.get());
		invoiceAddressDto.setZip(invoiceAddressZIP.get());
		invoiceAddressDto.setCity(invoiceAddressCity.get());

	}

	private void copyDtoToProperties() {
		companyName.set(contactDto.getCompanyname());
		uid.set(contactDto.getUid());
		title.set(contactDto.getTitle());
		firstName.set(contactDto.getFirstname());
		lastName.set(contactDto.getLastname());
		suffix.set(contactDto.getSuffix());

		// Address
		if (contactDto.getAddresses().containsKey(AddressType.PRIMARY)) {
			AddressDto addressDto = contactDto.getAddresses().get(
					AddressType.PRIMARY);
			addressAddress.set(addressDto.getStreet());
			addressZIP.set(addressDto.getZip());
			addressCity.set(addressDto.getCity());
		}
		// Shipping Address
		if (contactDto.getAddresses().containsKey(AddressType.SHIPPING)) {
			AddressDto addressDto = contactDto.getAddresses().get(
					AddressType.SHIPPING);
			shippingAddressAddress.set(addressDto.getStreet());
			shippingAddressZIP.set(addressDto.getZip());
			shippingAddressCity.set(addressDto.getCity());
		}
		// Invoice Address
		if (contactDto.getAddresses().containsKey(AddressType.INVOICE)) {
			AddressDto addressDto = contactDto.getAddresses().get(
					AddressType.INVOICE);
			invoiceAddressAddress.set(addressDto.getStreet());
			invoiceAddressZIP.set(addressDto.getZip());
			invoiceAddressCity.set(addressDto.getCity());
		}
	}

	public void setDto(ContactDto dto) {
		contactDto = dto;
		copyDtoToProperties();
	}

	public void setInvoices(List<InvoiceModel> invoices) {
		this.invoiceses = invoices;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (companyName.get() != null) {
			sb.append(companyName.get());
			sb.append(" ");
		}
		if (uid.get() != null) {
			sb.append(uid.get());
			sb.append(" ");
		}
		if (title.get() != null) {
			sb.append(title.get());
			sb.append(" ");
		}
		if (firstName.get() != null) {
			sb.append(firstName.get());
			sb.append(" ");
		}
		if (lastName.get() != null) {
			sb.append(lastName.get());
		}
		if (suffix.get() != null) {
			sb.append(suffix.get());
			sb.append(" ");
		}
		return sb.toString();
	}
}
