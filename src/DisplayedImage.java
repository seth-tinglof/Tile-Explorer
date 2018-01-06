import java.awt.Graphics;		//Used to override paintComponent.
import javax.swing.JPanel;		//Object is displayed on a frame.

@SuppressWarnings("serial")
public class DisplayedImage 
extends JPanel{
	
	private Displayable displayable;
	
	public DisplayedImage(Displayable displayable){
		this.displayable = displayable;
	}
	
	/**
	 * Sets displayable variable to parameter's value.
	 * @param displayable
	 */
	public void setDisplayable(Displayable displayable){
		this.displayable = displayable;
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(displayable.getImage(),
		getWidth()/2 - displayable.getImage().getWidth(this)/2,		//centers image horizontally in gridLayout cell. 
		getHeight()/2 - displayable.getImage().getHeight(this)/2, 	//centers image vertically in gridLayout cell. 
		this);
	}
}
