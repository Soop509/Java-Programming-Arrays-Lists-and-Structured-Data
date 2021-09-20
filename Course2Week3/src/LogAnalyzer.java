
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
    	 records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
    	 FileResource f = new FileResource(filename);
    	 for(String line: f.lines()) {
    		 LogEntry a = WebLogParser.parseEntry(line);
    		 records.add(a);
    	 }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le.toString());
         }
     }

     public int countUniqueIPs() {
    	 /*This method should return an integer representing the number of unique 
    	  * IP addresses. It should also assume that the instance variable records 
    	  * already has its ArrayList of Strings read in from a file
    	  */
    	 ArrayList<String> list = new ArrayList<String>();
    	 for(LogEntry log: records) {
    		 String currIp = log.getIpAddress().toString();
    		 if(!list.contains(currIp)) {
    			 list.add(currIp);
    		 }
    	 }
    	 return list.size();
     }
     
     public void printAllHigherThanNum(int num) {
    	 
    	 for(LogEntry item: records) {
    		 int code = item.getStatusCode();
    		 if(code>num) {
    			 System.out.println(item.toString());
    		 }
    	 }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
    	 /*This method accesses the web logs in records and returns an 
    	  * ArrayList of Strings of unique IP addresses that had access on the given day.
    	  * 
    	  * someday is in the format “MMM DD” where MMM is the first three characters of 
    	  * the month name with the first letter capitalized and the others in lowercase, 
    	  * and DD is the day in two digits (examples are “Dec 05” and “Apr 22”).
    	  */
    	 ArrayList<String> list = new ArrayList<String>();
    	 for(LogEntry entry: records) {
    		 String date = entry.getAccessTime().toString(); //entry string
    		 String ip = entry.getIpAddress().toString();
    		 if(date.indexOf(someday)!=-1) {
    			 if(!list.contains(ip)) {
    				 list.add(entry.getIpAddress().toString());
    			 }
    		 }
    	 }
    	 System.out.println("Number of unique vists = "+list.size());
    	 return list;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
    	 ArrayList<String> ips = new ArrayList<String>();
    	 
    	 for(LogEntry log: records) {
    		 String currIp = log.getIpAddress().toString();
    		 int code = log.getStatusCode();
    		 
    		 if(code>=low && code<=high) {
	    		 if(!ips.contains(currIp)) {
	    			 ips.add(currIp);
	    		 }
    		 }
    	 }
    	 return ips.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP(){
    	 /*This method returns a HashMap<String, Integer> that maps an 
    	  * IP address to the number of times that IP address appears in records,
    	  */
    	 HashMap<String, Integer> map = new HashMap<String, Integer>();
    	 for(LogEntry log: records) {
    		 String ip = log.getIpAddress();
    		 if(map.containsKey(ip)) {
    			 int value = map.get(ip) + 1;
    			 map.put(ip, value);
    		 }else {
    			 map.put(ip, 1);
    		 }
    	 }
    	 return map;
     }

     public int mostNumberVisitsByIP(HashMap<String, Integer> map) {
    	 /*This method returns the maximum number of visits to this website 
    	  * by a single IP address. 
    	  */
    	 int max = -1;
    	 String greatest = "";
    	 
    	 for(String string: map.keySet()) {
    		 if(map.get(string)>max) {
    			 max = map.get(string);
    			 greatest = string;
    		 }
    	 }
    	 System.out.println("Greatest number of visits: " + greatest + " with "+ max + " visits.");
    	 return max;
     }

     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
    	 /*This method returns an ArrayList of Strings of IP addresses that 
    	  * all have the maximum number of visits to this website.
    	  */
    	 
    	 ArrayList<String> list = new ArrayList<String>();
    	 int maxNumVisits = mostNumberVisitsByIP(map);
    	 
    	 for(String string: map.keySet()) {
    		 if(map.get(string)==maxNumVisits) {
    			 list.add(string);
    		 }
    	 }
    	 
    	 return list;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays(){
    	 /*This method returns a HashMap<String, ArrayList<String>> that uses records 
    	  * and maps days from web logs to an ArrayList of IP addresses that occurred 
    	  * on that day (including repeated IP addresses). A day is in the 
    	  * format “MMM DD” where MMM is the first three characters of the month 
    	  * name with the first letter capital and the others in lowercase,
    	  */
    	 HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    	 for(LogEntry log: records) {
    		 String dateFull = log.getAccessTime().toString();
    		 String[] data = dateFull.split(" ");
    		 String date = data[1] + " " + data[2];
    		 
    		 String ip = log.getIpAddress();
    		 if(map.containsKey(date)) {
    			 ArrayList<String> newValue = map.get(date);
    			 newValue.add(ip);
    			 map.put(date, newValue);
    		 }else {
    			 ArrayList<String> newValue = new ArrayList<String>();
    			 newValue.add(ip);
    			 map.put(date, newValue);
    		 }
    	 }
    	 return map;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
    	 /*This method returns the day that has the most IP address visits. 
    	  * If there is a tie, then return any such day.
    	  */
    	 int largest = -1;
    	 String greatest = "";
    	 
    	 for(String string: map.keySet()) {
    		 if(map.get(string).size()> largest) {
    			 greatest = string;
    			 largest = map.get(string).size();
    		 }
    	 }
    	 return greatest;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day) {
    	 /*This method returns an ArrayList<String> of IP addresses that had the most 
    	  * accesses on the given day.
    	  * 
    	  * the first parameter is a HashMap<String, ArrayList<String>> that uses records 
    	  * and maps days from web logs to an ArrayList of IP addresses that occurred 
    	  * on that day, and the second parameter is a String representing a day in 
    	  * the format “MMM DD”
    	  */
    	 
    	 ArrayList<String> list = map.get(day);
    	 HashMap<String, Integer> result = new HashMap<String, Integer>();
    	 
    	 for(String string: list) {
    		 if(result.containsKey(string)) {
    			 int newValue = result.get(string) + 1;
    			 result.put(string, newValue);
    		 }else {
    			 result.put(string, 1);
    		 }
    	 }
    	 
    	 return iPsMostVisits(result);
     }
}













