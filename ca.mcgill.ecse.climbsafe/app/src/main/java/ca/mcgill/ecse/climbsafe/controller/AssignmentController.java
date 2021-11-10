package ca.mcgill.ecse.climbsafe.controller;

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
  public static void InitiateAssignment() { // initiate all assignments

    ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

    int i = 0;
    int j = 0;
   while (i < climbSafe.getGuides().size()) {
      System.out.println(climbSafe.getGuides().size());
      while (j < climbSafe.getMembers().size()) {
        Member m = climbSafe.getMember(j); // for convenience
        Guide g = climbSafe.getGuide(i); // for convenience
        int nbrWeeks = m.getNrWeeks(); // number of weeks member wants to climb
        int totalWeeks = climbSafe.getNrWeeks(); // number of weeks in climbing season
        int start = 0;
        int end = 0;
        // Assignment assignment = climbSafe.getAssignment(j);
        // test if guide is available during that time
        // 1.calculate number of weeks guide is busy
        int k = 0;
        int sum = 0;
        while (k < g.getAssignments().size()) {
          sum += g.getAssignments().get(k).getMember().getNrWeeks();
          k++;
        }
        // 2. Is ( nbrWeeks_available >= nbrWeeks_member)?
        // ie. (totalWeeks - sum) >= nbrWeeks?
        if (m.getGuideRequired() == false && (!m.hasAssignment())) { // if member doesn't want a
                                                                     // guide
          Assignment a = new Assignment(0, m.getNrWeeks(), climbSafe.getMember(j), climbSafe);
        } else if ((totalWeeks - sum) >= nbrWeeks) {
          start = sum + 1;
          end = start + nbrWeeks - 1;
          if (m.getGuideRequired() == true && (!m.hasAssignment())) { // if member needs a guide,                                                                     // but has no guide yet
            Assignment a = new Assignment(start, end, climbSafe.getMember(j), climbSafe);
            a.setGuide(climbSafe.getGuide(i)); // add the guide to the assignment
          }
        }
        j++;
      }
      i++;
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
