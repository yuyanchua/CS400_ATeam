package application;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ChooseDir {
	
	DirectoryChooser dirChooser = new DirectoryChooser();
	Stage dirStage;
	Label lbTitle, lbDir, lbName;
	TextArea taDir;
	TextField tfName;
	Button btBrowse, btConfirm;
	String fileName, filePath;
	Driver driver;
	
	public ChooseDir(){
		lbTitle = new Label("Choose Directory");
		lbDir = new Label("Dir: ");
		lbName = new Label("Name: ");
		
		taDir = new TextArea();
		taDir.setMaxHeight(5);
		tfName = new TextField();
		
		btBrowse = new Button("Browse");
		btConfirm = new Button("Confirm");
		
		btBrowse.setOnAction(event); //if seperate only can this way
		btConfirm.setOnAction(add);
	}
	
	public void chooseDir(Stage stage, Driver driver) {
		dirStage = new Stage();
		this.driver = driver;
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);	 
		
		gridPane.add(lbTitle, 1, 0);
		gridPane.add(lbDir, 0, 1);
		gridPane.add(taDir, 1, 1);
		gridPane.add(btBrowse, 2, 1);
		gridPane.add(lbName, 0, 2);
		gridPane.add(tfName, 1, 2);
		gridPane.add(btConfirm, 1, 3);
		
		gridPane.setPadding(new Insets(5, 5, 5, 5));
		
		Scene scene = new Scene(gridPane, 700, 400);
		
		dirStage.setTitle("Choose Directory");
		dirStage.setScene(scene);
		dirStage.show();
	}
	
	EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			taDir.clear();
			File dir = dirChooser.showDialog(dirStage);
			if(dir != null) {
				filePath = dir.getAbsolutePath();
				taDir.appendText(filePath);
			}else {
				taDir.appendText(null);
			}
		}
	};
	
	EventHandler<ActionEvent> add = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			fileName = tfName.getText();
			String fullPath = filePath + "\\" + fileName + ".json";
//			System.out.println(fullPath);
			try {
				driver.writeJSON(fullPath);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}finally {
				dirStage.close();
			}
		}
	};
	
}
