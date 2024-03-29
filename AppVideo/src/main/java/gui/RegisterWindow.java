package gui;

import com.toedter.calendar.JDateChooser;
import controller.AppVideo;
import gui.Util.UIUtils;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.ZoneId;

public class RegisterWindow {

    private JFrame frmRegister;
    private JTextField textName;
    private JTextField textSurname;
    private JTextField textUsername;
    private JPasswordField passwordField;
    private JPasswordField repeatedPasswordField;
    private JTextField textMail;
    private JDateChooser dateOfBirth;
    private JLabel lblWarning;
    private JLabel lblWarningPassword;
    private JLabel lblWarningMail;
    private JLabel lblWarningDate;
    private JLabel lblWarningUser;

    /**
     * Create the application.
     */
    public RegisterWindow() {
        initialize();
    }

    /**
     * Show actual window
     */
    public void showWindow() {
        frmRegister.setLocationRelativeTo(null);
        frmRegister.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmRegister = new JFrame();
        frmRegister.setMinimumSize(new Dimension(900, 600));
        frmRegister.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginWindow.class.getResource("/images/multimediavideoplayer_128px.png")));
        frmRegister.setTitle("AppVideo");
        frmRegister.setBounds(100, 100, 900, 600);
        frmRegister.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmRegister.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                goToLoginWindow();
            }
        });
        frmRegister.getContentPane().setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPaneRegister = new JTabbedPane(JTabbedPane.TOP);
        tabbedPaneRegister.setBackground(Color.LIGHT_GRAY);
        frmRegister.getContentPane().add(tabbedPaneRegister);

        JPanel panelRegister = new JPanel();
        panelRegister.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
        panelRegister.setBorder(null);
        tabbedPaneRegister.addTab("Registrar", null, panelRegister, null);
        GridBagLayout gbl_panelRegister = new GridBagLayout();
        gbl_panelRegister.columnWidths = new int[]{60, 0, 0, 64, 131, 60, 0};
        gbl_panelRegister.rowHeights = new int[]{40, 0, 0, 0, 0, 0, 0, 60, 0, 40, 0, 0, 0, 0, 0, 60, 0};
        gbl_panelRegister.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_panelRegister.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        panelRegister.setLayout(gbl_panelRegister);

        lblWarning = new JLabel("");
        lblWarning.setForeground(Color.RED);
        lblWarning.setFont(new Font("Gill Sans MT", Font.BOLD, 13));
        GridBagConstraints gbc_lblWarning = new GridBagConstraints();
        gbc_lblWarning.anchor = GridBagConstraints.WEST;
        gbc_lblWarning.gridwidth = 4;
        gbc_lblWarning.insets = new Insets(0, 0, 5, 5);
        gbc_lblWarning.gridx = 1;
        gbc_lblWarning.gridy = 10;
        panelRegister.add(lblWarning, gbc_lblWarning);

        lblWarningPassword = new JLabel("");
        lblWarningPassword.setForeground(Color.RED);
        lblWarningPassword.setFont(new Font("Gill Sans MT", Font.BOLD, 13));
        GridBagConstraints gbc_lblWarningPassword = new GridBagConstraints();
        gbc_lblWarningPassword.anchor = GridBagConstraints.WEST;
        gbc_lblWarningPassword.gridwidth = 4;
        gbc_lblWarningPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblWarningPassword.gridx = 1;
        gbc_lblWarningPassword.gridy = 11;
        panelRegister.add(lblWarningPassword, gbc_lblWarningPassword);

        lblWarningMail = new JLabel("");
        lblWarningMail.setForeground(Color.RED);
        lblWarningMail.setFont(new Font("Gill Sans MT", Font.BOLD, 13));
        GridBagConstraints gbc_lblWarningMail = new GridBagConstraints();
        gbc_lblWarningMail.anchor = GridBagConstraints.WEST;
        gbc_lblWarningMail.gridwidth = 4;
        gbc_lblWarningMail.insets = new Insets(0, 0, 5, 5);
        gbc_lblWarningMail.gridx = 1;
        gbc_lblWarningMail.gridy = 12;
        panelRegister.add(lblWarningMail, gbc_lblWarningMail);

        lblWarningUser = new JLabel("");
        lblWarningUser.setForeground(Color.RED);
        lblWarningUser.setFont(new Font("Gill Sans MT", Font.BOLD, 13));
        GridBagConstraints gbc_lblWarningUser = new GridBagConstraints();
        gbc_lblWarningUser.anchor = GridBagConstraints.WEST;
        gbc_lblWarningUser.gridwidth = 4;
        gbc_lblWarningUser.insets = new Insets(0, 0, 5, 5);
        gbc_lblWarningUser.gridx = 1;
        gbc_lblWarningUser.gridy = 13;
        panelRegister.add(lblWarningUser, gbc_lblWarningUser);

        lblWarningDate = new JLabel("");
        lblWarningDate.setForeground(Color.RED);
        lblWarningDate.setFont(new Font("Gill Sans MT", Font.BOLD, 13));
        GridBagConstraints gbc_lblWarningDate = new GridBagConstraints();
        gbc_lblWarningDate.anchor = GridBagConstraints.WEST;
        gbc_lblWarningDate.gridwidth = 4;
        gbc_lblWarningDate.insets = new Insets(0, 0, 5, 5);
        gbc_lblWarningDate.gridx = 1;
        gbc_lblWarningDate.gridy = 14;
        panelRegister.add(lblWarningDate, gbc_lblWarningDate);

        JLabel lblName = new JLabel("Nombre: ");
        lblName.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_lblName = new GridBagConstraints();
        gbc_lblName.anchor = GridBagConstraints.EAST;
        gbc_lblName.insets = new Insets(0, 0, 5, 5);
        gbc_lblName.gridx = 1;
        gbc_lblName.gridy = 1;
        panelRegister.add(lblName, gbc_lblName);

        textName = new JTextField();
        textName.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_textName = new GridBagConstraints();
        gbc_textName.gridwidth = 3;
        gbc_textName.insets = new Insets(0, 0, 5, 5);
        gbc_textName.fill = GridBagConstraints.BOTH;
        gbc_textName.gridx = 2;
        gbc_textName.gridy = 1;
        panelRegister.add(textName, gbc_textName);
        textName.setColumns(10);

        JLabel lblSurname = new JLabel("Apellidos: ");
        lblSurname.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_lblSurname = new GridBagConstraints();
        gbc_lblSurname.anchor = GridBagConstraints.EAST;
        gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
        gbc_lblSurname.gridx = 1;
        gbc_lblSurname.gridy = 2;
        panelRegister.add(lblSurname, gbc_lblSurname);

        textSurname = new JTextField();
        textSurname.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_textSurname = new GridBagConstraints();
        gbc_textSurname.gridwidth = 3;
        gbc_textSurname.insets = new Insets(0, 0, 5, 5);
        gbc_textSurname.fill = GridBagConstraints.BOTH;
        gbc_textSurname.gridx = 2;
        gbc_textSurname.gridy = 2;
        panelRegister.add(textSurname, gbc_textSurname);
        textSurname.setColumns(10);

        JLabel lblUsername = new JLabel("Usuario: ");
        lblUsername.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_lblUsername = new GridBagConstraints();
        gbc_lblUsername.anchor = GridBagConstraints.EAST;
        gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
        gbc_lblUsername.gridx = 1;
        gbc_lblUsername.gridy = 3;
        panelRegister.add(lblUsername, gbc_lblUsername);

        textUsername = new JTextField();
        textUsername.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_textUsername = new GridBagConstraints();
        gbc_textUsername.fill = GridBagConstraints.BOTH;
        gbc_textUsername.insets = new Insets(0, 0, 5, 5);
        gbc_textUsername.gridx = 2;
        gbc_textUsername.gridy = 3;
        panelRegister.add(textUsername, gbc_textUsername);
        textUsername.setColumns(15);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.anchor = GridBagConstraints.EAST;
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.gridx = 1;
        gbc_lblPassword.gridy = 4;
        panelRegister.add(lblPassword, gbc_lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.insets = new Insets(0, 0, 5, 5);
        gbc_passwordField.fill = GridBagConstraints.BOTH;
        gbc_passwordField.gridx = 2;
        gbc_passwordField.gridy = 4;
        panelRegister.add(passwordField, gbc_passwordField);

        JLabel lblRepeatedPassword = new JLabel("Repetir:");
        lblRepeatedPassword.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_lblRepeatedPassword = new GridBagConstraints();
        gbc_lblRepeatedPassword.anchor = GridBagConstraints.EAST;
        gbc_lblRepeatedPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblRepeatedPassword.gridx = 3;
        gbc_lblRepeatedPassword.gridy = 4;
        panelRegister.add(lblRepeatedPassword, gbc_lblRepeatedPassword);

        repeatedPasswordField = new JPasswordField();
        repeatedPasswordField.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_repeatedPasswordField = new GridBagConstraints();
        gbc_repeatedPasswordField.insets = new Insets(0, 0, 5, 5);
        gbc_repeatedPasswordField.fill = GridBagConstraints.BOTH;
        gbc_repeatedPasswordField.gridx = 4;
        gbc_repeatedPasswordField.gridy = 4;
        panelRegister.add(repeatedPasswordField, gbc_repeatedPasswordField);

        JLabel lblMail = new JLabel("Mail: ");
        lblMail.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_lblMail = new GridBagConstraints();
        gbc_lblMail.anchor = GridBagConstraints.EAST;
        gbc_lblMail.insets = new Insets(0, 0, 5, 5);
        gbc_lblMail.gridx = 1;
        gbc_lblMail.gridy = 5;
        panelRegister.add(lblMail, gbc_lblMail);

        textMail = new JTextField();
        textMail.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_textMail = new GridBagConstraints();
        gbc_textMail.fill = GridBagConstraints.BOTH;
        gbc_textMail.gridwidth = 3;
        gbc_textMail.insets = new Insets(0, 0, 5, 5);
        gbc_textMail.gridx = 2;
        gbc_textMail.gridy = 5;
        panelRegister.add(textMail, gbc_textMail);
        textMail.setColumns(15);

        JLabel lblDateOfBirth = new JLabel("Año de nacimiento: ");
        lblDateOfBirth.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_lblDateOfBirth = new GridBagConstraints();
        gbc_lblDateOfBirth.anchor = GridBagConstraints.EAST;
        gbc_lblDateOfBirth.insets = new Insets(0, 0, 5, 5);
        gbc_lblDateOfBirth.gridx = 1;
        gbc_lblDateOfBirth.gridy = 6;
        panelRegister.add(lblDateOfBirth, gbc_lblDateOfBirth);

        dateOfBirth = new JDateChooser();
        dateOfBirth.getCalendarButton().setBackground(UIManager.getColor("InternalFrame.background"));
        dateOfBirth.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_dateOfBirth = new GridBagConstraints();
        gbc_dateOfBirth.insets = new Insets(0, 0, 5, 5);
        gbc_dateOfBirth.fill = GridBagConstraints.BOTH;
        gbc_dateOfBirth.gridx = 2;
        gbc_dateOfBirth.gridy = 6;
        panelRegister.add(dateOfBirth, gbc_dateOfBirth);

        final JButton btnExit = new JButton("Exit");
        btnExit.setForeground(new Color(255, 255, 255));
        btnExit.setBackground(new Color(204, 0, 0));
        btnExit.setPreferredSize(new Dimension(60, 30));
        btnExit.setBorder(new MatteBorder(1, 1, 1, 1, new Color(153, 0, 0)));
        btnExit.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                goToLoginWindow();
            }
        });

        GridBagConstraints gbc_btnExit = new GridBagConstraints();
        gbc_btnExit.insets = new Insets(0, 0, 5, 5);
        gbc_btnExit.gridx = 2;
        gbc_btnExit.gridy = 8;
        panelRegister.add(btnExit, gbc_btnExit);

        final JButton btnRegister = new JButton("Registrar");
        btnRegister.setPreferredSize(new Dimension(80, 30));
        btnRegister.setBorder(new MatteBorder(1, 1, 1, 1, UIUtils.getFocusedBorder()));
        panelRegister.getRootPane().setDefaultButton(btnRegister);
        btnRegister.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        btnRegister.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                resetCheckFields();
                register();
            }
        });

        GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
        gbc_btnRegistrar.insets = new Insets(0, 0, 5, 5);
        gbc_btnRegistrar.gridx = 4;
        gbc_btnRegistrar.gridy = 8;
        panelRegister.add(btnRegister, gbc_btnRegistrar);
    }

    /**
     * Comprueba que los campos de registro estén bien
     */
    private boolean checkFields() {
        boolean salida = true;
        boolean warningName = false;
        boolean warningSurname = false;
        if (textName.getText().trim().isEmpty()) {
            textName.setForeground(Color.RED);
            textName.setBorder(BorderFactory.createLineBorder(Color.RED));
            warningName = true;
            salida = false;
        }

        if (textSurname.getText().trim().isEmpty()) {
            textSurname.setForeground(Color.RED);
            textSurname.setBorder(BorderFactory.createLineBorder(Color.RED));
            warningSurname = true;
            salida = false;
        }

        if (warningName && warningSurname) {
            lblWarning.setText("Los campos Nombre y Apellidos estan incompletos");
        } else {
            if (warningName) lblWarning.setText("El campo Nombre esta incompleto");
            if (warningSurname) lblWarning.setText("El campo Apellidos esta incompleto");
            if (!warningName && !warningSurname) lblWarning.setText("");
        }


        //Comprobar formato contraseña
        String password = new String(passwordField.getPassword());
        String password2 = new String(repeatedPasswordField.getPassword());

        if (password.isEmpty()) {
            lblWarningPassword.setText("El campo Contraseña esta incompleto");
            passwordField.setForeground(Color.RED);
            passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
            if (password2.isEmpty()) {
                repeatedPasswordField.setForeground(Color.RED);
                repeatedPasswordField.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            salida = false;
        } else {
            if (password.length() < AppVideo.MIN_PASSWORD_LENGTH) {
                passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
                repeatedPasswordField.setBorder(BorderFactory.createLineBorder(Color.RED));
                //JOptionPane.showMessageDialog(frmRegister, "La contraseña debe tener al menos "+AppVideo.MIN_PASSLENGTH+" caracteres.\n","Registro", JOptionPane.ERROR_MESSAGE);
                lblWarningPassword.setText("La contraseña debe tener al menos " + AppVideo.MIN_PASSWORD_LENGTH + " caracteres");
                salida = false;
            } else {
                if (!password.equals(password2)) {
                    passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
                    repeatedPasswordField.setBorder(BorderFactory.createLineBorder(Color.RED));
                    //JOptionPane.showMessageDialog(frmRegister, "Las contraseñas no coinciden.\n","Registro", JOptionPane.ERROR_MESSAGE);
                    lblWarningPassword.setText("Las contraseñas no coinciden");
                    salida = false;
                } else lblWarningPassword.setText("");
            }
        }

        //Comprobar formato Mail
        if (textMail.getText().trim().isEmpty() || !textMail.getText().trim().contains("@")) {
            lblWarningMail.setText("El formato del Mail es incorrecto, ejemplo de uso: usuario@gmail.com");
            textMail.setForeground(Color.RED);
            textMail.setBorder(BorderFactory.createLineBorder(Color.RED));
            salida = false;
        } else lblWarningMail.setText("");

        //Comprobar formato Usuario y que no exista ya ese Usuario
        if (textUsername.getText().trim().isEmpty()) {
            lblWarningUser.setText("El campo Usuario esta incompleto");
            textUsername.setForeground(Color.RED);
            textUsername.setBorder(BorderFactory.createLineBorder(Color.RED));
            salida = false;
        } else {
            if (AppVideo.getInstance().isUserRegistered(textUsername.getText())) {
                textUsername.setBorder(BorderFactory.createLineBorder(Color.RED));
                //JOptionPane.showMessageDialog(frmRegister, "Ese nombre de usuario ya esta en uso, prueba con otro.\n","Registro", JOptionPane.ERROR_MESSAGE);
                lblWarningUser.setText("Ese nombre de usuario ya esta en uso, prueba con otro");
                salida = false;
            } else lblWarningUser.setText("");
        }

        //Comprobar formato dateOfBirth
        try {
            if (!parseLocalDate(dateOfBirth).toString().matches("\\d{4}-\\d{2}-\\d{2}")) {
                dateOfBirth.setBorder(BorderFactory.createLineBorder(Color.RED));
                dateOfBirth.setForeground(Color.RED);
                //JOptionPane.showMessageDialog(frmRegister, "El formato de la fecha es incorrecto.\n","Registro", JOptionPane.ERROR_MESSAGE);
                lblWarningDate.setText("El formato de la fecha es incorrecto");
                salida = false;
            } else lblWarningDate.setText("");
        } catch (NullPointerException e) {
            //JOptionPane.showMessageDialog(frmRegister, "El formato de la fecha es incorrecto.\n","Registro", JOptionPane.ERROR_MESSAGE);
            lblWarningDate.setText("El campo Fecha esta incompleto");
        }

        frmRegister.revalidate();
        frmRegister.pack();

        return salida;
    }

    public void resetCheckFields() {
        textName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textName.setForeground(Color.BLACK);
        textSurname.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textSurname.setForeground(Color.BLACK);
        textMail.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textMail.setForeground(Color.BLACK);
        textUsername.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textUsername.setForeground(Color.BLACK);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        passwordField.setForeground(Color.BLACK);
        repeatedPasswordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        repeatedPasswordField.setForeground(Color.BLACK);
    }

    /**
     * Cambia el formato de la fecha a LocalDate
     */
    public LocalDate parseLocalDate(JDateChooser myDate) {
        return myDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Vuelve a la ventana de Login
     */
    private void goToLoginWindow() {
        LoginWindow login = new LoginWindow();
        frmRegister.dispose();
        login.showWindow();
    }

    /**
     * Registra al usuario si se cumplen todas las condiciones
     */
    private void register() {
        String username = textUsername.getText();
        String password = new String(passwordField.getPassword());
        String mail = textMail.getText();
        String name = textName.getText();
        String surname = textSurname.getText();
        if (checkFields()) {
            LocalDate date = parseLocalDate(dateOfBirth);
            boolean isRegistered = AppVideo.getInstance().registerUser(name, surname, mail, username, password,
                    date.toString());
            if (isRegistered) {
                JOptionPane.showMessageDialog(frmRegister, "Usuario registrado correctamente.", "Registro", JOptionPane.INFORMATION_MESSAGE);
                goToLoginWindow();
            } else {
                JOptionPane.showMessageDialog(frmRegister, "No se ha podido llevar a cabo el registro.\n", "Registro", JOptionPane.ERROR_MESSAGE);
                frmRegister.setTitle("Login Gestor Eventos");
            }
        }
    }
}
