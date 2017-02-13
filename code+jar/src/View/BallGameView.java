package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;

import Controller.BallControl;
import Model.QueFile;



@SuppressWarnings("serial")
public class BallGameView extends JPanel {
	public JFrame mathFrame;
	JPanel mainPanel = new JPanel();
	private JPanel question;
	
	JPanel scorePanel; 
	public JLabel toLabel = new JLabel();
	public JLabel rightLabel;
	public JButton nextButton;
	public QueFile que; 
	public TimePanel tp;
	public JPanel backPanel;
	public Timer timer;
	public BallPanel ballPanel;
	public BallAnsPanel bAnsPanel;
	public JPanel choicePanel;
	public BallControl ballControl;
	public ActionListener taskPerformer;
	public BallPieChart p;
	public BallGameView() {
	
		ballControl = new BallControl();
                try {
                	ballControl.loadFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public void showMainPanel() throws IOException{
		p = new BallPieChart();
		mathFrame = new JFrame("Planet Game");
		mathFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		Border outline = BorderFactory.createLineBorder(Color.lightGray);
		//question
		mainPanel.setLayout(null);
		mainPanel.setSize(1000, 600);
		JLabel qLabel = new JLabel("Guess the ball:");
		qLabel.setBounds(10, 40, 100, 40);
		question = new JPanel();
		question.setLayout(null);
		question.setBounds(10,70,600,200);
		ballPanel = new BallPanel(); // draw the Ball
		question.add(ballPanel);
		
		
		
		mainPanel.add(qLabel);
		mainPanel.add(question);
		//answer label
		JLabel aLabel = new JLabel("choose your answer:");
		aLabel.setBounds(10, 300, 600, 40);
		//answer panel
		choicePanel = new JPanel();
		choicePanel.setLayout(null);
		choicePanel.setBounds(10, 330, 600, 170);
		choicePanel.setBorder(outline);
		bAnsPanel = new BallAnsPanel();
//		bAnsPanel.setBounds(2,2,570,165);
//		bAnsPanel.setLayout(null);
		choicePanel.add(bAnsPanel);
		choicePanel.setBorder(outline);
		
        
		mainPanel.add(aLabel);
		mainPanel.add(choicePanel);
		//Buttons  + timePanel(to count down)
		nextButton = new JButton("Next");
		JButton backButton = new JButton("Back");
		JPanel nextPanel = new JPanel();
		backPanel = new JPanel();
		nextPanel.setBorder(outline);
		backPanel.setBorder(outline);
		nextPanel.add(nextButton);
		backPanel.setLayout(null);
		nextPanel.setLayout(null);
		backPanel.setBounds(0, 0, 1000, 50);
		tp = new TimePanel();
		backPanel.add(tp);
		backButton.setBounds(0, 8, 100, 38);
		nextPanel.setBounds(0, 510, 1000, 50);
		nextButton.setBounds(480,5,100,40);
		//BackListener
		backButton.addActionListener(new ActionListener(){
			public  void actionPerformed(ActionEvent e) {
				mathFrame.dispose();
				new MainPanel().jframe.setVisible(true);
			}
		});
		backPanel.add(backButton);

		nextPanel.add(nextButton); 

		//NextListener
		nextButton.addActionListener(new FLagNextListener());
		mathFrame.add(nextPanel);
		mathFrame.add(backPanel);

		//Create a Timer to change the page automatically
		taskPerformer = new ActionListener (){
			public void actionPerformed(ActionEvent e){
			    changePage();			
			}
		};
		// add the task to the timer
		timer = new Timer(14000,taskPerformer);
		timer.start();
		//ScorePanel
		scorePanel = new JPanel();
		scorePanel.setLayout(null);
		scorePanel.setBounds(700, 40, 340, 500);
		scorePanel.setBorder(outline);
		toLabel.setText ("Total: " +ballControl.allData2.getCurNumber() + "/"+4);
		rightLabel = new JLabel("Right: " + ballControl.allData2.getRightNumber());  
		toLabel.setBounds(30,30,100,40);
		rightLabel.setBounds(30,80,100,40);
		p.getChartPanel().setBounds(2, 120, 300, 300);
		scorePanel.add(toLabel);
		scorePanel.add(rightLabel);
		scorePanel.add(p.getChartPanel());
		mainPanel.add(scorePanel);
		mathFrame.add(BorderLayout.CENTER, mainPanel);
		mathFrame.setSize(1000,600);
		mathFrame.setVisible(true);        


	}
      
	@SuppressWarnings("static-access")
	public void changePage(){
		backPanel.remove(tp);
		tp.w=0;
		backPanel.add(tp);
		backPanel.repaint();
		bAnsPanel.setBallChoice();
		scorePanel.remove(p.getChartPanel());
		
		if(ballControl.allData2.getCurNumber()>=ballControl.allData2.getTotalNumber()){
			ballControl.allData2.curNumber = ballControl.allData2.getTotalNumber();
			ballControl.allData2.setMyAnswer(bAnsPanel.getSetChoice());
			if(ballControl.allData2.getMyAnswer().
					equals(ballControl.allData2.queList1.get(bAnsPanel.m).getRightAns())){
				ballControl.allData2.rightNumber++;
				p.right++;
				rightLabel.setText("Right: " + ballControl.allData2.getRightNumber());
				ballControl.allData2.rightNumber--;
		     	}
			nextButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null,toLabel.getText() + "\n"  + rightLabel.getText() +"\n"+ "Please press OK and then go back.");   
				}    
			});
			p = new BallPieChart();
			p.getChartPanel().setBounds(2, 120, 300, 300);
			scorePanel.add(p.getChartPanel());
			scorePanel.repaint();
			p.right--;
			backPanel.remove(tp);
			backPanel.repaint();
			return ;
		}	
	   
		ballControl.allData2.setMyAnswer(bAnsPanel.getSetChoice());
		if(ballControl.allData2.getCurNumber()<ballControl.allData2.getTotalNumber()){
			
			question.remove(ballPanel);
			choicePanel.remove(bAnsPanel);
			
			timer.stop();
			timer = new Timer(14000,taskPerformer);
			timer.start();
			
			ballPanel.i++;
			question.add(ballPanel);
			question.repaint();
			ballControl.allData2.curNumber++;
			bAnsPanel.c1.setText(ballControl.allData2.queList1.get(ballControl.allData2.curNumber).getChoice1());
			bAnsPanel.c2.setText(ballControl.allData2.queList1.get(ballControl.allData2.curNumber).getChoice2());
			bAnsPanel.c3.setText(ballControl.allData2.queList1.get(ballControl.allData2.curNumber).getChoice3());
			
			choicePanel.add(bAnsPanel);
			choicePanel.repaint();
			
			
			if(ballControl.allData2.getMyAnswer().
				equals(ballControl.allData2.queList1.get(bAnsPanel.m).getRightAns())){
			ballControl.allData2.rightNumber++;
			p.right++;
			rightLabel.setText("Right: " + ballControl.allData2.getRightNumber());
	     	}
			
			toLabel.setText(("Total: " + ballControl.allData2.getCurNumber() + "/"
					+ballControl.allData2.getTotalNumber())); 
			
			p = new BallPieChart();
			p.getChartPanel().setBounds(2, 120, 300, 300);
			scorePanel.add(p.getChartPanel());
			scorePanel.repaint();
		}
		
           bAnsPanel.m++;

	}

	class FLagNextListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			changePage();
		} 

	}

}


