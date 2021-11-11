package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

/**@author Arturo Mory Ramirez
 * 
 * This method adds equipment items with their corresponding information in the climbSafe system
 * @param name is the name of the equipment item we would like to add in the climSafe system
 * @param weight is the weight of the equipment item we add in the climbSafe system
 * @param pricePerWeek is the price per week of the equipment item we add in the climbSafe system
 * @throws InvalidInputException for inputs that are wrong/cause errors
 *
 */

public class ClimbSafeFeatureSet4Controller {
    static ClimbSafe instanceapp = ClimbSafeApplication.getClimbSafe();

  public static void addEquipment(String name, int weight, int pricePerWeek)
      throws InvalidInputException {
      if(weight<=0) {
          throw new InvalidInputException("The weight must be greater than 0");
      }
      if(pricePerWeek<0) {
          throw new InvalidInputException("The price per week must be greater than or equal to 0");
      }
      if(name == "") {
          throw new InvalidInputException("The name must not be empty");
      }
      
      List<Equipment> equipments = instanceapp.getEquipment();
      for(Equipment e : equipments) {
          if(name.equals(e.getName())) {
              throw new InvalidInputException("The piece of equipment already exists");
          }
      }
      
      List<EquipmentBundle> equipmentBundles = instanceapp.getBundles();
      for(EquipmentBundle eb : equipmentBundles) {
          if(name.equals(eb.getName())) {
              throw new InvalidInputException("The equipment bundle already exists");
          }
      }
      
      try {
          instanceapp.addEquipment(name, weight, pricePerWeek);
          ClimbSafePersistence.save();
      } catch(RuntimeException e) {
          throw new InvalidInputException(e.getMessage());
      }
        
  }
  
  /**@author Arturo Mory Ramirez
   * 
   * This method updates the equipment items' information in the climbSafe system
   * @param oldName is the old name of the equipment item we wish to update in the climbSafe system
   * @param newName is the new name of the equipment item that is updated in the climbSafe system 
   * @param newWeight is the new weight of the equipment item that is updated in the climbSafe system
   * @param newPricePerWeek is the new price per week of the equipment item that is updated in the climbSafe system
   * @throws InvalidInputException for inputs that are wrong/cause errors
   */

  public static void updateEquipment(String oldName, String newName, int newWeight,
      int newPricePerWeek) throws InvalidInputException {
      
      if(oldName == "") {
          throw new InvalidInputException("The name must not be empty");
      }
      if(newName == "") {
          throw new InvalidInputException("The name must not be empty");
      }
      if(newWeight<=0) {
          throw new InvalidInputException("The weight must be greater than 0");
      }
      if(newPricePerWeek<0) {
          throw new InvalidInputException("The price per week must be greater than or equal to 0");
      }
      List<EquipmentBundle> equipmentBundles = instanceapp.getBundles();
      for(EquipmentBundle eb : equipmentBundles) {
          if(newName.equals(eb.getName())) {
              throw new InvalidInputException("An equipment bundle with the same name already exists");
          }
      }
      
      List<Equipment> equipments1 = instanceapp.getEquipment();
      for(Equipment e : equipments1) {
          if(e.getName().equals(newName) && !oldName.equals(newName)) {
              throw new InvalidInputException("The piece of equipment already exists");
          }
      }
      
     List<Equipment> equipments2 = instanceapp.getEquipment();
     boolean b = false;
      for(Equipment e : equipments2) {
        if (e.getName().equals(oldName)) {
          b=true;
        }
        }
          if(b==false){
          throw new InvalidInputException("The piece of equipment does not exist");
          }
      
    try {
          List<Equipment> equipmentItems = instanceapp.getEquipment();
          for(Equipment e: equipmentItems) {
              if(oldName.equals(e.getName())) {
                  e.setName(newName);
                  e.setWeight(newWeight);
                  e.setPricePerWeek(newPricePerWeek);
              }
          }
          ClimbSafePersistence.save(); 
      }catch(RuntimeException e){
         throw new InvalidInputException(e.getMessage());
          
    }

 }

}
