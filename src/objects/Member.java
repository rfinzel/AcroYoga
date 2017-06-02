package objects;

public class Member {
	private int id;
	private String admin;
	private String email;
	private String password;
	private String name;
	private String lastname;
	private String birthday;
	
	public Member(int id, String admin, String email, String password, String name, String lastname, String birthday) {
		this.id = id;
		this.admin = admin;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.birthday = birthday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
}
