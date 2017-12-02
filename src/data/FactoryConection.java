package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FactoryConection {
	
	private static FactoryConection instancia;
	private Logger logger = LogManager.getLogger(getClass());
	
	ProjectConfiguration pConf = new ProjectConfiguration();
	
	private String dbhost = pConf.getProperty("dbhost");
	private String dbuser = pConf.getProperty("dbuser");
	private String dbpassword = pConf.getProperty("dbpassword");
	private String dbport = pConf.getProperty("dbport");
	private String dbname = pConf.getProperty("dbname");
	
	private FactoryConection() throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw e;
		}
	}
	
	public static FactoryConection getInstancia() throws Exception{
		if (FactoryConection.instancia == null){
			FactoryConection.instancia = new FactoryConection();
		}
		return FactoryConection.instancia;
	}
	
	private Connection conn;
	private int cantConn=0;
	
	public Connection getConn() throws Exception{		
		try { 
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