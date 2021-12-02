package gui;

import model.Playlist;

import javax.swing.*;

import controller.AppVideo;
import gui.Util.SwapLayout;

import java.awt.*;
import java.util.List;

public class CreatePlaylistPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	JPanel vidPanel;
	JFrame parent;
	Playlist createdPlaylist;
	
	
	/**
	 * Create the panel.
	 */
	public CreatePlaylistPanel(JFrame parent) {
		vidPanel = new JPanel();
		vidPanel.setLayout(new SwapLayout(vidPanel));
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		this.parent = parent;
		setLayout(new BorderLayout(0, 0));
		
		JPanel searchPanel = new JPanel();
		add(searchPanel, BorderLayout.NORTH);
		
		textField = new JTextField();
		searchPanel.add(textField);
		textField.setColumns(30);
		
		JButton btnSearchButton = new JButton("BUSCAR");
		btnSearchButton.addActionListener(l->{PlaylistCreatorWindow playlistCreatorWindow = new PlaylistCreatorWindow();});
		searchPanel.add(btnSearchButton);

		showPlaylistPreview(filteredPlaylist);
		JScrollPane scrollPane = new JScrollPane(vidPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
	}
}

	
