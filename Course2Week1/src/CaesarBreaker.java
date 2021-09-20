import edu.duke.FileResource;

public class CaesarBreaker extends CaesarCipher {
	
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
	
	public String decrypt(String encrypted, int key){
		CaesarCipher cc = new CaesarCipher();
		String message = cc.encrypt(encrypted, 26 - key);
		return message;
	}
	public String halfOfString(String message, int start) {
		String result = "";
		for(int k = start; k<message.length(); k+=2) {
			result = result + message.substring(k, k+1);
			
		}
		
		return result;
	}
	
	public int getKey(String s) {
		int[] wordLength = countLetters(s);
		int max = maxIndex(wordLength);
		System.out.println("Max:" +max);
		
		int diff = 4 - max;
		if(diff>26) {
			diff -=26;
		}else if(diff<0) {
			diff+=26;
		}
		
		int key = 26 - diff;
		return key;
	}
	
	public void printOut(int[] arr) {
		for(int i = 0; i<arr.length; i++) {
			System.out.println(i+": "+arr[i]);
		}
	}
	
	public String decryptTwoKeys(String encrypted) {
		String string1 = halfOfString(encrypted, 0);
		String string2 = halfOfString(encrypted, 1);
		
		int s1Key = getKey(string1);
		int s2Key = getKey(string2);
		System.out.println("Key1: "+s1Key+ "\nKey2: "+s2Key);
		
		string1 = decrypt(string1, s1Key);
		string2= decrypt(string2, s2Key);
		
		int endLength = (string1+string2).length();
		StringBuilder sb = new StringBuilder();
		
		int count1 = 0;
		int count2 = 0;
		
		for(int i = 0; i<endLength; i++){
			if(i%2 == 0) {
				sb.append(string1.charAt(count1));
				count1 += 1;
			}else{
				sb.append(string2.charAt(count2));
				count2 += 1;
			}
		}
		return sb.toString();
	}
	public void testDecryptTwoKeysFile() {
		FileResource f = new FileResource();
		String file = f.asString();
		String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
		String a = decryptTwoKeys(file);
		System.out.println("Message: " + message + "\n\nDecrypted: " +a);
		
	}
	public void testDecryptTwoKeys() {
		String message = "/home/masupha/eclipse-workspace/Datasets/CommonWordsData/mysteryTwoKeys.txt";
		FileResource g = new FileResource(message);
		String b = g.asString();
		System.out.println(b);
		String a = decryptTwoKeys(b);
		System.out.println("\nDecrypted: " +a);
		
	}
	
	public void testCountLetters() {
		int[] a = countLetters("AAABbcd   kkkk");
		printOut(a);
		
	}
	
	public void testGetKey() {
		String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
		int key = 2;
		
		int[] wordLength= countLetters(message);
		String a = encrypt(message, key);
		
		getKey(a);
		
	}
	
	public void testDecrypt() {
		String a = encrypt("Hello there.", 6);
		System.out.println("Encrypted: "+a);
		String b = decrypt(a, 6);
		System.out.println("Dencrypted: "+b);
	}
	
	public static void main(String[] args) {
		CaesarBreaker cb = new CaesarBreaker();
		cb.testDecryptTwoKeys();
	}
}
