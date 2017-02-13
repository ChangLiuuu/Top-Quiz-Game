package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;

import Controller.FlagControl;
import Model.QueFile;



@SuppressWarnings("serial")
public class FlagGameView extends JPanel {
	FlagPieChart p;
	public JFrame mathFrame;
	JPanel mainPanel = new JPanel();
	private JPanel question;
	public JTextArea answer = new JTextArea();
	JPanel scorePanel; 
	public JLabel toLabel = new JLabel();
	public JLabel rightLabel;
	public JButton nextButton;
	public QueFile que; 
	
	public TimePanel tp;
	public JPanel backPanel;
	public Timer timer;
	public FlagPanel fPanel;
	public  FlagControl flagControl;
	public ActionListener taskPerformer;
	
	public FlagGameView()  {      
                flagControl = new FlagControl();
                try {
					flagControl.loadFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public void showMainPanel() throws IOException{
		p = new FlagPieChart();
		
		mathFrame = new JFrame("Flag Game");
		mathFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		Font bigFont = new Font("sanserif", Font.BOLD, 20);
		Border outline = BorderFactory.createLineBorder(Color.lightGray);
		//question
		mainPanel.setLayout(null);
		mainPanel.setSize(1000, 600);
		JLabel qLabel = new JLabel("Guess the flag");
		qLabel.setBounds(10, 40, 100, 40);
		question = new JPanel();
		question.setLayout(null);
		question.setBounds(10,70,600,240);
		fPanel = new FlagPanel(); // draw the flag
		question.add(fPanel);
		
		
		
		mainPanel.add(qLabel);
		mainPanel.add(question);
		//answer
		JLabel aLabel = new JLabel("Begin with a capital letter！ ONLY ONE WORD with no spaces");
		aLabel.setBounds(10, 230, 600, 200);
		answer.setFont(bigFont);
		answer.setBounds(10,350,600,100);

		mainPanel.add(aLabel);
		mainPanel.add(answer);
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
			@SuppressWarnings("static-access")
			public  void actionPerformed(ActionEvent e) {
				mathFrame.dispose();
				p.right=0;
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
		toLabel.setText ("Total: " +flagControl.allData1.getCurNumber() + "/"+5);
		rightLabel = new JLabel("Right: " + flagControl.allData1.getRightNumber());  
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

	public void changePage(){
		backPanel.remove(tp);
		tp.w=0;
		backPanel.add(tp);
		backPanel.repaint();
		
		scorePanel.remove(p.getChartPanel());
		
		
		flagControl.allData1.setMyAnswer(answer.getText());
		if(flagControl.allData1.getCurNumber()>=flagControl.allData1.getTotalNumber()){
			flagControl.allData1.curNumber = 4;
			if(flagControl.allData1.getMyAnswer().
					equals(flagControl.allData1.queList.get(flagControl.allData1.curNumber).getRightAns())){
				flagControl.allData1.rightNumber++;
				rightLabel.setText("Right: " + flagControl.allData1.getRightNumber());
				flagControl.allData1.rightNumber--;
				FlagPieChart.right++;
				p = new FlagPieChart(); 
				p.getChartPanel().setBounds(2, 120, 300, 300);
				scorePanel.add(p.getChartPanel());
				scorePanel.repaint();
				FlagPieChart.right--;
			}
			nextButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null,toLabel.getText() + "\n"  + rightLabel.getText() +"\n"+ "Please press OK and then go back.");   
				}    
			});
			answer.setEnabled(false);
			backPanel.remove(tp);
			backPanel.repaint();
			
			return;
		}	
			
		if(flagControl.allData1.getCurNumber()<flagControl.allData1.getTotalNumber()){
			if(flagControl.allData1.getMyAnswer().
					equals(flagControl.allData1.queList.get(flagControl.allData1.curNumber).getRightAns())){
				flagControl.allData1.rightNumber++;
				rightLabel.setText("Right: " + flagControl.allData1.getRightNumber());
				
				FlagPieChart.right++;
			    
			}
			flagControl.allData1.curNumber++;
			timer.stop();
			timer = new Timer(14000,taskPerformer);
			timer.start();
			question.remove(fPanel);
			fPanel.i++;
			question.add(fPanel);
			question.repaint();
			
			toLabel.setText(("Total: " + flagControl.allData1.getCurNumber() + "/"
					+flagControl.allData1.getTotalNumber())); 
			question.add(fPanel);
			answer.setText(null);
		}
		p = new FlagPieChart();
		p.getChartPanel().setBounds(2, 120, 300, 300);
		scorePanel.add(p.getChartPanel());
		scorePanel.repaint();


	}

	class FLagNextListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			changePage();
		} 

	}

}


