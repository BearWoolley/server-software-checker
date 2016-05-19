package uk.co.joshuawoolley.ssc.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

public class EditUser extends JFrame {

    private static final long serialVersionUID = -2739233325902689995L;

    private static EditUser frame;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblUserType;

    final JComboBox<UserType> userTypeBox;
    private JButton btnUpdateUser;
    private Label lblEdit;
    private Label lblDelete;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	if (ServerSoftwareChecker.user != null) {
	    EventQueue.invokeLater(new Runnable() {
		public void run() {
		    try {
			frame = new EditUser();
			frame.setVisible(true);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    });
	}
    }

    /**
     * Create the frame for EditUser.
     */
    public EditUser() {
	final UserManager um = new UserManager();

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
	contentPane.setLayout(null);
	contentPane.setLayout(null);
	btnHome.setBounds(10, 11, 128, 38);
	contentPane.add(btnHome);

	JSeparator separator = new JSeparator();
	separator.setBounds(10, 60, 414, 2);
	contentPane.add(separator);

	JLabel lblSelectUser = new JLabel("Select User:");
	lblSelectUser.setFont(new Font("Tahoma", Font.BOLD, 11));
	lblSelectUser.setBounds(10, 73, 73, 14);
	contentPane.add(lblSelectUser);

	final JComboBox<String> userComboBox = new JComboBox<String>();
	userComboBox.setBounds(82, 70, 128, 20);
	userComboBox.addItem(null);
	List<String> users = um.getUserNames();
	for (String user : users) {
	    userComboBox.addItem(user);
	}
	contentPane.add(userComboBox);

	lblEdit = new Label("User successfully edited");
	lblEdit.setBounds(148, 99, 186, 22);
	lblEdit.setVisible(false);
	contentPane.add(lblEdit);

	lblDelete = new Label("User successfully deleted");
	lblDelete.setBounds(148, 99, 176, 22);
	lblDelete.setVisible(false);
	contentPane.add(lblDelete);

	JButton btnEditUser = new JButton("Edit");
	btnEditUser.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String username = (String) userComboBox.getSelectedItem();
		if (username != null) {
		    usernameField.setText(username);
		    usernameField.setEditable(false);
		    setEditVisable(true);
		}
	    }
	});
	btnEditUser.setBounds(220, 69, 89, 23);
	contentPane.add(btnEditUser);

	JButton btnDelete = new JButton("Delete");
	btnDelete.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (userComboBox.getSelectedItem() != null) {
		    um.deleteUser((String) userComboBox.getSelectedItem());
		    userComboBox.setSelectedIndex(0);
		    lblDelete.setVisible(true);
		}
	    }
	});
	btnDelete.setBounds(319, 69, 89, 23);
	contentPane.add(btnDelete);

	lblUsername = new JLabel("Username:");
	lblUsername.setBounds(45, 145, 90, 14);
	contentPane.add(lblUsername);

	usernameField = new JTextField();
	usernameField.setBounds(114, 142, 86, 20);
	contentPane.add(usernameField);
	usernameField.setColumns(10);

	lblPassword = new JLabel("Password: ");
	lblPassword.setBounds(228, 145, 70, 14);
	contentPane.add(lblPassword);

	passwordField = new JPasswordField();
	passwordField.setBounds(295, 142, 97, 20);
	contentPane.add(passwordField);

	userTypeBox = new JComboBox<UserType>();
	userTypeBox.setToolTipText("Select the type for the user");
	userTypeBox.setModel(new DefaultComboBoxModel<UserType>(UserType.values()));
	userTypeBox.setBounds(190, 173, 97, 20);
	userTypeBox.setSelectedIndex(-1);
	contentPane.add(userTypeBox);

	lblUserType = new JLabel("User Type:");
	lblUserType.setBounds(119, 176, 70, 14);
	contentPane.add(lblUserType);

	btnUpdateUser = new JButton("Update User");
	btnUpdateUser.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		um.updateUser(usernameField.getText(), String.valueOf(passwordField.getPassword()),
			(UserType) userTypeBox.getSelectedItem());
		lblEdit.setVisible(true);
	    }
	});
	btnUpdateUser.setBounds(157, 214, 111, 23);
	contentPane.add(btnUpdateUser);

	setEditVisable(false);
    }

    /**
     * Toggle the visablity of the components
     * 
     * @param b
     *            True or false
     */
    private void setEditVisable(boolean b) {
	lblUsername.setVisible(b);
	lblPassword.setVisible(b);
	lblUserType.setVisible(b);
	usernameField.setVisible(b);
	passwordField.setVisible(b);
	userTypeBox.setVisible(b);
	btnUpdateUser.setVisible(b);
    }

}
