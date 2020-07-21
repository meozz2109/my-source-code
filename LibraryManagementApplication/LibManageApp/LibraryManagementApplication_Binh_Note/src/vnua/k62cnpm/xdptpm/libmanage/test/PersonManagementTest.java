package vnua.k62cnpm.xdptpm.libmanage.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import vnua.k62cnpm.xdptpm.libmanage.test.application.Person;
import vnua.k62cnpm.xdptpm.libmanage.test.application.PersonManagement;

public class PersonManagementTest {
	PersonManagement pm;

	@Before
	public void setUp() throws Exception {
		pm = new PersonManagement();
	}

	@After
	public void tearDown() throws Exception {
		pm = null;
	}

	@Test
	public void testGetMiddleName() {
		// Tao doi tuong gia
		Person personMock = Mockito.mock(Person.class);
		Person p = new Person("Tran Quang", "Minh");

		// Gia lap ket qua search (ton tai trong database)
		Mockito.when(personMock.search(p)).thenReturn(p);
		
		// Thuc hien search sau khi da gia lap
		Person searchPerson = personMock.search(p);
		String middleName = pm.getMiddleName(searchPerson);
		assertEquals("Quang", middleName);
	}
	
}
