import java.util.ArrayList;
import java.util.Random;

/**
 * Generates the pieces for the board.
 * 
 * @author Seth Tinglof
 * @version 1.2
 */
@SuppressWarnings("serial")
public class BoardGenerator
extends Random{
	
	ArrayList<Integer[]> usedLocations = new ArrayList<Integer[]>();	//Records the coordinates of all objects on the board
	
	private int size;		//Size of board
	
	/*Number of each object Present on board*/
	private int aIPlayers;
	private int rocks;
	private int shrubberies;
	
	private int UserPlayerX, UserPlayerY;								//Initial position of the user player.
	
	public BoardGenerator(
		int size,
		int aIPlayers,
		int rocks, 
		int shrubberies,
		int userPlayerX, 
		int userPlayerY){
			this.size        = 	   	  size;
			this.aIPlayers   = 	 aIPlayers;
			this.rocks       = 		 rocks;
			this.shrubberies = shrubberies;
			this.UserPlayerX = userPlayerX;
			this.UserPlayerY = userPlayerY;
			ImageResources.loadImages();
		}
	
	/**
	 * Generates the Displayable objects necessary 2D game board
	 * @param board
	 */
	public void generateBoard(ArrayList<Entity> board){
		this.generateUserPlayer(board);
		this.generateAIPlayers(board);
		this.generateRocks(board);
		this.generateShrubberies(board);
	}
	
	/**
	 * @return Integer[] with a length of 2 representing an x and y coordinate.
	 */
	private Integer[] generateLocation(){
		Integer[] position = new Integer[2];
		position[0] = new Integer(super.nextInt(size));
		position[1] = new Integer(super.nextInt(size));
		return position;
	}
	
	/**
	 * @return true or false randomly.
	 */
	public boolean randomBoolean(){
		if(nextInt(2) == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * Creates the User player objects and adds them to the parameter ArrayList.
	 * Should called before other generate methods
	 * @param board User is created on
	 */
	private void generateUserPlayer(ArrayList<Entity> board){
		Integer[] position = new Integer[2];
		position[0] = new Integer(UserPlayerX);
		position[1] = new Integer(UserPlayerY);
		usedLocations.add(position);
		board.add(new UserPlayer(position[0], position[1]));
	}
	
	/**
	 * Creates the AI player objects and adds them to the parameter ArrayList.
	 * @param board players are created on.
	 */
	private void generateAIPlayers(ArrayList<Entity> board){
		Integer[] currentLocation;
		
		for(int i = 0; i < aIPlayers; i++){
			currentLocation = this.generateLocation();
			if(!Util.integer_Array_ArrayList_Contains(usedLocations, currentLocation)){		//Ensures a new location is generated.
				usedLocations.add(currentLocation);
				board.add(new AIPlayer(currentLocation[0], currentLocation[1]));
			}
			else i--;																		//if location is already used, attempt to create Rock is repeated.
		}
	}
	
	/**
	 * Creates the Rock objects and adds them to the parameter ArrayList.
	 * @param board Rocks are created on.
	 */
	private void generateRocks(ArrayList<Entity> board){
		Integer[] currentLocation;
		
		for(int i = 0; i < rocks; i++){
			currentLocation = this.generateLocation();
			if(!Util.integer_Array_ArrayList_Contains(usedLocations, currentLocation)){		//Ensures a new location is generated.
				usedLocations.add(currentLocation);
				board.add(new Rock(currentLocation[0], currentLocation[1], this.nextInt(3)));
			}
			else i--;																		//if location is already used, attempt to create Rock is repeated.
		}
	}
	
	/**
	 * Creates the Shrubbery objects and adds them to the parameter ArrayList.
	 * @param board Shrubberies are created on.
	 */
	private void generateShrubberies(ArrayList<Entity> board){
		Integer[] currentLocation;
		
		for(int i = 0; i < shrubberies; i++){
			currentLocation = this.generateLocation();
			if(!Util.integer_Array_ArrayList_Contains(usedLocations, currentLocation)){		//Ensures a new location is generated.
				usedLocations.add(currentLocation);
				board.add(new Shrubbery(currentLocation[0], currentLocation[1]));
			}
			else i--;																		//if location is already used, attempt to create Rock is repeated.
		}
	}
}