import java.awt.Color;				 //set background color
import java.awt.GridLayout;		     //GridLayout is used as a layout manager.
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;   //allows player to take a turn and move their character.
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;			 //The Displayable objects are stored in an arrayList.

import javax.swing.JPanel;		     //Game class extends JPanel in order to be displayed
import javax.swing.SwingUtilities;

/**
 * Game object for inheritance project game.
 * Extends JPanel in order to show a board for the game.
 * 
 * @author Seth Tinglof
 * @version 1.1
 */
@SuppressWarnings("serial")
public class Game extends JPanel implements KeyListener{
	
	/*Constants for scan and sword attack moves.*/
	static final int SHORT_RANGE_SCAN = 10;
	static final int LONG_RANGE_SCAN  = 11;
	static final int SWORD = 12;
	
	private UserPlayer player;
	
	private int size;			//Size of the board used in the game.
	
	/*Number of each of these entities originally on on the board */
	private int enemies;
	private int rocks;
	private int shrubs;
	
	private int enemiesKilled = 0;
	
	/*User's starting position*/
	private int startingX, startingY;
	
	private boolean dead;				//true when user dies.
	
	private boolean takingTurn;			//true when game is waiting for user to take a turn.
	private int move;				    //move user has elected to take.
	private boolean hasSword;
	
	static final Displayable GRASS = new Grass();	//only one grass object is necessary; improves performance.
	
	private Hud hud;
	private Window window;				//Game tells window when to repaint and revalidate
	
	ArrayList<Entity> boardPieces = new ArrayList<Entity>();	//every entity displayed active in the game.
	DisplayedImage[][] displayedImages;							//the images displayed for the game.
	
	public Game(int startingX, int startingY, int size, int enemies, int rocks, int shrubs, Window window){
		this.dead 	  = false;
		this.hasSword = false;
		
		this.takingTurn = false;
		
		/*User's position*/
		this.startingX = startingX;
		this.startingY = startingY;
		
		this.size 	 =    size;
		
		/*Number of each of these things in the game*/
		this.enemies = enemies;		  //Enemies are AIPlayer Objects
		this.rocks   =   rocks;
		this.shrubs  =  shrubs;		  //Shrubbery Objects
		
		this.window = window;
		
		setupPanel();
		generateBoard();
	}
	
	/**
	 * Sets up panel to be ready to be displayed.
	 */
	public void setupPanel(){
		setLayout(new GridLayout(11, 11, 0, 0));
		setVisible(true);
		displayedImages = new DisplayedImage[size][size];
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
	}
	
	public void setHud(Hud hud){
		this.hud = hud;
	}
	
	/**
	 * Creates entities and places them at the correct position on the board.
	 */
	public void generateBoard(){
		BoardGenerator boardGen = new BoardGenerator(size, enemies, rocks, shrubs, startingX, startingY);
		boardGen.generateBoard(boardPieces);
		player = (UserPlayer) boardPieces.get(0);
		
		/*Create DisplayedImage objects and add them to the grid layout board*/
		for(int i = 0; i < displayedImages.length; i++){
			for(int j = 0; j < displayedImages[i].length; j++)
				displayedImages[i][j] = new DisplayedImage(GRASS);
		}
		updateDisplayedImages();
	}
	
	/**
	 * Allows user to take turns and play the game.
	 */
	public void play(){
		super.requestFocus();
		while(!dead){
			this.playerMove();
			this.enemyMove();
			this.removeInactive();
			this.updateDisplayedImages();
		}
	}
	
	/**
	 * Removes inactive entities from boardPieces
	 */
	public void removeInactive(){
		int numRemoved = 0;
		for(int i = 0; i < boardPieces.size() + numRemoved; i++){
			if(!boardPieces.get(i - numRemoved).getActive()){
				boardPieces.remove(i - numRemoved);
				numRemoved++;
			}
		}
		
		if(!player.getActive())
			dead = true;
	}
	
