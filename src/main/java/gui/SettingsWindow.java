package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		frmSettings.setMinimumSize(new Dimension(640, 480));
		frmSettings.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginWindow.class.getResource("/images/multimediavideoplayer_128px.png")));
		frmSettings.setBounds(100, 100, 640, 480);
		frmSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSettings.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				goToAppVideoWindow();
			}
		});
	}
	
	private void goToAppVideoWindow() {
		AppVideoWindow appVideo = new AppVideoWindow();
		frmSettings.dispose();
		appVideo.showWindow();
	}

}
