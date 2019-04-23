package application;

import java.awt.Desktop;
import java.io.File;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ChooseFile {
	
	private String filePath = null;
  
	public ChooseFile() {
		
	}
	
	public void showPath() {
		System.out.println(filePath);
	}
	
	public String getPath() {
		return filePath;
	}
	
	
	public void chooseFile(Stage stage, Driver driver, Main main){
		
		Stage fileStage = new Stage();
//		while(!output) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select JSON Files");
		fileChooser.getExtensionFilters().add(
				new FileChooser.ExtensionFilter("JSON File", "*.json"));
		TextArea ta = new TextArea();
		
		Button btBrowse = new Button("Browse File");
		Button btSelect = new Button("Select");
		
		FlowPane flow = new FlowPane();
		flow.getChildren().addAll(btBrowse, btSelect);
		flow.setPadding(new Insets(5, 5, 5, 5));
		
		btBrowse.setOnAction(e->{
			ta.clear();
			File file = fileChooser.showOpenDialog(stage);
			if(file != null) {
//				openFile(file);
				printLog(ta, file);
			}
		});
		
		btSelect.setOnAction(e->{
			if(filePath == null)
				//throw error

				return;
			if(filePath != null) {
//				showPath();
//				output = true;
//				driver.setFilePath(filePath);
				driver.readJSON(filePath, main);
				fileStage.close();
				
			}
		});
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(ta, flow);
		vBox.setPadding(new Insets(5, 5, 5, 5));
		vBox.setSpacing(5);
		
		Scene scene = new Scene(vBox, 400, 200);
		
		fileStage.setTitle("Choose File");
		fileStage.setScene(scene);
		fileStage.show();
		
//		}
		
		
	}
	
	private void printLog(TextArea textArea, File file) {
		if(file == null)
			return;
		filePath = file.getAbsolutePath();
		textArea.appendText(filePath+ "\n");
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
//		ChooseFile cf = new ChooseFile();
//		Application.launch(args);
//		cf.chooseFile();
//		cf.showPath();
//		Application.launch(args);
		
	}
}