	/**
	 * updates displayed images to match the boardPieces
	 */
	public void updateDisplayedImages(){
		
		/*Variables used to show extra board pieces in X or Y direction only when player is near map edge.*/
		int startEarlyX = 0;
		int startEarlyY = 0;
		int endLateX    = 0;
		int endLateY    = 0;
		
		/*determines if player is near map edge and extra board pieces should be displayed.*/ 
		if(player.getXPos() - 5 < 0)
			endLateX = -(player.getXPos() - 5);
		if(player.getYPos() - 5 < 0)
			endLateY = -(player.getYPos() - 5);
		if(player.getXPos() + 5 >= size)
			startEarlyX = player.getXPos() + 5 - (size - 1);
		if(player.getYPos() + 5 >= size)
			startEarlyY = player.getYPos() + 5 - (size - 1);
		
		/*Reset visible pieces to grass*/
		for(int i = player.getXPos() - 6 - startEarlyX; i <= player.getXPos() + 6 + endLateX; i++){
			if(i >= 0 && i < size){											//prevents array out of bounds
				for(int j = player.getYPos() - 6 - startEarlyY; j <= player.getYPos() + 6 + endLateY; j++){
					if(j >= 0 && j < size){									//prevents array out of bounds
						remove(displayedImages[i][j]);
						displayedImages[i][j].setDisplayable(GRASS);
					}
				}
			}
		}
		
		/*Changes image on DisplayedImage to the image on the corresponding board piece.*/
		for(int i = 0; i < boardPieces.size(); i++){
			if(boardPieces.get(i).getShown())
				displayedImages[boardPieces.get(i).getXPos()][boardPieces.get(i).getYPos()].setDisplayable(boardPieces.get(i));
		}
		
		for(int i = player.getYPos() - 5 - startEarlyY; i <= player.getYPos() + 5 + endLateY; i++){
			if(i >= 0 && i < size)		   												    //Prevents array out of bounds
				for(int k = player.getXPos() - 5 - startEarlyX; k <= player.getXPos() + 5 + endLateX; k++){
					if(k >= 0 && k < size)   												//Prevents array out of bounds
						add(displayedImages[k][i]);
				}
		}
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					window.revalidate();
					window.repaint();
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {}
	}

	/**
	 * Counts and displays number of each type of entity around the player.
	 * Entities must be within 15 spaces of the player's x and y coordinates.
	 */
	public void shortRangeScan(){
		int enemies = 0;
		int rocks = 0;
		int shrubs = 0;
		
		for(int i = 1; i < boardPieces.size(); i++){
			
			/*Checks if Entity is near the player*/
			if(boardPieces.get(i).getXPos() <= player.getXPos() + 15 && boardPieces.get(i).getXPos() >= player.getXPos() - 15 &&
			   boardPieces.get(i).getYPos() <= player.getYPos() + 15 && boardPieces.get(i).getYPos() >= player.getYPos() - 15){
				if(boardPieces.get(i) instanceof AIPlayer)
					enemies++;
				else if(boardPieces.get(i) instanceof Rock)
					rocks++;
				else if(boardPieces.get(i) instanceof Shrubbery)
					shrubs++;
			}
		}
		hud.shortRangeScan(enemies, shrubs, rocks);
	}
	
	/**
	 * Counts and displays number of each type of Entity on the board.
	 */
	public void longRangeScan(){
		int enemies = 0;
		int rocks = 0;
		int shrubs = 0;
		
		for(int i = 1; i < boardPieces.size(); i++){
			if(boardPieces.get(i) instanceof AIPlayer)
				enemies++;
			else if(boardPieces.get(i) instanceof Rock)
				rocks++;
			else if(boardPieces.get(i) instanceof Shrubbery)
				shrubs++;
		}
		hud.longRangeScan(enemies, shrubs, rocks);
	}
	
	/**
	 * checks if player has earned a sword
	 */
	public void earnedSword(){
		if(enemiesKilled >= 5 && !hasSword){
			hasSword = true;
			hud.weapons += "Sword";
		}
	}
	
	/**
	 * waits for the user to select a move.
	 * moves user in selected direction.
	 */
	public synchronized void playerMove(){
		takingTurn = true;
		
		while(takingTurn){
			try {
				 super.wait();
			} catch (InterruptedException e) {}
		}
		
	    player.move(move);
	    stopPieceLeavingBoard(player);
	    
	    /*Player will not move if it hits another entity (it will, however, damage it). */
	    if(userHitEntity()){
	    	player.unmove(move);
	    	stopPieceLeavingBoard(player);
	    }
	    if(move == 12)
	    	swordAttack();
	    move = -1;						   //resets the players move choice to an unused value for next turn.
	}
	
