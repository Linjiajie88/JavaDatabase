package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javafx.scene.chart.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class WelcomeUI {
	private Button enter;
	private HashMap<String, Scene> sceneMap;
	private Stage primaryStage;
	private query query;
	private String Target;
	private String SearchType;
	public static String type;
	public WelcomeUI(HashMap<String, Scene> sceneMap, Stage primaryStage) {
		this.sceneMap = sceneMap;
		this.primaryStage = primaryStage;
	}
	public Scene createWelcomScene() {
		
	 
	    //creat TableUI
		Table table = new Table(sceneMap, primaryStage);
    	sceneMap.put("table", table.createScene());
    	
    	Image pic = new Image("bg.jpg");
        ImageView b = new ImageView(pic);
        b.setFitHeight(500);
        b.setFitWidth(800);
        b.setPreserveRatio(true);
        b.setImage(pic);
        
		//create buttons
        enter = new Button("test");
        enter.setPrefWidth(90);
        
        
        TextField textField = new TextField ();
        textField.setText("Enter the Name here");
        
        
        MenuButton menu = new MenuButton("Search by Team or Player");
        //Creating menu Items
        menu.setMnemonicParsing(true);
       
        MenuItem item1 = new MenuItem("Player");
 
        MenuItem item2 = new MenuItem("Team");
        menu.getItems().addAll(item1, item2);
        
       item1.setOnAction(e->{
    	   menu.setText("Player");
    	   SearchType="Player";
       });
       
       item2.setOnAction(e->{
    	   menu.setText("Team");
    	   SearchType="Team";
       });
        
        //LoginUI loginUI = new LoginUI(sceneMap, primaryStage);
    	//sceneMap.put("login", loginUI.createLoginScene());
        
        enter.setOnAction(e->{
        	Target = textField.getText();
            System.out.println(Target);
            System.out.println(SearchType);
        	//this.query=new query();
        	//query.test();
        	primaryStage.setScene(sceneMap.get("table"));
        	
        });
        /*
        host.setOnAction(e->{
        	type = "host";
        	primaryStage.setScene(sceneMap.get("login"));
        });
        manager.setOnAction(e->{
        	type = "manager";
        	primaryStage.setScene(sceneMap.get("login"));
        });
        
        //image
        
        Image pic = new Image("welcome.png");
        ImageView b = new ImageView(pic);
        b.setFitHeight(500);
        b.setFitWidth(500);
        b.setPreserveRatio(true);
        b.setImage(pic);
        */
        //hboox
        HBox hBox = new HBox(10,textField,menu,enter);
        //postionBox.setLayoutX(200);
        //postionBox.setLayoutY(200);

        //create pane
        Group startpage = new Group();
        startpage.getChildren().addAll(b,hBox);
        Scene StartScene = new Scene(startpage,800,500);
		return StartScene;
	}

}
