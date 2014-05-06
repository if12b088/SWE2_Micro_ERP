package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceDto;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.dto.InvoiceRowDto;

public class InvoiceModel {

	private InvoiceDto invoiceDto = new InvoiceDto();

	private StringProperty nr = new SimpleStringProperty();
	private StringProperty infomation = new SimpleStringProperty();
	private StringProperty comment = new SimpleStringProperty();
	private ObservableList<InvoiceRowModel> rows = FXCollections
			.observableArrayList();

	// Dto
	private InvoiceDto dto;

	public InvoiceDto getDto() {
		return dto;
	}

	public void setDto(InvoiceDto dto) {
		this.dto = dto;
		copyDtoToProperties();
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

	public ObservableList<InvoiceRowModel> getRows() {
		return rows;
	}

	public void copyDtoToProperties() {
		this.nr.set(this.dto.getNr());
		this.infomation.set(this.dto.getInformation());
		this.comment.set(this.dto.getComment());
		// Rows
		this.rows.clear();
		for (InvoiceRowDto row : this.dto.getRows()) {
			InvoiceRowModel rowModel = new InvoiceRowModel();
			rowModel.setName(row.getName());
			rowModel.setAmount(row.getAmount());
			rowModel.setUst(row.getUst());
			rowModel.setPrice(row.getPrice());
			rows.add(rowModel);
		}

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
