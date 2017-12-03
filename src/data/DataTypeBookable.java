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
		return t;
	}
	
	public void add(TypeBookable b) throws Exception{
		ResultSet keyResultSet=null;
		PreparedStatement stmt=null;
		try {
			stmt= FactoryConection.getInstancia().getConn().prepareStatement(
					"insert into type_bookable (name_type_bookable,restriction,hours_limit,days_limit) values(?,?,?,?) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
	
			stmt.setString(1, b.getName()); // los numeros corresponden a los de ? de la query//
			stmt.setInt(2, b.getRestriction());
			stmt.setString(3, b.getHourslimit());
			stmt.setInt(4, b.getDayslimit());
			stmt.executeUpdate();
			stmt.getGeneratedKeys();
			keyResultSet= stmt.getGeneratedKeys(); //Preguntar que hace?
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
					"update type_bookable set name_type_bookable=?, restriction=?, hours_limit= ?, days_limit=? where id_type_bookable=?");
			stmt.setString(1, b.getName());
			stmt.setInt(2, b.getRestriction());
			stmt.setString(3, b.getHourslimit());
			stmt.setInt(4, b.getDayslimit());
			stmt.setInt(5, b.getId());
			int rowsAfected = stmt.executeUpdate();
			if (rowsAfected==0){
				throw new AppDataException(null, "Tipo elemento inexistente", Level.ERROR);
			
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
				throw new AppDataException(null, "Tipo elemento inexistente", Level.ERROR);
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
}