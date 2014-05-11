package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceRowDto;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class InvoiceModel {
	// ID
	private long id;

	private ContactModel contact = null;

	// Error
	private StringProperty errorMsg = new SimpleStringProperty();

	private InvoiceDto invoiceDto = new InvoiceDto();

	// private ContactDto contactReference = new ContactDto();

	private StringProperty nr = new SimpleStringProperty();
	private StringProperty infomation = new SimpleStringProperty();
	private StringProperty comment = new SimpleStringProperty();
	private ObservableList<InvoiceRowModel> rows = FXCollections
			.observableArrayList();
	private boolean locked = false;

	private BooleanBinding isLocked = new BooleanBinding() {
		@Override
		protected boolean computeValue() {
			return locked;
		}
	};

	public InvoiceDto getinvoiceDto() {
		copyPropertiesToDto();
		return invoiceDto;
	}

	public void setinvoiceDto(InvoiceDto dto) {
		this.invoiceDto = dto;
		copyDtoToProperties();
	}

	public ContactModel getContact() {
		return contact;
	}

	public void setContact(ContactModel contact) {
		this.contact = contact;
	}

	public BooleanBinding isLockedBinding() {
		return isLocked;
	}

	/**
	 * @return the locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @param locked
	 *            the locked to set
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	// errorMsg
	public String getErrorMsg() {
		return errorMsg.get();
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg.set(errorMsg);
	}

	public final StringProperty nrProperty() {
		return nr;
	}

	public final StringProperty informationProperty() {
		return infomation;
	}

	public final StringProperty commentProperty() {
		return comment;
	}
	
	// errorMsg
	public final StringProperty errorMsgProperty() {
		return errorMsg;
	}

	public ObservableList<InvoiceRowModel> getRows() {
		return rows;
	}

	private void copyDtoToProperties() {
		this.id = invoiceDto.getId();
		this.nr.set(this.invoiceDto.getNr());
		this.infomation.set(this.invoiceDto.getInformation());
		this.comment.set(this.invoiceDto.getComment());
		// Rows
		this.rows.clear();
		for (InvoiceRowDto row : this.invoiceDto.getRows()) {
			InvoiceRowModel rowModel = new InvoiceRowModel();
			rowModel.setName(row.getName());
			rowModel.setAmount(row.getAmount());
			rowModel.setUst(row.getUst());
			rowModel.setPrice(row.getPrice());
			rows.add(rowModel);
		}
		this.locked = invoiceDto.isLocked();
	}

	private void copyPropertiesToDto() {
		// TODO dates??
		invoiceDto.setId(id);
		invoiceDto.setNr(nr.get());
		invoiceDto.setInformation(infomation.get());
		invoiceDto.setComment(comment.get());

		invoiceDto.getRows().clear();
		for (InvoiceRowModel row : rows) {
			InvoiceRowDto rowDto = new InvoiceRowDto();
			rowDto.setName(row.getName());
			rowDto.setAmount(row.getAmount());
			rowDto.setUst(row.getUst());
			rowDto.setPrice(row.getPrice());
			rowDto.setInvoiceId(id);
			invoiceDto.getRows().add(rowDto);
		}
		invoiceDto.setLocked(locked);

	}

	public void printInvoice() {
		// String file = "c:/temp/FirstPdf.pdf";
		Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
		Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
				BaseColor.RED);
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

		try {
			OutputStream file = new FileOutputStream(new File("SamplePDF.pdf"));

			Document document = new Document();
			PdfWriter.getInstance(document, file);

			document.open();
			document.add(new Paragraph("First iText PDF", catFont));
			document.add(new Paragraph(contact.getLastName(), catFont));
			document.add(new Paragraph(new Date().toString()));

			document.close();
			file.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		setLocked(true);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (nr.get() != null) {
			sb.append(nr.get());
			sb.append(" ");
		}
		if (infomation.get() != null) {
			if (infomation.get().length() > 35) {
				sb.append(infomation.get().substring(0, 35));
			} else {
				sb.append(infomation.get());
			}
			sb.append(" ");
		}
		if (comment.get() != null) {
			if (comment.get().length() > 35) {
				sb.append(comment.get().substring(0, 35));
			} else {
				sb.append(comment.get());
			}
			sb.append(" ");
		}

		return sb.toString();
	}
}
