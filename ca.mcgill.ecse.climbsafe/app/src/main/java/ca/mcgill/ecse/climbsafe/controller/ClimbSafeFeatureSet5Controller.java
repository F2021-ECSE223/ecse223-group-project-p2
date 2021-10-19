package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;
import java.util.ArrayList;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;

public class ClimbSafeFeatureSet5Controller {
	private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
  public static void addEquipmentBundle(String name, int discount, List<String> equipmentNames,
      List<Integer> equipmentQuantities) throws InvalidInputException {
	      var error = "";
	      if (discount<0) {
	          error = "Discount must be at least 0  ";
	      }
	      if(discount>100) {
	          error = "Discount must be no more than 100  ";
	      }
	      if(name.isEmpty()) {
	          error = "Equipment bundle name cannot be empty ";
	      }
	      if (!error.isEmpty()) {
	          throw new InvalidInputException(error.trim());
	        }
	            try {
	                EquipmentBundle bundle = climbSafe.addBundle(name, discount);
	                for (int i=0;i<equipmentNames.size();i++) {
	                    List<Equipment>equipments = climbSafe.getEquipment();
	                    for(Equipment e : equipments) {
	                        if (e.getName() == equipmentNames.get(i)) {
	                            BundleItem bundleItem = bundle.addBundleItem(equipmentQuantities.get(i), climbSafe, e);
	                            bundle.addBundleItem(bundleItem);
	                        }
	                    }
	                }
	                climbSafe.addBundle(bundle);
	            } catch (RuntimeException e) {
	              error = e.getMessage();
	              throw new InvalidInputException(error);
	            }
  }

  public static void updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities)
      throws InvalidInputException {}

}
