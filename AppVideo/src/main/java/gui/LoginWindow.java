package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import controller.AppVideo;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class LoginWindow {

	/**
	 *  Atributes
	 */
	private JFrame frmLogin;
	private JTextField textUser;
	private JPasswordField passwordField;
	
	
	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		initialize();
	}
	
	/**
	 * Show actual window
	 */
	public void showWindow() {
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setBackground(Color.BLACK);
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginWindow.class.getResource("/images/multimediavideoplayer_128px.png")));
		frmLogin.setTitle("AppVideo");
		frmLogin.setBounds(100, 100, 1080, 720);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(new BoxLayout(frmLogin.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(0, 0));
		frmLogin.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{20, 10, 0, 205, 0, 10, 20, 0};
		gbl_panel.rowHeights = new int[]{20, 20, 0, 0, 20, 50, 0, 5, 0, 35, 0, 30, 30, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel AppVideoLetters = new JLabel("");
		AppVideoLetters.setIcon(new ImageIcon(LoginWindow.class.getResource("/images/AppVideo-letra.png")));
		AppVideoLetters.setPreferredSize(new Dimension(150, 30));
		AppVideoLetters.setForeground(new Color(148, 0, 211));
		AppVideoLetters.setFont(new Font("Virus Killer", Font.PLAIN, 32));
		GridBagConstraints gbc_AppVideoLetters = new GridBagConstraints();
		gbc_AppVideoLetters.insets = new Insets(0, 0, 5, 5);
		gbc_AppVideoLetters.gridx = 3;
		gbc_AppVideoLetters.gridy = 2;
		panel.add(AppVideoLetters, gbc_AppVideoLetters);
		
		JLabel AppVideoIcon = new JLabel("");
		AppVideoIcon.setIcon(new ImageIcon(LoginWindow.class.getResource("/images/multimediavideoplayer_128px.png")));
		GridBagConstraints gbc_AppVideoIcon = new GridBagConstraints();
		gbc_AppVideoIcon.insets = new Insets(0, 0, 5, 5);
		gbc_AppVideoIcon.gridx = 3;
		gbc_AppVideoIcon.gridy = 3;
		panel.add(AppVideoIcon, gbc_AppVideoIcon);
		
		JLabel UserIcon = new JLabel("");
		UserIcon.setIcon(new ImageIcon(LoginWindow.class.getResource("/images/UserIcon.png")));
		GridBagConstraints gbc_UserIcon = new GridBagConstraints();
		gbc_UserIcon.insets = new Insets(0, 0, 5, 5);
		gbc_UserIcon.anchor = GridBagConstraints.EAST;
		gbc_UserIcon.gridx = 2;
		gbc_UserIcon.gridy = 6;
		panel.add(UserIcon, gbc_UserIcon);
		
		textUser = new JTextField();
		textUser.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textUser.setForeground(SystemColor.windowBorder);
		textUser.setBackground(new Color(255, 255, 255));
		textUser.setHorizontalAlignment(SwingConstants.CENTER);
		textUser.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		textUser.setPreferredSize(new Dimension(7, 40));
		GridBagConstraints gbc_textUser = new GridBagConstraints();
		gbc_textUser.gridwidth = 2;
		gbc_textUser.insets = new Insets(0, 0, 5, 5);
		gbc_textUser.fill = GridBagConstraints.BOTH;
		gbc_textUser.gridx = 3;
		gbc_textUser.gridy = 6;
		panel.add(textUser, gbc_textUser);
		textUser.setColumns(10);
		
		JLabel PasswordIcon = new JLabel("");
		PasswordIcon.setIcon(new ImageIcon(LoginWindow.class.getResource("/images/passwordIcon.png")));
		GridBagConstraints gbc_PasswordIcon = new GridBagConstraints();
		gbc_PasswordIcon.insets = new Insets(0, 0, 5, 5);
		gbc_PasswordIcon.anchor = GridBagConstraints.EAST;
		gbc_PasswordIcon.gridx = 2;
		gbc_PasswordIcon.gridy = 8;
		panel.add(PasswordIcon, gbc_PasswordIcon);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		passwordField.setForeground(SystemColor.windowBorder);
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		passwordField.setPreferredSize(new Dimension(7, 40));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 8;
		panel.add(passwordField, gbc_passwordField);
		
		JButton btnLogin = new JButton("");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				loginUser();
			}
		});
		
		btnLogin.setPreferredSize(new Dimension(48, 48));
		btnLogin.setSize(new Dimension(48, 48));
		btnLogin.setBorderPainted(false);
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setIcon(new ImageIcon(LoginWindow.class.getResource("/images/join_enter_login_icon.png")));
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 3;
		gbc_btnLogin.gridy = 10;
		panel.add(btnLogin, gbc_btnLogin);
		
		JLabel lblNewLabel = new JLabel("not registered yet?");
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 13;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				registerUser();
			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new Color(102, 0, 204));
		btnRegister.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		btnRegister.setIcon(new ImageIcon(LoginWindow.class.getResource("/images/RegisterIcon.png")));
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.insets = new Insets(0, 0, 0, 5);
		gbc_btnRegister.gridx = 3;
		gbc_btnRegister.gridy = 14;
		panel.add(btnRegister, gbc_btnRegister);
		
		JLabel ImageLoginInterface = new JLabel("");
		ImageLoginInterface.setIcon(new ImageIcon(LoginWindow.class.getResource("/images/LoginFrameFont.jpg")));
		frmLogin.getContentPane().add(ImageLoginInterface);
	}
	
	private void loginUser() {
		if (textUser.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El usuario o clave son incorrectos", "Login Fallido",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		String user = textUser.getText();
		String pass = new String(passwordField.getPassword());
		if(AppVideo.getInstance().isUserRegistered(textUser.getText())){
			boolean login = AppVideo.getInstance().login(user, pass);
			if (!login) {
				JOptionPane.showMessageDialog(null, "El usuario o clave son incorrectos", "Login Fallido",
						JOptionPane.ERROR_MESSAGE);
			} else {
				//Crear ventana principal y mostrarla
				AppVideoWindow appVideoWindow = new AppVideoWindow();
				appVideoWindow.showWindow();
				frmLogin.setVisible(false);
			}
		}else {
			JOptionPane.showMessageDialog(frmLogin, "El usuario " + user +" no esta registrado en AppMusic.\n","Login fallido", JOptionPane.ERROR_MESSAGE);
			frmLogin.setTitle("Login Gestor Eventos");
		}
	}
	
	private void registerUser() {
		RegisterWindow registerWindow = new RegisterWindow();
		registerWindow.showWindow();
		frmLogin.setVisible(false);
	}
}