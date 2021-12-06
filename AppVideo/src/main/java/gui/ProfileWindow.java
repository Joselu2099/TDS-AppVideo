package gui;

import controller.AppVideo;
import gui.Util.UIUtils;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Objects;

public class ProfileWindow {

    private JFrame frmProfile;
    private JLabel lblName;
    private JLabel lblSurname;
    private JLabel lblMail;
    private JLabel lblUsername;
    private JLabel lblPremium;
    private JLabel lblFilter;
    private JButton btnBack;


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
        frmProfile.setMinimumSize(new Dimension(900, 680));
        frmProfile.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginWindow.class.getResource("/images/multimediavideoplayer_128px.png")));
        frmProfile.setBounds(100, 100, 900, 680);
        frmProfile.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{50, 0, 0, 20, 50, 20, 0, 0, 50, 0};
        gridBagLayout.rowHeights = new int[]{50, 0, 0, 0, 40, 0, 0, 29, 0, 0, 0, 30, 0, 0, 50, 40, 50, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 2.0, 0.0, 1.0, 0.0, 1.0, 0.0, 2.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 2.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        frmProfile.getContentPane().setLayout(gridBagLayout);
        frmProfile.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                goToAppVideoWindow();
            }
        });

        JLabel lblAppVideoIcon = new JLabel("");
        lblAppVideoIcon.setIcon(new ImageIcon(Objects.requireNonNull(SettingsWindow.class.getResource("/images/multimediavideoplayer_128px.png"))));
        GridBagConstraints gbc_lblAppVideoIcon = new GridBagConstraints();
        gbc_lblAppVideoIcon.insets = new Insets(0, 0, 5, 5);
        gbc_lblAppVideoIcon.gridx = 4;
        gbc_lblAppVideoIcon.gridy = 3;
        frmProfile.getContentPane().add(lblAppVideoIcon, gbc_lblAppVideoIcon);

        lblName = new JLabel("Nombre: " + AppVideo.getInstance().getCurrentUser().getName());
        lblName.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
        GridBagConstraints gbc_lblName = new GridBagConstraints();
        gbc_lblName.insets = new Insets(0, 0, 5, 5);
        gbc_lblName.gridx = 2;
        gbc_lblName.gridy = 6;
        frmProfile.getContentPane().add(lblName, gbc_lblName);

        lblSurname = new JLabel("Apellidos: " + AppVideo.getInstance().getCurrentUser().getSurname());
        lblSurname.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
        GridBagConstraints gbc_lblSurname = new GridBagConstraints();
        gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
        gbc_lblSurname.gridx = 2;
        gbc_lblSurname.gridy = 7;
        frmProfile.getContentPane().add(lblSurname, gbc_lblSurname);

        lblMail = new JLabel("Mail: " + AppVideo.getInstance().getCurrentUser().getMail());
        lblMail.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
        GridBagConstraints gbc_lblMail = new GridBagConstraints();
        gbc_lblMail.insets = new Insets(0, 0, 5, 5);
        gbc_lblMail.gridx = 2;
        gbc_lblMail.gridy = 8;
        frmProfile.getContentPane().add(lblMail, gbc_lblMail);

        lblUsername = new JLabel("Usuario: " + AppVideo.getInstance().getCurrentUser().getUsername());
        lblUsername.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
        GridBagConstraints gbc_lblUsername = new GridBagConstraints();
        gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
        gbc_lblUsername.gridx = 2;
        gbc_lblUsername.gridy = 10;
        frmProfile.getContentPane().add(lblUsername, gbc_lblUsername);

        lblFilter = new JLabel("Filtro: " + AppVideo.getInstance().getCurrentUser().getFilter().getClass().getSimpleName());
        lblFilter.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
        GridBagConstraints gbc_lblFilter = new GridBagConstraints();
        gbc_lblFilter.insets = new Insets(0, 0, 5, 5);
        gbc_lblFilter.gridx = 6;
        gbc_lblFilter.gridy = 6;
        frmProfile.getContentPane().add(lblFilter, gbc_lblFilter);

        lblPremium = new JLabel("Premium: " + AppVideo.getInstance().getCurrentUser().getPremium());
        lblPremium.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
        GridBagConstraints gbc_lblPremium = new GridBagConstraints();
        gbc_lblPremium.insets = new Insets(0, 0, 5, 5);
        gbc_lblPremium.gridx = 6;
        gbc_lblPremium.gridy = 8;
        frmProfile.getContentPane().add(lblPremium, gbc_lblPremium);

        JButton btnChangeMail = new JButton("Cambiar Mail");
        btnChangeMail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeUserMail();
            }
        });
        btnChangeMail.setIcon(new ImageIcon(Objects.requireNonNull(ProfileWindow.class.getResource("/images/changeMailIcon.png"))));
        btnChangeMail.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
        GridBagConstraints gbc_btnChangeMail = new GridBagConstraints();
        gbc_btnChangeMail.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnChangeMail.insets = new Insets(0, 0, 5, 5);
        gbc_btnChangeMail.gridx = 2;
        gbc_btnChangeMail.gridy = 9;
        frmProfile.getContentPane().add(btnChangeMail, gbc_btnChangeMail);

        JButton btnGeneratePDF = new JButton("Generar PDF");

        JButton btnQuitPremium = new JButton("Abandonar premium");
        JButton btnPremium = new JButton("Hazte premium");

        btnPremium.setEnabled(!AppVideo.getInstance().isCurrentUserPremium());
        btnQuitPremium.setEnabled(AppVideo.getInstance().isCurrentUserPremium());
        btnGeneratePDF.setEnabled(AppVideo.getInstance().isCurrentUserPremium());
        btnPremium.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                becomeUserPremium();

                btnPremium.setEnabled(!AppVideo.getInstance().isCurrentUserPremium());
                btnQuitPremium.setEnabled(AppVideo.getInstance().isCurrentUserPremium());
                btnGeneratePDF.setEnabled(AppVideo.getInstance().isCurrentUserPremium());
            }
        });
        btnPremium.setIcon(new ImageIcon(Objects.requireNonNull(ProfileWindow.class.getResource("/images/premiumIcon.png"))));
        btnPremium.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
        GridBagConstraints gbc_btnPremium = new GridBagConstraints();
        gbc_btnPremium.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnPremium.insets = new Insets(0, 0, 5, 5);
        gbc_btnPremium.gridx = 6;
        gbc_btnPremium.gridy = 9;
        frmProfile.getContentPane().add(btnPremium, gbc_btnPremium);

        JButton btnChangeUsername = new JButton("Cambiar username");
        btnChangeUsername.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeUserUsername();
            }
        });
        btnChangeUsername.setIcon(new ImageIcon(Objects.requireNonNull(ProfileWindow.class.getResource("/images/UserIcon.png"))));
        btnChangeUsername.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
        GridBagConstraints gbc_btnChangeUsername = new GridBagConstraints();
        gbc_btnChangeUsername.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnChangeUsername.insets = new Insets(0, 0, 5, 5);
        gbc_btnChangeUsername.gridx = 2;
        gbc_btnChangeUsername.gridy = 11;
        frmProfile.getContentPane().add(btnChangeUsername, gbc_btnChangeUsername);

        JButton btnChangePassword = new JButton("Cambiar password");
        btnChangePassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeUserPassword();
            }
        });
        btnChangePassword.setIcon(new ImageIcon(Objects.requireNonNull(ProfileWindow.class.getResource("/images/changePasswordIcon.png"))));
        btnChangePassword.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
        GridBagConstraints gbc_btnChangePassword = new GridBagConstraints();
        gbc_btnChangePassword.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnChangePassword.insets = new Insets(0, 0, 5, 5);
        gbc_btnChangePassword.gridx = 2;
        gbc_btnChangePassword.gridy = 12;
        frmProfile.getContentPane().add(btnChangePassword, gbc_btnChangePassword);

        btnQuitPremium.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                quitUserPremium();
                lblFilter.setText(AppVideo.getInstance().getCurrentUser().getFilter().getClass().getSimpleName());
                btnPremium.setEnabled(!AppVideo.getInstance().isCurrentUserPremium());
                btnQuitPremium.setEnabled(AppVideo.getInstance().isCurrentUserPremium());
                btnGeneratePDF.setEnabled(AppVideo.getInstance().isCurrentUserPremium());
            }
        });
        btnQuitPremium.setIcon(new ImageIcon(Objects.requireNonNull(ProfileWindow.class.getResource("/images/premiumIcon.png"))));
        btnQuitPremium.setSelectedIcon(null);
        btnQuitPremium.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
        GridBagConstraints gbc_btnQuitPremium = new GridBagConstraints();
        gbc_btnQuitPremium.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnQuitPremium.insets = new Insets(0, 0, 5, 5);
        gbc_btnQuitPremium.gridx = 6;
        gbc_btnQuitPremium.gridy = 11;
        frmProfile.getContentPane().add(btnQuitPremium, gbc_btnQuitPremium);

        btnGeneratePDF.setEnabled(AppVideo.getInstance().isCurrentUserPremium());
        btnGeneratePDF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (AppVideo.getInstance().getCurrentUser().isPremium()){
                    EventQueue.invokeLater(() -> {
                        JFileChooser jFileChooser = new JFileChooser(".");
                        int i = jFileChooser.showSaveDialog(frmProfile);
                        if (i == JFileChooser.APPROVE_OPTION){
                            if (!jFileChooser.getSelectedFile().isDirectory()) {
                                try {
                                    AppVideo.getInstance().generatePDF(jFileChooser.getSelectedFile().getAbsolutePath());
                                } catch (FileNotFoundException | MalformedURLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });
                }
                else
                    JOptionPane.showMessageDialog(frmProfile.getContentPane(), "Esta funcion es solo para usuarios premium", "Generar PDF", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnGeneratePDF.setIcon(new ImageIcon(Objects.requireNonNull(ProfileWindow.class.getResource("/images/pdfIcon.png"))));
        btnGeneratePDF.setSelectedIcon(null);
        btnGeneratePDF.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
        GridBagConstraints gbc_btnGeneratePDF = new GridBagConstraints();
        gbc_btnGeneratePDF.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnGeneratePDF.insets = new Insets(0, 0, 5, 5);
        gbc_btnGeneratePDF.gridx = 6;
        gbc_btnGeneratePDF.gridy = 12;
        frmProfile.getContentPane().add(btnGeneratePDF, gbc_btnGeneratePDF);

        btnBack = new JButton("");
        frmProfile.getRootPane().setDefaultButton(btnBack);
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goToAppVideoWindow();
            }
        });
        btnBack.setBorderPainted(false);
        btnBack.setBorder(new MatteBorder(1, 1, 1, 1, UIUtils.getFocusedBorder()));
        btnBack.setIcon(new ImageIcon(Objects.requireNonNull(ProfileWindow.class.getResource("/images/backIcon.png"))));
        GridBagConstraints gbc_btnBack = new GridBagConstraints();
        gbc_btnBack.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnBack.insets = new Insets(0, 0, 5, 5);
        gbc_btnBack.gridx = 1;
        gbc_btnBack.gridy = 15;
        frmProfile.getContentPane().add(btnBack, gbc_btnBack);
    }

    private void changeUserMail() {
        while (true) {

            String newMail = (String) JOptionPane.showInputDialog(frmProfile.getContentPane(), "Intruduce un nuevo mail : ",
                    "Cambiar mail", JOptionPane.PLAIN_MESSAGE, null, null, "");

            if (newMail.trim().contains("@")) {
                AppVideo.getInstance().changeMail(newMail);
                lblMail.setText("Mail: " + AppVideo.getInstance().getCurrentUser().getMail());
                break;
            } else
                JOptionPane.showMessageDialog(frmProfile.getContentPane(), "El formato del Mail es incorrecto, ejemplo de uso: usuario@gmail.com", "Cambiar mail", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeUserPassword() {
        while (true) {
            String actualPassword = (String) JOptionPane.showInputDialog(frmProfile.getContentPane(),
                    "Intruduce tu contraseña actual : ", "Cambiar contraseña",
                    JOptionPane.PLAIN_MESSAGE, null, null, "");

            if (actualPassword == null) break;

            if (AppVideo.getInstance().getCurrentUser().getPassword().equals(AppVideo.getInstance().encodePassword(actualPassword))) {

                String newPassword = (String) JOptionPane.showInputDialog(frmProfile.getContentPane(), "Introduce la nueva contraseña : ",
                        "Cambiar contraseña", JOptionPane.PLAIN_MESSAGE, null, null, "contraseña");

                if (newPassword == null) break;

                if (newPassword.length() < AppVideo.MIN_PASSWORD_LENGTH)
                    JOptionPane.showMessageDialog(frmProfile.getContentPane(),
                            "La contraseña debe tener al menos " + AppVideo.MIN_PASSWORD_LENGTH + " caracteres", "Cambiar contraseña", JOptionPane.ERROR_MESSAGE);
                else {
                    AppVideo.getInstance().changePassword(newPassword);
                    break;
                }

            } else {
                JOptionPane.showMessageDialog(frmProfile.getContentPane(), "Contraseña incorrecta, prueba otra vez", "Cambiar contraseña", JOptionPane.ERROR_MESSAGE);
            }


        }
    }

    private void changeUserUsername() {
        if (AppVideo.getInstance().getCurrentUser().isPremium()) {
            while (true) {

                String newUsername = (String) JOptionPane.showInputDialog(frmProfile.getContentPane(), "Intruduce un nuevo username : ",
                        "Change username", JOptionPane.PLAIN_MESSAGE, null, null, "");

                if (newUsername == null) break;

                if (!AppVideo.getInstance().isUserRegistered(newUsername)) {
                    AppVideo.getInstance().changeUsername(newUsername);
                    lblUsername.setText("Username: " + AppVideo.getInstance().getCurrentUser().getUsername());
                    break;
                } else
                    JOptionPane.showMessageDialog(frmProfile.getContentPane(), "El username " + newUsername + " ya esta en uso, prueba con otro \uD83D\uDE04", "Change username", JOptionPane.ERROR_MESSAGE);
            }
        } else
            JOptionPane.showMessageDialog(frmProfile.getContentPane(), "Esta funcion es solo para usuarios premium", "Change username", JOptionPane.ERROR_MESSAGE);
    }

    private void becomeUserPremium() {
        if (!AppVideo.getInstance().getCurrentUser().isPremium()) {
            AppVideo.getInstance().becomePremium();
            lblPremium.setText("Premium: " + AppVideo.getInstance().getCurrentUser().getPremium());
        } else JOptionPane.showMessageDialog(frmProfile.getContentPane(),
                "Ya eres premium \uD83D\uDE1D",
                "Become premium", JOptionPane.ERROR_MESSAGE);
    }

    private void quitUserPremium() {
        if (AppVideo.getInstance().getCurrentUser().isPremium()) {
            AppVideo.getInstance().quitPremium();
            lblPremium.setText("Premium: " + AppVideo.getInstance().getCurrentUser().getPremium());
        } else JOptionPane.showMessageDialog(frmProfile.getContentPane(),
                "No eres premium \uD83D\uDE1D",
                "Quit premium", JOptionPane.ERROR_MESSAGE);
    }

    private void goToAppVideoWindow() {
        AppVideoWindow appVideo = new AppVideoWindow();
        frmProfile.dispose();
        appVideo.showWindow();
    }

}
