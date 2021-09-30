/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.sql.Date;
import java.util.*;

// line 4 "../../../../../umple code 2.ump"
public class ClimbSafe
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbSafe Attributes
  private Date startDate;
  private Date endDate;

  //ClimbSafe Associations
  private List<Person> persons;
  private NMCprogram nMCprogram;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbSafe(Date aStartDate, Date aEndDate, NMCprogram aNMCprogram)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    persons = new ArrayList<Person>();
    if (aNMCprogram == null || aNMCprogram.getClimbSafe() != null)
    {
      throw new RuntimeException("Unable to create ClimbSafe due to aNMCprogram. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    nMCprogram = aNMCprogram;
  }

  public ClimbSafe(Date aStartDate, Date aEndDate, Administrator aAdministratorForNMCprogram)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    persons = new ArrayList<Person>();
    nMCprogram = new NMCprogram(this, aAdministratorForNMCprogram);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }
  /* Code from template association_GetMany */
  public Person getPerson(int index)
  {
    Person aPerson = persons.get(index);
    return aPerson;
  }

  public List<Person> getPersons()
  {
    List<Person> newPersons = Collections.unmodifiableList(persons);
    return newPersons;
  }

  public int numberOfPersons()
  {
    int number = persons.size();
    return number;
  }

  public boolean hasPersons()
  {
    boolean has = persons.size() > 0;
    return has;
  }

  public int indexOfPerson(Person aPerson)
  {
    int index = persons.indexOf(aPerson);
    return index;
  }
  /* Code from template association_GetOne */
  public NMCprogram getNMCprogram()
  {
    return nMCprogram;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPersons()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addPerson(Person aPerson)
  {
    boolean wasAdded = false;
    if (persons.contains(aPerson)) { return false; }
    ClimbSafe existingClimbSafe = aPerson.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aPerson.setClimbSafe(this);
    }
    else
    {
      persons.add(aPerson);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePerson(Person aPerson)
  {
    boolean wasRemoved = false;
    //Unable to remove aPerson, as it must always have a climbSafe
    if (!this.equals(aPerson.getClimbSafe()))
    {
      persons.remove(aPerson);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPersonAt(Person aPerson, int index)
  {  
    boolean wasAdded = false;
    if(addPerson(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePersonAt(Person aPerson, int index)
  {
    boolean wasAdded = false;
    if(persons.contains(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPersonAt(aPerson, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (persons.size() > 0)
    {
      Person aPerson = persons.get(persons.size() - 1);
      aPerson.delete();
      persons.remove(aPerson);
    }
    
    NMCprogram existingNMCprogram = nMCprogram;
    nMCprogram = null;
    if (existingNMCprogram != null)
    {
      existingNMCprogram.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "nMCprogram = "+(getNMCprogram()!=null?Integer.toHexString(System.identityHashCode(getNMCprogram())):"null");
  }
}