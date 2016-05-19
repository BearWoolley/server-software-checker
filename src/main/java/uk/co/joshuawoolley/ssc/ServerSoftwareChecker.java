package uk.co.joshuawoolley.ssc;

import uk.co.joshuawoolley.ssc.entities.User;
import uk.co.joshuawoolley.ssc.gui.Login;
import uk.co.joshuawoolley.ssc.util.PropertiesManager;

public class ServerSoftwareChecker {

    public static User user;

    /**
     * Main method to start the application
     * 
     * @param args
     *            List of arguments
     */
    public static void main(String[] args) {
	PropertiesManager prop = new PropertiesManager();
	prop.loadProperties();
	Login.main(null);
    }

}
