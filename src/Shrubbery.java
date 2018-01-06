import java.awt.Image;

/**
 * Shrubbery displayed on the board.
 * If the UserPlayer hits it, the UserPlayer gets its health back.
 * 
 * @author Seth Tinglof
 * @version 1.2
 */
public class Shrubbery 
extends InanimateObject{
	

	public Shrubbery(int xPos, int yPos) {
		super(xPos, yPos, true);
	}

	@Override
	public Image getImage() {
		return ImageResources.shrubbery;
	}
	
	@Override
	public void breakObject(){
		super.breakObject();
		Sound.shrubHit();
	}
}
