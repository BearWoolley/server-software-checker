package uk.co.joshuawoolley.ssc.report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import uk.co.joshuawoolley.ssc.util.PropertiesManager;

/**
 * @author Josh Woolley
 */
public class ReportManager {

    /**
     * Report Manager constructor
     */
    public ReportManager() {

    }

    /**
     * Gets a list of all the PDF files in the specified location
     * 
     * @return list of all files
     */
    public File[] getListOfFiles() {
	File folder = new File(PropertiesManager.properties.getProperty("savelocation"));
	return folder.listFiles();
    }

    /**
     * Gets a HashMap of all the File names and paths to location
     * 
     * @return HashMap<String, String> nameOfFiles HashMap of all files
     */
    public HashMap<String, String> getAllFileNames() {
	HashMap<String, String> nameOfFiles = new HashMap<String, String>();
	File[] listOfFiles = getListOfFiles();
	for (File report : listOfFiles) {
	    nameOfFiles.put(report.getName(), report.getAbsolutePath());
	}
	return nameOfFiles;
    }

    /**
     * Creates table model to view all reports the system has
     * 
     * @param model
     *            The model from the GUI
     * @return DefaultTableModel with all the files added to it.
     */
    public DefaultTableModel getReports(DefaultTableModel model) {
	File[] listOfFiles = getListOfFiles();
	for (File report : listOfFiles) {
	    model.addRow(createReportObject(report));
	}
	return model;
    }

    /**
     * Opens a PDF on the users computer
     * 
     * Code used from
     * http://stackoverflow.com/questions/2546968/open-pdf-file-on-the-fly-from-
     * a-java-application
     * 
     * @param reportLocation
     *            Location of were the report is located
     * @return true if the PDF was opened. False if the file was unable to be
     *         opened or no default PDF program is set
     */
    public boolean openReport(String reportLocation) {
	if (Desktop.isDesktopSupported()) {
	    try {
		File myFile = new File(reportLocation);
		Desktop.getDesktop().open(myFile);
		return true;
	    } catch (IOException ex) {
		return false;
	    }
	}
	return false;
    }

    /**
     * Creates a object containing the report to be used in the table
     * 
     * @param report
     *            The file of the report
     * @return The file as a Object[]
     */
    private Object[] createReportObject(File report) {
	Object[] reportObject = { report.getName() };
	return reportObject;
    }

}
