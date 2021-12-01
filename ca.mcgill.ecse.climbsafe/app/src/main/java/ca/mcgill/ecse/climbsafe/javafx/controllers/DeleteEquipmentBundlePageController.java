package ca.mcgill.ecse.climbsafe.javafx.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import static ca.mcgill.ecse.climbsafe.javafx.controllers.ViewUtils.successful;
import javafx.event.ActionEvent;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;

public class DeleteEquipmentBundlePageController {
	@FXML
	private TextField deleteName;

	// Event Listener on Button.onAction
	@FXML
	public void DeleteBundleClicked(ActionEvent event) {
	try {  
            String bundleName = deleteName.getText();
            if(bundleName == null || bundleName.trim().isEmpty()) {
                ViewUtils.showError("Please input a valid equipment bundle name");
            } else {
              if (successful(() -> ClimbSafeFeatureSet6Controller.deleteEquipmentBundle(bundleName))){
                deleteName.setText("");
                }
            }
        } 
	  catch (RuntimeException e) {

        ViewUtils.showError(e.getMessage());
    }
	}
}