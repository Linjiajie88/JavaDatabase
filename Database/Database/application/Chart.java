package application;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.chart.*;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Chart {
	private Button enter;
	private HashMap<String, Scene> sceneMap;
	private Stage primaryStage;
	private query query;
	private String Target;
	private String SearchType="blank";
	public static String type;
	public Chart(HashMap<String, Scene> sceneMap, Stage primaryStage) {
		this.sceneMap = sceneMap;
		this.primaryStage = primaryStage;
	}
	public Scene createScene(String type,ArrayList<Integer> list1,ArrayList<Integer>list2,ArrayList<Integer>list3) {
        //defining the axes
        final NumberAxis xAxis = new NumberAxis(list1.get(0),list1.get(list1.size()-1), 0);
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        yAxis.setLabel("pts/orb");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        
        //defining a series
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        series.setName("point");
        series2.setName("ORB");
                //populating the series with data
        
        for (int i = 0; i < list1.size(); i++) { 
        	series.getData().add(new XYChart.Data(list1.get(i),list2.get(i)));
        	series2.getData().add(new XYChart.Data(list1.get(i),list3.get(i)));
        }
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(series,series2);
        lineChart.setPrefSize(800, 600);

        //create pane
        Group startpage = new Group(lineChart);
        startpage.getChildren().addAll();
        Scene StartScene = new Scene(startpage,800,600);
		return StartScene;
	}
}