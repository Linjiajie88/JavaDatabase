package application;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.layout.VBox;
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
	private TableView<Team>table2;
	private String Target;
	private String SearchType;
	
	public Table(HashMap<String, Scene> sceneMap, Stage primaryStage) {
		this.sceneMap = sceneMap;
		this.primaryStage = primaryStage;
	}
	
	public Scene createScene(String target, String searchType) {
		
        
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
	    ObservableList<Team> data2 =
		        FXCollections.observableArrayList();
	    
	    ArrayList<Integer> yearlist = new ArrayList<Integer>();
	    ArrayList<Integer> ptslist = new ArrayList<Integer>();
	    ArrayList<Integer> orblist = new ArrayList<Integer>();


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
	       
	        //calling function 
	        if(searchType == "Player"){
	        	System.out.print("here");
	        	cStmt = conn.prepareCall("{call SearchForPlayer(?)}");
	        	cStmt.setString(1,target);
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
						data.addAll(FXCollections.observableArrayList(new Player(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4))));
						System.out.println(rs.getString(1) + ": " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
						yearlist.add(rs.getInt(2));
						orblist.add(rs.getInt(3));
						ptslist.add(rs.getInt(4));
						
						
					}
					hadResults = cStmt.getMoreResults();
				}
				
				
				table = new TableView<Player>();
				table.setItems(data);
				TableColumn NameCol = new TableColumn("Name");
				NameCol.setCellValueFactory(new PropertyValueFactory<>("PlayerName"));
				
		        TableColumn YearCol = new TableColumn("Year");
		        YearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
		        TableColumn age = new TableColumn("ORB");
		        age.setCellValueFactory(new PropertyValueFactory<>("orb"));
		        TableColumn PtsCol = new TableColumn("PTS");
		        PtsCol.setCellValueFactory(new PropertyValueFactory<>("point"));
				
		        table.getColumns().addAll(NameCol, YearCol,age,PtsCol);
		        Button backbtn  = new Button("Back");
		        
		        Button chartbtn = new Button("Chart");
		        
		        backbtn.setOnAction(e->{
		        	primaryStage.setScene(sceneMap.get("welcome"));
		        });
		        
		        chartbtn.setOnAction(e->{
		        	Chart mychart = new Chart(sceneMap,primaryStage);
	            	sceneMap.put("chart", mychart.createScene(target,yearlist,ptslist,orblist));
		        	primaryStage.setScene(sceneMap.get("chart"));
		        });
		        
		        HBox btnbox = new HBox(400,backbtn,chartbtn);
		        VBox box = new VBox(table,btnbox);
		        Group startpage = new Group();
		        startpage.getChildren().addAll(box);
		        scene = new Scene(startpage);
				
	        }
	        else {
	        	cStmt = conn.prepareCall("{call SearchForTeam(?)}");
	        	cStmt.setString(1,target);
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
						data2.addAll(new Team(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4)));
						System.out.println(rs.getString(1) + ": " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
						yearlist.add(rs.getInt(2));
						orblist.add(rs.getInt(3));
						ptslist.add(rs.getInt(4));
					}
					hadResults = cStmt.getMoreResults();
				}
				table2 = new TableView<Team>();
				table2.setItems(data2);
				TableColumn NameCol = new TableColumn("Team");
				NameCol.setCellValueFactory(new PropertyValueFactory<>("TeamName"));
				TableColumn pname = new TableColumn("Year");
		        pname.setCellValueFactory(new PropertyValueFactory<>("year"));
		        TableColumn YearCol = new TableColumn("ORB");
		        YearCol.setCellValueFactory(new PropertyValueFactory<>("orb"));
		        
		        TableColumn salaryCol = new TableColumn("PTS");
		        salaryCol.setCellValueFactory(new PropertyValueFactory<>("pts"));
				
		        //table2.autosize();
		        table2.setPrefSize(400, 500);
		        //table2.setMaxHeight(500);
		        table2.getColumns().addAll(NameCol,pname,YearCol,salaryCol);
		        
		        Button backbtn  = new Button("Back");
		        
		        Button chartbtn = new Button("Chart");
		        
		        backbtn.setOnAction(e->{
		        	primaryStage.setScene(sceneMap.get("welcome"));
		        });
		        
		        chartbtn.setOnAction(e->{
		        	Chart mychart = new Chart(sceneMap,primaryStage);
	            	sceneMap.put("chart", mychart.createScene(target,yearlist,ptslist,orblist));
		        	primaryStage.setScene(sceneMap.get("chart"));
		        });
		        HBox btnbox = new HBox(400,backbtn,chartbtn);
		        VBox box = new VBox(table2,btnbox);
		        
		        Group startpage = new Group();
		        startpage.getChildren().addAll(box);
		        scene = new Scene(startpage);
				//return scene;
	        }
	       
	        

			
	    

		
		


    } 
	    catch (SQLException ex) {
        // handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Error!");
        alert.showAndWait();
        
	    }
	    return scene;
	}
	    
	

}
