package mainpackage;

import javax.sound.sampled.LineUnavailableException;

public class Key {
	int key;
	double C=256, D=288, E=323.13, F=341.33, G=384, A=431.04, B=483.29;
	double Db=271.83, Eb=304.71, Gb=362.04, Ab=406.91, Bb=456.53;
	
	public Key(String key) {
		switch(key) {
		case("Cmaj"):
			this.key = 0;
			break;
		case("Am"):
			this.key = 0;
			break;
		case("Fmaj"):
			this.key = 1;
			break;
		case("Dm"):
			this.key = 1;
			break;
		case("Bbmaj"):
			this.key = 2;
			break;
		case("Gm"):
			this.key = 2;
			break;
		case("Ebmaj"):
			this.key = 3;
			break;
		case("Cm"):
			this.key = 3;
			break;
		case("Abmaj"):
			this.key = 4;
			break;
		case("Fm"):
			this.key = 4;
			break;
		case("Dbmaj"):
			this.key = 5;
			break;
		case("Bbm"):
			this.key = 5;
			break;
		case("Gbmaj"):
			this.key = 6;
			break;
		case("Ebm"):
			this.key = 6;
			break;
		case("Abm"):
			this.key = 7;
			break;
		default:
			System.out.println("invalid key");
			
		}
		setNotes();
	}
	
	public void setNotes() {
		if(this.key>=1) {
			B=Bb;
			if(this.key>=2) {
				E=Eb;
				if(this.key>=3) {
					A=Ab;
					if(this.key>=4) {
						D=Db;
						if(this.key>=5) {
							G=Gb;
							if(this.key>=6) {
								C=B; // Cb
								if(this.key>=7) {
									F=E; // Fb
								}
							}
						}
					}
				}
			}
		}
		
	}
	
	public void playScale() throws LineUnavailableException {
		Tone.playTone(C, 500, 0.4);
		Tone.playTone(D, 500, 0.4);
		Tone.playTone(E, 500, 0.4);
		Tone.playTone(F, 500, 0.4);
		Tone.playTone(G, 500, 0.4);
		Tone.playTone(A, 500, 0.4);
		Tone.playTone(B, 500, 0.4);
	}
	
	public String encrypt(String message) {
		String tones = "";
		//char[] tonesChar = tones.toCharArray();
		int temp;
		for(int i=0; i<message.length(); i++) {
			temp = message.charAt(i);
			System.out.println("Char ascii: "+temp+"\n");
			String result;
			String chString = Integer.toString(temp);
			if(temp<=122 && temp>=97) {
				// convert to base 4 (ignore earlier overthinking, this DOES work)
				result = Integer.toString(Integer.parseInt(chString, 10), 4);
				System.out.println(result);
				// convert to notes
				for(int j=0; j<result.length(); j++) {
					switch(result.charAt(j)) {
						case('0'):
							tones = tones.concat("F");
							break;
						case('1'):
							tones = tones.concat("G");
							break;
						case('2'):
							tones = tones.concat("A");
							break;
						case('3'):
							tones = tones.concat("B");
							break;
						default:
							System.out.println("why are you not entering the cases");
					}
				}
			}else if(temp<=90 && temp>=65) {
				// convert to base 3
				result = Integer.toString(Integer.parseInt(chString, 10), 3);
				// convert to notes
				for(int j=0; j<result.length(); j++) {
					switch(result.charAt(j)) {
						case('0'):
							tones = tones.concat("C");
							break;
						case('1'):
							tones = tones.concat("D");
							break;
						case('2'):
							tones = tones.concat("E");
							break;
					}
				}
			}
		}
		System.out.println(tones);
		return tones;
	}
	
