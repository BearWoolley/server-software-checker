/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.joshuawoolley.ssc.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Josh
 */
@Entity
@Table(name = "server", catalog = "project", schema = "")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Server.findAll", query = "SELECT s FROM Server s"),
	@NamedQuery(name = "Server.findByHostname", query = "SELECT s FROM Server s WHERE s.hostname = :hostname"),
	@NamedQuery(name = "Server.findByPort", query = "SELECT s FROM Server s WHERE s.port = :port"),
	@NamedQuery(name = "Server.findByUsername", query = "SELECT s FROM Server s WHERE s.username = :username"),
	@NamedQuery(name = "Server.findByPassword", query = "SELECT s FROM Server s WHERE s.password = :password"),
	@NamedQuery(name = "Server.findByFolderLocation", query = "SELECT s FROM Server s WHERE s.folderLocation = :folderLocation") })
public class Server implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "hostname", nullable = false, length = 16)
    private String hostname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "port", nullable = false)
    private int port;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "username", nullable = false, length = 64)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password", nullable = false, length = 64)
    private byte[] password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "folder_location", nullable = false, length = 128)
    private String folderLocation;

    /**
     * Server constructor with no parameters
     */
    public Server() {

    }

    /**
     * Server constructor with hostname parameter
     * 
     * @param hostname
     *            The server hostname
     */
    public Server(String hostname) {
	this.hostname = hostname;
    }

    /**
     * Server constructor with full parameters
     * 
     * @param hostname
     *            The server hostname
     * @param port
     *            The server port
     * @param username
     *            The server username
     * @param password
     *            The server password
     * @param folderLocation
     *            The server folder location
     */
    public Server(String hostname, int port, String username, byte[] password, String folderLocation) {
	this.hostname = hostname;
	this.port = port;
	this.username = username;
	this.password = password;
	this.folderLocation = folderLocation;
    }

    /**
     * Get the server hostname
     * 
     * @return hostname
     */
    public String getHostname() {
	return hostname;
    }

    /**
     * Set the server hostname
     * 
     * @param hostname
     *            The server hostname
     */
    public void setHostname(String hostname) {
	this.hostname = hostname;
    }

    /**
     * Get the server port
     * 
     * @return port
     */
    public int getPort() {
	return port;
    }

    /**
     * Set the server port
     * 
     * @param port
     *            The server port
     */
    public void setPort(int port) {
	this.port = port;
    }

    /**
     * Get the server username
     * 
     * @return username
     */
    public String getUsername() {
	return username;
    }

    /**
     * Set the server username
     * 
     * @param username
     *            The server username
     */
    public void setUsername(String username) {
	this.username = username;
    }

    /**
     * Get the server password
     * 
     * @return password
     */
    public byte[] getPassword() {
	return password;
    }

    /**
     * Set the server password
     * 
     * @param password
     *            The server password
     */
    public void setPassword(byte[] password) {
	this.password = password;
    }

    /**
     * Get the server folder location
     * 
     * @return folder location
     */
    public String getFolderLocation() {
	return folderLocation;
    }

    /**
     * Set the server folder location
     * 
     * @param folderLocation
     *            Location of the folder of the apps
     */
    public void setFolderLocation(String folderLocation) {
	this.folderLocation = folderLocation;
    }

    /**
     * The hashCode for the class
     */
    @Override
    public int hashCode() {
	int hash = 0;
	hash += (hostname != null ? hostname.hashCode() : 0);
	return hash;
    }

    /**
     * Equals method to see if object is type of server
     * 
     * @param object
     *            object to check
     */
    @Override
    public boolean equals(Object object) {
	if (!(object instanceof Server)) {
	    return false;
	}
	Server other = (Server) object;
	if ((this.hostname == null && other.hostname != null)
		|| (this.hostname != null && !this.hostname.equals(other.hostname))) {
	    return false;
	}
	return true;
    }

    /**
     * To string method for server
     */
    @Override
    public String toString() {
	return "Hostname: " + hostname + " Port: " + port + " Username: " + username + " Password: " + password;
    }

}
