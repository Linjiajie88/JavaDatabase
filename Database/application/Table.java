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
		table = new TableView<Player>();
		ObservableList<Player> data =
		        FXCollections.observableArrayList(
		            new Player("Jacob", "Smith", "jacob.smith@example.com"),
		            new Player("Isabella", "Johnson", "isabella.johnson@example.com"),
		            new Player("Ethan", "Williams", "ethan.williams@example.com"),
		            new Player("Emma", "Jones", "emma.jones@example.com"),
		            new Player("Michael", "Brown", "michael.brown@example.com")
		        );
		table.setItems(data);
		TableColumn firstNameCol = new TableColumn("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn age = new TableColumn("Age");
        TableColumn emailCol = new TableColumn("Email");
        
     
        
		Connection conn = null;
	    Statement stmt = null;
	    CallableStatement cStmt;
	    ResultSet rs = null;
	    int numRowsModified = 0;
	    String password;
	    String connectionString = "jdbc:mysql://localhost:3306?" +
	                                       "user=root&password=";

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
	        //rs = stmt.executeQuery("SHOW Databases;");
	        stmt.executeQuery("USE NBA;");
	        //numRowsModified = stmt.executeUpdate("insert into Student VALUES(70000, 'Amelia', 'Finance', 1);");
			//System.out.println(numRowsModified + " rows inserted.");

	        rs = stmt.executeQuery("SELECT PlayerName,Age,G,GS FROM nbanew where Playername = 'James Harden';");
	        //rs = stmt.executeQuery("SHOW Databases;");

			System.out.println("Students\n ID\tName\tdept_name\tcred_hours");
			
	        while(rs.next())
	        {
	        	System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
	        }
	    

		
		


    } catch (SQLException ex) {
        // handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    }
	    
	    
		
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol,age);
        
		
		Group startpage = new Group();
        startpage.getChildren().addAll(table);
        Scene StartScene = new Scene(startpage);
		return StartScene;
	}

}
