import java.util.*;

import edu.duke.FileResource;
import edu.duke.URLResource;

public class GladLibMap {
	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	private ArrayList<String> verbList;
	private ArrayList<String> fruitList;
	private Random myRandom;
	
	private ArrayList<String> used;
	private HashMap<String, ArrayList<String>> GladLibMap;
	
	private String dataSourceDirectory = "/home/masupha/eclipse-workspace/Datasets/GladLibData/data";
	
	public GladLibMap() {
		used = new ArrayList<String>();
		myRandom = new Random();
		GladLibMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		
	}
	
	private void initializeFromSource(String source) {
		/*
		adjectiveList = readIt(source+ "/adjective.txt");
		nounList = readIt(source+ "/noun.txt");
		colorList = readIt(source+ "/color.txt");
		countryList = readIt(source+ "/country.txt");
		nameList = readIt(source+ "/name.txt");
		animalList = readIt(source+ "/animal.txt");
		timeList = readIt(source+ "/timeframe.txt");
		verbList = readIt(source+ "/verb.txt");
		fruitList = readIt(source+ "/fruit.txt");
		used = new ArrayList<String>();
		*/
		
		String [] categories = {
			"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"
		};
		
		for(String string: categories) {
			ArrayList<String> values = readIt(source+ "/" +string + ".txt");
			GladLibMap.put(string, values);
		}
	}
	
	private String randomFrom(ArrayList<String> source) {
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		/*
		if(label.equals("adjective")) {
			return randomFrom(adjectiveList);
		}if(label.equals("noun")) {
			return randomFrom(nounList);
		}if(label.equals("color")) {
			return randomFrom(colorList);
		}if(label.equals("country")) {
			return randomFrom(countryList);
		}if(label.equals("name")) {
			return randomFrom(nameList);
		}if(label.equals("animal")) {
			return randomFrom(animalList);
		}if(label.equals("timeframe")) {
			return randomFrom(timeList);
		}if(label.equals("number")) {
			return " "+myRandom.nextInt(50)+5;
		}
		if(label.equals("verb")) {
			return randomFrom(verbList);
		}if(label.equals("fruit")) {
			return randomFrom(fruitList);
		}
		
		return "**UNKNOWN!**";
		*/
		if(label.equals("number")) {
			return " "+myRandom.nextInt(50)+5;
		}
			
		ArrayList<String> list = GladLibMap.get(label);
		return randomFrom(list);
	}
	
	public boolean used(String w) {
		if(used.isEmpty())return false;
		
		for(int k = 0; k<used.size(); k++) {
			String word = used.get(k);
			if(word.equals(w))return true;
		}
		return false;
	}
	
	private String processWord(String w) {
		int first = w.indexOf("<");
		int last = w.indexOf(">");
		if(first == -1 || last == -1) {
			return w;
		}
		
		String prefix = w.substring(0, first);
		String suffix = w.substring(last+1);
		String sub= "";
		
		
		while(true){
			sub = getSubstitute(w.substring(first+1, last));
			if(used(sub)) {
				//do nothing
			}else {
				used.add(sub);
				break;
			}
		}
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth) {
		int charsWritten = 0;
		for(String w: s.split("//s+")) {
			if(charsWritten+w.length()> lineWidth) {
				System.out.println();
				charsWritten = 0;
			}
			System.out.println(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source) {
		String story = "";
		if(source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word: resource.words()) {
				story = story + processWord(word)+" ";
			}
		}else {
			FileResource resource = new FileResource(source);
			for(String word: resource.words()) {
				story = story + processWord(word)+" ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if(source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line: resource.lines()) {
				list.add(line);
			}
		}else{
			FileResource resource = new FileResource(source);
			for(String line: resource.lines()) {
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory() {
		String template = fromTemplate(dataSourceDirectory+ "/madtemplate2.txt");
		printOut(template, 50);
		System.out.println("Total number of replaced words: "+used.size());
		System.out.println();
	}
	
	public void totalWordsInMap() {
		for(String string: GladLibMap.keySet()) {
			System.out.print("Key '"+string+"'");
			//for(String word: GladLibMap.get(string)) {
			//	System.out.println(word);
			//}
			System.out.println(" has "+GladLibMap.get(string).size() + " words");
		}
		System.out.println();
	}
	
	public void totalWordsConsidered() {
		System.out.println("Total words used: "+used.size());
		System.out.println();
		
	}
	public void test() {
		makeStory();
		totalWordsInMap();
		totalWordsConsidered();
	}
	
	public static void main(String[] args) {
		GladLibMap g = new GladLibMap();
		g.test();
	}
	
	
}
