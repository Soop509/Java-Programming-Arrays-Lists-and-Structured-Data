import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordsInFiles {

	private HashMap<String, ArrayList<String>> filenames;
	
	public WordsInFiles() {
		filenames = new HashMap<String, ArrayList<String>>();
	}

	private void addWordsFromFile(File f) {
		String file = f.getName();
		FileResource fr = new FileResource(f.toString());
		
		for(String lines: fr.lines()) {
			for(String string: lines.split(" ")) {

				ArrayList<String> arr = new ArrayList<String>();
				ArrayList<String> currArr = filenames.get(string); //The non-repeat array
				
				if(filenames.size() == 0) {
					arr.add(file);
					filenames.put(string, arr);
				}else {
					if(filenames.containsKey(string)) {
						if(currArr.contains(file)) {
							break;
						}else {
							currArr.add(file);
							filenames.put(string, currArr);
						}
						
					}else {
						arr.add(file);
						filenames.put(string, arr);
					
					}
				}
			}
		}
	}
	
	public void buildWordFileMap() {
		filenames.clear();
		DirectoryResource d = new DirectoryResource();
		for(File file: d.selectedFiles()) {
			addWordsFromFile(file);
		}
	}
	
	public void printOut() {
		for(String string: filenames.keySet()) {
			System.out.println(string + "\t" + filenames.get(string));
		}
	}
	
	public int maxNumber() {
		int max = -1;
		for(String string: filenames.keySet()) {
			ArrayList<String> curr = filenames.get(string);
			if(curr.size()>max) {
				max = curr.size();
			}
		}
		return max;
	}
	
	public ArrayList<String> wordsInNumFiles(int number) {
		ArrayList<String> list = new ArrayList<String>();
		for(String string: filenames.keySet()) {
			if(string.length()==number) {
				list.add(string);
			}
		}
		return list;
	}
	
	public void printFilesIn(String word) {
		buildWordFileMap();
		if(filenames.containsKey(word)) {
			for(String string: filenames.get(word)) {
				System.out.println(string);
			}
		}
	}
	
	public void testBuildWordFileMap() {
		buildWordFileMap();
		printOut();
	}
	
	public void testAddWordsFromFile() {
		String path = "/home/masupha/eclipse-workspace/Datasets/QuizGladLibsData/";
		//caesar.txt, hamlet.txt, likeit.txt, macbeth.txt, and romeo.txt.
		String files[] = new String[] {path+"caesar.txt",path+"hamlet.txt", path+"likeit.txt",
				path+"macbeth.txt", path+"romeo.txt", path+"confucius.txt", path+"errors.txt"};
		for(String file: files) {
			File f = new File(file);
			addWordsFromFile(f);
		}
		/*for(String string: filenames.keySet()) {
			ArrayList<String> arr = filenames.get(string);
			if(arr.size()==4) {									//5
				System.out.println(string);
			}
		}*/
		System.out.println("Contains 'sea': "+filenames.containsKey("sea"));
		System.out.println("tree: "+filenames.get("tree"));
	}
	
	public void testPrintFilesIn() {
		printFilesIn("sad");
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
	
	public void tester() {
		buildWordFileMap();
		maxNumber();
	}
	
	public static void main(String[] args) {
		WordsInFiles n = new WordsInFiles();
		n.testAddWordsFromFile();
		
	}
}
