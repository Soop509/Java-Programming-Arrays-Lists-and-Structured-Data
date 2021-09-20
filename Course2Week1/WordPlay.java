
public class WordPlay {
	
	public boolean isVowel(char ch) {
		String vowels = "IAEOUiaeou";
		
		for(int i=0; i<vowels.length(); i++) {
			char letter = vowels.charAt(i);
			if(letter == ch) {
				return true;
			}
		}
		return false;
	}
	
	public String replaceVowels(String phrase, char ch) {
		StringBuilder sb = new StringBuilder(phrase);
		
		for(int i=0; i<phrase.length()-1; i++) {
			char letter = sb.charAt(i);
			if(isVowel(letter)) {
				sb.setCharAt(i, ch);
			}
		}
		return sb.toString();
	}
	
	public String emphasize(String phrase, char ch) {
		System.out.println(phrase);
		StringBuilder sb = new StringBuilder(phrase);
		for(int i=0; i<phrase.length(); i++) {
			char currChar = sb.charAt(i);
			
			if(currChar ==ch) {
				//System.out.print("Letter "+currChar+" at index "+i+ " is at an ");
				
				if(i%2==0) {
					//System.out.println("even index");
					sb.setCharAt(i,  '*');
				}else {
					//System.out.println("odd index");
					sb.setCharAt(i,  '+');
				}
			
			}
		}
		return sb.toString();
	}
	
	public void test() {
		String a = emphasize("Mary Bella Abracadabra", 'a');
		System.out.println(a);	//“M+ry Bell+ +br*c*d*br+”.
	}
	
	public static void main(String[] args) {
		WordPlay wp = new WordPlay();
		wp.test();
	}
}
