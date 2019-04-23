package application;
	
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	HBox buttonBox;
	Button btAdd, btLoad, btSave, btStart, btSelect;
	ComboBox<String> topicBox;
	TableView<QuestionNode> table;
	ObservableList<QuestionNode> record;
	Stage stage;
	Driver driver = new Driver();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			root.setCenter(setTopicPane());
			root.setBottom(setHBox());
			BorderPane.setAlignment(setHBox(), Pos.CENTER);
			
			Scene scene = new Scene (root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Main");
			primaryStage.show();
			
			btLoad.setOnAction(event-> {
				System.out.println("Test");
				loadFile();
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Pane setTopicPane() {
		GridPane gPane = new GridPane();
		gPane.setAlignment(Pos.CENTER);
		gPane.add(setTable(), 0, 0);
		gPane.add(setCBox(), 1, 0);
		
		return gPane;
	}
	 
	private TableView<QuestionNode> setTable() {
		table = new TableView<>();
		record = FXCollections.observableArrayList();
		TableColumn<QuestionNode, String> topic = new TableColumn<>("Topics");
		topic.setMinWidth(75);
		topic.setCellValueFactory(new PropertyValueFactory<>("topic"));
		
		TableColumn<QuestionNode, String> quesNo = new TableColumn<>("Question Number");
		quesNo.setMinWidth(100);
		quesNo.setCellValueFactory(new PropertyValueFactory<>("questionNo"));
		
		table.setItems(record);
		//Fix the size of column to 2 (No extra blank column)
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getColumns().addAll(topic, quesNo);
		return table;
	}
	
	
	
	private VBox setCBox() {
		VBox vBox = new VBox();
		Label lbTopic = new Label("Topic");
		
		topicBox = new ComboBox<>();
		topicBox.setPromptText("Select topic");
//		topicBox.getItems().addAll();
		
		btSelect = new Button("Select");
		
		vBox.getChildren().addAll(lbTopic,topicBox, btSelect);
		vBox.setPadding(new Insets(5, 5, 5, 5));
		vBox.setSpacing(5);
		
		return vBox;
	}
	
	private HBox setHBox() {
		buttonBox = new HBox();
		btAdd = new Button("ADD");
		btLoad = new Button("LOAD");
		btSave = new Button("SAVE");
		btStart = new Button("START");
		
		buttonBox.getChildren().addAll(btAdd, btLoad, btSave, btStart);
		buttonBox.setPadding(new Insets(5, 5, 5, 5));
		buttonBox.setSpacing(5);
		
		btAdd.setOnAction(event -> {
			
		});
		btLoad.setOnAction(event-> loadFile());
		btSave.setOnAction(null);
		btStart.setOnAction(null);
		
		return buttonBox;
		
	}
	
	private void loadFile() {
		
		driver.load(stage, this);
		
	}
	
	public void setComboBox(List<String> topicList) {
		topicBox.getItems().addAll(topicList);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
