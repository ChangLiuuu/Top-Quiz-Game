package View;
	import java.awt.*;

import Controller.GameUtil;



@SuppressWarnings("serial")
public class TimePanel extends MathView{
	  PaintThread pt = new PaintThread();
	   Image youCan = GameUtil.getImage("images/Youcandoit.png");
       Image comeOn = GameUtil.getImage("images/Comeon.png");
       Image Timeisup = GameUtil.getImage("images/Timeisup.png");
		public TimePanel(){
	        //draw the time bar
			this.setBounds(160, 10, 800, 35);
			this.setLayout(null);
	        pt.start();	        
		}
	    
		public int w = 0;
		@Override
		public void paint(Graphics g) {
			//super.paintComponent(g);
			
			w = w + 2;
		 if(w<=300){
			g.drawRect(45, 1, 600, 33);
			g.drawString("Time",0, 18);
			g.setColor(Color.lightGray);
			g.fillRect(45, 1, w, 33);
			g.drawImage(youCan,700,0,null);
		 }else if(w<600 && w>300){
			g.drawRect(45, 1, 600, 33);
			g.drawString("Time",0, 18);
			g.setColor(Color.lightGray);
			g.fillRect(45, 1, w, 33);
			g.setColor(Color.red);
            g.drawString("Cheer up guys!",300,18);
            g.drawImage(comeOn, 700, 0, null);
	        
	        }else if(w >= 600){
	        	answer.setEditable(false);	
	        	g.drawRect(45, 1, 600, 33);
				g.drawString("Time",0, 18);
				g.setColor(Color.lightGray);
				g.fillRect(45, 1, 600, 33);
	        	g.setColor(Color.blue);
	        	g.drawString("Sorry, Time is up!",280,18);
	        	g.drawImage(Timeisup, 700, 0, null);
	        	
		 }
		}
			    
		class PaintThread extends Thread{

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
  


 	
   
   
   
    
    
    
    
