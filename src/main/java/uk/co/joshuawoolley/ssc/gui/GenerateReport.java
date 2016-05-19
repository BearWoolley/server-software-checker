package uk.co.joshuawoolley.ssc.gui;

import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import uk.co.joshuawoolley.ssc.ServerSoftwareChecker;
import uk.co.joshuawoolley.ssc.report.CreateReport;
import uk.co.joshuawoolley.ssc.report.ReportManager;
import uk.co.joshuawoolley.ssc.ssh.SSHManager;

public class GenerateReport extends JFrame {

    private static final long serialVersionUID = -2465693550545238546L;

    private String saveLocation;

    private JPanel contentPane;
    private static GenerateReport frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	if (ServerSoftwareChecker.user != null) {
	    EventQueue.invokeLater(new Runnable() {
		public void run() {
		    try {
			frame = new GenerateReport();
			frame.setVisible(true);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    });
	}
    }

    /**
     * Create the frame for GenerateReport.
     */
    public GenerateReport() {
	final SSHManager ssh = new SSHManager();
	final CreateReport cr = new CreateReport();
	final ReportManager rm = new ReportManager();

	setTitle("Server Software Checker");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 715, 421);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);

	JButton btnHome = new JButton("<< Home");
	btnHome.setBounds(10, 11, 128, 38);
	btnHome.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		Home.main(null);
	    }
	});
	contentPane.setLayout(null);
	contentPane.add(btnHome);

	JSeparator separator = new JSeparator();
	separator.setBounds(10, 60, 682, 2);
	contentPane.add(separator);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 73, 537, 298);
	contentPane.add(scrollPane);

	final Label label = new Label("Unable to open PDF file");
	label.setBounds(563, 269, 136, 73);
	label.setVisible(false);
	contentPane.add(label);

	final JTextPane textOutput = new JTextPane();
	textOutput.setText("Click Generate Report to view information about servers!");
	scrollPane.setViewportView(textOutput);

	final JButton btnOpenReport = new JButton("Open Report");
	btnOpenReport.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (!rm.openReport(saveLocation)) {
		    label.setVisible(true);
		}
	    }
	});
	btnOpenReport.setEnabled(false);
	btnOpenReport.setBounds(554, 197, 138, 56);
	contentPane.add(btnOpenReport);

	final JButton btnSaveReport = new JButton("Save Report");
	btnSaveReport.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		saveLocation = cr.saveReport(ssh.getResultList());
		btnOpenReport.setEnabled(true);
	    }
	});
	btnSaveReport.setEnabled(false);
	btnSaveReport.setBounds(554, 138, 138, 56);
	contentPane.add(btnSaveReport);

	JButton btnGenReport = new JButton("Generate Report");
	btnGenReport.setBounds(554, 79, 138, 56);
	btnGenReport.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String result = ssh.queryServers();
		textOutput.setText(result);
		btnSaveReport.setEnabled(true);
	    }
	});
	contentPane.add(btnGenReport);

    }
}
