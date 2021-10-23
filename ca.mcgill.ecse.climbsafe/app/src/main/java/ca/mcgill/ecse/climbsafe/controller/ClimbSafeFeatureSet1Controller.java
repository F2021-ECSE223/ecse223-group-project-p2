package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

public class ClimbSafeFeatureSet1Controller {

  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {
	  
	  //test for illegal inputs 
	  if ( nrWeeks < 0 ) {
		  throw new InvalidInputException("The number of climbing weeks must be greater than or equal to zero");
	  } 
	  if ( priceOfGuidePerWeek < 0) {
		  throw new InvalidInputException("The price of guide per week must be greater than or equal to zero");
	  }
	  //convert illegalargumentexception to invalidinputexception
	  try {
		  String date_s = startDate.toString(); 
		  java.sql.Date.valueOf(date_s); 
	  } catch (IllegalArgumentException e) {
		  throw new InvalidInputException("Invalid date");
	  }
	  ClimbSafeApplication new_instance = new ClimbSafeApplication();
	  ClimbSafe inst = ClimbSafeApplication.getClimbSafe();
	  inst.setStartDate(startDate);
	  inst.setNrWeeks(nrWeeks);
	  inst.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
  }

  public static void deleteMember(String email) {
	  int i = 0;
	  ClimbSafe inst = ClimbSafeApplication.getClimbSafe();
	  while (i < inst.getMembers().size() ) {
		  if ( inst.getMember(i).getEmail() == email ) {
			  inst.removeMember(inst.getMember(i));
			  return;
		  }
	  }
  }

  public static void deleteGuide(String email) {
	  int i = 0;
	  ClimbSafe inst = ClimbSafeApplication.getClimbSafe();
	  while (i < inst.getGuides().size() ) {
		  if ( inst.getGuide(i).getEmail() == email ) {
			  inst.removeGuide(inst.getGuide(i));
			  return;
		  }
	  }
  }

  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {}

}
