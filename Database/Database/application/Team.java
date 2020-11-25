package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Team {
    private final int orb;
    private final int year;
    private final SimpleStringProperty TeamName;
    private final int pts;
    
 
    Team(String TName, int Year,int Obr, int Pts) {
    	this.TeamName = new SimpleStringProperty(TName);
        this.orb = Obr;
        this.year = Year;
        this.pts = Pts;
        
        
    }
 
  

	public String getTeamName() {
		return TeamName.get();
	}



	public int getYear() {
		return year;
	}



	public int getPts() {
		return pts;
	}



	public int getOrb() {
		return orb;
	}

}