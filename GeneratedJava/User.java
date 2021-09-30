/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 90 "model.ump"
// line 155 "model.ump"
public abstract class User extends Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private int emergencyContact;
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aPassword, String aEmail, ClimbSafe aClimbSafe, int aEmergencyContact, String aName)
  {
    super(aPassword, aEmail, aClimbSafe);
    emergencyContact = aEmergencyContact;
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmergencyContact(int aEmergencyContact)
  {
    boolean wasSet = false;
    emergencyContact = aEmergencyContact;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public int getEmergencyContact()
  {
    return emergencyContact;
  }

  public String getName()
  {
    return name;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "emergencyContact" + ":" + getEmergencyContact()+ "," +
            "name" + ":" + getName()+ "]";
  }
}