package application;
	
import java.util.HashMap;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;


public class Main extends Application {
private HashMap<String, Scene> sceneMap;
	
	
	
	@Override
    public void start(Stage primaryStage) throws Exception{
		sceneMap = new HashMap<String, Scene>();
		WelcomeUI welcomeUI = new WelcomeUI(sceneMap, primaryStage);
		sceneMap.put("welcome", welcomeUI.createWelcomScene());
		primaryStage.setTitle("Database Application");
        primaryStage.setScene(sceneMap.get("welcome"));
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
