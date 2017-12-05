package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class ProjectConfiguration {
	private Logger logger = LogManager.getLogger(getClass());
	private static String filename = "C:\\Repositories\\utn-java-final-assignment-web\\WebContent\\WEB-INF\\config.properties";
	private String filename2 = "C:\\repositories\\utn-java-final-assignment-web\\WebContent\\WEB-INF\\config.properties";
	private static Map<String, String> properties = null; //Map <String, String,  es un diccionario//

	public void setProperty(String key, String value) {
		if (properties == null) {
			resetProperties();
		}
		properties.put(key, value);
	}

	public String getProperty(String key) throws Exception {
		if (properties == null){
			loadProperties();
		}
		return properties.get(key);
	}

	public void storeProperties() throws Exception {
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			output = new FileOutputStream(filename);

			for (Map.Entry<String, String> entry : properties.entrySet())
			{
				prop.setProperty(entry.getKey(), entry.getValue());
			}

			prop.store(output, null);

			flushProperties();

		} catch (IOException io) {
			logger.log(Level.ERROR, io.getMessage());
			throw io;
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					logger.log(Level.ERROR, e.getMessage());
					throw e;
				}
			}
		}
	}

	public void flushProperties(){
		properties = null;
	}

	private void resetProperties(){
		properties = new Hashtable<String, String>();
	}
	
	public Properties getProperties() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream(filename);

			prop.load(input);
			
			return prop;

		} catch (IOException ex) {
			logger.log(Level.ERROR, ex.getMessage());
			throw ex;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.log(Level.ERROR, e.getMessage());
					throw e;
				}
			}
		}
	}

	private void loadProperties() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(filename);

			prop.load(input);

			resetProperties();

			for (Entry<Object, Object> entry : prop.entrySet()){
				ProjectConfiguration.properties.put((String)entry.getKey(),	(String)entry.getValue());
			}
		} catch (IOException ex) {
			logger.log(Level.ERROR, ex.getMessage());
			throw ex;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.log(Level.ERROR, e.getMessage());
					throw e;
				}
			}
		}
	}
}
