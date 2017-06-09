package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import model.Student;
import model.StudentComparatorByName;
import model.StudentComparatorByNickName;

public class TestStudent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Student s1 = new Student("JONATHAN TAY YUAN WEI", 1, "JOHN", 1200.00);
		Student s2 = new Student("KYAW WINT THU", 2, "XI-Complex", 1200.00);
		Student s3 = new Student("MADHURI SRINIVASAN", 3, "MADDIE", 1200.00);
		Student s4 = new Student("PHAM LE PHONG", 4, "NICE CUTE GUY", 1200.00);
		ArrayList<Student> al = new ArrayList<Student>();
		al.add(s1);
		al.add(s2);
		al.add(s3);
		al.add(s4);
		System.out.println("Data as stored");
		for (Iterator iterator = al.iterator(); iterator.hasNext();) {
			Student student = (Student) iterator.next();
			System.out.println(student.toString());

		}
		Collections.sort(al);
		System.out.println("Data as stored via Comparable");
		for (Iterator iterator = al.iterator(); iterator.hasNext();) {
			Student student = (Student) iterator.next();
			System.out.println(student.toString());
		}
		al.sort(new StudentComparatorByName());
		System.out.println("Data as stored via Comparator (Name)");
		for (Iterator iterator = al.iterator(); iterator.hasNext();) {
			Student student = (Student) iterator.next();
			System.out.println(student.toString());
		}
		al.sort(new StudentComparatorByNickName());
		System.out.println("Data as stored via Comparable");
		for (Iterator iterator = al.iterator(); iterator.hasNext();) {
			Student student = (Student) iterator.next();
			System.out.println(student.toString());
		}

	}

}
