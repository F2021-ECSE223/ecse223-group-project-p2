/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 60 "model.ump"
// line 135 "model.ump"
public class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private int weight;
  private String nameOfTool;
  private int price;

  //Equipment Associations
  private List<Assignment> assignments;
  private EquipmentBundle equipmentBundle;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(int aWeight, String aNameOfTool, int aPrice, EquipmentBundle aEquipmentBundle)
  {
    weight = aWeight;
    nameOfTool = aNameOfTool;
    price = aPrice;
    assignments = new ArrayList<Assignment>();
    boolean didAddEquipmentBundle = setEquipmentBundle(aEquipmentBundle);
    if (!didAddEquipmentBundle)
    {
      throw new RuntimeException("Unable to create equipment due to equipmentBundle. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWeight(int aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setNameOfTool(String aNameOfTool)
  {
    boolean wasSet = false;
    nameOfTool = aNameOfTool;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public int getWeight()
  {
    return weight;
  }

  public String getNameOfTool()
  {
    return nameOfTool;
  }

  public int getPrice()
  {
    return price;
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
  /* Code from template association_GetOne */
  public EquipmentBundle getEquipmentBundle()
  {
    return equipmentBundle;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Assignment addAssignment(Date aDate, NMCprogram aNMCprogram, EquipmentBundle aEquipmentBundle, Member aMember)
  {
    return new Assignment(aDate, aNMCprogram, this, aEquipmentBundle, aMember);
  }

  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    Equipment existingEquipment = aAssignment.getEquipment();
    boolean isNewEquipment = existingEquipment != null && !this.equals(existingEquipment);
    if (isNewEquipment)
    {
      aAssignment.setEquipment(this);
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
    //Unable to remove aAssignment, as it must always have a equipment
    if (!this.equals(aAssignment.getEquipment()))
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasSet = false;
    //Must provide equipmentBundle to equipment
    if (aEquipmentBundle == null)
    {
      return wasSet;
    }

    if (equipmentBundle != null && equipmentBundle.numberOfEquipment() <= EquipmentBundle.minimumNumberOfEquipment())
    {
      return wasSet;
    }

    EquipmentBundle existingEquipmentBundle = equipmentBundle;
    equipmentBundle = aEquipmentBundle;
    if (existingEquipmentBundle != null && !existingEquipmentBundle.equals(aEquipmentBundle))
    {
      boolean didRemove = existingEquipmentBundle.removeEquipment(this);
      if (!didRemove)
      {
        equipmentBundle = existingEquipmentBundle;
        return wasSet;
      }
    }
    equipmentBundle.addEquipment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=assignments.size(); i > 0; i--)
    {
      Assignment aAssignment = assignments.get(i - 1);
      aAssignment.delete();
    }
    EquipmentBundle placeholderEquipmentBundle = equipmentBundle;
    this.equipmentBundle = null;
    if(placeholderEquipmentBundle != null)
    {
      placeholderEquipmentBundle.removeEquipment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "weight" + ":" + getWeight()+ "," +
            "nameOfTool" + ":" + getNameOfTool()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "equipmentBundle = "+(getEquipmentBundle()!=null?Integer.toHexString(System.identityHashCode(getEquipmentBundle())):"null");
  }
}