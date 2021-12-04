package gui;

import controller.AppVideo;
import gui.VideoPreview.VideoPreviewListPanel;
import model.Label;
import model.Playlist;
import model.Video;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class PlaylistEditorPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private VideoPreviewListPanel vidPanel;
	private Set<Label> labelSet = new TreeSet<>();
	private List<Video> selectedVideos;

	public String getSearchText() {
		return textField.getText();
	}

	public Set<Label> getSearchLabelSet() {
		return Collections.unmodifiableSet(labelSet);
	}

	/**
	 * Create the panel.
	 */
	public PlaylistEditorPanel(JFrame parent, List<Video> videoList) {
		selectedVideos=videoList;
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		setLayout(new BorderLayout());

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

		vidPanel = new VideoPreviewListPanel(new ArrayList<>(), vid->{
			if(selectedVideos.contains(vid)){
				//TODO mostrar graficamente que el video ha sido deseleccionado
				selectedVideos.remove(vid);
				System.out.println("Video " + vid.getTitle() + " eliminado de playlist");
			}else{
				//TODO mostrar graficamente que el video ha sido seleccionado
				selectedVideos.add(vid);
				System.out.println("Video " + vid.getTitle() + " añadido a playlist");
			}
		});
		showVideoPreview(AppVideo.getInstance().getFilteredVideoList());

		JButton btnSearchButton = new JButton("Buscar");
		btnSearchButton.addActionListener(l -> {
			showVideoPreview(AppVideo.getInstance().searchVideos(textField.getText(), labelSet));
		});
		searchBoxPanel.add(btnSearchButton);

		JButton btnSave = new JButton("Guardar");
		btnSave.addActionListener(l -> {
			AppVideo.getInstance().getAppVideoWindow().getCreatePlaylistPanel().setCreatedPlaylist(selectedVideos);
			AppVideo.getInstance().getAppVideoWindow().showWindow();

			parent.dispose();
		});
		searchBoxPanel.add(btnSave);

		JScrollPane scrollPane = new JScrollPane(vidPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		add(scrollPane, BorderLayout.CENTER);
	}

	public void goToCreatePlaylistPanel(JFrame parent, JFrame createPlaylist, List<Video> videoList){

	}

	public List<Video> getSelectedVideos(){
		return this.selectedVideos;
	}

	public void showVideoPreview(List<Video> videoList) {
		vidPanel.setPrewviewList(videoList);
	}
}
	
