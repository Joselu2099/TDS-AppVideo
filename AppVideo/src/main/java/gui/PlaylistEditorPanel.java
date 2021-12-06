package gui;

import controller.AppVideo;
import gui.VideoPreview.SelectVideoPrewviewListPanel;
import model.Label;
import model.Playlist;
import model.Video;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.*;

public class PlaylistEditorPanel extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private final SelectVideoPrewviewListPanel vidPanel;
	private Set<Label> labelSet = new TreeSet<>();
	private List<Video> selectedVideos;

	/**
	 * Show the jdialog.
	 */
	public void showPlaylistEditorPanel(JFrame parent){
		parent.setVisible(false);
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);
		parent.setVisible(true);
	}

	/**
	 * Create the jdialog.
	 */
	public PlaylistEditorPanel(Playlist playlist){
		selectedVideos=playlist.getListOfVideos();
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				AppVideoWindow.getActiveInstance().showWindow();
			}
		});
		setBounds(0, 0, 800, 600);
		setIconImage(Toolkit.getDefaultToolkit().getImage(PlaylistEditorPanel.class.getResource("/images/multimediavideoplayer_128px.png")));
		setMinimumSize(new Dimension(720, 480));

		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
		JPanel searchBoxPanel = new JPanel();
		searchPanel.add(searchBoxPanel);
		LabelEditorPanel labelManager = new LabelEditorPanel(labelSet,
				label -> {
					labelSet.add(label);
					AppVideo.getInstance().searchVideos(textField.getText(), labelSet);
				},
				label -> {
					labelSet.remove(label);
					AppVideo.getInstance().searchVideos(textField.getText(), labelSet);
				}
		);
		searchPanel.add(labelManager);
		add(searchPanel, BorderLayout.NORTH);

		textField = new JTextField();
		searchBoxPanel.add(textField);
		textField.setColumns(30);

		vidPanel = new SelectVideoPrewviewListPanel(AppVideo.getInstance().getFilteredVideoList(), playlist.getListOfVideos(), vid->{
			if(selectedVideos.contains(vid)){
				selectedVideos.remove(vid);
			}else{
				selectedVideos.add(vid);
			}
		});

		JButton btnSearchButton = new JButton("Buscar Video");
		btnSearchButton.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		btnSearchButton.addActionListener(l -> showVideoPreview(AppVideo.getInstance().searchVideos(textField.getText(), labelSet)));
		searchBoxPanel.add(btnSearchButton);

		JButton btnRemove = new JButton("Borrar Playlist");
		btnRemove.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		btnRemove.addActionListener(l -> {
			playlist.setListOfVideos(selectedVideos);
			if(AppVideo.getInstance().isPlaylistInCurrentUser(playlist.getTitle())) {
				AppVideoWindow.getActiveInstance().getCreatePlaylistPanel().setCurrentPlaylist(null);
				AppVideo.getInstance().removePlaylist(playlist.getTitle());
				dispose();
			}else JOptionPane.showMessageDialog(this,
					"La lista no puede ser eliminada ya que no ha sido guardada previamente :( ",
					"Playlist editor", JOptionPane.ERROR_MESSAGE);
		});
		searchBoxPanel.add(btnRemove);

		JButton btnSave = new JButton("Guardar Playlist");
		btnSave.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		btnSave.addActionListener(l -> {
			playlist.setListOfVideos(selectedVideos);
			if(!AppVideo.getInstance().createPlaylist(playlist))
				AppVideo.getInstance().updatePlaylist(playlist);
			AppVideoWindow.getActiveInstance().getCreatePlaylistPanel().setCurrentPlaylist(playlist);
			dispose();
		});
		searchBoxPanel.add(btnSave);

		JScrollPane scrollPane = new JScrollPane(vidPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		add(scrollPane, BorderLayout.CENTER);
	}

	public List<Video> getSelectedVideos(){
		return this.selectedVideos;
	}

	public void showVideoPreview(List<Video> videoList) {
		vidPanel.setPrewviewList(videoList);
	}
}
	
