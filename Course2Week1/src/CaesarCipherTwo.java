//import edu.duke.FileResource;

public class CaesarCipherTwo extends CaesarCipher {
	 String alphabet;
	 String shiftedAlphabet1;
	 String shiftedAlphabet2;
	 int mainKey1;
	 int mainKey2;

	 public CaesarCipherTwo(int key1, int key2) {
		 alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		 shiftedAlphabet1 = shift(alphabet, key1);
		 shiftedAlphabet2 = shift(alphabet, key2);
		 //int mainKey1 = key1;
		 //int mainKey2 = key2;
		 
	 }
	 
	 public String shift(String phrase, int key) {
			return (phrase.substring(key)+phrase.substring(0, key));
		}
	 
	 public String encrypt(String input, String shiftedAlphabet) {
		
		StringBuilder sb = new StringBuilder(input);
		String alphabetLC = alphabet.toLowerCase();
		
		for(int i=0; i<input.length(); i++) {
			char ch = sb.charAt(i);
			
			if(alphabet.indexOf(ch)!=-1) {
				int idx = alphabet.indexOf(ch);
				char newLetter = shiftedAlphabet.charAt(idx);
				sb.setCharAt(i, newLetter);
				
			}else if(alphabetLC.indexOf(ch)!=-1) {
				
				int idx = alphabetLC.indexOf(ch);
				char newLetter = shiftedAlphabet.charAt(idx);
				sb.setCharAt(i, newLetter);
			}
		}
		return sb.toString();
	}

	 public String encryptTwoKeys(String input) {
			
			StringBuilder sb = new StringBuilder(input);
			String encrypt1 = encrypt(input, shiftedAlphabet1);
			String encrypt2 = encrypt(input, shiftedAlphabet2);
			
			for(int i=0; i<input.length(); i++) {
				if(i%2==0) {
					//for even indexes...
					char ch = encrypt1.charAt(i);
					sb.setCharAt(i,ch);
				}else {
					//for odd indexes...
					char ch = encrypt2.charAt(i);
					sb.setCharAt(i,ch);
				}
			}
			return sb.toString();
		}
	 
	 public void testEncryptTwoKeys() {
		 String a = encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.");
		 System.out.println("Encrypted: "+a);
		 
	 }
	 
	public static void main(String[] args) {
		CaesarCipherTwo cc = new CaesarCipherTwo(26-14, 26-24);
		cc.testEncryptTwoKeys();
		
	}
}
	
 