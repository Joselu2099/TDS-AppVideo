package gui;

import gui.Util.SwapLayoutPanelWrapper;
import gui.VideoPreview.VideoPreviewListPanel;
import model.Video;
import org.jetbrains.annotations.NotNull;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class AppVideoPanel extends JPanel {

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
	
	public AppVideoPanel(JFrame parent ,List<Video> list ) {
		repoList = list;
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		this.parent = parent;
		setLayout(new BorderLayout(0, 0));
		
		showVideoPreview(repoList);
		JScrollPane scrollPane = new JScrollPane(vidPanel.getPanel(),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
	}

	public abstract void showVideoPreview(List<Video> videoList);

	public void updateVideoList(@NotNull List<Video> videoList){
		this.repoList = videoList;
		showVideoPreview(videoList);
	}

}
