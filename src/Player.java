/**
 * Abstract base for players in a game.
 * Players have the ability to move and attack.
 * 
 * @author Seth Tinglof
 * @version 1.1
 */
public abstract class Player 
extends Character{
	
	/* integer values for moveType in direction corresponding to name. */
	static final int UP = 2;
	static final int UP_RIGHT = 3;
	static final int RIGHT = 4;
	static final int DOWN_RIGHT = 5;
	static final int DOWN = 6;
	static final int DOWN_LEFT = 7;
	static final int LEFT = 8;
	static final int UP_LEFT = 1;
	
	static final int MOVE_DISTANCE = 1;		 //distance one move will take the player
	
	public Player(int xPos, int yPos) {
		super(xPos, yPos);
		setHealth(100);
	}
	
	/**
	 * Moves player in one of 8 surrounding directions.
	 * Direction moved depends on value for moveType entered
	 * as an argument.
	 * @param moveType
	 */
	public void move(int moveType){
		switch(moveType){
		case UP: 
				moveY(MOVE_DISTANCE);
				break;
				
		case UP_RIGHT: 
				moveY(MOVE_DISTANCE);
				moveX(MOVE_DISTANCE);
				break;
				
		case RIGHT: 
				moveX(MOVE_DISTANCE);
				break;
				
		case DOWN_RIGHT: 
				moveX(MOVE_DISTANCE);
				moveY(-MOVE_DISTANCE);
				break;
				
		case DOWN: 
				moveY(-MOVE_DISTANCE);
				break;
				
		case DOWN_LEFT: 
				moveY(-MOVE_DISTANCE);
				moveX(-MOVE_DISTANCE);
				break;
				
		case LEFT: 
				moveX(-MOVE_DISTANCE);
				break;
				
		case UP_LEFT: 
				moveY(MOVE_DISTANCE);
				moveX(-MOVE_DISTANCE);
				break;
		}
	}
	
	/**
	 * Inverse of move method.
	 * Will do the opposite of move method with the same argument.
	 * @param moveType
	 */
	public void unmove(int moveType){
		switch(moveType){
		case UP: 
				move(DOWN);
				break;
				
		case UP_RIGHT: 
				move(DOWN_LEFT);
				break;
				
		case RIGHT: 
				move(LEFT);
				break;
				
		case DOWN_RIGHT: 
				move(UP_LEFT);
				break;
				
		case DOWN: 
				move(UP);
				break;
				
		case DOWN_LEFT: 
				move(UP_RIGHT);
				break;
				
		case LEFT: 
				move(RIGHT);
				break;
				
		case UP_LEFT: 
				move(DOWN_RIGHT);
				break;
		}
	}
}
