/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;
import java.io.Serializable;

// line 106 "../../../../../ClimbSafePersistence.ump"
// line 1 "../../../../../ClimbSafeStateMachine.ump"
// line 96 "../../../../../ClimbSafe.ump"
public class Assignment implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private int startWeek;
  private int endWeek;
  private String code;

  //Assignment State Machines
  public enum Status { Assigned, Paid, Cancelled, Started, Finished, Banned }
  private Status status;

  //Assignment Associations
  private Member member;
  private Guide guide;
  private Hotel hotel;
  private ClimbSafe climbSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment(int aStartWeek, int aEndWeek, Member aMember, ClimbSafe aClimbSafe)
  {
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    code = null;
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create assignment due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create assignment due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    setStatus(Status.Assigned);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartWeek(int aStartWeek)
  {
    boolean wasSet = false;
    startWeek = aStartWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWeek(int aEndWeek)
  {
    boolean wasSet = false;
    endWeek = aEndWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setCode(String aCode)
  {
    boolean wasSet = false;
    code = aCode;
    wasSet = true;
    return wasSet;
  }

  public int getStartWeek()
  {
    return startWeek;
  }

  public int getEndWeek()
  {
    return endWeek;
  }

  public String getCode()
  {
    return code;
  }

  public String getStatusFullName()
  {
    String answer = status.toString();
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public boolean confirmPayment()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Assigned:
        setStatus(Status.Paid);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 13 "../../../../../ClimbSafeStateMachine.ump"
        rejectPaymentDueToPaid();
        setStatus(Status.Paid);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 20 "../../../../../ClimbSafeStateMachine.ump"
        rejectPaymentDueToCancelled();
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        // line 26 "../../../../../ClimbSafeStateMachine.ump"
        rejectPaymentDueToPaid();
        setStatus(Status.Started);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 32 "../../../../../ClimbSafeStateMachine.ump"
        rejectPaymentDueToFinished();
        setStatus(Status.Finished);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 37 "../../../../../ClimbSafeStateMachine.ump"
        rejectPaymentDueToBanned();
        setStatus(Status.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancelTrip()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Assigned:
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 11 "../../../../../ClimbSafeStateMachine.ump"
        refund();
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        // line 25 "../../../../../ClimbSafeStateMachine.ump"
        refund();
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 31 "../../../../../ClimbSafeStateMachine.ump"
        rejectCancelDueToFinished();
        setStatus(Status.Finished);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 38 "../../../../../ClimbSafeStateMachine.ump"
        rejectCancelDueToBanned();
        setStatus(Status.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finishTrip()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Assigned:
        // line 6 "../../../../../ClimbSafeStateMachine.ump"
        rejectFinishDueToNotStarted();
        setStatus(Status.Assigned);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 14 "../../../../../ClimbSafeStateMachine.ump"
        rejectFinishDueToNotStarted();
        setStatus(Status.Paid);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 19 "../../../../../ClimbSafeStateMachine.ump"
        rejectFinishDueToCancelled();
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        setStatus(Status.Finished);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 39 "../../../../../ClimbSafeStateMachine.ump"
        rejectFinishDueToBanned();
        setStatus(Status.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startTrip()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Assigned:
        // line 7 "../../../../../ClimbSafeStateMachine.ump"
        getMember().banMember();
        setStatus(Status.Banned);
        wasEventProcessed = true;
        break;
      case Paid:
        setStatus(Status.Started);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 18 "../../../../../ClimbSafeStateMachine.ump"
        rejectStartDueToCancelled();
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 30 "../../../../../ClimbSafeStateMachine.ump"
        rejectStartDueToFinished();
        setStatus(Status.Finished);
        wasEventProcessed = true;
        break;
      case Banned:
        // line 36 "../../../../../ClimbSafeStateMachine.ump"
        rejectStartDueToBanned();
        setStatus(Status.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean memberBanned()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Assigned:
        setStatus(Status.Banned);
        wasEventProcessed = true;
        break;
      case Paid:
        setStatus(Status.Banned);
        wasEventProcessed = true;
        break;
      case Cancelled:
        setStatus(Status.Banned);
        wasEventProcessed = true;
        break;
      case Started:
        setStatus(Status.Banned);
        wasEventProcessed = true;
        break;
      case Finished:
        setStatus(Status.Banned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
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
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setMember(Member aNewMember)
  {
    boolean wasSet = false;
    if (aNewMember == null)
    {
      //Unable to setMember to null, as assignment must always be associated to a member
      return wasSet;
    }
    
    Assignment existingAssignment = aNewMember.getAssignment();
    if (existingAssignment != null && !equals(existingAssignment))
    {
      //Unable to setMember, the current member already has a assignment, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Member anOldMember = member;
    member = aNewMember;
    member.setAssignment(this);

    if (anOldMember != null)
    {
      anOldMember.setAssignment(null);
    }
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
  public boolean setClimbSafe(ClimbSafe aClimbSafe)
  {
    boolean wasSet = false;
    if (aClimbSafe == null)
    {
      return wasSet;
    }

    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = aClimbSafe;
    if (existingClimbSafe != null && !existingClimbSafe.equals(aClimbSafe))
    {
      existingClimbSafe.removeAssignment(this);
    }
    climbSafe.addAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Member existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.setAssignment(null);
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
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeAssignment(this);
    }
  }

  // line 43 "../../../../../ClimbSafeStateMachine.ump"
   private void rejectStartDueToBanned(){
    throw new RuntimeException("Cannot start the trip due to a ban");
  }

  // line 46 "../../../../../ClimbSafeStateMachine.ump"
   private void rejectPaymentDueToBanned(){
    throw new RuntimeException("Cannot pay for the trip due to a ban");
  }

  // line 49 "../../../../../ClimbSafeStateMachine.ump"
   private void rejectCancelDueToBanned(){
    throw new RuntimeException("Cannot cancel the trip due to a ban");
  }

  // line 52 "../../../../../ClimbSafeStateMachine.ump"
   private void rejectFinishDueToBanned(){
    throw new RuntimeException("Cannot finish the trip due to a ban");
  }

  // line 55 "../../../../../ClimbSafeStateMachine.ump"
   private void rejectPaymentDueToPaid(){
    throw new RuntimeException("Trip has already been paid for");
  }

  // line 58 "../../../../../ClimbSafeStateMachine.ump"
   private void rejectPaymentDueToFinished(){
    throw new RuntimeException("Cannot pay for a trip which has finished");
  }

  // line 61 "../../../../../ClimbSafeStateMachine.ump"
   private void rejectCancelDueToFinished(){
    throw new RuntimeException("Cannot cancel a trip which has finished");
  }

  // line 64 "../../../../../ClimbSafeStateMachine.ump"
   private void rejectStartDueToFinished(){
    throw new RuntimeException("Cannot start a trip which has finished");
  }

  // line 67 "../../../../../ClimbSafeStateMachine.ump"
   private void rejectStartDueToCancelled(){
    throw new RuntimeException("Cannot start a trip which has been cancelled");
  }

  // line 70 "../../../../../ClimbSafeStateMachine.ump"
   private void rejectPaymentDueToCancelled(){
    throw new RuntimeException("Cannot pay for a trip which has been cancelled");
  }

  // line 73 "../../../../../ClimbSafeStateMachine.ump"
   private void rejectFinishDueToCancelled(){
    throw new RuntimeException("Cannot finish a trip which has been cancelled");
  }

  // line 76 "../../../../../ClimbSafeStateMachine.ump"
   private void rejectFinishDueToNotStarted(){
    throw new RuntimeException("Cannot finish a trip which has not started");
  }

  // line 79 "../../../../../ClimbSafeStateMachine.ump"
   private void refund(){
    if(status.equals(Status.Paid)){
    getMember().setRefund(50); 
    }else{
    getMember().setRefund(10);
    }
  }

  // line 86 "../../../../../ClimbSafeStateMachine.ump"
   public void authorization(String s){
    if(s.isEmpty()){
      throw new RuntimeException("Invalid authorization code");}else{
      code=s;
      confirmPayment();
      }
  }

  // line 93 "../../../../../ClimbSafeStateMachine.ump"
   public void setTestStatus(String s){
    Status status[] = Status.values();
    for(Status state: status) {
         if (state.name().equals(s)){
         this.status = state;
         }
      }
  }


  public String toString()
  {
    return super.toString() + "["+
            "startWeek" + ":" + getStartWeek()+ "," +
            "endWeek" + ":" + getEndWeek()+ "," +
            "code" + ":" + getCode()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 109 "../../../../../ClimbSafePersistence.ump"
  private static final long serialVersionUID = 11L ;

  
}