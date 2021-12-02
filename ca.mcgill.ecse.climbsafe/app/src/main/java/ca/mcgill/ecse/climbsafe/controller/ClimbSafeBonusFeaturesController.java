package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;

public class ClimbSafeBonusFeaturesController {

    /**
     * @author Salim Benchekroun 
     */

    //forget about this method
    public static void deleteAllGuides() { //deletes all guides in the system 

        ClimbSafe inst = ClimbSafeApplication.getClimbSafe();
        for (Guide g : inst.getGuides()) {
            g.delete();
        }   
        ClimbSafePersistence.save();
    }

    //forget about this method
    public static void banAllMembers() { //bans all members in the system 

        ClimbSafe inst = ClimbSafeApplication.getClimbSafe();
        for (Member m : inst.getMembers()) {
            m.banMember();
        }   
        ClimbSafePersistence.save();
    }
    
    //returns distribution of member with and without guide
    @SuppressWarnings("unchecked")
    public static ObservableList<Data> getMemberStats() {
        ArrayList<Data> a = new ArrayList<Data>();
        ClimbSafe inst = ClimbSafeApplication.getClimbSafe();
        int wG = 0; //number of members with a guide
        int nG = 0;//number of members without a guide 
        for (Member m : inst.getMembers()) { //count the number of member in each category 
            if ( m.isGuideRequired() == true) {
                wG++;
            }
            if ( m.isGuideRequired() == false) {
                nG++;
            }
        }
        //put the results in a Data object and add them to the ObservableList 
        Data withGuide = new Data("with Guide", wG);
        Data noGuide = new Data("no Guide", nG);
        a.add(noGuide); 
        a.add(withGuide);
        
        //just for testing
        wG = 50;
        nG = 10;
        
        return (ObservableList<Data>) a;
    }
}




