package logic;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import data.DataTypeBookable;
import entities.*;
import static java.time.temporal.ChronoUnit.DAYS;



public class ControllerABMCTypeBookable{
	private DataTypeBookable dataTypeBookable = new DataTypeBookable();
	private Application app = Application.getInstancia();
	
	

	public void RegisterTypeBookable(TypeBookable b)throws Exception{
		//app.isLoggedIn();
//		app.hasPermission(AccessLevel.CREATE_TYPEBOOKABLE);
		dataTypeBookable.add(b);
	}
	
	public void ModifyTypeBookable(TypeBookable b)throws Exception{
		//app.isLoggedIn();	
//		app.hasPermission(AccessLevel.MODIFY_TYPEBOOKABLE);
		dataTypeBookable.update(b);
	}
	
	public void DeleteTypeBookable(TypeBookable b)throws Exception{
		//app.isLoggedIn();
//		app.hasPermission(AccessLevel.DELETE_TYPEBOOKABLE);
		dataTypeBookable.delete(b);
	}
	
	public ArrayList<TypeBookable> getAll()throws Exception{
		return dataTypeBookable.getAll();
	}
	
	public ArrayList<TypeBookable> getAllByUser() throws Exception{
		ArrayList<TypeBookable> all = dataTypeBookable.getAll();
		if (!app.hasPermission(AccessLevel.CREATE_SPECIAL_RESERVATION)){
			all.removeIf(s -> s.getRestriction() == 1);
		}
		return all;
	}
	
	public ArrayList<TypeBookable> getAllByDate(Date date)throws Exception{
		ArrayList<TypeBookable> all = getAllByUser();
		all.removeIf(s -> DAYS.between(LocalDate.now(), date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) > s.getDayslimit() && s.getDayslimit() != 0);
		return all;
	}

	public TypeBookable getByName(TypeBookable t) throws Exception{
		return dataTypeBookable.getByName(t);	
	}	
}
