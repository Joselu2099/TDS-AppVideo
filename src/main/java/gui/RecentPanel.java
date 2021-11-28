package gui;

import gui.Util.SwapLayoutPanelWrapper;
import gui.VideoPreview.VideoPreviewListPanel;
import model.Video;
import javax.swing.*;
import java.util.List;


public class RecentPanel extends AppVideoPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the panel.
	 */
	JFrame parent;
	List<Video> repoList;
	List<Video> currentList;
	SwapLayoutPanelWrapper vidPanel = new SwapLayoutPanelWrapper();
	
	public RecentPanel(JFrame parent ,List<Video> list ) {
		super(parent, list);
	}
	
	public void showVideoPreview(List<Video> videoList) {
		currentList = videoList;
		super.vidPanel.swap(new VideoPreviewListPanel(videoList,vid->{
			VideoPlayerWindow player = new VideoPlayerWindow(vid);
			player.showPlayer(parent);
		}));
	}

	
}
