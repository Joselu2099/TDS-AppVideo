package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.AppVideo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;

public class ProfileWindow {

	private JFrame frmProfile;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblMail;
	private JLabel lblUsername;
	private JLabel lblPremium;
	private JLabel lblFilter;
	private JButton btnBack;
	

	/**
	 * Create the application.
	 */
	public ProfileWindow() {
		initialize();
	}
	
	/**
	 * Show actual window
	 */
	public void showWindow() {
		frmProfile.setLocationRelativeTo(null);
		frmProfile.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProfile = new JFrame();
		frmProfile.getContentPane().setBackground(Color.WHITE);
		frmProfile.setMinimumSize(new Dimension(740, 580));
		frmProfile.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginWindow.class.getResource("/images/multimediavideoplayer_128px.png")));
		frmProfile.setBounds(100, 100, 740, 580);
		frmProfile.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 0, 0, 20, 50, 20, 0, 0, 50, 0};
		gridBagLayout.rowHeights = new int[]{50, 0, 0, 0, 40, 0, 0, 29, 0, 0, 0, 30, 0, 0, 50, 40, 50, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 2.0, 0.0, 1.0, 0.0, 1.0, 0.0, 2.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 2.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frmProfile.getContentPane().setLayout(gridBagLayout);
		frmProfile.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				goToAppVideoWindow();
			}
		});
		
		JLabel lblAppVideoIcon = new JLabel("");
		lblAppVideoIcon.setIcon(new ImageIcon(SettingsWindow.class.getResource("/images/multimediavideoplayer_128px.png")));
		GridBagConstraints gbc_lblAppVideoIcon = new GridBagConstraints();
		gbc_lblAppVideoIcon.insets = new Insets(0, 0, 5, 5);
		gbc_lblAppVideoIcon.gridx = 4;
		gbc_lblAppVideoIcon.gridy = 3;
		frmProfile.getContentPane().add(lblAppVideoIcon, gbc_lblAppVideoIcon);
		
		lblName = new JLabel("Name: " + AppVideo.getInstance().getActualUser().getName());
		lblName.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 6;
		frmProfile.getContentPane().add(lblName, gbc_lblName);
		
		lblSurname = new JLabel("Surname: " + AppVideo.getInstance().getActualUser().getSurname());
		lblSurname.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 2;
		gbc_lblSurname.gridy = 7;
		frmProfile.getContentPane().add(lblSurname, gbc_lblSurname);
		
		lblMail = new JLabel("Mail: " + AppVideo.getInstance().getActualUser().getMail());
		lblMail.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		GridBagConstraints gbc_lblMail = new GridBagConstraints();
		gbc_lblMail.insets = new Insets(0, 0, 5, 5);
		gbc_lblMail.gridx = 2;
		gbc_lblMail.gridy = 8;
		frmProfile.getContentPane().add(lblMail, gbc_lblMail);
		
		lblUsername = new JLabel("Username: " + AppVideo.getInstance().getActualUser().getUsername());
		lblUsername.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 2;
		gbc_lblUsername.gridy = 10;
		frmProfile.getContentPane().add(lblUsername, gbc_lblUsername);
		
		lblFilter = new JLabel("Filter: " + AppVideo.getInstance().getActualUser().getFilter());
		lblFilter.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		GridBagConstraints gbc_lblFilter = new GridBagConstraints();
		gbc_lblFilter.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilter.gridx = 6;
		gbc_lblFilter.gridy = 6;
		frmProfile.getContentPane().add(lblFilter, gbc_lblFilter);
		
		lblPremium = new JLabel("Premium: " + AppVideo.getInstance().getActualUser().getPremium());
		lblPremium.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		GridBagConstraints gbc_lblPremium = new GridBagConstraints();
		gbc_lblPremium.insets = new Insets(0, 0, 5, 5);
		gbc_lblPremium.gridx = 6;
		gbc_lblPremium.gridy = 8;
		frmProfile.getContentPane().add(lblPremium, gbc_lblPremium);
		
		JButton btnChangeMail = new JButton("Change Mail");
		btnChangeMail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				while(true) {
					
					String newMail = (String) JOptionPane.showInputDialog(frmProfile.getContentPane(), "Intruduce un nuevo mail : ", 
							"Change of mail",JOptionPane.PLAIN_MESSAGE, null, null,"mail");
					
					if(newMail.trim().contains("@")) {
						AppVideo.getInstance().changeMail(newMail); 
						lblMail.setText("Mail: " + AppVideo.getInstance().getActualUser().getMail());
						break;
					}
					else JOptionPane.showMessageDialog(frmProfile.getContentPane(), "El formato del Mail es incorrecto, ejemplo de uso: usuario@gmail.com", "Change of mail", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnChangeMail.setIcon(new ImageIcon(ProfileWindow.class.getResource("/images/changeMailIcon.png")));
		btnChangeMail.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		btnChangeMail.setForeground(new Color(255, 255, 255));
		btnChangeMail.setBackground(new Color(138, 43, 226));
		GridBagConstraints gbc_btnChangeMail = new GridBagConstraints();
		gbc_btnChangeMail.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnChangeMail.insets = new Insets(0, 0, 5, 5);
		gbc_btnChangeMail.gridx = 2;
		gbc_btnChangeMail.gridy = 9;
		frmProfile.getContentPane().add(btnChangeMail, gbc_btnChangeMail);
		
		JButton btnPremium = new JButton("Become premium");
		btnPremium.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AppVideo.getInstance().becomePremium();
				lblPremium.setText("Premium: " + AppVideo.getInstance().getActualUser().getPremium());
			}
		});
		btnPremium.setIcon(new ImageIcon(ProfileWindow.class.getResource("/images/premiumIcon.png")));
		btnPremium.setSelectedIcon(null);
		btnPremium.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		btnPremium.setForeground(new Color(255, 255, 255));
		btnPremium.setBackground(new Color(138, 43, 226));
		GridBagConstraints gbc_btnPremium = new GridBagConstraints();
		gbc_btnPremium.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPremium.insets = new Insets(0, 0, 5, 5);
		gbc_btnPremium.gridx = 6;
		gbc_btnPremium.gridy = 9;
		frmProfile.getContentPane().add(btnPremium, gbc_btnPremium);
		
		JButton btnChangePassword = new JButton("Change password");
		btnChangePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				while(true) {
					
					String actualPassword = (String) JOptionPane.showInputDialog(frmProfile.getContentPane(), "Intruduce tu contraseña actual : ", 
							"Change of password",JOptionPane.PLAIN_MESSAGE, null, null,"password");
					
					if(AppVideo.getInstance().getActualUser().getPassword().equals(AppVideo.getInstance().encodePassword(actualPassword))) {
						
						String newPassword = (String) JOptionPane.showInputDialog(frmProfile.getContentPane(), "Introduce la nueva contraseña : ", 
								"Change of password",JOptionPane.PLAIN_MESSAGE, null, null,"password");
						
						if(newPassword.length() < AppVideo.MIN_PASSWORD_LENGTH) JOptionPane.showMessageDialog(frmProfile.getContentPane(), 
								"La contraseña debe tener al menos "+AppVideo.MIN_PASSWORD_LENGTH+" caracteres", "Change of password", JOptionPane.ERROR_MESSAGE);
						else{
							AppVideo.getInstance().changePassword(newPassword);
							break;
						}
						
					}else {
						JOptionPane.showMessageDialog(frmProfile.getContentPane(), "Contraseña incorrecta, prueba otra vez", "Change of password", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnChangePassword.setIcon(new ImageIcon(ProfileWindow.class.getResource("/images/changePasswordIcon.png")));
		btnChangePassword.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		btnChangePassword.setForeground(new Color(255, 255, 255));
		btnChangePassword.setBackground(new Color(138, 43, 226));
		GridBagConstraints gbc_btnChangePassword = new GridBagConstraints();
		gbc_btnChangePassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnChangePassword.insets = new Insets(0, 0, 5, 5);
		gbc_btnChangePassword.gridx = 2;
		gbc_btnChangePassword.gridy = 11;
		frmProfile.getContentPane().add(btnChangePassword, gbc_btnChangePassword);
		
		JButton btnGeneratePDF = new JButton("Generate PDF");
		btnGeneratePDF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(AppVideo.getInstance().getActualUser().isPremium()) AppVideo.getInstance().generatePDF();
				else JOptionPane.showMessageDialog(frmProfile.getContentPane(), "Esta funcion es solo para usuarios premium", "Generate PDF", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnGeneratePDF.setIcon(new ImageIcon(ProfileWindow.class.getResource("/images/pdfIcon.png")));
		btnGeneratePDF.setSelectedIcon(null);
		btnGeneratePDF.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		btnGeneratePDF.setForeground(new Color(255, 255, 255));
		btnGeneratePDF.setBackground(new Color(138, 43, 226));
		GridBagConstraints gbc_btnGeneratePDF = new GridBagConstraints();
		gbc_btnGeneratePDF.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGeneratePDF.insets = new Insets(0, 0, 5, 5);
		gbc_btnGeneratePDF.gridx = 6;
		gbc_btnGeneratePDF.gridy = 11;
		frmProfile.getContentPane().add(btnGeneratePDF, gbc_btnGeneratePDF);
		
		btnBack = new JButton("");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToAppVideoWindow();
			}
		});
		btnBack.setBackground(Color.WHITE);
		btnBack.setBorderPainted(false);
		btnBack.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(138, 43, 226)));
		btnBack.setIcon(new ImageIcon(ProfileWindow.class.getResource("/images/backIcon.png")));
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnBack.gridx = 1;
		gbc_btnBack.gridy = 15;
		frmProfile.getContentPane().add(btnBack, gbc_btnBack);
	}
	
	private void goToAppVideoWindow() {
		AppVideoWindow appVideo = new AppVideoWindow();
		frmProfile.dispose();
		appVideo.showWindow();
	}

}
