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
			 
			 Member m = climbSafe.getMember(j); //for convenience 
			 Guide g = climbSafe.getGuide(i); //for convenience 
			 
			 int nbrWeeks = m.getNrWeeks(); //number of weeks member wants to climb
			 int totalWeeks = climbSafe.getNrWeeks(); //number of weeks in climbing season 
			 int start = 0;
			 int end = 0;
			 
			assignment = climbSafe.getAssignment(j);	
				 
			//test if guide is available during that time 
			boolean isAvailable = true;
			//1.calculate number of weeks guide is busy
			int k = 0;
			int sum = 0;
			while ( k < g.getAssignments().size() ) {
				sum += m.getNrWeeks();
			}
			//2. Is ( nbrWeeks_available >= nbrWeeks_member)? 
			//  ie. (totalWeeks - sum) >= nbrWeeks? 
			if ( (totalWeeks-sum) >= nbrWeeks ) {
				isAvailable = true;
				start = sum + 1;
				end = totalWeeks - nbrWeeks;
			}
			
			
			// if guide not available 
			if ( isAvailable == false ) {  //go to next member
				continue; 
			}
			
			//if guide available 
			if ( m.getGuideRequired() == true && assignment.hasGuide() == false ) { // if member needs a guide, but has no guide yet 
				Assignment a = new Assignment(start, end, climbSafe.getMember(j), climbSafe);
				a.setGuide(climbSafe.getGuide(i)); //add the guide to the assignment 
			}
			 
			if ( m.getGuideRequired() == false && assignment.hasGuide() == false ) { // if member doesn't want a guide 
				Assignment a = new Assignment(0, m.getNrWeeks(), climbSafe.getMember(j), climbSafe);
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

