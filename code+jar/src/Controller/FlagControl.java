package Controller;

import java.io.IOException;
import Controller.AllDataControl;
public class FlagControl {

	public AllDataControl allData1 = new AllDataControl();
	   public void loadFile() throws IOException{
		   allData1.setFileName("src/Model/Country.txt");
		   allData1.loadQuestionText();
	   }
}
