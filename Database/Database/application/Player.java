package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Player {
    private final SimpleStringProperty PlayerName;
    private final int year;
    private final int orb;
    private final int point;
    
 
    Player(String fName, int Year, int Age,int pts) {
        this.PlayerName = new SimpleStringProperty(fName);
        this.year = Year;
        this.orb = Age;
        this.point = pts;
    }
 
    public String getPlayerName() {
        return PlayerName.get();
    }
    public void setFirstName(String fName) {
        PlayerName.set(fName);
    }
        
 

	public int getPoint() {
		return point;
	}

	public int getYear() {
		return year;
	}

	public int getOrb() {
		return orb;
	}


}