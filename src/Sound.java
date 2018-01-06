
import java.io.File;                //allows getting audio file.
import javax.sound.sampled.*;       //allows for audio playback 
/**
 * Methods for playing sound effects
 * 
 * @author Seth Tinglof 
 * @version 1.0
 */
public class Sound {
    
	 /**
     * plays sound for a rock being hit.
     */
    public static void rockHit()
    {
        try
        {
            Clip clip;
            File file;
            AudioInputStream audioInput; 
            file = new File("RockHit.wav");
            audioInput = AudioSystem.getAudioInputStream(file);        //creates audio input stream from the audio file
            clip = AudioSystem.getClip();                              //creates audio clip
            clip.open(audioInput);      
            clip.start();
        }catch(Exception e) {}
    }
    
    /**
     * plays sound for a Shrubbery being hit.
     */
    public static void shrubHit()
    {
        try
        {
            Clip clip;
            File file;
            AudioInputStream audioInput; 
            file = new File("ShrubHit.wav");
            audioInput = AudioSystem.getAudioInputStream(file);        //creates audio input stream from the audio file
            clip = AudioSystem.getClip();                              //creates audio clip
            clip.open(audioInput);      
            clip.start();
        }catch(Exception e) {}
    }
	
	/**
     * plays sound for an enemy being hit.
     */
    public static void enemyHit()
    {
        try
        {
            Clip clip;
            File file;
            AudioInputStream audioInput; 
            file = new File("enemyHit.wav");
            audioInput = AudioSystem.getAudioInputStream(file);        //creates audio input stream from the audio file
            clip = AudioSystem.getClip();                              //creates audio clip
            clip.open(audioInput);      
            clip.start();
        }catch(Exception e) {}
    }

    /**
     * plays sound for the player being hit.
     */
    public static void playerHit()
    {
        try
        {
            Clip clip;
            File file;
            AudioInputStream audioInput; 
            file = new File("PlayerHit.wav");
            audioInput = AudioSystem.getAudioInputStream(file);        //creates audio input stream from the audio file
            clip = AudioSystem.getClip();                              //creates audio clip
            clip.open(audioInput);      
            clip.start();
        }catch(Exception e) {}
    }
}