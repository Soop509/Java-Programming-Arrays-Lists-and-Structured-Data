import edu.duke.FileResource;

public class TestCaesarCipherTwo extends CaesarCipherTwo {
	/*private String alphabet;
	private String shiftedAlphabet1;
	private String shiftedAlphabet2;
	*/
	
	public TestCaesarCipherTwo(int key1, int key2) {
		super(key1, key2);
		shiftedAlphabet1 = shift(alphabet, key1);
		shiftedAlphabet2 = shift(alphabet, key2);
		mainKey1 = key1;
		mainKey2 = key2;
		
	}
	
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
	
	private int[] countLetters(String word) {
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

	public String halfOfString(String message, int start) {
		String result = "";
		for(int k = start; k<message.length(); k+=2) {
			result = result + message.substring(k, k+1);
			
		}
		
		return result;
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
				char newLetter = (shiftedAlphabet.toLowerCase()).charAt(idx);
				sb.setCharAt(i, newLetter);
			}
		}
		return sb.toString();
	}
	
	public void testEncrypt() {
		String string = "HELLO";
		TestCaesarCipherTwo c = new TestCaesarCipherTwo(2, 3);
		String a = c.encrypt(string, shiftedAlphabet1);
		System.out.println("encrypted = "+a);
	}

	public void simpleTests() {
		FileResource f = new FileResource("/home/masupha/eclipse-workspace/Datasets/ProgrammingBreakingCaesarData/wordsLotsOfEs.txt");
		String file = f.asString();
		TestCaesarCipherTwo cc = new TestCaesarCipherTwo(mainKey1, mainKey2);
		String en = cc.encryptTwoKeys(file);
		System.out.println("encrypted = "+en);
		
		TestCaesarCipherTwo ccd = new TestCaesarCipherTwo(26-mainKey1, 26-mainKey2);
		String de = ccd.encryptTwoKeys(en);
		System.out.println("---------------------------------------");
		System.out.println("Encrypted: "+en + "Decrypted: "+de);
		
	}
	public static void main(String[] args) {
		TestCaesarCipherTwo a = new TestCaesarCipherTwo(17, 3);
		a.simpleTests();
	}
}


