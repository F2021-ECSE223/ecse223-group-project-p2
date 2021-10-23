package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;

import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

public class ClimbSafeFeatureSet1Controller {

  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {
	  if ( nrWeeks < 0 || priceOfGuidePerWeek < 0) {
		  throw new InvalidInputException("nrWeeks and priceOfGuide must not be negative");
	  }
	  ClimbSafe new_instance = new ClimbSafe(startDate, nrWeeks, priceOfGuidePerWeek);
  }

  public static void deleteMember(String email) {
	  
  }

  public static void deleteGuide(String email) {}

  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {}

}
