package uk.co.joshuawoolley.ssc.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import uk.co.joshuawoolley.ssc.entities.Server;
import uk.co.joshuawoolley.ssc.entities.User;
import uk.co.joshuawoolley.ssc.user.UserType;

/**
 * @author Josh Woolley
 */
public class DatabaseManager {

    private EntityManagerFactory emf;
    private EntityManager manager;
    private Query query;

    /**
     * Constructor for DatabaseManager
     */
    public DatabaseManager() {
	emf = Persistence.createEntityManagerFactory("ServerSoftwareCheckerPU");
	manager = emf.createEntityManager();
    }

    /**
     * Get the Entity Manager
     * 
     * @return the manager
     */
    public EntityManager getManager() {
	return manager;
    }

    /**
     * Set the Entity Manager
     * 
     * @param manager
     *            the manager to set
     */
    public void setManager(EntityManager manager) {
	this.manager = manager;
    }

    /**
     * Get a list of all servers in the database
     * 
     * @return List<Servers> List of all servers
     */
    @SuppressWarnings("unchecked")
    public List<Server> getAllServers() {
	query = manager.createNamedQuery("Server.findAll");
	return query.getResultList();
    }

    /**
     * Get single server from the database
     * 
     * @param hostname
     *            The hostname of the server
     * @return Servers The Servers object that was requested
     */
    public Server getServer(String hostname) {
	query = manager.createNamedQuery("Server.findByHostname");
	query.setParameter("hostname", hostname);
	return (Server) query.getSingleResult();
    }

    /**
     * Insert a Server into the database
     * 
     * @param hostname
     *            The hostname of the server
     * @param port
     *            The port of the server
     * @param username
     *            The username to login to the server
     * @param password
     *            The encrypted password to access the server
     * @param location
     *            The location of were the apps are located
     * @return true
     */
    public boolean insertServer(String hostname, int port, String username, byte[] password, String location) {
	Server server = new Server(hostname, port, username, password, location);
	manager.getTransaction().begin();
	manager.persist(server);
	manager.getTransaction().commit();
	return true;
    }

    /**
     * Delete a Server from the database
     * 
     * @param hostname
     *            The hostname to delete
     * @return true
     */
    public boolean deleteServer(String hostname) {
	Server server = manager.find(Server.class, hostname);
	manager.getTransaction().begin();
	manager.remove(server);
	manager.getTransaction().commit();
	return true;
    }

    /**
     * Update a server with new fields
     * 
     * @param hostname
     *            The server's hostname
     * @param port
     *            The server's port
     * @param username
     *            The servers's username
     * @param password
     *            The server's encrypted password
     * @param location
     *            The server's apps location
     * @return true
     */
    public boolean updateServer(String hostname, int port, String username, byte[] password, String location) {
	Server server = new Server(hostname, port, username, password, location);
	manager.getTransaction().begin();
	manager.merge(server);
	manager.getTransaction().commit();
	return true;
    }

    /**
     * Get all users from the database
     * 
     * @return List<Users> List of all users
     */
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
	query = manager.createNamedQuery("User.findAll");
	return query.getResultList();
    }

    /**
     * Get single user from database via username
     * 
     * @param username
     *            The username of the user
     * 
     * @return Users The user object that was requested
     */
    public User getUser(String username) {
	query = manager.createNamedQuery("User.findByUsername");
	query.setParameter("username", username);
	return (User) query.getSingleResult();
    }

    /**
     * Insert a user into the database
     * 
     * @param username
     *            The username of the user
     * @param password
     *            The hashed password of the user
     * @param type
     *            The type of user: a = Admin, u = User
     */
    public boolean insertUser(String username, String password, UserType type) {
	User user = new User(username, password, type);
	manager.getTransaction().begin();
	manager.persist(user);
	manager.getTransaction().commit();
	return true;
    }

    /**
     * Update a user in the database
     * 
     * @param username
     *            The username of the user
     * @param password
     *            The hashed password of the user
     * @param type
     *            The usertype of the user
     * @return true
     */
    public boolean updateUser(String username, String password, UserType type) {
	User user = new User(username, password, type);
	manager.getTransaction().begin();
	manager.merge(user);
	manager.getTransaction().commit();
	return true;
    }

    /**
     * Delete a user from the database
     * 
     * @param username
     *            The username to delete
     * @return true
     */
    public boolean deleteUser(String username) {
	User user = manager.find(User.class, username);
	manager.getTransaction().begin();
	manager.remove(user);
	manager.getTransaction().commit();
	return true;
    }

    /**
     * Close the connection to the database
     */
    public void closeConnection() {
	manager.close();
	emf.close();
    }
}
