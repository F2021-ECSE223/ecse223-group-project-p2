package ca.mcgill.ecse.climbsafe.javafx.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import static ca.mcgill.ecse.climbsafe.javafx.controllers.ViewUtils.callController;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import javafx.event.ActionEvent;

public class ProcessAssignmentPageController {
	@FXML
	private TextField startTextField;
	@FXML
	private TextField cancelTextField;
	@FXML
	private TextField FinishTextField;

	// Event Listener on Button.onAction
	@FXML
	public void startClicked(ActionEvent event) {
	  if(startTextField.getText().trim().isEmpty()) {
	    ViewUtils.showError("Please input a valid week number");
	  }
	  else {
	  Integer week=Integer.parseInt(startTextField.getText());
      if(callController(() -> AssignmentController.StartTrip(week))){
	  }
      startTextField.clear();
	}
	}
	// Event Listener on Button.onAction
	@FXML
	public void cancelClicked(ActionEvent event) {
	  if(cancelTextField.getText().trim().isEmpty()) {
        ViewUtils.showError("Please input a valid email");
      }
      else {
	  String email=cancelTextField.getText();
      try{callController(() -> AssignmentController.cancelTrip(email));
      }catch(RuntimeException e) {
        ViewUtils.showError(e.getMessage());
      }
      }
      cancelTextField.clear();
	}
	  
	// Event Listener on Button.onAction
	@FXML
	public void finishClicked(ActionEvent event) {
	  if(FinishTextField.getText().trim().isEmpty()) {
        ViewUtils.showError("Please input a valid email");
      }
      else {
	  String email=FinishTextField.getText();
      try{callController(() -> AssignmentController.finishTrip(email));
      }catch(RuntimeException e) {
        ViewUtils.showError(e.getMessage());
      }
      }
      FinishTextField.clear();
	}
}
