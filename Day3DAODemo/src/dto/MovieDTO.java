package dto;

import java.io.Serializable;
// Data Transfer Object
public class MovieDTO implements Serializable {
	private int id;
	private String name;
	private String heroName;
	private double avgrating;
	public MovieDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MovieDTO(String name, String heroName, double avgrating) {
		super();
		this.name = name;
		this.heroName = heroName;
		this.avgrating = avgrating;
	}
	
	public MovieDTO(int id, String name, String heroName, double avgrating) {
		super();
		this.id = id;
		this.name = name;
		this.heroName = heroName;
		this.avgrating = avgrating;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeroName() {
		return heroName;
	}
	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}
	public double getAvgrating() {
		return avgrating;
	}
	public void setAvgrating(double avgrating) {
		this.avgrating = avgrating;
	}
	@Override
	public String toString() {
		return "MovieDTO [id=" + id + ", name=" + name + ", heroName=" + heroName + ", avgrating=" + avgrating + "]";
	}
	
}
