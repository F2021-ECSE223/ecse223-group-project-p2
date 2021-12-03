package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

public class ClimbSafeFeatureSet2Controller {
/**
 * @author Jian Long Ye (Noah)
 * This method registers a member into system that includes the email password, name,
 * emergency contact, number of weeks for climbing,if guide is needed, items and item quantities
 * @param email  The email the member registers with
 * @param password  The password the member registers with
 * @param name  The name the member registers with
 * @param emergencyContact  The emergency contact the member registers with
 * @param nrWeeks  The number of climbing weeks the member registers with
 * @param guideRequired  If a member wants a guide
 * @param hotelRequired  If member needs a hotel
 * @param itemNames  The item names in a list the member chose
 * @param itemQuantities The quantities of the item for each item in the item name list
 * @throws InvalidInputException This gives an exception whenever user puts an invalid input 
 */
  public static void registerMember(String email, String password, String name,
      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
      List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException { 
	  
	  var error="";
	  
	  if( email.equals(null) || password.equals(null) || name.equals(null)  || emergencyContact.equals(null)) {
		  error= "error";  
      } 
	  if(itemNames.size()!= itemQuantities.size()) {
		  
		  error ="size of item names is not equal to quntity of items" ;
	  }
	  
	  if (password.isEmpty()) {
		  error = "The password cannot be empty";
	  }
	  
	  if (name.isEmpty()) {
		  error = "The name cannot be empty";
	  }
	  
	  if (emergencyContact.isEmpty()) {
		  error = "The emergency contact cannot be empty";
	  }
	  
	  if (!(nrWeeks>0 && nrWeeks<=ClimbSafeApplication.getClimbSafe().getNrWeeks())) {
		  error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
	  }
	  
	  if (email.equals("admin@nmc.nt")) {
		  error= "The email entered is not allowed for members";
	  }
	  
	  for (int i=0; i<ClimbSafeApplication.getClimbSafe().getGuides().size();i++) {
			 
			 if (email.equals(ClimbSafeApplication.getClimbSafe().getGuide(i).getEmail())) {
				
					error= "A guide with this email already exists";
					break;
			  }
	  }
	  
	  
 for (int i=0; i<ClimbSafeApplication.getClimbSafe().getMembers().size();i++) {
	 //System.out.println(email +  " ------ " + ClimbSafeApplication.getClimbSafe().getMember(i).getEmail());
	 if (email.equals(ClimbSafeApplication.getClimbSafe().getMember(i).getEmail())) {
			error= "A member with this email already exists";
			break;
	  }
 }
 
 	  if (email.contains(" ")) {
	 error="The email must not contain any spaces";
 	  }
 
 	  if (!email.contains(".")) {
	 error= "Invalid email";
 	  }

 	  if(!(email.indexOf("@") >0)) {
 		  error="Invalid email";
 	  }
 	  
 	  if(!(email.indexOf("@") == email.lastIndexOf("@"))) {
 		  error="Invalid email";  
 	  }
 	  
 	  if(!(email.indexOf("@") < email.lastIndexOf(".") - 1)) {
 		  error="Invalid email";	  
 	  }
 	  
 	  if(!(email.lastIndexOf(".") < email.length() - 1)) {
 		  error="Invalid email";
 	  }

 	  for(int i=0; i<itemNames.size();i++) {
 		  if(BookableItem.getWithName(itemNames.get(i)) == null) {
 			  error= "Requested item not found";
 			  break;
 		  }
 	  }
 

 if (error.length() > 0) {
     throw new InvalidInputException(error.trim());
 }

	  try {
		 Member newMem = new Member(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired, ClimbSafeApplication.getClimbSafe());
		 for (int i=0; i<itemNames.size();i++) {
			 BookableItem newItem = BookableItem.getWithName(itemNames.get(i));
			 Integer quan = itemQuantities.get(i);
			 BookedItem item = new BookedItem (quan, ClimbSafeApplication.getClimbSafe(),newMem,newItem);	
			 ClimbSafeApplication.getClimbSafe().addBookedItem(item);
		 }
		 ClimbSafePersistence.save();
	  }
	  catch(RuntimeException e) {
		  error = e.getMessage();
		   throw new InvalidInputException(error);  
	  }
    }

 /**
  * @author Jian Long (Noah) Ye
  * This method updates the name, password, emergency contact, if guide is required
  * number of climbing weeks, if hotel is required, item names in a list and item quantities in a list for 
  * each item name
  * @param email  This is the email of the member(unique) that is not updated
  * @param newPassword  This is the updated password of the member
  * @param newName  This is the name the member wants to update to
  * @param newEmergencyContact  This is the updated emergency contact of the member
  * @param newNrWeeks  This is the updated number of climbing weeks for the member
  * @param newGuideRequired  This updates if a member wants a guide
  * @param newHotelRequired  This updates if a member needs a hotel
  * @param newItemNames  This updates the item names in the list
  * @param newItemQuantities  This updates the item quantity for each item in the list of item names
  * @throws InvalidInputException An exception will be thrown when an invalid input takes place
  */
  public static void updateMember(String email, String newPassword, String newName,
      String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
      boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities)
      throws InvalidInputException {
	 
	  var error ="";
	  
	  if( email.equals(null) || newPassword.equals(null) || newName.equals(null)  || newEmergencyContact.equals(null)) {
		  error= "error";  
      } 
	  
	
	  if(newPassword.isEmpty()) {
		  error="The password cannot be empty";   
	  }
	  
	  if(newName.isEmpty()) { 
		  error=" The name cannot be empty";  
	  }
	  
	  for(int i=0; i<newItemNames.size();i++) {
 		  if(BookableItem.getWithName(newItemNames.get(i)) == null) {
 			  error= "Requested item not found";
 			  break;
 		  }
	  }
 
	  if(newEmergencyContact.isEmpty()) {
		  
		  error="The emergency contact cannot be empty";
		  
		  throw new InvalidInputException(error);
	  }
	  
	  if (!(newNrWeeks>0 && newNrWeeks<=ClimbSafeApplication.getClimbSafe().getNrWeeks())) {
		  error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
	  }
	  
	  if (error.length() > 0) {
		     throw new InvalidInputException(error.trim());
		 }
	  
	  try {
		
		  Member theMem = null;
		  theMem = (Member) Member.getWithEmail(email);
		  
		  if(theMem==null) {
			  throw new InvalidInputException("Member not found");			  
		  }
		  
		  theMem.setName(newName);
 		  theMem.setEmergencyContact(newEmergencyContact);
		  theMem.setPassword(newPassword);
  	      theMem.setGuideRequired(newGuideRequired);
		  theMem.setHotelRequired(newHotelRequired);
		  theMem.setNrWeeks(newNrWeeks);
	
		  while(theMem.getBookedItems().size() > 0) {
			  theMem.getBookedItem(0).delete();
	
			  if(theMem.getBookedItems().size() == 0) {
				  break;
			 }
		  }
		  for(int i=0; i<newItemNames.size();i++) {
			BookableItem item = BookableItem.getWithName(newItemNames.get(i));
		  	theMem.addBookedItem(newItemQuantities.get(i),ClimbSafeApplication.getClimbSafe(), item);
		  }	
		  ClimbSafePersistence.save();
	  	}
	  
	  catch(RuntimeException e) {
		  error = e.getMessage();
		  throw new InvalidInputException(error);

	  }
  	}
  }

