package ca.mcgill.ecse.climbsafe.javafx.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import static ca.mcgill.ecse.climbsafe.javafx.controllers.ViewUtils.successful;
import ca.mcgill.ecse.climbsafe.controller.HotelController;
import javafx.event.ActionEvent;

public class HotelPageController {
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField hotelTextField;

	// Event Listener on Button.onAction
	@FXML
	public void assignClicked(ActionEvent event) {
	try {
	String email = emailTextField.getText();
	String hotel = hotelTextField.getText();
	 if (successful(() -> HotelController.assignHotel(email,hotel))) {
     }
	 emailTextField.clear();
     hotelTextField.clear();
	}catch(RuntimeException e){
	  ViewUtils.showError(e.getMessage());
	}
	}
}
