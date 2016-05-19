package uk.co.joshuawoolley.ssc.ssh;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class SSHConnector {

    private static String password;

    private String connectionRefused = "Connection refused to server. Server could be offline. \r\n";

    /**
     * Constructor for SSHConnector
     */
    public SSHConnector() {

    }

    /**
     * Get all versions for a server via SSH connection
     * 
     * Help used from JSch examples
     * http://www.jcraft.com/jsch/examples/Exec.java.html
     * 
     * @param hostname
     *            The hostname of the server
     * @param username
     *            The username of the server
     * @param port
     *            The port of the server
     * @param password
     *            The port of the server
     * @param location
     *            The folder location of the server
     * @return hashmap of all the versions
     */
    public HashMap<String, String> getVersions(String hostname, String username, int port, String password,
	    String location) {

	HashMap<String, String> result = new HashMap<String, String>();

	String command = "cd " + location + "; ls";

	String folders = "";
	for (int i = 0; i < 2; i++) {
	    if (folders.equals("") || folders.equals(connectionRefused)) {
		folders = getFolders(command, username, hostname, port, password);
	    }
	}

	if (!folders.equals(connectionRefused)) {
	    String[] foldersList = folders.split("\n");

	    for (String folder : foldersList) {
		command = "cd " + location + "/" + folder + "; ls";
		result.put(folder, getFolders(command, username, hostname, port, password));
	    }
	} else {
	    result.put("", folders);
	}

	return result;
    }

    private String getFolders(String command, String username, String hostname, int port, String password) {
	String result = "";

	JSch jsch = new JSch();

	Session session = null;
	try {
	    session = jsch.getSession(username, hostname, port);
	} catch (JSchException e) {
	    System.out.println(e.getMessage());
	}

	Properties config = new Properties();
	config.put("StrictHostKeyChecking", "no");
	session.setConfig(config);

	UserInfo ui = new MyUserInfo();
	session.setUserInfo(ui);
	try {
	    session.connect(); // Fails first time here
	} catch (JSchException e1) {

	}

	SSHConnector.password = password;

	Channel channel = null;
	try {
	    channel = session.openChannel("exec");
	} catch (JSchException e) {
	    return connectionRefused;
	}
	((ChannelExec) channel).setCommand(command);

	channel.setInputStream(null);

	((ChannelExec) channel).setErrStream(System.err);

	InputStream in = null;
	try {
	    in = channel.getInputStream();
	} catch (IOException e1) {
	    e1.printStackTrace();
	}

	try {
	    channel.connect();
	} catch (JSchException e) {
	    e.printStackTrace();
	}

	byte[] tmp = new byte[1024];
	while (true) {
	    try {
		while (in.available() > 0) {
		    int i = in.read(tmp, 0, 1024);
		    if (i < 0) {
			break;
		    }
		    result = new String(tmp, 0, i);
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    if (channel.isClosed()) {
		try {
		    if (in.available() > 0) {
			continue;
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
		break;
	    }
	}
	channel.disconnect();
	session.disconnect();

	return result;
    }

    /**
     * MyUserInfo class
     */
    public static class MyUserInfo implements UserInfo {

	/**
	 * Get passphrase
	 * 
	 * @return null
	 */
	public String getPassphrase() {
	    return null;
	}

	/**
	 * Get password
	 * 
	 * @return password
	 */
	public String getPassword() {
	    return password;
	}

	/**
	 * Prompt Password
	 * 
	 * @return true
	 */
	public boolean promptPassword(String message) {
	    return true;
	}

	/**
	 * Prompt Passphrase
	 * 
	 * @return false
	 */
	public boolean promptPassphrase(String message) {
	    return false;
	}

	/**
	 * Prompt Yes No
	 * 
	 * @return false
	 */
	public boolean promptYesNo(String message) {
	    return false;
	}

	/**
	 * Show Message
	 */
	public void showMessage(String message) {

	}

    }

}
