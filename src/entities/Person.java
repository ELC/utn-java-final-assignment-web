package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import entities.AccessLevel;
import util.Util;

public class Person implements Serializable{
	
	private int id;
	private String dni;
	private String name;
	private String lastName;
	private String username;
	private String email;
	private String password;
	private boolean enabled;
	private List<AccessLevel> privileges;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = Util.hash(username);
	}
	public boolean checkUserName(Person p){
		return Objects.equals(this.getUsername(), Util.hash(p.getUsername()));
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
		this.password = Util.hash(password);
	}
	public boolean checkPassword(Person p){
		return Objects.equals(this.getPassword(), Util.hash(p.getPassword()));
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public List<AccessLevel> getPrivileges() {
		return this.privileges;	
	}
	
	public void setPrivileges(int privileges) {
		this.privileges = AccessLevel.parsePermissions(privileges);
	}

	public boolean hasPermission(AccessLevel permission){ 
	    return getPrivileges().contains(permission);
	}

}
