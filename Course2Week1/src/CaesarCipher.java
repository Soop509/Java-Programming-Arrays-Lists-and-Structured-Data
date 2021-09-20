
public class CaesarCipher {
	
	public String shift(String phrase, int key) {
		return (phrase.substring(key)+phrase.substring(0, key));
	}
	
	public String encrypt(String input, int key) {
		StringBuilder sb = new StringBuilder(input);
		String alphabetUC= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String alphabetLC = alphabetUC.toLowerCase();
		
		for(int i=0; i<input.length(); i++) {
			char ch = sb.charAt(i);
			
			if(alphabetUC.indexOf(ch)!=-1) {
				
				String shiftedAlphabet = shift(alphabetUC, key);
				int idx = alphabetUC.indexOf(ch);
				char newLetter = shiftedAlphabet.charAt(idx);
				sb.setCharAt(i, newLetter);
				
			}else if(alphabetLC.indexOf(ch)!=-1) {
				
				String shiftedAlphabet = shift(alphabetLC, key);
				int idx = alphabetLC.indexOf(ch);
				char newLetter = shiftedAlphabet.charAt(idx);
				sb.setCharAt(i, newLetter);
			}
		}
		return sb.toString();
	}
	
	public String encryptTwoKeys(String input, int key1, int key2) {
		
		StringBuilder sb = new StringBuilder(input);
		String encrypt1 = encrypt(input, key1);
		String encrypt2 = encrypt(input, key2);
		
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
		String a = encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.", 26-14, 26-24);
		System.out.println(a);
		//Should return Czojq Ivdzle
	}
	public void testShift() {
		System.out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		System.out.println(shift("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 15));
	}
	public void testEncrypt() {
		String a = encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15);
		System.out.println(a);
	}
	
	public static void main(String[] args) {
		CaesarCipher cc = new CaesarCipher();
		cc.testEncryptTwoKeys();
		
	}
}
