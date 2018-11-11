import java.util.HashMap;

public class Class {
	String title;
	String status;
	String creditAmount;
	String capacity;
	String actual;
	
	public Class(String title, String status, String creditAmount,
			String capacity, String actual) {
		this.title = title;
		this.status = status;
		this.creditAmount = creditAmount;
		this.capacity = capacity;
		this.actual = actual;
	}
	
	public String toString() {
		String str = "Title: " + title.toUpperCase() + "\n";
		str += "Status: " + status + "\n";
		str += "Credits: " + creditAmount + "\n";
		str += "Capacity: " + capacity + "\n";
		str += "Actual: " + actual + "\n";
		
		return str;
	}
}