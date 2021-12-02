package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
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
		int wG = 0; //number of members with a guide
		int nG = 0;//number of members without a guide 
		ClimbSafe inst = ClimbSafeApplication.getClimbSafe();

		for (Member m : inst.getMembers()) { //count the number of member in each category 
			if ( m.isGuideRequired() == true) {
				wG++;
			}
			if ( m.isGuideRequired() == false) {
				nG++;
			}
		}

		//put the results in a Data object and add them to the ObservableList
		ObservableList<PieChart.Data> b = FXCollections.observableArrayList();
		if ( inst.getMembers().size() == 0) { // if no members, return 0
			b.add(new PieChart.Data("no member in list", 0));
			return b;
		}
		//if there are members in the list, return the percentages 

		//calculate percentage
		int percent_wG = wG/(inst.getMembers().size()) * 100;
		int percent_nG = 100 - percent_wG;
		
		b.add(new PieChart.Data("with Guide " + percent_wG + "%", percent_wG));
		b.add(new PieChart.Data("no Guide " + percent_nG + "%", percent_nG));

		return b;
	}
}




