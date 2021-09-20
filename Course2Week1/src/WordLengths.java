import edu.duke.FileResource;

public class WordLengths {
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
	
	public int wordLength(String word) {
		/*Count the number of characters in a word
		 * excluding all symbols.
		 */
		int count = 0;
		for(int i = 0; i<word.length(); i++) {
			char ch = word.charAt(i);
			if(isLetter(ch)) {
				count+=1;
			}	
		}
		return count;
	}
	
	public void countWordLengths(FileResource resource, int[] counts) {
		for(String word: resource.words()) {
			int wordLength = wordLength(word);
			
			counts[wordLength]+=1;
		}
		
		for(int i = 0; i<counts.length; i++) {
			System.out.println(i +"'s =\t\t" + counts[i]);
		}
		System.out.println("indexOfMax: " + indexOfMax(counts));
	}
	
	public int indexOfMax(int[] values) {
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
	
	public void testwordLength() {
		System.out.println(wordLength("Hel'o"));
	}
	
	public void testCountWordLengths() {
		FileResource f = new FileResource("/home/masupha/eclipse-workspace/Datasets/CommonWordsData/manywords.txt");
		int [] ar = new int[31];
		countWordLengths(f, ar);
		
	}
	
	public static void main(String[] args) {
		WordLengths a = new WordLengths();
		a.testCountWordLengths();
	}
}
