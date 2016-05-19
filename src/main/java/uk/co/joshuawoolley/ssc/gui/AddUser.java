package uk.co.joshuawoolley.ssc.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
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
import uk.co.joshuawoolley.ssc.user.UserManager;
import uk.co.joshuawoolley.ssc.user.UserType;

public class AddUser extends JFrame {

    private static final long serialVersionUID = -6990821843324804899L;

    private UserManager um;

    private JPanel contentPane;
    private static AddUser frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	if (ServerSoftwareChecker.user != null) {
	    EventQueue.invokeLater(new Runnable() {
		public void run() {
		    try {
			frame = new AddUser();
			frame.setVisible(true);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    });
	}
    }

    /**
     * Create the frame for AddUser.
     */
    public AddUser() {
	um = new UserManager();
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

	JLabel lblAddUser = new JLabel("Add User:");
	lblAddUser.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblAddUser.setBounds(10, 73, 70, 14);
	contentPane.add(lblAddUser);

	final Label successLbl = new Label("User was successfully added to the application");
	successLbl.setBounds(86, 85, 319, 22);
	contentPane.add(successLbl);
	successLbl.setVisible(false);

	JLabel lblNewLabel = new JLabel("Username:");
	lblNewLabel.setBounds(45, 120, 90, 14);
	contentPane.add(lblNewLabel);

	usernameField = new JTextField();
	usernameField.setBounds(114, 117, 86, 20);
	contentPane.add(usernameField);
	usernameField.setColumns(10);

	JLabel lblPassword = new JLabel("Password: ");
	lblPassword.setBounds(228, 120, 70, 14);
	contentPane.add(lblPassword);

	passwordField = new JPasswordField();
	passwordField.setBounds(295, 117, 97, 20);
	contentPane.add(passwordField);

	final JComboBox<UserType> userTypeBox = new JComboBox<UserType>();
	userTypeBox.setToolTipText("Select the type for the user");
	userTypeBox.setModel(new DefaultComboBoxModel<UserType>(UserType.values()));
	userTypeBox.setBounds(190, 173, 97, 20);
	userTypeBox.setSelectedIndex(-1);
	contentPane.add(userTypeBox);

	JLabel lblUserType = new JLabel("User Type:");
	lblUserType.setBounds(119, 176, 70, 14);
	contentPane.add(lblUserType);

	JButton btnAddUser = new JButton("Add User");
	btnAddUser.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String username = usernameField.getText();
		String password = String.valueOf(passwordField.getPassword());
		UserType ut = (UserType) userTypeBox.getSelectedItem();
		boolean result = um.addUser(username, password, ut);
		if (result) {
		    successLbl.setVisible(true);
		}
		usernameField.setText("");
		passwordField.setText("");
		userTypeBox.setSelectedIndex(-1);
	    }
	});
	btnAddUser.setBounds(174, 215, 89, 23);
	contentPane.add(btnAddUser);
    }
}
