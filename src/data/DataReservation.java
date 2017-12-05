package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entities.Person;
import entities.Reservation;

public class DataReservation {
	
	private Logger logger = LogManager.getLogger(getClass());
	private DataPerson dataPer= new DataPerson();
	private DataBookable databook=new DataBookable();
	
	private Reservation buildReservation(ResultSet rs) throws Exception{
		Reservation re= new Reservation();
		re.setPerson(dataPer.getById(rs.getInt("id_person")));
		re.setBookable(databook.getById(rs.getInt("id_bookable")));
		re.setDate(rs.getTimestamp("dateTimeReservation"));
		re.setDetail(rs.getString("detail"));
		re.setId(rs.getInt("id_reservation"));
		return re;
	}
	
	public ArrayList<Reservation> getAll() throws Exception{
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Reservation> res= new ArrayList<Reservation>();
		try{
			stmt= FactoryConection.getInstancia().getConn().createStatement();
			rs=stmt.executeQuery("Select * from reservation order by dateTimeReservation");
			if(rs!=null){
				while(rs.next()){
					Reservation re = buildReservation(rs);
					res.add(re);
				}			
			}
		} catch(SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw e;
		} finally{	
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {	
				logger.log(Level.ERROR, e.getMessage());
				throw e;
			}
		}	
		return res;
	}
	
	public void add(Reservation re)throws Exception{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"insert into reservation(id_person, id_bookable, dateTimeReservation, detail) values (?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setInt(1, re.getPerson().getId());
			stmt.setInt(2, re.getBookable().getId());
			stmt.setTimestamp(3, re.getDate());
			stmt.setString(4, re.getDetail());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				re.setId(keyResultSet.getInt(1));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw e;
		} finally {	
			try {
				if(keyResultSet!=null)keyResultSet.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				logger.log(Level.ERROR, e.getMessage());
				throw e;
			}
		}
	}
	
	public List<Reservation> getByIdPerson(Person p)throws Exception{
		ArrayList<Reservation> res = new ArrayList<Reservation>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from Reservation where id_person=?  order by dateTimeReservation");
			stmt.setInt(1, p.getId());
			rs=stmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Reservation re = buildReservation(rs);
					res.add(re);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw e;
		} finally {	
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				logger.log(Level.ERROR, e.getMessage());
				throw e;
			}
		}	
		return res;
	}
	
	public void delete(Reservation re) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"delete from reservation where id_reservation=?");
			stmt.setInt(1, re.getId());
			stmt.executeUpdate();		
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw e;
		} finally{	
			try {
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				logger.log(Level.ERROR, e.getMessage());
				throw e;
			}
		}
	}

	public void update(Reservation r) {}

	public Reservation getByIDRes(int id) {
		return null;
	}
}
