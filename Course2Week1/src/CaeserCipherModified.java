
public class CaeserCipherModified {
	int mainKey;
	private String alphabet;
	private String shiftedAlphabet;
	
	public String shift(String phrase, int key) {
		return (phrase.substring(key)+phrase.substring(0, key));
	}
	
	public CaeserCipherModified(int key) {
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedAlphabet = shift(alphabet, key);
		mainKey = key;
	}
	
	public String encrypt(String input) {
		System.out.println("alpha:" +alphabet);
		System.out.println("shifted alpha:" +shiftedAlphabet);
		
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
	
	public String decrypt(String input) {
		CaeserCipherModified cc = new CaeserCipherModified(26 - mainKey);
		return cc.encrypt(input);
	}
	
	public void test() {
		String a = encrypt("ABCD");
		System.out.println(a +"\nkey = "+mainKey);
		
	}

	public static void main(String[] args) {
		CaeserCipherModified a = new CaeserCipherModified(9);
		a.test();

	}

}
