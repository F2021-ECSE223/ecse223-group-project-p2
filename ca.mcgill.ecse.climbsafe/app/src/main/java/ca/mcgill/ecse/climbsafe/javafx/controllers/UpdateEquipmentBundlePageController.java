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
public class UpdateEquipmentBundlePageController {
	@FXML
	private TableView<updateItem> updateTable;
	@FXML
	private TextField oldBundleName;
	@FXML
	private TextField newBundleName;
	@FXML
	private TextField updateDiscount;
	@FXML
	private TextField newEquipName;
	@FXML
	private TextField newEquipQuan;
	
	List<updateItem> listForUpdate = new ArrayList<updateItem>();
	List<String> listEquipment = new ArrayList<String>();
	List<Integer> listQuantity = new ArrayList<Integer>();

	public class updateItem{
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
      updateTable.getColumns().add(createTableColumn("Equipment", "equipment"));
      updateTable.getColumns().add(createTableColumn("Quantity", "quantity"));
      
      updateTable.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> updateTable.setItems(getOverviewItems()));
      ClimbSafeFxmlView.getInstance().registerRefreshEvent(updateTable);
      
  }
	// Event Listener on Button.onAction
	@FXML
	public void UpdateBundleClicked(ActionEvent event) {
      try {
        String oldName = oldBundleName.getText();
        String newName = newBundleName.getText();
        int discount = Integer.parseInt(updateDiscount.getText());
        updateTable.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> updateTable.setItems(getOverviewItems()));
        ClimbSafeFxmlView.getInstance().registerRefreshEvent(updateTable);

        if (oldName == null || oldName.trim().isEmpty()){
          ViewUtils.showError("Please input a valid existing Bundle name");
        }
        if (newName == null || newName.trim().isEmpty()) {
          ViewUtils.showError("Please input a valid new Bundle name");
        }
        else {
          if (successful(() -> ClimbSafeFeatureSet5Controller.updateEquipmentBundle(oldName,newName,discount,listEquipment,listQuantity))){
              newBundleName.setText("");
              oldBundleName.setText("");
              updateDiscount.setText("");
              listForUpdate.clear();
              listEquipment.clear();
              listQuantity.clear();
              ClimbSafeFxmlView.getInstance().refresh();
              }
          listForUpdate.clear();
          listEquipment.clear();
          listQuantity.clear();
          ClimbSafeFxmlView.getInstance().refresh();
        }
      }

    catch (RuntimeException e) {

        ViewUtils.showError(e.getMessage());
    }
	  
	}
	// Event Listener on Button.onAction
	@FXML
	public void AddClicked(ActionEvent event) {
	  String equipment = newEquipName.getText();
      Integer quantity = Integer.valueOf(newEquipQuan.getText());
      updateItem aOverviewItem = new updateItem();
      listEquipment.add(equipment); // For system
      listQuantity.add(quantity);// For system
      
      aOverviewItem.quantity=quantity;
      aOverviewItem.equipment=equipment;
      listForUpdate.add(aOverviewItem); // for table
      
      newEquipName.setText("");
      newEquipQuan.setText("");
      
      ClimbSafeFxmlView.getInstance().refresh();
	  
	}
	
	public static TableColumn<updateItem, String> createTableColumn(String header,
        String propertyName) {
      TableColumn<updateItem, String> column = new TableColumn<>(header);
      column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
      return column;
    }

    public ObservableList<updateItem> getOverviewItems() {
    return FXCollections.observableList(listForUpdate);
}
}