package uk.co.joshuawoolley.ssc.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

    private Document document;

    /**
     * PDFGenerator constructor
     */
    public PDFGenerator() {
	document = new Document();
    }

    /**
     * Creates the PDF and saves it to the parameter saveLocation
     * 
     * @param apps
     *            List of Strings with versions
     * @param saveLocation
     *            The location of were the PDF is going to be saved to
     */
    public void createDocument(ArrayList<String> apps, String saveLocation) {
	try {
	    PdfWriter.getInstance(document, new FileOutputStream(saveLocation));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (DocumentException e) {
	    e.printStackTrace();
	}

	String date = new SimpleDateFormat("H:mm dd/MM/yyyy").format(new Date());
	document.open();
	try {
	    Font headerFont = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
	    Paragraph header = new Paragraph("Version Report " + date + "\n\n", headerFont);
	    header.setAlignment(Element.ALIGN_CENTER);
	    document.add(header);
	} catch (DocumentException e1) {
	    e1.printStackTrace();
	}
	for (String app : apps) {
	    try {
		document.add(new Paragraph(app));
	    } catch (DocumentException e) {
		e.printStackTrace();
	    }
	}
	document.close();
    }

}
