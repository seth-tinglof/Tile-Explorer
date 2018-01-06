import java.awt.Image;					

/**
 * A Rock object for a game on a 2D grid.
 * 
 * @author Seth Tinglof
 * @version 1.2
 */
public class Rock 
extends InanimateObject{
	private int size;
	private Image image;
	
	/**
	 * Creates a rock that can be breakable with a set size.
	 * Sets image for rock based on its size.
	 * If Rock has a size of 2 or more, it is not breakable.
	 * @param breakable
	 * @param size
	 */
	public Rock(int xPos, int yPos, int size){
		super(xPos, yPos, size < 2);
		this.size = size;
		setImage();
	}
	
	/**
	 * Sets image for rock based on size.
	 */
	public void setImage(){
		switch(size){
			case 0: image = ImageResources.rock1;
			break;
			
			case 1: image = ImageResources.rock2;
			break;
			
			case 2: image = ImageResources.rock3;
			break;
		}
	}

	@Override
	public Image getImage() {
		return image;
	}

	/**
	 * @return String with Rock's position, whether it is breakable, and its size.
	 */
	@Override
	public String toString() {
		return super.toString() + "Size: " + size + "\n";
	}
	
	@Override
	public void breakObject(){
		super.breakObject();
		Sound.rockHit();
	}
}
