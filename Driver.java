package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.stage.Stage;

public class Driver {
	private int score;
	private List<String> topicList;
	private List<QuestionNode> questionList;
	private String filePath = null;
	
	public void add() {
		
	}
	
	public void load(Stage stage, Main main) {
		ChooseFile cf = new ChooseFile();
		cf.chooseFile(stage, this, main);  //use this is to access back the parameter in this class
//		String filePath = cf.getPath();
//		if(filePath == null)
//			readJSON(filePath);
	}
	
	public void setFilePath(String filePath) {
		
		this.filePath = filePath;
	}
	
	public void readJSON(String filePath, Main main){
		try {
			System.out.println("In reading");
			Object obj = new JSONParser().parse(new FileReader(filePath));
			JSONObject jo = (JSONObject) obj;
			JSONArray jsonQuestionArr = (JSONArray) jo.get("questionArray");
		
			getAllJSONQuestion(jsonQuestionArr);
			main.setComboBox(topicList);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	private void getAllJSONQuestion(JSONArray jsonQuestionArr) {
		questionList = new ArrayList<>();
		topicList = new ArrayList<>();
		for(int i = 0; i < jsonQuestionArr.size(); i ++) {
			JSONObject jsonQuestion = (JSONObject) jsonQuestionArr.get(i);
			String metaData = (String) jsonQuestion.get("meta-data");
			String questionText = (String) jsonQuestion.get("questionText");
			String topic = (String) jsonQuestion.get("topic");
			if(!topicList.contains(topic))
				topicList.add(topic);
			String image = (String) jsonQuestion.get("image");
			JSONArray jsonChoiceArray = 
					(JSONArray) jsonQuestion.get("choiceArray");
			String [] choiceText = new String [jsonChoiceArray.size()];
			int answerIndex = -1;
			for(int j = 0; j < jsonChoiceArray.size(); j ++) {
				JSONObject jsonChoice = (JSONObject) jsonChoiceArray.get(i);
				String isCorrect = (String) jsonChoice.get("iscorrect");
				if(isCorrect.equals("T"))
					answerIndex = j;
				String choice = (String) jsonChoice.get("choice");
				choiceText[j] = choice;
			}
			
			QuestionNode qNode = new QuestionNode(metaData, questionText, 
					topic, image, choiceText, answerIndex);
			questionList.add(qNode);
		}
		
		displayJSONfile();
	}
	
	public void displayJSONfile() {
		System.out.println(topicList.toString());
	}
	
	public void save() {
		
	}
	
	private void writeJSON() {
		
	}
	
	public void start() {
		
	}
}
