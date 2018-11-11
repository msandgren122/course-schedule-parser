import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

public class Parser {
	
	// Parse each csv file and create professor classes, outputting the result to a text file
    public static void main(String[] args) {
    		HashMap<String, ArrayList<Class>> professors = new HashMap<>(); 
    		String workingDir = "/Users/zacharysnoek/Programming/java/course-schedule-parser/csv/";
        String l = "";
        String cvsSplitBy = ",";
        
        String fa18 = workingDir + "sp19.csv";
        
        try (BufferedReader br = new BufferedReader(new FileReader(fa18))) {

        		while ((l = br.readLine()) != null) {
            		
                String[] line = l.split(cvsSplitBy);
                try {
                		String comp = line[0].trim();
            	   		if (comp.equals("IN PROGRESS") || comp.equals("CANCELLED") || comp.equals("CLOSED") || comp.equals("COMPLETED") || comp.equals("PERMISSION")) {
            	   			int len = line.length;
            	   			int titleI = 1;
            	   			int statusI = 0;
            	   			
            	   			int firstName = 21;
            	   			int lastName = 22;
            	   			int creditI = 6;
            	   			int capacityI = 17;
            	   			int actualI = 18;
            	   			
            	   			if (len != 25) {
                	   			firstName++;
                	   			lastName++;
                	   			creditI++;
                	   			capacityI++;
                	   			actualI++;
            	   			}
            	   			String name = line[firstName] + line[lastName];

            	   			String title = line[titleI];
            	   			String status = line[statusI];
            	   			String creditAmount = line[creditI];
            	   			String capacity = line[capacityI];
            	   			String actual = line[actualI];
            	   			
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
}