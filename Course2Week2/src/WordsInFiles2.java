import java.io.File;
import java.util.*;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordsInFiles2 {
	private HashMap<String, ArrayList<String>> filenames;
	
	public WordsInFiles2() {
		filenames = new HashMap<String, ArrayList<String>>();
	}
	
	private void addWordsFromFile(File f) {
		/*This method should add all the words from f into the map.
		 *If a word is not in the map, then you must create a new ArrayList 
		 *of type String with this word, and have the word map to this ArrayList.
		 */
		
		String fileName = f.getName();
		FileResource fr = new FileResource(f.toString());
		
		for(String word: fr.words()) {
			if(filenames.containsKey(word)) {
				ArrayList array = filenames.get(word);
				if(array.contains(fileName)) {
					//do nothing
				}else {
					array.add(fileName);
					filenames.put(word, array);
				}
				
			}else {
				ArrayList<String> array = new ArrayList<String>();
				array.add(fileName);
				filenames.put(word, array);
			}
		}
	}
	
	public void buildWordFileMap() {
		/*This method first clears the map, and then uses a DirectoryResource 
		 *to select a group of files. For each file, it puts all of its words 
		 *into the map by calling the method addWordsFromFile. 
		 * */
		filenames.clear();
		DirectoryResource dr = new DirectoryResource();
		for(File file: dr.selectedFiles()) {
			addWordsFromFile(file);

		}
	}
	
	public int maxNumber() {
		/*This method returns the maximum number of files any word appears in, 
		 *considering all words from a group of files. In the example above,
		 *there are four files considered.
		 */
		int max = -1;
				
		for(String word: filenames.keySet()) {
			ArrayList<String> array = filenames.get(word);
			int size = array.size();
			if(size > max) {
				max = size;
			}
		}
		return max;
	}
	
	public ArrayList<String> wordsInNumFiles(int number){
		/*This method returns an ArrayList of words that appear 
		 * in exactly number files.
		 */
		ArrayList<String> list = new ArrayList<String>();
		for(String word: filenames.keySet()) {
			if(filenames.get(word).size()==number) {
				list.add(word);
			}
		}
		return list;
	}
	
	public void printFilesIn(String name) {
		/*This method prints the names of the files this word appears in, 
		 *one filename per line. 
		 */
		System.out.println("Files that '"+name+"' appears in: ");
		for(String file: filenames.get(name)) {
			System.out.println(file);
		}
	}
	
	public void appears(int number) {
		int count = 0;
		for(String string: filenames.keySet()) {
			if(filenames.get(string).size()==number) {
				count++;
				System.out.println(string);
			}
		}
		System.out.println("Total that appear in " + number + " files = "+count);
	}
	
	public void test() {
		String path = "/home/masupha/eclipse-workspace/Datasets/QuizGladLibsData/";
		String files[] = new String[] {path+"caesar.txt",path+"hamlet.txt", path+"likeit.txt",
				path+"macbeth.txt", path+"romeo.txt", path+"confucius.txt", path+"errors.txt"};
		
		for(String file: files) {
			File f = new File(file);
			addWordsFromFile(f);
		}
		appears(4);
		System.out.println("tree: "+filenames.get("tree"));
	}
	
	public static void main(String[] args) {
		WordsInFiles2 w = new WordsInFiles2();
		w.test();
		
	}
	
}
