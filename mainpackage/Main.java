package mainpackage;

import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;

public class Main {

	public static void main(String[] args) throws LineUnavailableException {
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		while(!exit) {
			System.out.println("Encode (type \"E\"), Decode (type \"D\") or exit (type \"X\")");
			String mode = sc.nextLine();
			System.out.println(mode);
			if(mode.contains("X")) {
				System.out.println("Exiting...");
				exit = true;
			}else if(mode.contains("E") || mode.contains("D")){
				System.out.println("Select a key: ");
				String keyInput = sc.nextLine();
				// TODO: check if valid key
				Key key = new Key(keyInput);
				/*Tone.initAudioLine();
				key.playScale();
				Tone.closeAudioLine();*/
				System.out.println("Key chosen: "+keyInput);
				if(keyInput.contains("X")) {
					System.out.println("Exiting...");
					exit = true;
				}else if(mode.contains("E")) {
					System.out.println("Input natural language message: ");
					String message = sc.nextLine();
					String tones = key.encode(message);
					System.out.println(tones);
					Tone.initAudioLine();
					key.playTones(tones);
					Tone.closeAudioLine();
				}else if(mode.contains("D")) {
					System.out.println("Input encoded message: ");
					String tones = sc.nextLine();
					String message = key.decode(tones);
					System.out.println(message);
				}else {
					System.out.println("Invalid input.");
				}
			}else {
				System.out.println("Invalid input.");
			}
		}

	}

}
