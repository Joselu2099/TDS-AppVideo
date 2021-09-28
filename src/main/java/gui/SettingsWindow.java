package gui;

import javax.swing.JFrame;

public class SettingsWindow {

	private JFrame frmSettings;

	/**
	 * Create the application.
	 */
	public SettingsWindow() {
		initialize();
	}

	/**
	 * Show actual window
	 */
	public void showWindow() {
		frmSettings.setLocationRelativeTo(null);
		frmSettings.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSettings = new JFrame();
		frmSettings.setBounds(100, 100, 450, 300);
		frmSettings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
