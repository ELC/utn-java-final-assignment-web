package entities;

public class UserRole {
	
	private int id;
	private String name;
	private int privileges;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrivileges() {
		return privileges;
	}
	public void setPrivileges(int privileges) {
		this.privileges = privileges;
	}

}
