import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Instructions for game.
 * 
 * @author Seth Tinglof
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Instructions 
extends ButtonPanel {
	public Instructions(){
		add(okayButton);
		okayButton.setBounds(700, 650, 75, 60);
		add(instructions);
		instructions.setBounds(0, 0, 920, 720);
		setVisible(true);
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
