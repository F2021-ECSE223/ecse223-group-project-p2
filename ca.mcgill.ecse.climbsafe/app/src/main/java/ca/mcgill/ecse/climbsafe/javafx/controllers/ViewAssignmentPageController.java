package ca.mcgill.ecse.climbsafe.javafx.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.ChoiceBox;
import java.sql.Date;
import java.time.LocalDate;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
import ca.mcgill.ecse.climbsafe.javafx.ClimbSafeFxmlView;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class ViewAssignmentPageController {
	@FXML
	private TableView<TOAssignment> overviewTable;
	@FXML
	private Button viewButton;

	 public void initialize() {
	    // initialize the overview table by adding new columns
	    overviewTable.getColumns().add(createTableColumn("Status", "status"));
	    overviewTable.getColumns().add(createTableColumn("Start Week", "startWeek"));
	    overviewTable.getColumns().add(createTableColumn("End Week", "endWeek"));
	    overviewTable.getColumns().add(createTableColumn("Member", "memberName"));
	    overviewTable.getColumns().add(createTableColumn("MemberEmail", "memberEmail"));
	    overviewTable.getColumns().add(createTableColumn("Guide", "guideName"));
	    overviewTable.getColumns().add(createTableColumn("GuideEmail", "guideEmail"));
	    overviewTable.getColumns().add(createTableColumn("Guide Cost", "totalCostForGuide"));
	    overviewTable.getColumns().add(createTableColumn("Equipment Cost", "totalCostForEquipment"));
	    overviewTable.getColumns().add(createTableColumn("Code", "authorizationCode"));
	    overviewTable.getColumns().add(createTableColumn("Refund", "refundedPercentageAmount"));

	    // Driver column needs to have customized string

	    // overview table if a refreshable element
	    overviewTable.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> overviewTable.setItems(getOverviewItems()));

	    // register refreshable nodes
	    ClimbSafeFxmlView.getInstance().registerRefreshEvent(overviewTable);
	  }
	 
	  public static TableColumn<TOAssignment, String> createTableColumn(String header,
	      String propertyName) {
	    TableColumn<TOAssignment, String> column = new TableColumn<>(header);
	    column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
	    return column;
	  }
	  
	  public ObservableList<TOAssignment> getOverviewItems() {
	    return FXCollections.observableList(ClimbSafeFeatureSet6Controller.getAssignments());
	  }
	// Event Listener on Button[#viewButton].onAction
	@FXML
	public void ButtonClicked(ActionEvent event) {
	  ClimbSafeFxmlView.getInstance().refresh();
	}
}
