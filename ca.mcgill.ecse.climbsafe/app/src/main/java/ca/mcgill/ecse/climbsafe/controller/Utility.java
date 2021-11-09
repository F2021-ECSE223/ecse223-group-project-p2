package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

public class Utility {
  private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
  public static Equipment findEquipment(String name) {
    Equipment foundEquipment = null;
    for (var e : climbSafe.getEquipment()) {
      if (e.getName().equals(name)) {
        foundEquipment = e;
        break;
      }
    }
    return foundEquipment;
  }

  public static EquipmentBundle findBundle(String name) {
    EquipmentBundle foundBundle = null;
    for (var b : climbSafe.getBundles()) {
      if (b.getName().equals(name)) {
        foundBundle = b;
        break;
      }
    }
    return foundBundle;
  }
}
