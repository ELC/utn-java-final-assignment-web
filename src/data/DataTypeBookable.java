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
import entities.TypeBookable;
import util.exceptions.AppDataException;

public class DataTypeBookable {
	
	private Logger logger = LogManager.getLogger(getClass());
	
	private static TypeBookable buildTypeBookable(ResultSet rs) throws SQLException{
		TypeBookable tb= new TypeBookable();
		tb.setId(rs.getInt("id_type_bookable"));
		tb.setName(rs.getString("name_type_bookable"));
		tb.setHourslimit(rs.getString("hours_limit"));
		tb.setDayslimit(rs.getInt("days_limit"));
		tb.setRestriction(rs.getInt("restriction"));
		tb.setMaxBookings(rs.getInt("max_bookings"));
		return tb;
	}
	
	public ArrayList<TypeBookable> getAll()throws Exception{
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<TypeBookable> typeBookables= new ArrayList<TypeBookable>();
		try{
			 stmt = FactoryConection.getInstancia().getConn().createStatement();
			 rs=stmt.executeQuery("Select * from type_bookable");
			if(rs!=null){
				while(rs.next()){
					TypeBookable tb = buildTypeBookable(rs);
					typeBookables.add(tb);
				}			
			}
		} catch(Exception e) {
			logger.log(Level.ERROR, e.getMessage());
			throw e;			
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {	
				logger.log(Level.ERROR, e.getMessage());
				throw e;
			}
		}
		
		
		return typeBookables;
	}
	
	public TypeBookable getById(int id) throws Exception{
		TypeBookable t=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from type_bookable where id_type_bookable=?");
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				t=buildTypeBookable(rs);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new AppDataException(null,"An error has occurred in the database, contact the system admin",Level.ERROR);
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
		return t;
	}
	
	public TypeBookable getByName(TypeBookable type)throws Exception{
		TypeBookable t=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from type_bookable where name_type_bookable=?");
			stmt.setString(1, type.getName());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				t=buildTypeBookable(rs);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new AppDataException(null,"An error has occurred in the database, contact the system admin",Level.ERROR);
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
		return t;
	}
	
	public void add(TypeBookable b) throws Exception{
		ResultSet keyResultSet=null;
		PreparedStatement stmt=null;
		try {
			stmt= FactoryConection.getInstancia().getConn().prepareStatement(
					"insert into type_bookable (name_type_bookable,restriction,hours_limit,days_limit,max_bookings) values(?,?,?,?,?) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
	
			stmt.setString(1, b.getName()); // los numeros corresponden a los de ? de la query//
			stmt.setInt(2, b.getRestriction());
			stmt.setString(3, b.getHourslimit());
			stmt.setInt(4, b.getDayslimit());
			stmt.setInt(5, b.getMaxBookings());
			stmt.executeUpdate();
			stmt.getGeneratedKeys();
			keyResultSet= stmt.getGeneratedKeys(); //Preguntar que hace?
			if(keyResultSet!=null && keyResultSet.next()) {
		
				b.setId(keyResultSet.getInt(1));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new AppDataException(null,"An error has occurred in the database, contact the system admin",Level.ERROR);
		} finally {
			try {
				if(keyResultSet!=null) {keyResultSet.close();}
					if (stmt!=null){
						stmt.close();
				}
				FactoryConection.getInstancia().releaseConn();
			} 
			catch (SQLException e) {
				logger.log(Level.ERROR, e.getMessage());
				throw e;
			}	
		}
	}
	
	public void update(TypeBookable b)throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"update type_bookable set name_type_bookable=?, restriction=?, hours_limit= ?, days_limit=?, max_bookings=? where id_type_bookable=?");
			stmt.setString(1, b.getName());
			stmt.setInt(2, b.getRestriction());
			stmt.setString(3, b.getHourslimit());
			stmt.setInt(4, b.getDayslimit());
			stmt.setInt(5, b.getMaxBookings());
			stmt.setInt(6, b.getId());
			int rowsAfected = stmt.executeUpdate();
			if (rowsAfected==0){
				throw new AppDataException(null, "The item doesn't exists", Level.ERROR);
			}
		} catch (AppDataException apd) {
			throw apd;
		} finally {	
			try {
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				logger.log(Level.ERROR, e.getMessage());
				throw e;
			}
		}
	}
	public void delete(TypeBookable b)throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"delete from type_bookable where id_type_bookable=?");
			stmt.setInt(1, b.getId());
			int rowsAfected = stmt.executeUpdate();
			if (rowsAfected==0){
				throw new AppDataException(null, "The item doesn't exists", Level.ERROR);
			}	
		} catch (AppDataException apd) {
			throw apd;
		} finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				logger.log(Level.ERROR, e.getMessage());
				throw e;
			}
		}
	}

	public ArrayList<TypeBookable> getAllByMaxBookings(Person per) throws Exception {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<TypeBookable> typeBookables= new ArrayList<TypeBookable>();
		try{
			stmt = FactoryConection.getInstancia().getConn()
					.prepareStatement("select tb.id_type_bookable, tb.restriction, tb.hours_limit, \r\n" + 
							"	tb.days_limit, tb.name_type_bookable, tb.max_bookings, \r\n" + 
							"	count(case res.id_person when ? then 1 else null end) as c\r\n" + 
							"from type_bookable tb\r\n" + 
							"inner join bookable b\r\n" + 
							"	on b.id_type_bookable = tb.id_type_bookable\r\n" + 
							"left join reservation res\r\n" + 
							"	on b.id_bookable = res.id_bookable\r\n" + 
							"where (res.id_person = ? and res.dateTimeReservation > current_timestamp()) \r\n" + 
							"	or (res.id_person != ? or res.id_person is null)\r\n" + 
							"group by 1, 2, 3, 4, 5, 6\r\n" + 
							"having c < tb.max_bookings;");
			stmt.setInt(1, per.getId());
			stmt.setInt(2, per.getId());
			stmt.setInt(3, per.getId());
			rs = stmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					TypeBookable tb = buildTypeBookable(rs);
					typeBookables.add(tb);
				}			
			}
		} catch(SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new AppDataException(null,"An error has occurred in the database, contact the system admin",Level.ERROR);	
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {	
				logger.log(Level.ERROR, e.getMessage());
				throw e;
			}
		}
		return typeBookables;
	}
}