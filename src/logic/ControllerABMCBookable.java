package logic;

import java.sql.Timestamp;
import java.util.ArrayList;
import data.DataBookable;
import entities.*;
public class ControllerABMCBookable {
	private DataBookable dataBookable= new DataBookable();
		
	public void RegisterBookable(Bookable b, Person per)throws Exception{
		per.hasPermission(AccessLevel.CREATE_BOOKABLE);
		dataBookable.add(b);
	}
	
	public void ModifyBookable(Bookable b, Person per)throws Exception{
		per.hasPermission(AccessLevel.MODIFY_BOOKABLE);
		dataBookable.update(b);
	}
	
	public void DeleteBookable(Bookable b, Person per)throws Exception{
		per.hasPermission(AccessLevel.DELETE_BOOKABLE);
		dataBookable.delete(b);
	}
	
	public Bookable getByName(Bookable b)throws Exception{
		return dataBookable.getByName(b);
	}
	
	public Bookable getById(Bookable b,Person user)throws Exception{
		user.hasPermission(AccessLevel.READ_BOOKABLE);
		return dataBookable.getById(b);
	}
	
	public Bookable getById(int id)throws Exception{
		return dataBookable.getById(id);
	}
	
	public Bookable getByName(String name)throws Exception{
		return dataBookable.getByName(name);
	}
	
	public ArrayList<Bookable> GetAll(Person per) throws Exception{
		per.hasPermission(AccessLevel.READ_BOOKABLE);
		return dataBookable.getAll();
	}

	public ArrayList<Bookable> getAllByType(TypeBookable bookable_type) throws Exception{
		return dataBookable.getAllByType(bookable_type);
	}

	public ArrayList<Bookable> getAllAvailable(TypeBookable bookable_type,Timestamp date) throws Exception{
		return dataBookable.getAvailableBookable(bookable_type, date);
	}
}
