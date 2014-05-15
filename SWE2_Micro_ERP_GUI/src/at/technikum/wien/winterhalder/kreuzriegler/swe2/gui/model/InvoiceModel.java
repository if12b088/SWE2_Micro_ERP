package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceRowDto;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class InvoiceModel {
	// ID
	private long id;

	private ContactModel contact = null;

	// Error
	private StringProperty errorMsg = new SimpleStringProperty();

	private InvoiceDto invoiceDto = new InvoiceDto();

	private StringProperty nr = new SimpleStringProperty();
	private StringProperty infomation = new SimpleStringProperty();
	private StringProperty comment = new SimpleStringProperty();
	private StringProperty sum = new SimpleStringProperty();
	private ObservableList<InvoiceRowModel> rows = FXCollections
			.observableArrayList();

	private StringBinding sumCalculation = new StringBinding() {

		@Override
		protected String computeValue() {
			double newSum = 0;
			for (InvoiceRowModel row : rows) {
				newSum += row.getGrossPrice();
			}
			return Math.round(newSum*100)/100 + "";
		}
	};

	private BooleanProperty locked = new SimpleBooleanProperty();

	public InvoiceModel(){
		
	}
	
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

	public final BooleanProperty lockedProperty() {
		return locked;
	}

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

	public final StringProperty sumProperty() {
		return sum;
	}

	/**
	 * @return the sumCalculation
	 */
	public StringBinding sumCalculationBinding() {
		return sumCalculation;
	}

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
			rowModel.setNetPrice(row.getPrice());
			rowModel.setGrossPrice(row.getPrice() * (row.getUst() / 100 + 1));
			rows.add(rowModel);
		}
		this.locked.set(invoiceDto.isLocked());
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
			rowDto.setPrice(row.getNetPrice());
			rowDto.setInvoiceId(id);
			invoiceDto.getRows().add(rowDto);
		}
		invoiceDto.setLocked(locked.get());
	}

	public void printInvoice() {
		// String file = "c:/temp/FirstPdf.pdf";
		Font catFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
		Font redFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL,
				BaseColor.RED);
		Font subFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
		Font smallBold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

		try {
			File fileName = new File("Rechnung.pdf");
			OutputStream file = new FileOutputStream(fileName);

			Document document = new Document();
			PdfWriter.getInstance(document, file);

			document.open();
			StringBuilder name = new StringBuilder();

			if (contact.getTitle() != null) {
				name.append(contact.getTitle());
				name.append(" ");
			}
			name.append(contact.getFirstName());
			name.append(" ");
			name.append(contact.getLastName());

			document.add(new Paragraph(name.toString(), catFont));

			if (contact.getShippingAddressDto().getStreet() != null
					&& contact.getShippingAddressDto().getZip() != null
					&& contact.getShippingAddressDto().getCity() != null) {
				document.add(new Paragraph(contact.getShippingAddressDto()
						.getStreet(), catFont));
				document.add(new Paragraph(contact.getShippingAddressDto()
						.getZip()
						+ " "
						+ contact.getShippingAddressDto().getCity(), catFont));
			} else {
				document.add(new Paragraph(contact.getAddressDto().getStreet(),
						catFont));
				document.add(new Paragraph(contact.getAddressDto().getZip()
						+ " " + contact.getAddressDto().getCity(), catFont));
			}
			document.add(new Paragraph("Meine GMBH", catFont));
			document.add(new Paragraph("MyStreet 1", catFont));
			document.add(new Paragraph("1234 Wien", catFont));

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();

			document.add(new Paragraph(dateFormat.format(date), catFont));

			// Table

			PdfPTable table = new PdfPTable(5);

			// t.setBorderColor(BaseColor.GRAY);
			// t.setPadding(4);
			// t.setSpacing(4);
			// t.setBorderWidth(1);

			PdfPCell cellName = new PdfPCell(new Phrase("Name"));
			cellName.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cellName);

			PdfPCell cellAmount = new PdfPCell(new Phrase("Anzahl"));
			cellAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cellAmount);

			PdfPCell cellUst = new PdfPCell(new Phrase("Ust"));
			cellUst.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cellUst);

			PdfPCell cellNetPrice = new PdfPCell(new Phrase("Netto"));
			cellNetPrice.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cellNetPrice);

			PdfPCell cellGrossPrice = new PdfPCell(new Phrase("Brutto"));
			cellGrossPrice.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cellGrossPrice);

			table.setHeaderRows(1);

			for (InvoiceRowModel row : rows) {
				table.addCell(row.getName());
				table.addCell(row.getAmount().toString());
				table.addCell(row.getUst().toString());
				table.addCell(row.getNetPrice().toString());
				table.addCell(row.getGrossPrice().toString());
			}
			document.add(table);
			document.add(new Paragraph("Summe: " + sum.get(), catFont));

			document.close();
			file.close();
			// Open PDF in new Window
			// Desktop.getDesktop().open(fileName);
		} catch (Exception e) {

			e.printStackTrace();
		}
		locked.set(true);
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
