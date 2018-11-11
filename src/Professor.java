import java.util.HashMap;

public class Professor {
	String firstName;
	String lastName;
	
	HashMap<String, Class> classes = new HashMap<>(); 

	public Professor(String firstName, String lastName, HashMap<String, Class> classes) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.classes = classes;
	}
}
