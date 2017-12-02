package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entities.Bookable;
import entities.TypeBookable;
import util.AppDataException;

public class DataBookable {
	private Logger logger = LogManager.getLogger(getClass());
	
	public static Bookable buildBookable(ResultSet rs) throws SQLException{
		Bookable b= new Bookable();
		b.setId(rs.getInt("id_bookable"));
		b.setName(rs.getString("name_bookable"));
		TypeBookable type_bookable = DataTypeBookable.getById(rs.getInt("id_type_bookable")); 
	    b.setType(type_bookable); 
		return b;
	}
	
	public ArrayList<Bookable> getAll() throws Exception{
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Bookable> bookables= new ArrayList<Bookable>();
		try{
			 stmt = FactoryConection.getInstancia().getConn().createStatement();
			 rs=stmt.executeQuery("Select * from bookable");
			if(rs!=null){
				while(rs.next()){
					Bookable b = buildBookable(rs);
					bookables.add(b);
				}			
			}
		} catch(SQLException e) {
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
		return bookables;
	}
	
	public Bookable getById(Bookable bo) throws Exception{
		Bookable b= new Bookable();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt= FactoryConection.getInstancia().getConn().prepareStatement(		
					"select * from bookable where id_bookable=?");
			stmt.setInt(1, bo.getId()); 
			rs = stmt.executeQuery();
			if(rs!=null && rs.next()){
				b=buildBookable(rs);
			}
		} catch (SQLException e) {
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
		return b;
	}
	
	public Bookable getById(int id) throws Exception{
		Bookable b = new Bookable();
		b.setId(id);
		return getById(b);
	}
	
	public  Bookable getByName(Bookable b) throws Exception{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt= FactoryConection.getInstancia().getConn().prepareStatement(		
					"select * from bookable bk inner join type_bookable type on bk.id_type_bookable=type.id_type_bookable where name_bookable=?"); 
			stmt.setString(1, b.getName()); 
			rs = stmt.executeQuery();
			if(rs!=null && rs.next()){
				b.setType(new TypeBookable());
				b.getType().setId(rs.getInt("id_type_bookable"));
				b.getType().setName(rs.getString("name_type_bookable"));
				b.setId(rs.getInt("id_bookable"));
				b.setName(rs.getString("name_bookable"));
			}
		} catch (Exception e) {
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
		return b;
	}
	
	public  Bookable getByName(String name) throws Exception{
			Bookable b= new Bookable();
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
				stmt= FactoryConection.getInstancia().getConn().prepareStatement(		
						"select * from bookable where name_bookable=?");
				stmt.setString(1, name); 
				rs = stmt.executeQuery();
				if(rs!=null && rs.next()){
					b=buildBookable(rs);
				}
			} catch (Exception e) {
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
			return b;
		}
	
	public  ArrayList<Bookable> getAllByType(TypeBookable bookable_type) throws Exception{
		ArrayList<Bookable> bookables= new ArrayList<Bookable>();
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt= FactoryConection.getInstancia().getConn().prepareStatement(		
					"select * from bookable where id_type_bookable=?");
			stmt.setInt(1, bookable_type.getId()); 
			rs = stmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Bookable b=buildBookable(rs);
					bookables.add(b);
				}			
			}
		} catch (SQLException e) {
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
		return bookables;
	}

	public void add(Bookable b) throws Exception{
		ResultSet keyResultSet=null;
		PreparedStatement stmt=null;
		try {
			stmt= FactoryConection.getInstancia().getConn().prepareStatement(
			"insert into bookable (name_bookable, id_type_bookable) values(?,?)",
			PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, b.getName()); 
			stmt.setInt(2, b.getType().getId());
			stmt.executeUpdate();
			stmt.getGeneratedKeys();
			keyResultSet= stmt.getGeneratedKeys(); 
			if(keyResultSet!=null && keyResultSet.next()) {
				
				b.setId(keyResultSet.getInt(1));
			}
			
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw e;
		} finally {
			try {
				if(keyResultSet!=null) {keyResultSet.close();}
				if (stmt!=null){
					stmt.close();
				}
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				logger.log(Level.ERROR, e.getMessage());
				throw e;
			}	
		}
	}
	
	public void update(Bookable b)throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"update bookable set name_bookable=?, id_type_bookable=? where id_bookable=?");
			stmt.setString(1, b.getName());
			stmt.setInt(2, b.getType().getId());
			stmt.setInt(3, b.getId());
			
			int rowsAfected = stmt.executeUpdate();
			if (rowsAfected==0){
				throw new AppDataException(null, "Elemento Inexistente", Level.ERROR);
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

	public void delete(Bookable b) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"delete from bookable where name_bookable=?");
			stmt.setString(1, b.getName());
			int rowsAfected = stmt.executeUpdate();
			if (rowsAfected==0){
				throw new AppDataException(null, "Elemento inexistente", Level.ERROR);
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
	
	public ArrayList<Bookable> getAvailableBookable(TypeBookable type,Timestamp date)throws Exception{
//		long time = date.getTime();
//		int limit = type.getHourslimit();
		
		ArrayList<Bookable> bookables= new ArrayList<Bookable>();
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt= FactoryConection.getInstancia().getConn().prepareStatement(
					"select bk.id_bookable, bk.name_bookable " +  
					"from bookable bk  " +
					"where bk.id_type_bookable=? and bk.id_bookable not in ( " +
					"	select res.id_bookable " +
					"	from reservation res " +
					"	where ((? >= res.dateTimeReservation) and (? < addTime(res.dateTimeReservation, ?))) or " + 
					"    ((? <= res.dateTimeReservation) and (addTime(?, ?) > res.dateTimeReservation)) " +
					");");
			String fecha = date.toString();
			String limit = type.getHourslimit();
			stmt.setInt(1, type.getId());
			stmt.setString(2, fecha);
			stmt.setString(3, fecha);
			stmt.setString(4, limit);
			stmt.setString(5, fecha);
			stmt.setString(6, fecha);
			stmt.setString(7, limit);
			rs = stmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Bookable b = new Bookable();
					b.setId(rs.getInt("id_bookable"));
					b.setName(rs.getString("name_bookable"));
					bookables.add(b);
				}
			}
		} catch (SQLException e) {
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
		return bookables;
		
		//		select *
		//		from bookable
		//		inner join type_bookable
		//			on bookable.id_type_bookable = type_bookable.id_type_bookable
		//		inner join reservation res
		//			on res.id_bookable = bookable.id_bookable
		//		where reservas.date <> ? or (reservas.date == ? and (reservas.time + ? < ? or ? + ? < reservas.time))
		//		where reservas.date <> date_ or (reservas.date == date_ and (reservas.time + limit < time or time + limit < reservas.time))
				
				
		
		//		select id_bookable, name_bookable
		//		from bookable
		//		where bookable.id_type_bookable = ? and (		
		//			select Count(*)
		// 			from bookable
		// 			inner join reservation res
		//				on res.id_bookable = bookable.id_bookable
		// 			where bookable.id_type_bookable = ? and (reservas.date == ? and not (reservas.time + ? < ? or ? + ? < reservas.time))
		//			) = 0
		
	
		// proyector, 10, 13
		// limite 2
		// Horario 12
		
		//   10 + 2 <= 12 or 12 + 2 < 10 => True
		//   13 + 2 <= 12 or 12 + 2 < 13 => False
	}	
}