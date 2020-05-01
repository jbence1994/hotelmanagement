package hotelmanagement.model;

public class Guest {

	private String firstName;
	private String lastName;
	private String citizenship;

	public Guest(String firstName, String lastName, String citizenship) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.citizenship = citizenship;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFullName() {
		return lastName + " " + firstName;
	}

}
