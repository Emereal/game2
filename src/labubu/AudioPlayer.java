package labubu;

import javax.sound.sampled.*;
import java.io.File;

public class AudioPlayer {
	
	private static Clip clip;
	
	public void playSong(String file) {
		try {
			File song = new File(file);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(song);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\n\nFile not found!");
		}
	}
	
	public static void stop() {
		if (clip != null && clip.isRunning()) {
			clip.stop();
			clip.close();
		}
	}

}
