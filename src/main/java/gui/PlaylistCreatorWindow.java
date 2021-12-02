package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PlaylistCreatorWindow extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField playlistName;

    public PlaylistCreatorWindow() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    	JPanel playlistCreatorPanel = new JPanel();
    	playlistCreatorPanel.setBorder(new EmptyBorder(15,15,15,15));
        setBounds(100, 100, 780, 520);

        setSize( 780, 520);
    	getContentPane().add(playlistCreatorPanel, BorderLayout.CENTER);

    	playlistCreatorPanel.setLayout(new BoxLayout(playlistCreatorPanel, BoxLayout.Y_AXIS));
    	
    	JLabel lblName = new JLabel("Playlist title: ");
        lblName.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_lblName = new GridBagConstraints();
        gbc_lblName.anchor = GridBagConstraints.EAST;
        gbc_lblName.insets = new Insets(0, 0, 5, 5);
        gbc_lblName.gridx = 1;
        gbc_lblName.gridy = 1;
        playlistCreatorPanel.add(lblName, gbc_lblName);

        playlistName = new JTextField();
        playlistName.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
        GridBagConstraints gbc_textName = new GridBagConstraints();
        gbc_textName.gridwidth = 3;
        gbc_textName.insets = new Insets(0, 0, 5, 5);
        gbc_textName.fill = GridBagConstraints.BOTH;
        gbc_textName.gridx = 2;
        gbc_textName.gridy = 1;
        playlistCreatorPanel.add(playlistName, gbc_textName);
        playlistName.setColumns(10);
    }
}

