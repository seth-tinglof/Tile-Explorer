import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Displays HUD information such as health.
 * 
 * @author Seth Tinglof
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Hud 
extends JPanel{
	int nearEnemies;
	int nearShrubberies;
	int nearRocks;
	
	int enemies = 0;
	int shrubberies = 0;
	int rocks = 0;
	
	String weapons = "";
	
	
	static final Font FONT1 = new Font("SansSerif", Font.PLAIN, 28);
	static final Font FONT2 = new Font("SansSerif", Font.PLAIN, 18);
	
	private Game game;
	
	public Hud(Game game){
		this.game = game;
	}
	
	/**
	 * Updates number of each type of entity near player.
	 * @param enemies number of nearby AIPlayer.
	 * @param shrubberies number of nearby Shrubbery.
	 * @param rocks number of nearby Rock.
	 */
	public void shortRangeScan(int enemies, int shrubberies, int rocks){
		this.nearEnemies = enemies;
		this.nearShrubberies = shrubberies;
		this.nearRocks = rocks;
	}

	/**
	 * Updates the number of each type of entity.
	 * @param enemies on the board.
	 * @param shruberries on the board.
	 * @param rocks on the board.
	 */
	public void longRangeScan(int enemies, int shruberries, int rocks){
		this.enemies = enemies;
		this.shrubberies = shruberries;
		this.rocks = rocks;
	}
	
	@Override
	public void paintComponent(Graphics g){
		/*displays health */
		g.setFont(FONT1); 
		g.drawString("Health: " + game.getHealth(), 30, 40);
		
		/*displays short range scan */
		g.setFont(FONT2);
		g.drawString("Nearby Enemies: ", 10, 100);
		g.drawString(Integer.toString(nearEnemies), 10, 125);
		g.drawString("Nearby Shrubberies: ", 10, 150);
		g.drawString(Integer.toString(nearShrubberies), 10, 175);
		g.drawString("Nearby Rocks: ", 10, 200);
		g.drawString(Integer.toString(nearRocks), 10, 225);
		
		/*displays long range scan */
		g.drawString("Enemies: ", 10, 300);
		g.drawString(Integer.toString(enemies), 10, 325);
		g.drawString("Shrubberies: ", 10, 350);
		g.drawString(Integer.toString(shrubberies), 10, 375);
		g.drawString("Rocks: ", 10, 400);
		g.drawString(Integer.toString(rocks), 10, 425);
		
		g.drawString("Weapons: ", 10, 500);
		g.drawString(weapons, 10 ,525);
	}
} 
