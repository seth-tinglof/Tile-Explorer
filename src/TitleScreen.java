import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Title screen for game.
 * Shown on startup.
 * @author Seth Tinglof
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TitleScreen 
extends ButtonPanel {
	
	public TitleScreen(){
		add(okayButton);
		add(title);
		title.setBounds(0, 0, 920, 720);
		okayButton.setBounds(400, 500, 75, 75);

		okayButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setOpen(false);
                synchronized(lock){
                	lock.notifyAll();
                }
            }
        });
	}
}
