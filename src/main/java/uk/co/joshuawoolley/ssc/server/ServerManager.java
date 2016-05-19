package uk.co.joshuawoolley.ssc.server;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.table.DefaultTableModel;

import uk.co.joshuawoolley.ssc.database.DatabaseManager;
import uk.co.joshuawoolley.ssc.entities.Server;
import uk.co.joshuawoolley.ssc.util.PropertiesManager;

/**
 * @author Josh Woolley
 */
public class ServerManager {

    private List<Server> allServers;
    private DatabaseManager dm;
    private Server server;

    /**
     * Constructor for ServerManager
     */
    public ServerManager() {
	dm = new DatabaseManager();
    }

    /**
     * Get the Database Manager
     * 
     * @return Database Manager
     */
    public DatabaseManager getDatabaseManager() {
	return dm;
    }

    /**
     * Set the Database Manager
     * 
     * @param dm
     *            Database Manager to be set
     */
    public void setDatabaseManager(DatabaseManager dm) {
	this.dm = dm;
    }

    /**
     * Gets all the servers from the database and creates a table of them
     * 
     * @param model
     *            The model from the GUI
     * @return the model containing all the servers
     */
    public DefaultTableModel getServerRows(DefaultTableModel model) {
	allServers = dm.getAllServers();
	for (Server server : allServers) {
	    model.addRow(createServerObject(server));
	}
	return model;
    }

    /**
     * Get list of all server hostnames
     * 
     * @return servers List of all the server hostnames
     */
    public List<String> getAllServersHostname() {
	List<String> servers = new ArrayList<String>();
	allServers = dm.getAllServers();
	for (Server server : allServers) {
	    servers.add(server.getHostname());
	}
	return servers;
    }

    /**
     * Get list of all servers
     * 
     * @return servers List of all the servers
     */
    public List<Server> getAllServers() {
	return dm.getAllServers();
    }

    /**
     * Create a server and save it to the database
     * 
     * @param hostname
     *            The server's hostname
     * @param port
     *            The server's port
     * @param username
     *            The server's username
     * @param password
     *            The server's password
     * @param folder
     *            The server's folder location
     * @return true on success
     */
    public boolean createServer(String hostname, int port, String username, String password, String folder) {
	return dm.insertServer(hostname, port, username, encryptPassword(password), folder);
    }

    /**
     * Delete server from database
     * 
     * @param hostname
     *            The hostname to be deleted
     * @return true on success
     */
    public boolean deleteServer(String hostname) {
	return dm.deleteServer(hostname);
    }

    /**
     * Update server in the database
     * 
     * @param hostname
     *            The hostname of the server
     * @param port
     *            The port of the server
     * @param username
     *            The username of the server
     * @param password
     *            The password of the server
     * @param folder
     *            The folder location of the server
     * @return true on success
     */
    public boolean updateServer(String hostname, int port, String username, String password, String folder) {
	return dm.updateServer(hostname, port, username, encryptPassword(password), folder);
    }

    /**
     * Get server from hostname
     * 
     * @param hostname
     *            The hostname of the server
     * @return server The requested Server
     */
    public Server getServer(String hostname) {
	return dm.getServer(hostname);
    }

    /**
     * Set the server
     * 
     * @param hostname
     *            the hostname of the server
     */
    public void setServer(String hostname) {
	this.server = getServer(hostname);
    }

    /**
     * Get username of the server
     * 
     * @param hostname
     *            The hostname of the server
     * @return the username
     */
    public String getUsername(String hostname) {
	if (server == null) {
	    server = getServer(hostname);
	}
	return server.getUsername();
    }

    /**
     * Get the decrypted password of the server
     * 
     * @param hostname
     *            The hostname of the server
     * @return the decrypted password
     */
    public String getPassword(String hostname) {
	if (server == null) {
	    server = getServer(hostname);
	}
	return decryptPassword(server.getPassword());
    }

    /**
     * Get the port of the server
     * 
     * @param hostname
     *            The hostname of the server
     * @return the port
     */
    public String getPort(String hostname) {
	if (server == null) {
	    server = getServer(hostname);
	}
	return String.valueOf(server.getPort());
    }

    /**
     * Get the folder location of the server
     * 
     * @param hostname
     *            The hostname of the server
     * @return the folder location
     */
    public String getFolder(String hostname) {
	if (server == null) {
	    server = getServer(hostname);
	}
	return server.getFolderLocation();
    }

    /**
     * Encrypt the password
     * 
     * @param password
     *            The password to be encrypted
     * @return the encrypted password
     */
    public byte[] encryptPassword(String password) {
	Key aesKey = new SecretKeySpec(PropertiesManager.properties.getProperty("encryptionkey").getBytes(), "AES");
	Cipher cipher;
	byte[] encrypted = null;
	try {
	    cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.ENCRYPT_MODE, aesKey);
	    encrypted = cipher.doFinal(password.getBytes());
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (NoSuchPaddingException e) {
	    e.printStackTrace();
	} catch (InvalidKeyException e) {
	    e.printStackTrace();
	} catch (IllegalBlockSizeException e) {
	    e.printStackTrace();
	} catch (BadPaddingException e) {
	    e.printStackTrace();
	}
	return encrypted;
    }

    /**
     * Decrypt the password
     * 
     * @param encryptedPassword
     *            The encrypted password
     * @return the decrypted password
     */
    public String decryptPassword(byte[] encryptedPassword) {
	Key aesKey = new SecretKeySpec(PropertiesManager.properties.getProperty("encryptionkey").getBytes(), "AES");
	Cipher cipher;
	String decrypted = null;
	try {
	    cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.DECRYPT_MODE, aesKey);
	    decrypted = new String(cipher.doFinal(encryptedPassword));
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (NoSuchPaddingException e) {
	    e.printStackTrace();
	} catch (InvalidKeyException e) {
	    e.printStackTrace();
	} catch (IllegalBlockSizeException e) {
	    e.printStackTrace();
	} catch (BadPaddingException e) {
	    e.printStackTrace();
	}
	return decrypted;
    }

    /**
     * Creates an object containing all the server details
     * 
     * @param server
     *            The server to be added to the Object[]
     * @return the server Object[]
     */
    private Object[] createServerObject(Server server) {
	Object[] serverObject = { server.getHostname(), server.getPort(), server.getUsername(),
		decryptPassword(server.getPassword()), server.getFolderLocation() };
	return serverObject;
    }

}
