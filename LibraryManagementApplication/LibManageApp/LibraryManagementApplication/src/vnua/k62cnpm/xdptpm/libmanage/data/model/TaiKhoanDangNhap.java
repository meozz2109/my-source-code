package vnua.k62cnpm.xdptpm.libmanage.data.model;

public class TaiKhoanDangNhap {
	private String userName, password, role;

	
	public TaiKhoanDangNhap(String userName, String password, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}
	
}
