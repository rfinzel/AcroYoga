package objects;

import java.sql.Date;
import java.sql.Timestamp;

public class Member {
	private int id;
	private Boolean admin;
	private String email;
	private String password;
	private String name;
	private String lastname;
	private Date birthday;
	
	public Member(int id, Boolean admin, String email, String password, String name, String lastname, Date birthday) {
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

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
}
