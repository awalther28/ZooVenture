/**
 * 
 */
package ZooVenture;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * @author allisonwalther
 *
 */
public class Music {
	private Clip clip;
	
	public Music(String music)
	{
		try {
			File musicFile = new File("src/Sounds/"+music+".wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void stop(){
		        if(clip == null) return;
		        clip.stop();
		    }
		    	
    public void loop() {		
        try {		
            if (clip != null) {		
                new Thread() {		
                    public void run() {		
                        synchronized (clip) {
                            clip.stop();		
                            clip.setFramePosition(0);		
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
