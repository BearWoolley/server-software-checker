package uk.co.joshuawoolley.ssc.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import uk.co.joshuawoolley.ssc.ServerSoftwareChecker;
import uk.co.joshuawoolley.ssc.server.ServerManager;

public class EditServer extends JFrame {

    private static final long serialVersionUID = 2313725131906872703L;

    private static EditServer frame;
    private JPanel contentPane;

    private JLabel lblHostname;
    private JTextField hostnameField;
    private JLabel lblUsername;
    private JTextField usernameField;
    private JLabel lblPort;
    private JTextField portField;
    private JLabel lblPassword;
    private JPasswordField passwordField;
    private JLabel lblFolderLocation;
    private JTextField folderField;
    private JButton btnUpdateServer;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	if (ServerSoftwareChecker.user != null) {
	    EventQueue.invokeLater(new Runnable() {
		public void run() {
		    try {
			frame = new EditServer();
			frame.setVisible(true);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    });
	}
    }

    /**
     * Create the frame for EditServer.
     */
    public EditServer() {
	setTitle("Server Software Checker");
	final ServerManager sm = new ServerManager();

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
	contentPane.setLayout(null);
	btnHome.setBounds(10, 11, 128, 38);
	contentPane.add(btnHome);

	JSeparator separator = new JSeparator();
	separator.setBounds(10, 60, 414, 2);
	contentPane.add(separator);

	JLabel lblSelectServer = new JLabel("Select Server:");
	lblSelectServer.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblSelectServer.setBounds(10, 76, 82, 14);
	contentPane.add(lblSelectServer);

	final JComboBox<String> serversComboBox = new JComboBox<String>();
	serversComboBox.setBounds(102, 73, 128, 20);
	serversComboBox.addItem(null);
	List<String> servers = sm.getAllServersHostname();
	for (String server : servers) {
	    serversComboBox.addItem(server);
	}
	contentPane.add(serversComboBox);

	final Label lblEdit = new Label("Server successfully edited");
	lblEdit.setBounds(137, 95, 241, 22);
	lblEdit.setVisible(false);
	contentPane.add(lblEdit);

	final Label lblDelete = new Label("Server successfully deleted");
	lblDelete.setBounds(137, 95, 219, 22);
	lblDelete.setVisible(false);
	contentPane.add(lblDelete);

	JButton btnEdit = new JButton("Edit");
	btnEdit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String hostname = (String) serversComboBox.getSelectedItem();
		if (hostname != null) {
		    sm.setServer(hostname);
		    hostnameField.setText(hostname);
		    hostnameField.setEditable(false);
		    usernameField.setText(sm.getUsername(hostname));
		    passwordField.setText(sm.getPassword(hostname));
		    portField.setText(sm.getPort(hostname));
		    folderField.setText(sm.getFolder(hostname));
		    setEditVisable(true);
		}
	    }
	});
	btnEdit.setBounds(240, 72, 89, 23);
	contentPane.add(btnEdit);

	JButton btnDelete = new JButton("Delete");
	btnDelete.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (serversComboBox.getSelectedItem() != null) {
		    sm.deleteServer((String) serversComboBox.getSelectedItem());
		    serversComboBox.setSelectedIndex(0);
		    lblDelete.setVisible(true);
		}
	    }
	});
	btnDelete.setBounds(335, 72, 89, 23);
	contentPane.add(btnDelete);

	lblHostname = new JLabel("Hostname:");
	lblHostname.setBounds(26, 126, 65, 14);
	contentPane.add(lblHostname);

	hostnameField = new JTextField();
	hostnameField.setBounds(90, 123, 86, 20);
	contentPane.add(hostnameField);
	hostnameField.setColumns(10);

	lblUsername = new JLabel("Username:");
	lblUsername.setBounds(221, 126, 65, 14);
	contentPane.add(lblUsername);

	usernameField = new JTextField();
	usernameField.setBounds(285, 123, 86, 20);
	contentPane.add(usernameField);
	usernameField.setColumns(10);

	lblPort = new JLabel("Port:");
	lblPort.setBounds(52, 164, 39, 14);
	contentPane.add(lblPort);

	portField = new JTextField();
	portField.setBounds(90, 161, 86, 20);
	contentPane.add(portField);
	portField.setColumns(10);

	lblPassword = new JLabel("Password:");
	lblPassword.setBounds(221, 164, 65, 14);
	contentPane.add(lblPassword);

	passwordField = new JPasswordField();
	passwordField.setBounds(285, 162, 86, 17);
	contentPane.add(passwordField);

	lblFolderLocation = new JLabel("Folder Location:");
	lblFolderLocation.setBounds(67, 204, 109, 14);
	contentPane.add(lblFolderLocation);

	folderField = new JTextField();
	folderField.setBounds(165, 201, 192, 20);
	contentPane.add(folderField);
	folderField.setColumns(10);

	btnUpdateServer = new JButton("Update Server");
	btnUpdateServer.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		sm.updateServer(hostnameField.getText(), Integer.parseInt(portField.getText()), usernameField.getText(),
			String.valueOf(passwordField.getPassword()), folderField.getText());
		lblEdit.setVisible(true);
	    }
	});
	btnUpdateServer.setBounds(137, 229, 128, 23);
	contentPane.add(btnUpdateServer);

	setEditVisable(false);
    }

    /**
     * Toggle the visablity of the components
     * 
     * @param b
     *            True or false
     */
    private void setEditVisable(boolean b) {
	lblHostname.setVisible(b);
	hostnameField.setVisible(b);
	lblUsername.setVisible(b);
	usernameField.setVisible(b);
	lblPort.setVisible(b);
	portField.setVisible(b);
	lblPassword.setVisible(b);
	passwordField.setVisible(b);
	lblFolderLocation.setVisible(b);
	folderField.setVisible(b);
	btnUpdateServer.setVisible(b);
    }
}
