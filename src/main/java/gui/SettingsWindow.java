package gui;

import controller.AppVideo;
import gui.Util.UIUtils;
import pulsador.Luz;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class SettingsWindow {

    private static final String NIGHTMODEACTIVATED = "Night mode activated";
    private static final String NIGHTMODEDISABLED = "Light mode disabled";

    private JFrame frmSettings;
    private JLabel lblLightNightMode;
    private JButton btnNightMode;
    private JButton btnBack;

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
        frmSettings.setMinimumSize(new Dimension(740, 580));
        frmSettings.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginWindow.class.getResource("/images/multimediavideoplayer_128px.png")));
        frmSettings.setBounds(100, 100, 740, 580);
        frmSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{50, 0, 0, 0, 20, 50, 20, 0, 0, 0, 50, 0};
        gridBagLayout.rowHeights = new int[]{50, 0, 0, 0, 40, 0, 0, 50, 0, 0, 0, 50, -8, 40, 50, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 2.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, 1.0, 0.0, Double.MIN_VALUE};
        frmSettings.getContentPane().setLayout(gridBagLayout);
        frmSettings.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                goToAppVideoWindow();
            }
        });

        JLabel lblAppVideoIcon = new JLabel("");
        lblAppVideoIcon.setIcon(new ImageIcon(SettingsWindow.class.getResource("/images/multimediavideoplayer_128px.png")));
        GridBagConstraints gbc_lblAppVideoIcon = new GridBagConstraints();
        gbc_lblAppVideoIcon.insets = new Insets(0, 0, 5, 5);
        gbc_lblAppVideoIcon.gridx = 5;
        gbc_lblAppVideoIcon.gridy = 3;
        frmSettings.getContentPane().add(lblAppVideoIcon, gbc_lblAppVideoIcon);

        lblLightNightMode = new JLabel(AppVideo.getInstance().getActualUser().isNightMode() ? "Night mode" : "Light mode");
        lblLightNightMode.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
        GridBagConstraints gbc_lblLightNightMode = new GridBagConstraints();
        gbc_lblLightNightMode.insets = new Insets(0, 0, 5, 5);
        gbc_lblLightNightMode.gridx = 2;
        gbc_lblLightNightMode.gridy = 6;
        frmSettings.getContentPane().add(lblLightNightMode, gbc_lblLightNightMode);

        // TODO
//        Luz luz = new Luz();

        btnNightMode = new JButton("");
        btnNightMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setNightMode();
            }
        });
        btnNightMode.setSelectedIcon(null);
        btnNightMode.setBorderPainted(false);
        btnNightMode.setBorder(new MatteBorder(1, 1, 1, 1, UIUtils.getFocusedBorder()));
        btnNightMode.setIcon(new ImageIcon(SettingsWindow.class.getResource("/images/nightModeIcon.png")));
        GridBagConstraints gbc_btnNightMode = new GridBagConstraints();
        gbc_btnNightMode.insets = new Insets(0, 0, 5, 5);
        gbc_btnNightMode.gridx = 2;
        gbc_btnNightMode.gridy = 7;
        frmSettings.getContentPane().add(btnNightMode, gbc_btnNightMode);

        btnBack = new JButton("");
        frmSettings.getRootPane().setDefaultButton(btnBack);
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goToAppVideoWindow();
            }
        });

        JButton btnNewButton = new JButton("New button");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 8;
        gbc_btnNewButton.gridy = 7;
        frmSettings.getContentPane().add(btnNewButton, gbc_btnNewButton);
        btnBack.setBorderPainted(false);
        btnBack.setBorder(new MatteBorder(1, 1, 1, 1, UIUtils.getFocusedBorder()));
        btnBack.setIcon(new ImageIcon(ProfileWindow.class.getResource("/images/backIcon.png")));
        GridBagConstraints gbc_btnBack = new GridBagConstraints();
        gbc_btnBack.insets = new Insets(0, 0, 5, 5);
        gbc_btnBack.gridx = 1;
        gbc_btnBack.gridy = 13;
        frmSettings.getContentPane().add(btnBack, gbc_btnBack);

    }

    private void setNightMode(){
        if (!AppVideo.getInstance().getActualUser().isNightMode()) {
            lblLightNightMode.setText(NIGHTMODEACTIVATED);
            AppVideo.getInstance().setNightMode(true);
        } else if (AppVideo.getInstance().getActualUser().isNightMode()) {
            lblLightNightMode.setText(NIGHTMODEDISABLED);
            AppVideo.getInstance().setNightMode(false);
        }
        SwingUtilities.updateComponentTreeUI(frmSettings);
    }

    private void goToAppVideoWindow() {
        AppVideoWindow appVideo = new AppVideoWindow();
        frmSettings.dispose();
        appVideo.showWindow();
    }

}
