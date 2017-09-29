package logic;

import entities.AccessLevel;
import entities.Person;

public class Application {
	private static Application instancia;
	
	private Person activePerson;
	
	public Person getActivePerson() {
		return activePerson;
	}

	public void setActivePerson(Person activePerson) {
		this.activePerson = activePerson;
	}
	
	public void LogOutPerson() throws Exception{
		isLoggedIn();
		activePerson = null;
	}

	public boolean isLoggedIn() throws Exception{
		return activePerson==null;
	}
	
	public boolean hasPermission(AccessLevel permission){
		return activePerson.getPrivileges().contains(permission);
	}
	
	private Application(){}
	
	public static Application getInstancia(){
		if (Application.instancia == null){
			Application.instancia = new Application();
		}
		return Application.instancia;
	}
}
