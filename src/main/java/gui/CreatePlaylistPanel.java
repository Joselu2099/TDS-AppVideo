package gui;

import gui.Util.SwapLayoutPanelWrapper;
import model.Video;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CreatePlaylistPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Video> repoList;
	private List<Video> currentList;
	JFrame parent;
	SwapLayoutPanelWrapper vidPanel = new SwapLayoutPanelWrapper();
	
	/**
	 * Create the panel.
	 */
	public CreatePlaylistPanel(JFrame parent ,List<Video> list ) {
		repoList = list;
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		this.parent = parent;
		setLayout(new BorderLayout(0, 0));
	}
}

	
