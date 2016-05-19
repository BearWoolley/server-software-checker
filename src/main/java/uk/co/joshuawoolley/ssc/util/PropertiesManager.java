package uk.co.joshuawoolley.ssc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Josh Woolley
 */
public class PropertiesManager {

    public static Properties properties;

    /**
     * Constructor for PropertiesManager
     */
    public PropertiesManager() {

    }

    /**
     * Load the properties file into the application
     */
    public void loadProperties() {
	properties = new Properties();
	String propFileName = "config.properties";

	InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

	if (inputStream != null) {
	    try {
		properties.load(inputStream);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

}
