package View;
	import java.awt.*;

import Controller.GameUtil;



@SuppressWarnings("serial")
public class FlagPanel extends FlagGameView{
	GameUtil gUtil = new GameUtil();
	PaintThread1 pt1 = new PaintThread1();
	public int i;
	
		public FlagPanel(){
	        //draw the time bar
			
			this.setBounds(0,20,500,200);
			this.setLayout(null);
	        pt1.start();	        
		}
	   
		@Override
		public void paint(Graphics g) {
			//super.paintComponent(g);
			Image flagPic = GameUtil.getImage("images/country" + i + ".png");
			g.drawImage(flagPic, 21, 12, null);

		}
			    
		class PaintThread1 extends Thread{

    	  public void run(){
    		while(true){ 
    			repaint();
    			try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		 }
    	  }	     	  
        }
   }
  


 	
   
   
   
    
    
    
    
