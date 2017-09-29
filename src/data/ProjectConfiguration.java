package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class ProjectConfiguration {

	private static String filename = "config.properties";
	private static Map<String, String> properties = null; //Map <String, String,  es un diccionario//

	public static void setProperty(String key, String value) {
		if (ProjectConfiguration.properties == null) {
			ProjectConfiguration.resetProperties();
		}
		ProjectConfiguration.properties.put(key, value);
	}

	public static String getProperty(String key) {
		if (ProjectConfiguration.properties == null){
			ProjectConfiguration.loadProperties();
		}

		return ProjectConfiguration.properties.get(key);
	}

	public static void storeProperties() {
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			output = new FileOutputStream(filename);

			for (Map.Entry<String, String> entry : ProjectConfiguration.properties.entrySet())
			{
				prop.setProperty(entry.getKey(), entry.getValue());
			}

			prop.store(output, null);

			ProjectConfiguration.flushProperties();

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	  }

	public static void flushProperties(){
		ProjectConfiguration.properties = null;
	}

	private static void resetProperties(){
		ProjectConfiguration.properties = new Hashtable<String, String>();
	}

	private static void loadProperties() {

		Properties prop = new Properties();
		InputStream input = null;


		try {
			input = new FileInputStream(filename);

			prop.load(input);

			ProjectConfiguration.resetProperties();


			for (Entry<Object, Object> entry : prop.entrySet())
			{
				ProjectConfiguration.properties.put(
						(String)entry.getKey(),
						(String)entry.getValue()
						);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	  }
}
