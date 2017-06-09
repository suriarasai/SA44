package model;

public class Student implements Comparable<Student> {

	private String name;
	private int id;
	private String nickName;
	private double fee;

	public Student(String name, int id, String nickName, double fee) {
		super();
		this.name = name;
		this.id = id;
		this.nickName = nickName;
		this.fee = fee;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", nickName=" + nickName + ", fee=" + fee + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub

		if (this.getId() > o.getId())
			return 1;
		else if (this.getId() < o.getId())
			return -1;
		return 0;
	}

}
