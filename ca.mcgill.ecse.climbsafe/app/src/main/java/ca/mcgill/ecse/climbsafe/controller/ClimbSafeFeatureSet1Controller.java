package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;

public class ClimbSafeFeatureSet1Controller {
      /**
       * @author Salim Benchekroun
       * @param startDate : the starting date of the climbing season 
       * @param nrWeeks : number of weeks in the climbing season 
       * @param priceOfGuidePerWeek : the price of guide per week 
       * */
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
      //ClimbSafeApplication new_instance = new ClimbSafeApplication();
      ClimbSafe inst = ClimbSafeApplication.getClimbSafe();
      inst.setStartDate(startDate);
      inst.setNrWeeks(nrWeeks);
      inst.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
  
  }
  /**
   * @author Salim Benchekroun
   * @param email : the email of a member 
   * */
  public static void deleteMember(String email) {
    ClimbSafe inst = ClimbSafeApplication.getClimbSafe();
      Member member = null;
      for ( Member m : inst.getMembers()) {
          if ( email.equals(m.getEmail()) ) {
             member=m;
          } 
      }
      if (member!=null) member.delete();
      return;
  }
  /**
   * @author Salim Benchekroun
   * @param email : the email of a member 
   * */
  public static void deleteGuide(String email) {
           ClimbSafe inst = ClimbSafeApplication.getClimbSafe();
      Guide guide=null;
      for ( Guide m :  inst.getGuides() ) {
          if (  email.equals(m.getEmail()) ) {
              guide=m;
          }  
      }
      if (guide!=null) guide.delete();
      return;
  }

  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {}

}