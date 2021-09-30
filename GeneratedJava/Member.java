/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 47 "model.ump"
// line 130 "model.ump"
public class Member extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Member Attributes
  private int totalPrice;
  private String climbingWeekNumber;
  private boolean hasGuide;
  private boolean stayBeforeClimb;
  private boolean stayAfterClimb;
  private int numberOfClimbingWeeks;

  //Member Associations
  private List<Assignment> assignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member(String aPassword, String aEmail, ClimbSafe aClimbSafe, int aEmergencyContact, String aName, int aTotalPrice, String aClimbingWeekNumber, boolean aHasGuide, boolean aStayBeforeClimb, boolean aStayAfterClimb, int aNumberOfClimbingWeeks)
  {
    super(aPassword, aEmail, aClimbSafe, aEmergencyContact, aName);
    totalPrice = aTotalPrice;
    climbingWeekNumber = aClimbingWeekNumber;
    hasGuide = aHasGuide;
    stayBeforeClimb = aStayBeforeClimb;
    stayAfterClimb = aStayAfterClimb;
    numberOfClimbingWeeks = aNumberOfClimbingWeeks;
    assignments = new ArrayList<Assignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTotalPrice(int aTotalPrice)
  {
    boolean wasSet = false;
    totalPrice = aTotalPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setClimbingWeekNumber(String aClimbingWeekNumber)
  {
    boolean wasSet = false;
    climbingWeekNumber = aClimbingWeekNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setHasGuide(boolean aHasGuide)
  {
    boolean wasSet = false;
    hasGuide = aHasGuide;
    wasSet = true;
    return wasSet;
  }

  public boolean setStayBeforeClimb(boolean aStayBeforeClimb)
  {
    boolean wasSet = false;
    stayBeforeClimb = aStayBeforeClimb;
    wasSet = true;
    return wasSet;
  }

  public boolean setStayAfterClimb(boolean aStayAfterClimb)
  {
    boolean wasSet = false;
    stayAfterClimb = aStayAfterClimb;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfClimbingWeeks(int aNumberOfClimbingWeeks)
  {
    boolean wasSet = false;
    numberOfClimbingWeeks = aNumberOfClimbingWeeks;
    wasSet = true;
    return wasSet;
  }

  public int getTotalPrice()
  {
    return totalPrice;
  }

  public String getClimbingWeekNumber()
  {
    return climbingWeekNumber;
  }

  public boolean getHasGuide()
  {
    return hasGuide;
  }

  public boolean getStayBeforeClimb()
  {
    return stayBeforeClimb;
  }

  public boolean getStayAfterClimb()
  {
    return stayAfterClimb;
  }

  public int getNumberOfClimbingWeeks()
  {
    return numberOfClimbingWeeks;
  }
  /* Code from template association_GetMany */
  public Assignment getAssignment(int index)
  {
    Assignment aAssignment = assignments.get(index);
    return aAssignment;
  }

  public List<Assignment> getAssignments()
  {
    List<Assignment> newAssignments = Collections.unmodifiableList(assignments);
    return newAssignments;
  }

  public int numberOfAssignments()
  {
    int number = assignments.size();
    return number;
  }

  public boolean hasAssignments()
  {
    boolean has = assignments.size() > 0;
    return has;
  }

  public int indexOfAssignment(Assignment aAssignment)
  {
    int index = assignments.indexOf(aAssignment);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Assignment addAssignment(Date aDate, NMCprogram aNMCprogram, Equipment aEquipment, EquipmentBundle aEquipmentBundle)
  {
    return new Assignment(aDate, aNMCprogram, aEquipment, aEquipmentBundle, this);
  }

  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    Member existingMember = aAssignment.getMember();
    boolean isNewMember = existingMember != null && !this.equals(existingMember);
    if (isNewMember)
    {
      aAssignment.setMember(this);
    }
    else
    {
      assignments.add(aAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssignment(Assignment aAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAssignment, as it must always have a member
    if (!this.equals(aAssignment.getMember()))
    {
      assignments.remove(aAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssignmentAt(Assignment aAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addAssignment(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignmentAt(Assignment aAssignment, int index)
  {
    boolean wasAdded = false;
    if(assignments.contains(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignmentAt(aAssignment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=assignments.size(); i > 0; i--)
    {
      Assignment aAssignment = assignments.get(i - 1);
      aAssignment.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "totalPrice" + ":" + getTotalPrice()+ "," +
            "climbingWeekNumber" + ":" + getClimbingWeekNumber()+ "," +
            "hasGuide" + ":" + getHasGuide()+ "," +
            "stayBeforeClimb" + ":" + getStayBeforeClimb()+ "," +
            "stayAfterClimb" + ":" + getStayAfterClimb()+ "," +
            "numberOfClimbingWeeks" + ":" + getNumberOfClimbingWeeks()+ "]";
  }
}