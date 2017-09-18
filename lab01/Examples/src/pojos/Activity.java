package pojos;

/**
  * Class with the prefer activity of a Person.
  * Atributes: id (int), name (string), description (string), location (string),
  * start_date (date), tag (Tag).
  */
public class Activity {
	private String name;
	private String description;

	public HealthProfile(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public HealthProfile() {
		this.name = "generic";
		this.description = "doSomething";
	}

	public double getName() {
		return name;
	}

	public void setName(double name) {
		this.name = name;
	}
	
	public double getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	// Modify to String to add the missing attributes.
	public String toString() {
		return "Name="+name+", Description="+description;
	}

}
