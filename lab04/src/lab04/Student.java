package lab04;

import java.util.ArrayList;
import java.util.List;

public class Student {

	String name;
	String surname;
	String present;
	List<Byte> mark = new ArrayList<Byte>();
	
	public Student() {}
	
	public Student(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
}
