package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Driver {
	private int score;
	private List<String> topicList = new ArrayList<>();
	private List<QuestionNode> allQuestionList = new ArrayList<>();
	private List<TopicGroup> allTopicList = new ArrayList<>();
	private String loadFilePath = null;
	private String saveFilePath = null;
	
	public void add(Stage stage) {
		AddQuestion addQues = new AddQuestion();
		addQues.addQuestion(stage, this);
	}
	
	public void load(Stage stage, Main main) {
		ChooseFile cf = new ChooseFile();
		cf.chooseFile(stage, this, main);  //use this is to access back the parameter in this class
//		String filePath = cf.getPath();
//		if(filePath == null)
//			readJSON(filePath);
	}
	
	public void setFilePath(String filePath) {
		
		this.loadFilePath = filePath;
	}
	
	public void readJSON(String filePath, Main main){
		try {
			System.out.println("In reading");
			Object obj = new JSONParser().parse(new FileReader(filePath));
			JSONObject jo = (JSONObject) obj;
			JSONArray jsonQuestionArr = (JSONArray) jo.get("questionArray");
		
			getAllJSONQuestion(jsonQuestionArr);
			classifyToTopic();
			main.setComboBox(topicList);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	private void getAllJSONQuestion(JSONArray jsonQuestionArr) {
//		questionList = new ArrayList<>();
//		topicList = new ArrayList<>();
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
				JSONObject jsonChoice = (JSONObject) jsonChoiceArray.get(j);
				String isCorrect = (String) jsonChoice.get("iscorrect");
				if(isCorrect.equals("T"))
					answerIndex = j;
				String choice = (String) jsonChoice.get("choice");
				choiceText[j] = choice;
			}
			QuestionNode qNode = new QuestionNode(metaData, questionText, 
					topic, image, choiceText, answerIndex);
			allQuestionList.add(qNode);
		}
		
		displayJSONfile();
	}
	
	private void classifyToTopic() {
		for(int i = 0; i < topicList.size(); i ++) {
			TopicGroup tgroup = new TopicGroup();
			String topicName = topicList.get(i);
			tgroup.setTopicName(topicName);
			List<QuestionNode> quesList = new ArrayList<>();
			
			for(QuestionNode node : allQuestionList) {
				String name = node.getTopic();
				if(topicName.equals(name))
					quesList.add(node);
			}
			
			tgroup.setQuestionList(quesList);
			allTopicList.add(tgroup);
		}
		
		
	}
	
	public void displayJSONfile() {
		System.out.println("Topic");
		System.out.println(topicList.toString());
		System.out.println("All");
//		System.out.println(allQuestionList.toString());
		for(QuestionNode node : allQuestionList)
			System.out.println(node.toString());
	}
	
	public void save(Stage stage) {
		ChooseDir cd = new ChooseDir();
		cd.chooseDir(stage, this);
//		writeJSON();
	}
	
	@SuppressWarnings("unchecked")
	public void writeJSON(String filePath) throws FileNotFoundException {
//		
		JSONObject jsonObj = new JSONObject();
		
		JSONArray jsonQuesArr = new JSONArray();
		for(QuestionNode node : allQuestionList) {
			JSONObject jsonOues = new JSONObject();
			
			jsonOues.put("meta-data", node.getMetaData());
			jsonOues.put("questionText", node.getContent());
			jsonOues.put("topic", node.getTopic());
			jsonOues.put("image", node.getTopic());
			
			JSONArray jsonChoiceArr = new JSONArray();
			int answerIndex = node.getAnswer();
			String [] choiceContent = node.getChoiceContents();
			for(int i = 0; i < choiceContent.length; i ++) {
				JSONObject jsonChoice = new JSONObject();
				if(answerIndex == i)
					jsonChoice.put("iscorrect", "T");
				else
					jsonChoice.put("iscorrect", "F");
				jsonChoice.put("choice", choiceContent[i]);
				jsonChoiceArr.add(jsonChoice);
			}
			jsonQuesArr.add(jsonOues);
		}
		
		jsonObj.put("questionArray", jsonQuesArr);
		
		PrintWriter pw = new PrintWriter(filePath);
		pw.write(jsonObj.toJSONString());
		
		pw.flush();
		pw.close();
	}
	
	public void start(Stage stage, Scene scene) {
		StartQuestionScreen startQues= new StartQuestionScreen(stage, scene);

	}
	
	public List<QuestionNode> getQuesNodeList(){
		return allQuestionList;
	}
}
