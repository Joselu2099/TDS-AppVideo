package gui;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JMenu;

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
		frmAppVideo.setMinimumSize(new Dimension(1280, 720));
		frmAppVideo.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginWindow.class.getResource("/images/multimediavideoplayer_128px.png")));
		frmAppVideo.setBounds(100, 100, 1280, 720);
		frmAppVideo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAppVideo.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				goToLoginWindow();
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frmAppVideo.setJMenuBar(menuBar);
		
		JMenu mnProfile = new JMenu("Profile");
		menuBar.add(mnProfile);
		
		JMenu mnFilters = new JMenu("Filters");
		menuBar.add(mnFilters);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmAppVideo.getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		JPanel home = new JPanel();
		tabbedPane.addTab("Home", null, home, null);
		
		JPanel explore = new JPanel();
		tabbedPane.addTab("Explore", null, explore, null);
		
		JPanel recent = new JPanel();
		tabbedPane.addTab("Recent", null, recent, null);
		
		JPanel playlists = new JPanel();
		tabbedPane.addTab("Playlists", null, playlists, null);
	}

	/**
	 * Vuelve a la ventana de Login
	 */
	private void goToLoginWindow() {
		LoginWindow login = new LoginWindow();
		frmAppVideo.dispose();
		login.showWindow();
	}

}
