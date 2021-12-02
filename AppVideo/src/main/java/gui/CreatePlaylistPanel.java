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

		JButton btnCreateButton = new JButton("CREAR");
		btnCreateButton.addActionListener(l -> {
			String playlistTitle = null;
			while (playlistTitle == null) {
				playlistTitle = (String) JOptionPane.showInputDialog(parent, "Intruduce un nombre para tu playlist: ",
						"Creating playlist", JOptionPane.PLAIN_MESSAGE, null, null, "");
				if(!AppVideo.getInstance().isPlaylistRegistered(playlistTitle) && playlistTitle!=null) {
					AppVideo.getInstance().createPlaylist(playlistTitle);
					createdPlaylist = AppVideo.getInstance().getCurrentUser().getPlaylist(playlistTitle);
					System.out.println(createdPlaylist);
				}
			}
			showPlaylistPreview(createdPlaylist);
		});
		searchPanel.add(btnCreateButton);

		JScrollPane scrollPane = new JScrollPane(vidPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void showPlaylistPreview(Playlist playlist) {
		//TODO mostrar playlist editable

		/*
		vidPanel.swap(new VideoPreviewListPanel(playlistList,vid->{
			VideoPlayerWindow player = new VideoPlayerWindow(vid);
			player.showPlayer(parent);
		}));
		*/
	}
}

	
