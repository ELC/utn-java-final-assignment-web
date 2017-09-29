package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConection {
	
	private static FactoryConection instancia;
	
	private String dbhost = ProjectConfiguration.getProperty("dbhost");
	private String dbuser = ProjectConfiguration.getProperty("dbuser");
	private String dbpassword = ProjectConfiguration.getProperty("dbpassword");
	private String dbport = ProjectConfiguration.getProperty("dbport");
	private String dbname = ProjectConfiguration.getProperty("dbname");
	
	private FactoryConection(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
	}
	
	public static FactoryConection getInstancia(){
		if (FactoryConection.instancia == null){
			FactoryConection.instancia = new FactoryConection();
		}
		return FactoryConection.instancia;
	}
	
	private Connection conn;
	private int cantConn=0;
	
	public Connection getConn(){		
		try { 
			if(conn==null || conn.isClosed()){
				conn=  DriverManager.getConnection("jdbc:mysql://"+dbhost+":"+dbport+"/"+dbname+"?user="+dbuser+"&password="+dbpassword);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cantConn++;
		return conn;		
	}
	
	public void releaseConn(){
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
}