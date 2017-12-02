package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.PreparedStatement;

import entities.UserRole;

public class DataUserRoles {
	
	private Logger logger = LogManager.getLogger(getClass());
	
	private UserRole buildUserRole(ResultSet rs) throws SQLException{
		UserRole ur=new UserRole();
		ur.setId(rs.getInt("id_user_roles"));
		ur.setName(rs.getString("name_user_roles"));
		ur.setPrivileges(rs.getInt("privileges"));
		return ur;
	}
	
	public ArrayList<UserRole> getAll() throws Exception{
		ArrayList<UserRole> userRoles= new ArrayList<UserRole>();
		try{
			Statement stmt = FactoryConection.getInstancia().getConn().createStatement();
			ResultSet rs=stmt.executeQuery("Select * from user_roles");
			if(rs!=null){
				while(rs.next()){
					UserRole ur = buildUserRole(rs);
					userRoles.add(ur);
				}			
			}
		} catch(SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw e;		
		}
		return userRoles;
	}
	
	public UserRole getById(int id) throws Exception{
		UserRole ur = null;
		java.sql.PreparedStatement stmt=null;
		ResultSet rs=null;
		try{
			stmt = FactoryConection.getInstancia().getConn().prepareStatement(
					"Select * from user_roles where id_user_roles = ?");
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				ur = buildUserRole(rs);	
			}
		} catch(SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw e;		
		}
		return ur;
	}
	
	public List<UserRole> getAllByPermission(int permission){
		return null;
	}
	
	public UserRole getByName(String name){
		return null;
	}

}
