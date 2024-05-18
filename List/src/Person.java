/**
 * Person class- represents Person object.
 */
public class Person implements Comparable<Person> {
	private String name;   // name of person
	private int birthYear; // person's year of birth
	private String id;     // person's ID

	/* Default constructor - creates a default Person object */
	public Person() {
		this("Anonymous", 1000, "000-000-000");
	}

	/* Constructor for Person objects.
	 * Initializes a new Person with the provided name, birth year, and ID */
	public Person(String name, int birthYear, String id) throws IllegalArgumentException {
		this.setName(name);
		this.setBirthYear(birthYear);
		this.setId(id);
	}

	/* Gets the name attribute of the Person object */
	public String getName() {
		return name;
	}

	/* Sets the name attribute of the Person object */
	public void setName(String name) throws IllegalArgumentException {
		if (name == null || name.trim().isEmpty()) // validation check
			throw new IllegalArgumentException("Name cannot be null or empty");

		this.name = name;
	}

	/* Gets the birthYear attribute of the Person object */
	public int getBirthYear() {
		return birthYear;
	}

	/* Sets the birthYear attribute of the Person object */
	public void setBirthYear(int birthYear) throws IllegalArgumentException {
		if (birthYear < 0) // validation check
			throw new IllegalArgumentException("Year cannot be negative");

		else
			this.birthYear = birthYear;
	}

	/* Gets the id attribute of the Person object */
	public String getId() {
		return id;
	}

	/* Sets the id attribute of the Person object */
	public void setId(String id) throws IllegalArgumentException {
		 // validate ID format as ###-###-### or #########
        if (id != null && (id.matches("\\d{3}-\\d{3}-\\d{3}") || id.matches("\\d{9}"))) {
            // remove any non-digit characters 
        	id = id.replaceAll("[^\\d]", ""); 
        	//format the ID as ###-###-###
        	this.id = id.substring(0, 3) + "-" + id.substring(3, 6) + 
        			"-" + id.substring(6); 
        } else
            throw new IllegalArgumentException("ID must be in the format of ###-###-### or #########.");
	}

	/* toString method for Person object */
	@Override
	public String toString() {
		return "name: " + getName() + ", birth year: " + getBirthYear() + ", ID: " + getId() + "\n";
	}

	/* compateTo method for Person object */
	@Override
	public int compareTo(Person person) {
		if (this.getBirthYear() < person.getBirthYear())
			return 1; // current person is older

		else if (this.getBirthYear() > person.getBirthYear())
			return -1; // current person is younger

		else
			return 0; // birth years are equal
	}
} // end of Person class
