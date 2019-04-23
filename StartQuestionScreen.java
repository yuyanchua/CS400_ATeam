package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * StartQuestionScreen
 * 
 * @author Ruiting Tong
 *
 */
public class StartQuestionScreen {

	private Button btConfirm, btFinish, btNext;
	private RadioButton btA, btB, btC, btD;
	private ToggleGroup tgAnswerGroup;
	
	private HBox buttonBox;
	private HBox contentBox;
	private VBox leftVBox;
	private VBox rightVBox;
	private String imageFile = null;
	private String questionContent = "I have one apple and you have one apple. "
			+ "How many apples do we have?";
	
	
	public StartQuestionScreen(Stage stage, Scene scene) {
		Stage quesStage = new Stage();
			BorderPane root = new BorderPane();
			root.setTop(null);
			root.setLeft(setLeftVBox());
			root.setBottom(setBottomHBox());
			root.setTop(setTopHBox());
			
			if (imageFile != null) {
				root.setRight(setRightVBox(imageFile));
			}
			
			root.setPadding(new Insets(20));
			Scene quesScene = new Scene(root, 400, 300);
//			quesScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			quesStage.setScene(quesScene);
			quesStage.setTitle("StartQuestionScreen");
			quesStage.show();
	}
	
	private HBox setBottomHBox() {
		buttonBox = new HBox();
		btConfirm = new Button("CONFIRM");
		btFinish = new Button("FINISH");
		btNext = new Button("NEXT");
		Text result = new Text("Result: ");
	    buttonBox.getChildren().addAll(btConfirm, btNext, btFinish, result);
	    buttonBox.setPadding(new Insets(5, 5, 5, 5));
	    buttonBox.setSpacing(5);
	    return buttonBox;		
	}
	
	private HBox setTopHBox() {
		contentBox = new HBox();
		Label question= new Label(questionContent);
		question.setWrapText(true);
	    contentBox.getChildren().add(question);
	    contentBox.setPadding(new Insets(5, 5, 5, 5));
	    contentBox.setAlignment(Pos.CENTER);
	    return contentBox;		
	}
	
	private VBox setLeftVBox() {
		leftVBox = new VBox();
		tgAnswerGroup = new ToggleGroup();
		
		btA = new RadioButton("A");
		btB = new RadioButton("B");
		btC = new RadioButton("C");
		btD = new RadioButton("D");
		
		setGroup(btA, btB, btC, btD);
		
		leftVBox.getChildren().addAll(btA, btB, btC, btD);
		leftVBox.setPadding(new Insets(5, 5, 5, 5));
		leftVBox.setSpacing(5);
		return leftVBox;
	}
	
	private void setGroup(RadioButton... rbList) {
		for(RadioButton rb : rbList)
			rb.setToggleGroup(tgAnswerGroup);
	}
	
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
		
	private VBox setRightVBox(String file) {
		rightVBox = new VBox();
		ImageView image = new ImageView(new Image(file));
		rightVBox.getChildren().add(image);
		return rightVBox;
	}
	public static void main(String[] args) {
//		launch(args);
//		new StartQuestionScreen();
	}
}
