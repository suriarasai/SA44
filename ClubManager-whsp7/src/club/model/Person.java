package club.model;

public class Person {

	private String surname;
	private String firstName;
	private String secondName;

	public Person(String surname, String firstName, String secondName) {
		this.surname = surname;
		this.firstName = firstName;
		this.secondName = secondName;
	}

	public String getSurname() {
		return surname;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	@Override
	public String toString() {
		if (surname != null)
			return "Person [surname=" + surname + ", firstName=" + firstName
					+ ", secondName=" + secondName + "]";
		else
			return "Person [firstName=" + firstName + ", secondName="
					+ secondName + "]";
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
}
