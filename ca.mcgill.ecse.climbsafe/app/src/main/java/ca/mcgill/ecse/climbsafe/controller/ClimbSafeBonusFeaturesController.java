package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class ClimbSafeBonusFeaturesController {

	/**
	 * @author Salim Benchekroun 
	 */
	//forget about this method
    //public static List<double> getNumericStats() {
     // List<Integer> numberList = new ArrayList<Integer>();
     // numberList.add(numberList);
    //}
	//returns distribution of member with and without guide
	@SuppressWarnings("unchecked")
	public static ObservableList<Data> getMemberStats() {
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
		ObservableList<PieChart.Data> b = FXCollections.observableArrayList();
		if ( inst.getMembers().size() == 0) { // if no members, return 0
			b.add(new PieChart.Data("no member in list", 0));
			return b;
		}
		//if there are members in the list, return the percentages 

		//calculate percentage
		double percent_wG = ((double)((double)wG/(double)(inst.getMembers().size()))) * 100;
		double percent_nG = 100 - percent_wG;

		b.add(new PieChart.Data("with Guide " + (int)percent_wG + "%", (int)percent_wG));
		b.add(new PieChart.Data("no Guide " + (int)percent_nG + "%", (int)percent_nG));

		return b;
	}


	public static Series DrawGraphLine() {

		XYChart.Series<Number, Number> c = new XYChart.Series();
		c.setName("Members per Week");	
		return c;

	}
}




