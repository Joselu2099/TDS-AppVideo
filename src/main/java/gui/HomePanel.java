package gui;

import gui.Util.SwapLayoutPanelWrapper;
import gui.VideoPreview.VideoPreviewListPanel;
import model.Video;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;
import controller.AppVideo;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private List<Video> repoList;
	private List<Video> currentList;
	JFrame parent;
	SwapLayoutPanelWrapper vidPanel = new SwapLayoutPanelWrapper();
	
	/**
	 * Create the panel.
	 */
	public HomePanel(JFrame parent ,List<Video> list ) {
		repoList = list;
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
		
		JButton btnLoadVideos = new JButton("Load Videos");
		btnLoadVideos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AppVideo.getInstance().loadVideos("xml/videos.xml");
			}
		});
		searchPanel.add(btnLoadVideos);
	}

	public void showVideoPreview(List<Video> videoList) {
		currentList = videoList;
		vidPanel.swap(new VideoPreviewListPanel(videoList,vid->{
			VideoPlayerWindow player = new VideoPlayerWindow(vid);
			player.showPlayer(parent);
		}));
	}
	
	public void filterByName(String text){
		currentList = repoList.stream()
				.filter(s-> s.getTitle().contains(text))
				.collect(Collectors.toList());
		showVideoPreview(currentList);
	}
	
	public void updateVideoList(@NotNull List<Video> videoList){
		this.repoList = videoList;
		showVideoPreview(videoList);
	}
}

	
