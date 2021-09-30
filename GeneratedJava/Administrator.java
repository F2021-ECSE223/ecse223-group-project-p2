/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 38 "model.ump"
// line 125 "model.ump"
public class Administrator extends Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Administrator Associations
  private NMCprogram nMCprogram;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Administrator(String aPassword, String aEmail, ClimbSafe aClimbSafe, NMCprogram aNMCprogram)
  {
    super(aPassword, aEmail, aClimbSafe);
    if (aNMCprogram == null || aNMCprogram.getAdministrator() != null)
    {
      throw new RuntimeException("Unable to create Administrator due to aNMCprogram. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    nMCprogram = aNMCprogram;
  }

  public Administrator(String aPassword, String aEmail, ClimbSafe aClimbSafe, ClimbSafe aClimbSafeForNMCprogram)
  {
    super(aPassword, aEmail, aClimbSafe);
    nMCprogram = new NMCprogram(aClimbSafeForNMCprogram, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public NMCprogram getNMCprogram()
  {
    return nMCprogram;
  }

  public void delete()
  {
    NMCprogram existingNMCprogram = nMCprogram;
    nMCprogram = null;
    if (existingNMCprogram != null)
    {
      existingNMCprogram.delete();
    }
    super.delete();
  }

}