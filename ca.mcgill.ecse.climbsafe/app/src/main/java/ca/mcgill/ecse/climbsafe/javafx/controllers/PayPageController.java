package ca.mcgill.ecse.climbsafe.javafx.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import static ca.mcgill.ecse.climbsafe.javafx.controllers.ViewUtils.successful;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.javafx.controllers.ViewUtils;
import javafx.event.ActionEvent;

public class PayPageController {
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField codeTextField;

	// Event Listener on Button.onAction
	@FXML
	public void buttonClicked(ActionEvent event) {
	  try {
	String email = emailTextField.getText();
	String code = codeTextField.getText();
	if (email == null || email.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid email");
    } else {
      if (successful(() -> AssignmentController.confirmPayment(email,code))) {
        emailTextField.setText("");
        codeTextField.setText("");
      } 
    }
	} catch (RuntimeException e) {
      ViewUtils.showError(e.getMessage());
    }
	}
}
