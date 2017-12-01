package logic;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.DataReservation;
import entities.*;

public class ControllerABMCReservation {
	private DataReservation dataRes = new DataReservation();
	
	public void RegisterReservation(Reservation re, Person per)throws Exception{
		per.hasPermission(AccessLevel.CREATE_RESERVATION);
		dataRes.add(re);
	}
	
	public ArrayList<Reservation> getAllReservation(Person per)throws Exception{
		per.hasPermission(AccessLevel.READ_RESERVATION);
		return dataRes.getAll();
	}

	public List<Reservation> getAllByUser(Person per) throws Exception{

		ArrayList<Reservation> reservations;
		if (per.hasPermission(AccessLevel.READ_ALLBOOKING)){
			reservations = (ArrayList<Reservation>) dataRes.getAll();
		} else{
			reservations = (ArrayList<Reservation>) dataRes.getByIdPerson(per);
		}
		Timestamp now = new Timestamp((new Date()).getTime());
		
		reservations.removeIf(s -> s.getDate().before(now));
		return reservations;		
	}
	

	public void DeleteReservation(Reservation re, Person per)throws Exception{
		per.hasPermission(AccessLevel.DELETE_RESERVATION);
		dataRes.delete(re);
	}
}
