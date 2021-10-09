package gui;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import controller.AppVideo;
import model.ImpopularsFilter;
import model.MinorsFilter;
import model.MyListsFilter;
import model.NoFilter;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		mntmEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToProfileWindow();
			}
		});
		mnProfile.add(mntmEdit);
		
		JMenu mnFilters = new JMenu("Filters");
		menuBar.add(mnFilters);
		
		JMenu mnSelect = new JMenu("Select");
		mnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		mnFilters.add(mnSelect);
		
		JMenuItem mntmMinors = new JMenuItem("Minors");
		mntmMinors.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AppVideo.getInstance().selectFilter(new MinorsFilter());;
			}
		});
		mnSelect.add(mntmMinors);
		
		JMenuItem mntmImpopulars = new JMenuItem("Impopulars");
		mntmImpopulars.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AppVideo.getInstance().selectFilter(new ImpopularsFilter());;
			}
		});
		mnSelect.add(mntmImpopulars);
		
		JMenuItem mntmMyLists = new JMenuItem("My lists");
		mntmMyLists.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AppVideo.getInstance().selectFilter(new MyListsFilter());;
			}
		});
		mnSelect.add(mntmMyLists);
		
		JMenuItem mntmRemoveFilter = new JMenuItem("Remove filter");
		mntmRemoveFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AppVideo.getInstance().selectFilter(new NoFilter());;
			}
		});
		mnFilters.add(mntmRemoveFilter);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		JMenuItem mntmMySettings = new JMenuItem("My settings");
		mntmMySettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToSettingsWindow();
			}
		});
		mnSettings.add(mntmMySettings);
		mnSettings.add(mntmHelp);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToLoginWindow();
			}
		});
		mnSettings.add(mntmExit);
		
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
	
	/**
	 * Va a la ventana de ajustes
	 */
	private void goToSettingsWindow() {
		SettingsWindow settings = new SettingsWindow();
		frmAppVideo.dispose();
		settings.showWindow();
	}
	
	/**
	 * Va a la ventana del perfil
	 */
	private void goToProfileWindow() {
		ProfileWindow profile = new ProfileWindow();
		frmAppVideo.dispose();
		profile.showWindow();
	}

}
