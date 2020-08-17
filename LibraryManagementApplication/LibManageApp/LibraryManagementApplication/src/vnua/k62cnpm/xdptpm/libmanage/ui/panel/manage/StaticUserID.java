package vnua.k62cnpm.xdptpm.libmanage.ui.panel.manage;

public class StaticUserID {
	private static StaticUserID instance;
	public static String userID;

	public StaticUserID() {

	}

	public static StaticUserID getInstance() {
		if (instance == null) {
			instance = new StaticUserID();
		}
		return instance;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

}
