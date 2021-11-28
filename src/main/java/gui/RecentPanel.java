package gui;

import gui.Util.SwapLayout;
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

public class RecentPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Video> repoList;
	private List<Video> currentList;
	JFrame parent;
	JPanel vidPanel;
	
	/**
	 * Create the panel.
	 */
	public RecentPanel(JFrame parent ,List<Video> list ) {
		repoList = list;
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		this.parent = parent;
		setLayout(new BorderLayout(0, 0));
		vidPanel = new JPanel();
		vidPanel.setLayout(new SwapLayout(vidPanel));
	}

	public void showVideoPreview(List<Video> videoList) {
		currentList = videoList;
		vidPanel.add(new VideoPreviewListPanel(videoList,vid->{
			VideoPlayerWindow player = new VideoPlayerWindow(vid);
			player.showPlayer(parent);
		}));
	}
	
	public void updateVideoList(@NotNull List<Video> videoList){
		this.repoList = videoList;
		showVideoPreview(videoList);
	}
}

	
