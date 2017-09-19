package examples.pojos;

/**
  * Class with the prefer activity of a Person.
  * Atributes: id (int), name (string), description (string), location (string),
  * start_date (date), tag (Tag).
  */
public class Activity {
	private String name;
	private String description;

	public Activity(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Activity() {
		this.name = "generic";
		this.description = "doSomething";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
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
