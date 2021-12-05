package gui;

import controller.AppVideo;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AppVideoWindow {

    private static AppVideoWindow activeInstance;
    private JFrame frmAppVideo;
    private HomePanel homePanel;
    private RecentPanel recentPanel;
    private MyPlaylistPanel myPlaylistPanel;
    private CreatePlaylistPanel createPlaylistPanel;

    /**
     * Create the application.
     */

    public AppVideoWindow() {
        initialize();
    }

    public static void setActiveInstance(AppVideoWindow appVideoWindow){
        AppVideoWindow.activeInstance = appVideoWindow;
    }

    public static AppVideoWindow getActiveInstance(){
        return AppVideoWindow.activeInstance;
    }

    /**
     * Show actual window
     */
    public void showWindow() {
        frmAppVideo.setLocationRelativeTo(null);
        frmAppVideo.setVisible(true);
    }

    /**
     * Hide actual window
     */
    public void hideWindow() {
        frmAppVideo.setVisible(false);
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
            public void windowClosing(WindowEvent e) {goToLoginWindow();
            }
        });

        JMenuBar menuBar = new JMenuBar();
        frmAppVideo.setJMenuBar(menuBar);

        JMenu mnProfile = new JMenu("Profile");
        menuBar.add(mnProfile);

        JMenuItem mntmEdit = new JMenuItem("Edit");
        mntmEdit.addActionListener(arg0 -> goToProfileWindow());
        mnProfile.add(mntmEdit);

        JMenu mnFilters = new JMenu("Filters");
        menuBar.add(mnFilters);

        JMenu mnSelect = new JMenu("Select");
        mnFilters.add(mnSelect);

        JMenuItem mntmMinors = new JMenuItem("Minors");
        mntmMinors.addActionListener(arg0 -> {
			AppVideo.getInstance().applyFilter(new MinorsFilter());
			System.out.println("Filtro minors");
		});
        mnSelect.add(mntmMinors);

        JMenuItem mntmImpopulars = new JMenuItem("Impopulars");
        mntmImpopulars.addActionListener(arg0 -> {
			AppVideo.getInstance().applyFilter(new ImpopularsFilter());
			System.out.println("Filtro impopulars");
		});
        mnSelect.add(mntmImpopulars);

        JMenuItem mntmMyLists = new JMenuItem("My lists");
        mntmMyLists.addActionListener(arg0 -> {
			AppVideo.getInstance().applyFilter(new MyListsFilter());
			System.out.println("Filtro my lists");
		});
        mnSelect.add(mntmMyLists);

        JMenuItem mntmRemoveFilter = new JMenuItem("Remove filter");
        mntmRemoveFilter.addActionListener(arg0 -> {
			AppVideo.getInstance().applyFilter(new NoFilter());
			System.out.println("Filtro eliminado");
		});
        mnFilters.add(mntmRemoveFilter);

        JMenu mnSettings = new JMenu("Settings");
        menuBar.add(mnSettings);

        JMenuItem mntmHelp = new JMenuItem("Help");
        mntmHelp.addActionListener(arg0 -> {

		});

        JMenuItem mntmMySettings = new JMenuItem("My settings");
        mntmMySettings.addActionListener(arg0 -> goToSettingsWindow());

        mnSettings.add(mntmMySettings);
        mnSettings.add(mntmHelp);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(arg0 -> goToLoginWindow());
        mnSettings.add(mntmExit);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frmAppVideo.getContentPane().add(tabbedPane, BorderLayout.NORTH);

        //JPanel home = new JPanel();
        homePanel = new HomePanel(frmAppVideo);
//        homePanel.showVideoPreview(VideoRepository.getInstance().getFilteredVideos());
        tabbedPane.addTab("Home", null, homePanel, null);

        recentPanel = new RecentPanel(frmAppVideo);
        tabbedPane.addTab("Recent", null, recentPanel, null);

        myPlaylistPanel = new MyPlaylistPanel(frmAppVideo);
        tabbedPane.addTab("My Playlists", null, myPlaylistPanel, null);

        createPlaylistPanel = new CreatePlaylistPanel(frmAppVideo);
        tabbedPane.addTab("Create Playlists", null, createPlaylistPanel, null);
    }

    public HomePanel getHomePanel() {
        return homePanel;
    }

    public RecentPanel getRecentPanel() {
        return recentPanel;
    }

    public MyPlaylistPanel getMyPlaylistPanel() {
        return myPlaylistPanel;
    }

    public CreatePlaylistPanel getCreatePlaylistPanel() {
        return createPlaylistPanel;
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
