package uk.co.joshuawoolley.ssc.util;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Josh Woolley
 */
public class PropertiesManagerTest {

    private PropertiesManager manager;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
	manager = new PropertiesManager();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLoadProperties() {
	manager.loadProperties();
	assertEquals(manager.properties.getProperty("savelocation"),
		System.getProperty("user.home") + "\\Documents\\Server Software Checker\\Reports");
    }

}
