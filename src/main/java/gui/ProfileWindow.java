package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

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
		frmProfile.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				goToAppVideoWindow();
			}
		});
	}
	
	private void goToAppVideoWindow() {
		AppVideoWindow appVideo = new AppVideoWindow();
		frmProfile.dispose();
		appVideo.showWindow();
	}

}
