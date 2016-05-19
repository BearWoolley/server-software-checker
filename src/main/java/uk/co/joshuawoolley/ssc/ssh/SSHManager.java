package uk.co.joshuawoolley.ssc.ssh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import uk.co.joshuawoolley.ssc.entities.Server;
import uk.co.joshuawoolley.ssc.server.ServerManager;

/**
 * @author Josh Woolley
 */
public class SSHManager {

    private SSHConnector ssh;
    private ServerManager sm;

    private ArrayList<String> resultList;

    /**
     * Constructor for SSHManager
     */
    public SSHManager() {
	resultList = new ArrayList<String>();
	ssh = new SSHConnector();
	sm = new ServerManager();
    }

    /**
     * Query the versions for all servers
     * 
     * @return a string containing all the servers and versions
     */
    public String queryServers() {
	List<Server> servers = sm.getAllServers();
	String hostname = "";
	for (Server server : servers) {
	    HashMap<String, String> apps = null;
	    apps = ssh.getVersions(server.getHostname(), server.getUsername(), server.getPort(),
		    sm.decryptPassword(server.getPassword()), server.getFolderLocation());
	    hostname = hostname + "Server " + server.getHostname() + " contains the following applications:" + "\n\n";
	    Iterator it = apps.entrySet().iterator();
	    while (it.hasNext()) {
		Map.Entry version = (Map.Entry) it.next();
		if (!version.getKey().equals("")) {
		    hostname += version.getKey() + " contains the following versions - ";
		}
		String result = (String) version.getValue();
		hostname += result.replace("\n", ", ") + "\n\n";
	    }
	}
	resultList.add(hostname);
	if (servers.isEmpty()) {
	    hostname = "No servers have been entered in to the application!";
	}
	return hostname;
    }

    /**
     * Get list of all the results
     * 
     * @return the resultList
     */
    public ArrayList<String> getResultList() {
	return resultList;
    }

    /**
     * Set the list of all the resultList
     * 
     * @param resultList
     *            the resultList to set
     */
    public void setResultList(ArrayList<String> resultList) {
	this.resultList = resultList;
    }

}
