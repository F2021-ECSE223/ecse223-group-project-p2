package ca.mcgill.ecse.climbsafe.javafx.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.javafx.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import static ca.mcgill.ecse.climbsafe.javafx.controllers.ViewUtils.successful;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class RegisterMemberController {
	@FXML
	private TextField registerFullName;
	@FXML
	private TextField registerEmail;
	@FXML
	private TextField registerPassword;
	@FXML
	private TextField registerContact;
	@FXML
	private TextField registerClimbingWeeks;
	@FXML
	private Button registerMember;
	@FXML
	private CheckBox checkGuide;
	@FXML
	private CheckBox checkHotel;
	@FXML
	private TextField registerItem;
	@FXML
	private TextField registerQuantity;
	@FXML
	private Button addItem;
	@FXML
	private TableView<OverviewItems> registerTable;
	@FXML
	private TextField updateFullName;
	@FXML
	private TextField updateEmail;
	@FXML
	private TextField updatePassword;
	@FXML
	private TextField updateContact;
	@FXML
	private TextField updateClimbingWeeks;
	@FXML
	private CheckBox updateGuideCheck;
	@FXML
	private CheckBox updateHotelCheck;
	@FXML
	private TextField updateItem;
	@FXML
	private TextField updateQuantity;
	@FXML
	private Button updateMember;
	@FXML
	private Button updateAddItems;
	@FXML
	private TableView <OverviewUpdateItems>updateTable;
	@FXML
	private TextField emailToDeleteMember;
	@FXML
	private Button deleteMember;
	
	List<OverviewItems> listForRegister = new ArrayList<OverviewItems>();
	List<OverviewUpdateItems> listForUpdate = new ArrayList<OverviewUpdateItems>();;
	List <Integer> amountRegister = new ArrayList <Integer>();
	List <String> itemRegister = new ArrayList <String>();
	
	List <Integer> amountUpdate = new ArrayList <Integer>();
	List <String> itemUpdate = new ArrayList <String>();
	
	
	public void initialize() {
		registerTable.getColumns().add(createTableColumn("Item", "Items"));
        registerTable.getColumns().add(createTableColumn("Quantity", "amount"));
        
        registerTable.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> registerTable.setItems(getOverviewItems()));
        ClimbSafeFxmlView.getInstance().registerRefreshEvent(registerTable);
        
		updateTable.getColumns().add(createTableColumnUpdate("Item", "Items"));
        updateTable.getColumns().add(createTableColumnUpdate("Quantity", "amount"));
        
        updateTable.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> updateTable.setItems(getOverviewUpdateItems()));
        ClimbSafeFxmlView.getInstance().registerRefreshEvent(updateTable);
        
		
	}
	
    
    public class OverviewItems{
    	private Integer amount ;
        private String Items;
        public Integer getAmount() {
          return amount;
        }
        public String getItems() {
          return Items;
        }
    }
    
    public class OverviewUpdateItems{
    	private Integer amount ;
        private String Items;
        
        public Integer getAmount() {
            return amount;
          }
          public String getItems() {
            return Items;
          }
    }

	// Event Listener on Button[#registerMember].onAction
	@FXML
	public void clickRegisterMember(ActionEvent event) {
        try {
            String fullName = registerFullName.getText();
            String email = registerEmail.getText();
            String password = registerPassword.getText();
            String contact = registerContact.getText();
            int numOfClimbingWeek = Integer.parseInt(registerClimbingWeeks.getText());
            boolean guide = checkGuide.isSelected();
            boolean hotel = checkHotel.isSelected();
            registerTable.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> registerTable.setItems(getOverviewItems()));
            ClimbSafeFxmlView.getInstance().registerRefreshEvent(registerTable);


              if (successful(() -> ClimbSafeFeatureSet2Controller.registerMember(email, password, fullName, contact, numOfClimbingWeek, guide, hotel, itemRegister, amountRegister))){
                  registerFullName.setText("");
                  registerEmail.setText("");
                  registerPassword.setText("");
                  registerContact.setText("");
                  registerClimbingWeeks.setText("");
                  amountRegister.clear();
                  itemRegister.clear();
                  listForRegister.clear();
                  checkGuide.setSelected(false);
                  checkHotel.setSelected(false);
   
                  }
              amountRegister.clear();
              itemRegister.clear();
              listForRegister.clear();
        }

        catch (RuntimeException e) {

            ViewUtils.showError(e.getMessage());
        }
    }

	// Event Listener on Button[#addItem].onAction
	@FXML
	public void registerAddItem(ActionEvent event) {
        // TODO Autogenerated

        String Item = registerItem.getText();
        Integer quantity = Integer.valueOf(registerQuantity.getText());
        OverviewItems aOverviewItem = new OverviewItems();
        itemRegister.add(Item); // For system
        amountRegister.add(quantity);// For system
        aOverviewItem.amount=quantity;
        aOverviewItem.Items=Item;
        listForRegister.add(aOverviewItem); // for table
        
        registerItem.setText("");
        registerQuantity.setText("");
        
        ClimbSafeFxmlView.getInstance().refresh();
    }

	// Event Listener on Button[#updateMember].onAction
	@FXML
	public void clickUpdateMember(ActionEvent event) {
		
		 try {


	            String fullNameUpdate = updateFullName.getText();
	            String emailUpdate = updateEmail.getText();
	            String passwordUpdate = updatePassword.getText();
	            String contactUpdate = updateContact.getText();
	            int numOfClimbingWeekUpdate = Integer.parseInt(updateClimbingWeeks.getText());
	            boolean guideUpdate = updateGuideCheck.isSelected();
	            boolean hotelUpdate = updateHotelCheck.isSelected();
	            



	              if (successful(() -> ClimbSafeFeatureSet2Controller.updateMember(emailUpdate, passwordUpdate, fullNameUpdate, contactUpdate, numOfClimbingWeekUpdate, guideUpdate, hotelUpdate, itemUpdate, amountUpdate))){
	                  updateFullName.setText("");
	                  updateEmail.setText("");
	                  updatePassword.setText("");
	                  updateContact.setText("");
	                  updateClimbingWeeks.setText("");
	                  updateGuideCheck.setSelected(false);
	                  updateHotelCheck.setSelected(false);
	                  amountUpdate.clear();
	  	              itemUpdate.clear();
	  	              listForUpdate.clear();
	  	              updateTable.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> updateTable.setItems(getOverviewUpdateItems()));
	  	              ClimbSafeFxmlView.getInstance().registerRefreshEvent(updateTable);
	   
	                  }
	              amountUpdate.clear();
                  itemUpdate.clear();
                  listForUpdate.clear();
	        }

	        catch (RuntimeException e) {

	            ViewUtils.showError(e.getMessage());
	        }
		  
	}
	// Event Listener on Button[#updateAddItems].onAction
	@FXML
	public void clickUpdateAddItems(ActionEvent event) {
		 String itemForupdate = updateItem.getText();
	        Integer quantityForUpdate = Integer.valueOf(updateQuantity.getText());
	        OverviewUpdateItems aOverviewItem = new OverviewUpdateItems();
	        itemUpdate.add(itemForupdate); // For system
	        amountUpdate.add(quantityForUpdate);// For system
	        aOverviewItem.amount=quantityForUpdate;
	        aOverviewItem.Items=itemForupdate;
	        listForUpdate.add(aOverviewItem); // for table
	        updateItem.setText("");
	        updateQuantity.setText("");
	        
	        ClimbSafeFxmlView.getInstance().refresh();
	}
	// Event Listener on Button[#deleteMember].onAction
	@FXML
	public void clickDeleteMember(ActionEvent event) {
		
		 try {
			 String deleteEmail = emailToDeleteMember.getText();
				

	              if (successful(() -> ClimbSafeFeatureSet1Controller.deleteMember(deleteEmail))){
	            	  emailToDeleteMember.setText("");
	   
	                  }
	        }

	        catch (RuntimeException e) {

	            ViewUtils.showError(e.getMessage());
	        }
		  
		
	}
	
	
	public static TableColumn<OverviewItems, String> createTableColumn(String header,
	          String propertyName) {
	        TableColumn<OverviewItems, String> column = new TableColumn<>(header);
	        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
	        return column;
	      }
	
	public static TableColumn<OverviewUpdateItems, String> createTableColumnUpdate(String header,
	          String propertyName) {
	        TableColumn<OverviewUpdateItems, String> column = new TableColumn<>(header);
	        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
	        return column;
	      }
	
	public ObservableList<OverviewItems> getOverviewItems() {
        return FXCollections.observableList(listForRegister);
      }
	
	public ObservableList<OverviewUpdateItems> getOverviewUpdateItems() {
        return FXCollections.observableList(listForUpdate);
      }
}