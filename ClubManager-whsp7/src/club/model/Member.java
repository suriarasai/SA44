package club.model;

public class Member extends Person implements Comparable<Member> {

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(String surname, String firstName, String secondName) {
		super(surname, firstName, secondName);
		// TODO Auto-generated constructor stub
	}

	private int memberNumber;

	public Member(String surname, String firstName, String secondName,
			int memberNumber) {
		super(surname, firstName, secondName);
		this.memberNumber = memberNumber;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	@Override
	public String toString() {
		return "Member [memberNumber = " + memberNumber + " "
				+ super.toString() + "]";
	}

	// Added so that Members can be sorted by membership number
	public int compareTo(Member other) {
		return (getMemberNumber() - other.getMemberNumber());
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

}
