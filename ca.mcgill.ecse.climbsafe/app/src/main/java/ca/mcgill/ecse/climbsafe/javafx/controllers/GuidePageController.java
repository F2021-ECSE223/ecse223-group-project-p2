package ca.mcgill.ecse.climbsafe.javafx.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import static ca.mcgill.ecse.climbsafe.javafx.controllers.ViewUtils.successful;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;

public class GuidePageController {
	@FXML
	private TextField nameGuide;
	@FXML
	private TextField emailGuide;
	@FXML
	private TextField passwordGuide;
	@FXML
	private TextField emergencyGuide;
	@FXML
	private Button registerGuide;
	@FXML
	private TextField newGuideName;
	@FXML
	private TextField newGuidePassword;
	@FXML
	private TextField newGuideEmergency;
	@FXML
	private Button updateGuide;
	@FXML
	private TextField deleteEmailGuide;
	@FXML
	private Button deleteGuide;

	// Event Listener on Button[#registerGuide].onAction
	@FXML
	public void addGuide(ActionEvent event) { //button registerGuide
		
		try {
			String email = emailGuide.getText();
			String name = nameGuide.getText();
			String password = passwordGuide.getText();
			String emergency = emergencyGuide.getText();
			
			if(email == null || email.trim().isEmpty()) {
				ViewUtils.showError("Please input a valid email");
}
			
			else if(name ==null || name.trim().isEmpty()) {
	
				ViewUtils.showError("Please input a valid name");
			}
			
			else if(password == null || password.trim().isEmpty()) {
				
				
				ViewUtils.showError("Please input a valid password");
			}
			
			else if(emergency ==null || emergency.trim().isEmpty()) {
				
				ViewUtils.showError("Please input a valid emergency contact");
			}
			
			
			else {
				if(successful(() -> ClimbSafeFeatureSet3Controller.registerGuide(email, password, name, emergency)))
				{
					emailGuide.setText("");
					nameGuide.setText("");
					passwordGuide.setText("");
					emergencyGuide.setText("");
				}	
			}	
		} catch (RuntimeException e){
			
			ViewUtils.showError(e.getMessage());
			
		}
		// TODO Autogenerated
		
	}
	// Event Listener on Button[#updateGuide].onAction
	@FXML
	public void addNewGuide(ActionEvent event) { //button updateGuide
		
		String aEmail = emailGuide.getText();
		String newName = newGuideName.getText();
		String newPassword = newGuidePassword.getText();
		String newEmergency = newGuideEmergency.getText();
		
		
		try {
			
			if(newName == null || newName.trim().isEmpty()) {
				
				ViewUtils.showError("Please input a valid new email");
				
			}else
			
			if(newPassword == null || newPassword.trim().isEmpty()) {
				
				ViewUtils.showError("Please input a valid new password");
			}else
			
			if(newEmergency ==null|| newEmergency.trim().isEmpty()) {
				
				ViewUtils.showError("Please input a valid new emergency contact");
			}
			
			else {
				if(successful(() -> ClimbSafeFeatureSet3Controller.updateGuide(aEmail, newPassword, newName, newEmergency)) )
					{
						newGuideName.setText("");
						newGuidePassword.setText("");
						newGuideEmergency.setText("");
					
					}
	
			}

		}
		catch (RuntimeException e) {
			
			ViewUtils.showError(e.getMessage());
			
		}
	}
		// TODO Autogenerated
	// Event Listener on Button[#deleteGuide].onAction
	@FXML
	public void removeGuide(ActionEvent event) { //button delete guide
		
		try {
			
			String deleteEmail = deleteEmailGuide.getText();
			
			if(deleteEmail == null || deleteEmail.trim().isEmpty()) {
				
				ViewUtils.showError("Please input a valid email to delete");
				
			}
			
			else {
			
				if(successful(() -> ClimbSafeFeatureSet1Controller.deleteGuide(deleteEmail)) )
				{
					deleteEmailGuide.setText("");	
		}
		}
		}catch (RuntimeException e) {
			
			 ViewUtils.showError(e.getMessage());
		}

	    }
		}

