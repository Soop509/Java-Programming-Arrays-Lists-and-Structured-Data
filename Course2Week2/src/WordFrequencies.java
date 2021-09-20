import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import edu.duke.FileResource;

public class WordFrequencies {
	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;
	
	public WordFrequencies() {
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}
	
	public void printOut() {
		/*Prints out the contents of any ArrayList<String> and its corresponding
		 * ArrayList<Integer>
		 */
		for(int k = 0; k<myWords.size(); k++){
			System.out.println("Entry: " +myFreqs.get(k) + "\t"+myWords.get(k));
		}
		
	}
	
	public Boolean contains(String a) {
		/*Checks whether a word exists in the input ArrayList<String>
		 */
		for(String word: myWords) {
			word = word.toLowerCase();
			if(word.equals(a)){
				
				return true;
			}
		}
		return false;
	}
	
	public String findWord(String string) {
		int startindex = -1;
		int endindex = -1;
		
		for(int k= 0; k<string.length(); k++) {
			char ch = string.charAt(k);
			if(isLetter(ch)) {
				startindex = k;
			}
		}
		
		for(int k= startindex; k<string.length(); k++) {
			char ch = string.charAt(k);
			int indexOfNextLine = string.indexOf("\n");
			if((!isLetter(ch))||(indexOfNextLine!=-1)) {
				endindex = k;
				break;
			}
		}
		System.out.println("start = "+startindex+ ", end = "+endindex);
		return string.substring(startindex, endindex);
	}
	
	public void processLine(String string) {
		for(String word: string.split(" ")) {
					
					word = word.toLowerCase();
					if(myWords.contains(word)) {
						int index = myWords.indexOf(word);
						int newFreq = myFreqs.get(index) +1;
						myFreqs.set(index, newFreq);
					}else {
						myWords.add(word);
						myFreqs.add(1);
					}
				}
			}
	
	public void findUnique() {
		/*Finds and stores unique words inside an ArrayList<String>
		 * and keys track of the word frequencies with ArrayList<Integer>
		 */
		myWords.clear();
		myFreqs.clear();
		FileResource f = new FileResource("/home/masupha/eclipse-workspace/Datasets/CommonWordsData/errors.txt");
		Iterable<String> line = f.lines();
		for(String a: line) {
			
			processLine(a);
		}
		
		System.out.println("\nNumber of unique words:" + myWords.size());
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

	public void findIndexOfMax() {
		int max = -1;
		int index = -1;
		if(myFreqs.size()==0) {
			System.out.println("ArrayList is empty.");
			return;
		}
		
		for(int k = 0; k<myFreqs.size(); k++) {
			int currValue = myFreqs.get(k);
			String string = myWords.get(k);
			
			if((currValue>max)&&(hasLetters(string))) {
				max = currValue;
				index = k;
			}
		}
		System.out.println();
		System.out.println("Most frequent: '" + myWords.get(index) + "' at "+ myFreqs.get(index) + " occurance(s).");
	}
	
	public void testFindUnique(){
		System.out.println("Starting test..."); 
		findUnique();
		findIndexOfMax();
	}

	public static void main(String[] args) {
		WordFrequencies t = new WordFrequencies();
		t.testFindUnique();
		
		
	}

}
