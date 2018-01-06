import java.awt.Image;

/**
 * An AI enemy in the inheritance project game.
 * 
 * @author Seth Tinglof
 * @version 1.2
 */
public class AIPlayer 
extends Player{

	static final int attackDamage = 20;
	
	public AIPlayer(int xPos, int yPos) {
		super(xPos, yPos);
	}

	/**
 	* AIPlayer performs a random move.
 	* @return int value of AIPlayer's move
 	*/
	public int move()
	{
		int moveType = ((int) (Math.random() * 8 + 1));
		super.move(moveType);
		return moveType;
	}
	
	@Override
	public Image getImage() {
		return ImageResources.aIPlayer;
	}

	/**
	 * Damages UserPlayer if hit by AI enemy.
	 */
	@Override
	public void damage(Entity entity) {
		if(entity instanceof UserPlayer){
			Sound.playerHit();
			((Character) entity).subtractHealth(attackDamage);
		}
	}
}
