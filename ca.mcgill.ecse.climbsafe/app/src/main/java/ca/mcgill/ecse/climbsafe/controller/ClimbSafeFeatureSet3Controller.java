package ca.mcgill.ecse.climbsafe.controller;



import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;



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
	  	 

	  
	  var error ="";
	  
	  if(email.isEmpty() || email.isBlank()) {
		  error ="Email cannot be empty";
		  throw new InvalidInputException(error);
		  
		  
	  }
	  
	  String emailOfAdmin = "admin@nmc.nt";
	  if(email.equals(emailOfAdmin)) {
		  error = "Email cannot be admin@nmc.nt";
		  throw new InvalidInputException(error);
		  
	  }
	  
	  List<Guide> guides = climbSafe.getGuides();
	  for(Guide g : guides) {
		  if(email.equals(g.getEmail())) {
			  error = "Email already linked to a guide account";
			  throw new InvalidInputException(error);
			  
		  }
		  
		  
	  }
	  
	  List<Member> members = climbSafe.getMembers();
	  for(Member m : members) {
		  if(email.equals(m.getEmail())) {
			  error ="Email already linked to a member account";
			  throw new InvalidInputException(error);
			  
		  }
		  
		  
	  }
	  
	  if(!email.contains(name.toLowerCase())) {
		  error="Invalid email";
		  throw new InvalidInputException(error);
		  
		  
	  }
	  
	  
	  if(!(email.indexOf("@") >0)) {
		  
		  error="Invalid email";
		  throw new InvalidInputException(error);
	  }
	  
	  if(!(email.indexOf("@") == email.lastIndexOf("@"))) {
		  error="Invalid email";
		  throw new InvalidInputException(error);
		  
	  }
	  
	  if(!(email.indexOf("@") < email.lastIndexOf(".") - 1)) {
		  error="Invalid email";
		  throw new InvalidInputException(error);
		  
	  }
	  
	  if(!(email.lastIndexOf(".") < email.length() - 1)) {
		  error="Invalid email";
		  throw new InvalidInputException(error);
		  
	  }
	  
	  if(email.contains(" ")) {
		  
		  error="Email must not contain any spaces";
		  throw new InvalidInputException(error);
		  
	  }
	  
	  if(password.isEmpty() || password.isBlank()) {
		  error="Password cannot be empty";
		  throw new InvalidInputException(error);
		  
		  
	  }
	  
	  
	  if(name.isEmpty() || name.isBlank()) {
		  error="Name cannot be empty";
		  throw new InvalidInputException(error);
		  
	  }
      
	  if(emergencyContact.isEmpty() || emergencyContact.isBlank()) {
		  error="Emergency contact cannot be empty";
		  throw new InvalidInputException(error);
		  
		  
	  }
	  
	  
	  
	  try {
		  
		  climbSafe.addGuide(email,password, name, emergencyContact);
		  
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
      var error ="";
	  if(newPassword.isEmpty() || newPassword.isBlank()) {
		  
		  error="Password cannot be empty";
		  
		  throw new InvalidInputException(error);
		  
	  }
	  
	  
	  if(newName.isEmpty() || newPassword.isBlank()) {
		  
		  error="Name cannot be empty";
		  
		  throw new InvalidInputException(error);
		  
	  }
	  
	  
	  if(newEmergencyContact.isEmpty() || newEmergencyContact.isBlank()) {
		  
		  error="Emergency contact cannot be empty";
		  
		  throw new InvalidInputException(error);
	  }
	  
	  
	  try {
		  
		  List<Guide> guides = climbSafe.getGuides();
		  for(Guide g : guides) {
			  if(email.equals(g.getEmail())) {
				 
				  g.setName(newName);
				  g.setEmergencyContact(newEmergencyContact);
				  g.setPassword(newPassword);

				  
			  }
			  
			  
    	  }
		  
		  		 
	  }
	  
	  catch(RuntimeException e) {
		  error = e.getMessage();
		  throw new InvalidInputException(error);
		  
	  }
	  
	  
	  
	  
	  
	  
	  
  }

}
