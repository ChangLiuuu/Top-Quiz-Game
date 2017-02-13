package Controller;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class GameUtil {
	
      public static Image getImage(String path){
    	  URL u = GameUtil.class.getClassLoader().getResource(path);
    	  BufferedImage img = null;
    	  try {
			img = ImageIO.read(u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
    	  
      }
      
      // select the flag randomly
      static int i;
      String photopath;
      public String flagPath(){
      
          // country0.png ~ 4.png
          photopath = "images/country" + (i++) + ".png";
      
		return photopath;
        
      }
      

}
