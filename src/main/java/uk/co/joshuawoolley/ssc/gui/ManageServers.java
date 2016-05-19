package uk.co.joshuawoolley.ssc.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import uk.co.joshuawoolley.ssc.ServerSoftwareChecker;

public class ManageServers extends JFrame {

    private static final long serialVersionUID = 6864548293535047812L;

    private static ManageServers frame;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	if (ServerSoftwareChecker.user != null) {
	    EventQueue.invokeLater(new Runnable() {
		public void run() {
		    try {
			frame = new ManageServers();
			frame.setVisible(true);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    });
	}
    }

    /**
     * Create the frame for ManageServers.
     */
    public ManageServers() {
	setTitle("Server Software Checker");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);

	JButton btnHome = new JButton("<< Home");
	btnHome.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		Home.main(null);
	    }
	});
	contentPane.setLayout(null);
	btnHome.setBounds(10, 11, 128, 38);
	contentPane.add(btnHome);

	JSeparator separator = new JSeparator();
	separator.setBounds(10, 60, 414, 2);
	contentPane.add(separator);

	JButton btnAddServer = new JButton("Add Server");
	btnAddServer.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		AddServer.main(null);
	    }
	});
	btnAddServer.setBounds(10, 109, 186, 61);
	contentPane.add(btnAddServer);

	JButton btnEditServer = new JButton("Edit Existing Server");
	btnEditServer.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		EditServer.main(null);
	    }
	});
	btnEditServer.setBounds(238, 109, 186, 61);
	contentPane.add(btnEditServer);
    }
}
