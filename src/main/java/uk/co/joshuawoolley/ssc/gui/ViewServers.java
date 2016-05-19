package uk.co.joshuawoolley.ssc.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import uk.co.joshuawoolley.ssc.ServerSoftwareChecker;
import uk.co.joshuawoolley.ssc.server.ServerManager;

public class ViewServers extends JFrame {

    private static final long serialVersionUID = 5480195629765358061L;

    private ServerManager sm;

    private JPanel contentPane;
    private static ViewServers frame;
    private JTable serverTbl;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	if (ServerSoftwareChecker.user != null) {
	    EventQueue.invokeLater(new Runnable() {
		public void run() {
		    try {
			frame = new ViewServers();
			frame.setVisible(true);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    });
	}
    }

    /**
     * Create the frame for ViewServers.
     */
    public ViewServers() {
	sm = new ServerManager();

	setTitle("Server Software Checker");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 714, 353);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JButton btnHome = new JButton("<< Home");
	btnHome.setBounds(10, 11, 128, 37);
	contentPane.add(btnHome);
	btnHome.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		Home.main(null);
	    }
	});

	JSeparator separator = new JSeparator();
	separator.setBounds(10, 59, 678, 2);
	contentPane.add(separator);

	JLabel lblNewLabel = new JLabel("Current Servers:");
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblNewLabel.setBounds(10, 72, 110, 14);
	contentPane.add(lblNewLabel);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 91, 678, 212);
	contentPane.add(scrollPane);

	DefaultTableModel model = new DefaultTableModel();
	serverTbl = new JTable(model);

	model.addColumn("Hostname");
	model.addColumn("Port");
	model.addColumn("Username");
	model.addColumn("Password");
	model.addColumn("Folder Locaton");

	model = sm.getServerRows(model);

	scrollPane.setViewportView(serverTbl);
	contentPane.setVisible(true);
    }
}
