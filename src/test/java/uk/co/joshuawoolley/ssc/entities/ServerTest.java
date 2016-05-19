package uk.co.joshuawoolley.ssc.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Josh Woolley
 */
public class ServerTest {

    private Server server;
    private User user;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
	server = new Server();
	user = new User();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSetHostname() {
	server.setHostname("127.0.0.1");
	assertEquals(server.getHostname(), "127.0.0.1");
    }

    @Test
    public void testSetPort() {
	server.setPort(21);
	assertEquals(server.getPort(), 21);
    }

    @Test
    public void testSetUsername() {
	server.setUsername("root");
	assertEquals(server.getUsername(), "root");
    }

    @Test
    public void testSetPassword() {
	byte[] password = "root".getBytes();
	server.setPassword(password);
	assertEquals(server.getPassword(), password);
    }

    @Test
    public void testSetFolder() {
	server.setFolderLocation("/home");
	assertEquals(server.getFolderLocation(), "/home");
    }

    @Test
    public void testHashCode() {
	assertEquals(server.hashCode(), 0);
    }

    @Test
    public void testEqualsTrue() {
	assertTrue(server.equals(server));
    }

    @Test
    public void testEqualsFalse() {
	assertFalse(server.equals(user));
    }

    @Test
    public void testToString() {
	byte[] password = "root".getBytes();

	server.setHostname("127.0.0.1");
	server.setPort(21);
	server.setUsername("root");
	server.setPassword(password);
	server.setFolderLocation("/home");
	assertEquals("Hostname: 127.0.0.1 Port: 21 Username: root Password: " + password, server.toString());
    }

}
