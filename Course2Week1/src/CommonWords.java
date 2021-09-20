import edu.duke.*;

public class CommonWords {
/* you should type in, compile, and understand the example programs from the lesson, 
 * including: 1) counting the twenty most common words from Shakespeare’s plays, 
 * 2) counting the resulting random rolls of dice (how many 2’s, 3’s, 4’s, etc), and 
 * 3) automatic decryption of the Caesar Cipher using statistical letter occurrences.
 */
	
	public String[] getCommon() {
		/*Gets common words from common.txt file and creates an array
		 * for  each word 
		 */
		FileResource resource = new FileResource("/home/masupha/eclipse-workspace/Datasets/CommonWordsData/common.txt");
		String[] common = new String[20];
		int index = 0;
		for(String s: resource.words()) {
			common[index] = s;
			index+=1;
		}
		return common;
	}
	
	public void countWords(FileResource resource, String[] common, int[] counts){
		/*Counts the number of words in the given file resource,
		 * 
		 */
		for(String word: resource.words()){
			int index = indexOf(common, word);	
			
			if(index != -1) {
				counts[index]+=1;
			}
		}
	}
	
	private int indexOf(String[] common, String word) {
		/*Returns the index of a word
		 */
		for(int k=0; k<common.length; k++) {
			if(common[k].equals(word)){
				return k;
			}
		}
		return -1;
	}

	void countShakespeare() {
		/*String[] plays = {"caesar.txt",
				"errors.txt", 
				"hamlet.txt",
				"likeit.txt",
				"macbeth.txt", 
				"romeo.txt"};
		*/
		String[] plays = {"romeo.txt"};
		
		String[] common = getCommon();
		int[] counts = new int[common.length];
		for(int k=0; k<plays.length; k++) {
			FileResource resource = new FileResource("/home/masupha/eclipse-workspace/Datasets/CommonWordsData/"+plays[k]);
			countWords(resource, common, counts);
			System.out.println("done with "+ plays[k]);
		}
		
		for(int k=0; k<common.length; k++) {
			System.out.println(common[k]+"\t"+counts[k]);
		}
		int a = maxIndex(counts);
		System.out.println("Most common word is: "+common[a]);
		
	}
	
	public int maxIndex(int[] values) {
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
	
	public static void main(String[] args) {
		CommonWords cw = new CommonWords();
		cw.countShakespeare();
	}
}
