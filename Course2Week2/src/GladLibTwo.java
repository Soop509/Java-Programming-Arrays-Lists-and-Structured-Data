/*Uses ArrayLists as a data structure to find out more
 * about a string or FileResource.
 */
import java.util.*;

import edu.duke.FileResource;

public class GladLibTwo {
	private HashMap<String, Integer> myMap;

	public GladLibTwo() {
		myMap = new HashMap<String, Integer>();
	}
	
	public void buildCodonMap(int start, String dna) {
		/*This method will build a new map of codons mapped to their counts from 
		 * the string dna with the reading frame with the position start 
		 * (a value of 0, 1, or 2).
		 */
		myMap.clear();
		
		for(int k = start; k<dna.length()-3; k+=3) {
			String string = dna.substring(k, k+3);
			if(myMap.containsKey(string)) {
				myMap.put(string, myMap.get(string)+1);
			}else {
				myMap.put(string, 1);
			}
		}
	}
	
	public String getMostCommonCodon() {
		String holder = "";
		int max = -1;
		
		for(String string: myMap.keySet()) {
			int currValue = myMap.get(string);
			if(currValue>max) {
				max = currValue;
				holder = string;
			}
		}
		return holder;
	}
	
	public void printCodonCounts(int start, int end) {
		for(String string: myMap.keySet()) {
			int count = myMap.get(string);
			if(count>start && count<end) {
				System.out.println(string + "\t" + count);
			}
		}
	}
	
	public void printOut() {
		for(String string: myMap.keySet()) {
			System.out.println(string + "\t" + myMap.get(string));
		}
	}
	
	public void unique() {
		System.out.println("Number of unique: "+ myMap.size());
	}
	
	public void test() {
		FileResource f = new FileResource("/home/masupha/eclipse-workspace/Datasets/PracticeGladLibsData/dnaMystery2");
		String file = f.asString();
		
		buildCodonMap(0, file);
		printOut();
		unique();
		String[] list = {"TGT", "ATG", "GAT", "CAA", "GCC", "CAG"};
		
		for(String word: list) {
			try {
				System.out.println(word + " = " +myMap.get(word));
	
			}finally {
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		GladLibTwo g = new GladLibTwo();
		g.test();
	}
}






