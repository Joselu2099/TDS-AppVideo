package gui;

import gui.VideoPreview.VideoPreviewListPanel;
import model.Playlist;
import javax.swing.*;
import controller.AppVideo;
import gui.Util.SwapLayout;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class CreatePlaylistPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Playlist createdPlaylist;
	private JPanel createPlaylistPanel;
	private VideoPreviewListPanel playlistPanel;
	private PlaylistEditorPanel videoSelector;

	/**
	 * Create the panel.
	 */
	public CreatePlaylistPanel(JFrame parent) {
		createPlaylistPanel = new JPanel();
		createPlaylistPanel.setLayout(new SwapLayout(createPlaylistPanel));
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		setLayout(new BorderLayout(0, 0));

		JPanel createPanel = new JPanel();
		createPanel.setLayout(new BoxLayout(createPanel,BoxLayout.Y_AXIS));
		add(createPanel, BorderLayout.NORTH);
		JPanel editorPanel = new JPanel();
		createPanel.add(editorPanel);

		JButton btnCreateButton = new JButton("CREAR");
		btnCreateButton.addActionListener(l -> {
			String playlistTitle = (String) JOptionPane.showInputDialog(parent, "Intruduce un nombre para tu playlist: ",
						"Creating playlist", JOptionPane.PLAIN_MESSAGE, null, null, "");
			if(!AppVideo.getInstance().isPlaylistRegistered(playlistTitle) && playlistTitle!=null
					&& playlistTitle.length()>0) {
				AppVideo.getInstance().createPlaylist(playlistTitle);
				createdPlaylist = AppVideo.getInstance().getCurrentUser().getPlaylist(playlistTitle);
				editPlaylist();
				System.out.println(createdPlaylist);
				showPlaylistPreview(createdPlaylist);
				//TODO temporal
				AppVideo.getInstance().removePlaylist(playlistTitle);
			}else if(playlistTitle!=null)JOptionPane.showMessageDialog(parent.getContentPane(),
					"Prueba otro nombre para tu playlist",
					"Playlist title", JOptionPane.ERROR_MESSAGE);
		});
		editorPanel.add(btnCreateButton);

		JButton btnEditButton = new JButton("Edit Playlist");
		btnEditButton.addActionListener(l -> {
			editPlaylist();
		});
		editorPanel.add(btnEditButton);

		playlistPanel = new VideoPreviewListPanel(new ArrayList<>(), vid->{
			VideoPlayerWindow player = new VideoPlayerWindow(vid);
			player.showPlayer(parent);
		});

		JScrollPane scrollPane = new JScrollPane(playlistPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane,BorderLayout.CENTER);
	}

	public void showPlaylistPreview(Playlist playlist) {
		playlistPanel.setPrewviewList(playlist.getListOfVideos());
	}

	public void editPlaylist(){
		JFrame editorFrame = new JFrame();
		videoSelector = new PlaylistEditorPanel(editorFrame, createdPlaylist);
		editorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		editorFrame.setContentPane(videoSelector);
		editorFrame.setBounds(0, 0, 800, 600);
		editorFrame.setLocationRelativeTo(null);
		editorFrame.setVisible(true);
	}

	public void setCreatedPlaylist(Playlist playlist){
		this.createdPlaylist = playlist;
		showPlaylistPreview(playlist);
	}
}

	
