package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model;

import java.util.logging.Logger;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;



public class InvoiceRowModel {
	private static final Logger logger = Logger.getLogger(InvoiceRowModel.class.getName());

	private StringProperty name = new SimpleStringProperty();
	private DoubleProperty amount = new SimpleDoubleProperty();
	private DoubleProperty ust = new SimpleDoubleProperty();
	private DoubleProperty netPrice = new SimpleDoubleProperty();
	private DoubleProperty grossPrice = new SimpleDoubleProperty();
	
//	private DoubleBinding grossCalculation = new DoubleBinding() {
//
//		@Override
//		protected double computeValue() {
//			logger.info("in calc");
//			return netPrice.get() * (ust.get() / 100 + 1);
//		}
//	};
	
	public InvoiceRowModel(){
		ChangeListener<Number> rowHasChangesListener = new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				grossPrice.set(netPrice.get() * (ust.get() / 100 + 1));
//				grossCalculation.invalidate();
				logger.info("change");
			}
		};
		ust.addListener(rowHasChangesListener);
		netPrice.addListener(rowHasChangesListener);
	}
	
	public InvoiceRowModel(String name, Double amount, Double ust, Double netPrice, Double grossPrice){
		this.name.set(name);
		this.amount.set(amount);
		this.ust.set(ust);
		this.netPrice.set(netPrice);
		this.grossPrice.set(grossPrice);
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
	public Double getNetPrice() {
		return netPrice.get();
	}
	public void setNetPrice(Double price) {
		this.netPrice.set(price);
	}
	public Double getGrossPrice() {
		return grossPrice.get();
	}
	public void setGrossPrice(Double price) {
		this.grossPrice.set(price);
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
	
	public DoubleProperty netPriceProperty(){
		return netPrice;
	}
	
	public DoubleProperty grossPriceProperty(){
		return grossPrice;
	}
}
