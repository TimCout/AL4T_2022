package trafficsim;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.InputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SoundManager {
	private static final Logger logger = Logger.getLogger(SoundManager.class.getName());
	Clip clip;
	Clip driftClip;

	public SoundManager() {
		try {
			// Vérification et chargement du premier son
			InputStream inputStream = getClass().getResourceAsStream("/Traffic Sounds - Free Sound Effects - Traffic Sound Clips - Sound Bites.wav");
			if (inputStream == null) {
				throw new IOException("Resource '/Traffic Sounds - Free Sound Effects - Traffic Sound Clips - Sound Bites.wav' not found");
			}
			AudioInputStream soundStream = AudioSystem.getAudioInputStream(inputStream);
			clip = AudioSystem.getClip();
			clip.open(soundStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);

			// Vérification et chargement du son de drift
			InputStream driftStreamInput = getClass().getResourceAsStream("/drift.wav");
			if (driftStreamInput == null) {
				throw new IOException("Resource '/drift.wav' not found");
			}
			AudioInputStream driftStream = AudioSystem.getAudioInputStream(driftStreamInput);
			driftClip = AudioSystem.getClip();
			driftClip.open(driftStream);
			driftClip.start();

			// Garder le programme en marche
			Thread.sleep(100000000); // looping as long as this thread is alive
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to load audio", e);
		}
	}
}