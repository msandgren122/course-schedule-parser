import java.util.HashMap;

public class Class {
	String title;
	String status;
	String creditAmount;
	String capacity;
	String actual;
	String instructor;
	String year;
	
	public Class(String title, String status, String creditAmount,
			String capacity, String actual, String instructor, String year) {
		this.title = title;
		this.status = status;
		this.creditAmount = creditAmount;
		this.capacity = capacity;
		this.actual = actual;
		this.instructor = instructor;
		this.year = year;
	}
	
	public String toString() {
		String str = "Title: " + title.toUpperCase() + "\n";
		str += "Status: " + status + "\n";
		str += "Credits: " + creditAmount + "\n";
		str += "Capacity: " + capacity + "\n";
		str += "Actual: " + actual + "\n";
		str += "Instructor: " + instructor + "\n";
		str += "Year: " + year + "\n";
		return str;
	}
}