package gui;

import controller.AppVideo;
import gui.VideoPreview.VideoPreviewListPanel;
import model.Playlist;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPlaylistPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private JPanel mainPanel;
	private JFrame parent;
	private Map<String, VideoPreviewListPanel> playlistsPanels = new HashMap<String, VideoPreviewListPanel>();
	private List<Playlist> filteredPlaylists = new ArrayList<>();
	private JScrollPane scrollPane;
	
	/**
	 * Create the panel.
	 */
	public MyPlaylistPanel(JFrame parent ) {
		mainPanel = new JPanel();
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		this.parent = parent;
		setLayout(new BorderLayout(0, 0));
		
		JPanel searchPanel = new JPanel();
		add(searchPanel, BorderLayout.NORTH);
		
		textField = new JTextField();
		searchPanel.add(textField);
		textField.setColumns(30);
		
		JButton btnSearchButton = new JButton("Buscar");
		btnSearchButton.addActionListener(l->{filteredPlaylists = AppVideo.getInstance().searchPlaylists(textField.getText());
											setPlaylistsPanels(filteredPlaylists);});
		searchPanel.add(btnSearchButton);

		setPlaylistsPanels(AppVideo.getInstance().getCurrentPlaylists());
		showPlaylistsPreview();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	}

	public void showPlaylistsPreview() {
		playlistsPanels.forEach((key, value) -> value.setPrewviewList(AppVideo.getInstance().getPlaylist(key).getListOfVideos()));
	}
	
	public void setPlaylistsPanels(List<Playlist> playlists){
		clearMainPanel();
		playlistsPanels = new HashMap<>();
		playlists.stream()
		.map(Playlist::getTitle)
		.forEach(p -> {
			playlistsPanels.put(p, new VideoPreviewListPanel(new ArrayList<>(), vid->{
				VideoPlayerWindow player = new VideoPlayerWindow(vid);
				player.showPlayer(parent);
			}));
			mainPanel.add(playlistsPanels.get(p));
			if(scrollPane!=null)
				remove(scrollPane);
			scrollPane = new JScrollPane(mainPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			add(scrollPane, BorderLayout.CENTER);
		});
		showPlaylistsPreview();
		revalidate();
		repaint();
	}
	
	public void clearMainPanel() {
		playlistsPanels.values().forEach(panel -> mainPanel.remove(panel));
	}
}

	
