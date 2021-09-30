/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;

// line 100 "model.ump"
// line 160 "model.ump"
public class Assignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private Date date;

  //Assignment Associations
  private NMCprogram nMCprogram;
  private Equipment equipment;
  private EquipmentBundle equipmentBundle;
  private Guide guide;
  private Hotel hotel;
  private Member member;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment(Date aDate, NMCprogram aNMCprogram, Equipment aEquipment, EquipmentBundle aEquipmentBundle, Member aMember)
  {
    date = aDate;
    boolean didAddNMCprogram = setNMCprogram(aNMCprogram);
    if (!didAddNMCprogram)
    {
      throw new RuntimeException("Unable to create assignment due to nMCprogram. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddEquipment = setEquipment(aEquipment);
    if (!didAddEquipment)
    {
      throw new RuntimeException("Unable to create assignment due to equipment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddEquipmentBundle = setEquipmentBundle(aEquipmentBundle);
    if (!didAddEquipmentBundle)
    {
      throw new RuntimeException("Unable to create assignment due to equipmentBundle. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create assignment due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }
  /* Code from template association_GetOne */
  public NMCprogram getNMCprogram()
  {
    return nMCprogram;
  }
  /* Code from template association_GetOne */
  public Equipment getEquipment()
  {
    return equipment;
  }
  /* Code from template association_GetOne */
  public EquipmentBundle getEquipmentBundle()
  {
    return equipmentBundle;
  }
  /* Code from template association_GetOne */
  public Guide getGuide()
  {
    return guide;
  }

  public boolean hasGuide()
  {
    boolean has = guide != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Hotel getHotel()
  {
    return hotel;
  }

  public boolean hasHotel()
  {
    boolean has = hotel != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_SetOneToMany */
  public boolean setNMCprogram(NMCprogram aNMCprogram)
  {
    boolean wasSet = false;
    if (aNMCprogram == null)
    {
      return wasSet;
    }

    NMCprogram existingNMCprogram = nMCprogram;
    nMCprogram = aNMCprogram;
    if (existingNMCprogram != null && !existingNMCprogram.equals(aNMCprogram))
    {
      existingNMCprogram.removeAssignment(this);
    }
    nMCprogram.addAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setEquipment(Equipment aEquipment)
  {
    boolean wasSet = false;
    if (aEquipment == null)
    {
      return wasSet;
    }

    Equipment existingEquipment = equipment;
    equipment = aEquipment;
    if (existingEquipment != null && !existingEquipment.equals(aEquipment))
    {
      existingEquipment.removeAssignment(this);
    }
    equipment.addAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasSet = false;
    if (aEquipmentBundle == null)
    {
      return wasSet;
    }

    EquipmentBundle existingEquipmentBundle = equipmentBundle;
    equipmentBundle = aEquipmentBundle;
    if (existingEquipmentBundle != null && !existingEquipmentBundle.equals(aEquipmentBundle))
    {
      existingEquipmentBundle.removeAssignment(this);
    }
    equipmentBundle.addAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setGuide(Guide aGuide)
  {
    boolean wasSet = false;
    Guide existingGuide = guide;
    guide = aGuide;
    if (existingGuide != null && !existingGuide.equals(aGuide))
    {
      existingGuide.removeAssignment(this);
    }
    if (aGuide != null)
    {
      aGuide.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setHotel(Hotel aHotel)
  {
    boolean wasSet = false;
    Hotel existingHotel = hotel;
    hotel = aHotel;
    if (existingHotel != null && !existingHotel.equals(aHotel))
    {
      existingHotel.removeAssignment(this);
    }
    if (aHotel != null)
    {
      aHotel.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMember(Member aMember)
  {
    boolean wasSet = false;
    if (aMember == null)
    {
      return wasSet;
    }

    Member existingMember = member;
    member = aMember;
    if (existingMember != null && !existingMember.equals(aMember))
    {
      existingMember.removeAssignment(this);
    }
    member.addAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    NMCprogram placeholderNMCprogram = nMCprogram;
    this.nMCprogram = null;
    if(placeholderNMCprogram != null)
    {
      placeholderNMCprogram.removeAssignment(this);
    }
    Equipment placeholderEquipment = equipment;
    this.equipment = null;
    if(placeholderEquipment != null)
    {
      placeholderEquipment.removeAssignment(this);
    }
    EquipmentBundle placeholderEquipmentBundle = equipmentBundle;
    this.equipmentBundle = null;
    if(placeholderEquipmentBundle != null)
    {
      placeholderEquipmentBundle.removeAssignment(this);
    }
    if (guide != null)
    {
      Guide placeholderGuide = guide;
      this.guide = null;
      placeholderGuide.removeAssignment(this);
    }
    if (hotel != null)
    {
      Hotel placeholderHotel = hotel;
      this.hotel = null;
      placeholderHotel.removeAssignment(this);
    }
    Member placeholderMember = member;
    this.member = null;
    if(placeholderMember != null)
    {
      placeholderMember.removeAssignment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "nMCprogram = "+(getNMCprogram()!=null?Integer.toHexString(System.identityHashCode(getNMCprogram())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "equipment = "+(getEquipment()!=null?Integer.toHexString(System.identityHashCode(getEquipment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "equipmentBundle = "+(getEquipmentBundle()!=null?Integer.toHexString(System.identityHashCode(getEquipmentBundle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null");
  }
}