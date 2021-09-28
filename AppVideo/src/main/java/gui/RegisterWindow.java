package gui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import controller.AppVideo;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

public class RegisterWindow {

	private JFrame frmRegister;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private JPasswordField repeatedPasswordField;
	private JTextField textMail;
	private JDateChooser dateOfBirth;
	
	
	/**
	 * Create the application.
	 */
	public RegisterWindow() {
		initialize();
	}

	/**
	 * Show actual window
	 */
	public void showWindow() {
		frmRegister.setLocationRelativeTo(null);
		frmRegister.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegister = new JFrame();
		frmRegister.setMinimumSize(new Dimension(854, 480));
		frmRegister.getContentPane().setBackground(Color.WHITE);
		frmRegister.setBackground(Color.BLACK);
		frmRegister.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginWindow.class.getResource("/images/multimediavideoplayer_128px.png")));
		frmRegister.setTitle("AppVideo");
		frmRegister.setBounds(100, 100, 854, 480);
		frmRegister.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegister.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				goToLoginWindow();
			}
		});
		frmRegister.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPaneRegistro = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneRegistro.setBackground(Color.LIGHT_GRAY);
		frmRegister.getContentPane().add(tabbedPaneRegistro);

		JPanel panelPadre = new JPanel();
		GridBagConstraints gbc_panelPadre = new GridBagConstraints();
		gbc_panelPadre.insets = new Insets(0, 0, 5, 5);
		gbc_panelPadre.fill = GridBagConstraints.BOTH;
		gbc_panelPadre.gridx = 0;
		gbc_panelPadre.gridy = 0;
		//panel.add(panel_1, gbc_panel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		tabbedPaneRegistro.addTab("Registro", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 60, 0, 0, 64, 131, 60, 0 };
		gbl_panel.rowHeights = new int[] { 40, 0, 0, 0, 0, 0, 0, 60, 0, 40, 0, 0, 60, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		

		JLabel lblName = new JLabel("Nombre: ");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		panel.add(lblName, gbc_lblName);

		textName = new JTextField();
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.gridwidth = 3;
		gbc_textName.insets = new Insets(0, 0, 5, 5);
		gbc_textName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textName.gridx = 2;
		gbc_textName.gridy = 1;
		panel.add(textName, gbc_textName);
		textName.setColumns(10);

		JLabel lblSurname = new JLabel("Apellidos: ");
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.anchor = GridBagConstraints.EAST;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 1;
		gbc_lblSurname.gridy = 2;
		panel.add(lblSurname, gbc_lblSurname);

		textSurname = new JTextField();
		GridBagConstraints gbc_textSurname = new GridBagConstraints();
		gbc_textSurname.gridwidth = 3;
		gbc_textSurname.insets = new Insets(0, 0, 5, 5);
		gbc_textSurname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSurname.gridx = 2;
		gbc_textSurname.gridy = 2;
		panel.add(textSurname, gbc_textSurname);
		textSurname.setColumns(10);

		JLabel lblUsername = new JLabel("Usuario: ");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 3;
		panel.add(lblUsername, gbc_lblUsername);

		textUsername = new JTextField();
		GridBagConstraints gbc_textUsername = new GridBagConstraints();
		gbc_textUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUsername.insets = new Insets(0, 0, 5, 5);
		gbc_textUsername.gridx = 2;
		gbc_textUsername.gridy = 3;
		panel.add(textUsername, gbc_textUsername);
		textUsername.setColumns(15);

		JLabel lblPassword = new JLabel("Contraseña: ");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 4;
		panel.add(lblPassword, gbc_lblPassword);

		/*
		password = new JPasswordField();
		password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(getVentanaRegistro(), "Se recomienda que la contraseña tenga un minimo de 8 caracteres "
											+ "y que incluya letras mayusculas,minusculas y números para una mayor seguridad.", "Contraseña", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		password.setColumns(15);
		GridBagConstraints gbc_password = new GridBagConstraints();
		gbc_password.fill = GridBagConstraints.HORIZONTAL;
		gbc_password.insets = new Insets(0, 0, 5, 5);
		gbc_password.gridx = 2;
		gbc_password.gridy = 4;
		panel.add(password, gbc_password);
		*/
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 4;
		panel.add(passwordField, gbc_passwordField);
		
		JLabel lblRepeatedPassword = new JLabel("Repetir: ");
		GridBagConstraints gbc_lblRepeatedPassword = new GridBagConstraints();
		gbc_lblRepeatedPassword.anchor = GridBagConstraints.EAST;
		gbc_lblRepeatedPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepeatedPassword.gridx = 3;
		gbc_lblRepeatedPassword.gridy = 4;
		panel.add(lblRepeatedPassword, gbc_lblRepeatedPassword);
		
		repeatedPasswordField = new JPasswordField();
		GridBagConstraints gbc_repeatedPasswordField = new GridBagConstraints();
		gbc_repeatedPasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_repeatedPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_repeatedPasswordField.gridx = 4;
		gbc_repeatedPasswordField.gridy = 4;
		panel.add(repeatedPasswordField, gbc_repeatedPasswordField);
		
		JLabel lblMail = new JLabel("Mail: ");
		GridBagConstraints gbc_lblMail = new GridBagConstraints();
		gbc_lblMail.anchor = GridBagConstraints.EAST;
		gbc_lblMail.insets = new Insets(0, 0, 5, 5);
		gbc_lblMail.gridx = 1;
		gbc_lblMail.gridy = 5;
		panel.add(lblMail, gbc_lblMail);

		textMail = new JTextField();
		GridBagConstraints gbc_textMail = new GridBagConstraints();
		gbc_textMail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMail.gridwidth = 3;
		gbc_textMail.insets = new Insets(0, 0, 5, 5);
		gbc_textMail.gridx = 2;
		gbc_textMail.gridy = 5;
		panel.add(textMail, gbc_textMail);
		textMail.setColumns(15);

		JLabel lblDateOfBirth = new JLabel("Fecha: ");
		GridBagConstraints gbc_lblDateOfBirth = new GridBagConstraints();
		gbc_lblDateOfBirth.anchor = GridBagConstraints.EAST;
		gbc_lblDateOfBirth.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateOfBirth.gridx = 1;
		gbc_lblDateOfBirth.gridy = 6;
		panel.add(lblDateOfBirth, gbc_lblDateOfBirth);

		dateOfBirth = new JDateChooser();
		GridBagConstraints gbc_dateOfBirth = new GridBagConstraints();
		gbc_dateOfBirth.insets = new Insets(0, 0, 5, 5);
		gbc_dateOfBirth.fill = GridBagConstraints.BOTH;
		gbc_dateOfBirth.gridx = 2;
		gbc_dateOfBirth.gridy = 6;
		panel.add(dateOfBirth, gbc_dateOfBirth);
		
		final JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				goToLoginWindow();
			}
		});
		
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 8;
		panel.add(btnCancelar, gbc_btnCancelar);

		final JButton btnRegister = new JButton("Register");
		btnRegister.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				register();
			}
		});
		
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistrar.gridx = 4;
		gbc_btnRegistrar.gridy = 8;
		panel.add(btnRegister, gbc_btnRegistrar);
		
		JLabel lblWarning = new JLabel("");
		GridBagConstraints gbc_lblWarning = new GridBagConstraints();
		gbc_lblWarning.insets = new Insets(0, 0, 5, 5);
		gbc_lblWarning.gridx = 2;
		gbc_lblWarning.gridy = 10;
		panel.add(lblWarning, gbc_lblWarning);
		
		JTabbedPane tabbedPaneServicios = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneRegistro.addTab("Servicios", null, tabbedPaneServicios, null);	
	}
	
	/**
	 * Comprueba que los campos de registro estén bien
	 */
	private boolean checkFields() {
		boolean salida = true;
		//borrar todos los errores en pantalla 
		//ocultarErrores();
		if (textName.getText().trim().isEmpty()) {
			//JOptionPane.showMessageDialog(textNombre, "El campo nombre no ha sido rellenado");
			textName.setForeground(Color.RED);
			textName.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (textSurname.getText().trim().isEmpty()) {
			//JOptionPane.showMessageDialog(textSurname, "El campo apellidos no ha sido rellenado");
			textSurname.setForeground(Color.RED);
			textSurname.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (textMail.getText().trim().isEmpty()) {
			//JOptionPane.showMessageDialog(textMail, "El campo mail no ha sido rellenado");
			textMail.setForeground(Color.RED);
			textMail.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (textUsername.getText().trim().isEmpty()) {
			//JOptionPane.showMessageDialog(textUser, "El campo usuario no ha sido rellenado");
			textUsername.setForeground(Color.RED);
			textUsername.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		String password = new String(passwordField.getPassword());
		String password2 = new String(repeatedPasswordField.getPassword());
		if (password.isEmpty()) {
			//JOptionPane.showMessageDialog(password, "El campo contraseña no ha sido rellenado");
			passwordField.setForeground(Color.RED);
			passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		} 
		if (password2.isEmpty()) {
			//JOptionPane.showMessageDialog(repeatedPassword, "Es necesario repetir la contraseña");
			repeatedPasswordField.setForeground(Color.RED);
			repeatedPasswordField.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		} 
		
		if (!password.equals(password2)) {
			passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
			repeatedPasswordField.setBorder(BorderFactory.createLineBorder(Color.RED));
			JOptionPane.showMessageDialog(frmRegister, "Las contraseñas no coinciden.\n","Registro", JOptionPane.ERROR_MESSAGE);
			salida = false;
		}
		
		if (password.length()<AppVideo.MIN_PASSLENGTH){
			passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
			repeatedPasswordField.setBorder(BorderFactory.createLineBorder(Color.RED));
			JOptionPane.showMessageDialog(frmRegister, "La contraseña debe tener al menos "+AppVideo.MIN_PASSLENGTH+" caracteres.\n","Registro", JOptionPane.ERROR_MESSAGE);
			salida = false;
		}
		
		if (!textMail.getText().trim().contains("@")) {
			textMail.setForeground(Color.RED);
			textMail.setBorder(BorderFactory.createLineBorder(Color.RED));
			JOptionPane.showMessageDialog(frmRegister, "El email no es válido.","Registro", JOptionPane.ERROR_MESSAGE);
			salida = false;
		}
		
		//Comprobar que no exista otro usuario con igual login
		if (AppVideo.getInstance().isUserRegistered(textUsername.getText())) {
			JOptionPane.showMessageDialog(frmRegister, "Ese nombre de usuario ya esta en uso, prueba con otro.\n","Registro", JOptionPane.ERROR_MESSAGE);
			textUsername.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		
		try {
			if( !parseLocalDate(dateOfBirth).toString().matches("\\d{4}-\\d{2}-\\d{2}") ) {
				dateOfBirth.setBorder(BorderFactory.createLineBorder(Color.RED));
				dateOfBirth.setForeground(Color.RED);
				JOptionPane.showMessageDialog(frmRegister, "El formato de la fecha es incorrecto.\n","Registro", JOptionPane.ERROR_MESSAGE);
				salida = false;
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(frmRegister, "El formato de la fecha es incorrecto.\n","Registro", JOptionPane.ERROR_MESSAGE);
		}
		
		frmRegister.revalidate();
		frmRegister.pack();
		
		return salida;
	}
	
	/**
	 * Cambia el formato de la fecha a LocalDate
	 */
	public LocalDate parseLocalDate(JDateChooser myDate){
		return myDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	/**
	 * Vuelve a la ventana de Login
	 */
	private void goToLoginWindow() {
		LoginWindow login = new LoginWindow();
		frmRegister.dispose();
		login.showWindow();
	}

	/**
	 * Registra al usuario si se cumplen todas las condiciones
	 */
	private void register() {
		String username = textUsername.getText();
		String password = new String(passwordField.getPassword());
		String mail = textMail.getText();
		String name = textName.getText();
		String surname = textSurname.getText();
		if(checkFields()) {
			LocalDate date = parseLocalDate(dateOfBirth);
			boolean registrado = false;
			registrado = AppVideo.getInstance().registerUser(name, surname, mail, username, password, 
					date.toString());
			if (registrado) {
				JOptionPane.showMessageDialog(frmRegister, "Asistente registrado correctamente.", "Registro", JOptionPane.INFORMATION_MESSAGE);
				goToLoginWindow();
			} else {
				JOptionPane.showMessageDialog(frmRegister, "No se ha podido llevar a cabo el registro.\n","Registro", JOptionPane.ERROR_MESSAGE);
				frmRegister.setTitle("Login Gestor Eventos");
			}
		}
	}
}