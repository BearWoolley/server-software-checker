package uk.co.joshuawoolley.ssc.user;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import uk.co.joshuawoolley.ssc.ServerSoftwareChecker;
import uk.co.joshuawoolley.ssc.database.DatabaseManager;
import uk.co.joshuawoolley.ssc.entities.User;

public class UserManager {

    private DatabaseManager dm;

    /**
     * Constructor for UserManager
     */
    public UserManager() {
	dm = new DatabaseManager();
    }

    /**
     * Checks to see if user is a valid user in the database
     * 
     * @param username
     *            Username of the account
     * @param password
     *            Password of the account
     * @return valid True if the details entered are correct or false if not
     *         valid
     */
    public boolean validateUser(String username, String password) {
	User user = dm.getUser(username);
	if (user != null) {
	    if (username.equals(user.getUsername()) && checkPasswords(password, user.getPassword())) {
		ServerSoftwareChecker.user = user;
		return true;
	    } else {
		return false;
	    }
	} else {
	    return false;
	}
    }

    /**
     * Add user to the database
     * 
     * @param username
     *            The username of the user
     * @param password
     *            The password of the user
     * @param ut
     *            The UserType of the user
     * @return true if successful or false if failed
     */
    public boolean addUser(String username, String password, UserType ut) {
	return dm.insertUser(username, hashPassword(password), ut);
    }

    /**
     * Delete user from the database
     * 
     * @param username
     *            The username to be deleteed
     * @return true if successful or false if failed
     */
    public boolean deleteUser(String username) {
	return dm.deleteUser(username);
    }

    /**
     * Update a user in the database
     * 
     * @param username
     *            The username of the user
     * @param password
     *            The password of the user
     * @param ut
     *            The usertype of the user
     * @return true if successful or false if failed
     */
    public boolean updateUser(String username, String password, UserType ut) {
	return dm.updateUser(username, hashPassword(password), ut);
    }

    /**
     * Get list of users
     * 
     * @return list of all username
     */
    public List<String> getUserNames() {
	List<String> users = new ArrayList<String>();
	List<User> listOfUsers = dm.getAllUsers();
	for (User user : listOfUsers) {
	    users.add(user.getUsername());
	}
	return users;
    }

    /**
     * Hash the user's password
     * 
     * @param password
     *            The password to be hashed
     * @return the hashed password
     */
    public String hashPassword(String password) {
	return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Check if the inputed password is the same as the hashed password in the
     * database
     * 
     * @param password
     *            The password the user entered
     * @param hashed
     *            The hashed password of the user in the database
     * @return true if they are equal or false if they are not
     */
    public boolean checkPasswords(String password, String hashed) {
	if (BCrypt.checkpw(password, hashed)) {
	    return true;
	} else {
	    return false;
	}
    }

}
