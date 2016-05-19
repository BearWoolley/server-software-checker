package uk.co.joshuawoolley.ssc.report;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import uk.co.joshuawoolley.ssc.util.PropertiesManager;

/**
 * @author Josh Woolley
 */
public class CreateReportTest {

    private CreateReport cr;
    private ArrayList<String> versions;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
	cr = new CreateReport();
	versions = new ArrayList<String>();
	versions.add(
		"Server 178.62.127.6 contains the following applications:\n\napp2 contains the following versions - 2.0, \n\napp1 contains the following versions - 1.0, 1.1,");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSaveReport() {
	PropertiesManager prop = new PropertiesManager();
	prop.loadProperties();
	PDFGenerator pdf = Mockito.mock(PDFGenerator.class);
	Mockito.doNothing().when(pdf).createDocument(Mockito.any(ArrayList.class), Mockito.anyString());

	String date = new SimpleDateFormat("H-mm-ss-dd-MM-yyyy").format(new Date());
	String saveLocation = PropertiesManager.properties.getProperty("savelocation") + "\\report_" + date + ".pdf";
	assertEquals(cr.saveReport(versions), saveLocation);
    }

}
