package uk.co.joshuawoolley.ssc.report;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

/**
 * @author Josh Woolley
 */
public class PDFGeneratorTest {

    private PDFGenerator pdf;
    private ArrayList<String> versions;
    private String location;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
	pdf = new PDFGenerator();
	versions = new ArrayList<String>();
	versions.add(
		"Server 178.62.127.6 contains the following applications:\n\napp2 contains the following versions - 2.0, \n\napp1 contains the following versions - 1.0, 1.1,");
	location = System.getProperty("user.home") + "\\Documents\\Server Software Checker\\testreport.pdf";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateDocument() {
	pdf.createDocument(versions, location);
	String text = readReport();
	assertTrue(text.contains("app2 contains the following versions - 2.0"));
    }

    private String readReport() {
	String text = "";
	try {
	    text = PdfTextExtractor.getTextFromPage(new PdfReader(location), 1);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return text;
    }

}
