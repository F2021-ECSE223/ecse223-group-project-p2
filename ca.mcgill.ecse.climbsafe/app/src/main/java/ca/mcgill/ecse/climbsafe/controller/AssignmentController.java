package ca.mcgill.ecse.climbsafe.controller;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
public class AssignmentController {
 //4,5 
  public static void InitiateAssignment() { //initiate all assignments
	  
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  Assignment assignment = climbSafe.getAssignment(0);
 	  
	  int i = 0;
	  int j = 0;
	  
	  while ( i <  climbSafe.getGuides().size() ) {
		 while ( j < climbSafe.getMembers().size() ){
			 
			 Member m = climbSafe.getMember(j);
			 
			 assignment = climbSafe.getAssignment(j);
			 int start = assignment.getStartWeek();
			 int end = assignment.getEndWeek();		
			 
			 if ( m.getGuideRequired() == true && assignment.hasGuide() == false ) { // if member needs a guide, but has no guide yet 
				 
				 //test if guide is available during that time 
				 boolean isAvailable = true;
				 int k = 0;
				 while ( k < climbSafe.getGuide(i).getAssignments().size() ) { //iterate through all the assignments of said guide 
					 int temp1 = climbSafe.getGuide(i).getAssignment(k).getStartWeek();
					 int temp2 = climbSafe.getGuide(i).getAssignment(k).getEndWeek();
					 //checking for overlap between region [temp1, temp2] and [start, end]
					 if ( (temp1 < start && temp2 > start) || (temp1 < end && temp2 > end ) ) { 
						 isAvailable = false; //if there is already an assignment in that time period, then guide is not available 
					 }
					 k++;
				 } 
				 if ( isAvailable == true ) {  // if guide available (doesn't have a member during that period) 
					 new Assignment(start, end, climbSafe.getMember(j), climbSafe);
				 }
			 }	 
			 j++;
		 }
		 i++; 
	  }
  }
 //6
  public static void StartTrip(int week) { //starts for all members startdate == week

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

