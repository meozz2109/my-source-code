package vnua.k62cnpm.xdptpm.libmanage.test.application;

import java.util.ArrayList;

public class Person {
	private String firstName;
	private String lastName;
	
	public Person(String firstName, String lastName) {
		if(firstName == null && lastName == null) {
			throw new IllegalArgumentException("Both names can not be null");
		}
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getFullName() {
		String first = (this.firstName!=null) ? this.firstName : "?";
		String last = (this.firstName!=null) ? this.lastName : "?";
		return first+" "+ last;
	}
	
	public ArrayList<Person> getPersonList(){
		ArrayList<Person> personList = new ArrayList<Person>();
		personList.add(new Person("Tran Quang", "Minh"));
		personList.add(new Person("Le Trung Tien", "Binh"));
		personList.add(new Person("Pham Thi", "Huong"));
		personList.add(new Person("Do Minh", "Tru"));
		return personList;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Person) {
			Person p = (Person) obj;
			if(p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
				return true;
			}
		}
		return false;
	}
	
	public Person search(Person searchPerson) {
		Person person = null;
		ArrayList<Person> personList = getPersonList();
		for (Person per : personList) {
			if(per.equals(searchPerson)) {
				person = per;
				break;
			}
		}
		return person;
	}
}
