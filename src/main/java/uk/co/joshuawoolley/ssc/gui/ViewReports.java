package uk.co.joshuawoolley.ssc.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import uk.co.joshuawoolley.ssc.ServerSoftwareChecker;
import uk.co.joshuawoolley.ssc.report.ReportManager;

public class ViewReports extends JFrame {

    private static final long serialVersionUID = -7507130273840509067L;

    private JPanel contentPane;
    private static ViewReports frame;
    private JTable table;
    private HashMap<String, String> reports;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	if (ServerSoftwareChecker.user != null) {
	    EventQueue.invokeLater(new Runnable() {
		public void run() {
		    try {
			frame = new ViewReports();
			frame.setVisible(true);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    });
	}
    }

    /**
     * Create the frame for ViewReport.
     */
    public ViewReports() {
	final ReportManager rm = new ReportManager();
	setTitle("Server Software Checker");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JButton btnHome = new JButton("<< Home");
	btnHome.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		Home.main(null);
	    }
	});
	btnHome.setBounds(10, 11, 128, 38);
	contentPane.add(btnHome);

	JSeparator separator = new JSeparator();
	separator.setBounds(10, 60, 414, 2);
	contentPane.add(separator);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 73, 209, 177);
	contentPane.add(scrollPane);

	DefaultTableModel model = new DefaultTableModel();
	table = new JTable(model);

	model.addColumn("Filename");

	model = rm.getReports(model);
	scrollPane.setViewportView(table);

	final JComboBox<String> reportComboBox = new JComboBox<String>();
	reportComboBox.setBounds(229, 116, 195, 38);
	reportComboBox.addItem(null);
	reports = rm.getAllFileNames();
	Iterator it = reports.entrySet().iterator();
	while (it.hasNext()) {
	    Map.Entry report = (Map.Entry) it.next();
	    reportComboBox.addItem((String) report.getKey());
	}
	contentPane.add(reportComboBox);

	JButton btnNewButton = new JButton("Open Report");
	btnNewButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String selected = (String) reportComboBox.getSelectedItem();
		if (selected != null) {
		    rm.openReport(reports.get(selected));
		}
	    }
	});
	btnNewButton.setBounds(254, 169, 142, 44);
	contentPane.add(btnNewButton);
    }
}
