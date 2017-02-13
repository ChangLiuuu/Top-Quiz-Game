package View;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class MainPanel {
	   public JFrame jframe;
	   JLabel labelTop, labelCenterBG, laberCenter1;
	   JPanel panelCenter;
	   JPanel panelN;
	   ImageIcon background;
	   
	   Checkbox checkCenter1,checkCenter2,checkCenter3;
	   JButton buttonDown;
	   
     public MainPanel(){
    	// Design the overall frame
    	jframe = new JFrame();
    	jframe.setTitle("TopQuiz");
    	jframe.setSize(1000, 590); 
        jframe.setResizable(false); // Window is not allowed to set the size. 
    	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	
   
     //Design the center of BorderLayout
    	
    	 //add the background to the LayeredPane and set the ContentPane transparent. 
    	 background = new ImageIcon("./src/images/CenterBackground1.jpg");
         labelCenterBG = new JLabel(background);
    	 labelCenterBG.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		 panelCenter = (JPanel)(jframe.getContentPane());
		 panelCenter.setOpaque(false);
    	 panelCenter.setLayout(null);  
    	 jframe.setLayout(null);
    	 jframe.getLayeredPane().add(labelCenterBG,new Integer(Integer.MIN_VALUE));
    	 
    	 //add the button "Start"
    	 buttonDown = new JButton("Start");
    	 buttonDown.setBounds(800, 500, 160, 50);
         panelCenter.add(buttonDown);
         
         //Create three JRadioButton into a ButtonGroup
         ButtonGroup buGroup = new ButtonGroup();
         JRadioButton c1 = new JRadioButton("Flag Game");
         JRadioButton c2 = new JRadioButton("Math Game");
         JRadioButton c3 = new JRadioButton("Ball Game");
         c1.setBounds(500, 100, 200, 50);
         c2.setBounds(500, 150, 200, 50);
         c3.setBounds(500, 200, 200, 50);
         c1.setBackground(new Color(160,85,211));
         buGroup.add(c1);
         buGroup.add(c2);
         buGroup.add(c3);
         panelCenter.add(c1);
         panelCenter.add(c2);
         panelCenter.add(c3);
         
        //set the ItemListen of the "Start" button which combines with JRadioButton
         buttonDown.addActionListener(new ActionListener(){

			      public void actionPerformed(ActionEvent e) {
				  // TODO Auto-generated method stub
			    	  if(c1.isSelected()){
				             try {
								new FlagGameView().showMainPanel();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				             jframe.setVisible(false);
			    	  }else if(c2.isSelected()){
				             try {
								new MathView().showMainPanel();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				             jframe.setVisible(false);
			    	  }else if(c3.isSelected()){
				             try {
								new BallGameView().showMainPanel();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				             jframe.setVisible(false);
			      }      
			      }
         });
  	
    	jframe.setVisible(true);
     }
    //  public static void main(String[] args) {
    	//  new QuizPanel();
	 // new PlanetGame();	
    	//  new FlagGame();
	// }
}

	
