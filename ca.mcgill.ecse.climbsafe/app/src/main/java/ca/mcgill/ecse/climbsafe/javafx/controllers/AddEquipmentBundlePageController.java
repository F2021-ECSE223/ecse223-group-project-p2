package ca.mcgill.ecse.climbsafe.javafx.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.javafx.ClimbSafeFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import static ca.mcgill.ecse.climbsafe.javafx.controllers.ViewUtils.successful;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
public class AddEquipmentBundlePageController {
	@FXML
	private TableView<addItem> addTable;
	@FXML
	private TextField addName;
	@FXML
	private TextField addDiscount;
	@FXML
	private TextField addEquipment;
	@FXML
	private TextField addEquipmentQuantity;
	
	List<addItem> listForAdd = new ArrayList<addItem>();
	List<String> listEquipment = new ArrayList<String>();
	List<Integer> listQuantity = new ArrayList<Integer>();

	public class addItem{
	  private String equipment;
	  private Integer quantity;
	  public String getEquipment() {
	    return equipment;
	  }
	  public Integer getQuantity() {
	    return quantity;
	  }
	}
	
	public void initialize() {
      addTable.getColumns().add(createTableColumn("Equipment", "equipment"));
      addTable.getColumns().add(createTableColumn("Quantity", "quantity"));
      
      addTable.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> addTable.setItems(getOverviewItems()));
      ClimbSafeFxmlView.getInstance().registerRefreshEvent(addTable);
      
  }
	// Event Listener on Button.onAction
	@FXML
	public void AddBundleClicked(ActionEvent event) {
      try {
        String name = addName.getText();
        int discount = Integer.parseInt(addDiscount.getText());
        addTable.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> addTable.setItems(getOverviewItems()));
        ClimbSafeFxmlView.getInstance().registerRefreshEvent(addTable);


          if (successful(() -> ClimbSafeFeatureSet5Controller.addEquipmentBundle(name,discount,listEquipment,listQuantity))){
              addName.setText("");
              addDiscount.setText("");
              }
          listForAdd.clear();
          listEquipment.clear();
          listQuantity.clear();
          ClimbSafeFxmlView.getInstance().refresh();
    }

    catch (RuntimeException e) {

        ViewUtils.showError(e.getMessage());
    }
	  
	}
	// Event Listener on Button.onAction
	@FXML
	public void AddClicked(ActionEvent event) {
	  String equipment = addEquipment.getText();
      Integer quantity = Integer.valueOf(addEquipmentQuantity.getText());
      addItem aOverviewItem = new addItem();
      listEquipment.add(equipment); // For system
      listQuantity.add(quantity);// For system
      
      aOverviewItem.quantity=quantity;
      aOverviewItem.equipment=equipment;
      listForAdd.add(aOverviewItem); // for table
      
      addEquipment.setText("");
      addEquipmentQuantity.setText("");
      
      ClimbSafeFxmlView.getInstance().refresh();
	  
	}
	
	public static TableColumn<addItem, String> createTableColumn(String header,
        String propertyName) {
      TableColumn<addItem, String> column = new TableColumn<>(header);
      column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
      return column;
    }

    public ObservableList<addItem> getOverviewItems() {
    return FXCollections.observableList(listForAdd);
}
}
