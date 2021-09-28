package gui;

import javax.swing.JFrame;

public class AppVideoWindow {

	private JFrame frmAppVideo;

	/**
	 * Create the application.
	 */
	public AppVideoWindow() {
		initialize();
	}

	/**
	 * Show actual window
	 */
	public void showWindow() {
		frmAppVideo.setLocationRelativeTo(null);
		frmAppVideo.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAppVideo = new JFrame();
		frmAppVideo.setBounds(100, 100, 450, 300);
		frmAppVideo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}