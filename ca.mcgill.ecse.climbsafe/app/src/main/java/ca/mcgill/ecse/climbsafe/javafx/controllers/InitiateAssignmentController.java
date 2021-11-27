package ca.mcgill.ecse.climbsafe.javafx.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import static ca.mcgill.ecse.climbsafe.javafx.controllers.ViewUtils.callController;
import ca.mcgill.ecse.climbsafe.javafx.controllers.ViewUtils;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;

public class InitiateAssignmentController {

	// Event Listener on Button.onAction
	@FXML
	public void ButtonClicked(ActionEvent event) {
	  var error = "";
	  try{callController(() -> AssignmentController.initiateAssignment());
	  }catch(RuntimeException e) {
	    error=e.getMessage();
	    ViewUtils.showError(error);
	  }
	}
}
