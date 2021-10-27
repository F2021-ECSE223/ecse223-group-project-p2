package ca.mcgill.ecse.climbsafe.controller;



import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.User;

public class ClimbSafeFeatureSet3Controller {

	
/**@author Danny Tu	
 * 
 * This method registers the guide using his information in the climbSafe system
 * @param email Email of the guide that we wish to register
 * @param password Password of the guide that gets registered in the climbSafe system
 * @param name Name of the guide that gets registered in the climbSafe system
 * @param emergency contact Phone number for the emergency contact of the guide that is registered in the climbSafe system
 * @throws InvalidInputException for inputs whenever errors occur
	
	*/
	
	private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
			
  public static void registerGuide(String email, String password, String name,
      String emergencyContact) 
    		  
    		  throws InvalidInputException {
	  	 
	  ClimbSafe inst = ClimbSafeApplication.getClimbSafe();
	  
	  var error ="";
	  
	  String emailOfAdmin = "admin@nmc.nt";
	  if(email.equals(emailOfAdmin)) {
		  error = "Email cannot be the email of the admin (admin@nmc.nt)";
		  throw new InvalidInputException(error);
		  
	  }
	  
	  List<Guide> guides = climbSafe.getGuides();
	  for(Guide g : guides) {
		  if(email.equals(g.getEmail())) {
			  error = "Email cannot be the same for another guide";
			  throw new InvalidInputException(error);
			  
		  }
		  
		  
	  }
	  
	  
	  if(email.contains(" ")) {
		  
		  error="A guide cannot register an email with spaces";
		  throw new InvalidInputException(error);
		  
	  }
	  
	 
	  if(email.isEmpty() || email.isBlank()) {
		  error ="A guide cannot register an empty or blank email";
		  throw new InvalidInputException(error);
		  
		  
	  }
	  
	  
	  if(password.isEmpty() || password.isBlank()) {
		  error="A guide cannot register an empty or blank password ";
		  throw new InvalidInputException(error);
		  
		  
	  }
	  
	  
	  if(name.isEmpty() || name.isBlank()) {
		  error="A guide cannot register an empty or blank name";
		  throw new InvalidInputException(error);
		  
	  }
      
	  if(emergencyContact.isEmpty() || emergencyContact.isBlank()) {
		  error="A guide cannot register an empty or blank emergency contact";
		  throw new InvalidInputException(error);
		  
		  
	  }
	  
	  
	  
	  try {
		  
		  inst.addGuide(email,password, name, emergencyContact);
		  
	  } catch(RuntimeException e) {
		  
		  error =e.getMessage();
		  throw new InvalidInputException(error);
		  
		  
	  }
	  
	  

	  
	 
      
	  
	  
	  
	  
  }
  
  /** @author Danny Tu
   * This method updates the guide's information in the climbSafe system
   * @param email Email of the guide in which information gets updated/modified
   * @param newPassword New Password of the guide 
   * @param newName New First Name of the guide for the climbSafe system
   * @param newEmergencyContact New Emergency Contact, which is a phone number, that gets registered into the climbSafe system
   * @throws InvalidInputException for inputs whenever errors occur
   * */
  

  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {
	  
	  //guide's email cannot be changed
	  ClimbSafe inst = ClimbSafeApplication.getClimbSafe();
      inst.addGuide(email, newPassword, newName, newEmergencyContact);
      
      var error ="";
      
	  if(newPassword.isEmpty() || newPassword.isBlank()) {
		  
		  error="A guide cannot put an empty or blank password a new password  ";
		  
		  throw new InvalidInputException(error);
		  
	  }
	  
	  
	  if(newName.isEmpty() || newName.isBlank()) {
		  
		  error="A guide cannot put an empty or blank  name as a new name ";
		  
		  throw new InvalidInputException(error);
		  
	  }
	  
	  
	  if(newEmergencyContact.isEmpty() || newEmergencyContact.isBlank()) {
		  
		  error="A guide cannot put an empty or blank emergency contact as a new emergency contact";
		  
		  throw new InvalidInputException(error);
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
  }

}
