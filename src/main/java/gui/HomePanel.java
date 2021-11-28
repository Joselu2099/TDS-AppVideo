package gui;

import gui.Util.SwapLayoutPanelWrapper;
import gui.VideoPreview.VideoPreviewListPanel;
import model.Video;
import javax.swing.*;

import controller.AppVideo;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePanel extends AppVideoPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;

	/**
	 * Create the panel.
	 */
	JFrame parent;
	List<Video> repoList;
	List<Video> currentList;
	SwapLayoutPanelWrapper vidPanel = new SwapLayoutPanelWrapper();
	public HomePanel(JFrame parent ,List<Video> list ) {
		
		super(parent, list);
		
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
		super.vidPanel.swap(new VideoPreviewListPanel(videoList,vid->{
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
}

	
