package model;

import java.util.Comparator;

public class StudentComparatorByNickName implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return o1.getNickName().compareTo(o2.getNickName());
	}

}
