package mainpackage;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Tone {
	
	private static SourceDataLine sdl;
	
	public static void initAudioLine() throws LineUnavailableException {
        float sampleRate = 44100;
        AudioFormat af = new AudioFormat(sampleRate, 8, 1, true, false);
        sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();
    }
	
	public static void playTone(double hz, int msecs, double vol) throws LineUnavailableException {
        float sampleRate = 44100;
        byte[] buf = new byte[1];
        for (int i = 0; i < msecs * sampleRate / 1000; i++) {
            double angle = i / (sampleRate / hz) * 2.0 * Math.PI;
            buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
            sdl.write(buf, 0, 1);
        }
	}
	
	public static void closeAudioLine() {
		sdl.drain();
        sdl.stop();
        sdl.close();
	}
}
