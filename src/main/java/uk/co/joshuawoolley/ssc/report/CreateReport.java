package uk.co.joshuawoolley.ssc.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import uk.co.joshuawoolley.ssc.util.PropertiesManager;

/**
 * @author Josh Woolley
 */
public class CreateReport {

    private PDFGenerator pdf;

    /**
     * Constructor for CreateReport
     */
    public CreateReport() {
	pdf = new PDFGenerator();
    }

    /**
     * Saves report to specified location
     * 
     * @param serverVersions
     *            List of Strings containing the versions on that server
     * @return the location of were the PDF was saved to
     */
    public String saveReport(ArrayList<String> serverVersions) {
	String date = new SimpleDateFormat("H-mm-ss-dd-MM-yyyy").format(new Date());
	String saveLocation = PropertiesManager.properties.getProperty("savelocation") + "\\report_" + date + ".pdf";
	pdf.createDocument(serverVersions, saveLocation);
	return saveLocation;
    }

}
