package View;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class BallAnsPanel extends BallGameView {
	
	public  JRadioButton c1;
	public  JRadioButton c2;
	public  JRadioButton c3;
	
	ButtonGroup buGroup;
	int m;
	public BallAnsPanel(){
	
		this.setBounds(8,16,400,120);
		this.setLayout(null);
	 buGroup = new ButtonGroup();
  
     c1 = new JRadioButton(ballControl.allData2.queList1.get(m).getChoice1());
     c2 = new JRadioButton(ballControl.allData2.queList1.get(m).getChoice2());
     c3 = new JRadioButton(ballControl.allData2.queList1.get(m).getChoice3());
     c1.setSelected(true);
    c1.setBounds(250, 0, 200, 50);
    c2.setBounds(250, 40, 200, 50);
    c3.setBounds(250, 80, 200, 50);
    buGroup.add(c1);
    buGroup.add(c2);
    buGroup.add(c3);
    this.add(c1);
    this.add(c2);
    this.add(c3);
	}
	
	public String setChoice = null;
	public void setBallChoice(){
	 
	 ArrayList<JRadioButton> array = new ArrayList<JRadioButton>();
	 array.add(c1);
	 array.add(c2);
	 array.add(c3);
	 for(int i = 0; i < 3; ++i){
	   if(array.get(i).isSelected()){
	     setChoice = array.get(i).getText(); 
	   }
	 }
	   
	}
	public String getSetChoice() {
		return setChoice;
	} 	
}
