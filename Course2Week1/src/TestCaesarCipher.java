import edu.duke.FileResource;

public class TestCaesarCipher extends CaesarBreaker {
	
	public boolean isLetter(char ch) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		alphabet = alphabet.toLowerCase() +alphabet;
		
		for(int i=0; i<alphabet.length(); i++) {
			char letter = alphabet.charAt(i);
			if(letter == ch) {
				return true;
			}
		}
		return false;
	}
	
	public int[] countLetters(String word) {
		/*Count the number of characters in a word
		 * excluding all symbols.
		 */
		int[] counts = new int[26];
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		word = word.toUpperCase();
		int index = -1;
		for(int i = 0; i<word.length(); i++) {
			char ch = word.charAt(i);
			
			if(isLetter(ch)) {
				index = alphabet.indexOf(ch);
				counts[index]+=1;
			}
		}
		return counts;
	}
	
	public int maxIndex(int[] values) {
		int max = -1;
		int maxIter = -1;
		
		for(int k = 0; k<values.length; k++) {
			if(max == -1) {
				max = values[k];
				maxIter = k;
				
			} else if((max != -1)&&(values[k]>max)) {
				maxIter = k;
				max = values[k];
			}
		}
		return maxIter;
	}

	public void breakCaesarCipher(String input) {
		int key = getKey(input);
		CaeserCipherModified cc = new CaeserCipherModified(key);
		String decrypted = cc.decrypt(input);
		System.out.println("Decrypted: "+decrypted);
	}
	
	public void testBreakCaesarCipher() {
		FileResource f = new FileResource("/home/masupha/eclipse-workspace/Datasets/ProgrammingBreakingCaesarData/wordsLotsOfEsEncrypted.txt");
		String string = f.asString();
		String a = decryptTwoKeys(string);
		System.out.println("nDecrypted: "+a);
	}
	
	public void simpleTests() {
		FileResource file = new FileResource("/home/masupha/eclipse-workspace/Datasets/ProgrammingBreakingCaesarData/wordsLotsOfEs.txt");
		String f = file.asString();
		CaeserCipherModified cc = new CaeserCipherModified(18);
		String encrypted = cc.encrypt(f);
		String decrypted = cc.decrypt(encrypted);
		System.out.println("Encrypted: "+encrypted + "\nDecrypted: "+decrypted);
	}
	public static void main(String[] args) {
		TestCaesarCipher tcc = new TestCaesarCipher();
		tcc.testBreakCaesarCipher();
	}
}
