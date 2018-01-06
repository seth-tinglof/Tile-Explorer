
/**
 * Abstract class for an Inanimate Object to be used in a game.
 * Inanimate objects can be breakable or not.
 * 
 * @author Seth Tinglof
 * @version 1.0
 */
public abstract class InanimateObject
extends Entity{
	private boolean breakable;
	
	public InanimateObject(int xPos, int yPos, boolean breakable)
	{
		super(xPos, yPos);
		this.breakable = breakable;
	}
	
	/**
	 * If object is breakable, break object.
	 * It will no longer be shown or be active.
	 */
	public void breakObject(){
		if(breakable){
			super.setShown(false);
			super.setActive(false);
		}
	}
	
	/**
	 * @return String with objects position and whether object is breakable.
	 */
	@Override
	public String toString(){
		return super.toString() + "Breakable: " + breakable + "\nBroken: " + !(getActive()) + "\n";
	}
}