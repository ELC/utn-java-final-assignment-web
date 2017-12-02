package logic;

import java.util.List;

import org.apache.logging.log4j.Level;

import data.DataPerson;
import entities.*;
import util.exceptions.LoginAppDataException;


public class ControllerABMCPerson {
	private DataPerson dataPer = new DataPerson();
	
	public Person LoginPerson(Person p) throws Exception{
		Person per= dataPer.getByUsername(p);
		if (per==null) {
			throw new LoginAppDataException("Nombre de usuario Incorrecto", Level.ERROR);
		}
		if (!per.isEnabled()) {
			throw new LoginAppDataException("Usuario no habilitado", Level.ERROR);
		}
		if(!per.checkPassword(p)){
			throw new LoginAppDataException("Contraseña Incorrecta", Level.ERROR);
		}
		return per;
	}
	
	public void RegisterPerson(Person p, Person per)throws Exception{
		per.hasPermission(AccessLevel.CREATE_USER);
		dataPer.add(p);
	}
	
	public void ModifyPerson(Person p, Person per)throws Exception{
		per.hasPermission(AccessLevel.MODIFY_USER);
		dataPer.update(p);
	}
	
	public void DeletePerson(Person p, Person per)throws Exception{
		per.hasPermission(AccessLevel.DELETE_USER);
		dataPer.delete(p);
	}

	public Person getByDni(Person p) throws Exception{
		return dataPer.getByDni(p);
	}
	
	public Person getByDni(String dni) throws Exception{
		return dataPer.getByDni(dni);
	}
	
	public List<Person> getAll() throws Exception{
		return dataPer.getAll();
	}
}
