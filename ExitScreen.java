package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ExitScreen extends Application {
  @Override
  public void start(Stage primaryStage) {
      try {
          Label goodbyeMessage = new Label("Thank you for using Quiz Generator!");
          
          BorderPane root = new BorderPane();
          root.setCenter(goodbyeMessage);
          Scene scene = new Scene(root,400,400);
          scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
          primaryStage.setScene(scene);
          primaryStage.setTitle("Exit");
          primaryStage.show();
      } catch(Exception e) {
          e.printStackTrace();
      }
  }
  
  public static void main(String[] args) {
      launch(args);
  }
}

