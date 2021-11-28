package gui;

import gui.Util.SwapLayoutPanelWrapper;
import model.Playlist;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class MyPlaylistPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private List<Playlist> repoPlaylists;
	private List<Playlist> currentPlaylists;
	SwapLayoutPanelWrapper vidPanel = new SwapLayoutPanelWrapper();
	JFrame parent;
	
	/**
	 * Create the panel.
	 */
	public MyPlaylistPanel(JFrame parent ,List<Playlist> list ) {
		repoPlaylists = list;
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
		btnSearchButton.addActionListener(l->filterByName(textField.getText()));
		searchPanel.add(btnSearchButton);
	}

	public void showPlaylistPreview(List<Playlist> playlistList) {
		currentPlaylists = playlistList;
		
		//TODO mostrar playlist
		
		/*
		vidPanel.swap(new VideoPreviewListPanel(playlistList,vid->{
			VideoPlayerWindow player = new VideoPlayerWindow(vid);
			player.showPlayer(parent);
		}));
		*/
	}
	
	public void filterByName(String text){
		currentPlaylists = repoPlaylists.stream()
				.filter(s-> s.getTitle().contains(text))
				.collect(Collectors.toList());
		showPlaylistPreview(currentPlaylists);
	}
	
	public void updateVideoList(@NotNull List<Playlist> playlistList){
		this.repoPlaylists = playlistList;
		showPlaylistPreview(playlistList);
	}
}

	