	/**
	 * UserPlayer damages any other entity in the same location as it.
	 * @return true if another Entity is in the same location as the player.
	 */
	public boolean userHitEntity(){
		for(int i = 1; i < boardPieces.size(); i++){
			if(Util.integerArraysEqual(player.getCoordinates(), boardPieces.get(i).getCoordinates())){
				player.damage(boardPieces.get(i));
				if(boardPieces.get(i) instanceof AIPlayer && ((AIPlayer) boardPieces.get(i)).getHealth() <= 0){
					enemiesKilled++;
					earnedSword();
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Moves enemies on the board.
	 */
	public void enemyMove(){
		for(int i = 0; i < boardPieces.size(); i++)
			if(boardPieces.get(i) instanceof AIPlayer){
				int moveType = ((AIPlayer) boardPieces.get(i)).move();
				stopPieceLeavingBoard(boardPieces.get(i));
				if(enemyHitEntity((AIPlayer)boardPieces.get(i))){
					((AIPlayer) boardPieces.get(i)).unmove(moveType);
					stopPieceLeavingBoard(boardPieces.get(i));
				}
			}
	}
	
	/**
	 * If the argument AIPlayer is at the same position as a UserPlayer or InanimateObject.
	 * then the AIPlayer damages that Entity.
	 * @param enemy
	 * @return true if an entity is at the same location as enemy.
	 */
	public boolean enemyHitEntity(AIPlayer enemy){
		for(int i = 0; i < boardPieces.size(); i++){
			if(Util.integerArraysEqual(enemy.getCoordinates(), boardPieces.get(i).getCoordinates()) && enemy != boardPieces.get(i)){
				if(boardPieces.get(i) instanceof UserPlayer){
					enemy.damage(boardPieces.get(i));
				}
				return true;
			}	
		}
		return false;
	}
	
	/**
	 * Player attacks with a sword.
	 */
	public void swordAttack(){
		Sword sword = new Sword(player.getXPos(), player.getYPos());
		for(int i = 1; i <= 8; i++){
			sword.move(i);
			for(int j = 0; j < boardPieces.size(); j++)
				if(Util.integerArraysEqual(sword.getCoordinates(), boardPieces.get(j).getCoordinates())){
					sword.damage(boardPieces.get(j));
					if(boardPieces.get(j) instanceof AIPlayer)
						enemiesKilled++;
				}
				if(i >= 7)
					sword.swordImage = 4;
				else if(i >= 5)
					sword.swordImage = 3;
				else if(i >= 3)
					sword.swordImage = 2;
				if(sword.getXPos() > 0 && sword.getXPos() < size && sword.getYPos() > 0 && sword.getYPos() < size) //prevent array out of bounds
					displayedImages[sword.getXPos()][sword.getYPos()].setDisplayable(sword);
				try {
					SwingUtilities.invokeAndWait(new Runnable() {
						
						@Override
						public void run() {
							window.revalidate();
							window.repaint();
						}
					});
				} catch (InvocationTargetException | InterruptedException e) {}
				Util.pause(100);
				if(sword.getXPos() > 0 && sword.getXPos() < size && sword.getYPos() > 0 && sword.getYPos() < size) //prevent array out of bounds
					displayedImages[sword.getXPos()][sword.getYPos()].setDisplayable(GRASS);
				sword.unmove(i);
		}
	}

	/**
	 * Causes any board piece that leaves the grid go back to stay at the grids edge.
	 * @Param piece
	 */
	public void stopPieceLeavingBoard(Entity piece){
		if(piece.getXPos() >= size){
			piece.setXPos(size - 1);
		}
		else if(piece.getXPos() < 0){
			piece.setXPos(0);
		}
		if(piece.getYPos() >= size){
			piece.setYPos(size - 1);
		}
		else if(piece.getYPos() < 0){
			piece.setYPos(0);
		}
	}
	
	public int getHealth(){
		return player.getHealth();
	}
	
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		if(takingTurn){
			if(e.getKeyCode() == KeyEvent.VK_W){
				move = Player.UP;
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_E){
				move = Player.UP_RIGHT;
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_D){
				move = Player.RIGHT;
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_C){
				move = Player.DOWN_RIGHT;
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_X){
				move = Player.DOWN;
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_Z){
				move = Player.DOWN_LEFT;
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_A){
				move = Player.LEFT;
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_Q){
				move = Player.UP_LEFT;
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_S){
				move = SHORT_RANGE_SCAN;
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_SPACE){
				move = LONG_RANGE_SCAN;
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_R && hasSword){
				move = SWORD;
			}
		}
	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {
		if(takingTurn){
			if(e.getKeyCode() == KeyEvent.VK_W && move == Player.UP){
				takingTurn = false;
				super.notifyAll();
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_E && move == Player.UP_RIGHT){
				takingTurn = false;
				super.notifyAll();
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_D && move == Player.RIGHT){
				takingTurn = false;
				super.notifyAll();
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_C && move == Player.DOWN_RIGHT){
				takingTurn = false;
				super.notifyAll();
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_X && move == Player.DOWN){
				takingTurn = false;
				super.notifyAll();
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_Z && move == Player.DOWN_LEFT){
				takingTurn = false;
				super.notifyAll();
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_A && move == Player.LEFT){
				takingTurn = false;
				super.notifyAll();
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_Q && move == Player.UP_LEFT){
				takingTurn = false;
				super.notifyAll();
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_S && move == SHORT_RANGE_SCAN){
				this.shortRangeScan();
				takingTurn = false;
				super.notifyAll();
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_SPACE && move == LONG_RANGE_SCAN){
				this.longRangeScan();
				takingTurn = false;
				super.notifyAll();
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_R && move == SWORD){
				takingTurn = false;
				super.notifyAll();
			}
		}
	}

	/**
	 * not used, but necessary for key listener interface.
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
}