package Controller;

import java.io.IOException;
import Controller.AllDataControl;

public class MathLoadControl {
	public AllDataControl allData = new AllDataControl();
   public void loadFile() throws IOException{
	   allData.setFileName("src/Model/Math.txt");
	   allData.loadQuestionText();    
   }
   
   
   
   
}
