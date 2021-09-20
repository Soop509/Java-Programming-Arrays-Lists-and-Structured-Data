
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class Tester{
	
	public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le.toString());
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2.toString());
    }
    
    public void testLogAnalyzer() {
    	LogAnalyzer la = new LogAnalyzer();
        la.readFile("/home/masupha/eclipse-workspace/WebLogProgram/short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
    	LogAnalyzer l = new LogAnalyzer();
    	l.readFile("/home/masupha/eclipse-workspace/WebLogProgram/weblog2_log");
   	 	System.out.println("Number of unique IPs: " +l.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum() {
    	LogAnalyzer l = new LogAnalyzer();
    	l.readFile("/home/masupha/eclipse-workspace/WebLogProgram/weblog1_log");
    	l.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
    	LogAnalyzer l = new LogAnalyzer();
    	l.readFile("/home/masupha/eclipse-workspace/WebLogProgram/weblog2_log");
    	ArrayList<String> list = l.uniqueIPVisitsOnDay("Sep 27");
    	
    	for(String a: list) {
    		System.out.println(a);
    	}
    }
    
    public void testCountUniqueIPsInRange() {
    	LogAnalyzer l = new LogAnalyzer();
    	l.readFile("/home/masupha/eclipse-workspace/WebLogProgram/weblog2_log");
    	System.out.println("Number of unique entries in range: "+l.countUniqueIPsInRange(200,299));
    	
    }
    
    public void testCountVisitsPerIP() {
    	LogAnalyzer l = new LogAnalyzer();
    	l.readFile("/home/masupha/eclipse-workspace/WebLogProgram/short-test_log");
    	HashMap<String, Integer> map = l.countVisitsPerIP();
    	for(String string: map.keySet()) {
    		System.out.println(string+" - "+map.get(string));
    	}
    }
    
    public void testMostNumberVisitsByIP() {
    	LogAnalyzer l = new LogAnalyzer();
    	l.readFile("/home/masupha/eclipse-workspace/WebLogProgram/weblog2_log");
    	HashMap<String, Integer> map = l.countVisitsPerIP();
    	l.mostNumberVisitsByIP(map);
    }
    
    public void testiPsMostVisits() {
    	LogAnalyzer l = new LogAnalyzer();
    	l.readFile("/home/masupha/eclipse-workspace/WebLogProgram/weblog2_log");
    	HashMap<String, Integer> map = l.countVisitsPerIP();
    	ArrayList<String> list = l.iPsMostVisits(map);
    	System.out.println("List: " + list);
    }
    
    public void testiPsForDays() {
    	LogAnalyzer l = new LogAnalyzer();
    	l.readFile("/home/masupha/eclipse-workspace/WebLogProgram/weblog3-short_log");
    	HashMap<String, ArrayList<String>> map = l.iPsForDays();
    	System.out.println(map);
    }
    
    public void testDayWithMostIPVisits() {
    	LogAnalyzer l = new LogAnalyzer();
    	l.readFile("/home/masupha/eclipse-workspace/WebLogProgram/weblog2_log");
    	HashMap<String, ArrayList<String>> map = l.iPsForDays();
    	System.out.println("Day with most visits: " + l.dayWithMostIPVisits(map));
    	
    }
    
    public void testiPsWithMostVisitsOnDay() {
    	LogAnalyzer l = new LogAnalyzer();
    	l.readFile("/home/masupha/eclipse-workspace/WebLogProgram/weblog2_log");
    	HashMap<String, ArrayList<String>> map = l.iPsForDays();
    	ArrayList<String> list = l.iPsWithMostVisitsOnDay(map, "Sep 29");
    	System.out.println(list);
    }
    
    //--------------------Main-------------------------
    public static void main(String[] args) {
    	Tester t = new Tester();
    	t.testiPsWithMostVisitsOnDay();
    }
}