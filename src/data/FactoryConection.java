package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FactoryConection {
	private static FactoryConection instance;
	private Logger logger = LogManager.getLogger(getClass());
	
	private FactoryConection() throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw e;
		}
	}
	
	public static FactoryConection getInstancia() throws Exception{
		if (FactoryConection.instance == null){
			FactoryConection.instance = new FactoryConection();
		}
		return FactoryConection.instance;
	}
	
	private Connection conn;
	private int cantConn=0;
	
	public Connection getConn() throws Exception{
		try {
			ProjectConfiguration pConf = new ProjectConfiguration();
			String dbhost = pConf.getProperty("dbhost");
			String dbuser = pConf.getProperty("dbuser");
			String dbpassword = pConf.getProperty("dbpassword");
			String dbport = pConf.getProperty("dbport");
			String dbname = pConf.getProperty("dbname");
			
			if(conn==null || conn.isClosed()){
				conn=  DriverManager.getConnection("jdbc:mysql://"+dbhost+":"+dbport+"/"+dbname+"?user="+dbuser+"&password="+dbpassword+"&characterEncoding=Latin1");
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw e;
		}
		cantConn++;
		return conn;		
	}
	
	public void releaseConn() throws Exception{
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw e;
		}	
	}
	
}