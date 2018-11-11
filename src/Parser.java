import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class Parser {
	
	// Parse each csv file and create professor classes, outputting the result to a text file
	public static void main(String[] args) {
		Parser p = new Parser();
	}
    public Parser() {
    		HashMap<String, ArrayList<Class>> professors = new HashMap<>(); 
    		String workingDir = "/Users/zacharysnoek/Programming/java/course-schedule-parser/csv/";
        String l = "";
        String cvsSplitBy = ",";
        
        String fa18 = workingDir + "fa18.csv";
        
        try (BufferedReader br = new BufferedReader(new FileReader(fa18))) {

        		while ((l = br.readLine()) != null) {
                ArrayList<String> line = parseLine(l);
                
                try {
                		String comp = line.get(0).trim();
            	   		if (comp.equals("IN PROGRESS") || comp.equals("CANCELLED") || comp.equals("CLOSED") || comp.equals("COMPLETED") || comp.equals("PERMISSION")) {
            	   			
            	   			String name = line.get(1);

            	   			String title = line.get(1);
            	   			String status = line.get(0);
            	   			String creditAmount = line.get(6);
            	   			String capacity = line.get(17);
            	   			String actual = line.get(18);
            	   			
            	   			Class c = new Class(title, status, creditAmount, capacity, actual);
            	   			
            	   			if (professors.containsKey(name)) {
            	   				professors.get(name).add(c);
            	   			} else {
            	   				ArrayList<Class> classes = new ArrayList<Class>();
            	   				classes.add(c);
            	   				professors.put(name, classes);
            	   			}
            	   		}
                } catch (IndexOutOfBoundsException e) {
                		e.printStackTrace();
                }
            }
        		for (String name : professors.keySet()) {
        			System.out.println(name);
        			System.out.println(professors.get(name).toString());
        			System.out.println("");
        		}
        } catch (IOException e) {
        		e.printStackTrace();
        }
    }
    
    public ArrayList<String> parseLine(String line) {
		char[] input = line.toCharArray();
		StringBuilder sb = new StringBuilder();
		ArrayList<String> output = new ArrayList<String>();
		
		boolean inQuotes = false;
		
		for (char c : input) {
			if (c == '"') {
				inQuotes = true;
			}
			if (inQuotes == true) {
				if (c == '"') {
					inQuotes = false;
				} else {
					sb.append(c);
				}
			}
			else if (c == ',') {
				// Add to array
				output.add(sb.toString());
				sb = new StringBuilder();
			} else {
				sb.append(c);
			}
		}
		
		return output;
    }
}