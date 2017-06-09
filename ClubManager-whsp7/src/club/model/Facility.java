package club.model;


public class Facility implements Comparable<Facility> {

    private String name;
    private String description;

    public Facility (String name) {
        this (name, null);
    }

    public Facility (String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName () {
        return name;
    }

    public String getDescription () {
        return description;
    }

    @Override
	public String toString() {
		return "Facility [name = " + name + ", description = " + description + "]";
	}

     //  Added so that Facilities can be sorted alphabetically
    public int compareTo (Facility other) {
        return (getName().compareTo(other.getName()));
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Facility() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
