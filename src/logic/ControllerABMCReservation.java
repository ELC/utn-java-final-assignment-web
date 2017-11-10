package logic;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.DataReservation;
import entities.*;

public class ControllerABMCReservation {
	private Application app = Application.getInstancia();
	private DataReservation dataRes = new DataReservation();
	
	public void RegisterReservation(Reservation re)throws Exception{
		//app.isLoggedIn();	
//		app.hasPermission(AccessLevel.CREATE_RESERVATION);
		dataRes.add(re);
	}
	
	public ArrayList<Reservation> getAllReservation()throws Exception{
		app.isLoggedIn();
//		app.hasPermission(AccessLevel.ACCESS_RESERVATION);
		return dataRes.getAll();
	}
	
	public List<Reservation> getAllByUser() throws Exception{
		app.isLoggedIn();
//		app.hasPermission(AccessLevel.DELETE_RESERVATION);
		ArrayList<Reservation> reservations;
		if (app.hasPermission(AccessLevel.READ_ALLBOOKING)){
			reservations = (ArrayList<Reservation>) dataRes.getAll();
		} else{
			reservations = (ArrayList<Reservation>) dataRes.getByIdPerson(app.getActivePerson());
		}
		Timestamp now = new Timestamp((new Date()).getTime());
		
		reservations.removeIf(s -> s.getDate().before(now));
		return reservations;		
	}
	
	public List<Reservation> getAllByUser(Person p) throws Exception{

		ArrayList<Reservation> reservations;
		if (false){ // app.hasPermission(AccessLevel.READ_ALLBOOKING) revisar para web
			reservations = (ArrayList<Reservation>) dataRes.getAll();
		} else{
			reservations = (ArrayList<Reservation>) dataRes.getByIdPerson(p);
		}
		Timestamp now = new Timestamp((new Date()).getTime());
		
		reservations.removeIf(s -> s.getDate().before(now));
		return reservations;		
	}
	

	public void DeleteReservation(Reservation re)throws Exception{
		app.isLoggedIn();
//		app.hasPermission(AccessLevel.DELETE_RESERVATION);
		dataRes.delete(re);
	}
}
