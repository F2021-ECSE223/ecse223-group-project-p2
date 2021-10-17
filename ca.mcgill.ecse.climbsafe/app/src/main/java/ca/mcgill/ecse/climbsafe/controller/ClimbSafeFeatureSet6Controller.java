package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;
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
public class ClimbSafeFeatureSet6Controller {
	private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

  public static void deleteEquipment(String name) throws InvalidInputException {
  List<Equipment> equipments = climbSafe.getEquipment();
  for (Equipment e : equipments) {
	  if (e.getName() == name){
		  e.delete();
		  break;
	  }
  }
  }
  // this method does not need to be implemented by a team with five team members
  public static void deleteEquipmentBundle(String name) {
  List<EquipmentBundle> bundles = climbSafe.getBundles();
  for (EquipmentBundle b : bundles) {
	  if (b.getName() == name){
		  b.delete();
		  break;
	  }
  }
  }
  public static List<TOAssignment> getAssignments() {
  var assignments = new ArrayList<TOAssignment>();
  for (var assignment : climbSafe.getAssignments()) {
	 int week = assignment.getEndWeek() - assignment.getStartWeek();
	 int totalCostForEquipmentPerWeek=0;
	  List<BookedItem> bookedItems = assignment.getMember().getBookedItems();
	  for(BookedItem booked : bookedItems) {
		  BookableItem i = booked.getItem();
		  if (i instanceof Equipment) {
			  totalCostForEquipmentPerWeek += ((Equipment)i).getPricePerWeek();
		  }else {
			  for(BundleItem b : ((EquipmentBundle)i).getBundleItems()) {
			  totalCostForEquipmentPerWeek += (b.getEquipment().getPricePerWeek()) * (b.getQuantity());
			  }
		  }
	  }
    assignments.add(new TOAssignment(assignment.getMember().getEmail(), assignment.getMember().getName(),
    		assignment.getGuide().getEmail(), assignment.getGuide().getName(),assignment.getHotel().getName(), assignment.getStartWeek(),
    		assignment.getEndWeek(), (assignment.getClimbSafe().getPriceOfGuidePerWeek()) * week, totalCostForEquipmentPerWeek * week ));
  }
  return assignments;
}
}

