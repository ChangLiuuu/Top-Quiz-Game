package Controller;

import java.io.IOException;
import Controller.AllDataControl;
public class BallControl {
	public AllDataControl allData2 = new AllDataControl();
	   public void loadFile() throws IOException{
		   allData2.setFileName("src/Model/Ball.txt");
		   allData2.loadBallText();
	   }
}
