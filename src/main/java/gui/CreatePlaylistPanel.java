package gui;

import model.Playlist;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CreatePlaylistPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Playlist> repoPlaylists;
	private List<Playlist> currentPlaylists;
	JFrame parent;
	
	/**
	 * Create the panel.
	 */
	public CreatePlaylistPanel(JFrame parent ,List<Playlist> list ) {
		repoPlaylists = list;
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		this.parent = parent;
		setLayout(new BorderLayout(0, 0));
	}
}

	
