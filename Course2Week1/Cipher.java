
public class Cipher {
	public String encrypt(String input, int key) {
		input = input.toUpperCase();
		StringBuilder encrypted = new StringBuilder(input);
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String shiftedAlpha = alpha.substring(key) +alpha.substring(0, key);
		
		for(int i = 0; i < encrypted.length(); i++) {
			char currChar = encrypted.charAt(i);
			int idx = alpha.indexOf(currChar);
			
			if(idx != -1) {
				char newChar = shiftedAlpha.charAt(idx);
				encrypted.setCharAt(i, newChar);
			}
		}
		return encrypted.toString();
	}
	
	public String encrypt2(String input, int key) {
		input = input.toUpperCase();
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String output = "";
		String newAlpha = alpha.substring(key)+alpha.substring(0, key);
		
		for(int i = 0; i<input.length(); i++) {
			//get the position of char in alphabet
			char letter = input.charAt(i);
			int letterPos = input.indexOf(letter);
			//if char in alphabet then
			if(letterPos!=-1) {
				//use position to get new letter in newAlpha
				char newLetter = newAlpha.charAt(letterPos);
				output = output+newLetter;
			}else {
				output = output+letter;
			}
		}
		return output;
	}
	public void testEncrypt() {
		String a = encrypt("HELLO", 2);
		System.out.println("a: "+a);
		
		//test-------------
		System.out.println("Reversed encrypt: "+ encrypt(a, 26-2));
	}
	
	public void testEncrypt2() {
		String a = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15);
		System.out.println(a);
		
		//test-------------
		System.out.println("Reversed encrypt: "+ encrypt(a, 26-2));
		
	}
	
	public static void main(String [] args) {
		Cipher t = new Cipher();
		t.testEncrypt2();
	}
}
