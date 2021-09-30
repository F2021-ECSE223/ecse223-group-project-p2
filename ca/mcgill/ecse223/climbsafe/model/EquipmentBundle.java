/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;
import java.sql.Date;

// line 69 "../../../../../umple code 2.ump"
public class EquipmentBundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EquipmentBundle Attributes
  private int price;
  private String nameOfBundle;
  private int discount;

  //EquipmentBundle Associations
  private List<Assignment> assignments;
  private List<Equipment> equipment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EquipmentBundle(int aPrice, String aNameOfBundle, int aDiscount)
  {
    price = aPrice;
    nameOfBundle = aNameOfBundle;
    discount = aDiscount;
    assignments = new ArrayList<Assignment>();
    equipment = new ArrayList<Equipment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setNameOfBundle(String aNameOfBundle)
  {
    boolean wasSet = false;
    nameOfBundle = aNameOfBundle;
    wasSet = true;
    return wasSet;
  }

  public boolean setDiscount(int aDiscount)
  {
    boolean wasSet = false;
    discount = aDiscount;
    wasSet = true;
    return wasSet;
  }

  public int getPrice()
  {
    return price;
  }

  public String getNameOfBundle()
  {
    return nameOfBundle;
  }

  public int getDiscount()
  {
    return discount;
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
  /* Code from template association_GetMany */
  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipment.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipment()
  {
    List<Equipment> newEquipment = Collections.unmodifiableList(equipment);
    return newEquipment;
  }

  public int numberOfEquipment()
  {
    int number = equipment.size();
    return number;
  }

  public boolean hasEquipment()
  {
    boolean has = equipment.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipment.indexOf(aEquipment);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Assignment addAssignment(Date aDate, NMCprogram aNMCprogram, Equipment aEquipment, Member aMember)
  {
    return new Assignment(aDate, aNMCprogram, aEquipment, this, aMember);
  }

  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    EquipmentBundle existingEquipmentBundle = aAssignment.getEquipmentBundle();
    boolean isNewEquipmentBundle = existingEquipmentBundle != null && !this.equals(existingEquipmentBundle);
    if (isNewEquipmentBundle)
    {
      aAssignment.setEquipmentBundle(this);
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
    //Unable to remove aAssignment, as it must always have a equipmentBundle
    if (!this.equals(aAssignment.getEquipmentBundle()))
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
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfEquipmentValid()
  {
    boolean isValid = numberOfEquipment() >= minimumNumberOfEquipment();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipment()
  {
    return 2;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Equipment addEquipment(int aWeight, String aNameOfTool, int aPrice)
  {
    Equipment aNewEquipment = new Equipment(aWeight, aNameOfTool, aPrice, this);
    return aNewEquipment;
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    EquipmentBundle existingEquipmentBundle = aEquipment.getEquipmentBundle();
    boolean isNewEquipmentBundle = existingEquipmentBundle != null && !this.equals(existingEquipmentBundle);

    if (isNewEquipmentBundle && existingEquipmentBundle.numberOfEquipment() <= minimumNumberOfEquipment())
    {
      return wasAdded;
    }
    if (isNewEquipmentBundle)
    {
      aEquipment.setEquipmentBundle(this);
    }
    else
    {
      equipment.add(aEquipment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    //Unable to remove aEquipment, as it must always have a equipmentBundle
    if (this.equals(aEquipment.getEquipmentBundle()))
    {
      return wasRemoved;
    }

    //equipmentBundle already at minimum (2)
    if (numberOfEquipment() <= minimumNumberOfEquipment())
    {
      return wasRemoved;
    }

    equipment.remove(aEquipment);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipment.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
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
    for(int i=equipment.size(); i > 0; i--)
    {
      Equipment aEquipment = equipment.get(i - 1);
      aEquipment.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "price" + ":" + getPrice()+ "," +
            "nameOfBundle" + ":" + getNameOfBundle()+ "," +
            "discount" + ":" + getDiscount()+ "]";
  }
}