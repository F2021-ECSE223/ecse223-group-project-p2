package ca.mcgill.ecse.climbsafe.controller;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
public class AssignmentController {
 //4 5 
  public static void InitiateAssignment() {
    
  }
 //6
  public static void StartTrip(int week) {

  }
  
  public static void finishTrip(String email) {
    Member member= findMember(email);
    member.getAssignment().finishTrip();
  }
  
  public static void cancelTrip(String email) {
    Member member= findMember(email);
    member.getAssignment().cancelTrip();
  }
  
  public static void confirmPayment(String email,String code) {
    Member member= findMember(email);
    member.getAssignment().authorization(code);
  }
    
  private static Member findMember(String email) {
    ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
    Member foundMember = null;
    foundMember=(Member) Member.getWithEmail(email);
    if (foundMember==null) {throw new RuntimeException("Member with email address "+email+" does not exist");}
    return foundMember;
  }
}
