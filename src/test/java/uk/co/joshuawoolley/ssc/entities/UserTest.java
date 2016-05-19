package uk.co.joshuawoolley.ssc.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.joshuawoolley.ssc.user.UserType;

/**
 * @author Josh Woolley
 */
public class UserTest {

    private User user;
    private User user2;
    private User user3;
    private Server server;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
	user = new User();
	server = new Server();
	user2 = new User("admin");
	user3 = new User("admintwo", "password", UserType.ADMIN);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSetUsername() {
	user.setUsername("testuser");
	assertEquals(user.getUsername(), "testuser");
    }

    @Test
    public void testSetPassword() {
	user2.setPassword("password");
	assertEquals(user2.getPassword(), "password");
    }

    @Test
    public void testSetTypeUser() {
	user3.setType(UserType.USER);
	assertEquals(user3.getType(), UserType.USER);
    }

    @Test
    public void testSetTypeAdmin() {
	user.setType(UserType.ADMIN);
	assertEquals(user.getType(), UserType.ADMIN);
    }

    @Test
    public void testHashCode() {
	assertEquals(user.hashCode(), 0);
    }

    @Test
    public void testEqualsTrue() {
	assertTrue(user.equals(user));
    }

    @Test
    public void testEqualsFalse() {
	assertFalse(user.equals(server));
    }

    @Test
    public void testToString() {
	user.setUsername("admin");
	user.setPassword("password");
	user.setType(UserType.ADMIN);

	assertEquals(user.toString(), "Username: admin Password: password User type: ADMIN");
    }

}
