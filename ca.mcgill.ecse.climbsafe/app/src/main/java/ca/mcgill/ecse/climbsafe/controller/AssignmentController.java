package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

public class AssignmentController {
  //Salim Danny JavaDoc
  public static void initiateAssignment() throws InvalidInputException { // initiate all assignments

    ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
    ArrayList<Assignment> allAss = new ArrayList<Assignment>();
   int memberIndex = 0;
   for (int i = 0; i < climbSafe.getGuides().size();i++) {
      Guide currentGuide = climbSafe.getGuide(i);
      for (Member currentMember: climbSafe.getMembers()) {
           
            
            if ( currentMember.hasAssignment() == true ) {
                memberIndex ++;
                continue;
            }
            
            if (!currentMember.getGuideRequired()) {
                Assignment a = new Assignment(1, currentMember.getNrWeeks(), currentMember, climbSafe);
                climbSafe.addAssignment(a);
                allAss.add(a);
                currentMember.getAssignment().setTestStatus("Assigned");
                ClimbSafePersistence.save();
                memberIndex ++;
                continue;
            }
        
            int memberWeeks = currentMember.getNrWeeks(); // number of weeks member wants to climb
            int totalWeeks = climbSafe.getNrWeeks(); // number of weeks in climbing season
            int start = 0;
            int end = 0;
            // Assignment assignment = climbSafe.getAssignment(j);
            // test if guide is available during that time
            // 1.calculate number of weeks guide is busy
    
            int busyWeeks = 0;
            for (int k = 0; k < currentGuide.getAssignments().size(); k++) {
              busyWeeks += currentGuide.getAssignments().get(k).getMember().getNrWeeks();
            }
            // 2. Is ( nbrWeeks_available >= nbrWeeks_member)?
            // ie. (totalWeeks - sum) >= nbrWeeks?
            if ((totalWeeks - busyWeeks) >= memberWeeks) {
              start = busyWeeks + 1;
              end = start + memberWeeks - 1;
              // if member needs a guide,                                                                     // but has no guide yet
                Assignment a = new Assignment(start, end, currentMember, climbSafe);
                a.setGuide(climbSafe.getGuide(i)); // add the guide to the assignment
                climbSafe.addAssignment(a);
                currentMember.getAssignment().setTestStatus("Assigned");
                allAss.add(a);
                ClimbSafePersistence.save();
                memberIndex ++;
                continue;
    
             }
            memberIndex ++;
      }
    }
   
   for (Member m : climbSafe.getMembers() ) {
       if ( m.hasAssignment() == false ) {
           throw new InvalidInputException("Assignments could not be completed for all members");
       }
   }
  }

  /**
   * used by Administrater to start all trips for a specific week
   * @author Runge (Karen) Fu
   * @param  week week number
   */
  public static void StartTrip(int week) throws InvalidInputException {
    String error = null;
    ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
    List<Member> members = climbSafe.getMembers();
    for (Member m : members) {
      if (m.hasAssignment() && m.getAssignment().getStartWeek() == week)
        try {
          m.getAssignment().startTrip();
          ClimbSafePersistence.save();
        } catch (RuntimeException e) {
          error = e.getMessage();
          throw new InvalidInputException(error);
        }
    }
  }
  /**
   * used by Administrater to finish a member's trip
   * @author Peini Cheng
   * @param  email email of the required member
   */
  public static void finishTrip(String email) throws InvalidInputException {
    String error = null;
    try {
      Member member = findMember(email);
      member.getAssignment().finishTrip();
      ClimbSafePersistence.save();
    } catch (RuntimeException e) {
      error = e.getMessage();
      throw new InvalidInputException(error);
    }
  }
  /**
   * used by Administrater to cancel a member's trip
   * @author Peini Cheng
   * @param  email email of the required member
   */
  public static void cancelTrip(String email) throws InvalidInputException {
    String error = null;
    try {
      Member member = findMember(email);
      member.getAssignment().cancelTrip();
      ClimbSafePersistence.save();
    } catch (RuntimeException e) {
      error = e.getMessage();
      throw new InvalidInputException(error);
    }
  }
  /**
   * used by Administrater to confirm payment for a member
   * @author Peini Cheng
   * @param  email email of the required member
   * @param  code authorization code
   */
  public static void confirmPayment(String email, String code) throws InvalidInputException {
    String error = null;
    try {
      Member member = findMember(email);
      member.getAssignment().authorization(code);
      ClimbSafePersistence.save();
    } catch (RuntimeException e) {
      error = e.getMessage();
      throw new InvalidInputException(error);
    }

  }
  /**
   * helper method to find member with email.
   * @author Peini Cheng
   * @param  email email of the required member
   */
  private static Member findMember(String email) throws InvalidInputException {
    ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
    Member foundMember = null;
    foundMember = (Member) Member.getWithEmail(email);
    if (foundMember == null) {
      throw new InvalidInputException("Member with email address " + email + " does not exist");
    }
    return foundMember;
  }
}