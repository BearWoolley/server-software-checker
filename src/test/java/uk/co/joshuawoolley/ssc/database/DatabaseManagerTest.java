package uk.co.joshuawoolley.ssc.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import uk.co.joshuawoolley.ssc.entities.Server;
import uk.co.joshuawoolley.ssc.entities.User;
import uk.co.joshuawoolley.ssc.user.UserType;

/**
 * @author Josh Woolley
 */
public class DatabaseManagerTest {

    private static List<Server> listOfServers;
    private static List<User> listOfUsers;
    private DatabaseManager dm;
    private EntityManager manager = Mockito.mock(EntityManager.class);
    private Query query = Mockito.mock(Query.class);
    private static Server server;
    private static User user;

    EntityTransaction et = Mockito.mock(EntityTransaction.class);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	listOfServers = new ArrayList<Server>();
	listOfUsers = new ArrayList<User>();

	byte[] password = "root".getBytes();
	server = new Server("127.0.0.1", 21, "root", password, "/home");

	user = new User("admin", "password", UserType.ADMIN);

	listOfServers.add(server);
	listOfServers.add(new Server("10.0.0.1", 21, "root", password, "/home/root"));

	listOfUsers.add(user);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
	dm = new DatabaseManager();
	dm.setManager(manager);
	Mockito.when(manager.getTransaction()).thenReturn(et);
	dm.setManager(manager);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetAllServers() {
	Mockito.when(manager.createNamedQuery("Server.findAll")).thenReturn(query);
	Mockito.when(query.getResultList()).thenReturn(listOfServers);
	assertEquals(dm.getAllServers(), listOfServers);
    }

    @Test
    public void testGetManager() {
	assertSame(manager, dm.getManager());
    }

    @Test
    public void testSetManager() {
	dm.setManager(manager);
	assertSame(manager, dm.getManager());
    }

    @Test
    public void testGetServer() {
	Mockito.when(manager.createNamedQuery("Server.findByHostname")).thenReturn(query);
	Mockito.when(query.getSingleResult()).thenReturn(server);
	assertEquals(dm.getServer("127.0.0.1"), server);
    }

    @Test
    public void testInsertServer() {
	assertTrue(dm.insertServer("127.0.0.1", 21, "root", "root".getBytes(), "/home"));
    }

    @Test
    public void testDeleteServer() {
	assertTrue(dm.deleteServer("127.0.0.1"));
    }

    @Test
    public void testUpdateServer() {
	Mockito.when(manager.createNamedQuery("Server.findByHostname")).thenReturn(query);
	Mockito.when(query.getSingleResult()).thenReturn(server);
	server.setUsername("admin");
	dm.insertServer("127.0.0.1", 21, "root", "root".getBytes(), "/home");
	dm.updateServer("127.0.0.1", 21, "admin", "admin".getBytes(), "/home");
	Server newserver = new Server("127.0.0.1", 21, "admin", "admin".getBytes(), "/home");
	assertEquals(dm.getServer("127.0.0.1").getUsername(), newserver.getUsername());
    }

    @Test
    public void testGetAllUsers() {
	Mockito.when(manager.createNamedQuery("User.findAll")).thenReturn(query);
	Mockito.when(query.getResultList()).thenReturn(listOfUsers);
	assertEquals(dm.getAllUsers(), listOfUsers);
    }

    @Test
    public void getUser() {
	Mockito.when(manager.createNamedQuery("User.findByUsername")).thenReturn(query);
	Mockito.when(query.getSingleResult()).thenReturn(user);
	assertEquals(dm.getUser("admin"), user);
    }

    @Test
    public void testInsertUser() {
	assertTrue(dm.insertUser("testuser", "password", UserType.USER));
    }

    @Test
    public void testUpdateUser() {
	Mockito.when(manager.createNamedQuery("User.findByUsername")).thenReturn(query);
	Mockito.when(query.getSingleResult()).thenReturn(user);
	user.setPassword("testpassword");
	dm.updateUser("admin", "testpassword", UserType.ADMIN);
	User newuser = new User("admin", "testpassword", UserType.ADMIN);
	assertEquals(dm.getUser("admin").getPassword(), newuser.getPassword());
    }

    @Test
    public void testDeleteUser() {
	assertTrue(dm.deleteUser("testuser"));
    }

    @Test
    public void testCloseConnection() {
	dm.closeConnection();
	assertFalse(dm.getManager().isOpen());
    }
}
