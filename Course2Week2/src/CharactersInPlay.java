import java.io.File;
import java.util.ArrayList;

import edu.duke.FileResource;

public class CharactersInPlay {
	private ArrayList<String> names = new ArrayList<String>();	//store names as lowercase
	private ArrayList<Integer> freqs = new ArrayList<Integer>();

	public void printOut() {
		
		for(int k = 0; k<names.size(); k++) {
			String name = names.get(k);
			int freq = freqs.get(k);
			System.out.println(name+ "\t" + freq);
		}
		System.out.println("Done printing out...\n");
	}
		
	public int findStart(String name) {
		
		for(int k= 0; k<name.length(); k++) {
			char ch = name.charAt(k);
			if(isLetter(ch)) {
				return k;
			}
		}
		return -1;
	}
	
	public void update(String name) {
		/*This method should update the two ArrayLists,
		 *adding the character’s name if it is not already
		 *there, and counting this line as one speaking part for this person. 
		 */
		if(names.contains(name)) {
			int index = names.indexOf(name);
			int newCount = freqs.get(index) + 1;
			freqs.set(index, newCount);
		}else {
			names.add(name);
			freqs.add(1);
		}
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
	
	public boolean hasLetters(String string) {
		
		string = string.toLowerCase();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		alphabet = alphabet.toLowerCase() +alphabet;
		for(int k = 0; k<string.length(); k++) {
			char ch = string.charAt(k);
			int idx = alphabet.indexOf(ch);
			if(idx!=-1) {
				return true;
			}
		}
		return false;
	}
	
	public void findAllCharacters() {
		freqs.clear();
		names.clear();
		FileResource f = new FileResource("/home/masupha/eclipse-workspace/Datasets/CommonWordsData/errors.txt");
		
		for(String line: f.lines()) {
			int period = line.indexOf(".");
			
			if(period ==-1) {
				//do nothing
			}else {
				String sub = line.substring(0, period);
				if(sub.toUpperCase()==sub) {
					update(sub);
				}
			}
		}
	}
	
	public void charactersWithNumParts(int num1, int num2) {
		/*prints out the characters with speaking parts greater than
		 * num1 and smaller that num2. num1 <= num2
		 */
		if(num1>num2) {
			System.out.println("num1 should be smaller than num2!");
			return;
		}
		
		System.out.println("Characters with speaking parts greater than "+num1+" and smaller than "+num2+":");
		for(int k = 0; k<names.size(); k++) {
			
			int parts = freqs.get(k);
			
			if((parts>=num1)&&(parts<=num2)) {
				String name = names.get(k);
				System.out.println(name+" with "+parts+".");
			}
		}
	}
	
	public void mainCharacter() {
		int maxIndex = -1;
		int measure = -1;
		
		for(int k = 0; k<freqs.size(); k++) {
			int currValue = freqs.get(k);
			if(currValue>measure) {
				maxIndex = k;
				measure = currValue;
			}
		}
		if(maxIndex ==-1) {
			System.out.println("There is no main character\n");
		}else {
			System.out.println("Main character is " +names.get(maxIndex)+ " with "+ freqs.get(maxIndex)+ " speaking parts.\n");
		}
	}

	public void testFindAllCharacters() {
		findAllCharacters();
		charactersWithNumParts(10, 150);
		//mainCharacter();
	}
	

	public static void main(String[] args) {
		CharactersInPlay c = new CharactersInPlay();
		c.testFindAllCharacters();
	}
}
