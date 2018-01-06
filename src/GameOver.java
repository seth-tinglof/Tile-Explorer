import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * displays gameOver screen
 * @author Seth Tinglof
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GameOver 
extends ButtonPanel {
	public GameOver(){
		add(playAgain);
		add(quit);
		add(gameOver);
		
		playAgain.setBounds(370, 500, 150, 75);
		quit.setBounds(410, 600, 75, 75);
		gameOver.setBounds(0, 0, 920, 720);
		
		playAgain.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setOpen(false);
                synchronized(lock){
                	lock.notifyAll();
                }
            }
        });
		
		quit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               System.exit(0);
            }
        });
	}
}
