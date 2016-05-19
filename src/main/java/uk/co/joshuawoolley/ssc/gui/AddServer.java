package uk.co.joshuawoolley.ssc.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import uk.co.joshuawoolley.ssc.ServerSoftwareChecker;
import uk.co.joshuawoolley.ssc.server.ServerManager;

public class AddServer extends JFrame {

    private static final long serialVersionUID = 3255093618243206174L;

    private JPanel contentPane;
    private static AddServer frame;
    private JTextField hostnameField;
    private JTextField usernameField;
    private JTextField portField;
    private JPasswordField passwordField;
    private JTextField folderField;

    private ServerManager sm;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	if (ServerSoftwareChecker.user != null) {
	    EventQueue.invokeLater(new Runnable() {
		public void run() {
		    try {
			frame = new AddServer();
			frame.setVisible(true);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    });
	}
    }

    /**
     * Create the frame for Add Server.
     */
    public AddServer() {
	sm = new ServerManager();

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

	final Label successLbl = new Label("Server was successfully added to the application");
	successLbl.setBounds(103, 68, 271, 29);
	contentPane.add(successLbl);
	successLbl.setVisible(false);

	JLabel lblNewLabel = new JLabel("Add Server:");
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblNewLabel.setBounds(10, 73, 79, 14);
	contentPane.add(lblNewLabel);

	JLabel lblHostname = new JLabel("Hostname:");
	lblHostname.setBounds(10, 108, 65, 14);
	contentPane.add(lblHostname);

	hostnameField = new JTextField();
	hostnameField.setBounds(74, 105, 86, 20);
	contentPane.add(hostnameField);
	hostnameField.setColumns(10);

	JLabel lblUsername = new JLabel("Username:");
	lblUsername.setBounds(205, 108, 65, 14);
	contentPane.add(lblUsername);

	usernameField = new JTextField();
	usernameField.setBounds(269, 105, 86, 20);
	contentPane.add(usernameField);
	usernameField.setColumns(10);

	JLabel lblPort = new JLabel("Port:");
	lblPort.setBounds(36, 146, 39, 14);
	contentPane.add(lblPort);

	portField = new JTextField();
	portField.setBounds(74, 143, 86, 20);
	contentPane.add(portField);
	portField.setColumns(10);

	JLabel lblPassword = new JLabel("Password:");
	lblPassword.setBounds(205, 146, 65, 14);
	contentPane.add(lblPassword);

	passwordField = new JPasswordField();
	passwordField.setBounds(269, 144, 86, 17);
	contentPane.add(passwordField);

	JLabel lblFolderLocation = new JLabel("Folder Location:");
	lblFolderLocation.setBounds(51, 186, 109, 14);
	contentPane.add(lblFolderLocation);

	folderField = new JTextField();
	folderField.setBounds(149, 183, 192, 20);
	contentPane.add(folderField);
	folderField.setColumns(10);

	JButton btnAddServer = new JButton("Add Server");
	btnAddServer.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String hostname = hostnameField.getText();
		String username = usernameField.getText();
		int port = Integer.parseInt(portField.getText());
		String password = String.valueOf(passwordField.getPassword());
		String folder = folderField.getText();

		boolean result = sm.createServer(hostname, port, username, password, folder);
		if (result) {
		    successLbl.setVisible(true);
		}
		hostnameField.setText("");
		usernameField.setText("");
		portField.setText("");
		passwordField.setText("");
		folderField.setText("");
	    }
	});
	btnAddServer.setBounds(153, 221, 109, 29);
	contentPane.add(btnAddServer);
    }
}
