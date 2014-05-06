package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InvoiceRowModel {
	private StringProperty name = new SimpleStringProperty();
	private DoubleProperty amount = new SimpleDoubleProperty();
	private DoubleProperty ust = new SimpleDoubleProperty();
	private DoubleProperty price = new SimpleDoubleProperty();
	
	public InvoiceRowModel(){
		
	}
	
	public InvoiceRowModel(String name, Double amount, Double ust, Double price){
		this.name.set(name);
		this.amount.set(amount);
		this.ust.set(ust);
		this.price.set(price);
	}
	
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public Double getAmount() {
		return amount.get();
	}
	public void setAmount(Double amount) {
		this.amount.set(amount);
	}
	public Double getUst() {
		return ust.get();
	}
	public void setUst(Double ust) {
		this.ust.set(ust);
	}
	public Double getPrice() {
		return price.get();
	}
	public void setPrice(Double price) {
		this.price.set(price);
	}
	
	//Properties
	public StringProperty nameProperty(){
		return name;
	}
	
	public DoubleProperty amountProperty(){
		return amount;
	}
	
	public DoubleProperty ustProperty(){
		return ust;
	}
	
	public DoubleProperty priceProperty(){
		return price;
	}
}
