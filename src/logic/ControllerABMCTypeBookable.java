package logic;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import data.DataTypeBookable;
import entities.*;
import util.exceptions.AccessDeniedException;

import static java.time.temporal.ChronoUnit.DAYS;

import java.sql.Timestamp;



public class ControllerABMCTypeBookable{
	private DataTypeBookable dataTypeBookable = new DataTypeBookable();

	public void RegisterTypeBookable(TypeBookable b, Person per)throws Exception{
		per.hasPermission(AccessLevel.CREATE_TYPEBOOKABLE);
		dataTypeBookable.add(b);
	}
	
	public void ModifyTypeBookable(TypeBookable b, Person per)throws Exception{
		per.hasPermission(AccessLevel.MODIFY_TYPEBOOKABLE);
		dataTypeBookable.update(b);
	}
	
	public void DeleteTypeBookable(TypeBookable b, Person per)throws Exception{
		per.hasPermission(AccessLevel.DELETE_TYPEBOOKABLE);
		dataTypeBookable.delete(b);
	}
	
	public ArrayList<TypeBookable> getAll()throws Exception{
		return dataTypeBookable.getAll();
	}
	
	public TypeBookable getById(int id, Person per)throws Exception{
		per.hasPermission(AccessLevel.READ_TYPEBOOKABLE);
		return dataTypeBookable.getById(id);
	}
	public ArrayList<TypeBookable> getAllByUser(Person per) throws Exception{
		per.hasPermission(AccessLevel.READ_TYPEBOOKABLE);
		ArrayList<TypeBookable> all = dataTypeBookable.getAllByMaxBookings(per);
		try{
			per.hasPermission(AccessLevel.CREATE_SPECIAL_RESERVATION);
		} catch (AccessDeniedException e){
			all.removeIf(s -> s.getRestriction() == 1);
		} finally {
			return all;
		}
		
		
	}
	
	public ArrayList<TypeBookable> getAllByDate(Timestamp date, Person per)throws Exception{
		ArrayList<TypeBookable> all = getAllByUser(per);
		all.removeIf(s -> DAYS.between(LocalDate.now(), date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) > s.getDayslimit() && s.getDayslimit() != 0);
		return all;
	}

	public TypeBookable getByName(TypeBookable t, Person per) throws Exception{
		per.hasPermission(AccessLevel.READ_TYPEBOOKABLE);
		TypeBookable tp = dataTypeBookable.getByName(t);
		if (tp != null && tp.getRestriction() == 1 && !per.hasPermission(AccessLevel.CREATE_SPECIAL_RESERVATION)){
			throw new Exception("You don't have enough permissions to view this item");
		}
		return tp;
	}	
}
