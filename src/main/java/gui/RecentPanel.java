package gui;

import gui.VideoPreview.VideoPreviewListPanel;
import model.Video;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

public class RecentPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JFrame parent;
	VideoPreviewListPanel videoPreviewListPanel;


	/**
	 * Create the panel.
	 */
	public RecentPanel(JFrame parent ,List<Video> list ) {
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		this.parent = parent;
		showVideoPreview(list);
		setLayout(new BorderLayout(0, 0));
	}

	public void showVideoPreview(List<Video> videoList) {
		if (videoPreviewListPanel == null){
			videoPreviewListPanel = new VideoPreviewListPanel(videoList,vid->{
				VideoPlayerWindow player = new VideoPlayerWindow(vid);
				player.showPlayer(parent);
			});
		}else{
			videoPreviewListPanel.setPrewviewList(videoList);
		}
	}
}

	
