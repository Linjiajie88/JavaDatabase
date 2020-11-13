package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Team {
    private final SimpleStringProperty PlayerName;
    private final int year;
    private final SimpleStringProperty TeamName;
    private final SimpleStringProperty Salary;
    
 
    Team(String TName,String PName, int Year, String salary) {
    	this.TeamName = new SimpleStringProperty(TName);
        this.PlayerName = new SimpleStringProperty(PName);
        this.year = Year;
        this.Salary = new SimpleStringProperty(salary);
        
        
    }
 
    public String getPlayerName() {
        return PlayerName.get();
    }
    public void setFirstName(String fName) {
        PlayerName.set(fName);
    }

	public String getTeamName() {
		return TeamName.get();
	}

	public String getSalary() {
		return Salary.get();
	}

	public int getYear() {
		return year;
	}

}