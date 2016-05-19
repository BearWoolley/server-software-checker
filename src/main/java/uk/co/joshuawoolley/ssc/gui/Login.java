package uk.co.joshuawoolley.ssc.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import uk.co.joshuawoolley.ssc.user.UserManager;

public class Login extends JFrame {

    private static final long serialVersionUID = -8818903087331389628L;

    private UserManager um;

    private JPanel contentPane;
    private static Login frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    frame = new Login();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the Login frame for Login.
     */
    public Login() {
	um = new UserManager();
	setTitle("Server Software Checker");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	final Label errorLbl = new Label("The Username or Password was incorrectly entered.");
	errorLbl.setForeground(Color.RED);
	errorLbl.setBounds(79, 42, 302, 20);
	errorLbl.setVisible(false);
	contentPane.add(errorLbl);

	final JTextField usernameField = new JTextField();
	usernameField.setBounds(175, 82, 86, 20);
	contentPane.add(usernameField);
	usernameField.setColumns(10);

	final JPasswordField passwordField = new JPasswordField();
	passwordField.setBounds(175, 110, 86, 20);
	contentPane.add(passwordField);

	JLabel lblUsername = new JLabel("Username:");
	lblUsername.setBounds(108, 85, 66, 14);
	contentPane.add(lblUsername);

	JLabel lblPassword = new JLabel("Password:");
	lblPassword.setBounds(108, 116, 66, 14);
	contentPane.add(lblPassword);

	JButton btnLogin = new JButton("Login");
	btnLogin.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String username = usernameField.getText();
		String password = String.valueOf(passwordField.getPassword());
		if (!um.validateUser(username, password)) {
		    errorLbl.setVisible(true);
		} else {
		    frame.setVisible(false);
		    Home.main(null);
		}
	    }
	});
	btnLogin.setBounds(153, 150, 89, 23);
	contentPane.add(btnLogin);

    }
}
