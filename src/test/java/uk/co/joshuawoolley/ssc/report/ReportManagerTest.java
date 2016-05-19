package uk.co.joshuawoolley.ssc.report;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.joshuawoolley.ssc.util.PropertiesManager;

/**
 * @author Josh Woolley
 */
public class ReportManagerTest {

    private ReportManager rm;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
	rm = new ReportManager();
    }

    @After
    public void tearDown() throws Exception {
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testGetListOfFiles() {
	PropertiesManager prop = new PropertiesManager();
	prop.loadProperties();
	File folder = new File(PropertiesManager.properties.getProperty("savelocation"));
	assertEquals(rm.getListOfFiles(), folder.listFiles());
    }

    @Test
    public void testGetAllFileNames() {
	PropertiesManager prop = new PropertiesManager();
	prop.loadProperties();
	HashMap<String, String> nameOfFiles = new HashMap<String, String>();
	File[] listOfFiles = rm.getListOfFiles();
	for (File report : listOfFiles) {
	    nameOfFiles.put(report.getName(), report.getAbsolutePath());
	}
	assertEquals(rm.getAllFileNames(), nameOfFiles);
    }

    @Test
    public void testGetReports() {
	// fail("Not yet implemented");
    }

    @Test
    public void testOpenReport() {
	// fail("Not yet implemented");
    }

}
