package ca.mcgill.ecse.climbsafe.features;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

import ca.mcgill.ecse.climbsafe.model.Administrator;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Hotel;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.NamedUser;
import ca.mcgill.ecse.climbsafe.model.User;
public class P2StepDefinitions {
	
	private ClimbSafe climbSafe;
	String error;
	
	
  @Given("the following ClimbSafe system exists: \\(p2)")
  public void the_following_climb_safe_system_exists_p2(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
	  climbSafe=ClimbSafeApplication.getClimbSafe();
	  List<Map<String, String>> rows = dataTable.asMaps();
	  for (var row : rows) {
	  climbSafe.setStartDate(Date.valueOf(row.get("startDate")));
	  climbSafe.setNrWeeks(Integer.parseInt(row.get("nrWeeks")));
	  climbSafe.setPriceOfGuidePerWeek(Integer.parseInt(row.get("priceOfGuidePerWeek")));
	  }
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    //throw new io.cucumber.java.PendingException();
  }

  @Given("the following equipment exists in the system: \\(p2)")
  public void the_following_equipment_exists_in_the_system_p2(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
	  List<Map<String, String>> rows = dataTable.asMaps(); //not sure to understand here
		 
		 for (var row : rows) {
			 
			 String name = row.get("name");
			 int weight = Integer.parseInt(row.get("weight"));
			 int pricePerWeek = Integer.parseInt(row.get("pricePerWeek"));
			 
			 climbSafe.addEquipment(name,weight,pricePerWeek);
			 
			 	
			
		 }
		 
    // For other transformations you can register a DataTableType.
    //throw new io.cucumber.java.PendingException();
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
  }
  
//salim
  @When("the administrator attempts to add an equipment bundle with name {string}, discount {string}, items {string}, and quantities {string} \\(p2)")
  public void the_administrator_attempts_to_add_an_equipment_bundle_with_name_discount_items_and_quantities_p2(
      String string, String string2, String string3, String string4) {
    // Write code here that turns the phrase above into concrete actions
	    // Write code here that turns the phrase above into concrete actions
	      //convert the strings to actual useful stuff 
	  try{ String name = string;
	      int discount = Integer.parseInt(string2);
	      if (discount<0) {
			  error = "Discount must be at least 0 ";
		  }
		  if(discount>100) {
			  error = "Discount must be no more than 100 ";
		  }
		  if(name.isEmpty()) {
			  error = "Equipment bundle name cannot be empty ";
		  }
	      ArrayList<String> itemList = new ArrayList<String>();
		  ArrayList<Integer> quantityList = new ArrayList<Integer>();
	      for(String item: string3.split(",")){
			   itemList.add(item);
			}
		  for(String quantity: string4.split(",")){
			   quantityList.add(Integer.parseInt(quantity));
			}
	      ClimbSafeFeatureSet5Controller.addEquipmentBundle(name, discount,itemList,quantityList );
	      }catch(InvalidInputException e) {
	    	  error=e.getMessage();
	      }
	      
    //throw new io.cucumber.java.PendingException();
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

  //@author Peini Cheng
  @Then("the error {string} shall be raised \\(p2)")
  public void the_error_shall_be_raised_p2(String string) {
    // Write code here that turns the phrase above into concrete actions
	  assertEquals(string,error);
   // throw new io.cucumber.java.PendingException();
  }

  //@author Peini Cheng
  @Given("the following equipment bundles exist in the system: \\(p2)")
  public void the_following_equipment_bundles_exist_in_the_system_p2(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
	  List<Map<String, String>> rows = dataTable.asMaps();
	  for (var row : rows) {
	  EquipmentBundle bundle = climbSafe.addBundle(row.get("name"), Integer.parseInt(row.get("discount")));
	  String items = row.get("items");
	  ArrayList<String> itemList = new ArrayList<String>();
	  String quantities = row.get("quantities");
	  ArrayList<String> quantityList = new ArrayList<String>();
	  for(String item: items.split(",")){
		   itemList.add(item);
		}
	  for(String quantity: quantities.split(",")){
		   quantityList.add(quantity);
		}
	  for (int i=0;i<itemList.size();i++) {
		  List<Equipment>equipments = climbSafe.getEquipment();
		  for(Equipment e : equipments) {
			  if (e.getName() == itemList.get(i)) {
				  BundleItem bundleItem = bundle.addBundleItem(Integer.parseInt(quantityList.get(i)), climbSafe, e);  
				  bundle.addBundleItem(bundleItem);
			  }
		  }
	  }
	  
	  climbSafe.addBundle(bundle);
	  }
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    //throw new io.cucumber.java.PendingException();
  }
  
  @After
  public void tearDown() {
    climbSafe.delete();
  }

}
