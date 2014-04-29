package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.tableCells;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.model.InvoiceRowModel;

public class ButtonCell extends TableCell<InvoiceRowModel, Boolean> {
	 
	final Button cellButton = new Button("x");
     
    public ButtonCell(final ObservableList<InvoiceRowModel> list){
          
         cellButton.setOnAction(new EventHandler<ActionEvent>(){

             @Override
             public void handle(ActionEvent t) {
                 list.remove(getIndex());
             }
         });
     }

     //Display button if the row is not empty
     @Override
     protected void updateItem(Boolean t, boolean empty) {
         super.updateItem(t, empty);
         if(!empty){
             setGraphic(cellButton);
         }
     }
     
     public Button getCellButton(){
    	 return cellButton;
     }
}
