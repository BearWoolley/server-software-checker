package uk.co.joshuawoolley.ssc.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import uk.co.joshuawoolley.ssc.database.DatabaseManager;
import uk.co.joshuawoolley.ssc.entities.Server;
import uk.co.joshuawoolley.ssc.util.PropertiesManager;

/**
 * @author Josh Woolley
 */
public class ServerManagerTest {

    private ServerManager sm;

    private List<Server> listOfServers;
    private Server server;
    private DatabaseManager dm;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
	dm = Mockito.mock(DatabaseManager.class);
	sm = new ServerManager();
	listOfServers = new ArrayList<Server>();

	server = new Server("127.0.0.1", 21, "root", "passworduytrfghj".getBytes(), "/home");
	listOfServers.add(server);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetServerRows() {
	sm.setDatabaseManager(dm);
	Mockito.when(dm.getAllServers()).thenReturn(listOfServers);

	PropertiesManager prop = new PropertiesManager();
	prop.loadProperties();

	DefaultTableModel model = new DefaultTableModel();
	DefaultTableModel newmodel = new DefaultTableModel();
	Object[] serverObject = { server.getHostname(), server.getPort(), server.getUsername(), server.getPassword(),
		server.getFolderLocation() };
	newmodel.addRow(serverObject);
	// assertEquals(sm.getServerRows(model), newmodel);
    }

    @Test
    public void testGetAllServersHostname() {
	sm.setDatabaseManager(dm);
	Mockito.doReturn(listOfServers).when(dm).getAllServers();
	List<String> hostnames = new ArrayList<String>();
	hostnames.add("127.0.0.1");
	assertEquals(sm.getAllServersHostname(), hostnames);

    }

    @Test
    public void testGetAllServers() {
	sm.setDatabaseManager(dm);
	Mockito.doReturn(listOfServers).when(dm).getAllServers();
	assertSame(dm.getAllServers(), listOfServers);
    }

    @Test
    public void testCreateServer() {
	PropertiesManager prop = new PropertiesManager();
	prop.loadProperties();
	sm.setDatabaseManager(dm);
	Mockito.doReturn(true).when(dm).insertServer(Mockito.anyString(), Mockito.anyInt(), Mockito.anyString(),
		Mockito.any(byte[].class), Mockito.anyString());
	assertTrue(sm.createServer("127.0.0.1", 22, "root", "password", "/home"));
    }

    @Test
    public void testDeleteServer() {
	sm.setDatabaseManager(dm);
	Mockito.doReturn(true).when(dm).deleteServer(Mockito.anyString());
	assertTrue(sm.deleteServer("127.0.0.1"));
    }

    @Test
    public void testUpdateServer() {
	PropertiesManager prop = new PropertiesManager();
	prop.loadProperties();
	sm.setDatabaseManager(dm);
	Mockito.doReturn(true).when(dm).updateServer(Mockito.anyString(), Mockito.anyInt(), Mockito.anyString(),
		Mockito.any(byte[].class), Mockito.anyString());
	assertTrue(sm.updateServer("127.0.0.1", 22, "root", "newpassword", "/home"));
    }

    @Test
    public void testGetServer() {
	sm.setDatabaseManager(dm);
	Mockito.doReturn(server).when(dm).getServer(Mockito.anyString());
	assertSame(sm.getServer("127.0.0.1"), server);
    }

    @Test
    public void testGetUsername() {
	sm.setDatabaseManager(dm);
	Mockito.doReturn(server).when(dm).getServer(Mockito.anyString());
	sm.setServer("127.0.0.1");
	assertEquals(sm.getUsername("127.0.0.1"), "root");
    }

    @Test
    public void testGetPassword() {
	sm.setDatabaseManager(dm);
	Mockito.doReturn(server).when(dm).getServer(Mockito.anyString());
	sm.setServer("127.0.0.1");
	// assertEquals(sm.getPassword("127.0.0.1"), "password");
    }

    @Test
    public void testGetPort() {
	sm.setDatabaseManager(dm);
	Mockito.doReturn(server).when(dm).getServer(Mockito.anyString());
	sm.setServer("127.0.0.1");
	assertEquals(sm.getPort("127.0.0.1"), "21");
    }

    @Test
    public void testGetFolder() {
	sm.setDatabaseManager(dm);
	Mockito.doReturn(server).when(dm).getServer(Mockito.anyString());
	sm.setServer("127.0.0.1");
	assertEquals(sm.getFolder("127.0.0.1"), "/home");
    }

    @Test
    public void testEncryptPassword() {
	// fail("Not yet implemented");
    }

    @Test
    public void testDecryptPassword() {
	// fail("Not yet implemented");
    }

}
