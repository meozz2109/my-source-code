package vnua.k62cnpm.xdptpm.libmanage.test.application;

public class PersonManagement {
	public String getMiddleName(Person p) {
		String middleName = null;
		
		// Kiem tra trong database
		Person checkPerson = p.search(p);
		
		if(checkPerson != null) {
			String firstName = p.getFirstName();
			
			int firstSpacePosition = firstName.indexOf(" ");
			middleName = firstName.substring(firstSpacePosition+1);
		}
		
		return middleName;
	}
}
