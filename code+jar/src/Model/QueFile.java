package Model;

public class QueFile {
    String question, rightAns;
    int lineNumber;
    String choice1,choice2,choice3;


	public  void setQuestion(String que) {
		this.question = que; 
		
	}
	public void setRightAns(String ans) {
		this.rightAns = ans;
	}
	public String getQuestion() {
		return question;
	}
	public String getRightAns() {
		return rightAns;
	}
	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}
	
	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}
	
	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}
	public String getChoice1() {
		return choice1;
	}
	public String getChoice2() {
		return choice2;
	}
	public String getChoice3() {
		return choice3;
	}
    
	
   


}
