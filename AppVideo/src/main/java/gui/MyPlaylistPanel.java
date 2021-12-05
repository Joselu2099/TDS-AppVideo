package gui;

import controller.AppVideo;
import gui.Util.SwapLayout;
import gui.VideoPreview.VideoPreviewListPanel;
import model.Playlist;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	/**
	 * Create the panel.
	 */
	public MyPlaylistPanel(JFrame parent ) {
		mainPanel = new JPanel();
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		this.parent = parent;
		setLayout(new BorderLayout());
		
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
		mainPanel.setLayout(new SwapLayout(mainPanel));
		add(mainPanel,BorderLayout.CENTER);
	}

	public void setPlaylistsPanels(List<Playlist> playlists){
		JPanel list = new JPanel();
		list.setLayout(new BoxLayout(list,BoxLayout.Y_AXIS));
		list.setBorder(new BevelBorder(BevelBorder.LOWERED,Color.RED,Color.BLACK));
		playlistsPanels = playlists.stream().collect(Collectors.toMap(Playlist::getTitle,playlist -> {
			VideoPreviewListPanel previewListPanel = new VideoPreviewListPanel(playlist.getListOfVideos(),video -> {
				VideoPlayerWindow player = new VideoPlayerWindow(video);
				player.showPlayer(parent);
			});
			previewListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			return previewListPanel;
		}));

		playlistsPanels.values().stream().forEach(list::add);

		JScrollPane scrollPane = new JScrollPane(list,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(scrollPane);
	}
	
}

	
