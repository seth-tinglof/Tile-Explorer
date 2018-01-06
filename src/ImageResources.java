import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Loads images into memory then allows them
 * to be accessed for future use.
 * 
 * @author Seth Tinglof
 * @version 1.0
 */
public class ImageResources {
	
	/* Images for different entities.
	 * Created here instead of in each entities class
	 * because only one of each Image is needed in memory.
	 */
	static Image aIPlayer;
	static Image shrubbery;
	static Image rock1;
	static Image rock2;
	static Image rock3;
	static Image sword1;
	static Image sword2;
	static Image sword3;
	static Image sword4;
	
	static ImageIcon title = new ImageIcon("Title.png");
	static ImageIcon instructions = new ImageIcon("instructions.png");
	static ImageIcon gameOver = new ImageIcon("GameOver.png");
	
	/**
	 * Loads images into memory.
	 */
	public static void loadImages(){
		try{
			aIPlayer = ImageIO.read(new File("AIPlayer.png"));
			shrubbery = ImageIO.read(new File("shrubbery.png"));
			rock1 = ImageIO.read(new File("Rock1.png"));
			rock2 = ImageIO.read(new File("Rock2.png"));
			rock3 = ImageIO.read(new File("Rock3.png"));
			sword1 = ImageIO.read(new File("sword1.png"));
			sword2 = ImageIO.read(new File("sword2.png"));
			sword3 = ImageIO.read(new File("sword3.png"));
			sword4 = ImageIO.read(new File("sword4.png"));
		}catch(IOException e){}
	}
}
