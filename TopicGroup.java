package application;

import java.util.List;

public class TopicGroup {
	private String topicName;
	private List<QuestionNode> questionList;
	private int size;
	
	public TopicGroup() {
		topicName = null;
		questionList = null;
	}
	
	public TopicGroup(String topicName, List<QuestionNode> questionList) {
		setTopicName(topicName);
		setQuestionList(questionList);
		
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public List<QuestionNode> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionNode> questionList) {
		this.questionList = questionList;
		setSize();
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize() {
		this.size = questionList.size();
	}
	
	public void addQuestionList(List<QuestionNode> questionList) {
		this.questionList.addAll(questionList);
	}
}
