package application;

/**
 * Question Node 
 * 
 * @author Ruiting Tong (Tom)
 *
 */
public class QuestionNode {
	private String content;
	private String topic;
	private String[] choiceContents;
	private int answer;
	private String metaData;
	private String image;
	
	public QuestionNode() {
		content = null;
		topic = null;
		choiceContents = null;
		answer = -1;
		metaData = null;
		image = null;
	}
	
	public QuestionNode(String metaData, String content, String topic, 
			String image, String [] choiceContenta, int answer) {
		setMetaData(metaData);
		setContent(content);
		setTopic(topic);
		setImage(image);
		setChoiceContents(choiceContents);
		setAnswer(answer);
		
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public void setTopic(String topic){
		this.topic = topic;
	}
	
	public void setChoiceContents(String[] choiceContents) {
		this.choiceContents = choiceContents;
	}
	
	public void setAnswer(int index) {
		this.answer = index;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}
	
	public String getContent(){
		return content;
	}
	
	public String getTopic(){
		return topic;
	}
	
	public int getAnswer() {
		return answer;
	}
	
	public String[] getChoiceContents() {
		return choiceContents;
	}
	
	public String getImage() {
		return image;
	}
	
	public String getMetaData() {
		return metaData;
	}	
}
