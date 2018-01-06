import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * Empty, Unused Space.
 * All grass are identical, so only one object is needed.
 * 
 * @author Seth Tinglof
 * @version 1.0
 */
public class Grass 
implements Displayable{
	Image image;
	
	public Grass(){
		try{
			image = ImageIO.read(new File("grass.png"));
		}catch(IOException e){}
	}
	@Override
	public Image getImage() {
		return image;
	}

}
