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
import uk.co.joshuawoolley.ssc.user.UserType;

public class Home extends JFrame {

    private static final long serialVersionUID = 8222041357039750617L;

    private JPanel contentPane;
    private static Home frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	if (ServerSoftwareChecker.user != null) {
	    EventQueue.invokeLater(new Runnable() {
		public void run() {
		    try {
			frame = new Home();
			frame.setVisible(true);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    });
	}
    }

    /**
     * Create the frame for Home.
     */
    public Home() {
	setTitle("Server Software Checker");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 995, 419);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JButton btnViewReports = new JButton("View Reports");
	btnViewReports.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		ViewReports.main(null);
	    }
	});
	btnViewReports.setBounds(286, 11, 128, 37);
	contentPane.add(btnViewReports);

	JButton btnViewServers = new JButton("View Servers");
	btnViewServers.setBounds(424, 11, 128, 37);
	contentPane.add(btnViewServers);
	btnViewServers.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		ViewServers.main(null);
	    }
	});

	if (ServerSoftwareChecker.user.getType().equals(UserType.ADMIN)) {
	    JButton btnManageServers = new JButton("Manage Servers");
	    btnManageServers.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    frame.setVisible(false);
		    ManageServers.main(null);
		}
	    });
	    btnManageServers.setBounds(562, 11, 128, 37);
	    contentPane.add(btnManageServers);
	}

	if (ServerSoftwareChecker.user.getType().equals(UserType.ADMIN)) {
	    JButton btnManageUsers = new JButton("Manage Users");
	    btnManageUsers.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    frame.setVisible(false);
		    ManageUsers.main(null);
		}
	    });
	    btnManageUsers.setBounds(700, 11, 128, 37);
	    contentPane.add(btnManageUsers);
	}

	JButton btnLogout = new JButton("Logout");
	btnLogout.setBounds(838, 11, 128, 37);
	contentPane.add(btnLogout);
	btnLogout.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		System.exit(1);
	    }
	});

	JButton btnHome = new JButton("Home");
	btnHome.setBounds(10, 11, 128, 37);
	contentPane.add(btnHome);

	JSeparator separator = new JSeparator();
	separator.setBounds(10, 59, 956, 2);
	contentPane.add(separator);

	JButton btnGenReport = new JButton("Generate Report");
	btnGenReport.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		GenerateReport.main(null);
	    }
	});
	btnGenReport.setBounds(148, 10, 128, 38);
	contentPane.add(btnGenReport);
    }
}
