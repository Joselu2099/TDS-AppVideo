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

import controller.AppVideo;

public class ProfileWindow {

	private JFrame frmProfile;

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
		frmProfile.setMinimumSize(new Dimension(640, 480));
		frmProfile.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginWindow.class.getResource("/images/multimediavideoplayer_128px.png")));
		frmProfile.setBounds(100, 100, 640, 480);
		frmProfile.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 0, 0, 20, 50, 20, 0, 0, 50, 0};
		gridBagLayout.rowHeights = new int[]{50, 0, 0, 0, 40, 0, 0, 29, 0, 0, 0, 30, 0, 0, 50, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 2.0, 0.0, 1.0, 0.0, 1.0, 0.0, 2.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 2.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, 1.0, 0.0, Double.MIN_VALUE};
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
		
		JLabel lblName = new JLabel("Name: " + AppVideo.getInstance().getActualUser().getName());
		lblName.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 6;
		frmProfile.getContentPane().add(lblName, gbc_lblName);
		
		JLabel lblPremium = new JLabel("Premium: ");
		lblPremium.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		GridBagConstraints gbc_lblPremium = new GridBagConstraints();
		gbc_lblPremium.insets = new Insets(0, 0, 5, 5);
		gbc_lblPremium.gridx = 6;
		gbc_lblPremium.gridy = 6;
		frmProfile.getContentPane().add(lblPremium, gbc_lblPremium);
		
		JLabel lblSurname = new JLabel("Surname: " + AppVideo.getInstance().getActualUser().getSurname());
		lblSurname.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 2;
		gbc_lblSurname.gridy = 7;
		frmProfile.getContentPane().add(lblSurname, gbc_lblSurname);
		
		JButton btnPremium = new JButton("Become premium");
		btnPremium.setIcon(new ImageIcon(ProfileWindow.class.getResource("/images/premiumIcon.png")));
		btnPremium.setSelectedIcon(null);
		btnPremium.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		btnPremium.setForeground(new Color(255, 255, 255));
		btnPremium.setBackground(new Color(138, 43, 226));
		GridBagConstraints gbc_btnPremium = new GridBagConstraints();
		gbc_btnPremium.insets = new Insets(0, 0, 5, 5);
		gbc_btnPremium.gridx = 6;
		gbc_btnPremium.gridy = 7;
		frmProfile.getContentPane().add(btnPremium, gbc_btnPremium);
		
		JLabel lblMail = new JLabel("Mail: " + AppVideo.getInstance().getActualUser().getMail());
		lblMail.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		GridBagConstraints gbc_lblMail = new GridBagConstraints();
		gbc_lblMail.insets = new Insets(0, 0, 5, 5);
		gbc_lblMail.gridx = 2;
		gbc_lblMail.gridy = 8;
		frmProfile.getContentPane().add(lblMail, gbc_lblMail);
		
		JLabel lblUsername = new JLabel("Username: " + AppVideo.getInstance().getActualUser().getUsername());
		lblUsername.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 2;
		gbc_lblUsername.gridy = 9;
		frmProfile.getContentPane().add(lblUsername, gbc_lblUsername);
		
		JLabel lblFilter = new JLabel("Filter: " + AppVideo.getInstance().getActualUser().getFilter());
		lblFilter.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		GridBagConstraints gbc_lblFilter = new GridBagConstraints();
		gbc_lblFilter.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilter.gridx = 6;
		gbc_lblFilter.gridy = 9;
		frmProfile.getContentPane().add(lblFilter, gbc_lblFilter);
		
		JButton btnChangePassword = new JButton("Change password");
		btnChangePassword.setIcon(new ImageIcon(ProfileWindow.class.getResource("/images/changePasswordIcon.png")));
		btnChangePassword.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		btnChangePassword.setForeground(new Color(255, 255, 255));
		btnChangePassword.setBackground(new Color(138, 43, 226));
		GridBagConstraints gbc_btnChangePassword = new GridBagConstraints();
		gbc_btnChangePassword.insets = new Insets(0, 0, 5, 5);
		gbc_btnChangePassword.gridx = 2;
		gbc_btnChangePassword.gridy = 10;
		frmProfile.getContentPane().add(btnChangePassword, gbc_btnChangePassword);
		
		JButton btnGeneratePDF = new JButton("Generate PDF");
		btnGeneratePDF.setIcon(new ImageIcon(ProfileWindow.class.getResource("/images/pdfIcon.png")));
		btnGeneratePDF.setSelectedIcon(null);
		btnGeneratePDF.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		btnGeneratePDF.setForeground(new Color(255, 255, 255));
		btnGeneratePDF.setBackground(new Color(138, 43, 226));
		GridBagConstraints gbc_btnGeneratePDF = new GridBagConstraints();
		gbc_btnGeneratePDF.insets = new Insets(0, 0, 5, 5);
		gbc_btnGeneratePDF.gridx = 6;
		gbc_btnGeneratePDF.gridy = 10;
		frmProfile.getContentPane().add(btnGeneratePDF, gbc_btnGeneratePDF);
	}
	
	private void goToAppVideoWindow() {
		AppVideoWindow appVideo = new AppVideoWindow();
		frmProfile.dispose();
		appVideo.showWindow();
	}

}
