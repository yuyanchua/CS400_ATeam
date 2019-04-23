package application;


//Java Program to create alert of confirmation

import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.control.*; 
import javafx.stage.Stage; 
import javafx.scene.control.Alert.AlertType; 
public class Confirm extends Application { 

 // launch the application 
 public void start(Stage s) 
 { 
     // set title for the stage 
     s.setTitle("creating alerts"); 

     // create a button 
     Button b = new Button("Confirmation alert"); 

     // create a tile pane 
     TilePane r = new TilePane(); 

     // create a alert 
     Alert a = new Alert(AlertType.NONE); 

     // action event 
     EventHandler<ActionEvent> event = new 
                      EventHandler<ActionEvent>() { 
         public void handle(ActionEvent e) 
         { 
             // set alert type 
             a.setAlertType(AlertType.CONFIRMATION); 
             a.setHeaderText(null);
             a.setContentText("Are you Sure?");
             // show the dialog 
             a.show(); 
         } 
     }; 


     // when button is pressed 
     b.setOnAction(event);

     // add button 
     r.getChildren().add(b); 

     // create a scene 
     Scene sc = new Scene(r, 200, 200); 

     // set the scene 
     s.setScene(sc); 

     s.show(); 
 } 

 public static void main(String args[]) 
 { 
     // launch the application 
     launch(args); 
 } 
} 