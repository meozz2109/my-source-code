package vnua.k62cnpm.xdptpm.libmanage.test.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class PersonTest {

	@Test
	public void testConstructor() {
		String firstName = "Lam", lastName = "Nguyen";
		Person per = new Person(firstName, lastName);
		assertEquals(firstName, per.getFirstName());
		assertEquals(lastName, per.getLastName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testException() {
		String firstName = "Lam", lastName = "Nguyen";
		Person per = new Person(null, null);
		assertEquals(firstName, per.getFirstName());
		assertEquals(lastName, per.getLastName());
	}

	@Test
	public void testGetFullName0() {
		String firstName = "Lam", lastName = "Nguyen";
		Person per = new Person(null, null);
		assertEquals(per.getFullName(), "? ?");
	}

	@Test
	public void testGetFullName1() {
		String firstName = "Lam", lastName = "Nguyen";
		Person per = new Person(null, lastName);
		assertEquals(per.getFullName(), "? Nguyen");
	}

	@Test
	public void testGetFullName2() {
		String firstName = "Lam", lastName = "Nguyen";
		Person per = new Person(firstName, null);
		assertEquals(per.getFullName(), "Lam ?");
	}

	@Test
	public void testGetFullName3() {
		String firstName = "Lam", lastName = "Nguyen";
		Person per = new Person(firstName, lastName);
		assertEquals(per.getFullName(), "Lam Nguyen");
	}

	@Test
	public void testSearch1() {
		Person p = new Person("", "");
		Person searchPerson = new Person(null, "Minh");
		assertNull(p.search(searchPerson));
	}
	
	@Test
	public void testSearch2() {
		Person p = new Person("", "");
		Person searchPerson = new Person("Tran Quang", "Minh");
		assertNotNull(p.search(searchPerson));
	}
	
	@Test
	public void testSearch3() {
		Person p = new Person("", "");
		Person searchPerson = new Person("Tran Quang", null);
		assertNull(p.search(searchPerson));
	}
	
	@Test
	public void testSearch4() {
		Person p = new Person("", "");
		Person searchPerson = new Person("Tran Quang", null);
		assertNull(p.search(searchPerson));
	}
	
}
