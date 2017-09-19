package examples.pojos;

/**
  * Class with the general description of Activities assigning a Tag.
  * Attributes:  label (String), lang (String).
  */
public class Tag {
	private String label;
	private String lang;

	public Tag(String label, String lang) {
		this.label = label;
		this.lang = lang;
	}

	public Tag() {
		this.label = "generic";
		this.lang = "doSomething";
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	
	// Modify to String to add the missing attributes.
	public String toString() {
		return "label="+label+", Lang="+lang;
	}

}
