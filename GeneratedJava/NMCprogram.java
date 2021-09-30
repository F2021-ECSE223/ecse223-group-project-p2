/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 35 "model.ump"
// line 120 "model.ump"
public class NMCprogram
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //NMCprogram Associations
  private ClimbSafe climbSafe;
  private Administrator administrator;
  private List<Assignment> assignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public NMCprogram(ClimbSafe aClimbSafe, Administrator aAdministrator)
  {
    if (aClimbSafe == null || aClimbSafe.getNMCprogram() != null)
    {
      throw new RuntimeException("Unable to create NMCprogram due to aClimbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    climbSafe = aClimbSafe;
    if (aAdministrator == null || aAdministrator.getNMCprogram() != null)
    {
      throw new RuntimeException("Unable to create NMCprogram due to aAdministrator. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    administrator = aAdministrator;
    assignments = new ArrayList<Assignment>();
  }

  public NMCprogram(Date aStartDateForClimbSafe, Date aEndDateForClimbSafe, String aPasswordForAdministrator, String aEmailForAdministrator, ClimbSafe aClimbSafeForAdministrator)
  {
    climbSafe = new ClimbSafe(aStartDateForClimbSafe, aEndDateForClimbSafe, this);
    administrator = new Administrator(aPasswordForAdministrator, aEmailForAdministrator, aClimbSafeForAdministrator, this);
    assignments = new ArrayList<Assignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_GetOne */
  public Administrator getAdministrator()
  {
    return administrator;
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
  public Assignment addAssignment(Date aDate, Equipment aEquipment, EquipmentBundle aEquipmentBundle, Member aMember)
  {
    return new Assignment(aDate, this, aEquipment, aEquipmentBundle, aMember);
  }

  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    NMCprogram existingNMCprogram = aAssignment.getNMCprogram();
    boolean isNewNMCprogram = existingNMCprogram != null && !this.equals(existingNMCprogram);
    if (isNewNMCprogram)
    {
      aAssignment.setNMCprogram(this);
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
    //Unable to remove aAssignment, as it must always have a nMCprogram
    if (!this.equals(aAssignment.getNMCprogram()))
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
    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = null;
    if (existingClimbSafe != null)
    {
      existingClimbSafe.delete();
    }
    Administrator existingAdministrator = administrator;
    administrator = null;
    if (existingAdministrator != null)
    {
      existingAdministrator.delete();
    }
    while (assignments.size() > 0)
    {
      Assignment aAssignment = assignments.get(assignments.size() - 1);
      aAssignment.delete();
      assignments.remove(aAssignment);
    }
    
  }

}