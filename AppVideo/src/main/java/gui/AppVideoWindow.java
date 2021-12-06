package gui;

import controller.AppVideo;
import gui.Util.UIUtils;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;


public class AppVideoWindow {

    private static AppVideoWindow activeInstance;
    private JFrame frmAppVideo;
    private HomePanel homePanel;
    private RecentPanel recentPanel;
    private MyPlaylistPanel myPlaylistPanel;
    private CreatePlaylistPanel createPlaylistPanel;
    private PopularPanel popularPanel;
    private JMenuItem mntmNightMode;

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
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmAppVideo = new JFrame();
        frmAppVideo.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginWindow.class.getResource("/images/multimediavideoplayer_128px.png")));
        frmAppVideo.setBounds(100, 100, 1280, 720);
        frmAppVideo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmAppVideo.setMinimumSize(new Dimension(900, 600));
        frmAppVideo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {goToLoginWindow();
            }
        });

        JMenuBar menuBar = new JMenuBar();
        frmAppVideo.setJMenuBar(menuBar);

        JMenu mnProfile = new JMenu("Perfil");
        menuBar.add(mnProfile);

        JMenuItem mntmEdit = new JMenuItem("Editar");
        mntmEdit.addActionListener(arg0 -> goToProfileWindow());
        mnProfile.add(mntmEdit);

        ButtonGroup bg = new ButtonGroup();

        JMenu mnFilters = new JMenu("Filtros");
        menuBar.add(mnFilters);
        mnFilters.setEnabled(AppVideo.getInstance().isCurrentUserPremium());

        JMenuItem mntmRemoveFilter = new JRadioButtonMenuItem("Sin filtro");
        mntmRemoveFilter.addActionListener(arg0 -> AppVideo.getInstance().applyFilter(new NoFilter()));
        mnFilters.add(mntmRemoveFilter);
        if (AppVideo.getInstance().getCurrentUser().getFilter() instanceof NoFilter) mntmRemoveFilter.setSelected(true);
        bg.add(mntmRemoveFilter);

        JMenuItem mntmMinors = new JRadioButtonMenuItem("Menores");
        mntmMinors.addActionListener(arg0 -> AppVideo.getInstance().applyFilter(new MinorsFilter()));
        mnFilters.add(mntmMinors);
        if (AppVideo.getInstance().getCurrentUser().getFilter() instanceof MinorsFilter) mntmMinors.setSelected(true);
        bg.add(mntmMinors);

        JMenuItem mntmImpopulars = new JRadioButtonMenuItem("Impopulares");
        mntmImpopulars.addActionListener(arg0 -> AppVideo.getInstance().applyFilter(new ImpopularsFilter()));
        mnFilters.add(mntmImpopulars);
        if (AppVideo.getInstance().getCurrentUser().getFilter() instanceof ImpopularsFilter) mntmImpopulars.setSelected(true);
        bg.add(mntmImpopulars);

        JMenuItem mntmLongName = new JRadioButtonMenuItem("Nombre largos");
        mntmLongName.addActionListener(arg0 -> AppVideo.getInstance().applyFilter(new LongNameFilter()));
        if (AppVideo.getInstance().getCurrentUser().getFilter() instanceof LongNameFilter) mntmLongName.setSelected(true);
        mnFilters.add(mntmLongName);
        bg.add(mntmLongName);

        JMenuItem mntmMyLists = new JRadioButtonMenuItem("Mis listas");
        mntmMyLists.addActionListener(arg0 -> AppVideo.getInstance().applyFilter(new MyListsFilter()));
        mnFilters.add(mntmMyLists);
        if (AppVideo.getInstance().getCurrentUser().getFilter() instanceof MyListsFilter) mntmMyLists.setSelected(true);
        bg.add(mntmMyLists);


        JMenu mnSettings = new JMenu("Settings");
        menuBar.add(mnSettings);

        mntmNightMode = new JMenuItem("");
        mntmNightMode.setHorizontalAlignment(SwingConstants.CENTER);
        mntmNightMode.setIcon(new ImageIcon(Objects.requireNonNull(AppVideoWindow.class.getResource(AppVideo.getInstance().getCurrentUser().isNightMode() ? "/images/lightModeIcon.png":"/images/nightModeIcon.png" ))));
        mntmNightMode.addActionListener(arg0 -> setNightMode());
        mnSettings.add(mntmNightMode);

        JMenuItem mntmHelp = new JMenuItem("Ayuda");
        mntmHelp.addActionListener(arg0 -> JOptionPane.showMessageDialog(frmAppVideo, "Cualquier problema o duda contactar a joseluis.sanchezc@um.es", "Ayuda", JOptionPane.PLAIN_MESSAGE));
        mnSettings.add(mntmHelp);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(arg0 -> goToLoginWindow());
        mnSettings.add(mntmExit);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frmAppVideo.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        //JPanel home = new JPanel();
        homePanel = new HomePanel(frmAppVideo);
        tabbedPane.addTab("Home", null, homePanel, null);

        recentPanel = new RecentPanel(frmAppVideo);
        tabbedPane.addTab("Recientes", null, recentPanel, null);

        myPlaylistPanel = new MyPlaylistPanel(frmAppVideo);
        tabbedPane.addTab("Mis Playlists", null,myPlaylistPanel, null);

        createPlaylistPanel = new CreatePlaylistPanel(frmAppVideo);
        tabbedPane.addTab("Crear Playlists", null, createPlaylistPanel, null);

        popularPanel = new PopularPanel(frmAppVideo);
        int popularTabIndex = tabbedPane.getTabCount();
        tabbedPane.addTab("Tendencias",null,popularPanel,null);
        tabbedPane.setEnabledAt(popularTabIndex,AppVideo.getInstance().isCurrentUserPremium());


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

    public JFrame getFrmAppVideo() {
        return frmAppVideo;
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
     * Va a la ventana del perfil
     */
    private void goToProfileWindow() {
        ProfileWindow profile = new ProfileWindow();
        profile.showWindow(frmAppVideo);
    }
    
    private void setNightMode(){
        if (!AppVideo.getInstance().getCurrentUser().isNightMode()) {
            AppVideo.getInstance().setNightMode(true);
            UIUtils.setNightMode(true,frmAppVideo);
        } else if (AppVideo.getInstance().getCurrentUser().isNightMode()) {
            AppVideo.getInstance().setNightMode(false);
            UIUtils.setNightMode(false,frmAppVideo);
        }
        mntmNightMode.setIcon(new ImageIcon(Objects.requireNonNull(AppVideoWindow.class.getResource(AppVideo.getInstance().getCurrentUser().isNightMode() ? "/images/lightModeIcon.png":"/images/nightModeIcon.png" ))));
    }
}
