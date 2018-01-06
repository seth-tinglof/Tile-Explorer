
/**
 * Character or Object with a position that can be shown or not shown.
 * 
 * @author Seth Tinglof
 * @version 1.0
 */
public abstract class Entity
implements Displayable
{
	private int xPos;
	private int yPos;

	private boolean shown;
	private boolean active;
	
	public Entity(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;		
		active = true;
		shown = true;
	}
	
   /**
	* @return true if object is shown.
	*/
	public boolean getShown(){
		return shown;
	}
	
	public void setShown(boolean shown){
		this.shown = shown;
	}
	
	/**
	 * @return active
	 */
	public boolean getActive(){
		return active;
	}
	
	/**
	 * sets value for active
	 * @param active
	 */
	public void setActive(boolean active){
		this.active = active;
	}
	
   /**
	* @return x position for display in grid
	*/
	public int getXPos(){
		return xPos;
	}
	
	/**
	 * Sets value for xPos.
	 * @param xPos
	 */
	public void setXPos(int xPos){
		this.xPos = xPos;
	}
	
   /**
	* @return y position for display in grid
	*/
	public int getYPos(){
		return yPos;
	}
	
	/**
	 * Sets value for yPos
	 * @param yPos
	 */
	public void setYPos(int yPos){
		this.yPos = yPos;
	}
	
	/**
	 * @return x and y position for Entity in an Integer array.
	 */
	public Integer[] getCoordinates(){
		return new Integer[]{new Integer(xPos), new Integer(yPos)};
	}
	
	/**
	 * @return String with Entity's position
	 */
	public String toString(){
		return "X Position: " + xPos + "\nY Position: " + yPos + "\n";
	}
}