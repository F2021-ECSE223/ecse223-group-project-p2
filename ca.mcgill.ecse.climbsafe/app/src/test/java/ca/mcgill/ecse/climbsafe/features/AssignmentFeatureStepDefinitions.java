package ca.mcgill.ecse.climbsafe.features;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Hotel;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignmentFeatureStepDefinitions {

  private ClimbSafe climbSafe;
  String error = "";

  /**
   * @author taken from previous step definitions
   */
  @Given("the following ClimbSafe system exists:")
  public void the_following_climb_safe_system_exists(io.cucumber.datatable.DataTable dataTable) {
    climbSafe = ClimbSafeApplication.getClimbSafe();
    error = "";
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      climbSafe.setStartDate(Date.valueOf(row.get("startDate")));
      climbSafe.setNrWeeks(Integer.parseInt(row.get("nrWeeks")));
      climbSafe.setPriceOfGuidePerWeek(Integer.parseInt(row.get("priceOfGuidePerWeek")));
    }
  }

  /**
   * @author taken from previous step definitions
   */
  @Given("the following pieces of equipment exist in the system:")
  public void the_following_pieces_of_equipment_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String name = row.get("name");
      int weight = Integer.parseInt(row.get("weight"));
      int pricePerWeek = Integer.parseInt(row.get("pricePerWeek"));
      climbSafe.addEquipment(name, weight, pricePerWeek);
    }
  }

  /**
   * @author taken from previous step definitions
   */
  @Given("the following equipment bundles exist in the system:")
  public void the_following_equipment_bundles_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      EquipmentBundle bundle =
          climbSafe.addBundle(row.get("name"), Integer.parseInt(row.get("discount")));
      String items = row.get("items");
      ArrayList<String> itemList = new ArrayList<String>();
      String quantities = row.get("quantity");
      ArrayList<String> quantityList = new ArrayList<String>();
      for (String item : items.split(",")) {
        itemList.add(item);
      }
      for (String quantity : quantities.split(",")) {
        quantityList.add(quantity);
      }
      for (int i = 0; i < itemList.size(); i++) {
        if (Equipment.hasWithName(itemList.get(i))) {
          BundleItem bundleItem = bundle.addBundleItem(Integer.parseInt(quantityList.get(i)),
              climbSafe, (Equipment) Equipment.getWithName(itemList.get(i)));
          bundle.addBundleItem(bundleItem);
        }
      }

      climbSafe.addBundle(bundle);
    }
  }

  /**
   * @author taken from previous step definitions
   */
  @Given("the following guides exist in the system:")
  public void the_following_guides_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> guideList = dataTable.asMaps();
    for (int i = 0; i < guideList.size(); i++) {
      String guideEmail = guideList.get(i).get("email");
      String guidePassword = guideList.get(i).get("password");
      String guideName = guideList.get(i).get("name");
      String guideEmergencyContact = guideList.get(i).get("emergencyContact");
      climbSafe.addGuide(guideEmail, guidePassword, guideName, guideEmergencyContact);
    }
  }

  /**
   * @author taken from previous step definitions
   */
  @Given("the following members exist in the system:")
  public void the_following_members_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> memberList = dataTable.asMaps();
    for (int i = 0; i < memberList.size(); i++) {
      climbSafe.addMember(memberList.get(i).get("email"), memberList.get(i).get("password"),
          memberList.get(i).get("name"), memberList.get(i).get("emergencyContact"),
          parseInt(memberList.get(i).get("nrWeeks")),
          parseBoolean(memberList.get(i).get("guideRequired")),
          parseBoolean(memberList.get(i).get("hotelRequired")));
    }
  }

  /**
   * @author Peini Cheng
   */
  @When("the administrator attempts to initiate the assignment process")
  public void the_administrator_attempts_to_initiate_the_assignment_process() {
    try {
      AssignmentController.InitiateAssignment();
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }
  }

  /**
   * @author Peini Cheng
   */
  @Then("the following assignments shall exist in the system:")
  public void the_following_assignments_shall_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> cucumberData = dataTable.asMaps();
    for (Map<String, String> assignmentData : cucumberData) {
      var member = (Member) User.getWithEmail(assignmentData.get("memberEmail"));
      var guide = (Guide) User.getWithEmail(assignmentData.get("guideEmail"));
      var hotel = Hotel.getWithName(assignmentData.get("hotelName"));
      int startWeek = Integer.valueOf(assignmentData.get("startWeek"));
      int endWeek = Integer.valueOf(assignmentData.get("endWeek"));
      var assignment = member.getAssignment();
      assertNotNull(assignment);
      assertEquals(guide, assignment.getGuide());
      assertEquals(hotel, assignment.getHotel());
      assertEquals(startWeek, assignment.getStartWeek());
      assertEquals(endWeek, assignment.getEndWeek());
    }
  }

  /**
   * @author Peini Cheng
   */
  @Then("the assignment for {string} shall be marked as {string}")
  public void the_assignment_for_shall_be_marked_as(String email, String status) {
    assertEquals(status, ((Member) Member.getWithEmail(email)).getAssignment().getStatus().name());
  }

  /**
   * @author Peini Cheng
   */
  @Then("the number of assignments in the system shall be {string}")
  public void the_number_of_assignments_in_the_system_shall_be(String string) {
    assertEquals(Integer.parseInt(string), climbSafe.getAssignments().size());
  }

  /**
   * @author Peini Cheng
   */
  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {
    assertTrue(error.startsWith(string));
  }

  /**
   * @author taken from previous step definitions
   */
  @Given("the following assignments exist in the system:")
  public void the_following_assignments_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> cucumberData = dataTable.asMaps();

    for (Map<String, String> assignmentData : cucumberData) {
      var assignmentMember = (Member) User.getWithEmail(assignmentData.get("memberEmail"));
      var assignmentGuide = (Guide) User.getWithEmail(assignmentData.get("guideEmail"));
      var assignmentHotel = Hotel.getWithName(assignmentData.get("hotelName"));
      int startWeek = Integer.valueOf(assignmentData.get("startWeek"));
      int endWeek = Integer.valueOf(assignmentData.get("endWeek"));

      Assignment newAssignment = climbSafe.addAssignment(startWeek, endWeek, assignmentMember);
      newAssignment.setGuide(assignmentGuide);
      newAssignment.setHotel(assignmentHotel);
    }
  }

  /**
   * @author Peini Cheng
   */
  @When("the administrator attempts to confirm payment for {string} using authorization code {string}")
  public void the_administrator_attempts_to_confirm_payment_for_using_authorization_code(
      String string, String string2) {
    try {
      AssignmentController.confirmPayment(string, string2);
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }
  }

  /**
   * @author Peini Cheng
   */
  @Then("the assignment for {string} shall record the authorization code {string}")
  public void the_assignment_for_shall_record_the_authorization_code(String string,
      String string2) {
    assertEquals(string2, ((Member) Member.getWithEmail(string)).getAssignment().getCode());
  }

  /**
   * @author Arturo Mory Ramirez
   */
  @Then("the member account with the email {string} does not exist")
  public void the_member_account_with_the_email_does_not_exist(String string) {

    var memberAccount = Member.getWithEmail(string);
    assertTrue(memberAccount == null || memberAccount instanceof Member);

  }

  /**
   * @author Arturo Mory Ramirez
   */
  @Then("there are {string} members in the system")
  public void there_are_members_in_the_system(String string) {

    assertEquals(Integer.parseInt(string), climbSafe.numberOfMembers());

  }

  /**
   * @author Arturo Mory Ramirez
   */
  @Then("the error {string} shall be raised")
  public void the_error_shall_be_raised(String string) {

    assertEquals(string, error);

  }

  /**
   * @author Arturo Mory Ramirez
   */
  @When("the administrator attempts to cancel the trip for {string}")
  public void the_administrator_attempts_to_cancel_the_trip_for(String string) {

    try {
      AssignmentController.cancelTrip(string);
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }

  }

  /**
   * @author Arturo Mory Ramirez
   */
  @Given("the member with {string} has paid for their trip")
  public void the_member_with_has_paid_for_their_trip(String string) {

    Member paidMember = (Member) Member.getWithEmail(string);
    paidMember.getAssignment().confirmPayment();

  }

  /**
   * @author Arturo Mory Ramirez
   */
  @Then("the member with email address {string} shall receive a refund of {string} percent")
  public void the_member_with_email_address_shall_receive_a_refund_of_percent(String string,
      String string2) {

    Member refundMember = (Member) Member.getWithEmail(string);

    assertEquals(Integer.parseInt(string2), refundMember.getRefund());

  }

  /**
   * @author Arturo Mory Ramirez
   */
  @Given("the member with {string} has started their trip")
  public void the_member_with_has_started_their_trip(String string) {

    Member startMember = (Member) Member.getWithEmail(string);
    startMember.getAssignment().setTestStatus("Started");

  }

  /**
   * @author Jian Long (Noah) Ye
   */
  @When("the administrator attempts to finish the trip for the member with email {string}")
  public void the_administrator_attempts_to_finish_the_trip_for_the_member_with_email(
      String email) {

    try {
      AssignmentController.finishTrip(email);
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }


  }

  /**
   * @author Jian Long (Noah) Ye
   */
  @Given("the member with {string} is banned")
  public void the_member_with_is_banned(String email) {

    Member bannedMem = (Member) Member.getWithEmail(email);
    bannedMem.banMember();

  }

  /**
   * @author Jian Long (Noah) Ye
   */
  @Then("the member with email {string} shall be {string}")
  public void the_member_with_email_shall_be(String email, String status) {
    Member theMem = (Member) Member.getWithEmail(email);
    assertEquals(theMem.getStatus().name(), status);
  }

  /**
   * @author Jian Long (Noah) Ye
   */
  @When("the administrator attempts to start the trips for week {string}")
  public void the_administrator_attempts_to_start_the_trips_for_week(String weekNum) {

    try {
      AssignmentController.StartTrip(Integer.parseInt(weekNum));
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }

  }

  /**
   * @author Jian Long (Noah) Ye
   */
  @Given("the member with {string} has cancelled their trip")
  public void the_member_with_has_cancelled_their_trip(String email) {
    Member cancelledMem = (Member) Member.getWithEmail(email);
    cancelledMem.getAssignment().setTestStatus("Cancelled");
  }

  /**
   * @author Jian Long (Noah) Ye
   */
  @Given("the member with {string} has finished their trip")
  public void the_member_with_has_finished_their_trip(String email) {

    Member finishMem = (Member) Member.getWithEmail(email);
    finishMem.getAssignment().setTestStatus("Finished");

  }
}
