package hotelmanagement.model;

public class Guest {

	private int id;
	private String firstName;
	private String lastName;
	private String documentNumber;
	private String citizenship;
	private String phone;
	private String email;

	public Guest(int id, String firstName, String lastName, String documentNumber, String citizenship, String phone,
			String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.documentNumber = documentNumber;
		this.citizenship = citizenship;
		this.phone = phone;
		this.email = email;
	}

	public int getId() {
		return id;
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

	public String getDocumentNumber() {
		return documentNumber;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Vendég neve: " + getFullName() + ", okmányazonosító száma: " + documentNumber + ", állampolgársága: "
				+ citizenship + ", telefonszáma: " + phone + ", e-mail címe: " + email + ".";
	}

}
