package data;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import entities.Person;
import entities.UserRole;
import util.AppDataException;
import util.Util;
import data.DataUserRoles;

public class DataPerson {
	
	private Person buildPerson(ResultSet rs) throws Exception{
			Person p = new Person();
		try {
			p.setId(rs.getInt("id_person"));
			p.setName(rs.getString("name_person"));
			p.setLastName(rs.getString("last_name_person"));
			p.setDni(rs.getString("dni"));
			p.setEnabled(rs.getBoolean("enable_person"));
			p.setUsername2(rs.getString("user_person"));
			p.setEmail(rs.getString("email"));
			p.setPassword2(rs.getString("password_person"));
			UserRole user_role = DataUserRoles.getById(rs.getInt("privileges"));
			p.setPrivileges(user_role.getPrivileges());
		} catch (Exception e) {
			throw e;
		}
		return p;
	}
	
	public List<Person> getAll() throws Exception{
		Statement stmt=null;
		ResultSet rs=null;
		List<Person> pers= new ArrayList<Person>();
		try{
			stmt= FactoryConection.getInstancia().getConn().createStatement();
			rs=stmt.executeQuery("Select * from person");
			if(rs!=null){
				while(rs.next()){
					Person p = buildPerson(rs);
					pers.add(p);
				}			
			}
		} catch(SQLException e) {
			throw e;		
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {	
				throw e;
			}
		}
		return pers;
	}
	
	public Person getById(int id) throws Exception{
		Person p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt = FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from person where id_person=?");
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				p=buildPerson(rs);
			}
			
		} catch (SQLException e) {
			throw e;
		} catch(Exception e) {
			throw e;
		} finally {	
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return p;
	}
	
	public Person getByEmail(String email)throws Exception{
		Person p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from person where email=?");
			stmt.setString(1, email);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				p=buildPerson(rs);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return p;
	}
	
	public ArrayList<Person> getAllEnable()throws Exception{
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Person> pers= new ArrayList<Person>();
		try{
			stmt= FactoryConection.getInstancia().getConn().createStatement();
			rs=stmt.executeQuery("Select * from person where enable_person=1");
			if(rs!=null){
				while(rs.next()){
					Person p = buildPerson(rs);
					pers.add(p);
				}			
			}
		} catch(SQLException e) {
			throw e;		
		} finally	{	
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {	
				e.printStackTrace();
			}
		}	
		return pers;
	}
	
	public Person getByUsername(Person per)throws Exception{
		Person p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from person where user_person=?");
			stmt.setString(1, per.getUsername());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				p=buildPerson(rs);
			}
		} catch (SQLException e) {
			throw e;
		} finally {	
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return p;
	}
	
	public Person getByDni(Person per)throws Exception{
		Person p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from person where dni=?");
			stmt.setString(1, per.getDni());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				p=buildPerson(rs);
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return p;
	}
	
	public Person getByDni(String dni)throws Exception{
		Person p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from person where dni=?");
			stmt.setString(1,dni);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				p=buildPerson(rs);
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return p;
	}	
	
	public void add(Person p) throws Exception{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"insert into person(dni, name_person,last_name_person, enable_person,user_person,password_person,email) values (?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, p.getDni());
			stmt.setString(2, p.getName());
			stmt.setString(3, p.getLastName());
			stmt.setBoolean(4, p.isEnabled());
			stmt.setString(5, p.getUsername());
			stmt.setString(6, p.getPassword());
			stmt.setString(7, p.getEmail());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				p.setId(keyResultSet.getInt(1));
			}
		} catch (SQLException e) {
			throw e;
		} finally{
			try {
				if(keyResultSet!=null)keyResultSet.close();
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete(Person p) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"delete from person where dni=?");
			stmt.setString(1, p.getDni());
			int rowsAfected = stmt.executeUpdate();
			if (rowsAfected==0){
				throw new AppDataException(null, "Persona inexistente", Level.ERROR);
			}
			
		
		} catch (AppDataException e) {
			throw e;
		} finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void update(Person p)throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"update person set name_person=?, last_name_person=?, user_person=?, email=?, password_person=?, enable_person=? where dni=?");
			stmt.setString(1, p.getName());
			stmt.setString(2, p.getLastName());
			stmt.setString(3, p.getUsername());
			stmt.setString(4, p.getEmail());
			stmt.setString(5, p.getPassword());
			stmt.setBoolean(6, p.isEnabled());
			stmt.setString(7, p.getDni());
			
			int rowsAfected = stmt.executeUpdate();
			if (rowsAfected==0){
				throw new AppDataException(null, "Persona inexistente", Level.ERROR);
			}	
		} catch (AppDataException e) {
			throw e;
		} finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
