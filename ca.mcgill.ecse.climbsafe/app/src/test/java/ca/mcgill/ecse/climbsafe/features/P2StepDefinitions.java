package ca.mcgill.ecse.climbsafe.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

<<<<<<< HEAD
=======


//test for commmit github

>>>>>>> parent of 60a067c (Update P2StepDefinitions.java)
public class P2StepDefinitions {
  @Given("the following ClimbSafe system exists: \\(p2)")
  public void the_following_climb_safe_system_exists_p2(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }
  
  //@author Danny
  @Given("the following equipment exists in the system: \\(p2)")
  public void the_following_equipment_exists_in_the_system_p2(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
	  
	  
	  List<Map<String, String>> rows = dataTable.asMaps(); //not sure to understand here
		 
		 for (var row : rows) {
			 
			 String name = row.get("name");
			 int weight = Integer.parseInt(row.get("weight"));
			 int pricePerWeek = Interger.parseInt(row.get("pricePerWeek"));
			 
			 climbsafe.addEquipement(row.get("weight")), climbsafe.addEquipement(row.get("pricePerWeek"))
			 
			 	
			
		 }
		 
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @When("the administrator attempts to add an equipment bundle with name {string}, discount {string}, items {string}, and quantities {string} \\(p2)")
  public void the_administrator_attempts_to_add_an_equipment_bundle_with_name_discount_items_and_quantities_p2(
      String string, String string2, String string3, String string4) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of equipment bundles in the system shall be {string} \\(p2)")
  public void the_number_of_equipment_bundles_in_the_system_shall_be_p2(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the equipment bundle {string} shall exist in the system \\(p2)")
  public void the_equipment_bundle_shall_exist_in_the_system_p2(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the equipment bundle {string} shall contain the items {string} with quantities {string} \\(p2)")
  public void the_equipment_bundle_shall_contain_the_items_with_quantities_p2(String string,
      String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the equipment bundle {string} shall have a discount of {string} \\(p2)")
  public void the_equipment_bundle_shall_have_a_discount_of_p2(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the error {string} shall be raised \\(p2)")
  public void the_error_shall_be_raised_p2(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following equipment bundles exist in the system: \\(p2)")
  public void the_following_equipment_bundles_exist_in_the_system_p2(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

}
