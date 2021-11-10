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

public class AssignmentController {
  // 4,5
  public static void InitiateAssignment() throws InvalidInputException { // initiate all assignments

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

  // 6
  public static void StartTrip(int week) throws InvalidInputException {
    String error = null;
    ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
    List<Member> members = climbSafe.getMembers();
    for (Member m : members) {
      if (m.hasAssignment() && m.getAssignment().getStartWeek() == week)
        try {
          m.getAssignment().startTrip();
        } catch (RuntimeException e) {
          error = e.getMessage();
          throw new InvalidInputException(error);
        }
    }
  }

  public static void finishTrip(String email) throws InvalidInputException {
    String error = null;
    try {
      Member member = findMember(email);
      member.getAssignment().finishTrip();
    } catch (RuntimeException e) {
      error = e.getMessage();
      throw new InvalidInputException(error);
    }
  }

  public static void cancelTrip(String email) throws InvalidInputException {
    String error = null;
    try {
      Member member = findMember(email);
      member.getAssignment().cancelTrip();
    } catch (RuntimeException e) {
      error = e.getMessage();
      throw new InvalidInputException(error);
    }
  }

  public static void confirmPayment(String email, String code) throws InvalidInputException {
    String error = null;
    try {
      Member member = findMember(email);
      member.getAssignment().authorization(code);
    } catch (RuntimeException e) {
      error = e.getMessage();
      throw new InvalidInputException(error);
    }

  }

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
