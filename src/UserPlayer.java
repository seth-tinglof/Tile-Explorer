import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class UserPlayer 
extends Player{

	static final int attackDamage = 34;
	Image image;
	
	public UserPlayer(int xPos, int yPos) {
		super(xPos, yPos);
		try{
			image = ImageIO.read(new File("Player.png"));
		}catch(IOException e){}
	}
	
	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public void damage(Entity entity) {
		if(entity instanceof Character){
			Sound.enemyHit();
			((Character) entity).subtractHealth(attackDamage);
		}
		
		else{
			if(entity instanceof InanimateObject){
				((InanimateObject) entity).breakObject();
				if(entity instanceof Shrubbery)
					setHealth(100);
			}
		}
	}
}