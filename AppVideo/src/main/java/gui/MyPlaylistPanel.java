package gui;

import controller.AppVideo;
import gui.Util.SwapLayout;
import gui.Util.WrapLayout;
import gui.VideoPreview.PlayListVideoPreviewPanel;
import gui.VideoPreview.VideoPreviewListPanel;
import javafx.scene.shape.Box;
import model.Playlist;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
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
	private Map<String, Component> playlistsPanels = new HashMap<>();
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
		
		JButton btnSearchButton = new JButton("Buscar Playlist");
		btnSearchButton.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		btnSearchButton.addActionListener(l->{filteredPlaylists = AppVideo.getInstance().searchPlaylists(textField.getText());
											setPlaylistsPanels(filteredPlaylists);});
		searchPanel.add(btnSearchButton);

		AppVideo.getInstance().subscribeFilteredVideoChange(()->{
			setPlaylistsPanels(AppVideo.getInstance().searchPlaylists(textField.getText()));
		});

		setPlaylistsPanels(AppVideo.getInstance().getCurrentPlaylists());
		mainPanel.setLayout(new SwapLayout(mainPanel));
		JScrollPane scrollPane = new JScrollPane(mainPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		add(scrollPane, BorderLayout.CENTER);
	}

	public void setPlaylistsPanels(List<Playlist> playlists){
		JPanel list = new JPanel();
		list.setLayout(new BoxLayout(list,BoxLayout.Y_AXIS));
		playlistsPanels = playlists.stream().collect(Collectors.toMap(Playlist::getTitle,playlist -> {
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			JLabel label = new JLabel("  "+playlist.getTitle());
			label.setFont(new Font("Gill Sans MT", Font.BOLD, 18));
			panel.add(label,BorderLayout.NORTH);
			VideoPreviewListPanel previewListPanel = new PlayListVideoPreviewPanel(playlist.getListOfVideos(), video -> {
				VideoPlayerWindow player = new VideoPlayerWindow(video);
				player.showPlayer(parent);
			});
//			previewListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			panel.add(previewListPanel,BorderLayout.CENTER);
			return new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		}));

		playlistsPanels.values().forEach(list::add);
		mainPanel.add(list);
	}
	
}

	
