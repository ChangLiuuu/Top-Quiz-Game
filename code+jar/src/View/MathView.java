package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;

import Controller.MathLoadControl;
import Model.QueFile;



@SuppressWarnings("serial")
public class MathView extends JPanel {
	public JFrame mathFrame;
	JPanel mainPanel = new JPanel();
	public JLabel question;
	public JTextArea answer = new JTextArea();
	JPanel scorePanel; 
	public JLabel toLabel = new JLabel();
	public JLabel rightLabel;
	public JButton nextButton;
	public QueFile que; 
	public MathLoadControl mathControl;
	public TimePanel tp;
	public JPanel backPanel;
	public Timer timer;
	public ActionListener taskPerformer;
	public MathPieChart p;
	public MathView() {
		mathControl = new MathLoadControl();
		try {
			mathControl.loadFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		que = new QueFile();

	}
	public void showMainPanel() throws IOException{
		p = new MathPieChart();
		mathFrame = new JFrame("Math Game");
		mathFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // title bar
		Font bigFont = new Font("sanserif", Font.BOLD, 20);
		Border outline = BorderFactory.createLineBorder(Color.lightGray);
		//question
		mainPanel.setLayout(null);
		mainPanel.setSize(1000, 600);
		JLabel qLabel = new JLabel("Question:");
		qLabel.setBounds(10, 40, 100, 40);
		question = new JLabel();
		question.setBounds(10,70,600,240);
		question.setBorder(outline);
		question.setFont(bigFont);
		question.setText(mathControl.allData.getQuestionText());
		mainPanel.add(qLabel);
		mainPanel.add(question);
		//answer
		JLabel aLabel = new JLabel("Put in your answer(with no spaces):");
		aLabel.setBounds(10, 230, 600, 200);
		answer.setFont(bigFont);
		answer.setBounds(10,350,600,100);

		mainPanel.add(aLabel);
		mainPanel.add(answer);
		//Buttons  + timePanel
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
				p.right=0;
				mathFrame.dispose();
				new MainPanel().jframe.setVisible(true);
			}
		});
		backPanel.add(backButton);

		nextPanel.add(nextButton); 

		//NextListener
		nextButton.addActionListener(new MathNextListener());
		mathFrame.add(nextPanel);
		mathFrame.add(backPanel);

		//Timer
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
        
		toLabel.setText ("Total: " + mathControl.allData.getCurNumber() + "/"
				+mathControl.allData.getTotalNumber());
		rightLabel = new JLabel("Right: " + mathControl.allData.getRightNumber());  
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
		backPanel.remove(p.getChartPanel());
		mathControl.allData.setMyAnswer(answer.getText());
		
		if(mathControl.allData.getCurNumber()>=mathControl.allData.getTotalNumber()){
			mathControl.allData.curNumber = mathControl.allData.getTotalNumber() - 1;
           
			if(mathControl.allData.getMyAnswer().
					equals( mathControl.allData.queList.get(mathControl.allData.curNumber).getRightAns())){
				mathControl.allData.rightNumber++;
				MathPieChart.right++;
				rightLabel.setText("Right: " + mathControl.allData.getRightNumber());
			}
			p = new MathPieChart();
			p.getChartPanel().setBounds(2, 120, 300, 300);
			scorePanel.add(p.getChartPanel());
			scorePanel.repaint();
			toLabel.setText(("Total: " + mathControl.allData.getCurNumber() + "/"
					+mathControl.allData.getTotalNumber())); 
			answer.setText(null);
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
		else{
		if(mathControl.allData.getMyAnswer().
				equals( mathControl.allData.queList.get(mathControl.allData.curNumber).getRightAns())){
			mathControl.allData.rightNumber++;
			p.right++;
			rightLabel.setText("Right: " + mathControl.allData.getRightNumber());
		}
		
		if(mathControl.allData.getCurNumber()<mathControl.allData.getTotalNumber()){
			mathControl.allData.curNumber++;
			
			toLabel.setText(("Total: " + mathControl.allData.getCurNumber() + "/"
					+mathControl.allData.getTotalNumber())); 
			question.setText(mathControl.allData.getQuestionText());
			answer.setText(null);backPanel.remove(tp);
			tp.w=0;
			p = new MathPieChart();
			p.getChartPanel().setBounds(2, 120, 300, 300);
			scorePanel.add(p.getChartPanel());
			scorePanel.repaint();
			backPanel.add(tp);
			backPanel.repaint();
			timer.stop();
			timer = new Timer(14000,taskPerformer);
			timer.start();
		}

		}
	}

	class MathNextListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			changePage();
		} 
	}

}


