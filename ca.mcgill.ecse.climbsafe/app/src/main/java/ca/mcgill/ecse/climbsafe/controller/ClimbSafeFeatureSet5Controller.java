
package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

public class ClimbSafeFeatureSet5Controller {
  private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
  
  /**
   * @author Runge (Karen) Fu
   * This method creates an equipment bundle
   * @param name  the name of the new equipment bundle
   * @param discount  the discount of the new equipment bundle
   * @param equipmentNames  list of equipment names that need to be add
   * @param equipmentQuantities  list of equipment quantities which need to be add
   * @throws InvalidInputException if an input exception occurred
   */
  public static void addEquipmentBundle(String name, int discount, List<String> equipmentNames,
      List<Integer> equipmentQuantities) throws InvalidInputException {
    //test for illegal inputs 
    String error=""; 
    if ( discount < 0 ) {
        error="Discount must be at least 0";
    }else
    if ( discount > 100) {
        error="Discount must be no more than 100";
    }else
    if ( name.isEmpty() ) {
        error="Equipment bundle name cannot be empty";
    }else if(equipmentNames.size()<2){
     error="Equipment bundle must contain at least two distinct types of equipment";
    }else if(equipmentQuantities.isEmpty()) {
     error="Each bundle item must have quantity greater than or equal to 1";
    }
    if (!error.isEmpty()) {
        throw new InvalidInputException(error.trim());
      }
    for(Integer q : equipmentQuantities) {
     if(q<1) {
   error="Each bundle item must have quantity greater than or equal to 1";  
     throw new InvalidInputException(error.trim());
     }
    }
    
      int typeCount = 0;
      String firstType=equipmentNames.get(0);
      for(String equipmentName : equipmentNames) {
       if(!equipmentName.equals(firstType)) {
        typeCount+=1;
       }
      }
      
      if (typeCount==0) {
       error="Equipment bundle must contain at least two distinct types of equipment";
       throw new InvalidInputException(error.trim());
      }
      
    for(int i=0;i<equipmentNames.size();i++) {
    String equipmentName = equipmentNames.get(i);
            if (!Equipment.hasWithName(equipmentName)) {
      error="Equipment " + equipmentName + " does not exist";
        throw new InvalidInputException(error.trim());
             }
   }
   
   try {
     EquipmentBundle bundle =climbSafe.addBundle(name, discount); 
     for(int i=0;i<equipmentNames.size();i++) {
     String equipmentName = equipmentNames.get(i);
     BundleItem bundleItem=climbSafe.addBundleItem(equipmentQuantities.get(i),bundle,(Equipment)Equipment.getWithName(equipmentName));
     bundle.addBundleItem(bundleItem);
      climbSafe.addBundle(bundle);
     }
     ClimbSafePersistence.save();
     } 
   catch (RuntimeException e) {
     error=e.getMessage();
     if (error.startsWith("Cannot create due to duplicate name."))
     {
       error = "A bookable item called "+name+" already exists";
     }
     throw new InvalidInputException(error);
   }
}
  
/**
 * @author Runge (Karen) Fu
 * This method updates the equipment bundle
 * @param oldName  name of the equipment bundle which will be updated
 * @param newName  new name for the equipment bundle which will be updated 
 * @param newDiscount  new discount for the equipment bundle
 * @param newEquipmentNames  list of equipment names that need to be updated
 * @param newEquipmentQuantities  list of equipment quantities that need to be updated
 * @throws InvalidInputException if an input exception occurred
 */
  public static void updateEquipmentBundle(String oldName, String newName, int newDiscount, 
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities) throws InvalidInputException {
    
    EquipmentBundle foundBundle = Utility.findBundle(oldName);
    
    //test for illegal inputs 
    var error = "";
  
    if ( newName.isEmpty() ) {
      error="Equipment bundle name cannot be empty";
    }
    if (newDiscount < 0) {
      error = "Discount must be at least 0";
    }
    if(newDiscount > 100) {    
      error = "Discount must be no more than 100";
    }
   
    int typeCount = 0;
    String firstType=newEquipmentNames.get(0);
    for(String equipmentName : newEquipmentNames) {
     if(!equipmentName.equals(firstType)) {
      typeCount+=1;
     }
    }
    if (typeCount==0) {
      error="Equipment bundle must contain at least two distinct types of equipment";
      throw new InvalidInputException(error.trim());
     }
    for(int i=0;i<newEquipmentNames.size();i++) {
      String newEquipmentName = newEquipmentNames.get(i);
    if (!Equipment.hasWithName(newEquipmentName)) 
      {
       error="Equipment " + newEquipmentName + " does not exist";
       throw new InvalidInputException(error.trim());
       }
    }
    for( int i =0; i<newEquipmentQuantities.size();i++) {
      int j = (Integer)newEquipmentQuantities.get(i);
      if (j<1)
        error = "Each bundle item must have quantity greater than or equal to 1";
    }
    
    if(foundBundle == null) {
      error = "Equipment bundle " +oldName+" does not exist";
    }
    
    if (!error.isEmpty()) {
      throw new InvalidInputException(error.trim());
    }

    if((!newName.equals(""))&& (!newName.equals(oldName)) && 
        BookableItem.hasWithName(newName)&& BookableItem.getWithName(newName).getClass()==EquipmentBundle.class) {
      error = "A bookable item called large bundle already exists";
      throw new InvalidInputException(error.trim());
    }

    try {
        foundBundle = (EquipmentBundle) BookableItem.getWithName(oldName);
        for(int i=0;i<newEquipmentNames.size();i++) {
          Equipment theEquip = (Equipment) BookableItem.getWithName(newEquipmentNames.get(i));
          for(BundleItem element:foundBundle.getBundleItems()) {
            if(element.getEquipment().getName().equals(theEquip.getName())) {
              error = "A bookable item called " + theEquip.getName()+" already exists";
                throw new InvalidInputException(error.trim());
            }
          }
          foundBundle.setName(newName);
          foundBundle.setClimbSafe(climbSafe);
          foundBundle.setDiscount(newDiscount);
          ClimbSafe climbSafe = theEquip.getClimbSafe();
          Integer equiQuan = newEquipmentQuantities.get(i);
          foundBundle.addBundleItem(equiQuan, climbSafe, theEquip);
          }
        ClimbSafePersistence.save();
        }
    catch (RuntimeException e) {
      error = e.getMessage();
      throw new InvalidInputException(error);
      }   
  }
}

