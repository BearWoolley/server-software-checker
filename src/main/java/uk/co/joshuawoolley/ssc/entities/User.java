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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import uk.co.joshuawoolley.ssc.user.UserType;

/**
 *
 * @author Josh
 */
@Entity
@Table(name = "user", catalog = "project", schema = "")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
	@NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
	@NamedQuery(name = "User.findByType", query = "SELECT u FROM User u WHERE u.type = :type") })
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "username", nullable = false, length = 64)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password", nullable = false, length = 64)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType type;

    /**
     * User constructor with no parameters
     */
    public User() {

    }

    /**
     * User constructor with username parameter
     * 
     * @param username
     *            The user's username
     */
    public User(String username) {
	this.username = username;
    }

    /**
     * User constructor with full parameters
     * 
     * @param username
     *            The user's username
     * @param password
     *            The user's password
     * @param type
     *            The user's UserType
     */
    public User(String username, String password, UserType type) {
	this.username = username;
	this.password = password;
	this.type = type;
    }

    /**
     * Get the user's username
     * 
     * @return username
     */
    public String getUsername() {
	return username;
    }

    /**
     * Set the user's username
     * 
     * @param username
     *            The user's username
     */
    public void setUsername(String username) {
	this.username = username;
    }

    /**
     * Get the user's password
     * 
     * @return password
     */
    public String getPassword() {
	return password;
    }

    /**
     * Set the user's password
     * 
     * @param password
     *            The user's password
     */
    public void setPassword(String password) {
	this.password = password;
    }

    /**
     * Get user's UserType
     * 
     * @return type
     */
    public UserType getType() {
	return type;
    }

    /**
     * Set the user's UserType
     * 
     * @param type
     *            The user's UserType
     */
    public void setType(UserType type) {
	this.type = type;
    }

    /**
     * The hashCode for the class
     */
    @Override
    public int hashCode() {
	int hash = 0;
	hash += (username != null ? username.hashCode() : 0);
	return hash;
    }

    /**
     * Equals method to see if object is type of user
     * 
     * @param object
     *            object to check
     */
    @Override
    public boolean equals(Object object) {
	if (!(object instanceof User)) {
	    return false;
	}
	User other = (User) object;
	if ((this.username == null && other.username != null)
		|| (this.username != null && !this.username.equals(other.username))) {
	    return false;
	}
	return true;
    }

    /**
     * To string method for user
     */
    @Override
    public String toString() {
	return "Username: " + username + " Password: " + password + " User type: " + type.toString();
    }

}
