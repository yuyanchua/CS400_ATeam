package application;
	
import java.util.ArrayList;
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
	List<String> boxList = new ArrayList<>();
	List<TopicGroup> topicList = new ArrayList<>();
	TableView<TopicGroup> table;
	ObservableList<TopicGroup> record;
	Stage stage;
	Scene scene;
	Driver driver = new Driver();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			root.setCenter(setTopicPane());
			root.setBottom(setHBox());
			BorderPane.setAlignment(setHBox(), Pos.CENTER);
			
			scene = new Scene (root,400,400);
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
	 
	private TableView<TopicGroup> setTable() {
		QuestionNode node = new QuestionNode("test", "test", "test", "test", null, 1); 
		ArrayList<QuestionNode> testList = new ArrayList<>();
		testList.add(node);
		TopicGroup test = new TopicGroup("test", testList);
		topicList.add(test);
		
		table = new TableView<>();
		record = FXCollections.observableArrayList(topicList);
		
		TableColumn<TopicGroup, String> topic = new TableColumn<>("Topics");
		topic.setMinWidth(75);
		topic.setCellValueFactory(new PropertyValueFactory<>("topicName"));
		
		TableColumn<TopicGroup, Integer> quesNo = new TableColumn<>("Question Number");
		quesNo.setMinWidth(100);
		quesNo.setCellValueFactory(new PropertyValueFactory<>("size"));
		
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
		
		btSelect = new Button("Select");
		btSelect.setOnAction(event -> addTable());
		
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
		
		btAdd.setOnAction(event -> addQuestion());
		btLoad.setOnAction(event-> loadFile());
		btSave.setOnAction(event -> saveFile());
		btStart.setOnAction(event -> startQuestion());
		
		return buttonBox;
		
	}
	
	private void addQuestion() {
		driver.add(stage);
	}
	
	private void loadFile() {
		driver.load(stage, this);
		
	}
	
	public void setComboBox(List<String> topicList) {
		topicBox.getItems().clear();
		for(String topic: topicList)
			if(!boxList.contains(topic))
				boxList.add(topic);
		topicBox.getItems().addAll(topicList);
	}
	
	public void setTopicList(List<TopicGroup> topicGroupList) {
		for(TopicGroup tgroup : topicGroupList) {
			if(!boxList.contains(tgroup.getTopicName()))
				topicGroupList.add(tgroup);
			else
				for(int i = 0; i < topicList.size(); i ++) {
					TopicGroup temp = topicList.get(i);
					if(temp.getTopicName().equals(tgroup.getTopicName()))
						topicList.get(i).addQuestionList(tgroup.getQuestionList());
				}
		}
	}
	
	private void saveFile() {
		driver.save(stage);
	}
	
	private void startQuestion() {
		driver.start(stage, scene);
		
	}
	
	private void addTable() {
		ObservableList<TopicGroup> list = FXCollections.observableArrayList();
		String topicName = topicBox.getValue();
		TopicGroup tgroup = null;
		for(TopicGroup temp : topicList)
			if(temp.getTopicName().equals(topicName))
				tgroup = temp;
		
		list.add(tgroup);
		record.addAll(list);
//		table.getItems().add(tgroup);
//		
//		record.add(tgroup);

	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
