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
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		mntmEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goToProfileWindow();
			}
		});
		mnProfile.add(mntmEdit);
		
		JMenu mnFilters = new JMenu("Filters");
		menuBar.add(mnFilters);
		
		JMenu mnSelect = new JMenu("Select");
		mnFilters.add(mnSelect);
		
		JMenuItem mntmMinors = new JMenuItem("Minors");
		mntmMinors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AppVideo.getInstance().selectFilter(new MinorsFilter());
				System.out.println("Filtro minors");
			}
		});
		mnSelect.add(mntmMinors);
		
		JMenuItem mntmImpopulars = new JMenuItem("Impopulars");
		mntmImpopulars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AppVideo.getInstance().selectFilter(new ImpopularsFilter());
				System.out.println("Filtro impopulars");
			}
		});
		mnSelect.add(mntmImpopulars);
		
		JMenuItem mntmMyLists = new JMenuItem("My lists");
		mntmMyLists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AppVideo.getInstance().selectFilter(new MyListsFilter());
				System.out.println("Filtro my lists");
			}
		});
		mnSelect.add(mntmMyLists);
		
		JMenuItem mntmRemoveFilter = new JMenuItem("Remove filter");
		mntmRemoveFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AppVideo.getInstance().selectFilter(new NoFilter());
				System.out.println("Filtro eliminado");
			}
		});
		mnFilters.add(mntmRemoveFilter);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		
		JMenuItem mntmMySettings = new JMenuItem("My settings");
		mntmMySettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goToSettingsWindow();
			}
		});
	
		mnSettings.add(mntmMySettings);
		mnSettings.add(mntmHelp);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
