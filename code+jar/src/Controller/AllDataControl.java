package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Model.QueFile;

public class AllDataControl {

   public String myAnswer;
   public  int totalNumber;
   public  int curNumber;
   public int rightNumber;
   public String questionText;
   public String fileName;
   public ArrayList<QueFile> queList = new ArrayList<QueFile>();
   public ArrayList<QueFile> queList1 = new ArrayList<QueFile>();
   public int lineNumber;
   
   public void setMyAnswer(String myAnswer) {
	this.myAnswer = myAnswer;
}
   public String getMyAnswer() {
	return myAnswer;
}
   public void setFileName(String fileName) {
	this.fileName = fileName;
}
   public String getFileName() {
	return fileName;
}
   
   
   public void loadQuestionText() throws IOException { 
	   BufferedReader br = new BufferedReader(new FileReader(getFileName()));
       String val = null;
       //Using split() method to separate the elements of each line into a String[]
		while(true){
			       if ((val = br.readLine()) == null) break;
                   String [] s = val.split("/");
                   QueFile quefile = new QueFile();
                   quefile.setQuestion(s[0]);
                   quefile.setRightAns(s[1]);
                   queList.add(quefile);
                   lineNumber++;
		}
		this.setTotalNumber(lineNumber);
		br.close();
}   
   
   public void loadBallText() throws IOException { 
	   BufferedReader br = new BufferedReader(new FileReader(getFileName()));
       String val = null;
       //Using split() method to separate the elements of each line into a String[]
		while(true){
			       if ((val = br.readLine()) == null) break;
                   String [] s = val.split("/");
                   QueFile quefile = new QueFile();
                   quefile.setQuestion(s[0]);
                   quefile.setRightAns(s[1]);
                   quefile.setChoice1(s[2]);
                   quefile.setChoice2(s[3]);
                   quefile.setChoice3(s[4]);
                   queList1.add(quefile);
                   lineNumber++;
		}
		this.setTotalNumber(lineNumber);
		br.close();
}   
   
   
   public String getQuestionText() {
	  return queList.get(curNumber).getQuestion();
}
   public String getBallText(){
	   return queList1.get(curNumber).getQuestion();
   }

public int getTotalNumber() {
	return lineNumber ;
}
public void setTotalNumber(int totalNumber) {
	this.totalNumber = totalNumber;
}
public int getCurNumber() {
	return curNumber + 1;
}
public void setCurNumber(int curNumber) {

	this.curNumber = curNumber;
}
public int getRightNumber() {
	
//	//System.out.println(queList.get(curNumber).getRightAns());
//	
//	if(queList.get(curNumber).getRightAns() == this.getMyAnswer()){
//		this.rightNumber++;
//	}
	return rightNumber;
}

   
}
