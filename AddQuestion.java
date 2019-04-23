package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddQuestion extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    try {
     HBox hButton=new HBox();
     HBox hText2=new HBox();
     VBox hCombo=new VBox();
      ObservableList<String> options =
          FXCollections.observableArrayList("Option1", "Option2", "Option3", "Option4");
      final ComboBox comboBox = new ComboBox<>(options);// create a new comboBox panel
      comboBox.setMinWidth(200);
      comboBox.setPromptText("Choose a right answer");
      hCombo.getChildren().add(comboBox);
      hCombo.setAlignment(Pos.TOP_RIGHT);
      Button myButton = new Button("Submit");// create a new button panel
      hButton.getChildren().add(myButton);
      hButton.setAlignment(Pos.CENTER);
      TextField myText1 = new TextField();// create a new testfield panel
      myText1.setPromptText("Type Question Here:");
      myText1.setMaxWidth(200);
      TextField myText2 = new TextField();
      myText2.setPromptText("Type Topic Here:");
      myText2.setMaxWidth(200);// create a new testfield panel
      hText2.getChildren().add(myText2);
      hText2.setAlignment(Pos.BASELINE_LEFT);
      BorderPane root = new BorderPane();
      root.setTop(myText1);
     
      root.setLeft(hText2);
      root.setRight(hCombo);
      root.setBottom(hButton);
      root.setPadding(new Insets(20));
      Scene scene = new Scene(root, 500, 300);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setTitle("AddQuestion");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
