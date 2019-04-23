package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndingScreen extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    try {
      HBox hButton1 = new HBox();
      Button button1 = new Button("    Exit without Save   ");
      button1.maxWidth(100);
      hButton1.getChildren().add(button1);
      hButton1.setAlignment(Pos.CENTER);

      HBox hButton2 = new HBox();
      Button button2 = new Button("       Exit and Save      ");
      button2.maxWidth(100);
      hButton2.getChildren().add(button2);
      hButton2.setAlignment(Pos.CENTER);
      Text text = new Text("Results of this quiz:");
      BorderPane root = new BorderPane();
      root.setTop(text);
      root.setCenter(hButton1);

      root.setBottom(hButton2);
      root.setPadding(new Insets(20));
      Scene scene = new Scene(root, 400, 300);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setTitle("EndingScreen");
      primaryStage.setScene(scene);
      primaryStage.show();
      
      button1.setOnAction(event ->{
    	  new ExitScreen().start(primaryStage);
      });
      button2.setOnAction(event ->{
    	  
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

}
