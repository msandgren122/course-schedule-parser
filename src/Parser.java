import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class Parser {
	
	// Parse each csv file and create professor classes, outputting the result to a text file
	public static void main(String[] args) {
		HashMap<String, String> csvFiles = new HashMap<String, String>();
		csvFiles.put("sp19", "19SP");
		csvFiles.put("fa18", "18FA");
		
		for (String file : csvFiles.keySet()) {
			Parser p = new Parser(file, csvFiles.get(file));
		}
	}
    
	public Parser(String file, String year) {
		parse(file, year);
    }
    
	public void parse(String file, String year) {
		//HashMap<String, ArrayList<Class>> professors = new HashMap<>(); 
		ArrayList<Class> classes = new ArrayList<Class>();
		
		String workingDir = "/Users/zacharysnoek/Programming/java/course-schedule-parser/csv/";
	    String l = "";
	    
	    String fa18 = workingDir + file + ".csv";
	    
	    try (BufferedReader br = new BufferedReader(new FileReader(fa18))) {
	
	    		while ((l = br.readLine()) != null) {
	            ArrayList<String> line = parseLine(l);
	            
	            try {
	            		String comp = line.get(0).trim();
	        	   		if (comp.equals("IN PROGRESS") || comp.equals("CANCELLED") || comp.equals("CLOSED") || comp.equals("COMPLETED") || comp.equals("PERMISSION")) {
	        	   			
	        	   			String title = line.get(1);
	        	   			String status = line.get(0);
	        	   			String creditAmount = line.get(6);
	        	   			String capacity = line.get(17);
	        	   			String actual = line.get(18);
	        	   			String instructor = line.get(21);
	        	   			
	        	   			Class c = new Class(title, status, creditAmount, capacity, actual, instructor, year);
	        	   			
//	        	   			if (professors.containsKey(title)) {
//	        	   				professors.get(title).add(c);
//	        	   			} else {
//	        	   				//ArrayList<Class> classes = new ArrayList<Class>();
//	        	   				classes.add(c);
//	        	   				professors.put(title, classes);
//	        	   			}
	        	   			classes.add(c);
	        	   		}
	            } catch (IndexOutOfBoundsException e) {
	            		//e.printStackTrace();
	            }
	        }
	    		for (Class cl : classes) {
	    			//System.out.println(cl.title);
	    			System.out.println(cl.toString());
	    			System.out.println("");
	    		}
	    } catch (IOException e) {
	    		//e.printStackTrace();
	    }
	}
	
    public ArrayList<String> parseLine(String line) {
		char[] input = line.toCharArray();
		StringBuilder sb = new StringBuilder();
		ArrayList<String> output = new ArrayList<String>();
		
		boolean inQuotes = false;
		
		for (char c : input) {
			if (c == '"' && inQuotes == false) {
				inQuotes = true;
			} else {
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
		}
		
		return output;
    }
}