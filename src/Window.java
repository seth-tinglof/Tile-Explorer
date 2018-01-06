import java.awt.Insets;    //used to get size of insets
import javax.swing.JFrame;

/**
 * Frame that the game is displayed in.
 * 
 * @author Seth Tinglof
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Window
extends JFrame{
	
	Game game;
	Hud hud;
	Insets insets;
	
	public Window(){
		while(true){
			setupWindow();
			displayPanel(new TitleScreen());
			displayPanel(new Instructions());
			
			game = new Game(3, 3, 200, 1200, 1200, 500, this);
			game.setBounds(0, 0, 720, 720);
			add(game);
			
			hud = new Hud(game);
			hud.setBounds(720, 0, 200, 720);
			add(hud);
			game.setHud(hud);
			
			revalidate();
			repaint();
			
			game.play();
			remove(game);
			remove(hud);
		    displayPanel(new GameOver());
	    }
	}
	
	/**
	 * Setup frame to be displayed.
	 */
	public void setupWindow(){
		setResizable(false); //resizable must be set before bounds.  This method has strange issues.
		setLayout(null);
		setVisible(true);
		insets = getInsets();
		setSize(920 + insets.right + insets.left, 720 + insets.top + insets.bottom);  //insets added to frame size
		setLocationRelativeTo(null);
		
		/*JVM terminates when window is closed */
		addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	System.exit(0);
            }
        });
	}
	
	/**
	 * displays ButtonPanel to user.
	 */
	public void displayPanel(ButtonPanel panel){
		add(panel);
		revalidate();
		repaint();
		synchronized(panel.lock){
			while(panel.isOpen()){
				try {
					panel.lock.wait();
				} catch (Exception e) {}
			}
		}
		remove(panel);
	}
}