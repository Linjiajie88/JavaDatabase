package application;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Table {
	private Scene scene;
	private HashMap<String, Scene> sceneMap;
	private Stage primaryStage;
	private TableView<Player> table;
	
	public Table(HashMap<String, Scene> sceneMap, Stage primaryStage) {
		this.sceneMap = sceneMap;
		this.primaryStage = primaryStage;
	}
	
	public Scene createScene() {
		//TableView table = new TableView();
		
        
     
        
		Connection conn = null;
	    Statement stmt = null;
	    CallableStatement cStmt;
	    ResultSet rs = null;
	    int numRowsModified = 0;
	    String password;
	    String connectionString = "jdbc:mysql://localhost:3306?" +
	                                       "user=root&password=";
	    
	    ObservableList<Player> data =
		        FXCollections.observableArrayList();

	    try {
			//System.out.print("Enter password: ");
			//Scanner input = new Scanner(System.in);
			password = "Qq532797625";
			//password = input.nextLine();
			connectionString = connectionString + password;

	        conn =
	           DriverManager.getConnection(connectionString);

	        // Do something with the Connection
	        stmt = conn.createStatement();
	        stmt.executeQuery("USE NBA;");
	        
	        //executequery 
	        rs = stmt.executeQuery("SELECT PlayerName,SeasonStart,Age,PTS FROM nbanew where Playername = 'James Harden';");
	        //rs = stmt.executeQuery("SHOW Databases;");
	        //System.out.println("Students\n ID\tName\tdept_name\tcred_hours");
	        while(rs.next())
	        {
	        	data.addAll(FXCollections.observableArrayList(new Player(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4))));
	        	System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
	        }
	        //calling function 
	        cStmt = conn.prepareCall("{call SeachForPlayer(?)}");
	        cStmt.setString(1,"kobe");
	        boolean hadResults = cStmt.execute();
	        
			//
			// Process all returned result sets
			//
			while (hadResults) {
				System.out.println ("\nProcessing result set");
				rs = cStmt.getResultSet();
	        	// process result set
				while(rs.next())
				{
				  System.out.println(rs.getString(1) + ": " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
				}
				hadResults = cStmt.getMoreResults();
			}

			
	    

		
		


    } catch (SQLException ex) {
        // handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    }
	    
	    table = new TableView<Player>();
	    /*
		ObservableList<Player> data =
		        FXCollections.observableArrayList(
		            new Player("Jacob", 1,1,1),
		            new Player("Isabella",2,2,2),
		            new Player("Ethan",3,3,3),
		            new Player("Emma",4,4,4),
		            new Player("Michael",5,5,5)
		        );
		*/
		table.setItems(data);
		TableColumn NameCol = new TableColumn("Name");
		NameCol.setCellValueFactory(new PropertyValueFactory<>("PlayerName"));
		
        TableColumn YearCol = new TableColumn("Year");
        YearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        TableColumn age = new TableColumn("Age");
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        TableColumn PtsCol = new TableColumn("PTS");
        PtsCol.setCellValueFactory(new PropertyValueFactory<>("point"));
		
        table.getColumns().addAll(NameCol, YearCol,age,PtsCol);
        
		
		Group startpage = new Group();
        startpage.getChildren().addAll(table);
        Scene StartScene = new Scene(startpage);
		return StartScene;
	}

}
