package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.Member;

public class ClimbSafeFeatureSet2Controller {
	
	

  public static void registerMember(String email, String password, String name,
      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
      List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException { 
	  
	  var error="";
	  
//	  System.out.println( "SIZEFADSASDASDADSADASDASD");
//	  ClimbSafeApplication.getClimbSafe().getBookedItems().clear();
//	  System.out.println(  ClimbSafeApplication.getClimbSafe().getBookedItems().size()+ "New size");
//	  
	  if( email.equals(null) || password.equals(null) || name.equals(null)  || emergencyContact.equals(null)) {
		  error= "error";
		  
      } 
	  System.out.println(itemNames.size());
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
 
//email.indexOf("@") > 0 // index starts at zero
//email.indexOf("@") = email.lastIndexOf("@")
//email.indexOf("@") < email.lastIndexOf(".") - 1
//email.lastIndexOf(".") < email.length() - 1
 

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
		 //Member newMem =  ClimbSafeApplication.getClimbSafe().addMember(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired);
		 Member newMem = new Member(email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired, ClimbSafeApplication.getClimbSafe());
		 for (int i=0; i<itemNames.size();i++) {
			 //Equipment e = new Equipment(itemNames.get(i),0,0,ClimbSafeApplication.getClimbSafe());
			 //ClimbSafeApplication.getClimbSafe().addBookedItem(itemQuantities.get(i), newMem, e);
			 BookableItem newItem = BookableItem.getWithName(itemNames.get(i));
			 Integer quan = itemQuantities.get(i);
			 BookedItem item = new BookedItem (quan, ClimbSafeApplication.getClimbSafe(),newMem,newItem);	
			 ClimbSafeApplication.getClimbSafe().addBookedItem(item);
		 }
		 //ClimbSafeApplication.getClimbSafe().addBoo 
	  }catch(RuntimeException e) {
		  error = e.getMessage();
		   throw new InvalidInputException(error);
		  
		
	  }

    }

 
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
	
		  while(true) {
			  theMem.getBookedItem(0).delete();
		
			  if(theMem.getBookedItems().size() == 0) {
				  break;
			  }
		  }
		 
		 

		  for(int i=0; i<newItemNames.size();i++) {
			BookableItem item = BookableItem.getWithName(newItemNames.get(i));
		  	theMem.addBookedItem(newItemQuantities.get(i),ClimbSafeApplication.getClimbSafe(), item);
		  }
		  
		 
		  
	
 
	  	}
	  
	  catch(RuntimeException e) {
		  error = e.getMessage();
		  throw new InvalidInputException(error);
		  
	  
	  
	  }
  	}
  }