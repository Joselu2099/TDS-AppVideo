package gui;

import gui.VideoPreview.VideoPreviewListPanel;
import model.Playlist;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.AppVideo;
import gui.Util.SwapLayout;
import model.Video;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class CreatePlaylistPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Playlist createdPlaylist;
	private VideoPreviewListPanel playlistPanel;
	private JPanel createPanel;
	private PlaylistEditorPanel videoSelector;
	private JLabel lblPlaylistName;

	/**
	 * Create the panel.
	 */
	public CreatePlaylistPanel(JFrame parent) {
		JPanel createPlaylistPanel = new JPanel();
		createPlaylistPanel.setLayout(new SwapLayout(createPlaylistPanel));
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		setLayout(new BorderLayout(0, 0));

		createPanel = new JPanel();
		createPanel.setLayout(new BoxLayout(createPanel,BoxLayout.Y_AXIS));
		add(createPanel, BorderLayout.NORTH);
		JPanel editorPanel = new JPanel();
		createPanel.add(editorPanel);

		JButton btnCreateButton = new JButton("Create Playlist");
		btnCreateButton.addActionListener(l -> {
			String playlistTitle = (String) JOptionPane.showInputDialog(parent, "Intruduce un nombre para tu playlist: ",
						"Creating playlist", JOptionPane.PLAIN_MESSAGE, null, null, "");
			if(!AppVideo.getInstance().isPlaylistInCurrentUser(playlistTitle) && playlistTitle!=null
					&& playlistTitle.length()>0) {
				createdPlaylist = new Playlist(playlistTitle);
				goToPlaylistEditorPanel();
				showPlaylistPreview(createdPlaylist);
				lblPlaylistName.setText(playlistTitle);
			}else if(playlistTitle!=null)JOptionPane.showMessageDialog(parent.getContentPane(),
					"Prueba otro nombre para tu playlist",
					"Playlist title", JOptionPane.ERROR_MESSAGE);
		});
		editorPanel.add(btnCreateButton);

		JButton btnEditButton = new JButton("Edit Playlist");
		btnEditButton.addActionListener(l -> {
			if(createdPlaylist!=null){
				goToPlaylistEditorPanel();
				createdPlaylist.setListOfVideos(videoSelector.getSelectedVideos());
				showPlaylistPreview(createdPlaylist);
			}else JOptionPane.showMessageDialog(parent.getContentPane(),
					"No has creado ninguna lista para poder editarla",
					"Playlist editor", JOptionPane.ERROR_MESSAGE);
		});
		editorPanel.add(btnEditButton);

		JPanel playlistNamePanel = new JPanel();
		lblPlaylistName = new JLabel("");
		lblPlaylistName.setFont(new Font("Gill Sans MT", Font.BOLD, 18));
		playlistNamePanel.add(lblPlaylistName);
		createPanel.add(playlistNamePanel);

		playlistPanel = new VideoPreviewListPanel(new ArrayList<>(), vid->{
			VideoPlayerWindow player = new VideoPlayerWindow(vid);
			player.showPlayer(parent);
		});

		JScrollPane scrollPane = new JScrollPane(playlistPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
		add(scrollPane,BorderLayout.CENTER);
	}

	public void showPlaylistPreview(Playlist playlist) {
		playlistPanel.setPrewviewList(playlist.getListOfVideos());
	}

	public void goToPlaylistEditorPanel(){
		JFrame editorFrame = new JFrame();
		videoSelector = new PlaylistEditorPanel(editorFrame, createdPlaylist);
		editorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		editorFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				AppVideoWindow.getActiveInstance().showWindow();
			}
		});
		editorFrame.setContentPane(videoSelector);
		editorFrame.setBounds(0, 0, 800, 600);
		AppVideoWindow.getActiveInstance().hideWindow();
		editorFrame.setLocationRelativeTo(null);
		editorFrame.setVisible(true);
	}

	public void setCreatedPlaylist(Playlist playlist){
		this.createdPlaylist = playlist;
		showPlaylistPreview(createdPlaylist);
	}
}

	
