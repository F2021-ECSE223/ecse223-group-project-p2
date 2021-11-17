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
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

public class ClimbSafeFeatureSet6Controller {
  private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
  
  /**
   * delete a certain equipment
   * corresponds to DeleteEquipment.feature
   * @author Peini Cheng
   * @param  name name of the equipment
   */
  public static void deleteEquipment(String name) throws InvalidInputException {
    Equipment e = Utility.findEquipment(name);
    if (e != null) {
      if (e.hasBundleItems()) {
        throw new InvalidInputException(
            "The piece of equipment is in a bundle and cannot be deleted");
      } else {
        e.delete();
        ClimbSafePersistence.save();
      }
    }
  }

  /**
   * delete a certain equipment bundle
   * corresponds to DeleteEquipmentBundle.feature
   * @author Peini Cheng
   * @param  name name of the equipment bundle
   */
  public static void deleteEquipmentBundle(String name) {
    EquipmentBundle b = Utility.findBundle(name);
    if (b != null) {
      b.delete();
      ClimbSafePersistence.save();
    }
  }

  /**
   * create a list of TOAssignemnt for Administrator to keep track of climbers' assignments
   * corresponds to ViewAssignment.feature
   * @author Peini Cheng
   * @return a list of TOAssignment
   */
  public static List<TOAssignment> getAssignments() {
    var assignments = new ArrayList<TOAssignment>();
    for (var assignment : climbSafe.getAssignments()) {
      int week = assignment.getEndWeek() - assignment.getStartWeek() + 1;
      int totalCostForEquipmentPerWeek = 0;
      int costForBundlePerWeek = 0;
      String guideEmail = null;
      String guideName = null;
      String hotelName = null;
      int guideCost = 0;
      if (assignment.getMember().isGuideRequired()) {
        guideEmail = assignment.getGuide().getEmail();
        guideName = assignment.getGuide().getName();
        guideCost = climbSafe.getPriceOfGuidePerWeek();
      }
      if (assignment.getMember().isHotelRequired()) {
        hotelName = assignment.getHotel().getName();
      }
      List<BookedItem> bookedItems = assignment.getMember().getBookedItems();
      for (BookedItem booked : bookedItems) {
        BookableItem i = booked.getItem();
        if (i instanceof Equipment) {
          totalCostForEquipmentPerWeek += ((Equipment) i).getPricePerWeek() * booked.getQuantity();
        } else {
          for (BundleItem b : ((EquipmentBundle) i).getBundleItems()) {
            costForBundlePerWeek += ((b.getEquipment().getPricePerWeek()) * (b.getQuantity()));
          }
          if (assignment.getMember().isGuideRequired()) {
            totalCostForEquipmentPerWeek += costForBundlePerWeek
                * (100 - ((EquipmentBundle) i).getDiscount()) / 100 * booked.getQuantity();
          } else {
            totalCostForEquipmentPerWeek += costForBundlePerWeek * booked.getQuantity();
          }
        }
      }
      assignments
          .add(new TOAssignment(assignment.getMember().getEmail(), assignment.getMember().getName(),
              guideEmail, guideName, hotelName, assignment.getStartWeek(), assignment.getEndWeek(),
              guideCost * week, totalCostForEquipmentPerWeek * week));
    }
    return assignments;
  }
}