	public String decrypt(String tones) {
		String message = "";
		char temp;
		String result = "";
		int i=0;
		while(i<tones.length()) {
			temp = tones.charAt(i);
			System.out.println("First tone in char group: "+temp+"\n");
			String toneVal = "0";
			String chString = Integer.toString(temp);
			if(temp=='F' || temp=='G' || temp=='A' || temp=='B') {
				for(int j=0; j<4; j++) {
					temp = tones.charAt(i);
					if(temp=='F') {
						toneVal = "0";
					}else if(temp=='G') {
						toneVal = "1";
					}else if(temp=='A') {
						toneVal = "2";
					}else if(temp=='B') {
						toneVal = "3";
					}else {
						System.out.println("Invalid lowercase tone");
					}
					i++;
					System.out.println("Tone "+j+": "+temp+"\n");
					message = message.concat(toneVal);
					System.out.println("String of toneVals: "+message);
				}
				message = Integer.toString(Integer.parseInt(message, 4), 10);
			}else if(temp=='C' || temp=='D' || temp=='E') {
				for(int j=0; j<4; j++) {
					temp = tones.charAt(i);
					if(temp=='C') {
						toneVal = "0";
					}else if(temp=='D') {
						toneVal = "1";
					}else if(temp=='E') {
						toneVal = "2";
					}else {
						System.out.println("Invalid uppercase tone");
					}
					i++;
					System.out.println("Tone "+j+": "+temp+"\n");
					message = message.concat(toneVal);
					System.out.println("String of toneVals: "+message);
				}
				message = Integer.toString(Integer.parseInt(message, 3), 10);
			}
			result = result.concat(message);
			message = "";
			System.out.println("Base 10 string so far: "+result);
		}
		i=0;
		String resultPT = "";
		while(i<result.length()) {
			String character = "";
			System.out.println(result.charAt(i));
			String charString = Character.toString(result.charAt(i));
			System.out.println(charString);
			if(result.charAt(i)=='1') {
				character = character.concat(charString);
				i++;
				charString = Character.toString(result.charAt(i));
				character = character.concat(charString);
				i++;
				charString = Character.toString(result.charAt(i));
				character = character.concat(charString);
				i++;
			}else {
				character = character.concat(charString);
				i++;
				charString = Character.toString(result.charAt(i));
				character = character.concat(charString);
				i++;
			}
			int charASCII = Integer.valueOf(character);
			char charPlainText = (char) charASCII;
			System.out.println("Letter: "+charPlainText);
			String strPT = Character.toString(charPlainText);
			resultPT = resultPT.concat(strPT);
		}
				
				// OLD CODE
				/*for(int j=0; j<result.length(); j++) {
					switch(result.charAt(j)) {
						case('0'):
							tones = tones.concat("F");
							break;
						case('1'):
							tones = tones.concat("G");
							break;
						case('2'):
							tones = tones.concat("A");
							break;
						case('3'):
							tones = tones.concat("B");
							break;
						default:
							System.out.println("why are you not entering the cases");
					}
				}
			}else if(temp<=90 && temp>=65) {
				// convert to base 3
				result = Integer.toString(Integer.parseInt(chString, 10), 4);
				// convert to notes
				for(int j=0; j<result.length(); j++) {
					switch(result.charAt(j)) {
						case('0'):
							tones = tones.concat("C");
							break;
						case('1'):
							tones = tones.concat("D");
							break;
						case('2'):
							tones = tones.concat("E");
							break;
					}
				}
			}
		}
		System.out.println(tones);*/
		return resultPT;
	}
	
	public void playTones(String tones) throws LineUnavailableException {
		for(int i=0; i<tones.length(); i++) {
			double tone = 100;
			switch(tones.charAt(i)) {
			case('F'):
				tone = F;
				break;
			case('G'):
				tone = G;
				break;
			case('A'):
				tone = A;
				break;
			case('B'):
				tone = B;
				break;
			case('C'):
				tone = C;
				break;
			case('D'):
				tone = D;
				break;
			case('E'):
				tone = E;
				break;
			}
			Tone.playTone(tone, 500, 0.4);
		}
	}
}
